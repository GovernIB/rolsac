package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de Públicos Objetivos (PORMAD)
 */
public class ListaPublicoController implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            PublicoObjetivoDelegate delegate = DelegateUtil.getPublicoObjetivoDelegate();
            request.setAttribute("publicoOptions", delegate.listarPublicoObjetivo());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }


}
