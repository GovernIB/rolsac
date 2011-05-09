package org.ibit.rol.sac.back.indra.actions;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BackRequestAction extends Action {

    protected static Log log = LogFactory.getLog(BackRequestAction.class);

    public ActionForward execute(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response)
            throws Exception {

        String lastPath = null;
        
        StaticBackAction sba = StaticBackAction.getInstance();
        Vector vect = sba.getCollection();
        RingBuffer buffer=null;
        //RingBuffer buffer=(RingBuffer)request.getSession().getAttribute("es.indra.struts.back.RingBuffer");
        																
        if (vect.size()>0) {
        	
        	//buffer = (RingBuffer)vect.get(0);
        	buffer=(RingBuffer)request.getSession().getAttribute("es.indra.struts.back.RingBuffer");
        	
        	if (buffer!=null) {
	        	buffer.print();
	        	
		        ArrayEntry entry = buffer.getLastPushed();
	
		        lastPath = (String) entry.getValue();
		        
		        if (lastPath != null) {
		            buffer.setNoPush(true);
		            buffer.setWereInvolved(true);
		        }
        	} else {
        		log.warn("No hay url para realizar el back desde microsites.");
        		lastPath="/main.do";//home del backoffice por defecto
        	}
        } else {
        	lastPath="/main.do";//home del backoffice por defecto
        }	
        	
        //request.getSession().setAttribute("es.indra.struts.back.RingBuffer", buffer);
        return new ActionForward(lastPath, true);
    }


}
