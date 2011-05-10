/**
 * User: jhorrach
 * Date: Nov 28, 2003
 * Time: 12:41:51 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.back.action.contenido.ficha.EditarFichaAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Controller para obtener una lista de perfiles
 */
public class ListaPerfilController implements Controller {

    protected static Log log = LogFactory.getLog(EditarFichaAction.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            PerfilDelegate delegate = DelegateUtil.getPerfilDelegate();
            request.setAttribute("perfilOptions", delegate.listarPerfiles());

            String idPadre = request.getParameter("idPadre");
            if (idPadre != null && !idPadre.equals("")){
                SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
                Long id = new Long(request.getParameter("idPadre"));
                request.setAttribute("padre", seccionDelegate.obtenerSeccion(id));
                request.setAttribute("idPadre", id);
            }


        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }

}
