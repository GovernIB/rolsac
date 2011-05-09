package org.ibit.rol.sac.back.subscripcions.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.subscripcions.base.Base;


public class HomeAction extends BaseAction  {


	protected static Log log = LogFactory.getLog(HomeAction.class);
	
		
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {

		
	   	
    	/***************************************************************/
    	/***************   RECOGER USUARIO Y ROLES   *******************/
    	/***************************************************************/    	
    	Base.usuarioRefresh(request);
    	String sysOradmin = (String)request.getSession().getAttribute("MVS_rol_sys_adm");
    	
    	
    	
        
        
        
        /***************************************************************/
    	/********************         ALTA         *********************/
    	/***************************************************************/        
        String alta=""+request.getParameter("accion");
        if ( (alta.equals("alta")) &&  (sysOradmin.equals("yes")) )  {
        	Base.borrarVSession(request);
        	request.setAttribute("MVS_pagina_inicio", "tipoSuscripcionEdita.do?accion=alta");
        	return mapping.findForward("inicio");
        }

        
    	/***************************************************************/
    	/********************     ELIMINAR         *********************/
    	/***************************************************************/
        String eliminar=""+request.getParameter("accion");
        if ( (eliminar.equals("eliminar")) &&  (sysOradmin.equals("yes")) )  {
            Long idtipo = new Long(0);
            idtipo = new Long(""+request.getParameter("idtipo"));        	
        	Base.borrarVSession(request);
        	request.setAttribute("MVS_pagina_inicio", "eliminartipo.do?idtipo="+idtipo);
        	return mapping.findForward("inicio");
        }    	
        	

            
        
        
        /***************************************************************/
    	/******************  MODIFICACION/GESTION   ********************/
    	/***************************************************************/          
		Long idtipo= new Long(0);
		try {
			idtipo = new Long(""+request.getParameter("idtipo"));
			if (Base.hasTipoSuscripcionPermiso(request, idtipo)) {
				Base.suscripcionRefresh(idtipo,request);
				//Base.menuRefresh(request);
			} else {
				Base.borrarVSession(request);
				log.warn("[AVISO] Intento de acceso al tipo de suscripcion id=" + idtipo + " sin los permisos necesarios");
				addMessage(request, "peticion.error");
				request.setAttribute("MVS_index_con_info", "yes");
			}
		} catch (Exception e) {
			Base.borrarVSession(request);
			log.error("Se ha producido un error accediendo al tipo de suscripcion : " + idtipo + "\n error=" + e.getMessage());
			addMessage(request, "peticion.error");
			request.setAttribute("MVS_index_con_info", "yes");
		}
		
		
		return mapping.findForward("inicio");
		
    }
	
	
}
