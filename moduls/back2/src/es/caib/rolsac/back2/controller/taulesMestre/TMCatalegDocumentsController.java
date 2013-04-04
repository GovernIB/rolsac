package es.caib.rolsac.back2.controller.taulesMestre;

import static es.caib.rolsac.utils.LogUtils.logException;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionCatalegDocuments;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.CatalegDocumentsDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/catalegDocuments/")
public class TMCatalegDocumentsController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMCatalegDocumentsController.class);
	
    @RequestMapping(value = "/catalegDocuments.do")
    public String pantallaCatalegDocuments(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMCatalegDocuments.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        String lang = getRequestLanguage(request);
        try {
        	model.put("excepcions", llistarExcepcionsDocumentacio(lang));
        } catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				logException(log, dEx);
			}
		}
		loadIndexModel (model, request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmCatalegDocuments.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatCataleg(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaCatalegDocumentsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> catalegDocDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();
		
    Map<String, Object> paramMap = new HashMap<String, Object>();
    Map<String, String> tradMap = new HashMap<String, String>();
    
    String lang = request.getLocale().getLanguage();
  // Parametres de cerca
    
    
    Long admResp = null;
    String admRespString = request.getParameter("admresp");
    if (admRespString != null && !"".equals(admRespString)) {
       Scanner scanner = new Scanner(admRespString);
        if (scanner.hasNextLong()) {
          admResp = scanner.nextLong();
          paramMap.put("admResponsable",admResp);
        }
    }
    
    Long idExcepcio = null;
    String excepcioString = request.getParameter("excepcio");
    if (excepcioString != null) {
       Scanner scanner = new Scanner(excepcioString);
        if (scanner.hasNextLong()) {
          idExcepcio = scanner.nextLong();
        }
    }
    
    // Textes (en todos los campos todos los idiomas)
    String textes = request.getParameter("textes");
    if (textes != null && !"".equals(textes)) {
      textes = textes.toUpperCase();
      tradMap.put("nombre", textes);
      tradMap.put("descripcion", textes);
    } else {
      tradMap.put("idioma", lang);
    }
    
    //Informaci�n de paginaci�n
    String pagPag = request.getParameter("pagPag");   
    String pagRes = request.getParameter("pagRes");
    
    if (pagPag == null) pagPag = String.valueOf(0); 
    if (pagRes == null) pagRes = String.valueOf(10);
          
    ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();    
    
		try {
			CatalegDocumentsDelegate catalegDocumentsDelegate = DelegateUtil.getCatalegDocumentsDelegate();
			resultadoBusqueda = catalegDocumentsDelegate.cercarDocumentsCatalegAmbMultiidioma(paramMap,tradMap,idExcepcio,pagPag,pagRes);
			  for (CatalegDocuments doc: castList(CatalegDocuments.class, resultadoBusqueda.getListaResultados()) ) {
			//for (CatalegDocuments doc: llistaCatalegDocuments) {
					TraduccionCatalegDocuments td = (TraduccionCatalegDocuments) doc.getTraduccion(request.getLocale().getLanguage());
					catalegDocDTO = new HashMap<String, Object>();
					catalegDocDTO.put("id", doc.getId());
					catalegDocDTO.put("nombre", td == null ? "" : td.getNombre());
					catalegDocDTO.put("descripcion", td == null ? "" : td.getDescripcion());
					llistaCatalegDocumentsDTO.add(catalegDocDTO);
			}			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
		resultats.put("nodes", llistaCatalegDocumentsDTO);

		return resultats;
	}
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        CatalegDocumentsDelegate catalegDelegate = DelegateUtil.getCatalegDocumentsDelegate();
	        CatalegDocuments cataleg = catalegDelegate.obtenirDocumentoCataleg(id);	        	        
	        
	        resultats.put("item_id", cataleg.getId());
	        
	        if (cataleg.getAdmResponsable() != null) {
				Integer admResp = cataleg.getAdmResponsable();
				resultats.put("item_admresp", admResp);
			}
	        
	        if (cataleg.getExcepcioDocumentacio() != null) {
				ExcepcioDocumentacio excepcio = cataleg.getExcepcioDocumentacio();
				resultats.put("item_excepcio", excepcio.getId());
			}
	        
			omplirCampsTraduibles(resultats, cataleg);
	        
	    } catch (DelegateException dEx) {
			log.error(ExceptionUtils.getStackTrace(dEx));
			if (dEx.isSecurityException()) {
				resultats.put("error", messageSource.getMessage("error.permisos", null, request.getLocale()));
			} else {
				resultats.put("error", messageSource.getMessage("error.altres", null, request.getLocale()));
			}
		}
	    
        return resultats;
	}


	private void omplirCampsTraduibles(Map<String, Object> resultats,
			CatalegDocuments cataleg) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null!=cataleg.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionCatalegDocuments) cataleg.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionCatalegDocuments());
			}
		}
	}
	
	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarDocumentCataleg(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			CatalegDocumentsDelegate catalegDelegate = DelegateUtil.getCatalegDocumentsDelegate();
			CatalegDocuments docCataleg = new CatalegDocuments();
			CatalegDocuments doccatalegOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				doccatalegOld = catalegDelegate.obtenirDocumentoCataleg(id);
				edicion = true;
				// Mantenim els valors originals del document en cataleg
				docCataleg.setId(doccatalegOld.getId());
				
			} catch (NumberFormatException nfe) {
				doccatalegOld = null;
				edicion = false;
			}
					
			// Idiomas
			TraduccionCatalegDocuments tc;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tc = (TraduccionCatalegDocuments) doccatalegOld.getTraduccion(lang);
					if (tc == null) {
						tc = new TraduccionCatalegDocuments();
					}
				} else {
					tc = new TraduccionCatalegDocuments();
				}

				tc.setNombre( RolUtil.limpiaCadena(request.getParameter("item_nom_" + lang)) );
				tc.setDescripcion( RolUtil.limpiaCadena(request.getParameter("item_descripcio_" + lang)) );
				
				docCataleg.setTraduccion(lang, tc);
			}
			// Fin idiomas
			
			// Administracio responsable
			try {
				String admRespStr = request.getParameter("item_admresp");
				if (admRespStr != null && !"".equals(admRespStr)) {
					Integer admResp = Integer.parseInt(admRespStr);
					docCataleg.setAdmResponsable(admResp);
				}
			} catch (NumberFormatException e) {
			    error = messageSource.getMessage("catdoc.error.administracio_responsable.incorrecte", null, request.getLocale());
				throw new NumberFormatException();
			}
			
			// Excepci� documentaci�
			try {
				String excepcioStr = request.getParameter("item_excepcio");
				if (excepcioStr != null && !"".equals(excepcioStr)) {
					Long excepcioId = Long.parseLong(excepcioStr);
					ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
					ExcepcioDocumentacio excepcio = excepcioDelegate.obtenirExcepcioDocumentacio(excepcioId);
					docCataleg.setExcepcioDocumentacio(excepcio);
				}
			} catch (NumberFormatException e) {
				error = messageSource.getMessage("catdoc.error.excepcio_documentacio.incorrecte", null, request.getLocale());
				throw new NumberFormatException();
			}
			
			Long catalegId = catalegDelegate.gravarDocumentCataleg(docCataleg);

			
			String ok = messageSource.getMessage("excdoc.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(catalegId, ok);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfe) {
			result = new IdNomDTO(-3l, error);
		}

		return result;
	}
    @RequestMapping(value = "/esborrarDocumentCataleg.do", method = POST)
	public @ResponseBody IdNomDTO esborrarDocumentCataleg(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			CatalegDocumentsDelegate catalegDelegate = DelegateUtil.getCatalegDocumentsDelegate();
			catalegDelegate.esborrarDocumentCataleg(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id del document no num�ric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	private List<IdNomDTO> llistarExcepcionsDocumentacio(String lang) throws DelegateException {
		ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
		List<IdNomDTO> excepcioObjDTOList = new ArrayList<IdNomDTO>();
		List<ExcepcioDocumentacio> llistaExcepcionsDocumentacio = excepcioDelegate.llistarExcepcioDocumentacio();
		TraduccionExcepcioDocumentacio ted;
		for (ExcepcioDocumentacio excepcio : llistaExcepcionsDocumentacio ) {
			ted = (TraduccionExcepcioDocumentacio) excepcio.getTraduccion(lang);
			excepcioObjDTOList.add(new IdNomDTO(excepcio.getId(), ted.getNombre()));
		}
		return excepcioObjDTOList;
	}
	
}

