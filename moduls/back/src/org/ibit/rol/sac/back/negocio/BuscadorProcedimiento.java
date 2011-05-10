package org.ibit.rol.sac.back.negocio;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;

public class BuscadorProcedimiento {

	private ProcedimientoDelegate procedimientoDelegate;


	public ProcedimientoLocal seleccionarProcedimiento(Long id) throws DelegateException {
		return getProcedimientoDelegate().obtenerProcedimiento(id);
	}


	public ProcedimientoDelegate getProcedimientoDelegate() {
		if(null==procedimientoDelegate) procedimientoDelegate=DelegateUtil.getProcedimientoDelegate();
		return procedimientoDelegate;
	}

	public void setProcedimientoDelegate(ProcedimientoDelegate procedimientoDelegate) {
		this.procedimientoDelegate = procedimientoDelegate;
	}

}
