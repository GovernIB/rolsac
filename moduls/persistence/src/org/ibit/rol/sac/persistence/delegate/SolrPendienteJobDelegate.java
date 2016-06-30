package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;


public class SolrPendienteJobDelegate implements StatelessDelegate {

	private static final long serialVersionUID = -4377464442759993342L;

    private long timeLen = 0L;

    SolrPendienteJobDelegateI impl;

	private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    }

	public SolrPendienteJobDelegateI getImpl() {

		return impl;
	}

	public void setImpl(SolrPendienteJobDelegateI impl) {

		this.impl = impl;
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, final FichaDelegate delegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
    public void indexarPendiente(final SolrIndexer solrIndexer, final ProcedimientoDelegate delegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
	public void indexarPendiente(final SolrIndexer solrIndexer, DocumentoDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, NormativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, TramiteDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, UnidadAdministrativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		impl.indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
	}
	
	public void actualizarJob(final SolrPendienteJob solrpendienteJob) throws DelegateException {
        impl.actualizarJob(solrpendienteJob);
    }

    public void indexarPendiente(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria enumCategoria) throws DelegateException {

        impl.indexarPendiente(solrIndexer, idElemento, enumCategoria);
    }

    public SolrPendienteResultado indexarPendiente(final SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
        return impl.indexarPendiente(solrIndexer, solrpendiente);
    }
    
    public void resolverPendiente(final SolrPendiente solrpendiente, final SolrPendienteResultado solrPendienteResultado) throws DelegateException {
    	impl.resolverPendiente(solrpendiente, solrPendienteResultado);
    }
  }
