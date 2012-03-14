package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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

import es.caib.rolsac.back2.util.UploadUtil;

/**
 * Servlet para la gesti�n de documentos y formularios de un tr�mite.
 */
@Controller
@RequestMapping("/documentsTramit/")
public class DocumentTramitBackController extends ArchivoController {
	
	private static Log log = LogFactory.getLog(DocumentTramitBackController.class);
	
	private MessageSource messageSource = null;
	
	@Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
		
	@RequestMapping(value = "/carregarDocumentTramit.do")
	public @ResponseBody Map<String, Object> carregarDocument(HttpServletRequest request)  {
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			Long id = new Long(request.getParameter("id"));
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();								
			
			DocumentTramit doc = tramiteDelegate.obtenirDocument(id);

			Map<String, Object> mapDoc = new HashMap<String, Object>();
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> idiomas = idiomaDelegate.listarLenguajes();
			TraduccionDocumento traDoc;
			
			for (String idioma: idiomas) {
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
	    	        	mapDoc.put("idioma_enllas_arxiu_" + idioma, "documentsTramit/archivo.do?id=" + doc.getId() + "&lang=" + idioma);
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
	public ResponseEntity<String> guardarDocument(HttpServletRequest request, HttpSession session)  {
				
		/* Forzar content type en la cabecera para evitar bug en IE y en Firefox.
		 * Si no se fuerza el content type Spring lo calcula y curiosamente depende del navegador desde el que se hace la petici�n.
		 * Esto se debe a que como esta petici�n es invocada desde un iFrame (oculto) algunos navegadores interpretan la respuesta como
		 * un descargable o fichero vinculado a una aplicaci�n. 
		 * De esta forma, y devolviendo un ResponseEntity, forzaremos el Content-Type de la respuesta.
		 */		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
						
		Locale locale = request.getLocale();
		String jsonResult = null;
		
		TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();		
				
		Map<String, String> valoresForm = new HashMap<String, String>();
		Map<String, FileItem> ficherosForm = new HashMap<String, FileItem>();
				
		try {
			
			//Aqu� nos llegar� un multipart, de modo que no podemos obtener los datos mediante request.getParameter().
			//Iremos recopilando los par�metros de tipo fichero en el Map ficherosForm y el resto en valoresForm.
			List<FileItem> items = UploadUtil.obtenerServletFileUpload().parseRequest(request);
			
			for (FileItem item : items) {
				if (item.isFormField()) {
					valoresForm.put(item.getFieldName(), item.getString("UTF-8"));
				} else {
					ficherosForm.put(item.getFieldName(), item);    				
				}
			}
						
			DocumentTramit documentTramit = new DocumentTramit();
			DocumentTramit documentTramitOld = null;
			
			Long docTramitId = null;
			int tipoDoc = "0".equals(valoresForm.get("tipDoc")) ? DocumentTramit.DOCINFORMATIU : DocumentTramit.FORMULARI;			
						
			String idDocument = tipoDoc == 0 ? "docTramitId" : "formTramitId";
			String tipoTag = tipoDoc == 0 ? "doc" : "form";
			String tituloTag = tipoTag + "_tramit_titol_";
			String descripcionTag = tipoTag + "_tramit_descripcio_";
			String archivoTag = tipoTag + "_tramit_arxiu_";			
			String idTag = "tramitId" + tipoTag;
			
			Long idTramite = new Long( valoresForm.get( idTag ) );
			Tramite tramite = tramiteDelegate.obtenerTramite( idTramite );
			
			boolean edicion = valoresForm.get(idDocument) != null && !"".equals( valoresForm.get(idDocument) );
								
			documentTramit.setTipus( tipoDoc );
			
			if ( edicion ) {
				
				docTramitId = Long.parseLong(valoresForm.get(idDocument));
				documentTramitOld = tramiteDelegate.obtenirDocument(docTramitId) ;
				
				documentTramit.setId(documentTramitOld.getId());			
				documentTramit.setArchivo(documentTramitOld.getArchivo());  // Este atributo parece que ya no se usa. Se mantiene por si acaso.
				documentTramit.setTramit(tramite);			
				documentTramit.setOrden(documentTramitOld.getOrden());			
			}
			
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			TraduccionDocumento traduccionDocumento;
					
			for (String lang: langs) {
				
				traduccionDocumento = new TraduccionDocumento();
				traduccionDocumento.setTitulo(valoresForm.get(tituloTag + lang));
				traduccionDocumento.setDescripcion(valoresForm.get(descripcionTag + lang));				
				
				// Archivo
				FileItem fileItem = ficherosForm.get(archivoTag + lang);
				
	    		if (fileItem != null && fileItem.getSize() > 0) {
	    			// nuevo fichero
	    			traduccionDocumento.setArchivo(UploadUtil.obtenerArchivo(traduccionDocumento.getArchivo(), fileItem));
	    		} else if (valoresForm.get(archivoTag + lang + "_delete") != null && !"".equals(valoresForm.get(archivoTag + lang + "_delete"))) {
	    			// borrar fichero
	    			traduccionDocumento.setArchivo(null);
	    		} else if (documentTramitOld != null) {
	    			// mantener el fichero anterior
	    			TraduccionDocumento traDocOld = (TraduccionDocumento) documentTramitOld.getTraduccion(lang);
	    			if (traDocOld != null) {
	    				traduccionDocumento.setArchivo(traDocOld.getArchivo());
	    			}
	    		}

				documentTramit.setTraduccion(lang, traduccionDocumento);
			}
		
		if ( valoresForm.get(idTag) != null && !"".equals(valoresForm.get(idTag)) ) {
			
			docTramitId = tramiteDelegate.grabarDocument(documentTramit, idTramite);
			
			String textoGuardadoCorrecto = "document.guardat.correcte";			
			if (tipoDoc == 1) textoGuardadoCorrecto = "formulari.guardat.correcte";
			
			jsonResult = new IdNomDTO(docTramitId,  messageSource.getMessage(textoGuardadoCorrecto, null, locale)).getJson();
			
		} else {
			String error = messageSource.getMessage("error.altres", null, locale);
			jsonResult = new IdNomDTO(-2l, error).getJson();
			log.error("Error guardant document");
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
	
	@RequestMapping(value = "/archivo.do")
    public void devolverArchivoDocumentoTramite(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.devolverArchivo(request, response);   
    }
	
	@Override
	public Archivo obtenerArchivo(HttpServletRequest request) throws Exception {		
        //obtener archivo concreto con el delegate
        Long id = new Long(request.getParameter("id"));
        String lang = request.getParameter("lang");
        DocumentoDelegate docDelegate = DelegateUtil.getDocumentoDelegate();                
        return docDelegate.obtenerArchivoDocumentoTramite(id, lang, false);
	}	
}