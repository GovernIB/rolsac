/**
 * User: jpernia
 * Date: Mar 17, 2004
 * Time: 12:44:04 PM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;
import org.ibit.rol.sac.model.Usuario;

/*
import es.caib.portal.indra.action.ProcPerTemaAction.ProcedimientoComparator;
import es.caib.sac.actuacio.model.ActuacioMinModel;
*/

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Controller para generar una lista de usuarios
 */
public class ListaUsuarioController implements Controller{

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
        	ArrayList listatodos = new ArrayList();
            UsuarioDelegate delegate = DelegateUtil.getUsuarioDelegate();

            ArrayList listaresultante;
            if (request.isUserInRole("sacsystem")) {
            	Collection lista = delegate.listarUsuariosPerfil("sacoper");
            	lista = delegate.listarUsuariosPerfil("sacsuper");
            	listatodos.addAll(lista);
            	lista = delegate.listarUsuariosPerfil("sacadmin");
            	listatodos.addAll(lista);
            	
        		listaresultante = new ArrayList(listatodos);
        		Comparator comp = new ProcedimientoComparator();
        	  	Collections.sort(listaresultante, comp);
              
                request.setAttribute("usuarioOptions", listaresultante);
            }

            
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
	  private static class ProcedimientoComparator implements Comparator {
		    public int compare(Object element1, Object element2) {
		    	
		    	String nom1 = ( ((Usuario)element1).getNombre()!=null )?((Usuario)element1).getNombre():"";
		    	String nom2 = ( ((Usuario)element2).getNombre()!=null )?((Usuario)element2).getNombre():"";
	    	
		    	return nom1.toLowerCase().compareTo(nom2.toLowerCase());
		    }
	  }	 	
}