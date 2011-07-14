/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 1:35:42 PM
 */
package org.ibit.rol.sac.back.action.sistema.boletin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.BoletinDelegate;
import org.ibit.rol.sac.model.Boletin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un Perfil.
 *
 * @struts.action
 *  name="boletinForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/boletin/editar"
 *  input=".sistema.boletin.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="boletinForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/boletin/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.boletin.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/boletin/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.boletin.lista"
 */
public class EditarBoletinAction extends BaseDispatchAction{


    protected static Log log = LogFactory.getLog(EditarBoletinAction.class);

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
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        Boletin boletin = new Boletin();
        VOUtils.populate(boletin, dForm);

        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();
        boletinDelegate.grabarBoletin(boletin);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", boletin.getId());
        log.debug("Creado/Actualizado " + boletin.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();

        Long id = (Long) dForm.get("id");
        if (boletinDelegate.tieneNormativas(id)){
            request.setAttribute("alert", "boletin.relacion");
            return mapping.findForward("success");
        } else {
            log.debug("Eliminado Boletin: " + id);
            boletinDelegate.borrarBoletin(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        BoletinDelegate boletinDelegate = DelegateUtil.getBoletinDelegate();

        Long id = new Long(request.getParameter("idBoletin"));
        Boletin boletin = boletinDelegate.obtenerBoletin(id);
        VOUtils.describe(dForm, boletin);

        return mapping.findForward("success");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        dForm.initialize(mapping);
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}
