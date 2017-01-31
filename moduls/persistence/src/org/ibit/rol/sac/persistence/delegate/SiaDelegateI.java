package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.SiaJob;



public interface SiaDelegateI {

	public void enviarTodos(SiaJob siaJob) throws DelegateException;

	public void enviarPendientes(SiaJob siaJob) throws DelegateException;

	public void info(SiaJob siaJob) throws DelegateException;

	public void revisarProcedimientosPorTiempo(SiaJob siaJob) throws DelegateException;
		
}
