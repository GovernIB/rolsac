package org.ibit.rol.sac.back.subscripcions.action.buscaordena;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SuscriptorDelegate;
import org.ibit.rol.sac.back.subscripcions.actionform.busca.BuscaOrdenaSuscriptorActionForm;


public class buscaordenaSuscriptoresAction extends Action {

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

    protected static Log log = LogFactory.getLog(org.ibit.rol.sac.back.subscripcions.action.buscaordena.buscaordenaSuscriptoresAction.class);
     
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    	
    	String filtroEstado = "ALL";
    	String filtroGrupo = "-1";
    	
        // Revisamos el filtro de estado elegido, inicio de la consulta
        if (request.getParameter("filtroEstado")!=null)
        	filtroEstado=request.getParameter("filtroEstado");

        // Revisamos el filtro de estado elegido, inicio de la consulta
        if (request.getParameter("filtroGrupo")!=null)
        	filtroGrupo=request.getParameter("filtroGrupo");
        	//filtroEstado=request.getParameter("filtroGrupo");
        
        
    	SuscriptorDelegate delegate = DelegateUtil.getSuscriptorDelegate();
    	delegate.init(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId(), filtroEstado, filtroGrupo);

    	
        //Podemos recibir datos de filtro u ordenación del listado
        BuscaOrdenaSuscriptorActionForm f = (BuscaOrdenaSuscriptorActionForm) form;
        
        
        if (f.getFiltro()!= null && f.getFiltro().length()>0)
        	delegate.setFiltro(f.getFiltro());
    
        if (f.getOrdenacion()!= null && f.getOrdenacion().length()>0)
        	delegate.setOrderby(f.getOrdenacion());

        // Indicamos la página a visualizar
        if (request.getParameter("pagina")!=null)
        	delegate.setPagina(Integer.parseInt(request.getParameter("pagina")));
        else
            delegate.setPagina(1);
        
        List lista=delegate.listar();
        request.setAttribute("parametros_pagina",delegate.getParametros());
        
        if (lista.size()!=0) // Si hay algún registro
            request.setAttribute("listado",lista);
        else  // Si no hay registros limpiamos el filtro
            {f.setFiltro("");
             f.setFiltroEstado("ALL");
             f.setFiltroGrupo("-1");
            }
            

        return mapping.findForward("listarSuscriptores");
        
    }


    

    
}

