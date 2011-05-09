package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

/**
 * Controller que introduce la guia telefónica de Unidad Administrativa.
 */
public class GuiaController extends ArbolUAController {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

        super.perform(tilesContext, request, response, servletContext);

        try {

            String ident = request.getParameter("idUA");
            PersonalDelegate delegate = DelegateUtil.getPersonalDelegate();

            if(ident != null){
                Long id = new Long(ident);
                request.setAttribute("idUA", id);
                request.setAttribute("listaGuia", delegate.listarPersonalUA(id));
            } else {
                request.setAttribute("listaGuia", Collections.EMPTY_LIST);
            }

        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}