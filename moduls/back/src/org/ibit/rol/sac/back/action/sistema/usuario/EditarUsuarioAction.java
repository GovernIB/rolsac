package org.ibit.rol.sac.back.action.sistema.usuario;

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
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Usuario;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.UsuarioDelegate;

/**
 * Action para editar un Usuario.
 *
 * @struts.action
 *  name="usuarioForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/usuario/editar"
 *  input=".sistema.usuario.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="usuarioForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/usuario/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.usuario.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/usuario/form.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.usuario.lista"
 *
 */
public class EditarUsuarioAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarUsuarioAction.class);

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
        Usuario usuario = new Usuario();
        VOUtils.populate(usuario, dForm);

        UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();
        
        // Le respetamos las UAs
        if (usuario.getId() != null) {       
        	Usuario usuarioViejo = usuarioDelegate.obtenerUsuario(usuario.getId());
        	usuario.setUnidadesAdministrativas(usuarioViejo.getUnidadesAdministrativas());
        }
        
        usuarioDelegate.grabarUsuario(usuario);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", usuario.getId());
        log.debug("Creado/Actualizado " + usuario.getId());

        request.setAttribute("unidadOptions", usuario.getUnidadesAdministrativas());
        
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminado Usuario: " + id);
        usuarioDelegate.borrarUsuario(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.initialize(mapping);

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();

        Long id = null;
        
        if (request.getParameter("idUsuario") != null){
            id = new Long(request.getParameter("idUsuario"));
        }

        if (request.getAttribute("idUsuario") != null){
            id = (Long) request.getAttribute("idUsuario");
        }
        
        Usuario user = usuarioDelegate.obtenerUsuario(id);
        VOUtils.describe(dForm, user);

        request.setAttribute("unidadOptions", user.getUnidadesAdministrativas());
        
        return mapping.findForward("success");
    }

    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en busqueda");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();

        List usuarios = usuarioDelegate.buscarUsuarios(dForm.getMap());

        if (usuarios.size() == 1) {
            Usuario user = (Usuario) usuarios.get(0);
            user = usuarioDelegate.obtenerUsuario(user.getId());
            //Hibernate.initialize(user.getUnidadesAdministrativas());
            VOUtils.describe(dForm, user);
            request.setAttribute("unidadOptions", user.getUnidadesAdministrativas());
            return mapping.findForward("success");
        }

        request.setAttribute("listaUsuarios", usuarios);

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
    
    public ActionForward unidades(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

    	log.debug("Entramos en unidades");
    	UsuarioDelegate usuarioDelegate = DelegateUtil.getUsuarioDelegate();

    	if (request.getParameter("usuario") != null && request.getParameter("unidad") != null){
    		Long idUsu = new Long(request.getParameter("usuario"));
    		Long idUnidad = new Long(request.getParameter("unidad"));

    		if ("alta".equals(request.getParameter("operacion")))
    			usuarioDelegate.anyadirUnidad(idUnidad, idUsu);
    		if ("baja".equals(request.getParameter("operacion")))
    			usuarioDelegate.eliminarUnidad(idUnidad, idUsu);

    		request.setAttribute("idUsuario", idUsu);
    		
    		return dispatchMethod(mapping, form, request, response, "seleccionar");
    		
    	}
    	
    	return mapping.findForward("cancel");
    }

    
}
