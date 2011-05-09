package org.ibit.rol.sac.back.action.sistema.espacio;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para mostrar un arbol de EspacioTerritorial.(PORMAD)
 *
 * @struts.action
 *  name="seccionForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/espacio/arbol"
 *  input="formulario.jsp"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path="/sistema/espacio/arbol.jsp"
 *
 */
public class ArbolEspacioTerritorialAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(ArbolEspacioTerritorialAction.class);

    protected Map<String,String> getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("boton.expandir", "expandir");
        return map;
    }

    public ActionForward expandir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en expandir");
        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
        List<Long> tieneHijos = new ArrayList<Long>();
        List<Long> tieneAcceso = new ArrayList<Long>();

        List<EspacioTerritorial> raices = espacioDelegate.listarEspacioTerritorialesRaiz();
        request.setAttribute("idEspacio", new Long(request.getParameter("idEspacio")));
        request.setAttribute("raizOptions", raices);

        for (EspacioTerritorial raizActual : raices){
            if (!espacioDelegate.listarHijosEspacioTerritorial(raizActual.getId()).isEmpty()){
                tieneHijos.add(raizActual.getId());
            }

            tieneAcceso.add(raizActual.getId());
        }

        if (request.getParameter("idSelect") != null) {
            log.info("idSelect= " + request.getParameter("idSelect"));
            Long id = new Long(request.getParameter("idSelect"));
            List<EspacioTerritorial> antecesores = espacioDelegate.listarAntecesoresEspacioTerritorial(id);
            for (EspacioTerritorial antecesor : antecesores){
                Long idAntecesor = antecesor.getId();
                request.setAttribute(idAntecesor.toString(), espacioDelegate.listarHijosEspacioTerritorial(idAntecesor));
                Collection<EspacioTerritorial> hijos = espacioDelegate.listarHijosEspacioTerritorial(idAntecesor);
                for (EspacioTerritorial hijoActual : hijos){
                    if (!espacioDelegate.listarHijosEspacioTerritorial(hijoActual.getId()).isEmpty()){
                        tieneHijos.add(hijoActual.getId());
                    }

                    tieneAcceso.add(hijoActual.getId());
                }
            }

            if (request.getParameter("ficha") != null){
                request.setAttribute("ficha", "true");
            }
        } else {
            return mapping.findForward("fail");
        }

        request.setAttribute("tieneHijos", tieneHijos);
        request.setAttribute("tieneAcceso", tieneAcceso);

        return mapping.findForward("success");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
        List<Long> tieneHijos = new ArrayList<Long>();
        List<Long> tieneAcceso = new ArrayList<Long>();

        if (request.getParameter("idEspacio") != null){
            request.setAttribute("idEspacio", new Long(request.getParameter("idEspacio")));
          
            request.setAttribute("raizOptions", espacioDelegate.listarEspacioTerritorialesRaiz());

            List<EspacioTerritorial> raices = espacioDelegate.listarEspacioTerritorialesRaiz();
            for (EspacioTerritorial raizActual : raices){
                if (!espacioDelegate.listarHijosEspacioTerritorial(raizActual.getId()).isEmpty()){
                    tieneHijos.add(raizActual.getId());
                }

                tieneAcceso.add(raizActual.getId());
            }

            if (request.getParameter("ficha") != null){
                request.setAttribute("ficha", "true");
            }

            request.setAttribute("tieneHijos", tieneHijos);
            request.setAttribute("tieneAcceso", tieneAcceso);

        }else{
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

}
