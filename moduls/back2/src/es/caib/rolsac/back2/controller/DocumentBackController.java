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
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DocumentoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
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
import es.indra.rol.sac.integracion.traductorTranslatorIB.Traductor;

@SuppressWarnings("deprecation")

@Controller
@RequestMapping("/documents/")
public class DocumentBackController extends ArchivoController {

    private static Log log = LogFactory.getLog(DocumentBackController.class);

    private MessageSource messageSource = null;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(value = "/guardarDocument.do", method = POST)
    public ResponseEntity<String> guardar(HttpServletRequest request, HttpSession session) {
        /*
         * Forzar content type en la cabecera para evitar bug en IE y en
         * Firefox. Si no se fuerza el content type Spring lo calcula y
         * curiosamente depende del navegador desde el que se hace la petición.
         * Esto se debe a que como esta petici�n es invocada desde un iFrame
         * (oculto) algunos navegadores interpretan la respuesta como un
         * descargable o fichero vinculado a una aplicación. De esta forma, y
         * devolviendo un ResponseEntity, forzaremos el Content-Type de la
         * respuesta.
         */
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        Locale locale = request.getLocale();
        String jsonResult = null;
        Map<String, String> valoresForm = new HashMap<String, String>();
        Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();

        try {

            // Recuperamos los valores del request
            recuperarForms(request, valoresForm, ficherosForm);

            // Recuperamos el documento antiguo si existe
            Documento docOld = recuperarDocOld(valoresForm);

            // Copiamos la información deseada al nuevo documento
            Documento doc = recuperarInformacionDocumento(valoresForm, docOld);

            // Actualizamos las traducciones y marcamos los archivos que deven
            // ser eliminados
            List<Long> archivosAborrar = new Vector<Long>();

            doc = gestionarTraducciones(valoresForm, ficherosForm, archivosAborrar, docOld, doc);

            // Guardar el documento
            String iden = "";

            if (valoresForm.get("procId") != null && !"".equals(valoresForm.get("procId"))) {
                iden = "procId";
            } else if (valoresForm.get("fitxaId") != null && !"".equals(valoresForm.get("fitxaId"))) {
                iden = "fitxaId";
            }
            
            boolean continuar = true;
            //#421 Comprobacion del tamaño del nombre de archivo.
            if (doc != null && doc.getLangs() != null) {
        	    //Buscamos el archivo del idioma.
	           	for(String idioma : doc.getLangs()) {
	   				TraduccionDocumento tradNor = (TraduccionDocumento) doc.getTraduccion(idioma);
	   				if (tradNor != null && tradNor.getArchivo() != null  && tradNor.getArchivo().getNombre() != null && tradNor.getArchivo().getNombre().length() >= Archivo.NOMBRE_LONGITUD_MAXIMA) {
	   					String error = messageSource.getMessage("error.fitxer.tamany_nom", null, locale);
	   	            	log.error("Error controlado, ha intentado subir un fichero con una longitud en el nombre de más de 128 caracteres.");
	   	            	jsonResult = new IdNomDTO(-3l, error).getJson();
	   	            	continuar = false;
	   				}
	   			}
            }
            
            if (continuar) {
            	jsonResult = guardarDocumento(valoresForm, iden, locale, archivosAborrar, doc);
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

    /**
     * Aquí nos llegará un multipart, de modo que no podemos obtener los datos
     * mediante request.getParameter(). Iremos recopilando los parámetros de
     * tipo fichero en el Map ficherosForm y el resto en valoresForm.
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
    private Documento recuperarDocOld(Map<String, String> valoresForm) throws DelegateException {

        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
        Documento docOld = null;

        if (!this.isDocumentoNuevo(valoresForm)) {
            Long docId = Long.parseLong(valoresForm.get("docId"));
            docOld = docDelegate.obtenerDocumento(docId);
        }

        return docOld;
    }

    /** Recuperamos la información antigua si el documento ya existia */
    private Documento recuperarInformacionDocumento(Map<String, String> valoresForm, Documento docOld) {

        Documento doc = new Documento();

        if (!this.isDocumentoNuevo(valoresForm)) {
            doc.setId(docOld.getId());
            // Este atributo parece que ya no se usa. Se mantiene por si acaso.
            doc.setArchivo(docOld.getArchivo()); 
            doc.setFicha(docOld.getFicha());
            doc.setProcedimiento(docOld.getProcedimiento());
            doc.setOrden(docOld.getOrden());
        }

        return doc;
    }

    /** Gestión de las traducciones y los archivos */
    private Documento gestionarTraducciones(Map<String, String> valoresForm, Map<String, FileItem> ficherosForm,
        List<Long> archivosAborrar, Documento docOld, Documento doc) throws DelegateException {

        TraduccionDocumento tradDoc;
        for (String lang : DelegateUtil.getIdiomaDelegate().listarLenguajes()) {

            tradDoc = new TraduccionDocumento();
            tradDoc.setTitulo(RolUtil.limpiaCadena(valoresForm.get("doc_titol_" + lang)));
            tradDoc.setDescripcion(RolUtil.limpiaCadena(valoresForm.get("doc_descripcio_" + lang)));

            FileItem fileItem = ficherosForm.get("doc_arxiu_" + lang); // Archivo

            if (fileItem != null && fileItem.getSize() > 0) {
            	
                if (!this.isDocumentoNuevo(valoresForm)) {
                	
                    TraduccionDocumento traDocOld = (TraduccionDocumento)docOld.getTraduccion(lang);
                    
                    // Si aún no hay traducción asociada es que no toca procesar el archivo adjunto.
                    if (traDocOld != null) {
                    
	                    if (this.isArchivoParaBorrar(valoresForm, lang) 
	                    		|| this.ficheroAdjuntoIsModificado(valoresForm, traDocOld)) {
	                    	
	                        // Se indica que hay que borrar el fichero.
	                        archivosAborrar.add(traDocOld.getArchivo().getId());
	                        
	                    }
                    
                    }
                    
                }
                
                // Nuevo archivo
                tradDoc.setArchivo(UploadUtil.obtenerArchivo(tradDoc.getArchivo(), fileItem));

            } else if (this.isArchivoParaBorrar(valoresForm, lang)) {
            	
                // Indicamos a la traducción del documento que no va a tener
                // asignado el archivo.
                TraduccionDocumento traDocOld = (TraduccionDocumento)docOld.getTraduccion(lang);
                archivosAborrar.add(traDocOld.getArchivo().getId());
                tradDoc.setArchivo(null);

            } else if (docOld != null) {
            	
                // mantener el fichero anterior
                TraduccionDocumento traDocOld = (TraduccionDocumento)docOld.getTraduccion(lang);
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
        List<Long> archivosBorrar, Documento doc) throws DelegateException {

        String jsonResult = null;
        Long docId = null;

        if (valoresForm.get(iden) != null && !"".equals(valoresForm.get(iden))) {

            DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
            Long id = Long.parseLong(valoresForm.get(iden));

            if (iden.equals("procId")) {
                // Seleccionamos si queremos guardar una ficha o un
                // procedimiento
                docId = docDelegate.grabarDocumento(doc, id, null);
            } else if (iden.equals("fitxaId")) {
                docId = docDelegate.grabarDocumento(doc, null, id);
            }

            for (Long idArchivo : archivosBorrar) {
                DelegateUtil.getArchivoDelegate().borrarArchivo(idArchivo);
            }

            jsonResult = new IdNomDTO(docId, messageSource.getMessage("document.guardat.correcte", null, locale))
                .getJson();

        } else {
            String error = messageSource.getMessage("error.altres", null, locale);
            jsonResult = new IdNomDTO(-2l, error).getJson();
            log.error("Error guardant document: No s'ha especificat id de procediment o de fitxer.");
        }

        return jsonResult;
    }

    @RequestMapping(value = "/carregarDocument.do")
    public @ResponseBody
    Map<String, Object> carregarDocument(HttpServletRequest request) {

        Map<String, Object> resultats = new HashMap<String, Object>();

        try {

            Long id = new Long(request.getParameter("id"));
            DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();
            Documento doc = docDelegate.obtenerDocumento(id);

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

                    // archivo
                    if (traDoc.getArchivo() != null) {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "documents/archivo.do?id=" + doc.getId() + "&lang=" + idioma);
                        mapDoc.put("idioma_nom_arxiu_" + idioma, traDoc.getArchivo().getNombre());

                    } else {
                        mapDoc.put("idioma_enllas_arxiu_" + idioma, "");
                        mapDoc.put("idioma_nom_arxiu_" + idioma, "");

                    }
                }
            }

            mapDoc.put("item_id", doc.getId());
            resultats.put("document", mapDoc);
            resultats.put("id", doc.getId());

        } catch (NumberFormatException nfe) {
            log.error("El id del document no es numeric: " + nfe.toString());
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

    @RequestMapping(value = "/archivo.do")
    public void devolverArchivoDocumento(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);
    }
    
    

    @Override
    public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {
        // Obtener archivo concreto con el delegate

        Long id = new Long(request.getParameter("id"));
        String lang = request.getParameter("lang");
        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();

        return docDelegate.obtenerArchivoDocumento(id, lang, false);
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
            log.error("DocumentBackController.traduir: El traductor no se encuentra en en contexto.");
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        } catch (Exception e) {
            log.error("DocumentBackController.traduir: Error en al traducir procedimiento: " + e);
            resultats.put("error", messageSource.getMessage("error.traductor", null, request.getLocale()));
        }

        return resultats;
    }

    private TraduccionDocumento getTraduccionOrigen(HttpServletRequest request, String idiomaOrigenTraductor) {
        TraduccionDocumento traduccioOrigen = new TraduccionDocumento();

        if (StringUtils.isNotEmpty(request.getParameter("doc_titol_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setTitulo(request.getParameter("doc_titol_" + idiomaOrigenTraductor));
        }

        if (StringUtils.isNotEmpty(request.getParameter("doc_descripcio_" + idiomaOrigenTraductor))) {
            traduccioOrigen.setDescripcion(request.getParameter("doc_descripcio_" + idiomaOrigenTraductor));
        }

        return traduccioOrigen;
    }

    /**
     * Método que indica si el documento obtenido de la petición es un documento
     * nuevo.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @return Devuelve <code>true</code> si es un documento nuevo.
     */
    private boolean isDocumentoNuevo(Map<String, String> valoresForm) {
        return (valoresForm.get("docId") == null || "".equals(valoresForm.get("docId")));
    }

    /**
     * Método que indica si el archivo adjunto de un documento se tiene que
     * borrar.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @param lang
     *            Indica el idioma del documento.
     * @return Devuelve <code>true</code> si el archivo adjunto de un documento
     *         se ha marcado para borrar.
     */
    private boolean isArchivoParaBorrar(Map<String, String> valoresForm, String lang) {
        return (valoresForm.get("doc_arxiu_" + lang + "_delete") != null && !"".equals(valoresForm.get("doc_arxiu_"
            + lang + "_delete")));
    }

    /**
     * Método que indica si se va a modificar el fichero adjunto a un documento
     * existente.
     * 
     * @param valoresForm
     *            Estructura de datos que contiene los valores enciados por el
     *            formulario.
     * @param traDocOld
     *            Indica un documento que se va a modificar.
     * @return Devuelve <code>true</code> si el fichero adjunto se va a
     *         modificar.
     */
    private boolean ficheroAdjuntoIsModificado(Map<String, String> valoresForm, TraduccionDocumento traDocOld) {
        return (traDocOld.getArchivo() != null);
    }

}
