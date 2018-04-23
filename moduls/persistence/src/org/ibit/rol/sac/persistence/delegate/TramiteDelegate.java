package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.remote.vuds.ActualizacionVudsException;
import org.ibit.rol.sac.persistence.remote.vuds.ValidateVudsException;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate para manipular Tramites.
 */
public class TramiteDelegate  {

	TramiteDelegateI impl;

	public TramiteDelegateI getImpl() {
		return impl;
	}

	public void setImpl(TramiteDelegateI impl) {
		this.impl = impl;
	}

   public boolean autorizaCrearTramite(Long idProcedimiento) throws DelegateException  {
    	return impl.autorizaCrearTramite(idProcedimiento); 
    }
    
        
    public boolean autorizaModificarTramite(Long idTramite) throws DelegateException {
       	return impl.autorizaModificarTramite(idTramite);
    }   	

	public void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException {
		impl.actualizarOrdenDocs(map, tid);
	}
	
	public void actualizarOrdenTasas(Map<String, String[]> map, long tid)
		throws DelegateException {
		impl.actualizarOrdenTasas(map, tid);
	}
	
	public void borrarDocument(Long id) throws DelegateException {
		impl.borrarDocument(id);
	}
	
	public void borrarDocumentos(Tramite tramite, List<DocumentTramit> documentos) throws DelegateException {
		impl.borrarDocumentos(tramite, documentos);
	}
	
	public void borrarTaxa(Long id) throws DelegateException {
		impl.borrarTaxa(id);
	}

	public void borrarTramite(Long id, Long idProc) throws DelegateException {
		impl.borrarTramite(id, idProc);
	}

	public Long grabarDocument(DocumentTramit doc, Long tid)
			throws DelegateException {
		return impl.grabarDocument(doc, tid);
	}

	public Long grabarTaxa(Taxa taxa, Long tid) throws DelegateException {
		return impl.grabarTaxa(taxa, tid);
	}

	public Long grabarTramite(Tramite tramite, Long idOC)
			throws DelegateException, ValidateVudsException, ActualizacionVudsException {
		return impl.grabarTramite(tramite, idOC);
	}

	public Tramite obtenerTramite(Long id) throws DelegateException {
		return impl.obtenerTramite(id);
	}

	public DocumentTramit obtenirDocument(Long docId) throws DelegateException {
		return impl.obtenirDocument(docId);
	}

	public Taxa obtenirTaxa(Long docId) throws DelegateException {
		return impl.obtenirTaxa(docId);
	}

	public SolrPendienteResultado indexarSolr(SolrIndexer solrIndexer, SolrPendiente solrPendiente) throws DelegateException {
    	return impl.indexarSolr(solrIndexer, solrPendiente);        
    }
	
	public SolrPendienteResultado indexarSolr(SolrIndexer solrIndexer, Long idAplicacion, EnumCategoria categoria) throws DelegateException {
    	return impl.indexarSolr(solrIndexer, idAplicacion, categoria);        
    }
    
	public SolrPendienteResultado indexarDocSolr(SolrIndexer solrIndexer, Long idAplicacion, EnumCategoria categoria) throws DelegateException {
    	return impl.indexarDocSolr(solrIndexer, idAplicacion, categoria);        
    }
	
	
    public SolrPendienteResultado desindexarSolr(SolrIndexer solrIndexer,  SolrPendiente solrPendiente) throws DelegateException {
    	return impl.desindexarSolr(solrIndexer, solrPendiente);    
    }

	public List<Long> buscarIdsTramites() throws DelegateException{
		return impl.buscarIdsTramites();
	}

	public ResultadoBusqueda consultaFormularios(FiltroGenerico filtro) throws DelegateException {
		return impl.consultaFormularios(filtro);
	}

}
