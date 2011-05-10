package org.ibit.rol.sac.back.subscripcions.action.edita;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.TraduccionSeccion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnvioSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;

/**
 * Acción que se ejecuta desde el formulario de detalle
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2006
 * 
 */
public class visualizaTrabajoAction extends BaseAction 
{
    /**
     * This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @return 
     */
	
	protected static Log log = LogFactory.getLog(visualizaTrabajoAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EnvioSuscripcionDelegate delegate = DelegateUtil.getEnvioSuscripcionDelegate();
		EnvioSuscripcion envio = null;
		
		if(request.getParameter("id")!=null)
		{
			Long id = new Long(""+request.getParameter("id"));
			envio = delegate.obtenerEnvio(id);
			
			request.setAttribute("contenidoHtml", envio.getContenidoHtml());
			request.setAttribute("titulo", envio.getAsunto());
			return mapping.findForward("visualizaTrabajo");
		}
		
		Long idSeccion = new Long(""+request.getParameter("idSeccion"));
		String titulo = request.getParameter("titulo");

		SeccionDelegate secDel = org.ibit.rol.sac.persistence.delegate.DelegateUtil.getSeccionDelegate();
		Seccion seccion = secDel.obtenerSeccion(idSeccion);
		TraduccionSeccion ts = (TraduccionSeccion) seccion.getTraduccion();
		request.setAttribute("seccion",ts.getNombre());
		request.setAttribute("titulo", titulo);

		
		return mapping.findForward("visualizaTrabajo");
		
	}
}

