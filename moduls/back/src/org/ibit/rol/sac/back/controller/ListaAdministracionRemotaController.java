package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizadorSingleton;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retorna una lista de administraciones remotas (PORMAD)
 */
public class ListaAdministracionRemotaController implements Controller{
    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
			SincronizadorSingleton.Estado estado = SincronizadorSingleton.running();

			switch (estado) {
			case Ejecutando:
				request.setAttribute("running", "eject");
				request.setAttribute("idRunning", SincronizadorSingleton.getAdminRemota().getId());
				break;
			case Error:
				request.setAttribute("running", "error");
				request.setAttribute("idRunning", SincronizadorSingleton.getAdminRemota().getId());
				break;
			case Parado:
				request.setAttribute("running", "parado");
				request.setAttribute("idRunning", "");
				break;
			}
			
            AdministracionRemotaDelegate delegate = DelegateUtil.getAdministracionRemotaDelegate();
            request.setAttribute("listaAdministracionRemota", delegate.listarAdministracionRemota());
        } catch (DelegateException e) {
            throw new ServletException(e);
        }
    }

}
