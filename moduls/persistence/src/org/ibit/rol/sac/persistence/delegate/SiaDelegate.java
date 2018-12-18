package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.ws.SiaResultado;



public class SiaDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -437746444276565342L;


    SiaDelegateI impl;

	

	public SiaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(SiaDelegateI impl) {
		this.impl = impl;
	}


	public void enviarTodos(SiaJob siaJob) throws DelegateException {
        impl.enviarTodos(siaJob);
    }
    
	public void enviarPendientes(SiaJob siaJob) throws DelegateException {
        impl.enviarPendientes(siaJob);
    }

	public void info(SiaJob siaJob)  throws DelegateException {
        impl.info(siaJob);
    }

	public void revisarProcedimientosPorTiempo(SiaJob siaJob) throws DelegateException {
        impl.revisarProcedimientosPorTiempo(siaJob);
    }

	public SiaResultado enviarProcedimientoNoActivo(ProcedimientoLocal proc) throws DelegateException {
		return impl.enviarProcedimientoNoActivo(proc);
	}
	
	public SiaResultado enviarServicioNoActivo(Servicio servicio) throws DelegateException {
		return impl.enviarServicioNoActivo(servicio);
	}
}
