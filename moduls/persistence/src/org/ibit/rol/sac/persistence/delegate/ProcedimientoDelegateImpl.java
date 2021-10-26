package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacade;
import org.ibit.rol.sac.persistence.intf.ProcedimientoFacadeHome;
import org.ibit.rol.sac.persistence.util.ProcedimientoFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;

/**
 * Business delegate para manipular procedimientos.
 */
public class ProcedimientoDelegateImpl implements StatelessDelegate, ProcedimientoDelegateI {

	private static final long serialVersionUID = 1L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * grabarProcedimiento(org.ibit.rol.sac.model.ProcedimientoLocal,
	 * java.lang.Long)
	 */
	@Override
	public Long grabarProcedimiento(final ProcedimientoLocal procedimiento, final Long idUA,
			final ProcedimientoMensaje procMensaje, final MensajeEmail mensajeEmail) throws DelegateException {
		try {
			return getFacade().grabarProcedimiento(procedimiento, idUA, procMensaje, mensajeEmail);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public Long grabarProcedimientoConTramites(final ProcedimientoLocal procedimiento, final Long idUA,
			final List listaTramitesParaBorrar, final List listaIdsTramitesParaActualizar,
			final ProcedimientoMensaje procMensaje, final MensajeEmail mensajeEmail) throws DelegateException {
		try {
			return getFacade().grabarProcedimientoConTramites(procedimiento, idUA, listaTramitesParaBorrar,
					listaIdsTramitesParaActualizar, procMensaje, mensajeEmail);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientos()
	 */
	@Override
	public List listarProcedimientos() throws DelegateException {
		try {
			return getFacade().listarProcedimientos();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * reordenarDocumentos(java.lang.Long, java.util.List)
	 */
	@Override
	public void reordenarDocumentos(final Long idProcedimiento, final List<Long> idDocumentos,
			final ProcedimientoMensaje procedimientoMensaje) throws DelegateException {
		try {
			getFacade().reordenarDocumentos(idProcedimiento, idDocumentos, procedimientoMensaje);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * obtenerProcedimiento(java.lang.Long)
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimiento(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerProcedimiento(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * obtenerProcedimientoParaSolr(java.lang.Long)
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimientoParaSolr(final Long id, final Session iSession)
			throws DelegateException {
		try {
			return getFacade().obtenerProcedimientoParaSolr(id, iSession);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * obtenerProcedimientoNewBack(java.lang.Long)
	 */
	@Override
	public ProcedimientoLocal obtenerProcedimientoNewBack(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerProcedimientoNewBack(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * buscarProcedimientos(java.util.Map, java.util.Map)
	 */
	@Override
	public List buscarProcedimientos(final Map param, final Map trad) throws DelegateException {
		try {
			return getFacade().buscarProcedimientos(param, trad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * buscadorProcedimientos(java.util.Map, java.util.Map,
	 * org.ibit.rol.sac.model.UnidadAdministrativa, boolean, boolean)
	 */
	@Override
	public ResultadoBusqueda buscadorProcedimientos(final Map parametros, final Map traduccion,
			final UnidadAdministrativa ua, final boolean uaFilles, final boolean uaMeves, final Long materia,
			final Long fetVital, final Long publicObjectiu, final String pagina, final String resultats,
			final int visible, final String en_plazo, final String telematico) throws DelegateException {
		try {
			return getFacade().buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital,
					publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * buscarProcedimientosMateria(java.lang.Long)
	 */
	@Override
	public List buscarProcedimientosMateria(final Long id) throws DelegateException {
		try {
			return getFacade().buscarProcedimientosMateria(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#anyadirTramite(
	 * java.lang.Long, java.lang.Long)
	 */
	@Override
	public void anyadirTramite(final Long tramite_id, final Long proc_id) throws DelegateException {
		try {
			getFacade().anyadirTramite(tramite_id, proc_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#eliminarTramite(
	 * java.lang.Long, java.lang.Long)
	 */
	@Override
	public void eliminarTramite(final Long tramite_id, final Long proc_id) throws DelegateException {
		try {
			getFacade().eliminarTramite(tramite_id, proc_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * borrarProcedimiento(java.lang.Long)
	 */
	@Override
	public void borrarProcedimiento(final Long id) throws DelegateException {
		try {
			getFacade().borrarProcedimiento(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosUA(java.lang.Long)
	 */
	@Override
	public List listarProcedimientosUA(final Long id) throws DelegateException {
		try {
			return getFacade().listarProcedimientosUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosUO(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public List listarProcedimientosUO(final Long id, final Integer conse) throws DelegateException {
		try {
			return getFacade().listarProcedimientosUO(id, conse);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosPublicosUA(java.lang.Long)
	 */
	@Override
	public List listarProcedimientosPublicosUA(final Long id) throws DelegateException {
		try {
			return getFacade().listarProcedimientosPublicosUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	private boolean publico(final ProcedimientoLocal proc) {
		final Date now = new Date();
		final boolean noCaducado = ((proc.getFechaCaducidad() == null) || proc.getFechaCaducidad().after(now));
		final boolean publicado = ((proc.getFechaPublicacion() == null) || proc.getFechaPublicacion().before(now));
		return this.visible(proc) && noCaducado && publicado;
	}

	protected boolean visible(final Validable validable) {
		return (validable.getValidacion().equals(Validacion.PUBLICA)
				|| validable.getValidacion().equals(Validacion.RESERVA));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosPublicosUA(java.lang.Long)
	 */
	@Override
	public List listarProcedimientosPublicos() throws DelegateException {
		final List procedimientosPublicos = new ArrayList();
		try {
			final List todosProcedimientos = getFacade().listarProcedimientos();
			for (final Iterator i = todosProcedimientos.iterator(); i.hasNext();) {
				final ProcedimientoLocal pl = (ProcedimientoLocal) i.next();
				if (this.publico(pl)) {
					procedimientosPublicos.add(pl);
				}
			}
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
		return procedimientosPublicos;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosPublicosHechoVital(java.lang.Long)
	 */
	@Override
	public List listarProcedimientosPublicosHechoVital(final Long id) throws DelegateException {
		try {
			return getFacade().listarProcedimientosPublicosHechoVital(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* PORMAD */
	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * listarProcedimientosPublicosUAHVMateria(java.lang.Long, java.lang.String[],
	 * java.lang.String[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		try {
			return getFacade().listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * consultarProcedimiento(java.lang.Long)
	 */
	@Override
	public ProcedimientoLocal consultarProcedimiento(final Long id) throws DelegateException {
		try {
			return getFacade().consultarProcedimiento(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void actualizarOrdenTramites(final ArrayList<Long> tramitesId) throws DelegateException {
		try {
			getFacade().actualizarOrdenTramites(tramitesId);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean existeOtroTramiteInicioProcedimiento(final Long procId, final Long tramiteId)
			throws DelegateException {
		try {
			return getFacade().existeOtroTramiteInicioProcedimiento(procId, tramiteId);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * buscarProcedimientosActivos(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.util.Date)
	 */
	@Override
	public int buscarProcedimientosActivos(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarProcedimientosActivos(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegateI#
	 * buscarProcedimientosCaducados(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.util.Date)
	 */
	@Override
	public int buscarProcedimientosCaducados(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarProcedimientosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		try {
			return getFacade().listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda buscadorProcedimientos(final BuscadorProcedimientoCriteria bc) throws DelegateException {
		try {
			return getFacade().buscadorProcedimientos(bc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean isProcedimientoConEstadoPublicacionPublica(final Long idProcedimiento) throws DelegateException {
		try {
			return getFacade().isProcedimientoConEstadoPublicacionPublica(idProcedimiento);
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
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) throws DelegateException {
		try {
			return getFacade().indexarSolr(solrIndexer, idElemento, categoria);
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

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private ProcedimientoFacade getFacade() throws RemoteException {
		return (ProcedimientoFacade) facadeHandle.getEJBObject();
	}

	protected ProcedimientoDelegateImpl() throws DelegateException {
		try {
			final ProcedimientoFacadeHome home = ProcedimientoFacadeUtil.getHome();
			final ProcedimientoFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<Long> buscarIdsProcedimientos() throws DelegateException {
		try {
			return getFacade().buscarIdsProcedimientos();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<Long> listarProcedimientosOrganoResolutori(final Long idOrganoResolutori) throws DelegateException {
		try {
			return getFacade().listarProcedimientosOrganoResolutori(idOrganoResolutori);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<ProcedimientoLocal> listarProcedimientosNormativa(final Long idElemento) throws DelegateException {
		try {
			return getFacade().listarProcedimientosNormativa(idElemento);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void actualizarProcedimiento(final ProcedimientoLocal proc) throws DelegateException {
		try {
			getFacade().actualizarProcedimiento(proc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<Long> getProcedimientosEstadoSIAAlterado() throws DelegateException {
		try {
			return getFacade().getProcedimientosEstadoSIAAlterado();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * Devuelve true si alguna normativa esta derogada.
	 */
	@Override
	public boolean isNormativaDerogada(final Long id) throws DelegateException {
		try {
			return getFacade().isNormativaDerogada(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * Devuelve true si alguna normativa no es valida.
	 */
	@Override
	public boolean isNormativaValidas(final Long id) throws DelegateException {
		try {
			return getFacade().isNormativaValidas(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * Devuelve true si alguna normativa no es valida.
	 */
	@Override
	public ResultadoBusqueda consultaProcedimientos(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaProcedimientos(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void grabarArchivos(final Long idProcedimiento, final Map<String, TraduccionProcedimientoLocal> traducciones,
			final List<Long> archivosAborrar) throws DelegateException {
		try {
			getFacade().grabarArchivos(idProcedimiento, traducciones, archivosAborrar);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public Archivo obtenerProcInfoAdicional(final Long id, final String idioma) throws DelegateException {
		try {
			return getFacade().obtenerProcInfoAdicional(id, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean checkInfoAdicional(final Long id) throws DelegateException {
		try {
			return getFacade().checkInfoAdicional(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean tieneNormativas(final Long id) throws DelegateException {
		try {
			return getFacade().tieneNormativas(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String obtenerNombreProcedimiento(final Long idProc, final boolean catalan) throws DelegateException {
		try {
			return getFacade().obtenerNombreProcedimiento(idProc, catalan);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
