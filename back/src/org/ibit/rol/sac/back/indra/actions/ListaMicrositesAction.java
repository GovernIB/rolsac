/**
 * Aitor Tomas Pin
 * Indra
 * 29/11/2006
 */

package org.ibit.rol.sac.back.indra.actions;

import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.ibit.rol.sac.micromodel.Microsite;
import org.ibit.rol.sac.micropersistence.delegate.DelegateUtil;
import org.ibit.rol.sac.micropersistence.delegate.DelegateException;
import org.ibit.rol.sac.micropersistence.delegate.MicrositeDelegate;
import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Controller para obtener una lista de Microsites
 */
public class ListaMicrositesAction implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

    	/*
        try {
        	
        	
        	//obtener usuario
        	UsuarioDelegate usudel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
        	Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());
            
        	
            MicrositeDelegate micro = DelegateUtil.getMicrositeDelegate();
            
            //List micros = micro.listarMicrositesThin();
            List micros = micro.listarMicrositesbyUser(usu);
            ArrayList mics= new ArrayList ();
        
            //saco los nombres de uas para cada microsite
            Iterator iter = micros.iterator();
        	while (iter.hasNext()) {
        		Microsite mic = (Microsite) iter.next();
        		UnidadAdministrativaDelegate uniad=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUADelegate();
            	UnidadAdministrativa uo = null;
            	try {
            		uo = uniad.obtenerUnidadAdministrativa(new Long(mic.getUnidadAdministrativa()));
            	}catch (Exception e) {
                }
            	mic.setNombreUA(((TraduccionUA)uo.getTraduccion(Idioma.DEFAULT)).getNombre());
            	if (usu.hasAccess(uo)) mics.add(mic);
        	} 
        	
        	request.setAttribute("micrositeOptions", mics);
        } catch (Exception e) {
            throw new ServletException(e);
        }
		*/
    }


}