/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 1:53:36 PM
 */
package org.ibit.rol.sac.back.action.sistema.tafectacion;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TipoAfectacionDelegate;
import org.ibit.rol.sac.model.TipoAfectacion;
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
 * Action para editar un Tipo de Afectacion.
 *
 * @struts.action
 *  name="tafectacionForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/tafectacion/editar"
 *  input=".sistema.tafectacion.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="tafectacionForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/tafectacion/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.tafectacion.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/tafectacion/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.tafectacion.lista"
 *
 */
public class EditarTipoAfectacionAction extends BaseDispatchAction{


    protected static Log log = LogFactory.getLog(EditarTipoAfectacionAction.class);

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

        log.debug("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TipoAfectacion tipo = new TipoAfectacion();
        VOUtils.populate(tipo, dForm);

        TipoAfectacionDelegate tipoDelegate = DelegateUtil.getTipoAfectacionDelegate();
        tipoDelegate.grabarTipoAfectacion(tipo);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", tipo.getId());
        log.debug("Creado/Actualizado " + tipo.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TipoAfectacionDelegate tipoDelegate = DelegateUtil.getTipoAfectacionDelegate();

        Long id = (Long) dForm.get("id");

        if (tipoDelegate.tieneAfectaciones(id)){
            request.setAttribute("alert", "tipo.relacion");
            return mapping.findForward("success");
        } else {
            log.debug("Eliminado Tipo Afectacion: " + id);
            tipoDelegate.borrarTipoAfectacion(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }
        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TipoAfectacionDelegate tipoDelegate = DelegateUtil.getTipoAfectacionDelegate();

        Long id = new Long(request.getParameter("idTipo"));
        TipoAfectacion tipo = tipoDelegate.obtenerTipoAfectacion(id);
        VOUtils.describe(dForm, tipo);

        return mapping.findForward("success");
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
