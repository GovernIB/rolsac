/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 11:48:41 AM
 */
package org.ibit.rol.sac.back.action.sistema.familia;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar una Familia.
 *
 * @struts.action
 *  name="familiaForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/familia/editar"
 *  input=".sistema.familia.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="familiaForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/familia/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.familia.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/matfamper.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.familia.lista"
 */
public class EditarFamiliaAction  extends BaseDispatchAction {
    protected static Log log = LogFactory.getLog(EditarFamiliaAction.class);

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
        Familia familia = new Familia();
        VOUtils.populate(familia, dForm);

        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
        familiaDelegate.grabarFamilia(familia);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", familia.getId());

        familia = familiaDelegate.obtenerFamilia((Long) dForm.get("id"));
        request.setAttribute("iconosFamilia", familia.getIconos());
        log.debug("Creado/Actualizado " + familia.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();

        Long id = (Long) dForm.get("id");

        if (familiaDelegate.tieneProcedimientos(id)){
                request.setAttribute("alert", "familia.relacion");
                request.setAttribute("idSelect", id);
                return dispatchMethod(mapping, form, request, response, "seleccionar");
        } else {
            log.debug("Eliminada Familia: " + id);
            familiaDelegate.borrarFamilia(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        FamiliaDelegate familiaDelegate = DelegateUtil.getFamiliaDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            Familia familia = familiaDelegate.obtenerFamilia(id);
            VOUtils.describe(dForm, familia);
            request.setAttribute("iconosFamilia", familia.getIconos());
            return mapping.findForward("success");
        }

        return mapping.findForward("fail");
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
