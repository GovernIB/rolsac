package org.ibit.rol.sac.persistence.delegate;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface FichaDelegateI {

	public abstract boolean autorizaCrearFicha(Integer validacionNormativa)
			throws DelegateException;

	public abstract boolean autorizaModificarFicha(Long idFicha)
			throws DelegateException;

	public abstract Long grabarFicha(Ficha ficha) throws DelegateException;

	public abstract List buscarFichas(Map parametros, Map traduccion)
			throws DelegateException;

	public abstract List buscarFichas(String texto) throws DelegateException;
	
	public abstract ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible) throws DelegateException;
	
	public abstract List buscarFichas(Map parametros, String traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden) throws DelegateException;	

	public abstract List listarFichas() throws DelegateException;

	public abstract List listarFichasCrawler() throws DelegateException;

	/**
	 * Lista todas las fichas y solo rellena el idioma por defecto. Los unicos atributos que se rellenan son id, titulo y url.
	 * @return List de fichas
	 * @throws DelegateException
	 */
	public abstract List listarFichasThin() throws DelegateException;

	public abstract Ficha obtenerFicha(Long id) throws DelegateException;
	
	public abstract List buscarFichasMateria(Long id) throws DelegateException;

	public abstract List buscarFichasHuerfanas() throws DelegateException;

	public abstract void anyadirMateria(Long materia_id, Long ficha_id)
			throws DelegateException;

	public abstract void eliminarMateria(Long materia_id, Long ficha_id)
			throws DelegateException;

	public abstract Archivo obtenerIconoFicha(Long id) throws DelegateException;

	public abstract Archivo obtenerImagenFicha(Long id)
			throws DelegateException;

	public abstract Archivo obtenerBanerFicha(Long id) throws DelegateException;

	public abstract List listarFichasUnidad(Long unidad_id)
			throws DelegateException;

	public abstract void borrarFicha(Long id) throws DelegateException;

	public abstract List listarFichasSeccionUA(Long ua_id, Long seccion_id)
			throws DelegateException;

	@SuppressWarnings("unchecked")
	public abstract List<Ficha> listarFichasSeccionUA(Long ua_id,
			String codEstSecc, String[] codEstHV, String[] codEstMat)
			throws DelegateException;

	public abstract List listarFichasSeccionUA(Long ua_id, String codEstSecc)
			throws DelegateException;

	public abstract List listarFichasSeccion(Long id) throws DelegateException;

	public abstract List listarFichasSeccionTodas(Long id)
			throws DelegateException;

	/*PORMAD*/
	public abstract List listarFichasSeccion(String codEstSecc,
			boolean caducados) throws DelegateException;

	public abstract Long crearFichaUA(Long unidad_id, Long seccion_id,
			Long ficha_id) throws DelegateException;
	
	public abstract Long crearFichaUA2(Long unidad_id, Long seccion_id,
			Long ficha_id) throws DelegateException;

	public abstract void subirFichaUA(Long id) throws DelegateException;

	public abstract void actualizarOrdenFichasUA(Enumeration params, Map valores)
			throws DelegateException;

	public abstract void borrarFichaUA(Long id) throws DelegateException;

	public abstract List buscarFichasHechoVital(Long id)
			throws DelegateException;

	public abstract void anyadirHechoVital(Long id, Long ficha_id)
			throws DelegateException;

	public abstract void eliminarHechoVital(Long id, Long ficha_id)
			throws DelegateException;

	public abstract void indexInsertaFicha(Ficha fic, ModelFilterObject filter)
			throws DelegateException;

	public abstract void indexBorraFicha(Long id) throws DelegateException;

	public abstract List getFichasMicrosite(String idsite)
			throws DelegateException;

	/**
	 * Devuelve las posibles fichas relacionadas con un foro.<br/>
	 * 
	 * @param idforo, String identificador del foro.
	 * @return List, lista de beans "Ficha".
	 * @throws DelegateException
	 */
	public abstract List getFichasForo(String idforo) throws DelegateException;

	/**
	 * Devuelve las posibles fichas relacionadas con un tema.<br/>
	 * 
	 * @param idtema, String identificador del tema.
	 * @return List, lista de beans "Ficha".
	 * @throws DelegateException
	 */
	public abstract List getFichasTema(String idtema) throws DelegateException;

	public abstract ModelFilterObject obtenerFilterObject(Ficha ficha)
			throws DelegateException;

	public abstract Hashtable getContenidos_web() throws DelegateException;

	public abstract void setContenidos_web(Hashtable contenidos_web)
			throws DelegateException;

	/**
	 * * Busca todos los {@link Ficha} que contengan la palabra de la busqueda que
	 * contengan una URL indexada en el crawler
	 */
	public abstract List<Ficha> buscarFichasCrawler(final String busqueda,
			final String idioma, final Date dataInici, final Date dataFi)
			throws DelegateException;

	public abstract List<FichaUA> listFichasUA(Long idFicha)
			throws DelegateException;

	public abstract Ficha obtenerFichaPMA(Long id) throws DelegateException;
	
	public int buscarFichasActivas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException;
	   
	public int buscarFichasCaducadas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException;
	
	public void crearSeccionesFichas( UnidadAdministrativa ua, String[] listaSeccionesFicha ) throws DelegateException;
	
	public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) throws DelegateException;
	
	public Ficha obtenerFichaDeFichaUA(Long idFichaUA) throws DelegateException;
	
}