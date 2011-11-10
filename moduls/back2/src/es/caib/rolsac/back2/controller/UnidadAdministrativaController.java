package es.caib.rolsac.back2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import org.ibit.rol.sac.model.transients.IdNomTransient;


@Controller
@RequestMapping("/unidadAdministrativa/")
public class UnidadAdministrativaController {

	@RequestMapping(value = "/listarHijos.do")
	public @ResponseBody List<IdNomTransient> llistatHijos(HttpSession session, HttpServletRequest request) {
		
		List<IdNomTransient> uaHijosJSON = new ArrayList<IdNomTransient>();
		List<UnidadAdministrativa> uaHijos = null;
		UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();

		try {
			if (session.getAttribute("unidadAdministrativa") != null) {
				UnidadAdministrativa ua = (UnidadAdministrativa) session.getAttribute("unidadAdministrativa");
				uaHijos = uaDelegate.listarHijosUA(ua.getId());
			} else {
				uaHijos = uaDelegate.listarUnidadesAdministrativasRaiz();
			}
			
			String lang = request.getLocale().getLanguage();
			for (UnidadAdministrativa ua: uaHijos) {
				uaHijosJSON.add(new IdNomTransient(ua.getId(), ua.getNombreUnidadAdministrativa(lang)));
			}
		} catch (DelegateException dEx) {
			MessageSource messages = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
			if (dEx.getCause() instanceof SecurityException) {
				String error = messages.getMessage("error.permisos", null, request.getLocale());
				uaHijosJSON.add(new IdNomTransient(-1l, error));
			} else {
				String error = messages.getMessage("error.altres", null, request.getLocale());
				uaHijosJSON.add(new IdNomTransient(-2l, error));
				dEx.printStackTrace();
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
			if (dEx.getCause() instanceof SecurityException) {
				model.put("error", "permisos");
			} else {
				model.put("error", "altres");
				dEx.printStackTrace();
			}
		}
		
		String redirectTo = request.getParameter("redirectTo");
		if (redirectTo == null || "".equals(redirectTo)) {
			redirectTo = "/quadreControl/quadreControl.do";
		}
		return "redirect:" + redirectTo;
	}
	
}
