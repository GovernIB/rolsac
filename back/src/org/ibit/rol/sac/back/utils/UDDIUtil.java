package org.ibit.rol.sac.back.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.ibit.rol.sac.integracion.uddi.UDDIManager;

/**
 *
 */
public class UDDIUtil {

    public static UDDIManager getManager(HttpServletRequest request) {
        ServletContext ctx = request.getSession(true).getServletContext();
        String username = ctx.getInitParameter("UDDI_USER");
        String password = ctx.getInitParameter("UDDI_PASS");

        UDDIManager manager = new UDDIManager(username, password);

        return manager;
    }

}
