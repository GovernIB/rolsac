package org.ibit.rol.sac.persistence.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.MensajeEmail;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.ProcedimientoMensaje;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.BuscadorProcedimientoCriteria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Session;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing.
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError).
 *
 */

public class ProcedimientoDelegate {

	ProcedimientoDelegateI impl;

	public ProcedimientoDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final ProcedimientoDelegateI impl) {
		this.impl = impl;
	}

	public boolean existeOtroTramiteInicioProcedimiento(final Long procId, final Long tramiteId)
			throws DelegateException {
		return impl.existeOtroTramiteInicioProcedimiento(procId, tramiteId);
	}

	public void anyadirTramite(final Long tramiteId, final Long procId) throws DelegateException {
		impl.anyadirTramite(tramiteId, procId);
	}

	public void borrarProcedimiento(final Long id) throws DelegateException {
		impl.borrarProcedimiento(id);
	}

	public List buscarProcedimientos(final Map param, final Map trad) throws DelegateException {
		return impl.buscarProcedimientos(param, trad);
	}

	/** @deprecated No se usa */
	@Deprecated
	public ResultadoBusqueda buscadorProcedimientos(final Map parametros, final Map traduccion,
			final UnidadAdministrativa ua, final boolean uaFilles, final boolean uaMeves, final Long materia,
			final Long fetVital, final Long publicObjectiu, final String pagina, final String resultats,
			final int visible, final String en_plazo, final String telematico) throws DelegateException {
		return impl.buscadorProcedimientos(parametros, traduccion, ua, uaFilles, uaMeves, materia, fetVital,
				publicObjectiu, pagina, resultats, visible, en_plazo, telematico);
	}

	/** @deprecated Se usa desde la API v1 */
	@Deprecated
	public List buscarProcedimientosMateria(final Long id) throws DelegateException {
		return impl.buscarProcedimientosMateria(id);
	}

	public ProcedimientoLocal consultarProcedimiento(final Long id) throws DelegateException {
		return impl.consultarProcedimiento(id);
	}

	public void eliminarTramite(final Long tramiteId, final Long procId) throws DelegateException {
		impl.eliminarTramite(tramiteId, procId);
	}

	public void tieneNormativas(final Long id) throws DelegateException {
		impl.tieneNormativas(id);
	}

	public Long grabarProcedimiento(final ProcedimientoLocal procedimiento, final Long idUA,
			final ProcedimientoMensaje procedimientoMensaje, final MensajeEmail mensajeEmail) throws DelegateException {
		return impl.grabarProcedimiento(procedimiento, idUA, procedimientoMensaje, mensajeEmail);
	}

	public Long grabarProcedimientoConTramites(final ProcedimientoLocal procedimiento, final Long idUA,
			final List listaTramitesParaBorrar, final List listaIdsTramitesParaActualizar,
			final ProcedimientoMensaje procedimientoMensaje, final MensajeEmail mensajeEmail) throws DelegateException {
		return impl.grabarProcedimientoConTramites(procedimiento, idUA, listaTramitesParaBorrar,
				listaIdsTramitesParaActualizar, procedimientoMensaje, mensajeEmail);
	}

	/** @deprecated Se usa desde API v1 */
	@Deprecated
	public List listarProcedimientosPublicos() throws DelegateException {
		return impl.listarProcedimientosPublicos();
	}

	/** @deprecated Se usa desde el back antiguo */
	@Deprecated
	public List listarProcedimientos() throws DelegateException {
		return impl.listarProcedimientos();
	}

	public List listarProcedimientosPublicosHechoVital(final Long id) throws DelegateException {
		return impl.listarProcedimientosPublicosHechoVital(id);
	}

	public List listarProcedimientosPublicosUA(final Long id) throws DelegateException {
		return impl.listarProcedimientosPublicosUA(id);
	}

	public List<Long> listarIdsProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		return impl.listarIdsProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
	}

	/** @deprecated Se usa desde el back antiguo */
	@Deprecated
	public List listarProcedimientosUA(final Long id) throws DelegateException {
		return impl.listarProcedimientosUA(id);
	}

	public List listarProcedimientosUO(final Long id, final Integer conse) throws DelegateException {
		return impl.listarProcedimientosUO(id, conse);
	}

	public ProcedimientoLocal obtenerProcedimiento(final Long id) throws DelegateException {
		return impl.obtenerProcedimiento(id);
	}

	public ProcedimientoLocal obtenerProcedimientoNewBack(final Long id) throws DelegateException {
		return impl.obtenerProcedimientoNewBack(id);
	}

	public void actualizarOrdenTramites(final ArrayList<Long> tramitesId) throws DelegateException {
		impl.actualizarOrdenTramites(tramitesId);
	}

	public void reordenarDocumentos(final Long idProcedimiento, final List<Long> idDocumentos,
			final ProcedimientoMensaje procedimientoMensaje) throws DelegateException {
		impl.reordenarDocumentos(idProcedimiento, idDocumentos, procedimientoMensaje);
	}

	public int buscarProcedimientosActivos(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		return impl.buscarProcedimientosActivos(listaUnidadAdministrativaId, fechaCaducidad);
	}

	public int buscarProcedimientosCaducados(final List<Long> listaUnidadAdministrativaId, final Date fechaCaducidad)
			throws DelegateException {
		return impl.buscarProcedimientosCaducados(listaUnidadAdministrativaId, fechaCaducidad);
	}

	public List<ProcedimientoLocal> listarProcedimientosPublicosUAHVMateria(final Long idUA, final String[] codEstMat,
			final String[] codEstHV) throws DelegateException {
		return impl.listarProcedimientosPublicosUAHVMateria(idUA, codEstMat, codEstHV);
	}

	public ResultadoBusqueda buscadorProcedimientos(final BuscadorProcedimientoCriteria bc) throws DelegateException {
		return impl.buscadorProcedimientos(bc);
	}

	public boolean isProcedimientoConEstadoPublicacionPublica(final Long idProcedimiento) throws DelegateException {
		return impl.isProcedimientoConEstadoPublicacionPublica(idProcedimiento);
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.indexarSolr(solrIndexer, solrPendiente);
	}

	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento,
			final EnumCategoria categoria) throws DelegateException {
		return impl.indexarSolr(solrIndexer, idElemento, categoria);
	}

	public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente)
			throws DelegateException {
		return impl.desindexarSolr(solrIndexer, solrPendiente);
	}

	public List<Long> buscarIdsProcedimientos() throws DelegateException {
		return impl.buscarIdsProcedimientos();
	}

	public List<ProcedimientoLocal> listarProcedimientosNormativa(final Long idElemento) throws DelegateException {
		return impl.listarProcedimientosNormativa(idElemento);
	}

	public void actualizarProcedimiento(final ProcedimientoLocal proc) throws DelegateException {
		impl.actualizarProcedimiento(proc);

	}

	public ProcedimientoLocal obtenerProcedimientoParaSolr(final Long idElemento, final Session iSession)
			throws DelegateException {
		return impl.obtenerProcedimientoParaSolr(idElemento, iSession);
	}

	public List<Long> getProcedimientosEstadoSIAAlterado() throws DelegateException {
		return impl.getProcedimientosEstadoSIAAlterado();
	}

	public List<Long> listarProcedimientosOrganoResolutori(final Long idOrganoResolutori) throws DelegateException {
		return impl.listarProcedimientosOrganoResolutori(idOrganoResolutori);
	}

	/**
	 * Comprueba si alguna de las normativas del procedicimiento está derogado.
	 *
	 * @param id
	 * @return
	 */
	public boolean isNormativaDerogada(final Long id) throws DelegateException {
		return impl.isNormativaDerogada(id);
	}

	/**
	 * Comprueba si alguna de las normativas del procedicimiento está validos.
	 *
	 * @param id
	 * @return
	 */
	public boolean isNormativaValidas(final Long id) throws DelegateException {
		return impl.isNormativaValidas(id);
	}

	public ResultadoBusqueda consultaProcedimientos(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaProcedimientos(filtro);
	}

	/**
	 * Metodo que graba los archivos acorde a las traducciones
	 *
	 * @param procedimiento
	 * @param traducciones
	 * @param archivosAborrar
	 */
	public void grabarArchivos(final Long idProcedimiento, final Map<String, TraduccionProcedimientoLocal> traducciones,
			final List<Long> archivosAborrar) throws DelegateException {
		impl.grabarArchivos(idProcedimiento, traducciones, archivosAborrar);
	}

	/**
	 * Obtiene el doc de info adicional
	 *
	 * @param id
	 * @param idioma
	 * @return
	 */
	public Archivo obtenerProcInfoAdicional(final Long id, final String idioma) throws DelegateException {
		return impl.obtenerProcInfoAdicional(id, idioma);
	}

	/**
	 * Comprueba si tiene la info adicional en catalán.
	 *
	 * @param id
	 * @return
	 * @throws DelegateException
	 */
	public boolean checkInfoAdicional(final Long id) throws DelegateException {
		return impl.checkInfoAdicional(id);
	}

	/**
	 * Obtiene solo el nombre del procedimiento
	 *
	 * @param idProc
	 * @param catalan
	 * @return
	 * @throws DelegateException
	 */
	public String obtenerNombreProcedimiento(final Long idProc, final boolean catalan) throws DelegateException {
		return impl.obtenerNombreProcedimiento(idProc, catalan);
	}

}
