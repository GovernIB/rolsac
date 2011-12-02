package es.caib.rolsac.back2.controller.taulesMestre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.BoletinDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.util.RolUtil;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/butlletinsOficials/")
public class TMButlletinsOficialsController {
    
    private static Log log = LogFactory.getLog(TMButlletinsOficialsController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/butlletins.do")
    public String pantallaButlletins(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMButlletinsOficials.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmButlletinsOficials.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }    
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatButlletins(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaButlletinsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> butlletiDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		try {
			BoletinDelegate butlletiDelegate = DelegateUtil.getBoletinDelegate();
			List<Boletin> llistaButlletins = butlletiDelegate.listarBoletines();
			for (Boletin butlleti: llistaButlletins) {
				butlletiDTO = new HashMap<String, Object>();
				butlletiDTO.put("id", butlleti.getId());
				butlletiDTO.put("enllas", butlleti.getEnlace());
				butlletiDTO.put("nom", butlleti.getNombre());
				llistaButlletinsDTO.add(butlletiDTO);
			}
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", llistaButlletinsDTO.size());
		resultats.put("nodes", llistaButlletinsDTO);

		return resultats;
	}
    
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        BoletinDelegate butlletiDelegate = DelegateUtil.getBoletinDelegate();
	        Boletin butlleti = butlletiDelegate.obtenerBoletin(id);	        	        
	        
	        resultats.put("item_id", butlleti.getId());
	        resultats.put("item_nom", butlleti.getNombre());
	        resultats.put("item_enllas", butlleti.getEnlace());
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
	public @ResponseBody IdNomDTO guardarButlleti(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			BoletinDelegate butlletiDelegate = DelegateUtil.getBoletinDelegate();
			Boletin butlleti = new Boletin();
						
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				butlletiDelegate.obtenerBoletin(id);
				butlleti.setId(id);
			} catch (NumberFormatException nfe) {
			}
			
			butlleti.setNombre(request.getParameter("item_nom"));
			butlleti.setEnlace(request.getParameter("item_enllas"));
			Long tipusId = butlletiDelegate.grabarBoletin(butlleti);
			
			String ok = messageSource.getMessage("butlleti.guardat.correcte", null, request.getLocale());
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
    
    
    @RequestMapping(value = "/esborrarButlleti.do", method = POST)
	public @ResponseBody IdNomDTO esborrarButlleti(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			BoletinDelegate butlletiDelegate = DelegateUtil.getBoletinDelegate();
			butlletiDelegate.borrarBoletin(id);
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
			log.error("Error: Id de bulletí no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
