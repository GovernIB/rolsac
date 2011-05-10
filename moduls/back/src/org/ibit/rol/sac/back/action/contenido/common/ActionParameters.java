package org.ibit.rol.sac.back.action.contenido.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ActionParameters {
	public ActionMapping mapping;
	public ActionForm form;
	public HttpServletRequest request;
	public HttpServletResponse response;

	public ActionParameters(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		this.mapping = mapping;
		this.form = form;
		this.request = request;
		this.response = response;
	}

	public String getParameter(String name) {
		return request.getParameter(name);
	}

	public void setAttribute(String name, Object value) {
		request.setAttribute(name, value);
	}

	 
	
}