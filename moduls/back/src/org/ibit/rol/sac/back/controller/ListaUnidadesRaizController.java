package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class ListaUnidadesRaizController implements Controller{

     public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            UnidadAdministrativaDelegate uaDelegate = DelegateUtil.getUADelegate();
            request.setAttribute("unidadOptions", uaDelegate.listarUnidadesAdministrativasRaiz());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}
