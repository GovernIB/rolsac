package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

public interface UnidadAdministrativaDelegateI {

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	public abstract Long crearUnidadAdministrativaRaiz(
			UnidadAdministrativa unidad) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public abstract Long crearUnidadAdministrativa(UnidadAdministrativa unidad,
			Long padre_id) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public abstract void actualizarUnidadAdministrativa(
			UnidadAdministrativa unidad, Long padre_id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarHijosUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarHijosUA(java.lang.Long)
	 */
	public abstract List listarHijosUA(Long id) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarDescendientesConse(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarDescendientesConse(java.lang.String)
	 */
	public abstract List listarDescendientesConse(String id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz()
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz()
	 */
	public abstract List listarUnidadesAdministrativasRaiz()
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerPrimeraUnidadAdministrativaRaiz()
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerPrimeraUnidadAdministrativaRaiz()
	 */
	public abstract UnidadAdministrativa obtenerPrimeraUnidadAdministrativaRaiz()
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz(boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz(boolean)
	 */
	public abstract List listarUnidadesAdministrativasRaiz(boolean publicadas)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
	public abstract List listarPadresUnidadAdministrativa(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativaAcceso(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativaAcceso(java.lang.Long)
	 */
	public abstract List listarPadresUnidadAdministrativaAcceso(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarBusinessKey()
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarBusinessKey()
	 */
	public abstract List listarBusinessKey() throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativas(java.util.Map, java.util.Map)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativas(java.util.Map, java.util.Map)
	 */
	public abstract List buscarUnidadesAdministrativas(Map parametros,
			Map traduccion) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativa(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativa(java.lang.Long)
	 */
	public abstract UnidadAdministrativa obtenerUnidadAdministrativa(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#informacionGeneral(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#informacionGeneral(java.lang.Long)
	 */
	public abstract UnidadAdministrativa informacionGeneral(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUAPormad(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUAPormad(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUAPormad(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativa(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativa(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativa(Long id)
			throws DelegateException;

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPORMAD(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPORMAD(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativaPORMAD(
			Long id) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorNombre(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorNombre(java.lang.String)
	 */
	public abstract UnidadAdministrativa obtenerUnidadAdministrativaPorNombre(
			String nombre) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
	public abstract UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(
			String codEst) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoPequenyaUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoPequenyaUA(java.lang.Long)
	 */
	public abstract Archivo obtenerFotoPequenyaUA(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoGrandeUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoGrandeUA(java.lang.Long)
	 */
	public abstract Archivo obtenerFotoGrandeUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoHorizontalUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoHorizontalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoHorizontalUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoVerticalUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoVerticalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoVerticalUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoSaludoUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoSaludoVerticalUA(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cambiarOrden(java.lang.Long, java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cambiarOrden(java.lang.Long, java.lang.Long)
	 */
	public abstract void cambiarOrden(Long ua1_id, Long ua2_id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#anyadirEdificio(java.lang.Long, java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#anyadirEdificio(java.lang.Long, java.lang.Long)
	 */
	public abstract void anyadirEdificio(Long edificio_id, Long ua_id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#eliminarEdificio(java.lang.Long, java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#eliminarEdificio(java.lang.Long, java.lang.Long)
	 */
	public abstract void eliminarEdificio(Long edificio_id, Long ua_id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadPorBusinessKey(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadPorBusinessKey(java.lang.String)
	 */
	public abstract UnidadAdministrativa obtenerUnidadPorBusinessKey(String bk)
			throws DelegateException;
	
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarUnidadAdministrativa(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarUnidadAdministrativa(java.lang.Long)
	 */
	public abstract void borrarUnidadAdministrativa(Long id) 
	 		throws DelegateException;
	
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarUnidadAdministrativaRaiz(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarUnidadAdministrativa(java.lang.Long)
	 */
	public abstract void borrarUnidadAdministrativaRaiz(Long id) 
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#fijarBusinessKey(java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#fijarBusinessKey(java.lang.Long, java.lang.String)
	 */
	public abstract void fijarBusinessKey(Long id, String businessKey)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarBusinessKey(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#borrarBusinessKey(java.lang.String)
	 */
	public abstract boolean borrarBusinessKey(String businessKey)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidad(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidad(java.lang.Long)
	 */
	public abstract UnidadAdministrativa cargarArbolUnidad(Long id)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidadId(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidadId(java.lang.Long)
	 */
	public abstract List cargarArbolUnidadId(Long id) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#repararOrdenFichasUA()
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#repararOrdenFichasUA()
	 */
	public abstract void repararOrdenFichasUA() throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasPorAmbito(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasPorAmbito(java.lang.Long)
	 */
	public abstract List<UnidadAdministrativa> listarUnidadesAdministrativasPorAmbito(
			Long ambito) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativasPorAmbito(java.lang.Long, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarUnidadesAdministrativasPorAmbito(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public abstract List<UnidadAdministrativa> buscarUnidadesAdministrativasPorAmbito(
			Long ambito, final String busqueda, final String idioma)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUAEspacioTerritorial(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUAEspacioTerritorial(java.lang.Long)
	 */
	public abstract List<UnidadAdministrativa> listarUAEspacioTerritorial(
			Long idEspacio) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String, java.util.List)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String, java.util.List)
	 */
	public abstract UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo, List UAOpcionales)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadesAdministrativasArbreTerritorial(java.lang.Long, java.lang.String)
	 */
	public abstract UnidadAdministrativa obtenerUnidadesAdministrativasArbreTerritorial(
			Long idEspacio, String tipo) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscar(java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscar(java.lang.String, java.lang.String)
	 */
	public abstract List<UnidadAdministrativa> buscar(final String busqueda,
			final String idioma) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecMat(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecMat(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public abstract Map<String, List<?>> listarProcFichSecMat(final Long idUA,
			final Long idMat, final String ceSec) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecMat(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecMat(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	public abstract List<Ficha> listarFichSecMat(final Long idUA,
			final Long idMat, final String ceSec, boolean caducados)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcMat(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcMat(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
	public abstract List<ProcedimientoLocal> listarProcMat(final Long idUA,
			final Long idMat, final String[] codMat, boolean include,
			boolean caducados) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecHV(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcFichSecHV(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	public abstract Map<String, List<?>> listarProcFichSecHV(final Long idUA,
			final Long idHV, final String ceSec) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecHV(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarFichSecHV(java.lang.Long, java.lang.Long, java.lang.String, boolean)
	 */
	public abstract List<Ficha> listarFichSecHV(final Long idUA,
			final Long idHV, final String ceSec, boolean caducados)
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcHV(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarProcHV(java.lang.Long, java.lang.Long, java.lang.String[], boolean, boolean)
	 */
	public abstract List<ProcedimientoLocal> listarProcHV(final Long idUA,
			final Long idHV, final String[] codMat, boolean include,
			boolean caducados) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarProcedimientosUA(java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarProcedimientosUA(java.lang.Long, java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public abstract List<ProcedimientoLocal> buscarProcedimientosUA(
			final Long idUA, final String busqueda, final String idioma,
			final Date dataInici, final Date dataFi) throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarFichasUA(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#buscarFichasUA(java.lang.String, java.lang.String, java.util.Date, java.util.Date)
	 */
	public abstract Set<Ficha> buscarFichasUA(final String busqueda, final String idioma,final Date dataInici, final Date dataFi ) 
			throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexInsertaUA(org.ibit.rol.sac.model.UnidadAdministrativa, org.ibit.lucene.indra.model.ModelFilterObject)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexInsertaUA(org.ibit.rol.sac.model.UnidadAdministrativa, org.ibit.lucene.indra.model.ModelFilterObject)
	 */
	public abstract void indexInsertaUA(UnidadAdministrativa ua,
			ModelFilterObject filter) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexBorraUA(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#indexBorraUA(java.lang.Long)
	 */
	public abstract void indexBorraUA(Long id) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFilterObject(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFilterObject(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	public abstract ModelFilterObject obtenerFilterObject(
			UnidadAdministrativa ua) throws DelegateException;

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMolla(java.lang.Long, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMolla(java.lang.Long, java.lang.String)
	 */
	public abstract StringBuffer getUaMolla(Long idua, String _idioma)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMollaBack2(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#getUaMollaBack2(java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	public abstract StringBuffer getUaMollaBack2(Long idua, String _idioma, String url, String uaIdPlaceholder)
			throws DelegateException;

	
    public boolean autorizarEliminarUA(Long idUa)
    		throws DelegateException;

    /* (non-Javadoc)
	 * Descripcion: Comprobar si el usuario tiene privilegios para crear una UA.
	 */
	public Boolean autorizarCrearUA() throws DelegateException;
    
    public void eliminarUaSinRelaciones(Long idUA)
    		throws DelegateException;
    
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPM(java.lang.Long)
	 */
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPM(java.lang.Long)
	 */
    public abstract UnidadAdministrativa obtenerUnidadAdministrativaPM(Long id) 
    		throws DelegateException;
}