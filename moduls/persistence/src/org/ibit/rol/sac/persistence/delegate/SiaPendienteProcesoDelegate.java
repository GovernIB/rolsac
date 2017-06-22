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


public class SiaPendienteProcesoDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -4377464442759993342L;

    private long timeLen = 0L;

    SiaPendienteProcesoDelegateI impl;

	private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    }

	public SiaPendienteProcesoDelegateI getImpl() {
		return impl;
	}

	public void setImpl(SiaPendienteProcesoDelegateI impl) {
		this.impl = impl;
	}

	
    
    public List<SiaPendiente> getSiaPendientesEnviar() throws DelegateException {
        return impl.getSiaPendientesEnviar();
    }
    
    public ResultadoBusqueda getSiaPendientes(final FiltroSia filtro) throws DelegateException {
        return impl.getSiaPendientes(filtro);
    }
    
    public ResultadoBusqueda getSiaProceso(final FiltroSia filtro) throws DelegateException {
        return impl.getSiaProceso(filtro);
    }
    
    public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente, ProcedimientoLocal procedimiento) throws DelegateException {
        return impl.generarSiaPendiente(siaPendiente, procedimiento);
    }
    
    public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException {
        return impl.actualizarSiaPendiente(siaPendiente);
    }

    public void borrarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException {
        impl.borrarSiaPendiente(siaPendiente);
    }

    public boolean checkJobsActivos() throws Exception, DelegateException{
		return impl.checkJobsActivos();
	}

	public SiaJob crearSiaJob(String tipo) throws DelegateException{
		return impl.crearSiaJob(tipo);
	}
	
	public void actualizarSiaJob(SiaJob siaJob) throws DelegateException{
		impl.actualizarSiaJob(siaJob);
	}

	public void cerrarSiaJob(Long idSiaJob) throws DelegateException{
		impl.cerrarSiaJob(idSiaJob);
	}

	public Boolean cerrarJobs() throws DelegateException{
		return impl.cerrarJobs();
	}
	
	public void actualizarProcedimiento(ProcedimientoLocal proc, SiaResultado resultado) throws DelegateException{
		impl.actualizarProcedimiento(proc, resultado);
	} 

	public ResultadoBusqueda getSiaUAs(final int pagina,final int cuantos, final String orden, final String ordenAsc) throws DelegateException {
		return impl.getSiaUAs(pagina, cuantos, orden, ordenAsc);
	}

	public void grabarSiaUA(SiaUA siaUA)  throws DelegateException {
		impl.grabarSiaUA(siaUA);
	}

	public SiaUA obtenerSiaUA(UnidadAdministrativa ua) throws DelegateException {
		return impl.obtenerSiaUA(ua);
	}
	
	public SiaUA obtenerSiaUA(final Long id)  throws DelegateException {
		return impl.obtenerSiaUA(id);
	}

	public void borrarSiaUA(Long id) throws DelegateException {
		impl.borrarSiaUA(id);
	}

	public void borrarSiaPendientes() throws DelegateException {
		impl.borrarSiaPendientes();
	}

	
	
}
