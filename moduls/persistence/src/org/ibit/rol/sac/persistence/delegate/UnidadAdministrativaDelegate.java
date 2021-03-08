package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;

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

public class UnidadAdministrativaDelegate {

	UnidadAdministrativaDelegateI impl = null;

	public UnidadAdministrativaDelegateI getImpl() {
		return impl;
	}

	public void setImpl(final UnidadAdministrativaDelegateI impl) {
		this.impl = impl;
	}

	public void actualizarUnidadAdministrativa(final UnidadAdministrativa unidad, final Long padreId)
			throws DelegateException {
		impl.actualizarUnidadAdministrativa(unidad, padreId);
	}

	public void actualizarEdificiosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosEdificios) throws DelegateException {
		impl.actualizarEdificiosUnidadAdministrativa(unidad, idsNuevosEdificios);
	}

	public void actualizarUsuariosUnidadAdministrativa(final UnidadAdministrativa unidad,
			final List<Long> idsNuevosUsuarios) throws DelegateException {
		impl.actualizarUsuariosUnidadAdministrativa(unidad, idsNuevosUsuarios);
	}

	public List cargarArbolUnidadId(final Long id) throws DelegateException {
		return impl.cargarArbolUnidadId(id);
	}

	public UnidadAdministrativa consultarUnidadAdministrativa(final Long id) throws DelegateException {
		return impl.consultarUnidadAdministrativa(id);
	}

	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(final Long id) throws DelegateException {
		return impl.consultarUnidadAdministrativaSinFichas(id);
	}

	public UnidadAdministrativa consultarUnidadAdministrativaPMA(final Long id) throws DelegateException {
		return impl.consultarUnidadAdministrativaPMA(id);
	}

	public Long crearUnidadAdministrativa(final UnidadAdministrativa unidad, final Long padreId)
			throws DelegateException {
		return impl.crearUnidadAdministrativa(unidad, padreId);
	}

	public Long crearUnidadAdministrativaRaiz(final UnidadAdministrativa unidad) throws DelegateException {
		return impl.crearUnidadAdministrativaRaiz(unidad);
	}

	@Override
	public boolean equals(final Object obj) {
		return impl.equals(obj);
	}

	public StringBuffer getUaMollaBack2(final Long idua, final String idioma, final String url,
			final String uaIdPlaceholder) throws DelegateException {
		return impl.getUaMollaBack2(idua, idioma, url, uaIdPlaceholder);
	}

	@Override
	public int hashCode() {
		return impl.hashCode();
	}

	public List listarHijosUA(final Long id) throws DelegateException {
		return impl.listarHijosUA(id);
	}

	public List listarPadresUnidadAdministrativa(final Long id) throws DelegateException {
		return impl.listarPadresUnidadAdministrativa(id);
	}

	public List listarUnidadesAdministrativasRaiz() throws DelegateException {
		return impl.listarUnidadesAdministrativasRaiz();
	}

	public List listarTodasUnidadesAdministrativasRaiz() throws DelegateException {
		return impl.listarTodasUnidadesAdministrativasRaiz();
	}

	/**
	 * Autorización eliminar Unidad Administrativa.Devuleve true si tiene acceso.
	 */
	public boolean autorizarEliminarUA(final Long idUa) throws DelegateException {
		return impl.autorizarEliminarUA(idUa);
	}

	/**
	 * Comprobar si el usuario tiene privilegios para crear una UA.
	 */
	public Boolean autorizarCrearUA() throws DelegateException {
		return impl.autorizarCrearUA();
	}

	/**
	 * Eliminar una unidad administrativa. Se podra eliminar la UA, si no tiene
	 * elementos relacionados (Procedimientos,Normativas,etc Antes de elminar la UA
	 * ha de validar que la UA no tenga relaciones.
	 */
	public void eliminarUaSinRelaciones(final Long idUA) throws DelegateException {
		impl.eliminarUaSinRelaciones(idUA);
	}

	public Archivo obtenerFotoGrandeUA(final Long id) throws DelegateException {
		return impl.obtenerFotoGrandeUA(id);
	}

	public Archivo obtenerFotoPequenyaUA(final Long id) throws DelegateException {
		return impl.obtenerFotoPequenyaUA(id);
	}

	public Archivo obtenerLogoHorizontalUA(final Long id) throws DelegateException {
		return impl.obtenerLogoHorizontalUA(id);
	}

	public Archivo obtenerLogoSaludoUA(final Long id) throws DelegateException {
		return impl.obtenerLogoSaludoUA(id);
	}

	public Archivo obtenerLogoSaludoVerticalUA(final Long id) throws DelegateException {
		return impl.obtenerLogoSaludoVerticalUA(id);
	}

	public Archivo obtenerLogoVerticalUA(final Long id) throws DelegateException {
		return impl.obtenerLogoVerticalUA(id);
	}

	public UnidadAdministrativa obtenerUnidadAdministrativa(final Long id) throws DelegateException {
		return impl.obtenerUnidadAdministrativa(id);
	}

	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(final String codEst)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorCodEstandar(codEst);
	}

	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodDir3(final String codDir3, final boolean inicializar)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorCodDir3(codDir3, inicializar);
	}

	@Override
	public String toString() {
		return impl.toString();
	}

	public String obtenerCadenaFiltroUA(final Long ua, final boolean uaFilles, final boolean uaMeves)
			throws DelegateException {
		return impl.obtenerCadenaFiltroUA(ua, uaFilles, uaMeves);
	}

	public String obtenerCadenaFiltroUAPorDir3(final String codigoDir3UA, final boolean uaFilles, final boolean uaMeves)
			throws DelegateException {
		return impl.obtenerCadenaFiltroUAPorDir3(codigoDir3UA, uaFilles, uaMeves);
	}

	public ResultadoBusqueda buscadorUnidadesAdministrativas(final Map<String, Object> parametros,
			final Map<String, String> traduccion, final Long id, final String idioma, final boolean uaFilles,
			final boolean uaMeves, final Long materia, final String pagina, final String resultats)
			throws DelegateException {
		return impl.buscadorUnidadesAdministrativas(parametros, traduccion, id, idioma, uaFilles, uaMeves, materia,
				pagina, resultats);
	}

	public void reordenar(final Long id, final Integer ordenNuevo, final Integer ordenAnterior, final Long idPadre)
			throws DelegateException {
		impl.reordenar(id, ordenNuevo, ordenAnterior, idPadre);
	}

	public List<Seccion> listarSeccionesUA(final Long idUA) throws DelegateException {
		return impl.listarSeccionesUA(idUA);
	}

	public int cuentaFichasSeccionUA(final Long idUA, final Long idSeccion) throws DelegateException {
		return impl.cuentaFichasSeccionUA(idUA, idSeccion);
	}

	public List<FichaDTO> listarFichasSeccionUA(final Long idUA, final Long idSeccion, final String idioma,
			final PaginacionCriteria paginacion) throws DelegateException {
		return impl.listarFichasSeccionUA(idUA, idSeccion, idioma, paginacion);
	}

	public List<FichaDTO> listarFichasSeccionUASinPaginacion(final Long idUA, final Long idSeccion, final String idioma)
			throws DelegateException {
		return impl.listarFichasSeccionUASinPaginacion(idUA, idSeccion, idioma);
	}

	public void actualizaFichasSeccionUA(final Long idUA, final Long idSeccion, final List<FichaDTO> fichas)
			throws DelegateException {
		impl.actualizaFichasSeccionUA(idUA, idSeccion, fichas);
	}

	public void eliminarFotoGrande(final Long idUA) throws DelegateException {
		impl.eliminarFotoGrande(idUA);
	}

	public void eliminarFotoPetita(final Long idUA) throws DelegateException {
		impl.eliminarFotoPetita(idUA);
	}

	public void eliminarLogoHorizontal(final Long idUA) throws DelegateException {
		impl.eliminarLogoHorizontal(idUA);
	}

	public void eliminarLogoVertical(final Long idUA) throws DelegateException {
		impl.eliminarLogoVertical(idUA);
	}

	public void eliminarLogoSalutacio(final Long idUA) throws DelegateException {
		impl.eliminarLogoSalutacio(idUA);
	}

	public void eliminarLogoTipos(final Long idUA) throws DelegateException {
		impl.eliminarLogoTipos(idUA);
	}

	public void eliminarSeccionUA(final Long idUA, final Long idSeccion) throws DelegateException {
		impl.eliminarSeccionUA(idUA, idSeccion);
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

	public List<Long> buscarIdsUas() throws DelegateException {
		return impl.buscarIdsUas();
	}

	public List<Long> obtenerHijosUnidadAdministrativa(final Long idUA) throws DelegateException {
		return impl.obtenerHijosUnidadAdministrativa(idUA);
	}

	public String checkProcedimientosUA(final Long id) throws DelegateException {
		return impl.checkProcedimientosUA(id);
	}

	public ResultadoBusqueda consultaUnidadesAdministrativas(final FiltroGenerico filtro) throws DelegateException {
		return impl.consultaUnidadesAdministrativas(filtro);
	}

	public String consultaCodigoDir3(final Long id) throws DelegateException {
		return impl.consultaCodigoDir3(id);
	}

	public UnidadAdministrativa obtenerPadreDir3(final Long idUA) throws DelegateException {
		return impl.obtenerPadreDir3(idUA);
	}
}
