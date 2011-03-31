package org.ibit.rol.sac.back.subscripcions.action.lista;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.TraDynaActionForm;
import org.ibit.rol.sac.back.subscripcions.actionform.listaActionForm;

public class listaGruposAction extends BaseAction {

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

	protected static Log log = LogFactory.getLog(listaGruposAction.class);
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {

        listaActionForm f = (listaActionForm) form;
        
        //********************************************************
        //************* ERROR DE VALIDACION **********************
        //********************************************************
        if (request.getSession().getAttribute("grupoForm")!=null && request.getAttribute(Globals.ERROR_KEY)!=null) {
        	TraDynaActionForm fdet=(TraDynaActionForm) request.getSession().getAttribute("grupoForm");
        	request.setAttribute("grupoForm", fdet);
            return mapping.findForward("detallegrupo");
        }
        //********************************************************
        //********************** CREAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("crear")) {
        	request.getSession().removeAttribute("grupoForm");
        	return mapping.findForward("detalleGrupo");
        }
        //********************************************************
        //********************* BORRAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("borrar")) {
            Long id =null;
            GrupoSuscripcionDelegate bdgrupo = DelegateUtil.getGrupoSuscripcionDelegate();
            
            String lis="";
            for (int i=0;i<f.getSeleccionados().length;i++) {
                id = new Long(f.getSeleccionados()[i]);
                bdgrupo.borrarGrupo(id);
                lis+=id+", ";
            }
            lis=lis.substring(0,lis.length()-2);
            
            addMessage(request, "mensa.listagrupo");
            addMessage(request, "mensa.listatipborradas", new String(lis));
            
            return mapping.findForward("info");
        }

        addMessage(request, "peticion.error");
        return mapping.findForward("info");

    }

}