package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.ParseUtil;
import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/familia/")
public class TMFamiliaController {
    
	private static Log log = LogFactory.getLog(TMFamiliaController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/familia.do")
    public String pantallaFamilia(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMFamilia.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmFamilia.jsp");

        	// afegir llista de perfils
        	String lang = request.getLocale().getLanguage();
        	String nombrePerfil;
        	List<IdNomDTO> perfilsDTO = new LinkedList<IdNomDTO>();
        	PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
        	try {
        		for (PerfilCiudadano perfil: (List<PerfilCiudadano>) perfilDelegate.listarPerfiles()) {
        			Traduccion traPerfil = perfil.getTraduccion(lang);
        			nombrePerfil = traPerfil == null ? "" : ((TraduccionPerfilCiudadano) traPerfil).getNombre();   
        			perfilsDTO.add(new IdNomDTO(perfil.getId(), nombrePerfil));
        		}
        		
        		model.put("perfils", perfilsDTO);
        	} catch (DelegateException dEx) {
    			if (dEx.isSecurityException()) {
    				log.error("Permisos insuficients: " + dEx.getMessage());
    				model.put("error", "permisos");
    			} else {
    				log.error("Error: " + dEx.getMessage());
    				model.put("error", "altres");
    			}
    		}
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatFamilia(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaFamiliaDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> familiaDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
			List<Familia> llistaFamilies = familiaDelegate.listarFamilias();
			for (Familia familia: llistaFamilies) {
				TraduccionFamilia tf = (TraduccionFamilia) familia.getTraduccion(request.getLocale().getLanguage());
				familiaDTO = new HashMap<String, Object>();
				familiaDTO.put("id", familia.getId());
				familiaDTO.put("nom", tf == null ? "" : tf.getNombre());
				familiaDTO.put("descripcio", tf == null ? "" : tf.getDescripcion());
				llistaFamiliaDTO.add(familiaDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaFamiliaDTO.size());
		resultats.put("nodes", llistaFamiliaDTO);

		return resultats;
	}
    
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
	        Familia familia = familiaDelegate.obtenerFamilia(id);	        	        
	        
	        resultats.put("item_id", familia.getId());
	        omplirCampsTraduibles(resultats, familia);
			
	        // iconos
 			if (familia.getIconos() != null) {
 				Map<String, Object> iconaDTO;
 				List<Map<String, Object>> llistaIcones = new ArrayList<Map<String, Object>>();

 				for(IconoFamilia icona: (Set<IconoFamilia>) familia.getIconos()) {
 					if (icona != null && icona.getIcono() != null) {
 						iconaDTO = new HashMap<String, Object>(3);
 						iconaDTO.put("id", icona.getId());
 						iconaDTO.put("nombre", icona.getIcono().getNombre());
 		                llistaIcones.add(iconaDTO);
 					} else {
 						log.error("La família " + familia.getId() + " te una icona null o sense arxiu.");
 					}
 	            }
 				
 				resultats.put("icones", llistaIcones);
 			} else {
 	            resultats.put("icones", null);
 	        } 
            // fin iconos
	        
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
    
    private void omplirCampsTraduibles(Map<String, Object> resultats, Familia familia) throws DelegateException {
		IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		List<String> langs = idiomaDelegate.listarLenguajes();
		
		for (String lang: langs) {
		    if (null != familia.getTraduccion(lang)) {
				resultats.put(lang, (TraduccionFamilia) familia.getTraduccion(lang));
			} else {
				resultats.put(lang, new TraduccionFamilia());
			}
		}
	}
    
    
    @RequestMapping(value = "/guardar.do", method = POST)
	public @ResponseBody IdNomDTO guardarFamilia(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
			Familia familia = new Familia();
			Familia familiaOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				familiaOld = familiaDelegate.obtenerFamilia(id);
				// Mantenemos los valores originales que tiene el usuario.
				familia.setId(familiaOld.getId());
				familia.setProcedimientosLocales(familiaOld.getProcedimientosLocales());
				familia.setIconos(familiaOld.getIconos());
				edicion = true;
			} catch (NumberFormatException nfe) {
				familiaOld = null;
				edicion = false;
			}
			
			// Idiomas
			TraduccionFamilia tf;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tf = (TraduccionFamilia) familiaOld.getTraduccion(lang);
					if (tf == null) {
						tf = new TraduccionFamilia();
					}
				} else {
					tf = new TraduccionFamilia();
				}

				tf.setNombre(request.getParameter("item_nom_" + lang));
				tf.setDescripcion(request.getParameter("item_descripcio_" + lang));
				familia.setTraduccion(lang, tf);
			}
			// Fin idiomas
			
			// Iconos: o no hay cambios o se han de eliminar algunos (las adiciones se hacen en IconaFamiliaBackController).
			if (edicion) {
				IconoFamiliaDelegate iconaFamiliaDelegate = DelegateUtil.getIconoFamiliaDelegate();
				List<Long> codisIcones = new LinkedList<Long>();
	
				// obtenim  els ids de les icones
				Enumeration<String> parametersNames = request.getParameterNames();
	            while(parametersNames.hasMoreElements()) {
	            	String nomParametre = (String) parametersNames.nextElement();                    
	                String[] elements = nomParametre.split("_");
	                
	                if ("icones".equals(elements[0]) && "id".equals(elements[1])) {
	                    // En aquest cas, elements[2] es igual al id de la icona
	                	Long id = ParseUtil.parseLong(request.getParameter(nomParametre));
	                	if (id != null) {
	                    	codisIcones.add(id);
	                	} else {
	                		log.warn("S'ha rebut un id de icona no numéric: " + id);
	                	}
	                }
	            }
	            
	            // eliminar
	            Set<Long> iconesABorrar = new HashSet<Long>();
	            Boolean iconaTrobada;
	            for (IconoFamilia icona: (Set<IconoFamilia>) familiaOld.getIconos()) {
                	iconaTrobada = Boolean.FALSE;
	                for (Long iconaId: codisIcones) {
	                	if (icona.getId().equals(iconaId)) {
	                		iconaTrobada = Boolean.TRUE;
	                		break;
	                	}
	                }
	                if (!iconaTrobada) {
	                	iconesABorrar.add(icona.getId());
	                }
                }
	            iconaFamiliaDelegate.borrarIconosFamilia(iconesABorrar);
			}
            // fin Iconos
			
			Long familiaId = familiaDelegate.grabarFamilia(familia);
			String ok = messageSource.getMessage("familia.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(familiaId, ok);

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
    
    
    @RequestMapping(value = "/esborrarFamilia.do", method = POST)
	public @ResponseBody IdNomDTO esborrarFamilia(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
			familiaDelegate.borrarFamilia(id);
			resultatStatus.setId(1l);
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de familia no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
