package org.ibit.rol.sac.back.action.sistema.administracionremota;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.AdministracionRemotaDelegate;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;
import org.ibit.rol.sac.integracion.ws.sicronizacion.SincronizadorSingleton;
import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.EspacioTerritorial;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un AdministracionRemota. (PORMAD)
 *
 * @struts.action
 *  name="administracionRemotaForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/administracionRemota/editar"
 *  input=".sistema.administracionRemota.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="administracionRemotaForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/administracionRemota/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.administracionRemota.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/administracionRemota/lista.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.administracionRemota.lista"
 */
public class EditarAdministracionRemotaAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarAdministracionRemotaAction.class);

    protected Map<String, String> getKeyMethodMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.alta", "alta");
        map.put("boton.baja", "baja");
        map.put("boton.clean", "clean");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        AdministracionRemotaDelegate adminDelegate = DelegateUtil.getAdministracionRemotaDelegate();
        
        Long idAdmin = (Long)dForm.get("id");
        
        AdministracionRemota admin;
        if(idAdmin == null){
        	admin = new AdministracionRemota();
        }else {
			admin =  adminDelegate.obtenerAdministracionRemota(idAdmin);
		}
        
        VOUtils.populate(admin, dForm);
        Long idEspacioTerrit = (Long) dForm.get("idEspacioTerrit");
        if(idEspacioTerrit != null){
            EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
            EspacioTerritorial espacioTerritorial = espacioDelegate.obtenerEspacioTerritorial(idEspacioTerrit);
            admin.setEspacioTerrit(espacioTerritorial);
        }
        
        FormFile logopfile = (FormFile) dForm.get("logopfile");
        if (archivoValido(logopfile)) {
        	admin.setLogop(populateArchivo(admin.getLogop(), logopfile));
        } else if (((Boolean) dForm.get("borrarlogop")).booleanValue()) {
            log.debug("entram borrar Logop");
            adminDelegate.borrarLogop(idAdmin);
            admin.setLogop(null);
        }
        
        if (admin.getLogop() != null) {
            dForm.set("nombrelogop", admin.getLogop().getNombre());
        } else {
            dForm.set("nombrelogop", null);
        }
        
        FormFile logogfile = (FormFile) dForm.get("logogfile");
        if (archivoValido(logogfile)) {
        	admin.setLogog(populateArchivo(admin.getLogog(), logogfile));
        } else if (((Boolean) dForm.get("borrarlogog")).booleanValue()) {
        	log.debug("entram borrar Logog");
        	adminDelegate.borrarLogog(idAdmin);
        	admin.setLogog(null);
        }
        
        if (admin.getLogog() != null) {
        	dForm.set("nombrelogog", admin.getLogog().getNombre());
        } else {
        	dForm.set("nombrelogog", null);
        }
        
        
        adminDelegate.grabarAdministracionRemota(admin);
        if(idAdmin != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", admin.getId());
        log.debug("Creado/Actualizado " + admin.getId());
        
        if(adminDelegate.isEmpty(admin.getId()))
        	request.setAttribute("admBacia", "admBacia");
        
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
        
        Long id = (Long) dForm.get("id");
        
        if(administracionRemotaDelegate.isEmpty(id)){
        	log.debug("Eliminado AdministracionRemota: " + id);
        	administracionRemotaDelegate.borrarAdministracionRemota(id);
        	request.setAttribute("alert", "confirmacion.baja");
        	dForm.reset(mapping, request);
        	return mapping.findForward("lista");
        }else{
        	request.setAttribute("alert", "administracionRemota.sinc.error.borrar");
        	return mapping.findForward("success");
        }
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();

        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");
        
        if (id != null){
            AdministracionRemota admin = administracionRemotaDelegate.obtenerAdministracionRemota(id);
            VOUtils.describe(dForm, admin);
            
            if (admin.getEspacioTerrit() != null) {
            	dForm.set("idEspacioTerrit",admin.getEspacioTerrit().getId());
            }
            
            if(administracionRemotaDelegate.isEmpty(id))
            	request.setAttribute("admBacia", "admBacia");
            
            if (admin.getLogop() != null) {
            	dForm.set("nombrelogop", admin.getLogop().getNombre());
            }
            if (admin.getLogog() != null) {
            	dForm.set("nombrelogog", admin.getLogog().getNombre());
            }
            
            return mapping.findForward("success");
        }
        
        return mapping.findForward("fail");
    }
    
    /**
     * Da de alta pormad en una administracion remota
     */
    public ActionForward alta(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	if(!SincronizadorSingleton.Estado.Parado.equals(SincronizadorSingleton.running())){
    		return mapping.findForward("cancel");
    	}
    	
    	log.debug("Entramos en Alta");
    	DynaValidatorForm dForm = (DynaValidatorForm) form;
    	
    	AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
        
        Long id = null;

        if (dForm.get("id") != null)
            id = (Long)dForm.get("id");
        
        if (id != null){
            AdministracionRemota administracionRemota = administracionRemotaDelegate.obtenerAdministracionRemota(id);

        	SincronizadorSingleton sing = SincronizadorSingleton.getInstance();
        	sing.alta(administracionRemota);
            
			
            if(administracionRemotaDelegate.isEmpty(id))
            	request.setAttribute("admBacia", "admBacia");
            return mapping.findForward("success");
        }
    	
    	return mapping.findForward("cancel");
    }
    
    /**
     * Da de baja pormad de una administracion remota
     */
    public ActionForward baja(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	
    	if(!SincronizadorSingleton.Estado.Parado.equals(SincronizadorSingleton.running())){
    		return mapping.findForward("cancel");
    	}
    	
    	log.debug("Entramos en Baja");
    	DynaValidatorForm dForm = (DynaValidatorForm) form;
    	
    	AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();
        
        Long id = (Long)dForm.get("id");
        
        if (id != null){
            AdministracionRemota administracionRemota = administracionRemotaDelegate.obtenerAdministracionRemota(id);
            
        	SincronizadorSingleton sing = SincronizadorSingleton.getInstance();
        	sing.baja(administracionRemota);

            if(administracionRemotaDelegate.isEmpty(id))
            	request.setAttribute("admBacia", "admBacia");
            return mapping.findForward("success");
        }
    	
    	return mapping.findForward("cancel");
    }
    
    /**
     * Limpia alguna excepcion ocurrida en el proceso de alta o baja
     */
    public ActionForward clean(ActionMapping mapping, ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    	log.debug("Entramos en Clean");
    	
    	if(!SincronizadorSingleton.Estado.Error.equals(SincronizadorSingleton.running())){
    		return mapping.findForward("cancel");
    	}
    	SincronizadorSingleton.cleanException();
    	
    	DynaValidatorForm dForm = (DynaValidatorForm) form;
    	AdministracionRemotaDelegate administracionRemotaDelegate = DelegateUtil.getAdministracionRemotaDelegate();

    	Long id = (Long)dForm.get("id");
        
        if (id != null){
            AdministracionRemota admin = administracionRemotaDelegate.obtenerAdministracionRemota(id);
            VOUtils.describe(dForm, admin);
            
            if (admin.getEspacioTerrit() != null) {
            	dForm.set("idEspacioTerrit",admin.getEspacioTerrit().getId());
            }
            
            if(administracionRemotaDelegate.isEmpty(id))
            	request.setAttribute("admBacia", "admBacia");
            
            if (admin.getLogop() != null) {
            	dForm.set("nombrelogop", admin.getLogop().getNombre());
            }
            if (admin.getLogog() != null) {
            	dForm.set("nombrelogog", admin.getLogog().getNombre());
            }
            
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
        
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.debug("Entramos en unspecified");
        return null;
    }

}
