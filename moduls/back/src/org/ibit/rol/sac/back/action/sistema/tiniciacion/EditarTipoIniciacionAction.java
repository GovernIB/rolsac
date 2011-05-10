/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 1:53:36 PM
 */
package org.ibit.rol.sac.back.action.sistema.tiniciacion;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.model.Iniciacion;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un Tipo de Iniciacion
 *
 * @struts.action
 *  name="tiniciacionForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/tiniciacion/editar"
 *  input=".sistema.tiniciacion.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="tiniciacionForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/tiniciacion/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.tiniciacion.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/tiniciacion/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.tiniciacion.lista"
 *
 */
public class EditarTipoIniciacionAction extends BaseDispatchAction{


    protected static Log log = LogFactory.getLog(EditarTipoIniciacionAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        Iniciacion tipo = new Iniciacion();
        VOUtils.populate(tipo, dForm);

        IniciacionDelegate tipoDelegate = DelegateUtil.getIniciacionDelegate();
        tipoDelegate.grabarIniciacion(tipo);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", tipo.getId());
        log.info("Creado/Actualizado " + tipo.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        IniciacionDelegate tipoDelegate = DelegateUtil.getIniciacionDelegate();

        Long id = (Long) dForm.get("id");
 
            log.info("Eliminado Tipo iniciacion: " + id);
            tipoDelegate.borrarIniciacion(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        
        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        IniciacionDelegate tipoDelegate = DelegateUtil.getIniciacionDelegate();
        Long id = new Long(request.getParameter("idTipo"));
        Iniciacion tipo = tipoDelegate.obtenerIniciacion(id);
        VOUtils.describe(dForm, tipo);

        return mapping.findForward("success");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        dForm.reset(mapping, request);
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

}
