package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;

/**
 *
 */
public class IdentificacionController implements Controller {

    private static String[] roles = new String[]{"sacsystem", "sacadmin", "sacoper", "sacsuper"};

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

        if (request.getRemoteUser() != null) {
            tilesContext.putAttribute("username", request.getRemoteUser());

            List rolenames = new ArrayList(roles.length);
            for (int i = 0; i < roles.length; i++) {
                if (request.isUserInRole(roles[i])) {
                    rolenames.add(roles[i]);
                }
            }
            tilesContext.putAttribute("rolenames", rolenames);
        }
    }
}
