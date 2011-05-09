package org.ibit.rol.sac.back.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *   Obtiene la lista de las agrupaciones de Hecho Vital (PORMAD)
 */
public class AgrupacionHVController implements Controller {

    protected static Log log = LogFactory.getLog(AgrupacionHVController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
            HttpServletResponse response, ServletContext servletContext)
			throws ServletException, IOException {
	
		try {
			PublicoObjetivoDelegate delegate = DelegateUtil.getPublicoObjetivoDelegate();
			request.setAttribute("listaPublico", delegate.listarPublicoObjetivo());
		} catch (DelegateException e) {
			throw new ServletException(e);
		}
	
	}
}