/**
 * User: jhorrach
 * Date: Mar 30, 2004
 * Time: 1:23:40 PM
 */
package org.ibit.rol.sac.back.action.contenido.formulario;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Formulario;
import org.ibit.rol.sac.persistence.delegate.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para editar un Documento
 *
 * @struts.action
 *  name="formularioForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/formulario/editar"
 *  input=".contenido.formulario.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="formularioForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/formulario/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.formulario.form"
 *
 * @struts.action-forward
 *  name="tramite" path="/contenido/tramite/seleccionar.do"
 *
 */
@Deprecated
public class EditarFormularioAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarFormularioAction.class);

    protected Map getKeyMethodMap(){
        Map map = new HashMap();
        map.put("boton.insertar","editar");
        map.put("boton.modificar","editar");
        map.put("boton.seleccionar","seleccionar");
        map.put("boton.eliminar","eliminar");
        map.put("boton.cancelar","cancelar");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception{

        log.info("entramos en editar");
        DynaValidatorForm dForm = (DynaValidatorForm)form;
        FormularioDelegate formDelegate = DelegateUtil.getFormularioDelegate();
        TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();
        Long idTramite = new Long(request.getParameter("idTramite"));

        Formulario formulario;
        if (dForm.get("id") == null){
            formulario = new Formulario();
        } else {
            //Recuperamos los datos del documento
            formulario = formDelegate.obtenerFormulario((Long)dForm.get("id"));
        }
        VOUtils.populate(formulario, dForm);

        FormFile archivo = (FormFile) dForm.get("fichero");
        if (archivoValido(archivo)){
            formulario.setArchivo(populateArchivo(formulario.getArchivo(), archivo));
        } else if (request.getParameter("borrarfichero") != null){
            formulario.setArchivo(null);
        }

        if (formulario.getArchivo() != null){
            dForm.set("nombreFichero", formulario.getArchivo().getNombre());
        } else {
            dForm.set("nombreFichero", null);
        }

        FormFile manual = (FormFile) dForm.get("ficheroManual");
        if (archivoValido(manual)){
            formulario.setManual(populateArchivo(formulario.getManual(), manual));
        } else if (request.getParameter("borrarmanual") != null){
            formulario.setManual(null);
        }

        if (formulario.getManual() != null){
            dForm.set("nombreManual", formulario.getManual().getNombre());
        } else {
            dForm.set("nombreManual", null);
        }

        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }

        if (request.getParameter("idTramite") != null){
            Long id = new Long(request.getParameter("idTramite"));
            Long idFormulario = formDelegate.grabarFormulario(formulario);
            //@Deprecated tramiteDelegate.anyadirFormulario(idTramite, idFormulario);
            dForm.set("id",formulario.getId());
            log.info("Creat/Actualitzat " + formulario.getId());
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("tramite");
        }

        return mapping.findForward("fail");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        DynaValidatorForm dForm = (DynaValidatorForm) form;
        FormularioDelegate formDelegate = DelegateUtil.getFormularioDelegate();

        if (request.getParameter("idSelect") != null){
            Long id = new Long(request.getParameter("idSelect"));
            Formulario formulario = formDelegate.obtenerFormulario(id);
            VOUtils.describe(dForm, formulario);
            if (formulario.getArchivo() != null){
                dForm.set("nombreFichero", formulario.getArchivo().getNombre());
            }
            if (formulario.getManual() != null){
                dForm.set("nombreManual", formulario.getManual().getNombre());
            }

        } else {
            return mapping.findForward("tramite");
        }

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        FormularioDelegate formDelegate = DelegateUtil.getFormularioDelegate();
        TramiteDelegate tramiteDelegate = DelegateUtil.getTramiteDelegate();

        if (request.getParameter("idSelect") != null && request.getParameter("idTramite") != null){
            Long id = new Long(request.getParameter("idSelect"));
            Long idTramite = new Long(request.getParameter("idTramite"));
            log.info("Eliminado Formulario: " + id);
          //@Deprecated tramiteDelegate.eliminarFormulario(idTramite, id);
            formDelegate.borrarFormulario(id);
            DynaValidatorForm dForm = (DynaValidatorForm) form;
            dForm.reset(mapping, request);

            request.setAttribute("idSelect", idTramite);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
            return mapping.findForward("tramite");

        }

        return mapping.findForward("fail");
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        log.info("Entramos en cancelar");
        DynaValidatorForm dForm = (DynaValidatorForm)form;

        if (request.getParameter("idTramite") != null){
            Long id = new Long(request.getParameter("idTramite"));
            dForm.reset(mapping, request);
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("tramite");
        }

        return mapping.findForward("fail");
    }

    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        log.info("Entramos en unspecified");
        return null;
    }

}
