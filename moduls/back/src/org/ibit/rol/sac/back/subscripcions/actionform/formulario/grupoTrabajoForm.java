package org.ibit.rol.sac.back.subscripcions.actionform.formulario;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

public class grupoTrabajoForm extends DynaActionForm  {

    protected static Log log = LogFactory.getLog(grupoTrabajoForm.class);

    public void initialize(ActionMapping actionMapping) {
        super.initialize(actionMapping);
    }

    public void reset(ActionMapping mapping, HttpServletRequest request){
        super.reset(mapping, request);

        set("id", new Long(0));
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {

    	ActionErrors errors = new ActionErrors();
    	/*
    	if(httpServletRequest.getParameter("modificalinea")!=null || httpServletRequest.getParameter("anyadelinea")!=null) {
        
    		if (!TraDynaActionForm.esEntero(""+get("orden")) )
            	errors.add("orden", new ActionError("error.lineaformu.orden"));
    		
    		if (!TraDynaActionForm.esEntero(""+get("tamano")) )
            	errors.add("tamano", new ActionError("error.lineaformu.tamano"));

    		if (!TraDynaActionForm.esEntero(""+get("lineas")) )
            	errors.add("lineas", new ActionError("error.lineaformu.lineas"));

    		String[] etis=(String[])get("etiq");
    		if ( etis[0].length()==0)
    			errors.add("etiq", new ActionError("error.lineaformu.etiq"));

    	}

*/
    	return errors;

    }
    
}
