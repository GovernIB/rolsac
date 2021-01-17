package es.caib.rolsac.back2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servinf/")
public class ArchivoServicioController extends ArchivoController {

	@SuppressWarnings("unused")
	private MessageSource messageSource = null;

	@Autowired
	public void setMessageSource(final MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value = "/archivo.do", method = GET)
	public void llistatFitxers(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		this.devolverArchivo(request, response);
	}

	@Override
	public Archivo obtenerArchivo(final HttpServletRequest request) throws Exception {

		final Long id = new Long(request.getParameter("id"));
		final String idioma = request.getParameter("lang");

		final ServicioDelegate servDelegate = DelegateUtil.getServicioDelegate();
		final Archivo archivo = servDelegate.obtenerServInfoAdicional(id, idioma);
		return archivo;
	}

}
