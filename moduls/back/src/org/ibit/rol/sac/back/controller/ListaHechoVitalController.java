/**
 * User: jhorrach
 * Date: Dec 17, 2003
 * Time: 1:45:35 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de hechos vitales
 */
public class ListaHechoVitalController implements Controller{
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            HechoVitalDelegate delegate = DelegateUtil.getHechoVitalDelegate();
            request.setAttribute("listaHechos", delegate.listarHechosVitales());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}
