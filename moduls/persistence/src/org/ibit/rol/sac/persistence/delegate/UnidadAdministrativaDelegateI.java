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

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

public interface UnidadAdministrativaDelegateI {
	
	public ResultadoBusqueda buscadorUnidadesAdministrativas(Map<String, Object> parametros, Map<String, String> traduccion, Long id, String idioma, boolean uaFilles, boolean uaMeves, Long materia, String pagina, String resultats)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativaRaiz(org.ibit.rol.sac.model.UnidadAdministrativa)
	 */
	public abstract Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#crearUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public abstract Long crearUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.lang.Long)
	 */
	public abstract void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long padre_id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarEdificiosUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	public abstract void actualizarEdificiosUnidadAdministrativa(UnidadAdministrativa unidad, List<Long> idsNuevosEdificios)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#actualizarUsuariosUnidadAdministrativa(org.ibit.rol.sac.model.UnidadAdministrativa, java.util.List<java.lang.Long>)
	 */
	public abstract void actualizarUsuariosUnidadAdministrativa(UnidadAdministrativa unidad, List<Long> idsNuevosUsuarios)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarHijosUA(java.lang.Long)
	 */
	public abstract List listarHijosUA(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarUnidadesAdministrativasRaiz()
	 */
	public abstract List listarUnidadesAdministrativasRaiz()
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarTodasUnidadesAdministrativasRaiz()
	 */
	public abstract List listarTodasUnidadesAdministrativasRaiz()
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#listarPadresUnidadAdministrativa(java.lang.Long)
	 */
	public abstract List listarPadresUnidadAdministrativa(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativa(java.lang.Long)
	 */
	public abstract UnidadAdministrativa obtenerUnidadAdministrativa(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativa(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativa(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#consultarUnidadAdministrativaSinFichas(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#consultarUnidadAdministrativaPMA(java.lang.Long)
	 */
	public abstract UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerUnidadAdministrativaPorCodEstandar(java.lang.String)
	 */
	public abstract UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(
			String codEst) throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoPequenyaUA(java.lang.Long)
	 */
	public abstract Archivo obtenerFotoPequenyaUA(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerFotoGrandeUA(java.lang.Long)
	 */
	public abstract Archivo obtenerFotoGrandeUA(Long id)
			throws DelegateException;
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoHorizontalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoHorizontalUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoVerticalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoVerticalUA(Long id)
			throws DelegateException;

	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoSaludoUA(Long id)
			throws DelegateException;
	
	// CODI AFEGIT PELS LOGOS
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#obtenerLogoSaludoVerticalUA(java.lang.Long)
	 */
	public abstract Archivo obtenerLogoSaludoVerticalUA(Long id)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate2#cargarArbolUnidadId(java.lang.Long)
	 */
	public abstract List cargarArbolUnidadId(Long id)
			throws DelegateException;
	

	
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
	public Boolean autorizarCrearUA()
			throws DelegateException;
	
	public void eliminarUaSinRelaciones(Long idUA)
			throws DelegateException;
	
	public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre) 
			throws DelegateException;
	
	public abstract String obtenerCadenaFiltroUA(Long ua, boolean uaFilles, boolean uaMeves)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#listarSeccionesUA(java.lang.Long)
	 */
	public abstract List<Seccion> listarSeccionesUA(final Long idUA)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#cuentaFichasSeccionUA(java.lang.Long, java.lang.Long)
	 */
	public abstract int cuentaFichasSeccionUA(final Long idUA, final Long idSeccion)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#listarFichasSeccionUA(java.lang.Long, java.lang.Long)
	 */
	public abstract List<FichaDTO> listarFichasSeccionUA(final Long idUA, final Long idSeccion, String idioma, PaginacionCriteria paginacion)
			throws DelegateException;
	
	public abstract List<FichaDTO> listarFichasSeccionUASinPaginacion(final Long idUA, final Long idSeccion, String idioma)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#actualizaFichasSeccionUA(java.lang.Long, java.lang.Long, java.util.List)
	 */
	public abstract void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<FichaDTO> fichas)
			throws DelegateException;
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarFotoGrande(java.lang.Long)
	 */
    public abstract void eliminarFotoGrande(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarFotoPetita(java.lang.Long)
	 */
    public abstract void eliminarFotoPetita(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarLogoHorizontal(java.lang.Long)
	 */
    public abstract void eliminarLogoHorizontal(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarLogoVertical(java.lang.Long)
	 */
    public abstract void eliminarLogoVertical(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarLogoSalutacio(java.lang.Long)
	 */
    public abstract void eliminarLogoSalutacio(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarLogoTipos(java.lang.Long)
	 */
    public abstract void eliminarLogoTipos(Long idUA)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarSeccionUA(java.lang.Long, java.lang.Long)
	 */
    public abstract void eliminarSeccionUA(Long idUA, Long idSeccion)
    		throws DelegateException;
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarSeccionUA(java.lang.Long, java.lang.Long)
	 */
    public abstract SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, SolrPendiente solrPendiente) throws DelegateException;
    
    /* (non-Javadoc)
   	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarSeccionUA(java.lang.Long, java.lang.Long)
   	 */
       public abstract SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final Long idElemento, final EnumCategoria categoria) throws DelegateException;
        
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.UnidadAdministrativaDelegate#eliminarSeccionUA(java.lang.Long, java.lang.Long)
	 */
    public abstract SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, SolrPendiente solrPendiente) throws DelegateException;

	public abstract List<Long> buscarIdsUas() throws DelegateException;

	public List<Long> obtenerHijosUnidadAdministrativa(Long idUA) throws DelegateException;

}
