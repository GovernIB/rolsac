package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;

import es.caib.rolsac.utils.ResultadoBusqueda;


public class SolrPendienteDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -4377464442759993342L;

    private long timeLen = 0L;

    SolrPendienteDelegateI impl;

	private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    }

	public SolrPendienteDelegateI getImpl() {
		return impl;
	}

	public void setImpl(SolrPendienteDelegateI impl) {
		this.impl = impl;
	}

	

    public List<SolrPendiente> getPendientes() throws DelegateException {
    	return impl.getPendientes();
    }
    
    public SolrPendienteJob crearSorlPendienteJob() throws DelegateException {
    	return impl.crearSorlPendienteJob();
    }
    
    public void cerrarSorlPendienteJob(SolrPendienteJob solrpendienteJob) throws DelegateException {
    	 impl.cerrarSorlPendienteJob(solrpendienteJob);
    }

    public Boolean borrarCaducadas() throws DelegateException {

        return impl.borrarCaducadas();
    }

    public Boolean grabarSolrPendiente(String tipo, Long idElemento, Long accion) throws DelegateException {

        return impl.grabarSolrPendiente( tipo, idElemento,  accion);
    }

	public ResultadoBusqueda getPendientes(int pagina, int resultados) throws DelegateException{
		return impl.getPendientes(pagina, resultados);
	}

	public List<SolrPendienteJob> getListJobs(int cuantos) throws DelegateException{
		return impl.getListJobs(cuantos);
	}

	/**
	 * Comprueba si hay algun job pendiente.
	 */
	public boolean checkJobsActivos() throws DelegateException {
		return impl.checkJobsActivos();
	}
    


}
