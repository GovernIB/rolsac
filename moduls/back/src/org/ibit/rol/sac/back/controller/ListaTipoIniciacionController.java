/**
 * User: jhorrach
 * Date: Dec 9, 2003
 * Time: 1:23:51 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller para obtener una lista de Tipo tiniciacion
 */
public class ListaTipoIniciacionController implements Controller {
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
        	IniciacionDelegate delegate = DelegateUtil.getIniciacionDelegate();
            request.setAttribute("tipoOptions", delegate.listarIniciacion());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }


}
