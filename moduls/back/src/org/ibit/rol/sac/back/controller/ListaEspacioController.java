package org.ibit.rol.sac.back.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

/**
 * Controller para obtener un listado de EspacioTerritoriales (PORMAD)
 */
public class ListaEspacioController implements Controller {

    protected static Log log = LogFactory.getLog(ListaEspacioController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            EspacioTerritorialDelegate seccionDelegate = DelegateUtil.getEspacioTerritorialDelegate();

            if (StringUtils.isNotEmpty(request.getParameter("idEspacio"))) {
                Long idEspacio = new Long(request.getParameter("idEspacio"));

                request.setAttribute("idEspacio", idEspacio);
                request.setAttribute("listaAntecesores", seccionDelegate.listarAntecesoresEspacioTerritorial(idEspacio));
                request.setAttribute("listaEspacios", seccionDelegate.listarHijosEspacioTerritorial(idEspacio));
            } else {
                request.setAttribute("idEspacio", "");
                request.setAttribute("listaAntecesores", new ArrayList<EspacioTerritorial>());
                request.setAttribute("listaEspacios", seccionDelegate.listarEspacioTerritorialesRaiz());
            }

        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }

}
