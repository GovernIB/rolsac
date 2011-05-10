package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;

/**
 * Controller para obtener un listado de Secciones
 */
public class ListaSeccionController implements Controller {

    protected static Log log = LogFactory.getLog(ListaSeccionController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();

            if (StringUtils.isNotEmpty(request.getParameter("idSeccion"))) {
                Long idSeccion = new Long(request.getParameter("idSeccion"));

                request.setAttribute("idSeccion", idSeccion);
                request.setAttribute("listaAntecesores", seccionDelegate.listarAntecesoresSeccion(idSeccion));
                request.setAttribute("listaSecciones", seccionDelegate.listarHijosSeccion(idSeccion));
            } else {
                request.setAttribute("idSeccion", "");
                request.setAttribute("listaAntecesores", new ArrayList());
                request.setAttribute("listaSecciones", seccionDelegate.listarSeccionesRaiz());
            }

        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }

}
