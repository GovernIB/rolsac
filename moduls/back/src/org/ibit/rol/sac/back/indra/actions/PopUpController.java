package org.ibit.rol.sac.back.indra.actions;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
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

public class PopUpController extends org.ibit.rol.sac.back.controller.NormativaController {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

    	super.perform(tilesContext, request, response, servletContext);
    	
    	/*
        try{
    		//MODIFICADO INDRA (AITOR TOMAS PIN)
            if ("Micro".equals(request.getParameter("opcion"))){
            	
            	//recoger usuario..... 
            	UsuarioDelegate usudel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
            	Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());


                
                MicrositeDelegate delegate = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
                Map propi2 = new HashMap();
            	//propi2.put("traduccion.titulo","%"+request.getParameter("filtro")+"%");
                propi2.put("visible","%S%");
                request.setAttribute("elementOptions", delegate.listarMicrositesFiltro(usu, propi2));
                request.setAttribute("opcion", "Micro");
            }


        } catch (Exception e) {
            throw new ServletException(e);
        }
		*/
    }

}
