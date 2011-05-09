package org.ibit.rol.sac.back.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizadorSingleton;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  (PORMAD)
 */
public class AdminRemotaController implements Controller {

    protected static Log log = LogFactory.getLog(AdminRemotaController.class);

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        try {
            EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
            request.setAttribute("listaEspacios", espacioDelegate.listarEspaciosTerritoriales());
        } catch (DelegateException e) {
            log.error(e);
        }

		SincronizadorSingleton.Estado estado = SincronizadorSingleton.running();
		
		switch (estado) {
		case Ejecutando:
			request.setAttribute("running", "eject");
			request.setAttribute("idRunning", SincronizadorSingleton.getAdminRemota().getId());
			if(SincronizadorSingleton.Tipo.Alta.equals(SincronizadorSingleton.getTipo())){
				request.setAttribute("runtipo", "alta");
			}else {
				request.setAttribute("runtipo", "baja");
			}
			break;
		case Error:
			request.setAttribute("running", "error");
			request.setAttribute("idRunning", SincronizadorSingleton.getAdminRemota().getId());
			request.setAttribute("runerror", SincronizadorSingleton.getException().toString());
			break;
		case Parado:
			request.setAttribute("running", "parado");
			request.setAttribute("idRunning", "null");
			break;
		}
    }
}