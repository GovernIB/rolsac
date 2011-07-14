package org.ibit.rol.sac.back.action.sistema.agrupacionm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.BaseDispatchAction;
import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.persistence.delegate.AgrupacionMDelegate;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.MateriaAgrupacionMDelegate;
import org.ibit.rol.sac.persistence.delegate.SeccionDelegate;


/**
 * Action para editar una Agrupacion Materia.(ROLSAC)
 *
 * @struts.action
 *  name="agrupacionMForm"
 *  scope="request"
 *  validate="true"
 *  path="/sistema/agrupacionm/editar"
 *  input=".sistema.agrupacionm.form"
 *  parameter="action"
 *
 * @struts.action
 *  name="agrupacionMForm"
 *  scope="request"
 *  validate="false"
 *  path="/sistema/agrupacionm/seleccionar"
 *  parameter="action"
 *
 * @struts.action-forward
 *  name="success" path=".sistema.agrupacionm.form"
 *
 * @struts.action-forward
 *  name="cancel" path="/sistema/matfamper.do"
 *
 * @struts.action-forward
 *  name="lista" path=".sistema.agrupacionm.lista"
 */
public class EditarAgrupacionMAction extends BaseDispatchAction {

    protected static Log log = LogFactory.getLog(EditarAgrupacionMAction.class);

    protected Map getKeyMethodMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("boton.insertar", "editar");
        map.put("boton.modificar", "editar");
        map.put("boton.eliminar", "eliminar");
        map.put("boton.seleccionar", "seleccionar");
        map.put("boton.subir", "subir");
        map.put("agrupacionm.relacion.materias", "materia");
        return map;
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

		log.debug("Entramos en editar");
		TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
		AgrupacionMDelegate agrupacionMDelegate = DelegateUtil.getAgrupacionMDelegate();
		
		
		AgrupacionMateria agrupacionm;
		if (dForm.get("id") == null){
			agrupacionm = new AgrupacionMateria();
		} else {
		//Recuperamos los datos de la ficha
			agrupacionm = agrupacionMDelegate.obtenerAgrupacionM((Long)dForm.get("id"));
		}
		
		VOUtils.populate(agrupacionm, dForm);
		
		Long idSeccion = (Long) dForm.get("idSeccion");
		SeccionDelegate seccionDelegate = DelegateUtil.getSeccionDelegate();
		agrupacionm.setSeccion(seccionDelegate.obtenerSeccion(idSeccion));
		
		agrupacionMDelegate.grabarAgrupacionM(agrupacionm);
		if(dForm.get("id") != null){
			request.setAttribute("alert", "confirmacion.modificacion");
			//si no mostra els procediments associats
			agrupacionm = agrupacionMDelegate.obtenerAgrupacionM(agrupacionm.getId());
			request.setAttribute("listaMaterias", agrupacionm.getMateriasAgrupacionM());
		} else {
			request.setAttribute("alert", "confirmacion.alta");
		}
		dForm.set("id", agrupacionm.getId());
		log.debug("Creado/Actualizado " + agrupacionm.getId());
		return mapping.findForward("success");
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	
		log.debug("Entramos en eliminar");
		TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
		AgrupacionMDelegate agrupacionMDelegate = DelegateUtil.getAgrupacionMDelegate();
		
		Long id = (Long) dForm.get("id");
		
		log.debug("Eliminado Agrupacion Materia: " + id);
		agrupacionMDelegate.borrarAgrupacionM(id);
		request.setAttribute("alert", "confirmacion.baja");
		dForm.reset(mapping, request);
		
		return mapping.findForward("cancel");
	}
	
	public ActionForward seleccionar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	
		log.debug("Entramos en seleccionar");
		TraDynaValidatorForm dForm = (TraDynaValidatorForm) form;
		AgrupacionMDelegate agrupacionMDelegate = DelegateUtil.getAgrupacionMDelegate();
		Long id = null;
		
		if (request.getParameter("idSelect") != null)
		id = new Long(request.getParameter("idSelect"));
		
		if (request.getAttribute("idSelect") != null)
		id = (Long) request.getAttribute("idSelect");
		
		if (id != null){
		AgrupacionMateria agrupacionm = agrupacionMDelegate.obtenerAgrupacionM(id);
		VOUtils.describe(dForm, agrupacionm);
		
		if (agrupacionm.getSeccion() != null) {
		dForm.set("idSeccion", agrupacionm.getSeccion().getId());
		}
		
		request.setAttribute("listaMaterias", agrupacionm.getMateriasAgrupacionM());
		return mapping.findForward("success");
		}
		
		return mapping.findForward("fail");
	}
	
	public ActionForward subir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	
		log.debug("Entramos en subir");
		MateriaAgrupacionMDelegate materiaAgrupacionMDelegate = DelegateUtil.getMateriaAgrupacionMDelegate();
		
		Long idAgru = new Long(request.getParameter("idSelect"));
		materiaAgrupacionMDelegate.subirOrden(idAgru);
		
		return mapping.findForward("lista");
	}
	
	public ActionForward materia(ActionMapping mapping, ActionForm form,
	             HttpServletRequest request,
	             HttpServletResponse response) throws Exception {
	
		log.debug("Entramos en materia");
		MateriaAgrupacionMDelegate matAgruDelegate = DelegateUtil.getMateriaAgrupacionMDelegate();
		
		if (request.getParameter("agru") != null){
		Long idAgru = new Long(request.getParameter("agru"));
		
		if ("alta".equals(request.getParameter("operacion"))){
		Long idMate = new Long(request.getParameter("materia"));
		matAgruDelegate.grabarMateriaAgrupacionM(new MateriaAgrupacionM(), idMate, idAgru);
		}
		
		if ("baja".equals(request.getParameter("operacion"))){
		Long id = new Long(request.getParameter("idAgruMate"));
		matAgruDelegate.borrarMateriaAgrupacionM(id);
		}
		
		if ("subir".equals(request.getParameter("operacion"))){
		Long id = new Long(request.getParameter("idAgruMate"));
		matAgruDelegate.subirOrden(id);
		}
		
		request.setAttribute("idSelect", idAgru);
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
