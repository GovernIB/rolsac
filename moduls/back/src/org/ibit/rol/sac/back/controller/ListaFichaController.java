/**
 * User: jhorrach
 * Date: Mar 31, 2004
 * Time: 11:21:38 AM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller para generar una lista de fichas
 */
public class ListaFichaController implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            FichaDelegate delegate = DelegateUtil.getFichaDelegate();
            request.setAttribute("fichaOptions", delegate.listarFichasThin());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}