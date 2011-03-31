/**
 * User: jhorrach
 * Date: Dec 17, 2003
 * Time: 1:45:35 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de Agrupaciones de hechos vitales (PORMAD)
 */
public class ListaAgrupacionHVController implements Controller{
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            AgrupacionHVDelegate delegate = DelegateUtil.getAgrupacionHVDelegate();
            request.setAttribute("agrupacionOptions", delegate.listarAgrupacionHV());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}
