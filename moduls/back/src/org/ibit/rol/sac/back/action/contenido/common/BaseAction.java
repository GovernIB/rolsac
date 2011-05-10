package org.ibit.rol.sac.back.action.contenido.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BaseAction {

	public BaseAction() {
		super();
	}

	protected ActionForward forwardOK(ActionMapping mapping) {
		return mapping.findForward("success");
	}

	protected ActionForward forwardFallo(ActionMapping mapping) {
		return mapping.findForward("fail");
	}

	public ActionForward forwardError(ActionParameters params) {
		return params.mapping.findForward("cancel");
	}
	
	protected String establecerAtributoServidor(HttpServletRequest request) {
		String servidor;
		String value = System.getProperty("es.indra.caib.rolsac.oficina");
		if ((value == null) || value.equals("N"))
	    	servidor=System.getProperty("es.caib.rolsac.portal.url");
	    else //INDRA
	    	servidor = "http://"+request.getServerName()+":"+request.getServerPort();
		return servidor;
	}


	protected ActionForward forwardCancel(ActionParameters params) {
		return params.mapping.findForward("cancel");
	}

}