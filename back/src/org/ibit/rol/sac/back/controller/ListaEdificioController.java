package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller para generar una lista de Edificios
 */
public class ListaEdificioController implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            EdificioDelegate delegate = DelegateUtil.getEdificioDelegate();
            request.setAttribute("edificioOptions", delegate.listarEdificios());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}