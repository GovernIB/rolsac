package es.caib.rolsac.back2.controller.taulesMestre;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Iniciacion;
import org.ibit.rol.sac.model.TraduccionTratamiento;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;

@Controller
@RequestMapping("/tipusUnitat/")
public class TMTipusUnitatController {
	
	private static Log log = LogFactory.getLog(TMTipusUnitatController.class);
    
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    @RequestMapping(value = "/tipusUnitat.do")
    public String pantallaTipusUnitat(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMTipusUnitat.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmTipusUnitat.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }
    

	@RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatTipusUnitat(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaUnitatsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> unitatDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
			List<Tratamiento> llistaUnitats = unitatDelegate.listarTratamientos();
			for (Tratamiento unitat: llistaUnitats) {
				TraduccionTratamiento tu = (TraduccionTratamiento) unitat.getTraduccion(request.getLocale().getLanguage());
				unitatDTO = new HashMap<String, Object>();
				unitatDTO.put("id", unitat.getId());
				unitatDTO.put("codi_estandard", unitat.getCodigoEstandar());
				unitatDTO.put("tipus", tu == null ? "" : tu.getTipo());
				unitatDTO.put("carreg_femeni", tu == null ? "" : tu.getCargoF());
				unitatDTO.put("carreg_masculi", tu == null ? "" : tu.getCargoM());
				unitatDTO.put("tratament_femeni", tu == null ? "" : tu.getTratamientoF());
				unitatDTO.put("tratament_masculi", tu == null ? "" : tu.getTratamientoM());
				
				llistaUnitatsDTO.add(unitatDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaUnitatsDTO.size());
		resultats.put("nodes", llistaUnitatsDTO);

		return resultats;
	}
	
	
	@RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
	        Tratamiento unitat = unitatDelegate.obtenerTratamiento(id);	        	        
	        
	        resultats.put("item_id", unitat.getId());
	        resultats.put("item_codi_estandard", unitat.getCodigoEstandar());
	        
	        // idiomes
	        if (unitat.getTraduccion("ca") != null) {
				resultats.put("ca", (TraduccionTratamiento) unitat.getTraduccion("ca"));
			} else {
				resultats.put("ca", new TraduccionTratamiento());
			}
	        if (unitat.getTraduccion("es") != null) {
				resultats.put("es", (TraduccionTratamiento) unitat.getTraduccion("es"));
			} else {
				resultats.put("es", new TraduccionTratamiento());
			}
	        if (unitat.getTraduccion("en") != null) {
				resultats.put("en", (TraduccionTratamiento) unitat.getTraduccion("en"));
			} else {
				resultats.put("en", new TraduccionTratamiento());
			}
	        if (unitat.getTraduccion("de") != null) {
				resultats.put("de", (TraduccionTratamiento) unitat.getTraduccion("de"));
			} else {
				resultats.put("de", new TraduccionTratamiento());
			}
	        if (unitat.getTraduccion("fr") != null) {
				resultats.put("fr", (TraduccionTratamiento) unitat.getTraduccion("fr"));
			} else {
				resultats.put("fr", new TraduccionTratamiento());
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
	public @ResponseBody IdNomDTO guardarTipusUnitat(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
			Tratamiento unitat = new Tratamiento();
			Tratamiento unitatOld;
			
			boolean edicion;
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				unitatOld = unitatDelegate.obtenerTratamiento(id);
				edicion = true;
				unitat.setId(unitatOld.getId());
			} catch (NumberFormatException nfe) {
				unitatOld = null;
				edicion = false;
			}
			
			unitat.setCodigoEstandar(request.getParameter("item_codi_estandard"));
			
			// Idiomas
			TraduccionTratamiento tu;
			IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
			List<String> langs = idiomaDelegate.listarLenguajes();

			for (String lang: langs) {
				if (edicion) {
					tu = (TraduccionTratamiento) unitatOld.getTraduccion(lang);
					if (tu == null) {
						tu = new TraduccionTratamiento();
					}
				} else {
					tu = new TraduccionTratamiento();
				}

				tu.setTipo(request.getParameter("item_tipus_" + lang));
				tu.setCargoF(request.getParameter("item_carreg_femeni_" + lang));
				tu.setCargoM(request.getParameter("item_carreg_masculi_" + lang));
				tu.setTratamientoF(request.getParameter("item_tractament_femeni_" + lang));
				tu.setTratamientoM(request.getParameter("item_tractament_masculi_" + lang));
				unitat.setTraduccion(lang, tu);
			}
			// Fin idiomas
			
			Long tipusId = unitatDelegate.grabarTratamiento(unitat);
			
			String ok = messageSource.getMessage("tipusUnitat.guardat.correcte", null, request.getLocale());
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
	

	@RequestMapping(value = "/esborrarTipusUnitat.do", method = POST)
	public @ResponseBody IdNomDTO esborrarTipusUnitat(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			TratamientoDelegate unitatDelegate = DelegateUtil.getTratamientoDelegate();
			if (unitatDelegate.tieneUnidades(id)){
				resultatStatus.setId(-1l);
				String error = messageSource.getMessage("tipusNormativa.guardat.error", null, request.getLocale());
				resultatStatus.setNom(error);
			} else {
				unitatDelegate.borrarTratamiento(id);
				resultatStatus.setId(1l);
				resultatStatus.setNom("correcte");
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				resultatStatus.setId(-1l);
			} else {
				resultatStatus.setId(-2l);
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		} catch (NumberFormatException nfEx) {
			resultatStatus.setId(-3l);
			log.error("Error: Id de tipus d'iniciació no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
}
