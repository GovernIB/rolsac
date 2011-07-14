package org.ibit.rol.sac.back.action.organigrama.edificio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.TraduccionEdificio;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.EdificioDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para editar un Edificio.
 *
 * @struts.action
 *  name="edificioForm"
 *  scope="request"
 *  validate="true"
 *  path="/organigrama/edificio/editar"
 *  input=".organigrama.edificio.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="edificioForm"
 *  scope="request"
 *  validate="false"
 *  path="/organigrama/edificio/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".organigrama.edificio.form"
 *
 * @struts.action-forward
 *  name="cancel" path=".organigrama.edificio.form"
 *
 * @struts.action-forward
 *  name="lista" path=".organigrama.edificio.lista"
 *
 */
public class EditarEdificioAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarEdificioAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.busqueda", "busqueda");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("edificio.relacion.unidades", "unidades");
        
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();

        Edificio edificio;
        if (dForm.get("id") == null){
            edificio = new Edificio();
        } else {
            edificio = edificioDelegate.obtenerEdificio((Long)dForm.get("id"));
        }
        VOUtils.populate(edificio, dForm);

        FormFile fotopfile = (FormFile) dForm.get("fotopfile");
        if (archivoValido(fotopfile)) {
            edificio.setFotoPequenya(populateArchivo(edificio.getFotoPequenya(), fotopfile));
        } else if (((Boolean) dForm.get("borrarfotop")).booleanValue()) {
            edificio.setFotoPequenya(null);
        }

        if (edificio.getFotoPequenya() != null) {
            dForm.set("nombrefotop", edificio.getFotoPequenya().getNombre());
        } else {
            dForm.set("nombrefotop", null);
        }


        FormFile fotogfile = (FormFile) dForm.get("fotogfile");
        if (archivoValido(fotogfile)) {
            edificio.setFotoGrande(populateArchivo(edificio.getFotoGrande(), fotogfile));
        } else if (((Boolean) dForm.get("borrarfotog")).booleanValue()) {
            edificio.setFotoGrande(null);
        }

        if (edificio.getFotoGrande() != null) {
            dForm.set("nombrefotog", edificio.getFotoGrande().getNombre());
        } else {
            dForm.set("nombrefotog", null);
        }


        FormFile planofile = (FormFile) dForm.get("planofile");
        if (archivoValido(planofile)) {
            edificio.setPlano(populateArchivo(edificio.getPlano(), planofile));
        } else if (((Boolean) dForm.get("borrarplano")).booleanValue()) {
            edificio.setPlano(null);
        }

        if (edificio.getPlano() != null) {
            dForm.set("nombreplano", edificio.getPlano().getNombre());
        } else {
            dForm.set("nombreplano", null);
        }


        edificioDelegate.grabarEdificio(edificio);

         if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", edificio.getId());

        log.debug("Creado/Actualizado " + edificio.getId());
        
        request.setAttribute("unidadOptions", edificio.getUnidadesAdministrativas());

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();

        Long id = (Long) dForm.get("id");
        edificioDelegate.borrarEdificio(id);
        dForm.reset(mapping, request);

        request.setAttribute("alert", "confirmacion.baja");
        log.debug("Eliminado Edificio: " + id);

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null){
            id = new Long(request.getParameter("idSelect"));
        }

        if (request.getAttribute("idSelect") != null){
            id = (Long) request.getAttribute("idSelect");
        }

        if (id != null){
            Edificio edificio = edificioDelegate.obtenerEdificio(id);
            VOUtils.describe(dForm, edificio);

            if (edificio.getFotoGrande() != null)
                dForm.set("nombrefotog",edificio.getFotoGrande().getNombre());
            if (edificio.getFotoPequenya() != null)
                dForm.set("nombrefotop",edificio.getFotoPequenya().getNombre());
            if (edificio.getPlano() != null)
                dForm.set("nombreplano",edificio.getPlano().getNombre());

            dForm.set("direccion", edificio.getDireccion());
            dForm.set("telefono", edificio.getTelefono());
            dForm.set("fax", edificio.getFax());
            
            request.setAttribute("unidadOptions", edificio.getUnidadesAdministrativas());

        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    public ActionForward busqueda(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en busqueda");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();

        Map paramMap = new HashMap();
        Map tradMap = new HashMap();

        Iterator itTrad = ((ArrayList) dForm.get("traducciones")).iterator();
        Iterator itLang = idiomaDelegate.listarLenguajes().iterator();

        if(!"".equals(dForm.get("direccion"))){
            paramMap.put("direccion", dForm.get("direccion").toString().toUpperCase());
        }
        if(!"".equals(dForm.get("codigoPostal"))){
            paramMap.put("codigoPostal", dForm.get("codigoPostal").toString().toUpperCase());
        }
        if(!"".equals(dForm.get("poblacion"))){
            paramMap.put("poblacion", dForm.get("poblacion").toString().toUpperCase());
        }
        if(!"".equals(dForm.get("telefono"))){
            paramMap.put("telefono", dForm.get("telefono").toString().toUpperCase());
        }
        if(!"".equals(dForm.get("fax"))){
            paramMap.put("fax", dForm.get("fax").toString().toUpperCase());
        }
        if(!"".equals(dForm.get("email"))){
            paramMap.put("email", dForm.get("email").toString().toUpperCase());
        }


        tradMap.put("idioma", idiomaDelegate.lenguajePorDefecto());
        while (itLang.hasNext()){
            TraduccionEdificio tEdificio = (TraduccionEdificio) itTrad.next();
            int x = 0;
            x += tEdificio.getDescripcion().length();
            if (x > 0){
                tradMap.put("idioma", itLang.next().toString());
                tradMap.put("descripcion", tEdificio.getDescripcion().toUpperCase());
                break;
            } else {
                itLang.next();
            }
        }
        List edificios = edificioDelegate.buscarEdificios(paramMap, tradMap);
        request.setAttribute("listaEdificios", edificios);
        return mapping.findForward("lista");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

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
    	EdificioDelegate edificioDelegate = DelegateUtil.getEdificioDelegate();

    	if (request.getParameter("edificio") != null && request.getParameter("unidad") != null){
    		Long idEdi = new Long(request.getParameter("edificio"));
    		Long idUnidad = new Long(request.getParameter("unidad"));

    		if ("alta".equals(request.getParameter("operacion")))
    			edificioDelegate.anyadirUnidad(idUnidad, idEdi);
    		if ("baja".equals(request.getParameter("operacion")))
        		edificioDelegate.eliminarUnidad(idUnidad, idEdi);

    		request.setAttribute("idSelect", idEdi);
    		return dispatchMethod(mapping, form, request, response, "seleccionar");
    		
    	}
    	
    	return mapping.findForward("cancel");
    }
    
}
