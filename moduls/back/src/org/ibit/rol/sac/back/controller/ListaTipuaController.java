package org.ibit.rol.sac.back.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;

/**
 * Controller que introduce el listado de tipos de Unidad Administrativa.
 */
public class ListaTipuaController implements Controller {

    /**protected static Log log = LogFactory.getLog(ListaTipuaController.class);**/

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

        try {
            TratamientoDelegate delegate = DelegateUtil.getTratamientoDelegate();
            request.setAttribute("listaTipuas", delegate.listarTratamientos());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}
