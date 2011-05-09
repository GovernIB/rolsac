package org.ibit.rol.sac.back.indra.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.tiles.TilesRequestProcessor;
import org.ibit.rol.sac.back.action.CustomRequestProcessor;

public class BackRequestProcessor extends CustomRequestProcessor {

    protected static Log log = LogFactory.getLog(BackRequestProcessor.class);

    public void init(ActionServlet servlet, ModuleConfig moduleConfig)
        throws ServletException {

        super.init(servlet, moduleConfig);
    }
    
    public void setMarker(RingBuffer buffer, HttpServletRequest request) {
        if (buffer.isForwardPossible()) {
            request.setAttribute("es.indra.struts.isForward", "true"); 
        } else {
            request.setAttribute("es.indra.struts.isForward", "false"); 
        }

        if (buffer.isBackPossible()) {
            request.setAttribute("es.indra.struts.isBack", "true"); 
        } else {
            request.setAttribute("es.indra.struts.isBack", "false"); 
        }
        
    }
    protected ActionForward processActionPerform(HttpServletRequest request,
        HttpServletResponse response, Action action, ActionForm form,
        ActionMapping mapping) throws IOException, ServletException {

        try {
            ActionForward forward = (action.execute(mapping, form, request,
                response));

            RingBuffer buffer = (RingBuffer) request.getSession().getAttribute(
                "es.indra.struts.back.RingBuffer");

            if (buffer == null) {
            	//quiere decir que hay sesion nueva
            	
            	//comprobar que el vector no pase del limite maximo
                StaticBackAction sba = StaticBackAction.getInstance();
                Vector vect = sba.getCollection(); 
                if (vect.size()>sba.MAX_COLLECTION) vect.remove(0);
            	
            	
                buffer = new RingBuffer(100);
                request.getSession().setAttribute(
                    "es.indra.struts.back.RingBuffer", buffer);
                sba.addElementToCollection(buffer);
            }

            String query = calculateQueryString(request);
            
            //gestion de urls excepcion las cuales no guardamos
            String[] excepciones = {"layout","poparbol","popup","grafico","calendario",request.getContextPath()+"/sistema/seccion/arbol.do"};
            boolean guarda = true;
            for(int i=0; i<excepciones.length;i++)
            {
            	if (query.indexOf(excepciones[i])!=-1) {guarda=false;break;}
            }
            
            if ((query != null) && (query.indexOf(".do") != -1) && (guarda)) {
                
                if (buffer.isNoPush() == true) {
                    // returns
                    buffer.setNoPush(false);
                    setMarker(buffer, request);
                    return forward;
                }
                if (buffer.isWereInvolved() == true) {
                    // returns
                    buffer.setWereInvolved(false);
                    setMarker(buffer, request);
                    return forward;
                }
                ArrayEntry entry = new ArrayEntry(query);
                buffer.push(entry);
                setMarker(buffer, request);
            }

            return forward;

        } catch (Exception e) {
            return (processException(request, response, e, form, mapping));
        }

    }

    private String calculateQueryString(HttpServletRequest request) {

        String sourceURL = null;

        // The alternative Methode request.getRequestURL() has the side-effect
        // to do an endless loop, so we must stay by this deprecated method.
        StringBuffer url = javax.servlet.http.HttpUtils.getRequestURL(request);

        String parametros = "";
        Enumeration enumera = request.getParameterNames();
        boolean mas = enumera.hasMoreElements();
        if (mas) parametros="?";
        while (mas)
        {
        	String name = (String)enumera.nextElement();
            String valor = request.getParameter(name);
            parametros+=(name.equals("operacion")?"cancela":name+"="+valor);
            mas = enumera.hasMoreElements();
            if (mas) parametros+="&";
        }
        
        url.append(parametros);

        sourceURL = url.toString();
        return sourceURL;
    }
}
