package org.ibit.rol.sac.back.action.sistema.espacio;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EspacioTerritorialDelegate;

/**
 * Action para editar una EspacioTerritorial.(PORMAD)
 *
 * @struts.action
 *  name="espacioForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/espacio/editar"
 *  input=".sistema.espacio.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="espacioForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/espacio/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.espacio.form"
 *
 * @struts.action-forward
 *  name="cancel" path=".sistema.espacio.lista"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.espacio.lista"
 *
 */
public class EditarEspacioTerritorialAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarEspacioTerritorialAction.class);

    protected Map<String,String> getKeyMethodMap() {
        Map<String,String> map = new HashMap<String,String>();
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

        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
        Long idPadre = (Long) dForm.get("idPadre");
        Long idEspacio = (Long) dForm.get("id");
        
        EspacioTerritorial espacio;
        boolean modificant = (idEspacio != null);
        if(modificant){
        	espacio = espacioDelegate.obtenerEspacioTerritorial(idEspacio);
        }else{
        	espacio = new EspacioTerritorial();
        }
        
        FormFile mapafile = (FormFile) dForm.get("mapafile");
        if (archivoValido(mapafile)) {
        	espacio.setMapa(populateArchivo(espacio.getMapa(), mapafile));
        } else if (((Boolean) dForm.get("borrarmapa")).booleanValue()) {
            log.debug("entram borrar Mapa");
            espacioDelegate.borrarMapa(idEspacio);
            espacio.setMapa(null);
        }
        
        if (espacio.getMapa() != null) {
            dForm.set("nombremapa", espacio.getMapa().getNombre());
        } else {
            dForm.set("nombremapa", null);
        }
        
        FormFile logofile = (FormFile) dForm.get("logofile");
        if (archivoValido(logofile)) {
        	espacio.setLogo(populateArchivo(espacio.getLogo(), logofile));
        } else if (((Boolean) dForm.get("borrarlogo")).booleanValue()) {
            log.debug("entram borrar Logo");
            espacioDelegate.borrarLogo(idEspacio);
            espacio.setLogo(null);
        }
        
        if (espacio.getLogo() != null) {
            dForm.set("nombrelogo", espacio.getLogo().getNombre());
        } else {
            dForm.set("nombrelogo", null);
        }
        
        if (modificant) {
            VOUtils.populate(espacio, dForm);
            espacioDelegate.actualizarEspacioTerritorial(espacio, idPadre);
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            VOUtils.populate(espacio, dForm);
            espacioDelegate.crearEspacioTerritorial(espacio, idPadre);
            dForm.set("id", espacio.getId());
            request.setAttribute("alert", "confirmacion.alta");
        }

        if (espacio.getPadre() != null) {
            request.setAttribute("padre", espacio.getPadre());
        }
        log.debug("Creado/Actualizado " + espacio.getId());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminado EspacioTerritorial: " + id);
        espacioDelegate.borrarEspacioTerritorial(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EspacioTerritorialDelegate espacioDelegate = DelegateUtil.getEspacioTerritorialDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            EspacioTerritorial espacio = espacioDelegate.obtenerEspacioTerritorial(id);
            VOUtils.describe(dForm, espacio);
            if (espacio.getPadre() != null) {
                dForm.set("idPadre", espacio.getPadre().getId());
                request.setAttribute("padre", espacio.getPadre());
            }
            
            if (espacio.getMapa() != null) {
                dForm.set("nombremapa", espacio.getMapa().getNombre());
            }
            if (espacio.getLogo() != null) {
            	dForm.set("nombrelogo", espacio.getLogo().getNombre());
            }

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
