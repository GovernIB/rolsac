package org.ibit.rol.sac.back.indra.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.micromodel.Microsite;
import org.ibit.rol.sac.micropersistence.delegate.MicrositeDelegate;
import org.ibit.rol.sac.micropersistence.delegate.DelegateException;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;

/**
 * Controller que saca los microsites asociados a una uo.
 */
public class ListaMicrositesUAAction extends org.ibit.rol.sac.back.controller.UAController  {

    public void perform(ComponentContext tilesContext,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {

        super.perform(tilesContext, request, response, servletContext);

        try {
        	
        	
        	String value = System.getProperty("es.caib.rolsac.microsites");
        	
        	if ((value == null) || value.equals("N")) {
        		
        		request.setAttribute("micrositeOptions", Collections.EMPTY_LIST);
        		
        	} else {
	        	String ident = request.getParameter("idUA");
	        	
	        	//recoger usuario 
	        	//UsuarioDelegate usudel=org.ibit.rol.sac.persistence.delegate.DelegateUtil.getUsuarioDelegate();
	        	//Usuario usu = usudel.obtenerUsuariobyUsername(request.getRemoteUser());

	            if(ident != null){
                    Long id = new Long(ident);
                    request.setAttribute("micrositeOptions", obtenirMicrosites(id));
	            } else {
	                request.setAttribute("micrositeOptions", Collections.EMPTY_LIST);
	            }
        	}
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private List obtenirMicrosites(Long id) throws DelegateException {

        MicrositeDelegate micro = org.ibit.rol.sac.micropersistence.delegate.DelegateUtil.getMicrositeDelegate();
        //List micros = micro.listarMicrositesSin(usu);
        List micros = micro.listarMicrositesThin();
        List mics= new ArrayList();

        Iterator iter = micros.iterator();
        while (iter.hasNext()) {
            Microsite mic = (Microsite) iter.next();
            if (mic.getUnidadAdministrativa()==id.intValue()) mics.add(mic);
        }
        return mics;
    }

}