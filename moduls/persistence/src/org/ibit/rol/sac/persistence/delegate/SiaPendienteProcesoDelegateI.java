package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.util.FiltroSia;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface SiaPendienteProcesoDelegateI {

	public List<SiaPendiente> getSiaPendientesEnviar() throws DelegateException;
	
	public ResultadoBusqueda getSiaPendientes(final FiltroSia filtro) throws DelegateException;
	
	public ResultadoBusqueda getSiaProceso(final FiltroSia filtro) throws DelegateException;
		
	public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente, ProcedimientoLocal procedimiento) throws DelegateException;
	
	public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException;
	
	public void borrarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException;

	public boolean checkJobsActivos() throws DelegateException;

	public SiaJob crearSiaJob(String tipo)  throws DelegateException;

	public void actualizarSiaJob(SiaJob siaJob)  throws DelegateException;
	
	public void cerrarSiaJob(Long idSiaJob) throws DelegateException;

	public Boolean cerrarJobs() throws DelegateException;
	
	public void actualizarProcedimiento(ProcedimientoLocal proc, SiaResultado resultado) throws DelegateException;
	
	public ResultadoBusqueda getSiaUAs(int pagina, int cuantos, final String orden, final String ordenAsc) throws DelegateException;

	public void grabarSiaUA(SiaUA siaUA) throws DelegateException;

	public SiaUA obtenerSiaUA(Long id) throws DelegateException;

	public SiaUA obtenerSiaUA(UnidadAdministrativa ua) throws DelegateException;
	
	public void borrarSiaUA(Long id) throws DelegateException;

}