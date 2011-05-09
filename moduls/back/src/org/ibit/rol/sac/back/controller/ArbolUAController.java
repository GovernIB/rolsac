package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Collections;

/**
 * Comprobaremos si el usuario conectado tiene acceso a esa UA y a sus padres
 */
public class ArbolUAController implements Controller {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

        try {
            String ident = request.getParameter("idUA");
            UnidadAdministrativaDelegate delegate = DelegateUtil.getUADelegate();
            
            if(ident != null){
                Long id = new Long(ident);
                request.setAttribute("id", id);

                // Se utiliza para la molla de pan
                List listaPadres = delegate.listarPadresUnidadAdministrativaAcceso(id);
                request.setAttribute("listaAntecesores", listaPadres);

                List hijos = delegate.listarHijosUA(id);
                request.setAttribute("listaUAs", hijos);
            } else {
                request.setAttribute("id", "");

                request.setAttribute("listaAntecesores", Collections.EMPTY_LIST);

                List listaRaiz = delegate.listarUnidadesAdministrativasRaiz();
                request.setAttribute("listaUAs", listaRaiz);
            }
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }
}
