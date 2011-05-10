package org.ibit.rol.sac.back.controller.ws;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.Especificacion;
import org.ibit.rol.sac.integracion.uddi.UDDIException;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;

/**
 * Controller para generar una lista de Edificios
 */
public class ListaEspecificacionesController implements Controller {

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            UDDIManager manager = UDDIUtil.getManager(request);

            Especificacion[] especificaciones = manager.listarEspecificaciones();
            request.setAttribute("especificaciones", Arrays.asList(especificaciones));

        } catch (UDDIException e) {
            throw new ServletException(e);
        }

    }
}