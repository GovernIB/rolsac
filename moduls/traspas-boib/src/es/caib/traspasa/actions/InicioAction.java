package es.caib.traspasa.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.tiles.ComponentContext;
import org.apache.struts.tiles.Controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import es.caib.traspasa.bean.TrNormativaLocalBean;

public class InicioAction implements Controller {


    public void perform(ComponentContext componentContext, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
    	request.setAttribute("APPTRS_normativa", new TrNormativaLocalBean());
    }
 
}