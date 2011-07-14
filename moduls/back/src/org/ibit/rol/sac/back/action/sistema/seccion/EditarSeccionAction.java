package org.ibit.rol.sac.back.action.sistema.seccion;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;

/**
 * Action para editar una Seccion.
 *
 * @struts.action
 *  name="seccionForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/seccion/editar"
 *  input=".sistema.seccion.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="seccionForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/seccion/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.seccion.form"
 *
 * @struts.action-forward
 *  name="cancel" path=".sistema.seccion.lista"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.seccion.lista"
 *
 */
public class EditarSeccionAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarSeccionAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.subir", "subir");
        map.put("boton.actualizar_orden", "actualizar_orden");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;

        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
        Long idPadre = (Long) dForm.get("idPadre");
        Long idSeccion = (Long) dForm.get("id");
        Seccion seccion;

        if (idSeccion != null) {
            seccion = seccionDelegate.obtenerSeccion(idSeccion);
            VOUtils.populate(seccion, dForm);
            seccionDelegate.actualizarSeccion(seccion, idPadre);
            request.setAttribute("listaFichas", seccion.getFichasUA());
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            seccion = new Seccion();
            VOUtils.populate(seccion, dForm);
            seccionDelegate.crearSeccion(seccion, idPadre);
            dForm.set("id", seccion.getId());
            request.setAttribute("alert", "confirmacion.alta");
        }

        if (seccion.getPadre() != null) {
            request.setAttribute("padre", seccion.getPadre());
        }
        request.setAttribute("iconosFamilia", seccion.getFichasUA());
        log.debug("Creado/Actualizado " + seccion.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminada Seccion: " + id);
        seccionDelegate.borrarSeccion(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            Seccion seccion = seccionDelegate.obtenerSeccion(id);
            VOUtils.describe(dForm, seccion);
            if (seccion.getPadre() != null) {
                dForm.set("idPadre", seccion.getPadre().getId());
                request.setAttribute("padre", seccion.getPadre());
            }
            Set setFichasUA = seccion.getFichasUA();
            Object[] fichasUA = setFichasUA.toArray(); 
            for(Object o : fichasUA) {
            		FichaUA fichaUA =(FichaUA)o;
            		if(null==fichaUA.getFicha())
            			log.debug("fichaUA id="+fichaUA.getId()+" no tiene ficha");
            }
            
			request.setAttribute("listaFichas", fichasUA);

            //Comprobamos que esté definida la UA raiz en las propiedades,
            // si no existe no se podran crear fichas desde la sección.

            String codiEstandarUAArrel = System.getProperty("es.caib.rolsac.codiEstandarUAArrel");
            log.debug(codiEstandarUAArrel);
            if(!"".equals(codiEstandarUAArrel) && codiEstandarUAArrel !=null){
                request.setAttribute("mostrarNuevaFicha" , "mostrarNuevaFicha");
            }

            return mapping.findForward("success");
        }

        return mapping.findForward("fail");
    }

    public ActionForward subir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

        log.debug("Entramos en subir");
        SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
        Long idSubir = new Long(request.getParameter("idSubir"));
        seccionDelegate.subirOrden(idSubir);

        return mapping.findForward("lista");
    }

    public ActionForward actualizar_orden(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

		log.debug("Entramos en actualizar_orden");
		SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
		
		Long id = null;
		if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));
		
		String actOrden = "N";
		if (request.getParameter("actOrden") != null)
			actOrden = request.getParameter("actOrden");

        if (id != null){
            seccionDelegate.actualizarOrdenFichasSeccion(id, request.getParameterNames(), request.getParameterMap() );	
            if (actOrden.equals("S")) seccionDelegate.actualizarOrdenFichasSeccionHuecos(id);
        }
		
		request.setAttribute("idSelect", id);
		return dispatchMethod(mapping, form, request, response, "seleccionar");
    }
    
    
    
    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}
