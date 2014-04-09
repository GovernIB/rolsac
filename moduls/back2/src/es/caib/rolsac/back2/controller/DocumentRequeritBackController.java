package es.caib.rolsac.back2.controller;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.hibernate.exception.ExceptionUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionDocumento;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.CatalegDocumentsDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
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

/**
 * Servlet per la gesti� de documents requerits.
 */
@Controller
@RequestMapping("/documentsRequerits/")
public class DocumentRequeritBackController {

	private static Log log = LogFactory.getLog(TramiteBackController.class);	
	private MessageSource messageSource = null;
	private static final long DOC_CATALEG = 1;
	private static final long DOC_ESPECIFIC = 2;

	@Autowired
	public void setMessageSource(MessageSource messageSource){
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/documentsRequerits.do", method = GET)
	public void obtenerTramite(HttpServletRequest request, HttpServletResponse response) {}    

	@RequestMapping(value = "/carregarDocumentRequerit.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaDetall(HttpSession session, HttpServletRequest request) {    

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			
			Long id = new Long(request.getParameter("id"));
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();								

			DocumentTramit doc = tramiteDelegate.obtenirDocument(id);

			Map<String, Object> mapDoc = new HashMap<String, Object>();

			Long tipusDocRequerit=null;
			if (doc.getDocCatalogo() != null){
				tipusDocRequerit=DOC_CATALEG;
			} else {
				tipusDocRequerit=DOC_ESPECIFIC;
			}

			String idDocCat = "";
			String idExcDoc = "" ;
			if (tipusDocRequerit==DOC_CATALEG){
				idDocCat = "" + doc.getDocCatalogo().getId();
				CatalegDocumentsDelegate catalegDelegate = DelegateUtil.getCatalegDocumentsDelegate();
				CatalegDocuments catDoc = catalegDelegate.obtenirDocumentoCataleg(doc.getDocCatalogo().getId());
				ExcepcioDocumentacio excepcio=catDoc.getExcepcioDocumentacio();
				if (excepcio!=null){
					idExcDoc = "" + excepcio.getId();
				} 
			} else { // DOCUMENT ESPEC�FIC
				if (doc.getExcepcioDocumentacio()!=null){
					idExcDoc = "" + doc.getExcepcioDocumentacio().getId();
					ExcepcioDocumentacioDelegate excDocDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
					ExcepcioDocumentacio excepcio = excDocDelegate.obtenirExcepcioDocumentacio(doc.getExcepcioDocumentacio().getId());
					if (excepcio!=null){
						idExcDoc = "" + excepcio.getId();
					}
				}
			}
			mapDoc.put("item_tipdoc", tipusDocRequerit);
			mapDoc.put("item_id", doc.getId());      
			mapDoc.put("cataleg_id", idDocCat);
			mapDoc.put("excepcio_id", idExcDoc);

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
				}
			}

			resultats.put("documentRequerit", mapDoc);
			resultats.put("id", doc.getId());

		} catch (DelegateException dEx) {
			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}            
		} catch (NumberFormatException nFEx) {
			logException(log, nFEx);
			resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));    		
		}

		return resultats;
		
	}

	@RequestMapping(value = "/guardarDocumentRequerit.do", method = POST)
	public @ResponseBody ResponseEntity<String> guardar(HttpSession session, HttpServletRequest request) {		

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");

		String error = null;		
		IdNomDTO result = null;
		DocumentTramit docTramit = null;
		String nomDoc = "";
		TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();	

		try {

			Tramite tramite = tramiteDelegate.obtenerTramite( new Long(request.getParameter("docReqTramitId")) );			

			String docReqId = request.getParameter("docReqId");
			// Se cambia el "item_tipdoc_ca" por el "item_tipdoc_" + idioma  
			Long tipusDocRequerit = Long.parseLong(request.getParameter("item_tipdoc_" + DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));

			boolean edicion = !"".equals(docReqId);
			if ( !edicion ) {
				docTramit = new DocumentTramit();
				docTramit.setOrden(0L);
			} else{
				docTramit = DelegateUtil.getTramiteDelegate().obtenirDocument(new Long(docReqId));
			}

			// Idiomas
			TraduccionDocumento traduccionDoc;			
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();
			Map traducciones = new HashMap(langs.size());

			for (String lang: langs) {

				traduccionDoc = (TraduccionDocumento)docTramit.getTraduccion(lang);
				if ( traduccionDoc == null ){ 
					traduccionDoc = new TraduccionDocumento();
				}

				String titolDoc = null;	
				if (tipusDocRequerit == DOC_ESPECIFIC){
					titolDoc = request.getParameter("doc_requerit_titol_" + lang);
				}

				traduccionDoc.setTitulo( RolUtil.limpiaCadena(titolDoc) );
				traduccionDoc.setDescripcion( RolUtil.limpiaCadena(request.getParameter("doc_requerit_descripcio_" + lang)) );

				traducciones.put(lang, traduccionDoc);
				
			}

			docTramit.setTraduccionMap(traducciones);
			// Fin idiomas

			CatalegDocuments catDoc=null;
			ExcepcioDocumentacio excepcioDoc=null;

			if (tipusDocRequerit==DOC_CATALEG){
				try {
					// Se cambia el "item_cataleg_ca" por el "item_cataleg_" + idioma
					Long IdDocCataleg = Long.parseLong(request.getParameter("item_cataleg_" + DelegateUtil.getIdiomaDelegate().lenguajePorDefecto()));
					CatalegDocumentsDelegate catDocDelegate = DelegateUtil.getCatalegDocumentsDelegate();
					catDoc  = catDocDelegate.obtenirDocumentoCataleg(IdDocCataleg);
					nomDoc =((TraduccionCatalegDocuments)catDoc.getTraduccion("ca")).getNombre();
				} catch (NumberFormatException e) {
					error = messageSource.getMessage("error.numeracio", null, request.getLocale());
					throw new NumberFormatException();
				}

			} else { //DOCUMENT ESPECÍFIC
				String item_excepcio = request.getParameter("item_excepcio_ca");
				String item_check_excepcio = request.getParameter("item_check_excepcio_ca");

				if (item_excepcio!=null && item_excepcio.equalsIgnoreCase("") && item_check_excepcio!=null ){
					try{
						throw new Exception(messageSource.getMessage("document_requerit.formulari_docreq_tramit.causes_excepcio.obligatori", null, request.getLocale()));
					}catch (Exception e){
						result = new IdNomDTO(-3l, e.getMessage());
					}
				}
				else if (item_excepcio!=null && !item_excepcio.equalsIgnoreCase("")){
					try{
						Long idExcepcioDoc = Long.parseLong(item_excepcio);
						ExcepcioDocumentacioDelegate excDocDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
						excepcioDoc = excDocDelegate.obtenirExcepcioDocumentacio(idExcepcioDoc);
					} catch (NumberFormatException e) {
						error = messageSource.getMessage("error.numeracio", null, request.getLocale());
						throw new NumberFormatException();
					}
				} 
				nomDoc =((TraduccionDocumento) docTramit.getTraduccion("ca")).getTitulo();
			}

			docTramit.setDocCatalogo(catDoc);
			docTramit.setExcepcioDocumentacio(excepcioDoc);
			docTramit.setTipus(new Integer(request.getParameter("tipDoc"))); //TIPUS DOCUMENT TR�MIT (FORM,INFO,REQ)

			tramiteDelegate.grabarDocument(docTramit, tramite.getId());

		} catch (DelegateException dEx) {

			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx)); 
			}		
		}

		if (result == null) {
			result = new IdNomDTO();
			result.setId(docTramit.getId());
			result.setNom(nomDoc);        
		}

		return new ResponseEntity<String>(result.getJson(), responseHeaders, HttpStatus.CREATED);
		
	}

	@RequestMapping(value = "/esborrarDocumentRequerit.do", method = POST)	
	public @ResponseBody IdNomDTO esborrar(HttpServletRequest request) {
		
		IdNomDTO resultatStatus = new IdNomDTO();

		Long docReqId = new Long(request.getParameter("docReqId"));

		try {
			
			TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
			tramiteDelegate.borrarDocument(docReqId);

			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				logException(log, dEx);
			}
		}

		return resultatStatus;
		
	}	

	@RequestMapping(value = "/recuperaExcepcionsDocumentacio.do", method = POST)
	public @ResponseBody Map<String, Object> recuperaExcepcionsDocumentacio(HttpSession session, HttpServletRequest request) {    

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			Long id = new Long(request.getParameter("id"));

			CatalegDocumentsDelegate catalegDelegate = DelegateUtil.getCatalegDocumentsDelegate();

			CatalegDocuments catDoc = catalegDelegate.obtenirDocumentoCataleg(id);

			ExcepcioDocumentacio excepcio=catDoc.getExcepcioDocumentacio();
			if (excepcio!=null){
				resultats.put("excepcio_id", excepcio.getId());
			} else {
				resultats.put("excepcio_id", "");
			}
			resultats.put("id", catDoc.getId());
		} catch (DelegateException dEx) {
			logException(log, dEx);

			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}            
		} catch (NumberFormatException nFEx) {
			logException(log, nFEx);
			resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));        
		}

		return resultats;    
		
	}

}