package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

/**
 * Controller que gestiona el personal de la guia telefónica.
 */
public class PersonalController implements Controller {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {


           String ident = request.getParameter("idUA");

            if(ident != null && ident.length()>0){
                Long id = new Long(ident);
                request.setAttribute("idUA", id);
                try {
                	request.setAttribute("Uatexto", ((TraduccionUA)(DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(id).getTraduccion())).getNombre());
                }
                catch (DelegateException e) {
                    throw new ServletException(e);
                }
            }


    }

}