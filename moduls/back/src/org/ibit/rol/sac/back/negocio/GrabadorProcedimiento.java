package org.ibit.rol.sac.back.negocio;

import org.ibit.rol.sac.back.form.TraDynaValidatorForm;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;

public class GrabadorProcedimiento extends GrabadorBase {

	public <T extends org.ibit.rol.sac.model.Classificable> T grabar(TraDynaValidatorForm dForm, T t) throws DelegateException {
		ProcedimientoLocal procedimiento = (ProcedimientoLocal)t; 
		Long id = getProcedimientoDelegate().grabarProcedimiento(procedimiento, obtenerUA(dForm));
		procedimiento.setId(id);
		return t;
	}

	private Long obtenerUA(TraDynaValidatorForm dForm) {
		return (Long) dForm.get("idUA");
	}

	private ProcedimientoDelegate procedimientoDelegate;

	public ProcedimientoDelegate getProcedimientoDelegate() {
		if(null==procedimientoDelegate) procedimientoDelegate=DelegateUtil.getProcedimientoDelegate();
		return procedimientoDelegate;
	}

	public void setProcedimientoDelegate(ProcedimientoDelegate procedimientoDelegate) {
		this.procedimientoDelegate = procedimientoDelegate;
	}

	
}
