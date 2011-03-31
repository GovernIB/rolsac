package org.ibit.rol.sac.back.subscripcions.action.edita;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.TraDynaActionForm;
import org.ibit.rol.sac.back.subscripcions.utils.VOUtils;
import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.model.TraduccionGrupoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;



/**
 * Acción que se ejecuta desde el formulario de detalle
 * 
 * Autor: Fausto Navarro
 * Copyright INDRA 2006
 * 
 */
public class gruposEditaAction extends BaseAction 
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
	
	protected static Log log = LogFactory.getLog(gruposEditaAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GrupoSuscripcionDelegate delegate = DelegateUtil.getGrupoSuscripcionDelegate();
		GrupoSuscripcion grupo = null;
		TraDynaActionForm f = (TraDynaActionForm) form;
		
		TipoSuscripcion tipo = (TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion");
		
		String pModifica="" + request.getParameter("modifica");
		String pAnyade="" + request.getParameter("anyade");
		
		if ( (!pModifica.equals("null")) || (!pAnyade.equals("null")) ) { 
			
			if (f.get("id") == null) {
				grupo = new GrupoSuscripcion();
				grupo.setTipoSuscripcion(tipo);
			} else {  // Es modificacion
				grupo = delegate.obtenerGrupo((Long)f.get("id"));
				//************COMPROBACION DE IDES*************
				/*if (grupo.getTipoSuscripcion().getId().longValue()!= tipo.getId().longValue())
				 {
				 addMessage(request, "peticion.error");
				 return mapping.findForward("info");
				 }
				 request.setAttribute("identificador",((TraduccionGrupoSuscripcion)grupo.getTraduccion()).getNombre());
				 */
				//*********************************************
			}
			
			VOUtils.populate(grupo, f);
			
			grupo.setTipoSuscripcion(tipo);//getTipoSuscripcion().setId(tipo.getId());
			grupo.setIdentificador((String) f.get("identificador"));
			
			delegate.grabar(grupo);	
			if(request.getParameter("anyade")!=null) 
				addMessage(request, "mensa.nuevogrupo");
			if(request.getParameter("modifica")!=null)	
				addMessage(request, "mensa.modifgrupo");	
			
			addMessage(request, "mensa.editargrupo", "" + grupo.getId().longValue());
			addMessage(request, "mensa.listagrupo");
			
			return mapping.findForward("info");
			
		}
		
		//********************************************************
		//********************** EDITAMOS ************************
		//********************************************************
		if (request.getParameter("id")!=null) {     
			Long id = new Long(""+request.getParameter("id"));
			
			if (id != null){
				
				grupo = delegate.obtenerGrupo(id);
				//************COMPROBACION DE IDES*************
				if (grupo.getTipoSuscripcion().getId().longValue()!=((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId().longValue())
				{
					addMessage(request, "peticion.error");
					return mapping.findForward("info");
				}
				
				//*********************************************
				f.set("identificador", (String)grupo.getIdentificador());
				List tradform = (List) f.get("traducciones");
				List langs = DelegateUtil.getIdiomaDelegate().listarLenguajes();
				for (int i = 0; i < langs.size(); i++) {
					TraduccionGrupoSuscripcion trad = (TraduccionGrupoSuscripcion)grupo.getTraduccion(""+langs.get(i));
					TraduccionGrupoSuscripcion grupotrad= (TraduccionGrupoSuscripcion)tradform.get(i);
					if (trad != null) {
						grupotrad.setNombre(trad.getNombre());
						tradform.set(i, grupotrad);
					}
				}
				return mapping.findForward("detalle");
				
				
			} else {
				addMessage(request, "info.noposible");
				return mapping.findForward("info");
			}
			
			
			
		}
		return mapping.findForward("info");
	}
    
}

