package org.ibit.rol.sac.back.action.contenido.procedimiento;

import org.apache.struts.action.ActionForward;
import org.ibit.rol.sac.back.action.contenido.common.ActionParameters;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;

public class RelacionarMateriaAction extends org.ibit.rol.sac.back.action.contenido.common.RelacionarMateriaAction {

   
  
    protected boolean faltanParametres(ActionParameters params) {
		return params.getParameter("procedimiento") == null || params.getParameter("materia") == null;
	}
  
	
	protected void desrelacionarMateria(ActionParameters actionParams) throws DelegateException {
		getProcedimientoDelegate().eliminarMateria(obtenirIdMateria(actionParams), obtenirIdInformacion(actionParams));
	}
 
	protected void relacionarMateria(ActionParameters params) throws DelegateException {
		getProcedimientoDelegate().anyadirMateria(obtenirIdMateria(params),obtenirIdInformacion(params));
	}

	protected void eliminarMateriaSinClassificar(ActionParameters params) throws DelegateException {
		getProcedimientoDelegate().eliminarMateria(obtenirIdMateriaSinClassificar(),obtenirIdInformacion(params));
	}
	
	protected void afegirMateriaSinClassificar(ActionParameters actionParams) throws DelegateException {
		getProcedimientoDelegate().anyadirMateria(obtenirIdMateriaSinClassificar(),obtenirIdInformacion(actionParams));
	}


	protected boolean informacionNoEstaClassificada(ActionParameters params) throws DelegateException {
		ProcedimientoLocal procedimiento = getProcedimientoDelegate().obtenerProcedimiento(obtenirIdInformacion(params));
		return !procedimiento.estaClasificado();
	}

	@Override
	protected Long obtenirIdInformacion(ActionParameters params) {
		return new Long(params.getParameter("procedimiento"));
	}
	


	//getters and setters
	  
    ProcedimientoDelegate procedimientoDelegate;


	public ProcedimientoDelegate getProcedimientoDelegate() {
		if(null==procedimientoDelegate) 
			procedimientoDelegate = DelegateUtil.getProcedimientoDelegate();
		return procedimientoDelegate;
	}

	public void setProcedimientoDelegate(ProcedimientoDelegate procedimientoDelegate) {
		this.procedimientoDelegate = procedimientoDelegate;
	}

	
	
	
}
