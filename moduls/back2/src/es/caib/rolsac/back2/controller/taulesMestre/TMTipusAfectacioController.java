package es.caib.rolsac.back2.controller.taulesMestre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.model.TraduccionTipoAfectacion;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TipoAfectacionDelegate;

import es.caib.rolsac.back2.util.RolUtil;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/tipusAfectacio/")
public class TMTipusAfectacioController {

	private static Log log = LogFactory.getLog(TMTipusAfectacioController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/tipusAfectacions.do")
    public String pantallaTipusAfectacio(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusAfectacio.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusAfectacio.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatTipusAfectacions(HttpServletRequest request) {

       List<IdNomDTO> llistaTipus = new ArrayList<IdNomDTO>();
       Map<String,Object> resultats = new HashMap<String,Object>();

       try {                      		   
			TipoAfectacionDelegate tipoAfectacioDelegate = DelegateUtil.getTipoAfectacionDelegate();
			List<TipoAfectacion> llistaTipusAfectacio = tipoAfectacioDelegate.listarTiposAfectaciones();
			for(TipoAfectacion tipoAfectacion : llistaTipusAfectacio){
				TraduccionTipoAfectacion tp = (TraduccionTipoAfectacion) tipoAfectacion.getTraduccion(request.getLocale().getLanguage());
                llistaTipus.add(new IdNomDTO(tipoAfectacion.getId(), tp == null ? "" : tp.getNombre()));                
           }
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
            } else {
            	log.error("Error: " + dEx.getMessage());
            }
		}

		resultats.put("total", llistaTipus.size());
        resultats.put("nodes", llistaTipus);

		return resultats;
	}
    
    

	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        TipoAfectacionDelegate tipoAfectacioDelegate = DelegateUtil.getTipoAfectacionDelegate();
	        TipoAfectacion tipusAfectacio = tipoAfectacioDelegate.obtenerTipoAfectacion(id);	        	        
	        
	        resultats.put("item_id", tipusAfectacio.getId());
	        
	        // idiomes
	        if (tipusAfectacio.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionTipoAfectacion) tipusAfectacio.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionTipoAfectacion());
			}
	        if (tipusAfectacio.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionTipoAfectacion) tipusAfectacio.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionTipoAfectacion());
			}
	        if (tipusAfectacio.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionTipoAfectacion) tipusAfectacio.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionTipoAfectacion());
			}
	        if (tipusAfectacio.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionTipoAfectacion) tipusAfectacio.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionTipoAfectacion());
			}
	        if (tipusAfectacio.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionTipoAfectacion) tipusAfectacio.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionTipoAfectacion());
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
	public @ResponseBody IdNomDTO guardarProcediment(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			TipoAfectacionDelegate tipoAfectacioDelegate = DelegateUtil.getTipoAfectacionDelegate();
			TipoAfectacion tipusAfectacio = new TipoAfectacion();
			TipoAfectacion tipusAfectacioOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				tipusAfectacioOld = tipoAfectacioDelegate.obtenerTipoAfectacion(id);
				edicion = true;
				// Mantenemos los valores originales que tiene el procedimiento.
				tipusAfectacio.setId(tipusAfectacioOld.getId());
			} catch (NumberFormatException nfe) {
				tipusAfectacioOld = null;
				edicion = false;
			}
					
			// Idiomas
			TraduccionTipoAfectacion tpa;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tpa = (TraduccionTipoAfectacion) tipusAfectacioOld.getTraduccion(lang);
					if (tpa == null) {
						tpa = new TraduccionTipoAfectacion();
					}
				} else {
					tpa = new TraduccionTipoAfectacion();
				}

				tpa.setNombre(request.getParameter("item_nom_" + lang));
				tipusAfectacio.setTraduccion(lang, tpa);
			}
			// Fin idiomas
			
			Long tipusId = tipoAfectacioDelegate.grabarTipoAfectacion(tipusAfectacio);

			
			String ok = messageSource.getMessage("tipusAfectacio.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(tipusId, ok);

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
	
	
	@RequestMapping(value = "/esborrarTipusAfectacio.do", method = POST)
	public @ResponseBody IdNomDTO esborrarTipusAfectacio(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			TipoAfectacionDelegate tipoAfectacioDelegate = DelegateUtil.getTipoAfectacionDelegate();
			tipoAfectacioDelegate.borrarTipoAfectacion(id);
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
			log.error("Error: Id de tipus de Afectacio no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
}
