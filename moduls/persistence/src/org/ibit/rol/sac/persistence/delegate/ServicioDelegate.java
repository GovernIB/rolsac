package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validable;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.criteria.BuscadorServicioCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.ServicioFacade;
import org.ibit.rol.sac.persistence.intf.ServicioFacadeHome;
import org.ibit.rol.sac.persistence.util.ServicioFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;

/**
 * Business delegate para manipular servicios.
 */
public class ServicioDelegate implements StatelessDelegate {

	private static final long serialVersionUID = 1L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#grabarServicio(org.
	 * ibit.rol.sac.model.Servicio, java.lang.Long)
	 */
	public Long grabarServicio(final Servicio servicio, final Long idUA) throws DelegateException {
		try {
			return getFacade().grabarServicio(servicio, idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#reordenarDocumentos(
	 * java.lang.Long, java.util.List)
	 */
	public void reordenarDocumentos(final Long idServicio, final List<Long> idDocumentos) throws DelegateException {
		try {
			getFacade().reordenarDocumentos(idServicio, idDocumentos);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#obtenerServicio(java.
	 * lang.Long)
	 */
	public Servicio obtenerServicio(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerServicio(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * obtenerServicioParaSolr(java.lang.Long)
	 */
	public Servicio obtenerServicioParaSolr(final Long id, final Session iSession) throws DelegateException {
		try {
			return getFacade().obtenerServicioParaSolr(id, iSession);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * obtenerServicioNewBack(java.lang.Long)
	 */
	public Servicio obtenerServicioNewBack(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerServicioNewBack(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscarServicios(java.
	 * util.Map, java.util.Map)
	 */
	public List buscarServicios(final Map param, final Map trad) throws DelegateException {
		try {
			return getFacade().buscarServicios(param, trad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#buscadorServicios(
	 * java.util.Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa,
	 * boolean, boolean)
	 */
	public ResultadoBusqueda buscadorServicios(final Map parametros, final Map traduccion,
			final UnidadAdministrativa ua, final boolean uaFilles, final boolean uaMeves, final Long materia,
			final Long fetVital, final Long publicObjectiu, final String pagina, final String resultats,
			final int visible, final String en_plazo, final String telematico) throws DelegateException {
		try {
			return getFacade().buscadorServicios(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital,
					publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * buscarServiciosMateria(java.lang.Long)
	 */
	public List buscarServiciosMateria(final Long id) throws DelegateException {
		try {
			return getFacade().buscarServiciosMateria(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#borrarServicio(java.
	 * lang.Long)
	 */
	public void borrarServicio(final Long id) throws DelegateException {
		try {
			getFacade().borrarServicio(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#listarServiciosUA(
	 * java.lang.Long)
	 */
	public List listarServiciosUA(final Long id) throws DelegateException {
		try {
			return getFacade().listarServiciosUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public boolean publico(final Servicio servicio) {
		final Date now = new Date();
		final boolean noCaducado = ((servicio.getFechaDespublicacion() == null)
				|| servicio.getFechaDespublicacion().after(now));
		final boolean publicado = ((servicio.getFechaPublicacion() == null)
				|| servicio.getFechaPublicacion().before(now));
		return this.visible(servicio) && noCaducado && publicado;
	}

	protected boolean visible(final Validable validable) {
		return (validable.getValidacion().equals(Validacion.PUBLICA)
				|| validable.getValidacion().equals(Validacion.RESERVA));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * listarServiciosPublicosHechoVital(java.lang.Long)
	 */
	public List listarServiciosPublicosHechoVital(final Long id) throws DelegateException {
		try {
			return getFacade().listarServiciosPublicosHechoVital(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* PORMAD */
	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * listarServiciosPublicosUAHVMateria(java.lang.Long, java.lang.String[],
	 * java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	public List<Long> listarIdsServiciosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		try {
			return getFacade().listarIdsServiciosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#consultarServicio(
	 * java.lang.Long)
	 */
	public Servicio consultarServicio(final Long id) throws DelegateException {
		try {
			return getFacade().consultarServicio(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * buscarServiciosActivos(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.util.Date)
	 */
	public int buscarServiciosActivos(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarServiciosActivos(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.ServicioDelegateI#
	 * buscarServiciosCaducados(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.util.Date)
	 */
	public int buscarServiciosCaducados(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarServiciosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Servicio> listarServiciosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		try {
			return getFacade().listarServiciosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda buscadorServicios(final BuscadorServicioCriteria bc) throws DelegateException {
		try {
			return getFacade().buscadorServicios(bc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public boolean isServicioConEstadoPublicacionPublica(final Long idServicio) throws DelegateException {
		try {
			return getFacade().isServicioConEstadoPublicacionPublica(idServicio);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		try {
			return getFacade().indexarSolr(solrIndexer, solrPendiente);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) throws DelegateException {
		try {
			return getFacade().indexarSolr(solrIndexer, idElemento, categoria);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

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

	private ServicioFacade getFacade() throws RemoteException {
		return (ServicioFacade) facadeHandle.getEJBObject();
	}

	protected ServicioDelegate() throws DelegateException {
		try {
			final ServicioFacadeHome home = ServicioFacadeUtil.getHome();
			final ServicioFacade remote = home.create();
			facadeHandle = remote.getHandle();
		} catch (final NamingException e) {
			throw new DelegateException(e);
		} catch (final CreateException e) {
			throw new DelegateException(e);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Long> buscarIdsServicios() throws DelegateException {
		try {
			return getFacade().buscarIdsServicios();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Long> listarServiciosOrganoResolutori(final Long idOrganoResolutori) throws DelegateException {
		try {
			return getFacade().listarServiciosOrganoResolutori(idOrganoResolutori);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Servicio> listarServiciosNormativa(final Long idElemento) throws DelegateException {
		try {
			return getFacade().listarServiciosNormativa(idElemento);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public void actualizarServicio(final Servicio proc) throws DelegateException {
		try {
			getFacade().actualizarServicio(proc);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public List<Long> getServiciosEstadoSIAAlterado() throws DelegateException {
		try {
			return getFacade().getServiciosEstadoSIAAlterado();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/**
	 * Devuelve true si alguna normativa esta derogada.
	 */
	public boolean isNormativaDerogada(final Long id) throws DelegateException {
		try {
			return getFacade().isNormativaDerogada(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public ResultadoBusqueda consultaServicios(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaServicios(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public String getEnlaceTelematico(final Long idServicio, final String lang) throws DelegateException {
		try {
			return getFacade().getEnlaceTelematico(idServicio, lang);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public Archivo obtenerServInfoAdicional(final Long id, final String idioma) throws DelegateException {
		try {
			return getFacade().obtenerServInfoAdicional(id, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}
}
