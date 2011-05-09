/**
 * User: jhorrach
 * Date: Jan 8, 2004
 * Time: 12:59:23 PM
 */
package org.ibit.rol.sac.back.action.sistema.seccion;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.model.Seccion;
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
 *  name="seccionForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/seccion/arbol"
 *  input="formulario.jsp"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path="/sistema/seccion/arbol.jsp"
 *
 */
public class ArbolSeccionAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(ArbolSeccionAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.expandir", "expandir");
        return map;
    }

    public ActionForward expandir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.info("Entramos en expandir");
        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
        List tieneHijos = new ArrayList();
        List tieneAcceso = new ArrayList();

        request.setAttribute("idSeccion", new Long(request.getParameter("idSeccion")));
        request.setAttribute("raizOptions", seccionDelegate.listarSeccionesRaizPerfil());

        Iterator raices = seccionDelegate.listarSeccionesRaizPerfil().iterator();
        while (raices.hasNext()){
            Seccion raizActual = ((Seccion) raices.next());
            if (!seccionDelegate.listarHijosSeccion(raizActual.getId()).isEmpty()){
                tieneHijos.add(raizActual.getId());
            }

            if (hasRole(request, raizActual.getPerfil())){
                tieneAcceso.add(raizActual.getId());
            }
        }

        if (request.getParameter("idSelect") != null) {
            log.info("idSelect= " + request.getParameter("idSelect"));
            Long id = new Long(request.getParameter("idSelect"));
            Iterator antecesores = seccionDelegate.listarAntecesoresSeccion(id).iterator();
            while (antecesores.hasNext()){
                Long idAntecesor = ((Seccion) antecesores.next()).getId();
                request.setAttribute(idAntecesor.toString(), seccionDelegate.listarHijosSeccion(idAntecesor));
                Iterator hijos = seccionDelegate.listarHijosSeccion(idAntecesor).iterator();
                while (hijos.hasNext()){
                    Seccion hijoActual = ((Seccion) hijos.next());
                    if (!seccionDelegate.listarHijosSeccion(hijoActual.getId()).isEmpty()){
                        tieneHijos.add(hijoActual.getId());
                    }

                    if (hasRole(request, hijoActual.getPerfil())){
                        tieneAcceso.add(hijoActual.getId());
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
        request.setAttribute("tieneAcceso", tieneAcceso);

        return mapping.findForward("success");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
        List tieneHijos = new ArrayList();
        List tieneAcceso = new ArrayList();

        if (request.getParameter("idSeccion") != null){
            request.setAttribute("idSeccion", new Long(request.getParameter("idSeccion")));
          
            request.setAttribute("raizOptions", seccionDelegate.listarSeccionesRaizPerfil());

            Iterator raices = seccionDelegate.listarSeccionesRaizPerfil().iterator();
            while (raices.hasNext()){
                Seccion raizActual = ((Seccion) raices.next());
                if (!seccionDelegate.listarHijosSeccion(raizActual.getId()).isEmpty()){
                    tieneHijos.add(raizActual.getId());
                }

                if (hasRole(request, raizActual.getPerfil())){
                    tieneAcceso.add(raizActual.getId());
                }
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
