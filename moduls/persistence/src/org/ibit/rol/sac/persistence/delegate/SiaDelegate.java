package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.persistence.util.FiltroSia;


public class SiaDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -437746444276565342L;


    SiaDelegateI impl;

	

	public SiaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(SiaDelegateI impl) {
		this.impl = impl;
	}


	public void enviarTodos() throws DelegateException {
        impl.enviarTodos();
    }
    
	public void enviarPendientes() throws DelegateException {
        impl.enviarPendientes();
    }

}
