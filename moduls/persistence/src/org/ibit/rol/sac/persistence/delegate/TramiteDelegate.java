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
public class TramiteDelegate {

	TramiteDelegateI impl;

	public TramiteDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final TramiteDelegateI impl) {
		this.impl = impl;
	}

	public boolean autorizaCrearTramite(final Long idProcedimiento) throws DelegateException {
		return impl.autorizaCrearTramite(idProcedimiento);
	}

	public boolean autorizaModificarTramite(final Long idTramite) throws DelegateException {
		return impl.autorizaModificarTramite(idTramite);
	}

	public void actualizarOrdenDocs(final Map<String, String[]> map, final long tid) throws DelegateException {
		impl.actualizarOrdenDocs(map, tid);
	}

	public void actualizarOrdenTasas(final Map<String, String[]> map, final long tid) throws DelegateException {
		impl.actualizarOrdenTasas(map, tid);
	}

	public void borrarDocument(final Long id) throws DelegateException {
		impl.borrarDocument(id);
	}

	public void borrarDocumentos(final Tramite tramite, final List<DocumentTramit> documentos)
			throws DelegateException {
		impl.borrarDocumentos(tramite, documentos);
	}

	public void borrarTaxa(final Long id) throws DelegateException {
		impl.borrarTaxa(id);
	}

	public void borrarTramite(final Long id, final Long idProc) throws DelegateException {
		impl.borrarTramite(id, idProc);
	}

	public Long grabarDocument(final DocumentTramit doc, final Long tid) throws DelegateException {
		return impl.grabarDocument(doc, tid);
	}

	public Long grabarTaxa(final Taxa taxa, final Long tid) throws DelegateException {
		return impl.grabarTaxa(taxa, tid);
	}

	public Long grabarTramite(final Tramite tramite, final Long idOC)
			throws DelegateException, ValidateVudsException, ActualizacionVudsException {
		return impl.grabarTramite(tramite, idOC);
	}

	public Tramite obtenerTramite(final Long id) throws DelegateException {
		return impl.obtenerTramite(id);
	}

	public DocumentTramit obtenirDocument(final Long docId) throws DelegateException {
		return impl.obtenirDocument(docId);
	}

	public Taxa obtenirTaxa(final Long docId) throws DelegateException {
		return impl.obtenirTaxa(docId);
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.indexarSolr(solrIndexer, solrPendiente);
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idAplicacion,
			final EnumCategoria categoria) throws DelegateException {
		return impl.indexarSolr(solrIndexer, idAplicacion, categoria);
	}

	public SolrPendienteResultado indexarDocSolr(final SolrIndexer solrIndexer, final Long idAplicacion,
			final EnumCategoria categoria) throws DelegateException {
		return impl.indexarDocSolr(solrIndexer, idAplicacion, categoria);
	}

	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.desindexarSolr(solrIndexer, solrPendiente);
	}

	public List<Long> buscarIdsTramites() throws DelegateException {
		return impl.buscarIdsTramites();
	}

	public ResultadoBusqueda consultaFormularios(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaFormularios(filtro);
	}

	public ResultadoBusqueda consultaTramites(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaTramites(filtro);
	}

	public String getEnlaceTelematico(final Long codigo, final String lang) throws DelegateException {
		return impl.getEnlaceTelematico(codigo, lang);
	}

}
