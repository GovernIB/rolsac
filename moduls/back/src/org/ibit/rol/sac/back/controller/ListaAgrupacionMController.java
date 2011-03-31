package org.ibit.rol.sac.back.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.AgrupacionMDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

/**
 * Retorna una lista de Agrupaciones de materias (ROLSAC)
 */
public class ListaAgrupacionMController implements Controller {

    public void perform(ComponentContext componentContext, HttpServletRequest request,
            HttpServletResponse response, ServletContext servletContext)
	throws ServletException, IOException {
	
		try {
			AgrupacionMDelegate delegate = DelegateUtil.getAgrupacionMDelegate();
			request.setAttribute("agrupacionmOptions", delegate.listarAgrupacionM());
		} catch (DelegateException e) {
			throw new ServletException(e);
		}
	}
	
}
