package org.ibit.rol.sac.back.controller.ws;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller para generar una lista de Edificios
 */
public class ListaUnidadesSinBusinessKeyController implements Controller {

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            UnidadAdministrativaDelegate delegate = DelegateUtil.getUADelegate();
            List unidades = delegate.listarUnidadesAdministrativasRaiz(false);

            request.setAttribute("unidades", unidades);

        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}