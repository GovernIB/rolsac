package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.persistence.util.FiltroSia;


public interface SiaPendienteProcesoDelegateI {

	public List<SiaPendiente> getSiaPendientesEnviar() throws DelegateException;
	
	public List<SiaPendiente> getSiaPendientes(final FiltroSia filtro) throws DelegateException;
	
	public List<SiaJob> getSiaProceso(final FiltroSia filtro) throws DelegateException;
		
	public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException;
	
	public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException;
	
	public void borrarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException;

	public boolean checkJobsActivos() throws DelegateException;

	public SiaJob crearSiaJob(String tipo)  throws DelegateException;

	public void actualizarSiaJob(SiaJob siaJob)  throws DelegateException;
	
	public void cerrarSiaJob(Long idSiaJob) throws DelegateException;

	public Boolean cerrarJobs() throws DelegateException;

}
