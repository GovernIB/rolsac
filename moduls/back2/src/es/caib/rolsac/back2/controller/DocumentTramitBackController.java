package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.back2.util.UploadUtil;
import es.indra.rol.sac.integracion.traductor.Traductor;

/**
 * Servlet para la gestión de documentos y formularios de un tr�mite.
 */
@Controller
@RequestMapping("/documentsTramit/")
public class DocumentTramitBackController extends ArchivoController {

    private static Log log = LogFactory.getLog(DocumentTramitBackController.class);

    private MessageSource messageSource = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {

        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/carregarDocumentTramit.do")
    public @ResponseBody
    Map<String, Object> carregarDocument(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();

        try {
            Long id = new Long(request.getParameter("id"));
            TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();

            DocumentTramit doc = tramiteDelegate.obtenirDocument(id);

            Map<String, Object> mapDoc = new HashMap<String, Object>();
            IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
            List<String> idiomas = idiomaDelegate.listarLenguajes();
            TraduccionDocumento traDoc;

            for (String idioma : idiomas) {
                traDoc = (TraduccionDocumento) doc.getTraduccion(idioma);
                if (traDoc != null) {
                    if (traDoc.getTitulo() != null) {
                        mapDoc.put("idioma_titol_" + idioma, traDoc.getTitulo());
                    } else {
                        mapDoc.put("idioma_titol_" + idioma, "");
                    }

                    if (traDoc.getDescripcion() != null) {
                        mapDoc.put("idioma_descripcio_" + idioma, traDoc.getDescripcion());
                    } else {
                        mapDoc.put("idioma_descripcio_" + idioma, "");
                    }

                    // Archivo
                    if (traDoc.getArchivo() != null) {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "documentsTramit/archivo.do?id=" + doc.getId()
                            + "&lang=" + idioma);
                        mapDoc.put("idioma_nom_arxiu_" + idioma, traDoc.getArchivo().getNombre());
                    } else {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "");
                        mapDoc.put("idioma_nom_arxiu_" + idioma, "");
                    }
                }
            }

            mapDoc.put("item_id", doc.getId());
            resultats.put("documentTramit", mapDoc);
            resultats.put("id", doc.getId());

        } catch (NumberFormatException nfe) {
            log.error("El id del document no es n�meric: " + nfe.toString());
            resultats.put("id", -3);
        } catch (DelegateException dEx) {
            if (dEx.isSecurityException()) {
                log.error("Error de permisos: " + dEx.toString());
                resultats.put("id", -1);
            } else {
                log.error("Error: " + dEx.toString());
                resultats.put("id", -2);
            }
        }

        return resultats;
    }

    @RequestMapping(value = "/guardarDocumentTramit.do", method = POST)
    public ResponseEntity<String> guardar(HttpServletRequest request, HttpSession session) {

        /*
         * Forzar content type en la cabecera para evitar bug en IE y en
         * Firefox. Si no se fuerza el content type Spring lo calcula y
         * curiosamente depende del navegador desde el que se hace la petición.
         * Esto se debe a que como esta petici�n es invocada desde un iFrame
         * (oculto) algunos navegadores interpretan la respuesta como un
         * descargable o fichero vinculado a una aplicaci�n. De esta forma, y
         * devolviendo un ResponseEntity, forzaremos el Content-Type de la
         * respuesta.
         */
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

        Locale locale = request.getLocale();
        String jsonResult = null;

        Map<String, String> valoresForm = new HashMap<String, String>();
        Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

        try {
            // Recuperamos los valores del request
            recuperarForms(request, valoresForm, ficherosForm);

            // Tags que nos identifican que tipo de documentos son
            int tipoDoc = "0".equals(valoresForm.get("tipDoc")) ? DocumentTramit.DOCINFORMATIU
                : DocumentTramit.FORMULARI;
            String tipoTag = tipoDoc == 0 ? "doc" : "form";
            String idDocument = tipoDoc == 0 ? "docTramitId" : "formTramitId";
            String idTag = "tramitId" + tipoTag;

            // Recuperamos el documento antiguo en caso de que se trate de una
            // modificación
            DocumentTramit documentTramitOld = recuperarDocOld(valoresForm, idDocument);
            // DocumentTramit documentTramitOld = tramiteDelegate.obtenirDocument(Long.parseLong(valoresForm.get(idDocument)));

            // Copiamos la información deseada al nuevo documento
            DocumentTramit documentTramit = recuperarInformacionDocumento(valoresForm, documentTramitOld, tipoDoc, idTag);

            // Actualizamos las traducciones y marcamos los archivos que deven ser eliminados
            List<Long> archivosBorrar = new Vector<Long>();
            documentTramit = gestionarTraducciones(valoresForm, ficherosForm, archivosBorrar, documentTramitOld,
                documentTramit, tipoTag);

            //#421 Comprobacion del tamaño del nombre de archivo.
            if (documentTramit != null && documentTramit.getArchivo() != null && documentTramit.getArchivo().getNombre() != null && documentTramit.getArchivo().getNombre().length() >= Archivo.NOMBRE_LONGITUD_MAXIMA) {
            	String error = messageSource.getMessage("error.fitxer.tamany_nom", null, locale);
            	log.error("Error controlado, ha intentado subir un fichero con una longitud en el nombre de más de 128 caracteres.");
            	jsonResult = new IdNomDTO(-3l, error).getJson();
            } else {
            	 // Guardar el documento
                jsonResult = guardarDocumento(valoresForm, idTag, locale, archivosBorrar, documentTramit);
            }
           
        } catch (FileUploadException fue) {
            String error = messageSource.getMessage("error.fitxer.tamany", null, locale);
            jsonResult = new IdNomDTO(-3l, error).getJson();
            log.error(error + ": " + fue.toString());

        } catch (UnsupportedEncodingException uee) {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error(error + ": " + uee.toString());

        } catch (NumberFormatException nfe) {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error(error + ": " + nfe.toString());

        } catch (DelegateException de) {
            String error = null;
            if (de.isSecurityException()) {
                error = messageSource.getMessage("error.permisos", null, locale);
                jsonResult = new IdNomDTO(-1l, error).getJson();
            } else {
                error = messageSource.getMessage("error.altres", null, locale);
                jsonResult = new IdNomDTO(-2l, error).getJson();
            }
            log.error(error + ": " + de.toString());
        }

        return new ResponseEntity<String>(jsonResult, responseHeaders, HttpStatus.CREATED);
    }

    /** Aquí nos llegará un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
     *  Iremos recopilando los parámetros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
     */
    private void recuperarForms(HttpServletRequest request, Map<String, String> valoresForm,
        Map<String, FileItem> ficherosForm) throws UnsupportedEncodingException, FileUploadException {

        List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
            } else {
                ficherosForm.put(item.getFieldName(), item);
            }
        }
    }

    /** Vemos si se debe recuperar el documento viejo */
    private DocumentTramit recuperarDocOld(Map<String, String> valoresForm, String idDocument) throws DelegateException {

        TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
        DocumentTramit docOld = null;
        if (valoresForm.get(idDocument) != null && !"".equals(valoresForm.get(idDocument))) {
            Long docId = Long.parseLong(valoresForm.get(idDocument));
            docOld = tramiteDelegate.obtenirDocument(docId);
        }

        return docOld;
    }

    /** Recuperamos la información antigua si el documento ya existia */
    private DocumentTramit recuperarInformacionDocumento(Map<String, String> valoresForm,
        DocumentTramit documentTramitOld, int tipoDoc, String idTag) throws DelegateException {

        String idDocument = tipoDoc == 0 ? "docTramitId" : "formTramitId";
        DocumentTramit documentTramit = new DocumentTramit();
        documentTramit.setTipus(tipoDoc);

        if (valoresForm.get(idDocument) != null && !"".equals(valoresForm.get(idDocument))) {
            Long idTramite = new Long(valoresForm.get(idTag));
            TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
            Tramite tramite = tramiteDelegate.obtenerTramite(idTramite);

            documentTramit.setId(documentTramitOld.getId());
            documentTramit.setTramit(tramite);
            documentTramit.setOrden(documentTramitOld.getOrden());
        }

        return documentTramit;
    }

    /** Gestión de las traducciones y los archivos */
    private DocumentTramit gestionarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm,
        List<Long> archivosAborrar, DocumentTramit docOld, DocumentTramit doc, String tipoTag) throws DelegateException {

        String tituloTag = tipoTag + "_tramit_titol_";
        String descripcionTag = tipoTag + "_tramit_descripcio_";
        String archivoTag = tipoTag + "_tramit_arxiu_";

        TraduccionDocumento tradDoc;
        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {
            tradDoc = new TraduccionDocumento();
            tradDoc.setTitulo(RolUtil.limpiaCadena(valoresForm.get(tituloTag + lang)));
            tradDoc.setDescripcion(RolUtil.limpiaCadena(valoresForm.get(descripcionTag + lang)));

            // Archivo
            FileItem fileItem = ficherosForm.get(archivoTag + lang);
            if (fileItem != null && fileItem.getSize() > 0) {
                // En caso de que el documento ya exista y se quiera cambiar el archivo adjunto
                if (docOld != null) {
                    TraduccionDocumento traDocOld = (TraduccionDocumento) docOld.getTraduccion(lang);
                    if (traDocOld != null && traDocOld.getArchivo() != null) {
                        archivosAborrar.add(traDocOld.getArchivo().getId());
                    }
                }

                // Nuevo archivo.
                tradDoc.setArchivo(UploadUtil.obtenerArchivo(tradDoc.getArchivo(), fileItem));

            } else if (valoresForm.get(archivoTag + lang + "_delete") != null
                && !"".equals(valoresForm.get(archivoTag + lang + "_delete"))) {
                // Indicamos a la traducción del documento que no va a tener asignado el archivo.
                TraduccionDocumento traDocOld = (TraduccionDocumento) docOld.getTraduccion(lang);
                archivosAborrar.add(traDocOld.getArchivo().getId());
                tradDoc.setArchivo(null);

            } else if (docOld != null) {
                // mantener el fichero anterior
                TraduccionDocumento traDocOld = (TraduccionDocumento) docOld.getTraduccion(lang);
                if (traDocOld != null) {
                    tradDoc.setArchivo(traDocOld.getArchivo());
                }
            }

            doc.setTraduccion(lang, tradDoc);
        }

        return doc;
    }

    /** Guardado del documento */
    private String guardarDocumento(Map<String, String> valoresForm, String iden, Locale locale,
        List<Long> archivosBorrar, DocumentTramit documentTramit) throws DelegateException {

        String jsonResult = null;
        Long docTramitId = null;
        if (valoresForm.get(iden) != null && !"".equals(valoresForm.get(iden))) {
            TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
            Long idTramite = Long.parseLong(valoresForm.get(iden));
            docTramitId = tramiteDelegate.grabarDocument(documentTramit, idTramite);

            for (Long idArchivo : archivosBorrar) {
                DelegateUtil.getArchivoDelegate().borrarArchivo(idArchivo);
            }

            jsonResult = new IdNomDTO(docTramitId, messageSource.getMessage("document.guardat.correcte", null, locale)).getJson();

        } else {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error("Error guardant document: No s'ha especificat id de procediment o de fitxer.");
        }

        return jsonResult;
    }

    @RequestMapping(value = "/archivo.do")
    public void devolverArchivoDocumentoTramite(HttpServletRequest request, HttpServletResponse response) throws Exception {

        this.devolverArchivo(request, response);
    }

    @Override
    public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {

        // obtener archivo concreto con el delegate
        Long id = new Long(request.getParameter("id"));
        String lang = request.getParameter("lang");
        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
        return docDelegate.obtenerArchivoDocumentoTramite(id, lang, false);
    }

    @RequestMapping(value = "/traduir.do")
    public @ResponseBody
    Map<String, Object> traduir(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();

        try {
            String idiomaOrigenTraductor = DelegateUtil.getIdiomaDelegate().lenguajePorDefecto();

            TraduccionDocumento traduccioOrigen = getTraduccionOrigen(request, idiomaOrigenTraductor);
            List<Map<String, Object>> traduccions = new LinkedList<Map<String, Object>>();
            Traductor traductor = (Traductor) request.getSession().getServletContext().getAttribute("traductor");
            traduccions = traductor.translate(traduccioOrigen, idiomaOrigenTraductor);

            resultats.put("traduccions", traduccions);

        } catch (DelegateException dEx) {
            logException(log, dEx);
            if (dEx.isSecurityException()) {
                resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
            } else {
                resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
            }
        } catch (NullPointerException npe) {
            log.error("DocumentTramitBackController.traduir: El traductor no se encuentra en en contexto.");
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        } catch (Exception e) {
            log.error("DocumentTramitBackController.traduir: Error en al traducir documento del trámite: " + e);
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        }

        return resultats;
    }

    /**
     * Recuperación de los campos de los documentos según el tipo de documento 
     * @param request
     * @param idiomaOrigenTraductor
     * @return devuelve un traduccionDocumento
     */
    private TraduccionDocumento getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {

        TraduccionDocumento traduccioOrigen = new TraduccionDocumento();

        if (StringUtils.isNotEmpty(request.getParameter("form_tramit_titol_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setTitulo(request.getParameter("form_tramit_titol_" + idiomaOrigenTraductor));
        } else if (StringUtils.isNotEmpty(request.getParameter("doc_tramit_titol_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setTitulo(request.getParameter("doc_tramit_titol_" + idiomaOrigenTraductor));
        } else if (StringUtils.isNotEmpty(request.getParameter("doc_requerit_titol_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setTitulo(request.getParameter("doc_requerit_titol_" + idiomaOrigenTraductor));
        }

        if (StringUtils.isNotEmpty(request.getParameter("form_tramit_descripcio_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setDescripcion(request.getParameter("form_tramit_descripcio_" + idiomaOrigenTraductor));
        } else if (StringUtils.isNotEmpty(request.getParameter("doc_tramit_descripcio_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setDescripcion(request.getParameter("doc_tramit_descripcio_" + idiomaOrigenTraductor));
        } else if (StringUtils.isNotEmpty(request.getParameter("doc_requerit_descripcio_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setDescripcion(request.getParameter("doc_requerit_descripcio_" + idiomaOrigenTraductor));
        }

        return traduccioOrigen;
    }

}
