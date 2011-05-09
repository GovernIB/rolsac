package org.ibit.rol.sac.back.action.contenido.enlace;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.persistence.delegate.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Action para editar un Enlace
 *
 * @struts.action
 *  name="enlaceForm"
 *  scope="request"
 *  validate="true"
 *  path="/contenido/enlace/editar"
 *  input=".contenido.enlace.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="enlaceForm"
 *  scope="request"
 *  validate="false"
 *  path="/contenido/enlace/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".contenido.enlace.form"
 *
 * @struts.action-forward
 *  name="procedimiento" path="/contenido/procedimiento/seleccionar.do"
 *
 * @struts.action-forward
 *  name="ficha" path="/contenido/ficha/seleccionar.do"
 *
 */
public class EditarEnlaceAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarEnlaceAction.class);

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
        
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EnlaceDelegate enlDelegate = DelegateUtil.getEnlaceDelegate();

        Enlace enlace;
        if (dForm.get("id") == null){
        	enlace = new Enlace();
        } else {
            //Recuperamos los datos del enlace
        	enlace = enlDelegate.obtenerEnlace((Long)dForm.get("id"));
        }

        VOUtils.populate(enlace, dForm);
        Enlace enlaceOld = null;
        boolean modificant = dForm.get("id") != null;
        if (modificant){
            //Recuperamos los datos del enlace
        	enlaceOld = (Enlace)enlDelegate.obtenerEnlace((Long)dForm.get("id"));
        }        

        /*
        //tractam els fitxers associats a les traduccions
        Iterator lang = idiomaDelegate.listarLenguajes().iterator();
        Iterator fichers =  Arrays.asList((FormFile[]) dForm.get("fichers")).iterator();
        //iteram per tots els idiomes
        while (lang.hasNext()){
            log.info("Entro en el while de ficheros");
            FormFile fichero = (FormFile) fichers.next();
            String idioma = (String) lang.next();
            TraduccionDocumento traduccion = (TraduccionDocumento) documento.getTraduccion(idioma);
            // tractam fichero
            if (archivoValido(fichero)){
                traduccion.setArchivo((populateArchivo(traduccion.getArchivo(), fichero)));
                documento.setTraduccion(idioma, traduccion);
            } else if (request.getParameter("borrarfichero_" + idioma) != null){
                traduccion.setArchivo(null);
                documento.setTraduccion(idioma, traduccion);
            } else if (modificant){
            	TraduccionDocumento traduccionOld = (TraduccionDocumento) documentoOld.getTraduccion(idioma);
                if (traduccionOld != null && traduccionOld.getArchivo() != null)
                    traduccion.setArchivo(traduccionOld.getArchivo());
            }
        }
        */
        
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }

        /*
         * por si hay que poner en procedimientos (copiado de documentos)
        if (request.getParameter("idProcedimiento") != null){
            Long id = new Long(request.getParameter("idProcedimiento"));
            docDelegate.grabarDocumento(documento, id, null);
            dForm.set("id",documento.getId());
            log.info("Creat/Actualitzat " + documento.getId());
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("procedimiento");
        }
        */

        if (request.getParameter("idFicha") != null){
            Long id = new Long(request.getParameter("idFicha"));
            enlDelegate.grabarEnlace(enlace, null, id);
            dForm.set("id", enlace.getId());
            log.info("Creat/Actualitzat " + enlace.getId());
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("ficha");
        }

        return mapping.findForward("fail");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        EnlaceDelegate enlDelegate = DelegateUtil.getEnlaceDelegate();

        if (request.getParameter("idSelect") != null){
            Long id = new Long(request.getParameter("idSelect"));
            Enlace enlace = enlDelegate.obtenerEnlace(id);
            VOUtils.describe(dForm, enlace);

        } else {
            return mapping.findForward("fail");
        }

        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        EnlaceDelegate enlDelegate = DelegateUtil.getEnlaceDelegate();

        if (request.getParameter("idSelect") != null){
            Long id = new Long(request.getParameter("idSelect"));
            log.info("Eliminado Enlace: " + id);
            enlDelegate.borrarEnlace(id);
            TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
            dForm.reset(mapping, request);

            /*
            if (request.getParameter("idProcedimiento") != null){
                Long idProcedimiento = new Long(request.getParameter("idProcedimiento"));
                request.setAttribute("idSelect", idProcedimiento);
                request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
                return mapping.findForward("procedimiento");

            }
            */

            if (request.getParameter("idFicha") != null){
                Long idFicha = new Long(request.getParameter("idFicha"));
                request.setAttribute("idSelect", idFicha);
                request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));
                return mapping.findForward("ficha");

            }

        }

        return mapping.findForward("fail");
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        log.info("Entramos en cancelled");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm)form;
        dForm.reset(mapping, request);

        /*
        if (request.getParameter("idProcedimiento") != null){
            Long id = new Long(request.getParameter("idProcedimiento"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("procedimiento");
        }
        */
        if (request.getParameter("idFicha") != null){
            Long id = new Long(request.getParameter("idFicha"));
            request.setAttribute("idSelect", id);
            request.setAttribute("action", this.getResources(request).getMessage("boton.seleccionar"));

            return mapping.findForward("ficha");
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
