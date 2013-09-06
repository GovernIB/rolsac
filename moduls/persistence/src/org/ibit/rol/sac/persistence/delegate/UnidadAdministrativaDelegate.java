package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Seccion;
import es.caib.rolsac.utils.ResultadoBusqueda;
import org.ibit.rol.sac.model.FichaResumenUA;

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

	
	public void setImpl(UnidadAdministrativaDelegateI impl) {
		this.impl = impl;
	}

	
	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad,
			Long padreId) throws DelegateException {
		impl.actualizarUnidadAdministrativa(unidad, padreId);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo
	 * */
	public void anyadirEdificio(Long edificioId, Long uaId)
			throws DelegateException {
		impl.anyadirEdificio(edificioId, uaId);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo
	 * */
	public boolean borrarBusinessKey(String businessKey)
			throws DelegateException {
		return impl.borrarBusinessKey(businessKey);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo
	 * */
	public List<UnidadAdministrativa> buscar(String busqueda, String idioma)
			throws DelegateException {
		return impl.buscar(busqueda, idioma);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public List<ProcedimientoLocal> buscarProcedimientosUA(Long idUA,
			String busqueda, String idioma, Date dataInici, Date dataFi)
					throws DelegateException {
		return impl.buscarProcedimientosUA(idUA, busqueda, idioma, dataInici,
				dataFi);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo
	 * */
	public List buscarUnidadesAdministrativas(Map parametros, Map traduccion)
			throws DelegateException {
		return impl.buscarUnidadesAdministrativas(parametros, traduccion);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(
			Long ambito, String busqueda, String idioma)
					throws DelegateException {
		return impl.buscarUnidadesAdministrativasPorAmbito(ambito, busqueda,
				idioma);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public void cambiarOrden(Long ua1Id, Long ua2Id) throws DelegateException {
		impl.cambiarOrden(ua1Id, ua2Id);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public UnidadAdministrativa cargarArbolUnidad(Long id)
			throws DelegateException {
		return impl.cargarArbolUnidad(id);
	}

	
	public List cargarArbolUnidadId(Long id) throws DelegateException {
		return impl.cargarArbolUnidadId(id);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public UnidadAdministrativa consultarUAPormad(Long id)
			throws DelegateException {
		return impl.consultarUAPormad(id);
	}

	
	public UnidadAdministrativa consultarUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativa(id);
	}

	
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativaSinFichas(id);
	}

	
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativaPMA(id);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public UnidadAdministrativa consultarUnidadAdministrativaPORMAD(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativaPORMAD(id);
	}

	
	public Long crearUnidadAdministrativa(UnidadAdministrativa unidad,
			Long padreId) throws DelegateException {
		return impl.crearUnidadAdministrativa(unidad, padreId);
	}

	
	public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad)
			throws DelegateException {
		return impl.crearUnidadAdministrativaRaiz(unidad);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo.
	 * */
	public void eliminarEdificio(Long edificioId, Long uaId)
			throws DelegateException {
		impl.eliminarEdificio(edificioId, uaId);
	}

	
	@Override
	public boolean equals(Object obj) {
		return impl.equals(obj);
	}

	
	/**
	 * @deprecated	Se usa en el back antiguo.
	 * */
	public void fijarBusinessKey(Long id, String businessKey)
			throws DelegateException {
		impl.fijarBusinessKey(id, businessKey);
	}

	
	/**
	 * @deprecated	No se usa.
	 * */
	public StringBuffer getUaMolla(Long idua, String idioma)
			throws DelegateException {
		return impl.getUaMolla(idua, idioma);
	}

	
	public StringBuffer getUaMollaBack2(Long idua, String idioma, String url, String uaIdPlaceholder)
			throws DelegateException {
		return impl.getUaMollaBack2(idua, idioma, url, uaIdPlaceholder);
	}

	
	@Override
	public int hashCode() {
		return impl.hashCode();
	}

	
	public void indexBorraUA(Long id) throws DelegateException {
		impl.indexBorraUA(id);
	}
	

	public void indexInsertaUA(UnidadAdministrativa ua, ModelFilterObject filter)
			throws DelegateException {
		impl.indexInsertaUA(ua, filter);
	}


	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa informacionGeneral(Long id)
			throws DelegateException {
		return impl.informacionGeneral(id);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public List listarBusinessKey() throws DelegateException {
		return impl.listarBusinessKey();
	}


	/**
	 * @deprecated	No se usa
	 * */
	public List listarDescendientesConse(String id) throws DelegateException {
		return impl.listarDescendientesConse(id);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public List<Ficha> listarFichSecHV(Long idUA, Long idHV, String ceSec,
			boolean caducados) throws DelegateException {
		return impl.listarFichSecHV(idUA, idHV, ceSec, caducados);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public List<Ficha> listarFichSecMat(Long idUA, Long idMat, String ceSec,
			boolean caducados) throws DelegateException {
		return impl.listarFichSecMat(idUA, idMat, ceSec, caducados);
	}

	
	public List listarHijosUA(Long id) throws DelegateException {
		return impl.listarHijosUA(id);
	}
	

	public List listarPadresUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.listarPadresUnidadAdministrativa(id);
	}

	
	/**
	 * @deprecated	Se usa desde el back antiguo
	 * */
	public List listarPadresUnidadAdministrativaAcceso(Long id)
			throws DelegateException {
		return impl.listarPadresUnidadAdministrativaAcceso(id);
	}
	

	/**
	 * @deprecated	Se usa desde el back antiguo
	 * */
	public Map<String, List<?>> listarProcFichSecHV(Long idUA, Long idHV,
			String ceSec) throws DelegateException {
		return impl.listarProcFichSecHV(idUA, idHV, ceSec);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public Map<String, List<?>> listarProcFichSecMat(Long idUA, Long idMat,
			String ceSec) throws DelegateException {
		return impl.listarProcFichSecMat(idUA, idMat, ceSec);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public List<ProcedimientoLocal> listarProcHV(Long idUA, Long idHV,
			String[] codMat, boolean include, boolean caducados)
					throws DelegateException {
		return impl.listarProcHV(idUA, idHV, codMat, include, caducados);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public List<ProcedimientoLocal> listarProcMat(Long idUA, Long idMat,
			String[] codMat, boolean include, boolean caducados)
					throws DelegateException {
		return impl.listarProcMat(idUA, idMat, codMat, include, caducados);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public List<UnidadAdministrativa> listarUAEspacioTerritorial(Long idEspacio)
			throws DelegateException {
		return impl.listarUAEspacioTerritorial(idEspacio);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(
			Long ambito) throws DelegateException {
		return impl.listarUnidadesAdministrativasPorAmbito(ambito);
	}
	

	public List listarUnidadesAdministrativasRaiz() throws DelegateException {
		return impl.listarUnidadesAdministrativasRaiz();
	}
	

	/**
	 * @deprecated	Se usa desde el back antiguo.
	 * */
	public List listarUnidadesAdministrativasRaiz(boolean publicadas)
			throws DelegateException {
		return impl.listarUnidadesAdministrativasRaiz(publicadas);
	}
	

	public List listarTodasUnidadesAdministrativasRaiz() throws DelegateException {
		return impl.listarTodasUnidadesAdministrativasRaiz();
	}
	

	/**
	 * Autorización eliminar Unidad Administrativa.Devuleve true si tiene acceso.
	 */
	public boolean autorizarEliminarUA(Long idUa) throws DelegateException {
		return impl.autorizarEliminarUA(idUa);
	}

	
	/**
	 * Comprobar si el usuario tiene privilegios para crear una UA.
	 */
	public Boolean autorizarCrearUA() throws DelegateException {
		return impl.autorizarCrearUA();
	}

	
	/**
	 * Eliminar una unidad administrativa. Se podra eliminar la UA, si no tiene elementos relacionados (Procedimientos,Normativas,etc
	 * Antes de elminar la UA ha de validar que la UA no tenga relaciones.
	 */
	public void eliminarUaSinRelaciones(Long idUA) throws DelegateException {
		impl.eliminarUaSinRelaciones(idUA);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public ModelFilterObject obtenerFilterObject(UnidadAdministrativa ua)
			throws DelegateException {
		return impl.obtenerFilterObject(ua);
	}

	
	public Archivo obtenerFotoGrandeUA(Long id) throws DelegateException {
		return impl.obtenerFotoGrandeUA(id);
	}

	
	public Archivo obtenerFotoPequenyaUA(Long id) throws DelegateException {
		return impl.obtenerFotoPequenyaUA(id);
	}


	public Archivo obtenerLogoHorizontalUA(Long id) throws DelegateException {
		return impl.obtenerLogoHorizontalUA(id);
	}

	
	public Archivo obtenerLogoSaludoUA(Long id) throws DelegateException {
		return impl.obtenerLogoSaludoUA(id);
	}

	
	public Archivo obtenerLogoSaludoVerticalUA(Long id)
			throws DelegateException {
		return impl.obtenerLogoSaludoVerticalUA(id);
	}

	
	public Archivo obtenerLogoVerticalUA(Long id) throws DelegateException {
		return impl.obtenerLogoVerticalUA(id);
	}

	
	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz()
			throws DelegateException {
		return impl.obtenerPrimeraUnidadAdministrativaRaiz();
	}
	

	public UnidadAdministrativa obtenerUnidadAdministrativa(Long id)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativa(id);
	}
	

	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(
			String codEst) throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorCodEstandar(codEst);
	}   
 

	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(
			String nombre) throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorNombre(nombre);
	}
	
	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo, List UAOpcionales)
					throws DelegateException {
		return impl.obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,
				tipo, UAOpcionales);
	}	
	

	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo) throws DelegateException {
		return impl.obtenerUnidadesAdministrativasArbreTerritorial(idEspacio,
				tipo);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk)
			throws DelegateException {
		return impl.obtenerUnidadPorBusinessKey(bk);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id)
			throws DelegateException {
		return impl.obtenerUnidadAdministrativaPM(id);
	}
	

	/**
	 * @deprecated	No se usa
	 * */
	public void repararOrdenFichasUA() throws DelegateException {
		impl.repararOrdenFichasUA();
	}	

	
	@Override
	public String toString() {
		return impl.toString();
	}

	
	public String obtenerCadenaFiltroUA(Long ua,
			boolean uaFilles, boolean uaMeves) throws DelegateException {
		return impl.obtenerCadenaFiltroUA(ua, uaFilles, uaMeves);
	}
	

	public ResultadoBusqueda buscadorUnidadesAdministrativas(Map<String, Object> parametros, Map<String, String> traduccion, Long id, String idioma, boolean uaFilles, boolean uaMeves, Long materia, String pagina, String resultats) throws DelegateException {
		return impl.buscadorUnidadesAdministrativas(parametros, traduccion, id, idioma, uaFilles, uaMeves, materia, pagina, resultats);
	}	
	

	public void reordenar( Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre) throws DelegateException {
		impl.reordenar(id, ordenNuevo, ordenAnterior, idPadre);
	}
	

	public List<Seccion> listarSeccionesUA(Long idUA) 
			throws DelegateException {
		return impl.listarSeccionesUA(idUA);
	}
	

	public Long cuentaFichasSeccionUA(final Long idUA, final Long idSeccion)
			throws DelegateException {
		return impl.cuentaFichasSeccionUA(idUA, idSeccion);
	}
	

	public List<FichaResumenUA> listarFichasSeccionUA(Long idUA, Long idSeccion)
			throws DelegateException {
		return impl.listarFichasSeccionUA(idUA, idSeccion);
	}
	

	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<Long> listaIdFichasLong)
			throws DelegateException {
		impl.actualizaFichasSeccionUA(idUA, idSeccion, listaIdFichasLong);
	}

}
