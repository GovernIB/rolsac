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
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.BoletinDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import es.caib.rolsac.back2.controller.PantallaBaseController;
import es.caib.rolsac.back2.util.RolUtil;
import es.caib.rolsac.utils.ResultadoBusqueda;

@Controller
@RequestMapping("/butlletinsOficials/")
public class TMButlletinsOficialsController extends PantallaBaseController {
    
    private static Log log = LogFactory.getLog(TMButlletinsOficialsController.class);
	
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

		loadIndexModel (model, request);	
        return "index";
    }    
    
    
    @RequestMapping(value = "/llistat.do")
	public @ResponseBody Map<String, Object> llistatButlletins(HttpServletRequest request) {
	
		List<Map<String, Object>> llistaButlletinsDTO = new ArrayList<Map<String, Object>>();
		Map<String, Object> butlletiDTO;
		Map<String, Object> resultats = new HashMap<String, Object>();

		//Información de paginación
		String pagPag = request.getParameter("pagPag");		
		String pagRes = request.getParameter("pagRes");
		
		if (pagPag == null) pagPag = String.valueOf(0); 
		if (pagRes == null) pagRes = String.valueOf(10);
     		
		ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();       		
		
		try {
			
			BoletinDelegate butlletiDelegate = DelegateUtil.getBoletinDelegate();
			
			resultadoBusqueda = butlletiDelegate.listarBoletines(Integer.parseInt(pagPag), Integer.parseInt(pagRes));
			
			for (Object o : resultadoBusqueda.getListaResultados()) {
				
				Long id = (Long) ((Object[]) o)[0];
				String nom = (String) ((Object[]) o)[1];
				String enllas = (String) ((Object[]) o)[2];
				
				butlletiDTO = new HashMap<String, Object>();
				butlletiDTO.put("id", id);
				butlletiDTO.put("enllas", enllas);
				butlletiDTO.put("nom", nom);
				
				llistaButlletinsDTO.add(butlletiDTO);				
				
			}
			
		} catch (DelegateException dEx) {
			if (dEx.isSecurityException()) {
				log.error("Permisos insuficients: " + dEx.getMessage());
			} else {
				log.error("Error: " + dEx.getMessage());
			}
		}

		resultats.put("total", resultadoBusqueda.getTotalResultados());
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
			log.error("Error: Id de bullet� no n�meric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
