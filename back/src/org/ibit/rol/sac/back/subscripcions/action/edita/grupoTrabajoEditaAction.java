package org.ibit.rol.sac.back.subscripcions.action.edita;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionFormClass;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.ModuleConfig;
import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EnvioSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.formulario.grupoTrabajoForm;



/**
 * Acción que se ejecuta desde el formulario de detalle
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2007
 * 
 */
public class grupoTrabajoEditaAction extends BaseAction 
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
	
	protected static Log log = LogFactory.getLog(grupoTrabajoEditaAction.class);
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	grupoTrabajoForm f = (grupoTrabajoForm) form;
    	EnvioSuscripcionDelegate delegate = DelegateUtil.getEnvioSuscripcionDelegate();
    	Long idTrabajo = (Long)f.get("idtrabajo");
    	EnvioSuscripcion envio = delegate.obtenerEnvio(idTrabajo);

        //********************************************************
        //******** INSERTAMOS O MODIFICAMOS LA LINEA *************
        //********************************************************
        
        if (request.getParameter("anyadegrupo")!=null) {
        	
        	Long idGrupo = (Long) f.get("id");
        	Set grupos = envio.getGrupos();
        	GrupoSuscripcion grupo = null;
        	for(Iterator it= grupos.iterator(); it.hasNext();)
        	{
            	grupo = (GrupoSuscripcion) it.next();
            	if(grupo.getId().longValue() == idGrupo.longValue())
            	{
                    addMessage(request, "peticion.grupo.repetido");
           			addMessage(request, "mensa.editartrabajo", ""+f.get("idtrabajo"));
                    return mapping.findForward("info");
            	}
        	}
        	GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
        	grupo = grupoDelegate.obtenerGrupo(idGrupo);
        	envio.getGrupos().add(grupo);
        	delegate.grabarEnvio(envio);
        	
        	if (request.getParameter("anyadegrupo")!=null) {
        		addMessage(request, "mensa.grupocreado");
       			addMessage(request, "mensa.editartrabajo", ""+f.get("idtrabajo"));
       			return mapping.findForward("info");
        	}
        	else {
        		return new ActionForward("/trabajoEdita.do?id=" + f.get("idtrabajo"));	
        	}
        	
    	}

    	
        //********************************************************
        //***************** NUEVA LINEA **************************
        //********************************************************
    	if (request.getParameter("idtrabajo")!=null) {
    		// Inicializamos el form
    		ModuleConfig module = mapping.getModuleConfig();
    		FormBeanConfig formBeanConfig = module.findFormBeanConfig("grupoTrabajoForm");
    		DynaActionFormClass dynaClass = DynaActionFormClass.createDynaActionFormClass(formBeanConfig);
    		grupoTrabajoForm fdet = (grupoTrabajoForm) dynaClass.newInstance();
    		
    		fdet.set("idtrabajo", new Long(""+request.getParameter("idtrabajo")));
        	request.setAttribute("grupoTrabajoForm", fdet);
        	
        	GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
        	
			request.setAttribute("grupos",grupoDelegate.listarCombo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId()));

    		
        	return mapping.findForward("detalle");
    	}
    	
         
        addMessage(request, "peticion.error");
        return mapping.findForward("info");
    }
}
