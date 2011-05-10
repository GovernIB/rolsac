/**
 * User: jhorrach
 * Date: Mar 31, 2004
 * Time: 11:21:38 AM
 */
package org.ibit.rol.sac.back.controller;

import org.apache.struts.tiles.Controller;
import org.apache.struts.tiles.ComponentContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.model.Edificio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Controller para generar una lista de fichas
 */
public class ListaCoordenadasController implements Controller{
      protected static Log log = LogFactory.getLog(ListaCoordenadasController.class);

    public void perform(ComponentContext componentContext, HttpServletRequest request,
                        HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {

        try {
            Edificio edificio;
            Long idEdificio;
            try {
            	String idArg = request.getParameter("idEdificio");
                idEdificio = Long.valueOf(idArg);            	
            } catch (Exception e) {
                idEdificio = null;
            }
            
            if (idEdificio != null) {
            	
            	EdificioDelegate delegate = DelegateUtil.getEdificioDelegate();
                edificio = delegate.obtenerEdificio(idEdificio);
        
                String lsDir = ""+ edificio.getDireccion();
                lsDir = lsDir.replaceAll("nº","");
                lsDir = lsDir.replaceAll(",","");
                String lsCP = ""+ edificio.getCodigoPostal();
                if (lsCP.equals("null")) lsCP= "";
                String lsPbl = ""+ edificio.getPoblacion();
                if (lsPbl.equals("null")) lsPbl= "";                
                if (edificio.getDireccion()!= null) request.setAttribute("dirEdi", lsDir + " , " + lsCP + " , " + lsPbl + " , " + "España");
 
            	request.setAttribute("latEdi", edificio.getLatitud());
        		request.setAttribute("lngEdi", edificio.getLongitud());

            }
        	if (request.getAttribute("latEdi") == null || request.getAttribute("lngEdi") == null)
        	{
            	request.setAttribute("latEdi", "39.5690036");
            	request.setAttribute("lngEdi", "2.6436571");
        	}            
            if (request.getAttribute("dirEdi") == null) request.setAttribute("dirEdi", "");

            String servidor="";
            String value = System.getProperty("es.indra.caib.rolsac.oficina");
            ResourceBundle rb =	ResourceBundle.getBundle("sac-back-messages");
            
            if ((value == null) || value.equals("N"))
            	servidor = System.getProperty("es.caib.rolsac.portal.url");
            else
            	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
        	request.setAttribute("host", servidor);
            String googleMapKey =System.getProperty("es.indra.caib.rolsac.googleMapKey");          
            if (googleMapKey != null){
        	    request.setAttribute("googleMapKey",googleMapKey);
            }
                        
        } catch (DelegateException e) {
            throw new ServletException(e);
        }

    }
}