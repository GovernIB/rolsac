/**
 * User: jhorrach
 * Date: Nov 26, 2003
 * Time: 1:16:54 PM
 */
package org.ibit.rol.sac.back.action.sistema.perfil;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.TraduccionPerfilCiudadano;
import org.ibit.rol.sac.persistence.delegate.PerfilDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;


/**
 * Action para editar un Perfil.
 *
 * @struts.action
 *  name="perfilForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/perfil/editar"
 *  input=".sistema.perfil.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="perfilForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/perfil/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.perfil.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/matfamper.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.perfil.lista"
 *
 */
public class EditarPerfilAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarPerfilAction.class);

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
        PerfilCiudadano perfil = new PerfilCiudadano();
        VOUtils.populate(perfil, dForm);

        PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();
        perfilDelegate.grabarPerfil(perfil);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", perfil.getId());

        perfil = perfilDelegate.obtenerPerfil((Long) dForm.get("id"));
        request.setAttribute("iconosFamilia", perfil.getIconosFamilia());
        request.setAttribute("iconosMateria", perfil.getIconosMateria());
        log.debug("Creado/Actualizado " + perfil.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminado Perfil: " + id);
        perfilDelegate.borrarPerfil(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        PerfilDelegate perfilDelegate = DelegateUtil.getPerfilDelegate();

        Long id = new Long(request.getParameter("idPerfil"));
        PerfilCiudadano perfil = perfilDelegate.obtenerPerfil(id);
        VOUtils.describe(dForm, perfil);
        request.setAttribute("iconosFamilia", perfil.getIconosFamilia());
        request.setAttribute("iconosMateria", perfil.getIconosMateria());

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

