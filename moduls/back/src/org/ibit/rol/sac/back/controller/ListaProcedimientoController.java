/**
 * User: jhorrach
 * Date: Mar 23, 2004
 * Time: 1:29:10 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de Procedimientos
 */
public class ListaProcedimientoController implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            ProcedimientoDelegate delegate = DelegateUtil.getProcedimientoDelegate();
            request.setAttribute("procedimientoOptions", delegate.listarProcedimientos());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }


}
