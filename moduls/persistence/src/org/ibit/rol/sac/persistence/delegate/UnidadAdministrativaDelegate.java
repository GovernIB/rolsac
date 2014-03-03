package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.criteria.PaginacionCriteria;
import org.ibit.rol.sac.model.dto.FichaDTO;

import es.caib.rolsac.persistence.lucene.model.ModelFilterObject;
import es.caib.rolsac.utils.ResultadoBusqueda;

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
	
	public void actualizarUnidadAdministrativa(UnidadAdministrativa unidad, Long padreId) throws DelegateException {
		impl.actualizarUnidadAdministrativa(unidad, padreId);
	}
	
	public List cargarArbolUnidadId(Long id) throws DelegateException {
		return impl.cargarArbolUnidadId(id);
	}
	
	public UnidadAdministrativa consultarUnidadAdministrativa(Long id) throws DelegateException {
		return impl.consultarUnidadAdministrativa(id);
	}
	
	public UnidadAdministrativa consultarUnidadAdministrativaSinFichas(Long id) throws DelegateException {
		return impl.consultarUnidadAdministrativaSinFichas(id);
	}
	
	public UnidadAdministrativa consultarUnidadAdministrativaPMA(Long id)
			throws DelegateException {
		return impl.consultarUnidadAdministrativaPMA(id);
	}
	
	public Long crearUnidadAdministrativa(UnidadAdministrativa unidad, Long padreId) throws DelegateException {
		return impl.crearUnidadAdministrativa(unidad, padreId);
	}
	
	public Long crearUnidadAdministrativaRaiz(UnidadAdministrativa unidad) throws DelegateException {
		return impl.crearUnidadAdministrativaRaiz(unidad);
	}
	
	@Override
	public boolean equals(Object obj) {
		return impl.equals(obj);
	}
	
	public StringBuffer getUaMollaBack2(Long idua, String idioma, String url, String uaIdPlaceholder) throws DelegateException {
		return impl.getUaMollaBack2(idua, idioma, url, uaIdPlaceholder);
	}
	
	@Override
	public int hashCode() {
		return impl.hashCode();
	}
	
	public void indexBorraUA(Long id) throws DelegateException {
		impl.indexBorraUA(id);
	}
	
	public void indexInsertaUA(UnidadAdministrativa ua, ModelFilterObject filter) throws DelegateException {
		impl.indexInsertaUA(ua, filter);
	}
	
	public List listarHijosUA(Long id) throws DelegateException {
		return impl.listarHijosUA(id);
	}
	
	public List listarPadresUnidadAdministrativa(Long id) throws DelegateException {
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
	
	public UnidadAdministrativa obtenerUnidadAdministrativa(Long id) throws DelegateException {
		return impl.obtenerUnidadAdministrativa(id);
	}
	
	public UnidadAdministrativa obtenerUnidadAdministrativaPorCodEstandar(String codEst) throws DelegateException {
		return impl.obtenerUnidadAdministrativaPorCodEstandar(codEst);
	}
	
	@Override
	public String toString() {
		return impl.toString();
	}
	
	public String obtenerCadenaFiltroUA(Long ua, boolean uaFilles, boolean uaMeves) throws DelegateException {
		return impl.obtenerCadenaFiltroUA(ua, uaFilles, uaMeves);
	}
	
	public ResultadoBusqueda buscadorUnidadesAdministrativas(Map<String, Object> parametros, Map<String, String> traduccion, Long id, String idioma, boolean uaFilles, boolean uaMeves, Long materia, String pagina, String resultats) throws DelegateException {
		return impl.buscadorUnidadesAdministrativas(parametros, traduccion, id, idioma, uaFilles, uaMeves, materia, pagina, resultats);
	}	
	
	public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior, Long idPadre) throws DelegateException {
		impl.reordenar(id, ordenNuevo, ordenAnterior, idPadre);
	}
	
	public List<Seccion> listarSeccionesUA(Long idUA) throws DelegateException {
		return impl.listarSeccionesUA(idUA);
	}
	
	public int cuentaFichasSeccionUA(final Long idUA, final Long idSeccion) throws DelegateException {
		return impl.cuentaFichasSeccionUA(idUA, idSeccion);
	}
	
	public List<FichaDTO> listarFichasSeccionUA(Long idUA, Long idSeccion, String idioma, PaginacionCriteria paginacion) throws DelegateException {
		return impl.listarFichasSeccionUA(idUA, idSeccion, idioma, paginacion);
	}
	

	public void actualizaFichasSeccionUA(Long idUA, Long idSeccion, List<FichaDTO> fichas) throws DelegateException {
		impl.actualizaFichasSeccionUA(idUA, idSeccion, fichas);
	}
	
    public void eliminarFotoGrande(Long idUA)
    		throws DelegateException {
    	impl.eliminarFotoGrande(idUA);
    }
    
    public void eliminarFotoPetita(Long idUA)
    		throws DelegateException {
    	impl.eliminarFotoPetita(idUA);
    }
    
    public void eliminarLogoHorizontal(Long idUA)
    		throws DelegateException {
    	impl.eliminarLogoHorizontal(idUA);
    }
    
    public void eliminarLogoVertical(Long idUA)
    		throws DelegateException {
    	impl.eliminarLogoVertical(idUA);
    }
    
    public void eliminarLogoSalutacio(Long idUA)
    		throws DelegateException {
    	impl.eliminarLogoSalutacio(idUA);
    }
    
    public void eliminarLogoTipos(Long idUA)
    		throws DelegateException {
    	impl.eliminarLogoTipos(idUA);
    }
    
    public void eliminarSeccionUA(Long idUA, Long idSeccion)
    		throws DelegateException {
    	impl.eliminarSeccionUA(idUA, idSeccion);
    }
    
}
