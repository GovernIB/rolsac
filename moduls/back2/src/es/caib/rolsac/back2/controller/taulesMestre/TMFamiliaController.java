package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IconoFamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
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
			
			// Iconos
			/* Para hacer menos accesos a BBDD se comprueba si es edicion o no. 
             * En el primer caso es bastante probable que se repitan la mayoria de iconos.
             */
			if (request.getParameter("iconos") != null && !"".equals(request.getParameter("iconos"))){
				IconoFamiliaDelegate iconoFamiliaDelegate = DelegateUtil.getIconoFamiliaDelegate();
                Set<IconoFamilia> iconesNoves = new HashSet<IconoFamilia>();
                String[] codisIconesNoves = request.getParameter("icones").split(",");
                
                if (edicion){
                    for (int i = 0; i<codisIconesNoves.length; i++){
                    	for (IconoFamilia icone: (Set<IconoFamilia>) familiaOld.getIconos()){
                          if(icone.getId().equals(Long.valueOf(codisIconesNoves[i]))) { // icono ya existente
                              iconesNoves.add(icone);
                              codisIconesNoves[i] = null;
                              break;
                          }
                      }                            
                    }                         
                }                    
                
                for (String codiUA: codisIconesNoves){
                    if (codiUA != null){
                    	iconesNoves.add(iconoFamiliaDelegate.obtenerIconoFamilia(ParseUtil.parseLong(codiUA)));
                    }                        
                }
                
                familia.setIconos(iconesNoves);                                                 
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
