package es.caib.rolsac.back2.controller.taulesMestre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.BoletinDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;

import es.caib.rolsac.back2.util.RolUtil;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/destinataris/")
public class TMDestinatarisController {
    
	private static Log log = LogFactory.getLog(TMDestinatarisController.class);
	
    private MessageSource messageSource = null;
    
    @Autowired
    public void setMessageSource(MessageSource messageSource){
        this.messageSource = messageSource;
    }
    
    
    @RequestMapping(value = "/destinataris.do")
    public String pantallaDestinataris(Map<String, Object> model, HttpServletRequest request) {
        model.put("menu", 1);
        model.put("submenu", "layout/submenu/submenuTMDestinataris.jsp");
        
        RolUtil rolUtil= new RolUtil(request);
        if (rolUtil.userIsAdmin()) {
        	model.put("escriptori", "pantalles/taulesMestres/tmDestinataris.jsp");
        } else {
        	model.put("error", "permisos");
        }

        return "index";
    }  

    
    @RequestMapping(value = "/llistat.do")
   	public @ResponseBody Map<String, Object> llistatDestinataris(HttpServletRequest request) {
   	
   		List<Map<String, Object>> llistaDestinatarisDTO = new ArrayList<Map<String, Object>>();
   		Map<String, Object> destinatariDTO;
   		Map<String, Object> resultats = new HashMap<String, Object>();

   		try {
   			DestinatarioDelegate destinatariDelegate = DelegateUtil.getDestinatarioDelegate();
   			List<Destinatario> llistaDestinataris = destinatariDelegate.listarDestinatarios();
   			for (Destinatario destinatari: llistaDestinataris) {
   				destinatariDTO = new HashMap<String, Object>();
   				destinatariDTO.put("id", destinatari.getId());
   				destinatariDTO.put("nom", destinatari.getNombre());
   				destinatariDTO.put("email", destinatari.getEmail());
   				destinatariDTO.put("endpoint", destinatari.getEndpoint());
   				destinatariDTO.put("id_remot", destinatari.getIdRemoto());
   				llistaDestinatarisDTO.add(destinatariDTO);
   			}
   		} catch (DelegateException dEx) {
   			if (dEx.isSecurityException()) {
   				log.error("Permisos insuficients: " + dEx.getMessage());
   			} else {
   				log.error("Error: " + dEx.getMessage());
   			}
   		}

   		resultats.put("total", llistaDestinatarisDTO.size());
   		resultats.put("nodes", llistaDestinatarisDTO);

   		return resultats;
   	}
    
    
    @RequestMapping(value = "/pagDetall.do")
	public @ResponseBody Map<String, Object> recuperaDetall(HttpServletRequest request) {
	    Map<String, Object> resultats = new HashMap<String, Object>();
	    try {
	        Long id = new Long(request.getParameter("id"));
	        
	        DestinatarioDelegate destinatariDelegate = DelegateUtil.getDestinatarioDelegate();
	        Destinatario destinatari = destinatariDelegate.obtenerDestinatario(id);	        	        
	        
	        resultats.put("item_id", destinatari.getId());
	        resultats.put("item_nom", destinatari.getNombre());
	        resultats.put("item_email", destinatari.getEmail());
	        resultats.put("item_endpoint", destinatari.getEndpoint());
	        resultats.put("item_id_remot", destinatari.getIdRemoto());
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
	public @ResponseBody IdNomDTO guardarDestinatari(HttpServletRequest request) {

		IdNomDTO result = null;
		String error = null;

		try {
			DestinatarioDelegate destinatariDelegate = DelegateUtil.getDestinatarioDelegate();
			Destinatario destinatari = new Destinatario();
						
			try {
				Long id = Long.parseLong(request.getParameter("item_id"));
				destinatariDelegate.obtenerDestinatario(id);
				destinatari.setId(id);
			} catch (NumberFormatException nfe) {
			}
			
			destinatari.setNombre(request.getParameter("item_nom"));
			destinatari.setEmail(request.getParameter("item_email"));
			destinatari.setEndpoint(request.getParameter("item_endpoint"));
			destinatari.setIdRemoto(request.getParameter("item_id_remot"));
			
			Long tipusId = destinatariDelegate.grabarDestinatario(destinatari);
			
			String ok = messageSource.getMessage("destinatari.guardat.correcte", null, request.getLocale());
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
    
    
    @RequestMapping(value = "/esborrarDestinatari.do", method = POST)
	public @ResponseBody IdNomDTO esborrarDestinatari(HttpServletRequest request) {
		IdNomDTO resultatStatus = new IdNomDTO();
		try {
			Long id = new Long(request.getParameter("id"));
			DestinatarioDelegate destinatariDelegate = DelegateUtil.getDestinatarioDelegate();
			destinatariDelegate.borrarDestinatario(id);
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
			log.error("Error: Id de destinatari no númeric: " + ExceptionUtils.getStackTrace(nfEx));
		}
		return resultatStatus;
	}
    
}
