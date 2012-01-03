/**
 * User: jhorrach
 * Date: Dec 2, 2003
 * Time: 1:03:08 PM
 */
package org.ibit.rol.sac.back.action.sistema.hechovital;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.HechoVitalForm;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.HechoVitalDelegate;
import org.ibit.rol.sac.persistence.delegate.HechoVitalProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.model.HechoVital;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.TraduccionHechoVital;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar un Hecho Vital.  (PORMAD)
 *
 * @struts.action
 *  name="hechoVitalForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/hechovital/editar"
 *  input=".sistema.hechovital.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="hechoVitalForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/hechovital/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.hechovital.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/hevagrpub.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.hevagrpub"
 */
public class EditarHechoVitalAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarHechoVitalAction.class);

    protected Map getKeyMethodMap() {
        Map map = new HashMap();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.subir", "subir");
        map.put("hechovital.relacion.procedimientos", "procedimiento");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en editar");
        HechoVitalForm dForm = (HechoVitalForm) form;
        IdiomaDelegate idiomaDelegate = DelegateUtil.getIdiomaDelegate();
        HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
        HechoVital hechoVital = new HechoVital();
        
        VOUtils.populate(hechoVital, dForm);
        
        HechoVital hechovitalOld = null;
        boolean modificant = dForm.get("id") != null;
        if (modificant){
            //Recuperamos los datos del hecho vital
        	hechovitalOld = (HechoVital)hechoVitalDelegate.obtenerHechoVital((Long)dForm.get("id"));
        }
        
        //tractam tots els fitxers associats a les traduccions
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();
        Iterator distribs =  Arrays.asList((FormFile[]) dForm.get("distribs")).iterator();
        Iterator normats =  Arrays.asList((FormFile[]) dForm.get("normats")).iterator();
        Iterator contens =  Arrays.asList((FormFile[]) dForm.get("contens")).iterator();
        //iteram per tots els idiomes
        while (lang.hasNext()){
            log.debug("Entro en el while de ficheros");
            FormFile distribComp = (FormFile) distribs.next();
            FormFile normativa = (FormFile) normats.next();
            FormFile contenido = (FormFile) contens.next();
            String idioma = (String) lang.next();
            TraduccionHechoVital traduccion = (TraduccionHechoVital) hechoVital.getTraduccion(idioma);
            // tractam distribucióCompetencial
            if (archivoValido(distribComp)){
                traduccion.setDistribComp((populateArchivo(traduccion.getDistribComp(), distribComp)));
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrardistrib_" + idioma) != null){
                traduccion.setDistribComp(null);
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionHechoVital traduccionOld = (TraduccionHechoVital) hechovitalOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getDistribComp() != null)
                    traduccion.setDistribComp(traduccionOld.getDistribComp());
            }
            // tractam normativa aplicada
            if (archivoValido(normativa)){
                traduccion.setNormativa((populateArchivo(traduccion.getNormativa(), normativa)));
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarnormat_" + idioma) != null){
                traduccion.setNormativa(null);
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionHechoVital traduccionOld = (TraduccionHechoVital) hechovitalOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getNormativa() != null)
                    traduccion.setNormativa(traduccionOld.getNormativa());
            }
            // tractam contenido
            if (archivoValido(contenido)){
                traduccion.setContenido((populateArchivo(traduccion.getContenido(), contenido)));
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarconten_" + idioma) != null){
                traduccion.setContenido(null);
                hechoVital.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionHechoVital traduccionOld = (TraduccionHechoVital) hechovitalOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getContenido() != null)
                    traduccion.setContenido(traduccionOld.getContenido());
            }
        }

        FormFile fotofile = (FormFile) dForm.get("fotofile");
        if (archivoValido(fotofile)) {
            hechoVital.setFoto(populateArchivo(hechoVital.getFoto(), fotofile));
        } else if (((Boolean) dForm.get("borrarfoto")).booleanValue()) {
            hechoVital.setFoto(null);
        }else if (modificant){
            if(hechovitalOld.getFoto()!=null){
                hechoVital.setFoto(hechovitalOld.getFoto());
            }
        }

        if (hechoVital.getFoto() != null) {
            dForm.set("nombrefoto", hechoVital.getFoto().getNombre());
        } else {
            dForm.set("nombrefoto", null);
        }

        FormFile iconofile = (FormFile) dForm.get("iconofile");
        if (archivoValido(iconofile)) {
            hechoVital.setIcono(populateArchivo(hechoVital.getIcono(), iconofile));
        } else if (((Boolean) dForm.get("borraricono")).booleanValue()) {
            hechoVital.setIcono(null);
        }else if (modificant){
            if(hechovitalOld.getIcono()!=null){
                hechoVital.setIcono(hechovitalOld.getIcono());
            }
        }

        if (hechoVital.getIcono() != null) {
            dForm.set("nombreicono", hechoVital.getIcono().getNombre());
        } else {
            dForm.set("nombreicono", null);
        }

        FormFile iconogfile = (FormFile) dForm.get("iconogfile");
        if (archivoValido(iconogfile)) {
            hechoVital.setIconoGrande(populateArchivo(hechoVital.getIconoGrande(), iconogfile));
        } else if (((Boolean) dForm.get("borrariconog")).booleanValue()) {
            hechoVital.setIconoGrande(null);
        }else if (modificant){
            if(hechovitalOld.getIconoGrande()!=null){
                hechoVital.setIconoGrande(hechovitalOld.getIconoGrande());
            }
        }

        if (hechoVital.getIconoGrande() != null) {
            dForm.set("nombreiconog", hechoVital.getIconoGrande().getNombre());
        } else {
            dForm.set("nombreiconog", null);
        }
        
        hechoVitalDelegate.grabarHechoVital(hechoVital);
        if(modificant){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", hechoVital.getId());
        log.debug("Creado/Actualizado " + hechoVital.getId());
        //si no mostra els procediments associats
        request.setAttribute("listaProcedimientos", hechoVital.getHechosVitalesProcedimientos());
    	request.setAttribute("idSelect", hechoVital.getId());
    	return dispatchMethod(mapping, form, request, response, "seleccionar");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();

        Long id = (Long) dForm.get("id");

        log.debug("Eliminado Hecho Vital: " + id);
        hechoVitalDelegate.borrarHechoVital(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");

        if (id != null){
            HechoVital hechoVital = hechoVitalDelegate.obtenerHechoVital(id);
            VOUtils.describe(dForm, hechoVital);
            if (hechoVital.getFoto() != null)
                dForm.set("nombrefoto",hechoVital.getFoto().getNombre());

             if (hechoVital.getIcono() != null)
                dForm.set("nombreicono",hechoVital.getIcono().getNombre());

             if (hechoVital.getIconoGrande() != null)
                dForm.set("nombreiconog",hechoVital.getIconoGrande().getNombre());
            
            request.setAttribute("listaProcedimientos", hechoVital.getHechosVitalesProcedimientos());
            return mapping.findForward("success");
        }

        return mapping.findForward("fail");
    }

    public ActionForward subir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.debug("Entramos en subir");
        HechoVitalDelegate hechoVitalDelegate = DelegateUtil.getHechoVitalDelegate();

        Long idHecho = new Long(request.getParameter("idSelect"));
        hechoVitalDelegate.subirOrden(idHecho);

        return mapping.findForward("lista");
    }

    public ActionForward procedimiento(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        log.debug("Entramos en procedimiento");
        HechoVitalProcedimientoDelegate hechoProcDelegate = DelegateUtil.getHechoVitalProcedimientoDelegate();

        if (request.getParameter("hecho") != null){
            Long idHecho = new Long(request.getParameter("hecho"));

            if ("alta".equals(request.getParameter("operacion"))){
                Long idProcedimiento = new Long(request.getParameter("procedimiento"));
                hechoProcDelegate.grabarHechoVitalProcedimiento(new HechoVitalProcedimiento(), idHecho, idProcedimiento);
            }

            if ("baja".equals(request.getParameter("operacion"))){
                Long id = new Long(request.getParameter("idHechoProc"));
                hechoProcDelegate.borrarHechoVitalProcedimiento(id);
            }

            if ("subir".equals(request.getParameter("operacion"))){
                Long id = new Long(request.getParameter("idHechoProc"));
                hechoProcDelegate.subirOrden(id);
            }

            request.setAttribute("idSelect", idHecho);
            return dispatchMethod(mapping, form, request, response, "seleccionar");
        }

        return mapping.findForward("cancel");
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
