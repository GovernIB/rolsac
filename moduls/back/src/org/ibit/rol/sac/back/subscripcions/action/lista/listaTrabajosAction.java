package org.ibit.rol.sac.back.subscripcions.action.lista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnvioSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.listaActionForm;
import org.ibit.rol.sac.back.subscripcions.actionform.formulario.trabajoForm;

public class listaTrabajosAction extends BaseAction {

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

	protected static Log log = LogFactory.getLog(listaTrabajosAction.class);
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {

        listaActionForm f = (listaActionForm) form;
        
		GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
		
		TipoSuscripcion ts = (TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion");
		
		request.setAttribute("grupos",grupoDelegate.listarCombo(ts.getId()));

    	
 
        //********************************************************
        //************* ERROR DE VALIDACION **********************
        //********************************************************
        if (request.getSession().getAttribute("trabajoForm")!=null && request.getAttribute(Globals.ERROR_KEY)!=null) {
        	trabajoForm fdet=(trabajoForm) request.getSession().getAttribute("trabajoForm");
        	request.setAttribute("trabajoForm", fdet);
			request.setAttribute("canales",Suscripcionback.CANALES);
			request.setAttribute("estados",Suscripcionback.ESTADOS_ENVIO);
			request.setAttribute("tipos",Suscripcionback.TIPO_ENVIO);
        	/*
            // Relleno el combo de Actividades
            ActividadDelegate bdActivi = DelegateUtil.getActividadagendaDelegate();
            request.setAttribute("actividadesCombo", bdActivi.listarCombo(((Microsite)request.getSession().getAttribute("MVS_site")).getId()));
            */
            return mapping.findForward("detalleTrabajo");
        }
        //********************************************************
        //********************** CREAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("crear")) {
			request.setAttribute("canales",Suscripcionback.CANALES);
			request.setAttribute("estados",Suscripcionback.ESTADOS_ENVIO);
			request.setAttribute("tipos",Suscripcionback.TIPO_ENVIO);

        	/*
            // Relleno el combo de Actividades
            ActividadDelegate bdActivi = DelegateUtil.getActividadagendaDelegate();
            request.setAttribute("actividadesCombo", bdActivi.listarCombo(((Microsite)request.getSession().getAttribute("MVS_site")).getId()));
            */
        	request.getSession().removeAttribute("trabajoForm");
        	return mapping.findForward("detalleTrabajo");
        }
        //********************************************************
        //********************* BORRAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("borrar")) {
            Long id =null;
            EnvioSuscripcionDelegate envioDelegate = DelegateUtil.getEnvioSuscripcionDelegate();
            
            String lis="";
            for (int i=0;i<f.getSeleccionados().length;i++) {
                id = new Long(f.getSeleccionados()[i]);
                envioDelegate.borrarEnvio(id);
                lis+=id+", ";
            }
            lis=lis.substring(0,lis.length()-2);
            
            
            addMessage(request, "mensa.listatrabajo");
            addMessage(request, "mensa.listatrabajosborrados", new String(lis));

            return mapping.findForward("info");
        }

        addMessage(request, "peticion.error");
        return mapping.findForward("info");

    }

}