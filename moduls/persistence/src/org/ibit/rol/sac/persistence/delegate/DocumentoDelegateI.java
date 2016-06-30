package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

public interface DocumentoDelegateI
{
	
	public abstract Long grabarDocumento(Documento documento, Long procedimiento_id, Long ficha_id) throws DelegateException;
	
	public abstract Documento obtenerDocumento(Long id) throws DelegateException;
	
	public abstract void borrarDocumento(Long id) throws DelegateException;
	
	public abstract Archivo obtenerArchivoDocumento(Long id, String lang, boolean useDefault) throws DelegateException;
	
	public abstract Archivo obtenerArchivoDocumentoTramite(Long id, String lang, boolean useDefault) throws DelegateException;
    
	public abstract void actualizarOrdenDocs(Map map) throws DelegateException;

    public abstract SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException;
    
    public abstract SolrPendienteResultado indexarSolrProcedimientoDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException;
    
    public abstract SolrPendienteResultado indexarSolrFichaDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException;

    public abstract SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException;

	public abstract List<Long> obtenerDocumentosFichaSolr(Long idFicha) throws DelegateException;
	
	public abstract List<Long> obtenerDocumentosProcedimientoSolr(Long idProcedimiento) throws DelegateException;
}
