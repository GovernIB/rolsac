package org.ibit.rol.sac.back.action.sistema.publico;

import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.PublicoObjetivoDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.HashMap;

/**
 * Action para editar una Público Objetivo.
 *
 * @struts.action
 *  name="publicoForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/publico/editar"
 *  input=".sistema.publico.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="publicoForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/publico/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.publico.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/hevagrpub.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.hevagrpub"
 */
public class EditarPublicoAction extends BaseDispatchAction{

    protected static Log log = LogFactory.getLog(EditarPublicoAction.class);

    protected Map getKeyMethodMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.subir", "subir");
        map.put("boton.seleccionar", "seleccionar");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en editar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        PublicoObjetivoDelegate publicoDelegate = DelegateUtil.getPublicoObjetivoDelegate();


        PublicoObjetivo   publico;
        if (dForm.get("id") != null) {
             publico= publicoDelegate.obtenerPublicoObjetivo((Long)dForm.get("id"));
        } else {
             publico= new PublicoObjetivo();
        }

        
        VOUtils.populate(publico, dForm);
        publicoDelegate.grabarPublicoObjetivo(publico);
        
        if(dForm.get("id") != null){
            request.setAttribute("alert", "confirmacion.modificacion");
        } else {
            request.setAttribute("alert", "confirmacion.alta");
        }
        dForm.set("id", publico.getId());
        log.info("Creado/Actualizado " + publico.getId());
        
        return mapping.findForward("success");
    }

    public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en eliminar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        PublicoObjetivoDelegate publicoDelegate = DelegateUtil.getPublicoObjetivoDelegate();

        Long id = (Long) dForm.get("id");

        log.info("Eliminado Público Objetivo: " + id);
        publicoDelegate.borrarPublicoObjetivo(id);
        request.setAttribute("alert", "confirmacion.baja");
        dForm.reset(mapping, request);

        return mapping.findForward("lista");
    }

    public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

        log.info("Entramos en seleccionar");
        TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
        PublicoObjetivoDelegate publicoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
        Long id = null;

        if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");
        
        if (id != null){
        	PublicoObjetivo publico = publicoDelegate.obtenerPublicoObjetivo(id);
            VOUtils.describe(dForm, publico);
            return mapping.findForward("success");
        }

        return mapping.findForward("fail");
    }
    
    public ActionForward subir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

		log.info("Entramos en subir");
		PublicoObjetivoDelegate publicoObjetivoDelegate = DelegateUtil.getPublicoObjetivoDelegate();
		
		Long idPubl = new Long(request.getParameter("idSelect"));
		publicoObjetivoDelegate.subirOrden(idPubl);
		
		return mapping.findForward("lista");
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
