package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Obtiene un Espacio Territorial padre/raiz (PORMAD)
 */
public class EspacioController implements Controller {

    protected static Log log = LogFactory.getLog(EspacioController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            String idPadre = request.getParameter("idPadre");
            if (idPadre != null && !idPadre.equals("")){
            	EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
                Long id = new Long(request.getParameter("idPadre"));
                request.setAttribute("padre", espacioDelegate.obtenerEspacioTerritorial(id));
                request.setAttribute("idPadre", id);
            }


        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }

}
