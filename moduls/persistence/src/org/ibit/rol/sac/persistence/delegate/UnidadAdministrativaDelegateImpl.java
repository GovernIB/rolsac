package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadAdministrativaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadAdministrativaFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate para manipular Unidades Administrativas.
 */
public class UnidadAdministrativaDelegateImpl implements StatelessDelegate, UnidadAdministrativaDelegateI {

	private static final long serialVersionUID = 234459285681808474L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	@Override
	public Long crearUnidadAdministrativaRaiz(final UnidadAdministrativa unidad) throws DelegateException {
		try {
			return getFacade().crearUnidadAdministrativaRaiz(unidad);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.lang.Long)
	 */
	@Override
	public Long crearUnidadAdministrativa(final UnidadAdministrativa unidad, final Long padre_id)
			throws DelegateException {
		try {
			return getFacade().crearUnidadAdministrativa(unidad, padre_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa,
	 * java.lang.Long)
	 */
	@Override
	public void actualizarUnidadAdministrativa(final UnidadAdministrativa unidad, final Long padre_id)
			throws DelegateException {
		try {
			getFacade().actualizarUnidadAdministrativa(unidad, padre_id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * actualizarEdificiosUnidadAdministrativa(org.ibit.rol.sac.model.
	 * UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	@Override
	public void actualizarEdificiosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosEdificios) throws DelegateException {
		try {
			getFacade().actualizarEdificiosUnidadAdministrativa(unidad, idsNuevosEdificios);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * actualizarUsuariosUnidadAdministrativa(org.ibit.rol.sac.model.
	 * UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	@Override
	public void actualizarUsuariosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosUsuarios) throws DelegateException {
		try {
			getFacade().actualizarUsuariosUnidadAdministrativa(unidad, idsNuevosUsuarios);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * listarHijosUA(java.lang.Long)
	 */
	@Override
	public List listarHijosUA(final Long id) throws DelegateException {
		try {
			return getFacade().listarHijosUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * listarUnidadesAdministrativasRaiz()
	 */
	@Override
	public List listarUnidadesAdministrativasRaiz() throws DelegateException {
		try {
			return getFacade().listarUnidadesAdministrativasRaiz();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * listarTodasUnidadesAdministrativasRaiz()
	 */
	@Override
	public List listarTodasUnidadesAdministrativasRaiz() throws DelegateException {
		try {
			return getFacade().listarTodasUnidadesAdministrativasRaiz();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * listarPadresUnidadAdministrativa(java.lang.Long)
	 */
	@Override
	public List listarPadresUnidadAdministrativa(final Long id) throws DelegateException {
		try {
			return getFacade().listarPadresUnidadAdministrativa(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerUnidadAdministrativa(java.lang.Long)
	 */
	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativa(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativa(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * consultarUnidadAdministrativa(java.lang.Long)
	 */
	@Override
	public UnidadAdministrativa consultarUnidadAdministrativa(final Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativa(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
	@Override
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(final Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativaPMA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(final String codEst)
			throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativaPorCodEstandar(codEst);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodDir3(final String codDir3, final boolean inicializar)
			throws DelegateException {
		try {
			return getFacade().obtenerUnidadAdministrativaPorCodDir3(codDir3, inicializar);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerFotoPequenyaUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerFotoPequenyaUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerFotoPequenyaUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerFotoGrandeUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerFotoGrandeUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerFotoGrandeUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	// CODI AFEGIT PELS LOGOS
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerLogoHorizontalUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerLogoHorizontalUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoHorizontalUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	// CODI AFEGIT PELS LOGOS
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerLogoVerticalUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerLogoVerticalUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoVerticalUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	// CODI AFEGIT PELS LOGOS
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerLogoSaludoUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerLogoSaludoUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoSaludoUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	// CODI AFEGIT PELS LOGOS
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
	@Override
	public Archivo obtenerLogoSaludoVerticalUA(final Long id) throws DelegateException {
		try {
			return getFacade().obtenerLogoSaludoVerticalUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * cargarArbolUnidadId(java.lang.Long)
	 */
	@Override
	public List cargarArbolUnidadId(final Long id) throws DelegateException {
		try {
			return getFacade().cargarArbolUnidadId(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegateI#
	 * getUaMollaBack2(java.lang.Long, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public StringBuffer getUaMollaBack2(final Long idua, final String _idioma, final String url,
			final String uaIdPlaceholder) throws DelegateException {
		try {
			return getFacade().getUaMollaBack2(idua, _idioma, url, uaIdPlaceholder);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public boolean autorizarEliminarUA(final Long idUa) throws DelegateException {
		try {
			return getFacade().autorizarEliminarUA(idUa);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/*
	 * (non-Javadoc) Descripcion: Comprobar si el usuario tiene privilegios para
	 * crear una UA.
	 */
	@Override
	public Boolean autorizarCrearUA() throws DelegateException {
		try {
			return getFacade().autorizarCrearUA();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String obtenerCadenaFiltroUA(final Long ua, final boolean uaFilles, final boolean uaMeves)
			throws DelegateException {
		try {
			return getFacade().obtenerCadenaFiltroUA(ua, uaFilles, uaMeves);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String obtenerCadenaFiltroUAPorDir3(final String codigoDir3UA, final boolean uaFilles, final boolean uaMeves)
			throws DelegateException {
		try {
			return getFacade().obtenerCadenaFiltroUAPorDir3(codigoDir3UA, uaFilles, uaMeves);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarUaSinRelaciones(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarUaSinRelaciones(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void reordenar(final Long id, final Integer ordenNuevo, final Integer ordenAnterior, final Long idPadre)
			throws DelegateException {
		try {
			getFacade().reordenar(id, ordenNuevo, ordenAnterior, idPadre);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda buscadorUnidadesAdministrativas(final Map<String, Object> parametros,
			final Map<String, String> traduccion, final Long id, final String idioma, final boolean uaFilles,
			final boolean uaMeves, final Long materia, final String pagina, final String resultats)
			throws DelegateException {
		try {
			return getFacade().buscadorUnidadesAdministrativas(parametros, traduccion, id, idioma, uaFilles, uaMeves,
					materia, pagina, resultats);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(final Long id) throws DelegateException {
		try {
			return getFacade().consultarUnidadAdministrativaSinFichas(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<Seccion> listarSeccionesUA(final Long idUA) throws DelegateException {
		try {
			return getFacade().listarSeccionesUA(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public int cuentaFichasSeccionUA(final Long idUA, final Long idSeccion) throws DelegateException {
		try {
			return getFacade().cuentaFichasSeccionUA(idUA, idSeccion);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<FichaDTO> listarFichasSeccionUA(final Long idUA, final Long idSeccion, final String idioma,
			final PaginacionCriteria paginacion) throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUA(idUA, idSeccion, idioma, paginacion);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public List<FichaDTO> listarFichasSeccionUASinPaginacion(final Long idUA, final Long idSeccion, final String idioma)
			throws DelegateException {
		try {
			return getFacade().listarFichasSeccionUASinPaginacion(idUA, idSeccion, idioma);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void actualizaFichasSeccionUA(final Long idUA, final Long idSeccion, final List<FichaDTO> fichas)
			throws DelegateException {
		try {
			getFacade().actualizaFichasSeccionUA(idUA, idSeccion, fichas);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarFotoGrande(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarFotoGrande(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarFotoPetita(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarFotoPetita(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarLogoHorizontal(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarLogoHorizontal(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarLogoVertical(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarLogoVertical(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarLogoSalutacio(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarLogoSalutacio(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarLogoTipos(final Long idUA) throws DelegateException {
		try {
			getFacade().eliminarLogoTipos(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public void eliminarSeccionUA(final Long idUA, final Long idSeccion) throws DelegateException {
		try {
			getFacade().eliminarSeccionUA(idUA, idSeccion);
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
	public List<Long> obtenerHijosUnidadAdministrativa(final Long idUA) throws DelegateException {
		try {
			return getFacade().obtenerHijosUnidadAdministrativa(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public ResultadoBusqueda consultaUnidadesAdministrativas(final FiltroGenerico filtro) throws DelegateException {
		try {
			return getFacade().consultaUnidadesAdministrativas(filtro);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String consultaCodigoDir3(final Long id) throws DelegateException {
		try {
			return getFacade().consultaCodigoDir3(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private UnidadAdministrativaFacade getFacade() throws RemoteException {
		return (UnidadAdministrativaFacade) facadeHandle.getEJBObject();
	}

	protected UnidadAdministrativaDelegateImpl() throws DelegateException {
		try {
			final UnidadAdministrativaFacadeHome home = UnidadAdministrativaFacadeUtil.getHome();
			final UnidadAdministrativaFacade remote = home.create();
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
	public List<Long> buscarIdsUas() throws DelegateException {
		try {
			return getFacade().buscarIdsUas();
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public String checkProcedimientosUA(final Long id) throws DelegateException {
		try {
			return getFacade().checkProcedimientosUA(id);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

	@Override
	public UnidadAdministrativa obtenerPadreDir3(final Long idUA) throws DelegateException {
		try {
			return getFacade().obtenerPadreDir3(idUA);
		} catch (final RemoteException e) {
			throw new DelegateException(e);
		}
	}

}
