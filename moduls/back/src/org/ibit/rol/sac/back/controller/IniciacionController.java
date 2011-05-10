 
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.NormativaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

public class IniciacionController implements Controller {
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {

            IniciacionDelegate tiniciacionDelegate = DelegateUtil.getIniciacionDelegate();
            

            request.setAttribute("tiniciacionOptions", tiniciacionDelegate.listarIniciacion());
            
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }


}