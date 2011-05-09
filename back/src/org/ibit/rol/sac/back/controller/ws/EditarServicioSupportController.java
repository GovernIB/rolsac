package org.ibit.rol.sac.back.controller.ws;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.back.utils.UDDIUtil;
import org.ibit.rol.sac.integracion.uddi.Organismo;
import org.ibit.rol.sac.integracion.uddi.UDDIException;
import org.ibit.rol.sac.integracion.uddi.UDDIManager;
import org.ibit.rol.sac.integracion.uddi.Especificacion;

/**
 * Controller para generar una lista de Edificios
 */
public class EditarServicioSupportController implements Controller {

    protected static Log log = LogFactory.getLog(EditarServicioSupportController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            UDDIManager manager = UDDIUtil.getManager(request);

            Organismo[] organismos = manager.listarOrganismosPropios();
            request.setAttribute("organismos", Arrays.asList(organismos));

            Especificacion[] especificaciones = manager.listarEspecificaciones();
            request.setAttribute("especificaciones", Arrays.asList(especificaciones));


        } catch (UDDIException e) {
            throw new ServletException(e);
        }

    }
}