package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.ServicioMensaje;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.FichaFacade;
import org.ibit.rol.sac.persistence.intf.FichaFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;

/**
 * Business delegate para manipular fichas.
 */
public class FichaDelegateImpl implements StatelessDelegate, FichaDelegateI {

	private static final long serialVersionUID = 1L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	@Override
	public void actualizaEnlacesFicha(final Long id, final List<Enlace> enlacesNuevos,
			final List<Enlace> enlacesAEliminar) throws DelegateException {
		try {
			getFacade().actualizaEnlacesFicha(id, enlacesNuevos, enlacesAEliminar);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void reordenarDocumentos(final Long idFicha, final List<Long> idDocumentos,
			final ProcedimientoMensaje procedimientoMensaje, final ServicioMensaje servicioMensaje)
			throws DelegateException {
		try {
			getFacade().reordenarDocumentos(idFicha, idDocumentos, procedimientoMensaje, servicioMensaje);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#autorizaCrearFicha(java.
	 * lang.Integer)
	 */
	@Override
	public boolean autorizaCrearFicha(final Integer validacionNormativa) throws DelegateException {
		try {
			return getFacade().autorizaCrearFicha(validacionNormativa);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#autorizaModificarFicha(
	 * java.lang.Long)
	 */
	@Override
	public boolean autorizaModificarFicha(final Long idFicha) throws DelegateException {
		try {
			return getFacade().autorizaModificarFicha(idFicha);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#grabarFicha(org.ibit.rol
	 * .sac.model.Ficha)
	 */
	@Override
	public Long grabarFicha(final Ficha ficha) throws DelegateException {
		try {
			return getFacade().grabarFicha(ficha);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#buscarFichas(java.util.
	 * Map, java.util.Map, org.ibit.rol.sac.model.UnidadAdministrativa, boolean,
	 * boolean)
	 */
	@Override
	public ResultadoBusqueda buscarFichas(final Map parametros, final Map traduccion, final UnidadAdministrativa ua,
			final Long idFetVital, final Long idMateria, final Long idPublic, final boolean uaFilles,
			final boolean uaMeves, final String campoOrdenacion, final String orden, final String pagina,
			final String resultats, final int campoVisible) throws DelegateException {
		try {
			return getFacade().buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles,
					uaMeves, campoOrdenacion, orden, pagina, resultats, campoVisible);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#buscarFichas(java.util.
	 * Map, java.util.String, org.ibit.rol.sac.model.UnidadAdministrativa, boolean)
	 */
	public List buscarFichas(final Map parametros, final String traduccion, final UnidadAdministrativa ua,
			final Long idFetVital, final Long idMateria, final Long idPublic, final boolean uaFilles,
			final boolean uaMeves, final String campoOrdenacion, final String orden) throws DelegateException {
		try {
			return getFacade().buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles,
					uaMeves, campoOrdenacion, orden);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerFicha(java.lang.
	 * Long)
	 */
	@Override
	public Ficha obtenerFicha(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerFicha(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerFichaParaSolr(
	 * java.lang.Long,net.sf.hibernate.Session)
	 */
	@Override
	public Ficha obtenerFichaParaSolr(final Long id, final Session iSession) throws DelegateException {
		try {
			return getFacade().obtenerFichaParaSolr(id, iSession);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerIconoFicha(java.
	 * lang.Long)
	 */
	@Override
	public Archivo obtenerIconoFicha(final Long id, final String idioma) throws DelegateException {
		try {
			return getFacade().obtenerIconoFicha(id, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerImagenFicha(java.
	 * lang.Long)
	 */
	@Override
	public Archivo obtenerImagenFicha(final Long id, final String idioma) throws DelegateException {
		try {
			return getFacade().obtenerImagenFicha(id, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#obtenerBanerFicha(java.
	 * lang.Long)
	 */
	@Override
	public Archivo obtenerBanerFicha(final Long id, final String idioma) throws DelegateException {
		try {
			return getFacade().obtenerBanerFicha(id, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#borrarFicha(java.lang.
	 * Long)
	 */
	@Override
	public void borrarFicha(final Long id) throws DelegateException {
		try {
			getFacade().borrarFicha(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listarFichasSeccionUA(
	 * java.lang.Long, java.lang.String, java.lang.String[], java.lang.String[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Ficha> listarFichasSeccionUA(final Long ua_id, final String codEstSecc, final String[] codEstHV,
			final String[] codEstMat) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUA(ua_id, codEstSecc, codEstHV, codEstMat);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listarFichasSeccionTodas
	 * (java.lang.Long)
	 */
	@Override
	public List listarFichasSeccionTodas(final Long id) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionTodas(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#crearFichaUA2(java.lang.
	 * Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public Long crearFichaUA2(final Long unidad_id, final Long seccion_id, final Long ficha_id)
			throws DelegateException {
		try {
			return getFacade().crearFichaUA2(unidad_id, seccion_id, ficha_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#borrarFichaUA(java.lang.
	 * Long)
	 */
	@Override
	public void borrarFichaUA(final Long id) throws DelegateException {
		try {
			getFacade().borrarFichaUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ibit.rol.sac.persistence.delegate.FichaDelegateI#getContenidos_web()
	 */
	@Override
	public Hashtable getContenidos_web() throws DelegateException {
		try {
			return getFacade().getContenidos_web();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#setContenidos_web(java.
	 * util.Hashtable)
	 */
	@Override
	public void setContenidos_web(final Hashtable contenidos_web) throws DelegateException {
		try {
			getFacade().setContenidos_web(contenidos_web);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ibit.rol.sac.persistence.delegate.FichaDelegateI#listFichasUA(java.lang.
	 * Long)
	 */
	@Override
	public List<FichaUA> listFichasUA(final Long idFicha) throws DelegateException {
		try {
			return getFacade().listFichasUA(idFicha);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public Ficha obtenerFichaPMA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerFichaPMA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public int buscarFichasActivas(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarFichasActivas(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public int buscarFichasCaducadas(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		try {
			return getFacade().buscarFichasCaducadas(listaUnidadAdministrativaId, fechaCaducidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void borrarFichasUAdeFicha(final List<FichaUA> fichasUA) throws DelegateException {
		try {
			getFacade().borrarFichasUAdeFicha(fichasUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public Ficha obtenerFichaDeFichaUA(final Long idFichaUA) throws DelegateException {
		try {
			return getFacade().obtenerFichaDeFichaUA(idFichaUA);
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

	@Override
	public Integer comprobarRelacionFicha(final Long idFitxa) throws DelegateException {
		try {
			return getFacade().comprobarRelacionFicha(idFitxa);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda consultaFichas(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaFichas(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda consultaFichasUA(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaFichasUA(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private FichaFacade getFacade() throws RemoteException {
		return (FichaFacade) facadeHandle.getEJBObject();
	}

	protected FichaDelegateImpl() throws DelegateException {
		try {
			final FichaFacadeHome home = FichaFacadeUtil.getHome();
			final FichaFacade remote = home.create();
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
	public List<Long> buscarIdsFichas() throws DelegateException {
		try {
			return getFacade().buscarIdsFichas();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
