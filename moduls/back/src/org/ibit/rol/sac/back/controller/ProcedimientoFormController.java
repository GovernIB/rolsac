package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 05-feb-2010
 * Time: 9:54:41
 * To change this template use File | Settings | File Templates.
 */
public class ProcedimientoFormController implements Controller {

    public void perform(ComponentContext componentContext, HttpServletRequest request, HttpServletResponse httpServletResponse, ServletContext servletContext) throws ServletException, IOException {
        String value = System.getProperty("es.caib.rolsac.tramite.sistra");

        if(!((value == null) || value.equals("N"))){
            request.setAttribute("mostrarTramite", "mostrar");
        }
    }
    
}
