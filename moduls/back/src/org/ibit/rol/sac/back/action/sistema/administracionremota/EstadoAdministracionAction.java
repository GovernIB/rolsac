package org.ibit.rol.sac.back.action.sistema.administracionremota;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.BaseAction;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizadorSingleton;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * (PORMAD)
 * @struts.action
 *  path="/sistema/administracionRemota/estado"
 */
public class EstadoAdministracionAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
    	  
    	String estadoOld = request.getParameter("estado");
    	SincronizadorSingleton.Estado estado = SincronizadorSingleton.running();
    	
    	if(getEstado(estado).equals(estadoOld)){
    		SincronizadorSingleton.getInstance().makeMeWaitForEstado(30000);
    		estado = SincronizadorSingleton.running();
    	}
        
        JSONObject object = new JSONObject();
        object.put("etiqueta",getEstado(estado));
        
        response.getWriter().print(object.toString());
        
        return mapping.findForward("success");
    }
    
    
    private String getEstado(SincronizadorSingleton.Estado estado){
    	String resultado=null;
        
		switch (estado) {
		case Ejecutando:
			resultado="eject";
			break;
		case Error:
			resultado="error";
			break;
		case Parado:
			resultado="parado";
			break;
		}
		return resultado;
    }
}




