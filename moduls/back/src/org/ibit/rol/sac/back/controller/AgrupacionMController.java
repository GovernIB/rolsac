package org.ibit.rol.sac.back.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.model.Seccion;


/**
 *   Obtiene la lista de las agrupaciones de Materias (ROLSAC)
 */
public class AgrupacionMController implements Controller {

    protected static Log log = LogFactory.getLog(AgrupacionMController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
            HttpServletResponse response, ServletContext servletContext)
			throws ServletException, IOException {
	
		try {

			log.warn("SE COGE LA SECCION GRUP MATERIAS POR CODIGO ESTANDAR");
			SeccionDelegate delegate = DelegateUtil.getSeccionDelegate();
            String codiEstandarSec = System.getProperty("es.caib.rolsac.codiEstandarSecGrupMat");
            Seccion seccion = delegate.obtenerSeccionCE(codiEstandarSec);
			request.setAttribute("listaSeccion", delegate.listarHijosSeccion(seccion.getId()));
			
		} catch (DelegateException e) {
			throw new ServletException(e);
		}
	
	}
}
