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
import org.ibit.rol.sac.model.TraduccionTipo;
import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.TipoNormativaDelegate;

import es.caib.rolsac.back2.util.RolUtil;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
@RequestMapping("/tipusNormatives/")
public class TMTipusNormativesController {
    
	private static Log log = LogFactory.getLog(TMTipusNormativesController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/tipusNormatives.do")
    public String pantallaTipusNormativa(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusNormatives.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusNormatives.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatTipusNormatives(HttpServletRequest request) {

       List<IdNomDTO> llistaTipus = new ArrayList<IdNomDTO>();
       Map<String,Object> resultats = new HashMap<String,Object>();

       try {                      		   
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			List<Tipo> llistaTipusNormativa = tipoNormativaDelegate.listarTiposNormativas();
			for(Tipo tipo : llistaTipusNormativa){
				TraduccionTipo tp = (TraduccionTipo) tipo.getTraduccion(request.getLocale().getLanguage());
                llistaTipus.add(new IdNomDTO(tipo.getId(), tp == null ? "" : tp.getNombre()));                
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
	        
	        TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
	        Tipo tipus = tipoNormativaDelegate.obtenerTipoNormativa(id);	        	        
	        
	        resultats.put("item_id", tipus.getId());
	        
	        // idiomes
	        if (tipus.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionTipo) tipus.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionTipo());
			}
	        if (tipus.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionTipo) tipus.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionTipo());
			}
	        if (tipus.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionTipo) tipus.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionTipo());
			}
	        if (tipus.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionTipo) tipus.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionTipo());
			}
	        if (tipus.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionTipo) tipus.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionTipo());
			}
	        // fi idiomes
			
	    } catch (DelegateException dEx) {
			log.error(ExceptionUtils.getFullStackTrace(dEx));
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
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			Tipo tipus = new Tipo();
			Tipo tipusOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				tipusOld = tipoNormativaDelegate.obtenerTipoNormativa(id);
				edicion = true;
				// Mantenemos los valores originales que tiene el procedimiento.
				tipus.setId(tipusOld.getId());
			} catch (NumberFormatException nfe) {
				tipusOld = null;
				edicion = false;
			}
					
			// Idiomas
			TraduccionTipo tp;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tp = (TraduccionTipo) tipusOld.getTraduccion(lang);
					if (tp == null) {
						tp = new TraduccionTipo();
					}
				} else {
					tp = new TraduccionTipo();
				}

				tp.setNombre(request.getParameter("item_nom_" + lang));
				tipus.setTraduccion(lang, tp);
			}
			// Fin idiomas
			
			Long tipusId = tipoNormativaDelegate.grabarTipoNormativa(tipus);

			
			String ok = messageSource.getMessage("tipusNormativa.guardat.correcte", null, request.getLocale());
			result = new IdNomDTO(tipusId, ok);

		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				error = messageSource.getMessage("error.permisos", null, request.getLocale());
				result = new IdNomDTO(-1l, error);
			} else {
				error = messageSource.getMessage("error.altres", null, request.getLocale());
				result = new IdNomDTO(-2l, error);
				log.error(ExceptionUtils.getFullStackTrace(dEx));
			}
		} catch (NumberFormatException nfe) {
			result = new IdNomDTO(-3l, error);
		}

		return result;
	}
	
	
	@RequestMapping(value = "/esborrarTipusNormativa.do", method = POST)
	public @ResponseBody IdNomDTO esborrarTipusNormativa(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			TipoNormativaDelegate tipoNormativaDelegate = DelegateUtil.getTipoNormativaDelegate();
			tipoNormativaDelegate.borrarTipoNormativa(id);
			resultatStatus.setId(1l);
			resultatStatus.setNom("correcte");
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getFullStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de tipus de normativa no númeric: " + ExceptionUtils.getFullStackTrace(nfEx));
		}
		return resultatStatus;
	}
	
}
