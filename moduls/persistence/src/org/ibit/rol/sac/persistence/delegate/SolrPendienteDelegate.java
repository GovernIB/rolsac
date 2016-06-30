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

    public void crearJob(String tipoIndexacion) throws DelegateException {
         impl.crearJob(tipoIndexacion);
    }
    
    public void indexarTodoFicha(final SolrPendienteJob solrPendienteJob) throws DelegateException {
        impl.indexarTodoFicha(solrPendienteJob);
    }
    
    public void indexarTodoProcedimiento(final SolrPendienteJob solrPendienteJob) throws DelegateException {
        impl.indexarTodoProcedimiento(solrPendienteJob);
    }
    
    public void indexarTodoNormativa(final SolrPendienteJob solrPendienteJob) throws DelegateException {
        impl.indexarTodoNormativa(solrPendienteJob);
    }
    
    public void indexarTodoTramite(final SolrPendienteJob solrPendienteJob) throws DelegateException {
        impl.indexarTodoTramite(solrPendienteJob);
    }
    
    public void indexarTodoUA(final SolrPendienteJob solrPendienteJob) throws DelegateException {
        impl.indexarTodoUA(solrPendienteJob);
    }

    public Boolean indexarPendientes() throws DelegateException {
        return impl.indexarPendientes();
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
    


}
