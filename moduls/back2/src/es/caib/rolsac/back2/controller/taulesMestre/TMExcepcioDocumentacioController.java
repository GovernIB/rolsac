package es.caib.rolsac.back2.controller.taulesMestre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.ExcepcioDocumentacio;
import org.ibit.rol.sac.model.TraduccionExcepcioDocumentacio;

import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ExcepcioDocumentacioDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/excepcioDocumentacio/")
public class TMExcepcioDocumentacioController extends PantallaBaseController {
    
	private static Log log = LogFactory.getLog(TMExcepcioDocumentacioController.class);
	
    @RequestMapping(value = "/excepcioDocumentacio.do")
    public String pantallaExcepcioDocumentacio(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMExcepcioDocumentacio.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmExcepcioDocumentacio.jsp");
        } else {
        	model.put("error", "permisos");
        }

		loadIndexModel (model, request);	
        return "index";
    }
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatTipusNormatives(HttpServletRequest request) {

       List<IdNomDTO> llistaExcepcio = new ArrayList<IdNomDTO>();
       Map<String,Object> resultats = new HashMap<String,Object>();

       //Información de paginación
       String pagPag = request.getParameter("pagPag");   
       String pagRes = request.getParameter("pagRes");
       
       if (pagPag == null) pagPag = String.valueOf(0); 
       if (pagRes == null) pagRes = String.valueOf(10);
             
       ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();            
       
       try {                      		   
    	   ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
         resultadoBusqueda = excepcioDelegate.llistarExcepcioDocumentacio(Integer.parseInt(pagPag), Integer.parseInt(pagRes));
         
         for (ExcepcioDocumentacio excepcio: castList(ExcepcioDocumentacio.class, resultadoBusqueda.getListaResultados()) ) {
				  TraduccionExcepcioDocumentacio traduccioExcepcio = (TraduccionExcepcioDocumentacio) excepcio.getTraduccion(request.getLocale().getLanguage());
				  llistaExcepcio.add(new IdNomDTO(excepcio.getId(), traduccioExcepcio == null ? "" : traduccioExcepcio.getNombre()));                
         }
		    } catch (DelegateException dEx) {
			 if (dEx.isSecurityException()) {
				  log.error("Permisos insuficients: " + dEx.getMessage());
       } else {
            	log.error("Error: " + dEx.getMessage());
       }
		  }

		resultats.put("total", resultadoBusqueda.getTotalResultados());
        resultats.put("nodes", llistaExcepcio);

		return resultats;
	}
    
    

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
	        ExcepcioDocumentacio excepcio = excepcioDelegate.obtenirExcepcioDocumentacio(id);      	        
	        
	        resultats.put("item_id", excepcio.getId());
	        
	        // idiomes
	        if (excepcio.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionExcepcioDocumentacio) excepcio.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionExcepcioDocumentacio());
			}
	        if (excepcio.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionExcepcioDocumentacio) excepcio.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionExcepcioDocumentacio());
			}
	        if (excepcio.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionExcepcioDocumentacio) excepcio.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionExcepcioDocumentacio());
			}
	        if (excepcio.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionExcepcioDocumentacio) excepcio.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionExcepcioDocumentacio());
			}
	        if (excepcio.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionExcepcioDocumentacio) excepcio.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionExcepcioDocumentacio());
			}
	        // fi idiomes
			
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
    
	
	@RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarExcepcioDocumentacio(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
			ExcepcioDocumentacio excepcio = new ExcepcioDocumentacio();
			ExcepcioDocumentacio excepcioOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				excepcioOld = excepcioDelegate.obtenirExcepcioDocumentacio(id);
				edicion = true;
				// Mantenim els valors originals de l'excepcio
				excepcio.setId(excepcioOld.getId());
			} catch (NumberFormatException nfe) {
				excepcioOld = null;
				edicion = false;
			}
					
			// Idiomas
			TraduccionExcepcioDocumentacio te;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					te = (TraduccionExcepcioDocumentacio) excepcioOld.getTraduccion(lang);
					if (te == null) {
						te = new TraduccionExcepcioDocumentacio();
					}
				} else {
					te = new TraduccionExcepcioDocumentacio();
				}

				te.setNombre(request.getParameter("item_nom_" + lang));
				te.setDescripcion(request.getParameter("item_descri_" + lang));
				excepcio.setTraduccion(lang, te);
			}
			// Fin idiomas
			
			Long excepcioId = excepcioDelegate.gravarExcepcioDocumentacio(excepcio);

			
			String ok = messageSource.getMessage("excdoc.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(excepcioId, ok);

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
	
	
	@RequestMapping(value = "/esborrarExcepcioDocumentacio.do", method = POST)
	public @ResponseBody IdNomDTO esborrarExcepcioDocumentacio(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			ExcepcioDocumentacioDelegate excepcioDelegate = DelegateUtil.getExcepcioDocumentacioDelegate();
			excepcioDelegate.esborrarExcepcioDocumentacio(id);
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
			log.error("Error: Id d'excepcio de documentació no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
}
