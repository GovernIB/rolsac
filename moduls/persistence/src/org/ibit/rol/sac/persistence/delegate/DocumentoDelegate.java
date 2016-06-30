package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

public class DocumentoDelegate  implements DocumentoDelegateI 
{
	DocumentoDelegateI impl;

	public DocumentoDelegateI getImpl() {
		return impl;
	}
	
	public void setImpl(DocumentoDelegateI impl) {
		this.impl = impl;
    }
	    
	public Long grabarDocumento(Documento documento, Long procedimiento_id, Long ficha_id) throws DelegateException {
		return impl.grabarDocumento(documento,procedimiento_id, ficha_id);        
    }
	
	public Documento obtenerDocumento(Long id) throws DelegateException {
		return impl.obtenerDocumento(id);
    }
	
	public void borrarDocumento(Long id) throws DelegateException {
         impl.borrarDocumento(id);        
    }
	
	public Archivo obtenerArchivoDocumento(Long id, String lang, boolean useDefault) throws DelegateException {
		return impl.obtenerArchivoDocumento(id, lang, useDefault);		
	}
	
	public Archivo obtenerArchivoDocumentoTramite(Long id, String lang, boolean useDefault) throws DelegateException {
		return impl.obtenerArchivoDocumentoTramite(id, lang, useDefault);        
    }
    
	public void actualizarOrdenDocs(Map map) throws DelegateException {
		impl.actualizarOrdenDocs(map);        
    }	

    public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
        return impl.indexarSolr(solrIndexer, solrPendiente);        
    }
    
    public SolrPendienteResultado indexarSolrProcedimientoDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException {
    	return impl.indexarSolrProcedimientoDoc(solrIndexer, idElemento, categoria);        
    }
    
    public SolrPendienteResultado indexarSolrFichaDoc(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException {
    	return impl.indexarSolrFichaDoc(solrIndexer, idElemento, categoria);        
    }
    

    public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
        return impl.desindexarSolr(solrIndexer, solrPendiente);        
    }

	public List<Long> obtenerDocumentosFichaSolr(Long idFicha) throws DelegateException {
		 return impl.obtenerDocumentosFichaSolr(idFicha);        
	}
	
	public List<Long> obtenerDocumentosProcedimientoSolr(Long idProcedimiento) throws DelegateException {
		 return impl.obtenerDocumentosProcedimientoSolr(idProcedimiento);        
	}
}