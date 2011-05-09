package org.ibit.rol.sac.back.action.sistema.agrupacionhv;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.AgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalAgrupacionHVDelegate;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar una Agrupacion Hecho Vital.(PORMAD)
 *
 * @struts.action
 *  name="agrupacionHVForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/agrupacion/editar"
 *  input=".sistema.agrupacion.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="agrupacionHVForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/agrupacion/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.agrupacion.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/hevagrpub.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.agrupacion.lista"
 */
public class EditarAgrupacionHVAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarAgrupacionHVAction.class);

    protected Map getKeyMethodMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.subir", "subir");
        map.put("agrupacion.relacion.hechosvitales", "hechovital");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();


        AgrupacionHechoVital agrupacion;
        if (dForm.get("id") == null){
            agrupacion = new AgrupacionHechoVital();
        } else {
            //Recuperamos los datos de la ficha
            agrupacion = agrupacionHVDelegate.obtenerAgrupacionHV((Long)dForm.get("id"));
        }

        VOUtils.populate(agrupacion, dForm);
        
        Long idPublico = (Long) dForm.get("idPublico");
        PublicoObjetivoDelegate publicoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
        agrupacion.setPublico(publicoDelegate.obtenerPublicoObjetivo(idPublico));
        
        
        FormFile fotofile = (FormFile) dForm.get("fotofile");
        if (archivoValido(fotofile)) {
            agrupacion.setFoto(populateArchivo(agrupacion.getFoto(), fotofile));
        } else if (((Boolean) dForm.get("borrarfoto")).booleanValue()) {
            agrupacion.setFoto(null);
        }

        if (agrupacion.getFoto() != null) {
            dForm.set("nombrefoto", agrupacion.getFoto().getNombre());
        } else {
            dForm.set("nombrefoto", null);
        }

        FormFile iconofile = (FormFile) dForm.get("iconofile");
        if (archivoValido(iconofile)) {
            agrupacion.setIcono(populateArchivo(agrupacion.getIcono(), iconofile));
        } else if (((Boolean) dForm.get("borraricono")).booleanValue()) {
            agrupacion.setIcono(null);
        }

        if (agrupacion.getIcono() != null) {
            dForm.set("nombreicono", agrupacion.getIcono().getNombre());
        } else {
            dForm.set("nombreicono", null);
        }

        FormFile iconogfile = (FormFile) dForm.get("iconogfile");
        if (archivoValido(iconogfile)) {
            agrupacion.setIconoGrande(populateArchivo(agrupacion.getIconoGrande(), iconogfile));
        } else if (((Boolean) dForm.get("borrariconog")).booleanValue()) {
            agrupacion.setIconoGrande(null);
        }

        if (agrupacion.getIconoGrande() != null) {
            dForm.set("nombreiconog", agrupacion.getIconoGrande().getNombre());
        } else {
            dForm.set("nombreiconog", null);
        }
        
        agrupacionHVDelegate.grabarAgrupacionHV(agrupacion);
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
            //si no mostra els procediments associats
            agrupacion = agrupacionHVDelegate.obtenerAgrupacionHV(agrupacion.getId());
            request.setAttribute("listaHechosvitales", agrupacion.getHechosVitalesAgrupacionHV());
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", agrupacion.getId());
        log.info("Creado/Actualizado " + agrupacion.getId());
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();

        Long id = (Long) dForm.get("id");

        log.info("Eliminado Agrupacion Hecho Vital: " + id);
        agrupacionHVDelegate.borrarAgrupacionHV(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("cancel");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        AgrupacionHVDelegate agrupacionHVDelegate = DelegateUtil.getAgrupacionHVDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");
        
        if (id != null){
        	AgrupacionHechoVital agrupacion = agrupacionHVDelegate.obtenerAgrupacionHV(id);
            VOUtils.describe(dForm, agrupacion);
            
            if (agrupacion.getPublico() != null) {
                dForm.set("idPublico", agrupacion.getPublico().getId());
            }
            
            if (agrupacion.getFoto() != null)
                dForm.set("nombrefoto",agrupacion.getFoto().getNombre());

            if (agrupacion.getIcono() != null)
                dForm.set("nombreicono",agrupacion.getIcono().getNombre());

            if (agrupacion.getIconoGrande() != null)
                dForm.set("nombreiconog",agrupacion.getIconoGrande().getNombre());
            
            request.setAttribute("listaHechosvitales", agrupacion.getHechosVitalesAgrupacionHV());
            return mapping.findForward("success");
        }

        return mapping.findForward("fail");
    }

    public ActionForward subir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en subir");
        HechoVitalAgrupacionHVDelegate hechoVitalAgrupacionHVDelegate = DelegateUtil.getHechoVitalAgrupacionHVDelegate();
        
        Long idAgru = new Long(request.getParameter("idSelect"));
        hechoVitalAgrupacionHVDelegate.subirOrden(idAgru);

        return mapping.findForward("lista");
    }

    public ActionForward hechovital(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en hechovital");
        HechoVitalAgrupacionHVDelegate hechoAgruDelegate = DelegateUtil.getHechoVitalAgrupacionHVDelegate();

        if (request.getParameter("agru") != null){
            Long idAgru = new Long(request.getParameter("agru"));

            if ("alta".equals(request.getParameter("operacion"))){
                Long idHecho = new Long(request.getParameter("hechovital"));
                hechoAgruDelegate.grabarHechoVitalAgrupacionHV(new HechoVitalAgrupacionHV(), idHecho, idAgru);
            }

            if ("baja".equals(request.getParameter("operacion"))){
                Long id = new Long(request.getParameter("idAgruHecho"));
                hechoAgruDelegate.borrarHechoVitalAgrupacionHV(id);
            }

            if ("subir".equals(request.getParameter("operacion"))){
                Long id = new Long(request.getParameter("idAgruHecho"));
                hechoAgruDelegate.subirOrden(id);
            }

            request.setAttribute("idSelect", idAgru);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
    }

    public ActionForward cancelled(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        dForm.reset(mapping, request);
        return mapping.findForward("cancel");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

}
