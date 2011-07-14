package org.ibit.rol.sac.back.action.organigrama.guia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegateImpl;
import org.ibit.rol.sac.persistence.delegate.PersonalDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;

/**
 * Action para editar una Persona.
 *
 * @struts.action
 *  name="personalForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/guia/editar"
 *  input=".organigrama.guia.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="personalForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/guia/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".organigrama.guia.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/organigrama/guia/form.do"
 *
 * @struts.action-forward
 *  name="lista" path=".organigrama.guia.lista"
 *
 */
public class EditarGuiaAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarGuiaAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("usuario.relacion.unidades", "unidades");

        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        Personal persona = new Personal();
        VOUtils.populate(persona, dForm);

        PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
        
        // Le respetamos las UAs
        /*
        if (usuario.getId() != null) {       
        	Usuario usuarioViejo = usuarioDelegate.obtenerUsuario(usuario.getId());
        	usuario.setUnidadesAdministrativas(usuarioViejo.getUnidadesAdministrativas());
        }
        */
        Long idUA = (Long) dForm.get("idUA");
        UnidadAdministrativa ua = new UnidadAdministrativa();
        ua.setId(idUA);
        persona.setUnidadAdministrativa(ua);
        
        personalDelegate.grabarPersonal(persona, idUA);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", persona.getId());
        log.debug("Creado/Actualizado " + persona.getId());

        return mapping.findForward("success");
    	  	
    	
    	/*
        log.debug("Entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;

        PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();

        Personal persona;
                
        if (dForm.get("id") == null){
             persona = new Personal();
        } else {
            //Recuperamos los datos del personal
            persona = personalDelegate.obtenerPersonal((Long)dForm.get("id"));
        }

        VOUtils.populate(persona, dForm);
        
        Long idUA = (Long) dForm.get("idUA");

        Long persona_id = personalDelegate.grabarPersonal(persona, idUA);
        persona.setId(persona_id);

        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", persona.getId());
        log.debug("Creado/Actualizado " + persona.getId());

        return mapping.findForward("success");
        */
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        PersonalDelegate personaDelegate = DelegateUtil.getPersonalDelegate();

        Long id = (Long) dForm.get("id");
        personaDelegate.borrarPersonal(id);
        dForm.reset(mapping, request);

        request.setAttribute("alert", "confirmacion.baja");
        log.debug("Eliminada Persona: " + id);

        dForm.initialize(mapping);
        
        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();

        Long id = new Long(request.getParameter("idPersonal"));
        Personal pers = personalDelegate.obtenerPersonal(id);
        
        VOUtils.describe(dForm, pers);
        
        if (pers.getUnidadAdministrativa() != null || request.getAttribute("idUA")!=null){
            dForm.set("idUA", pers.getUnidadAdministrativa().getId());
            dForm.set("nombreUA", ((TraduccionUA)pers.getUnidadAdministrativa().getTraduccion()).getNombre());
        }

        return mapping.findForward("success");
    }

    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en busqueda");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        
        PersonalDelegate personalDelegate = DelegateUtil.getPersonalDelegate();
        List personas;
        
        Map paramMap = new HashMap();
        
        paramMap.put("username", dForm.get("username"));
        paramMap.put("nombre", dForm.get("nombre"));
        paramMap.put("funciones", dForm.get("funciones"));
        paramMap.put("cargo", dForm.get("cargo"));
        paramMap.put("email", dForm.get("email"));
        paramMap.put("extensionPublica", dForm.get("extensionPublica"));
        paramMap.put("numeroLargoPublico", dForm.get("numeroLargoPublico"));
        paramMap.put("extensionPrivada", dForm.get("extensionPrivada"));
        paramMap.put("numeroLargoPrivado", dForm.get("numeroLargoPrivado"));
        paramMap.put("extensionMovil", dForm.get("extensionMovil"));
        paramMap.put("numeroLargoMovil", dForm.get("numeroLargoMovil"));
        paramMap.put("unidadAdministrativa.id", dForm.get("idUA"));
        
        personas = personalDelegate.listarPersonalFiltro(paramMap);

        
        request.setAttribute("listaGuia", personas);

        return mapping.findForward("lista");
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
