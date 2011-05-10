package org.ibit.rol.sac.back.action.contenido.ficha;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.back.action.contenido.common.BaseAction;
import org.ibit.rol.sac.back.form.ProcedimientoForm;
import org.ibit.rol.sac.back.negocio.GrabadorProcedimiento;
import org.ibit.rol.sac.back.utils.VOUtils;
import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FamiliaDelegate;
import org.ibit.rol.sac.persistence.delegate.IdiomaDelegate;
import org.ibit.rol.sac.persistence.delegate.IniciacionDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate;

public class GuardarFichaAction extends BaseAction {

	
	protected static Log log = LogFactory.getLog(GuardarFichaAction.class);
/*
	  
	protected ActionForward guardar(EditarFichaAction action, ActionParameters params) throws DelegateException, Exception {

		log.info("Entramos en action guardar");
		ProcedimientoForm dForm = (ProcedimientoForm) params.form;
		ProcedimientoLocal procedimiento = crearProcedimientoRellenadoConCamposFormulario(dForm);
		procedimiento = getGrabadorProcedimiento().guardar(dForm, procedimiento); 
		dForm.set("id", procedimiento.getId());
		log.info("Creat/Actualitzat " + procedimiento.getId());
		params.setAttribute("host", establecerAtributoServidor(params.request));
		
		 
		if (obtenerIdFormulario(dForm) != null) { 
			return dispatchSeleccionarProcedimiento(action, params, procedimiento.getId());
		}
		 
		return forwardEdicionProcedimientoOK(params.mapping, params.request);
		
	}
	

	
	private ActionForward dispatchSeleccionarProcedimiento(
			EditarProcedimientoAction action, ActionParameters params, Long id) throws Exception {
		params.setAttribute("alert", "confirmacion.modificacion");
		params.setAttribute("idSelect", id);
		return action.dispatchMethod(params, "seleccionar");
	}

	
*/	
	private ProcedimientoLocal inicializarProcedimiento(ProcedimientoForm dForm) throws DelegateException {


		Long id=obtenerIdFormulario(dForm);
		ProcedimientoLocal procedimiento = esUnProcedimientoNuevo(id) ? 
				crearInicializarProcedimiento() :
					getProcedimientoDelegate().consultarProcedimiento(id);
				return procedimiento;
	}



	private ActionForward forwardEdicionProcedimientoOK(ActionMapping mapping, HttpServletRequest request) {
		request.setAttribute("alert", "confirmacion.alta");
		return forwardOK(mapping);
	}

	private Long obtenerIdFormulario(ProcedimientoForm dForm) {
		return (Long)dForm.get("id");
	}


	private ProcedimientoLocal crearInicializarProcedimiento() {
		ProcedimientoLocal procedimiento;
		procedimiento = new ProcedimientoLocal();
		inicializarCollectionsProcedimiento(procedimiento);
		return procedimiento;
	}

	private void inicializarCollectionsProcedimiento(
			ProcedimientoLocal procedimiento) {
		procedimiento.setMaterias(new HashSet<Materia>());
		procedimiento.setHechosVitalesProcedimientos(new HashSet<HechoVitalProcedimiento>());

	}
	private boolean esUnProcedimientoNuevo(Long id) {
		boolean isNew = id==null || (id instanceof Long && ((Long)id)==0);
		return isNew;
	}

	protected ProcedimientoLocal crearProcedimientoRellenadoConCamposFormulario(ProcedimientoForm dForm)
	throws DelegateException, Exception {
		ProcedimientoLocal procedimiento;

		procedimiento = inicializarProcedimiento(dForm);		

		rellenarCamposUnicosYTraducibles(dForm, procedimiento);

		//establecer campos de fechas, indicador, ventana, idfamilia, idIniciacion
		procedimiento.setFechaActualizacion(dForm.getFechaActualizacion());
		procedimiento.setFechaCaducidad(dForm.getFechaCaducidad());
		procedimiento.setFechaPublicacion(dForm.getFechaPublicacion());
		if((""+dForm.get("indicador").toString()).equals("on"))
			procedimiento.setIndicador("1");
		else
			procedimiento.setIndicador("0"); 

		if((dForm.get("ventana").toString()).equals("on"))
			procedimiento.setVentanillaUnica("1");  //VUDS
		else
			procedimiento.setVentanillaUnica("0"); 

		if (dForm.get("idFamilia") != null) {
			procedimiento.setFamilia(getFamiliaDelegate().obtenerFamilia((Long) dForm.get("idFamilia")));
		}
		if (dForm.get("idIniciacion") != null) {
			procedimiento.setIniciacion(getIniciacionDelegate().obtenerIniciacion((Long) dForm.get("idIniciacion")));
		}

		Long idUAResol = (Long) dForm.get("idUAResol");
		if(null!=idUAResol && 0!=idUAResol) {
			UnidadAdministrativa uaResol = getUaDelegate().obtenerUnidadAdministrativa(idUAResol);
			procedimiento.setOrganResolutori(uaResol);
		}

		return procedimiento;
	}
	
	private void rellenarCamposUnicosYTraducibles(ProcedimientoForm dForm,
			ProcedimientoLocal procedimiento) throws Exception {
		VOUtils.populate(procedimiento, dForm,getIdiomaDelegate());  
	}


	  ProcedimientoDelegate procedimientoDelegate;

	  FamiliaDelegate familiaDelegate;
	  IniciacionDelegate iniciacionDelegate;
	  IdiomaDelegate idiomaDelegate;
	  UnidadAdministrativaDelegate uaDelegate;
	  GrabadorProcedimiento grabadorProcedimiento;

	  	public ProcedimientoDelegate getProcedimientoDelegate() {
			if(null==procedimientoDelegate) procedimientoDelegate=DelegateUtil.getProcedimientoDelegate();
			  return procedimientoDelegate;
		}
		public FamiliaDelegate getFamiliaDelegate() {
			if(null==familiaDelegate) familiaDelegate = DelegateUtil.getFamiliaDelegate();
			return familiaDelegate;
		}
		public IniciacionDelegate getIniciacionDelegate() {
			if(null==iniciacionDelegate) iniciacionDelegate = DelegateUtil.getIniciacionDelegate();

			return iniciacionDelegate;
		}
		public IdiomaDelegate getIdiomaDelegate() {
			if(null==idiomaDelegate) idiomaDelegate=DelegateUtil.getIdiomaDelegate();
			return idiomaDelegate;
		}

		public GrabadorProcedimiento getGrabadorProcedimiento() {
			if(null==grabadorProcedimiento) grabadorProcedimiento=new GrabadorProcedimiento();
			return grabadorProcedimiento;
		}
		public void setGrabadorProcedimiento(GrabadorProcedimiento grabadorProcedimiento) {
			this.grabadorProcedimiento = grabadorProcedimiento;
		}

		public void setProcedimientoDelegate(ProcedimientoDelegate delegate) {this.procedimientoDelegate=delegate;}
	    public void setFamiliaDelegate(FamiliaDelegate familiaDelegate) {
			this.familiaDelegate = familiaDelegate;
		}
		public void setIniciacionDelegate(IniciacionDelegate iniciacionDelegate) {
			this.iniciacionDelegate = iniciacionDelegate;
		}
	    public void setIdiomaDelegate(IdiomaDelegate idiomaDelegate) {
			this.idiomaDelegate = idiomaDelegate;
		}

	    public UnidadAdministrativaDelegate getUaDelegate() {
			if(null==uaDelegate) uaDelegate = DelegateUtil.getUADelegate();
			return uaDelegate;
		}
		public void setUaDelegate(UnidadAdministrativaDelegate uaDelegate) {
			this.uaDelegate = uaDelegate;
		}




}
