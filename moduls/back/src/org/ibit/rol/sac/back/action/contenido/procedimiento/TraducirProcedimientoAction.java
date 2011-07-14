package org.ibit.rol.sac.back.action.contenido.procedimiento;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;

import es.indra.rol.sac.integracion.traductor.Traductor;

public class TraducirProcedimientoAction extends GuardarProcedimientoAction{

	protected static Log log = LogFactory.getLog(TraducirProcedimientoAction.class);

	public ActionForward traducir(ActionParameters actionParameters) throws Exception {
		log.debug("Entramos en traducir");

		ProcedimientoForm dForm = (ProcedimientoForm) actionParameters.form;

		traducir (actionParameters.request, dForm);
		
		ProcedimientoLocal procedimiento = crearProcedimientoRellenadoConCamposFormulario(dForm);
	
		dForm.set("id", procedimiento.getId());
		dForm.set("indicador",procedimiento.getIndicador());  //1
		dForm.set("ventana",procedimiento.getVentanillaUnica()); //1
		actionParameters.request.setAttribute("host", establecerAtributoServidor(actionParameters.request));
		actionParameters.request.setAttribute("materiaOptions", procedimiento.getMaterias());
		actionParameters.request.setAttribute("normativaOptions", procedimiento.getNormativas());
		actionParameters.request.setAttribute("tramiteOptions", procedimiento.getTramites());
		actionParameters.request.setAttribute("documentoOptions", procedimiento.getDocumentos());
		actionParameters.request.setAttribute("procedimientoForm", dForm);
		return forwardOK(actionParameters.mapping);
		
	}
	
	   /**
     * Método que traduce un formulario de Procedimiento
     * @author - Indra 
     * @param request - petición de usuario
     * @param dForm - formulario dinámico enviado por el usuario
     * @throws Exception
     */
    private void traducir (HttpServletRequest request, ProcedimientoForm dForm) throws Exception  {	

    	
		Traductor traductor = (Traductor) request.getSession().getAttribute("traductor");
		String idiomaOrigen = "ca";

        TraduccionProcedimientoLocal procOrigen = (TraduccionProcedimientoLocal)dForm.get("traducciones", 0);        	

        Iterator itTradprocs = ((ArrayList) dForm.get("traducciones")).iterator();                
        Iterator<String> itLang = traductor.getListLang().iterator(); 
        
        while (itLang.hasNext()){
            
        	TraduccionProcedimientoLocal procDesti = (TraduccionProcedimientoLocal) itTradprocs.next();
        	String idiomaDesti = itLang.next();
        	
        	if (!idiomaOrigen.equals(idiomaDesti)) 
        	{
        		traductor.setDirTraduccio(idiomaOrigen, idiomaDesti);
        		
        		if (traductor.traducir(procOrigen, procDesti)){
        			request.setAttribute("alert", "traduccion.confirmacion");
        			request.setAttribute("booleanCheckValidar", "false");
        		}else {
        			request.setAttribute("alert", "traduccion.error");
        			break;
        		}
        		
        	}
        }
        
        log.debug("Traducción procedimiento - Id: " + (Long) dForm.get("id"));

    }    

}
