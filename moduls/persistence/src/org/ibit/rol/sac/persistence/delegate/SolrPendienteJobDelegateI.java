package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;


public interface SolrPendienteJobDelegateI {

	public void indexarPendiente(final SolrIndexer solrIndexer, final FichaDelegate fichaDelegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;
    public void indexarPendiente(final SolrIndexer solrIndexer, final ProcedimientoDelegate fichaDelegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;
	public void indexarPendiente(final SolrIndexer solrIndexer, final DocumentoDelegate docuDelegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;
	public void indexarPendiente(final SolrIndexer solrIndexer, final NormativaDelegate normativaDelegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;
	public void indexarPendiente(final SolrIndexer solrIndexer, final TramiteDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;
	public void indexarPendiente(final SolrIndexer solrIndexer, final UnidadAdministrativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException;

	public void actualizarJob(SolrPendienteJob solrpendienteJob) throws DelegateException;	
	
	public void indexarPendiente(SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria enumCategoria) throws DelegateException;
    public SolrPendienteResultado indexarPendiente(SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException;
	public void resolverPendiente(SolrPendiente solrpendiente, SolrPendienteResultado solrPendienteResultado)  throws DelegateException;
		
}
