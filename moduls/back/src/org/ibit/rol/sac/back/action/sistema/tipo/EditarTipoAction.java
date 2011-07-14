/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 1:53:36 PM
 */
package org.ibit.rol.sac.back.action.sistema.tipo;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.TipoNormativaDelegate;
import org.ibit.rol.sac.model.Tipo;
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
 * Action para editar un Tipo.
 *
 * @struts.action
 *  name="tipoForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/tipo/editar"
 *  input=".sistema.tipo.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="tipoForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/tipo/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.tipo.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/tipo/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.tipo.lista"
 *
 */
public class EditarTipoAction extends BaseDispatchAction{


    protected static Log log = LogFactory.getLog(EditarTipoAction.class);

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
        Tipo tipo = new Tipo();
        VOUtils.populate(tipo, dForm);

        TipoNormativaDelegate tipoDelegate = DelegateUtil.getTipoNormativaDelegate();
        tipoDelegate.grabarTipoNormativa(tipo);
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
        TipoNormativaDelegate tipoDelegate = DelegateUtil.getTipoNormativaDelegate();

        Long id = (Long) dForm.get("id");
        //Tipo tipo = tipoDelegate.obtenerTipoNormativa(id);

        if (tipoDelegate.tieneNormativas(id)){
            request.setAttribute("alert", "tipo.relacion");
            return mapping.findForward("success");
        } else {
            log.debug("Eliminado Tipo: " + id);
            tipoDelegate.borrarTipoNormativa(id);
            request.setAttribute("alert", "confirmacion.baja");
            dForm.reset(mapping, request);
        }

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        TipoNormativaDelegate tipoDelegate = DelegateUtil.getTipoNormativaDelegate();

        Long id = new Long(request.getParameter("idTipo"));
        Tipo tipo = tipoDelegate.obtenerTipoNormativa(id);
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
