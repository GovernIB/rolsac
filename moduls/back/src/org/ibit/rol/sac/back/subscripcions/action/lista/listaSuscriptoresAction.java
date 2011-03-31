package org.ibit.rol.sac.back.subscripcions.action.lista;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.ibit.rol.sac.persistence.delegate.GrupoSuscripcionDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorClaveDelegate;
import org.ibit.rol.sac.persistence.delegate.SuscriptorDelegate;
import org.ibit.rol.sac.back.subscripcions.Suscripcionback;
import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.back.subscripcions.action.BaseAction;
import org.ibit.rol.sac.back.subscripcions.actionform.listaActionForm;
import org.ibit.rol.sac.back.subscripcions.actionform.formulario.suscriptorForm;

public class listaSuscriptoresAction extends BaseAction {

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

	protected static Log log = LogFactory.getLog(listaSuscriptoresAction.class);
	
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {

        listaActionForm f = (listaActionForm) form;
        
        request.getSession().removeAttribute("inicioDefecto");
        
        //********************************************************
        //************* ERROR DE VALIDACION **********************
        //********************************************************
        if (request.getSession().getAttribute("suscriptorForm")!=null && request.getAttribute(Globals.ERROR_KEY)!=null) {
        	suscriptorForm fdet=(suscriptorForm) request.getSession().getAttribute("suscriptorForm");        	
        	request.setAttribute("suscriptorForm", fdet);
        	//request.setAttribute("canales",Suscripcionback.CANALES);
        	//request.setAttribute("estados",Suscripcionback.ESTADOS_ENVIO);
        	//request.setAttribute("tipos",Suscripcionback.TIPO_ENVIO);
        	
            return mapping.findForward("detallesuscriptor");
        }
        //********************************************************
        //********************** CREAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("crear")) {
        	
        	//request.setAttribute("grupos",Suscripcionback.CANALES);
        	GrupoSuscripcionDelegate grupoDelegate = DelegateUtil.getGrupoSuscripcionDelegate();
        	request.setAttribute("grupos",grupoDelegate.listarCombo(((TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion")).getId()));
        	
        	
			request.setAttribute("estados",Suscripcionback.ESTADOS_SUSCRIPCION);
			request.setAttribute("tipos",Suscripcionback.TIPO_ENTIDAD);
			
			
			
			request.setAttribute("estado",Suscriptor.TIPO_ACTIVO);
			
			request.setAttribute("grupo",new Integer(0));
			request.setAttribute("origen",Suscriptor.ORIGEN_MANUAL);
			
			
			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    		//request.setAttribute("fechaAlta",df.format(new Date()));
			
			
			request.getSession().removeAttribute("suscriptorForm");
			request.setAttribute("grupo",new Integer(0));
			
			request.getSession().setAttribute("inicioDefecto","1");
			
        	return mapping.findForward("detalleSuscriptor");
        }
        //********************************************************
        //********************* BORRAMOS *************************
        //********************************************************
        if ( f.getAccion().equals("borrar")) {
            Long id =null;
            
            SuscriptorDelegate bdsuscriptor = DelegateUtil.getSuscriptorDelegate();

            SuscriptorClaveDelegate bdsuscriptorClave = DelegateUtil.getSuscriptorClaveDelegate();
            
            String lis="";
            for (int i=0;i<f.getSeleccionados().length;i++) {
                id = new Long(f.getSeleccionados()[i]);
                String mail = bdsuscriptor.obtenerSuscriptor(id).getEmail();
                	 //Intento borrar la clave, 
                	String estado = bdsuscriptor.obtenerSuscriptor(id).getEstado(); 
                	if ( !estado.equalsIgnoreCase("B")){
                		bdsuscriptor.borrarSuscriptor(id);
                		TipoSuscripcion tipo = (TipoSuscripcion)request.getSession().getAttribute("MVS_suscripcion");
                   		bdsuscriptorClave.borrarSuscriptorClave(bdsuscriptorClave.recuperarSuscriptorClave(tipo.getId(), mail));                	}
                lis+=id+", ";
            }
            lis=lis.substring(0,lis.length()-2);
            
            addMessage(request, "mensa.listasuscriptor");
            addMessage(request, "mensa.listasusborradas", new String(lis));
            
            return mapping.findForward("info");
        }

        addMessage(request, "peticion.error");
        return mapping.findForward("info");

    }

}