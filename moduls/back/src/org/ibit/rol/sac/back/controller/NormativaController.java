/**
 * User: jhorrach
 * Date: Feb 24, 2004
 * Time: 12:05:53 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.back.action.contenido.ficha.EditarFichaAction;
import org.ibit.rol.sac.micropersistence.delegate.MicrositeDelegate;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NormativaController implements Controller {

	protected static Log log = LogFactory.getLog(NormativaController.class);

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {


        try {
            if ("Tipo".equals(request.getParameter("opcion"))){
                TipoNormativaDelegate delegate = DelegateUtil.getTipoNormativaDelegate();

                request.setAttribute("elementOptions", delegate.listarTiposNormativas());
                request.setAttribute("opcion", "Tipo");
            }

            if ("Boletin".equals(request.getParameter("opcion"))){
                BoletinDelegate delegate = DelegateUtil.getBoletinDelegate();

                request.setAttribute("elementOptions", delegate.listarBoletines());
                request.setAttribute("opcion", "Boletin");
            }

            if ("procedimiento".equals(request.getParameter("opcion"))){
                ProcedimientoDelegate delegate = DelegateUtil.getProcedimientoDelegate();

                request.setAttribute("elementOptions", delegate.listarProcedimientos());
                request.setAttribute("opcion", "procedimiento");
            }
            
            //MODIFICADO INDRA (AITOR TOMAS PIN)
            if ("Micro".equals(request.getParameter("opcion"))){
            	
            	//obtenemos la ip y el puerto para los links
            	/*String dir = "";
            	String ip = request.getServerName().toString();
            	String port = String.valueOf(request.getServerPort());
            	dir = ip + ":" + port;
            	request.setAttribute("direccion", dir);*/
            	
            	//comprobar permisos..... 
            	org.ibit.rol.sac.micropersistence.delegate.UsuarioDelegate usudel=org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getUsuarioDelegate();
            	org.ibit.rol.sac.micromodel.Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());
                             
                MicrositeDelegate delegate = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
                Map propi2 = new HashMap();
            	//propi2.put("traduccion.titulo","%"+request.getParameter("filtro")+"%");
                propi2.put("visible","%S%");
                request.setAttribute("elementOptions", delegate.listarMicrositesFiltro(usu, propi2));
                request.setAttribute("opcion", "Micro");
                request.setAttribute("portalUrl", obtenerUrlBase());
                
            }


        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

	private String obtenerUrlBase() throws Exception {
		String url = System.getProperty("es.caib.rolsac.portal.url");
		if(null==url) {
			String msg = "falta definir la propietat: es.caib.rolsac.portal.url";
			log.error(msg);
			throw new Exception(msg);
		}
		return url;
	}

}
