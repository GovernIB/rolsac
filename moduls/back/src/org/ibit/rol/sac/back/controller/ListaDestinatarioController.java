package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de hechos vitales(PORMAD)
 */
public class ListaDestinatarioController implements Controller{
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            DestinatarioDelegate delegate = DelegateUtil.getDestinatarioDelegate();
            request.setAttribute("listaDestinatario", delegate.listarDestinatarios());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}
