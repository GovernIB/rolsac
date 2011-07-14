package org.ibit.rol.sac.back.action.sistema.destinatario;

import org.apache.struts.validator.DynaValidatorForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.DestinatarioDelegate;
import org.ibit.rol.sac.model.Destinatario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un Destinatario.(PORMAD)
 *
 * @struts.action
 *  name="destinatarioForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/destinatario/editar"
 *  input=".sistema.destinatario.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="destinatarioForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/destinatario/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.destinatario.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/destinatario/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.destinatario.lista"
 */
public class EditarDestinatarioAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarDestinatarioAction.class);

    protected Map getKeyMethodMap() {
        Map<String, String> map = new HashMap<String, String>();
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
        DestinatarioDelegate destinatarioDelegate = DelegateUtil.getDestinatarioDelegate();
        
        Destinatario destinatario = new Destinatario();
        
        VOUtils.populate(destinatario, dForm);
        
        destinatarioDelegate.grabarDestinatario(destinatario);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", destinatario.getId());
        log.debug("Creado/Actualizado " + destinatario.getId());
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        DestinatarioDelegate destinatarioDelegate = DelegateUtil.getDestinatarioDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminado Destinatario: " + id);
        destinatarioDelegate.borrarDestinatario(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        DestinatarioDelegate destinatarioDelegate = DelegateUtil.getDestinatarioDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");
        
        if (id != null){
            Destinatario destinatario = destinatarioDelegate.obtenerDestinatario(id);
            VOUtils.describe(dForm, destinatario);
            
            return mapping.findForward("success");
        }
        
        return mapping.findForward("fail");
    }
    
    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        dForm.reset(mapping, request);
        
        //TODO: implementar la funcion
        
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}
