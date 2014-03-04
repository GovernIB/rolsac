package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.lucene.model.ModelFilterObject;
import es.caib.rolsac.utils.ResultadoBusqueda;

public interface FichaDelegateI {

	public abstract boolean autorizaCrearFicha(Integer validacionNormativa)
			throws DelegateException;

	public abstract boolean autorizaModificarFicha(Long idFicha)
			throws DelegateException;

	public abstract Long grabarFicha(Ficha ficha) throws DelegateException;
	
	public abstract ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) throws DelegateException;
	
	public abstract Ficha obtenerFicha(Long id) throws DelegateException;
	
	public abstract Archivo obtenerIconoFicha(Long id) throws DelegateException;

	public abstract Archivo obtenerImagenFicha(Long id)
			throws DelegateException;

	public abstract Archivo obtenerBanerFicha(Long id) throws DelegateException;
	
	public abstract void borrarFicha(Long id) throws DelegateException;
	
	@SuppressWarnings("unchecked")
	public abstract List<Ficha> listarFichasSeccionUA(Long ua_id,
			String codEstSecc, String[] codEstHV, String[] codEstMat)
			throws DelegateException;
	
	public abstract List listarFichasSeccionTodas(Long id)
			throws DelegateException;
	
	public abstract Long crearFichaUA2(Long unidad_id, Long seccion_id, Long ficha_id)
			throws DelegateException;
	
	public abstract void borrarFichaUA(Long id) throws DelegateException;
	
	public abstract void indexInsertaFicha(Ficha fic, ModelFilterObject filter)
			throws DelegateException;

	public abstract void indexBorraFicha(Long id) throws DelegateException;
	
	public abstract ModelFilterObject obtenerFilterObject(Ficha ficha)
			throws DelegateException;

	public abstract Hashtable getContenidos_web() throws DelegateException;

	public abstract void setContenidos_web(Hashtable contenidos_web)
			throws DelegateException;
	
	public abstract List<FichaUA> listFichasUA(Long idFicha)
			throws DelegateException;

	public abstract Ficha obtenerFichaPMA(Long id) throws DelegateException;
	
	public int buscarFichasActivas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException;
	   
	public int buscarFichasCaducadas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException;
	
	public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) throws DelegateException;
	
	public Ficha obtenerFichaDeFichaUA(Long idFichaUA) throws DelegateException;
	
}
