/**
 * User: jhorrach
 * Date: Feb 5, 2004
 * Time: 12:00:48 PM
 */
package org.ibit.rol.sac.back.action.organigrama.unidad;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para mostrar un arbol de secciones.
 *
 * @struts.action
 *  name="unidadForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/unidad/poparbol"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path="/organigrama/unidad/poparbol.jsp"
 *
 */
public class ArbolUAAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(ArbolUAAction.class);
    
    

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.expandir", "expandir");
        map.put("boton.contraer", "contraer");
        return map;
    }

    
    UnidadAdministrativaDelegate uaDelegate;

    public ActionForward expandir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en expandir");
        List tieneHijos = new ArrayList();

    	request.setAttribute("idUA",new Long(request.getParameter("idUA")));
        
        List<UnidadAdministrativa> raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null);
        request.setAttribute("raizOptions", raices);

        UnidadAdministrativaDelegate uaDelegate = null==this.uaDelegate? DelegateUtil.getUADelegate() : this.uaDelegate;
        
        for (int i = 0; i < raices.size(); i++) {
            UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
            if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()){
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = uaDelegate.listarPadresUnidadAdministrativa(id);

            for (int i = 0; i < antecesores.size(); i++) {

                UnidadAdministrativa antecesor = (UnidadAdministrativa) antecesores.get(i);

                List hijos = uaDelegate.listarHijosUA(antecesor.getId());
                request.setAttribute(antecesor.getId().toString(), hijos);

                for (int j = 0; j < hijos.size(); j++) {

                    UnidadAdministrativa hijo = (UnidadAdministrativa) hijos.get(j);

                    if (!uaDelegate.listarHijosUA(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }
                }

            }

            if (request.getParameter("ficha") != null){
                request.setAttribute("ficha", "true");
            }

        } else {
            return mapping.findForward("fail");
        }

        request.setAttribute("tieneHijos", tieneHijos);

        return mapping.findForward("success");
    }

    public ActionForward contraer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en contraer");
        List tieneHijos = new ArrayList();

    	request.setAttribute("idUA",new Long(request.getParameter("idUA")));
        
        List<UnidadAdministrativa> raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null);

        UnidadAdministrativaDelegate uaDelegate = null==this.uaDelegate? DelegateUtil.getUADelegate() : this.uaDelegate;
        
        for (int i = 0; i < raices.size(); i++) {
            UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
            if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()){
                tieneHijos.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {

            Long id = new Long(request.getParameter("idSelect"));
            List antecesores = uaDelegate.listarPadresUnidadAdministrativa(id);

            for (int i = 0; (i + 1) < antecesores.size(); i++) {

                UnidadAdministrativa antecesor = (UnidadAdministrativa) antecesores.get(i);

                List hijos = uaDelegate.listarHijosUA(antecesor.getId());
                request.setAttribute(antecesor.getId().toString(), hijos);

                for (int j = 0; j < hijos.size(); j++) {

                    UnidadAdministrativa hijo = (UnidadAdministrativa) hijos.get(j);

                    if (!uaDelegate.listarHijosUA(hijo.getId()).isEmpty()) {
                        tieneHijos.add(hijo.getId());
                    }

                }

            }

            if (request.getParameter("ficha") != null){
                request.setAttribute("ficha", "true");
            }

        } else {
            return mapping.findForward("fail");
        }

        request.setAttribute("tieneHijos", tieneHijos);

        return mapping.findForward("success");
    }



    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        List tieneHijos = new ArrayList();

        if (request.getParameter("idUA") != null){
        	request.setAttribute("idUA",new Long(request.getParameter("idUA")));
            
            List<UnidadAdministrativa> raices = buscarRaicesUnidadesAdministrativas(request.getParameter("padres") != null);

            
            request.setAttribute("raizOptions", raices);

            UnidadAdministrativaDelegate uaDelegate = null==this.uaDelegate? DelegateUtil.getUADelegate() : this.uaDelegate;
            
            for (int i = 0; i < raices.size(); i++) {
                UnidadAdministrativa raizActual = (UnidadAdministrativa) raices.get(i);
                if (!uaDelegate.listarHijosUA(raizActual.getId()).isEmpty()){
                    tieneHijos.add(raizActual.getId());
                }
            }

            if (request.getParameter("ficha") != null){
                request.setAttribute("ficha", "true");
            }

            request.setAttribute("tieneHijos", tieneHijos);

        }else{
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    
    private List<UnidadAdministrativa> buscarRaicesUnidadesAdministrativas(boolean padre) throws DelegateException {
    
    	UnidadAdministrativaDelegate uaDelegate = null==this.uaDelegate? DelegateUtil.getUADelegate() : this.uaDelegate;
    	
    	List<UnidadAdministrativa> raices = (List<UnidadAdministrativa>)uaDelegate.listarUnidadesAdministrativasRaiz();
        
        // u92770(enric@dgtic) cas en que volem llistar  els pares de les arrels:
        if(padre) {
        	List<UnidadAdministrativa> raicesPadre=new ArrayList<UnidadAdministrativa>();
        	for(UnidadAdministrativa ua:raices) {
        		if(null==ua.getPadre()) raicesPadre.add(ua); 
        		else raicesPadre.add(ua.getPadre());
        	}
        	raices=raicesPadre;
        }
        return raices;
    }

	public UnidadAdministrativaDelegate getUaDelegate() {
		return uaDelegate;
	}

	public void setUaDelegate(UnidadAdministrativaDelegate uaDelegate) {
		this.uaDelegate = uaDelegate;
	}
    
    
}
