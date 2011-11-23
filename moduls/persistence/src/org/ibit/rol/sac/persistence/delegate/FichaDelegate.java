package org.ibit.rol.sac.persistence.delegate;

import org.ibit.lucene.indra.model.ModelFilterObject;
import org.ibit.rol.sac.persistence.intf.FichaFacade;
import org.ibit.rol.sac.persistence.intf.FichaFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaFacadeUtil;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular fichas.
 * ejaen@dgtic  - u92770
 * Classe desacoplada del standard EJB2.0 per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 *  
 */
public class FichaDelegate implements FichaDelegateI {

	FichaDelegateI impl;

	
	public FichaDelegateI getImpl() {
		return impl;
	}	

	public void setImpl(FichaDelegateI impl) {
		this.impl = impl;
	}
	
		
	public boolean autorizaCrearFicha(Integer validacionNormativa)
			throws DelegateException {
		return impl.autorizaCrearFicha(validacionNormativa);
	}

	public boolean autorizaModificarFicha(Long idFicha)
			throws DelegateException {
		return impl.autorizaModificarFicha(idFicha);
	}

	public Long grabarFicha(Ficha ficha) throws DelegateException {
		return impl.grabarFicha(ficha);
	}

	public List buscarFichas(Map parametros, Map traduccion)
			throws DelegateException {
		return impl.buscarFichas(parametros, traduccion);
	}

	public List buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, boolean uaFilles, boolean uaMeves)
           throws DelegateException {
	   return impl.buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, uaFilles, uaMeves);
}
	
	public List buscarFichas(String texto) throws DelegateException {
		return impl.buscarFichas(texto);
	}

	public List listarFichas() throws DelegateException {
		return impl.listarFichas();
	}

	public List listarFichasCrawler() throws DelegateException {
		return impl.listarFichasCrawler();
	}

	public List listarFichasThin() throws DelegateException {
		return impl.listarFichasThin();
	}

	public Ficha obtenerFicha(Long id) throws DelegateException {
		return impl.obtenerFicha(id);
	}

	public List buscarFichasMateria(Long id) throws DelegateException {
		return impl.buscarFichasMateria(id);
	}

	public List buscarFichasHuerfanas() throws DelegateException {
		return impl.buscarFichasHuerfanas();
	}

	public void anyadirMateria(Long materia_id, Long ficha_id)
			throws DelegateException {
		impl.anyadirMateria(materia_id, ficha_id);
	}

	public void eliminarMateria(Long materia_id, Long ficha_id)
			throws DelegateException {
		impl.eliminarMateria(materia_id, ficha_id);
	}

	public Archivo obtenerIconoFicha(Long id) throws DelegateException {
		return impl.obtenerIconoFicha(id);
	}

	public Archivo obtenerImagenFicha(Long id) throws DelegateException {
		return impl.obtenerImagenFicha(id);
	}

	public Archivo obtenerBanerFicha(Long id) throws DelegateException {
		return impl.obtenerBanerFicha(id);
	}

	public List listarFichasUnidad(Long unidad_id) throws DelegateException {
		return impl.listarFichasUnidad(unidad_id);
	}

	public void borrarFicha(Long id) throws DelegateException {
		impl.borrarFicha(id);
	}

	public List listarFichasSeccionUA(Long ua_id, Long seccion_id)
			throws DelegateException {
		return impl.listarFichasSeccionUA(ua_id, seccion_id);
	}

	public List<Ficha> listarFichasSeccionUA(Long ua_id, String codEstSecc,
			String[] codEstHV, String[] codEstMat) throws DelegateException {
		return impl.listarFichasSeccionUA(ua_id, codEstSecc, codEstHV,
				codEstMat);
	}

	public List listarFichasSeccionUA(Long ua_id, String codEstSecc)
			throws DelegateException {
		return impl.listarFichasSeccionUA(ua_id, codEstSecc);
	}

	public List listarFichasSeccion(Long id) throws DelegateException {
		return impl.listarFichasSeccion(id);
	}

	public List listarFichasSeccionTodas(Long id) throws DelegateException {
		return impl.listarFichasSeccionTodas(id);
	}

	public List listarFichasSeccion(String codEstSecc, boolean caducados)
			throws DelegateException {
		return impl.listarFichasSeccion(codEstSecc, caducados);
	}

	public Long crearFichaUA(Long unidad_id, Long seccion_id, Long ficha_id)
			throws DelegateException {
		return impl.crearFichaUA(unidad_id, seccion_id, ficha_id);
	}

	public void subirFichaUA(Long id) throws DelegateException {
		impl.subirFichaUA(id);
	}

	public void actualizarOrdenFichasUA(Enumeration params, Map valores)
			throws DelegateException {
		impl.actualizarOrdenFichasUA(params, valores);
	}

	public void borrarFichaUA(Long id) throws DelegateException {
		impl.borrarFichaUA(id);
	}

	public List buscarFichasHechoVital(Long id) throws DelegateException {
		return impl.buscarFichasHechoVital(id);
	}

	public void anyadirHechoVital(Long id, Long ficha_id)
			throws DelegateException {
		impl.anyadirHechoVital(id, ficha_id);
	}

	public void eliminarHechoVital(Long id, Long ficha_id)
			throws DelegateException {
		impl.eliminarHechoVital(id, ficha_id);
	}

	public void indexInsertaFicha(Ficha fic, ModelFilterObject filter)
			throws DelegateException {
		impl.indexInsertaFicha(fic, filter);
	}

	public void indexBorraFicha(Long id) throws DelegateException {
		impl.indexBorraFicha(id);
	}

	public List getFichasMicrosite(String idsite) throws DelegateException {
		return impl.getFichasMicrosite(idsite);
	}

	public List getFichasForo(String idforo) throws DelegateException {
		return impl.getFichasForo(idforo);
	}

	public List getFichasTema(String idtema) throws DelegateException {
		return impl.getFichasTema(idtema);
	}

	public ModelFilterObject obtenerFilterObject(Ficha ficha)
			throws DelegateException {
		return impl.obtenerFilterObject(ficha);
	}

	public Hashtable getContenidos_web() throws DelegateException {
		return impl.getContenidos_web();
	}

	public void setContenidos_web(Hashtable contenidos_web)
			throws DelegateException {
		impl.setContenidos_web(contenidos_web);
	}

	public List<Ficha> buscarFichasCrawler(String busqueda, String idioma,
			Date dataInici, Date dataFi) throws DelegateException {
		return impl.buscarFichasCrawler(busqueda, idioma, dataInici, dataFi);
	}

	public List<FichaUA> listFichasUA(Long idFicha) throws DelegateException {
           return impl.listFichasUA(idFicha);
	}

    public Ficha obtenerFichaPMA(Long id) throws DelegateException {
           return impl.obtenerFichaPMA(id);
	}
	
    
    public int buscarFichasActivas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad) throws DelegateException {
		return impl.buscarFichasActivas(listaUnidadAdministrativaId,fechaCaducidad);
	}
	public int buscarFichasCaducadas(List<Long> listaUnidadAdministrativaId, Date fechaCaducidad )throws DelegateException {
		return impl.buscarFichasCaducadas(listaUnidadAdministrativaId,fechaCaducidad);
	}


}
