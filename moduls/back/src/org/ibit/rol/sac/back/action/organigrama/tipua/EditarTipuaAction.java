package org.ibit.rol.sac.back.action.organigrama.tipua;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TratamientoDelegate;

/**
 * Action para editar un Tipo de Unidad Administrativa.
 *
 * @struts.action
 *  name="tipuaForm"
 *  scope="request"
 *  validate="true"
 *  path="/organigrama/tipua/editar"
 *  input=".organigrama.tipua.form"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".organigrama.tipua.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/organigrama/tipua.do"
 *
 * @struts.action-forward
 *  name="lista" path=".organigrama.tipua.lista"
 *
 */
public class EditarTipuaAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarTipuaAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        Tratamiento tipua = new Tratamiento();
        VOUtils.populate(tipua,dForm);

        TratamientoDelegate tipuaDelegate = DelegateUtil.getTratamientoDelegate();
        tipuaDelegate.grabarTratamiento(tipua);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }

        dForm.set("id", tipua.getId());
        log.info("Creat/Actualitzat " + tipua.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TratamientoDelegate tipuaDelegate = DelegateUtil.getTratamientoDelegate();

        Long id = (Long) dForm.get("id");
        if (tipuaDelegate.tieneUnidades(id)){
                request.setAttribute("alert", "tipua.relacion");
                request.setAttribute("idTipua", id);
                return dispatchMethod(mapping, form, request, response, "seleccionar");
        } else {
            log.info("Eliminada Familia: " + id);
            tipuaDelegate.borrarTratamiento(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }
        log.info("Eliminado Tipo UA: " + id);

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TratamientoDelegate tipuaDelegate = DelegateUtil.getTratamientoDelegate();

        Long id = null;

        if (request.getParameter("idTipua") != null)
           id = new Long(request.getParameter("idTipua"));

        if (request.getAttribute("idTipua") != null)
           id = (Long) request.getAttribute("idTipua");

        if (id != null){
            Tratamiento tip = tipuaDelegate.obtenerTratamiento(id);
            VOUtils.describe(dForm,tip);
            return mapping.findForward("success");
        }
        return mapping.findForward("fail");
    }

    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en busqueda");
        TratamientoDelegate tipuaDelegate = DelegateUtil.getTratamientoDelegate();

        List tipuas = tipuaDelegate.listarTratamientos();

        request.setAttribute("listaTipuas", tipuas);

        return mapping.findForward("lista");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
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
