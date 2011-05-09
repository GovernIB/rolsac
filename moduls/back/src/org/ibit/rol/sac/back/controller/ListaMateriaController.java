/**
 * User: jhorrach
 * Date: Nov 28, 2003
 * Time: 12:44:04 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.MateriaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import java.io.IOException;

/**
 * Controller para generar una lista de Materias
 */
public class ListaMateriaController implements Controller {

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            MateriaDelegate delegate = DelegateUtil.getMateriaDelegate();
            request.setAttribute("materiaOptions", delegate.listarMaterias());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}
