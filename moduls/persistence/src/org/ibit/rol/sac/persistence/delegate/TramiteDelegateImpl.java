package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.TramiteFacade;
import org.ibit.rol.sac.persistence.intf.TramiteFacadeHome;
import org.ibit.rol.sac.persistence.util.TramiteFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing.
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError).
 *
 */

public class TramiteDelegateImpl implements StatelessDelegate, TramiteDelegateI {

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarTramite(org.ibit
	 * .rol.sac.model.Tramite, java.lang.Long)
	 */
	@Override
	public Long grabarTramite(final Tramite tramite, final Long idOC) throws DelegateException {
		try {
			return getFacade().grabarTramite(tramite, idOC);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenerTramite(java.
	 * lang.Long)
	 */
	@Override
	public Tramite obtenerTramite(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerTramite(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarTramite(java.
	 * lang.Long)
	 */
	@Override
	public void borrarTramite(final Long id, final Long idProc) throws DelegateException {
		try {
			getFacade().borrarTramite(id, idProc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private TramiteFacade getFacade() throws RemoteException {
		return (TramiteFacade) facadeHandle.getEJBObject();
	}

	protected TramiteDelegateImpl() throws DelegateException {
		try {
			final TramiteFacadeHome home = TramiteFacadeUtil.getHome();
			final TramiteFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#
	 * afegirDocumentInformatiu(java.lang.Long, java.lang.Long)
	 */

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarDocument(org.
	 * ibit.rol.sac.model.DocumentTramit, java.lang.Long)
	 */
	@Override
	public Long grabarDocument(final DocumentTramit doc, final Long tid) throws DelegateException {
		try {
			return getFacade().grabarDocument(doc, tid);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenirDocument(java.
	 * lang.Long)
	 */
	@Override
	public DocumentTramit obtenirDocument(final Long docId) throws DelegateException {
		try {
			return getFacade().obtenirDocument(docId);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarDocument(java.
	 * lang.Long)
	 */
	@Override
	public void borrarDocument(final Long id) throws DelegateException {
		try {
			getFacade().borrarDocument(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarDocument(java.
	 * lang.Long)
	 */
	@Override
	public void borrarDocumentos(final Tramite tramite, final List<DocumentTramit> documentos)
			throws DelegateException {
		try {
			getFacade().borrarDocumentos(tramite, documentos);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#actualizarOrdenDocs(
	 * java.util.Map, long)
	 */
	@Override
	public void actualizarOrdenDocs(final Map<String, String[]> map, final long tid) throws DelegateException {
		try {
			getFacade().actualizarOrdenDocs(map, tid);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#actualizarOrdenDocs(
	 * java.util.Map, long)
	 */
	@Override
	public void actualizarOrdenTasas(final Map<String, String[]> map, final long tid) throws DelegateException {
		try {
			getFacade().actualizarOrdenTasas(map, tid);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenirTaxa(java.lang.
	 * Long)
	 */
	@Override
	public Taxa obtenirTaxa(final Long docId) throws DelegateException {
		try {
			return getFacade().obtenirTaxa(docId);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarTaxa(org.ibit.
	 * rol.sac.model.Taxa, java.lang.Long)
	 */
	@Override
	public Long grabarTaxa(final Taxa taxa, final Long tid) throws DelegateException {
		try {
			return getFacade().grabarTaxa(taxa, tid);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarTaxa(java.lang.
	 * Long)
	 */
	@Override
	public void borrarTaxa(final Long id) throws DelegateException {
		try {
			getFacade().borrarTaxa(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}

	}

	@Override
	public boolean autorizaCrearTramite(final Long idProcedimiento) throws DelegateException {
		try {
			return getFacade().autorizaCrearTramite(idProcedimiento);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean autorizaModificarTramite(final Long idTramite) throws DelegateException {
		try {
			return getFacade().autorizaModificarTramite(idTramite);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		try {
			return getFacade().indexarSolr(solrIndexer, solrPendiente);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idAplicacion,
			final EnumCategoria categoria) throws DelegateException {
		try {
			return getFacade().indexarSolr(solrIndexer, idAplicacion, categoria);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public SolrPendienteResultado indexarDocSolr(final SolrIndexer solrIndexer, final Long idAplicacion,
			final EnumCategoria categoria) throws DelegateException {
		try {
			return getFacade().indexarDocSolr(solrIndexer, idAplicacion, categoria);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		try {
			return getFacade().desindexarSolr(solrIndexer, solrPendiente);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<Long> buscarIdsTramites() throws DelegateException {
		try {
			return getFacade().buscarIdsTramites();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda consultaFormularios(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaFormularios(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda consultaTramites(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaTramites(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String getEnlaceTelematico(final Long codigo, final String lang) throws DelegateException {
		try {
			return getFacade().getEnlaceTelematico(codigo, lang);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}
}
