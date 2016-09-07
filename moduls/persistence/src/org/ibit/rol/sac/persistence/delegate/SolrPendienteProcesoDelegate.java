package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;

import es.caib.rolsac.utils.ResultadoBusqueda;


public class SolrPendienteProcesoDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -4377464442759993342L;

    private long timeLen = 0L;

    SolrPendienteProcesoDelegateI impl;

	private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    }

	public SolrPendienteProcesoDelegateI getImpl() {
		return impl;
	}

	public void setImpl(SolrPendienteProcesoDelegateI impl) {
		this.impl = impl;
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


}
