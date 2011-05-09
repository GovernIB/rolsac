/**
 * User: jhorrach
 * Date: Mar 3, 2004
 * Time: 1:48:27 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProcedimientoController implements Controller {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {


        try {
            if ("Familia".equals(request.getParameter("opcion"))){
                FamiliaDelegate delegate = DelegateUtil.getFamiliaDelegate();

                request.setAttribute("elementOptions", delegate.listarFamilias());
                request.setAttribute("opcion", "Familia");
            }
            if ("Iniciacion".equals(request.getParameter("opcion"))){
                IniciacionDelegate delegate = DelegateUtil.getIniciacionDelegate();
             
            	request.setAttribute("elementOptions",delegate.listarIniciacion());
                request.setAttribute("opcion", "Iniciacion");
            }
            if ("materia".equals(request.getParameter("opcion"))){
                MateriaDelegate delegate = DelegateUtil.getMateriaDelegate();

                request.setAttribute("elementOptions", delegate.listarMaterias());
                request.setAttribute("opcion", "materia");
            }
            if ("normativa".equals(request.getParameter("opcion"))){
                NormativaDelegate delegate = DelegateUtil.getNormativaDelegate();

                request.setAttribute("elementOptions", delegate.listarNormativas());
                request.setAttribute("opcion", "normativa");
            }

        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }

}
