package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.dto.IdNomDTO;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *  Gestiona las UAs de la miga de pan.
 */
@Controller
@RequestMapping("/unidadAdministrativa/")
public class UnidadAdministrativaController {

	private static Log log = LogFactory.getLog(UnidadAdministrativaController.class);
	
	@SuppressWarnings("unchecked")
    @RequestMapping(value = "/listarHijos.do")
	public @ResponseBody List<IdNomDTO> llistatHijos(HttpSession session, Locale locale, 
	        @RequestParam(value = "id", required = false) Long uaId) {
		
		List<IdNomDTO> uaHijosJSON = new ArrayList<IdNomDTO>();
		List<UnidadAdministrativa> uaHijos;
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

		try {
		    if (uaId != null) {
                uaHijos = uaDelegate.listarHijosUA(uaId);
		    } else if (session.getAttribute("unidadAdministrativa") != null) {
				UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
				uaHijos = uaDelegate.listarHijosUA(ua.getId());
			} else {
				uaHijos = uaDelegate.listarUnidadesAdministrativasRaiz();
			}
			
			String lang = locale.getLanguage();
			for (UnidadAdministrativa ua: uaHijos) {
				uaHijosJSON.add(new IdNomDTO(ua.getId(), ua.getNombreUnidadAdministrativa(lang)));
			}
		} catch (DelegateException dEx) {
			MessageSource messages = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
			if (dEx.isSecurityException()) {
				String error = messages.getMessage("error.permisos", null, locale);
				uaHijosJSON.add(new IdNomDTO(-1l, error));
			} else {
				String error = messages.getMessage("error.altres", null, locale);
				uaHijosJSON.add(new IdNomDTO(-2l, error));
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}

		return uaHijosJSON;
	}


	@RequestMapping(value = "/cambiarUA.do")
	public String cambiarUA(Map<String, Object> model, HttpSession session, HttpServletRequest request) {
		try {
			Long uaId = Long.parseLong(request.getParameter("ua"));
			UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
			UnidadAdministrativa ua = uaDelegate.obtenerUnidadAdministrativa(uaId);
			session.setAttribute("unidadAdministrativa", ua);
			session.setAttribute("forzarUA", Boolean.TRUE); // Forzar a UAServletFilter a elegir una UA si no hay.
		} catch (NumberFormatException nfe) {
			session.removeAttribute("unidadAdministrativa");
			session.setAttribute("forzarUA", Boolean.FALSE); // No forzar a UAServletFilter a elegir una UA.
		} catch (DelegateException dEx) {
			session.setAttribute("forzarUA", Boolean.FALSE);
			if (dEx.isSecurityException()) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				log.error(ExceptionUtils.getStackTrace(dEx));
			}
		}
		
		String redirectTo = request.getParameter("redirectTo");
		if (redirectTo == null || "".equals(redirectTo)) {
			redirectTo = "/quadreControl/quadreControl.do";
		}
		return "redirect:" + redirectTo;
	}
	
	
	public static void actualizarUAMigaPan(HttpSession session, UnidadAdministrativa ua) {
		session.setAttribute("unidadAdministrativa", ua);		
	}
}
