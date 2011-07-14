package org.ibit.rol.sac.back.action.contenido.procedimiento;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.contenido.common.BaseAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.negocio.BuscadorProcedimiento;
import org.ibit.rol.sac.back.utils.MateriasUtils;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionFamilia;
import org.ibit.rol.sac.model.TraduccionIniciacion;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;


public class SeleccionarProcedimientoAction extends BaseAction {

    protected static Log log = LogFactory.getLog(SeleccionarProcedimientoAction.class);

    private IdiomaDelegate idiomaDelegate;

	public IdiomaDelegate getIdiomaDelegate() {
        if(null==idiomaDelegate) idiomaDelegate = DelegateUtil.getIdiomaDelegate();
		return idiomaDelegate;
	}

	public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
		this.idiomaDelegate = idiomaDelegate;
	}
	
	private BuscadorProcedimiento buscadorProcedimiento;

	public BuscadorProcedimiento getBuscadorProcedimiento() {
    	if (null==buscadorProcedimiento) buscadorProcedimiento = new BuscadorProcedimiento();
		return buscadorProcedimiento;
	}
	public void setBuscadorProcedimiento(BuscadorProcedimiento buscadorProcedimiento) {
		this.buscadorProcedimiento = buscadorProcedimiento;
	}
	


	protected ActionForward seleccionar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request) throws DelegateException, Exception {
		
		log.debug("Entramos en seleccionar");

        ProcedimientoLocal procedimientoLocal = buscarProcedimientoSeleccionado(mapping, request);
        if (null==procedimientoLocal) 	return forwardFallo(mapping);
        ProcedimientoForm dForm = rellenarCamposFormularioProcedimiento(procedimientoLocal, (ProcedimientoForm)form);
        rellenarAtributosDelRequest(request, procedimientoLocal, dForm);
        return forwardOK(mapping);
		
	}
	
	 
	private void rellenarAtributosDelRequest(HttpServletRequest request,
			ProcedimientoLocal procedimientoLocal, ProcedimientoForm dForm) {
		if (request.getAttribute("idSelect") != null)
        	request.setAttribute("procedimientoForm", dForm);

        request.setAttribute("host", establecerAtributoServidor(request));

        log.debug("procId "+procedimientoLocal.getId()+ " esta clasificado? " + procedimientoLocal.estaClasificado());
		List<Materia> materiasFiltradas = MateriasUtils.listarMateriasFiltrandoSinClasificar(procedimientoLocal.getMaterias());
		request.setAttribute("materiaOptions", materiasFiltradas);
		log.debug("procId "+procedimientoLocal.getId()+ " tiene "+materiasFiltradas.size()+" materiaOptions");

		request.setAttribute("normativaOptions", procedimientoLocal.getNormativas());
        request.setAttribute("tramiteOptions", procedimientoLocal.getTramites());
        request.setAttribute("documentoOptions", procedimientoLocal.getDocumentos());
	}
	
	
	private ProcedimientoLocal buscarProcedimientoSeleccionado(ActionMapping mapping, HttpServletRequest request)
			throws DelegateException {
		Long id = obtenerIdRequest(request);
		if (null==id) return null;
		ProcedimientoLocal procedimientoLocal = getBuscadorProcedimiento().seleccionarProcedimiento(id);
		return procedimientoLocal;
	}
	
	private ProcedimientoForm rellenarCamposFormularioProcedimiento(
			ProcedimientoLocal procedimientoLocal, ProcedimientoForm dForm)
			throws Exception {
        VOUtils.describe(dForm, procedimientoLocal,getIdiomaDelegate());
        dForm.setFechaActualizacion(procedimientoLocal.getFechaActualizacion());
        dForm.setFechaCaducidad(procedimientoLocal.getFechaCaducidad());
        dForm.setFechaPublicacion(procedimientoLocal.getFechaPublicacion());
        dForm.set("indicador",procedimientoLocal.getIndicador());
        dForm.set("ventana",procedimientoLocal.getVentanillaUnica());
        if (procedimientoLocal.getUnidadAdministrativa() != null){
        	dForm.set("idUA", procedimientoLocal.getUnidadAdministrativa().getId());
        	String nombreUA=((TraduccionUA)procedimientoLocal.getUnidadAdministrativa().getTraduccion()).getNombre();
        	//FIXME nombreUA=nombreUA.replaceAll("'","\\'");
        	dForm.set("nombreUA", nombreUA);
        }
        if (procedimientoLocal.getFamilia() != null){
        	dForm.set("idFamilia", procedimientoLocal.getFamilia().getId());
        	dForm.set("nombreFamilia", ((TraduccionFamilia)procedimientoLocal.getFamilia().getTraduccion()).getNombre());
        }
        if (procedimientoLocal.getIniciacion() != null){
        	dForm.set("idIniciacion", procedimientoLocal.getIniciacion().getId());
        	dForm.set("nombreIniciacion", ((TraduccionIniciacion)procedimientoLocal.getIniciacion().getTraduccion()).getNombre());
        }

        if (null!= procedimientoLocal.getOrganResolutori()){
        	dForm.set("idUAResol", procedimientoLocal.getOrganResolutori().getId());
        	String nombreUA=((TraduccionUA)procedimientoLocal.getOrganResolutori().getTraduccion()).getNombre();
        	//FIXME nombreUA=nombreUA.replaceAll("'","\\'");
        	dForm.set("nombreUAResol", nombreUA);
        }
        return dForm;
	}
	
	
	private Long obtenerIdRequest(HttpServletRequest request) {
		Long id=0L;

		if (request.getParameter("idSelect") != null)
            id = new Long(request.getParameter("idSelect"));

        if (request.getAttribute("idSelect") != null)
            id = (Long) request.getAttribute("idSelect");
		return id;
	}

	
	

}
