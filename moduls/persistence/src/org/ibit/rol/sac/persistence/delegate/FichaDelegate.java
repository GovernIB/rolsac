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
	
	
	public boolean autorizaCrearFicha(Integer validacionNormativa) throws DelegateException {
		return impl.autorizaCrearFicha(validacionNormativa);
	}

	
	public boolean autorizaModificarFicha(Long idFicha)
			throws DelegateException {
		return impl.autorizaModificarFicha(idFicha);
	}

	
	public Long grabarFicha(Ficha ficha) throws DelegateException {
		return impl.grabarFicha(ficha);
	}
	
	
	public ResultadoBusqueda buscarFichas(Map parametros, Map traduccion, UnidadAdministrativa ua, Long idFetVital, Long idMateria, Long idPublic, boolean uaFilles, boolean uaMeves, String campoOrdenacion, String orden, String pagina, String resultats, int campoVisible)
           throws DelegateException {
	   return impl.buscarFichas(parametros, traduccion, ua, idFetVital, idMateria, idPublic, uaFilles, uaMeves, campoOrdenacion, orden, pagina, resultats, campoVisible);
	}
	
	
	public Ficha obtenerFicha(Long id) throws DelegateException {
		return impl.obtenerFicha(id);
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
	
	
	public void borrarFicha(Long id) throws DelegateException {
		impl.borrarFicha(id);
	}
	
	
	public List<Ficha> listarFichasSeccionUA(Long ua_id, String codEstSecc,
			String[] codEstHV, String[] codEstMat) throws DelegateException {
		return impl.listarFichasSeccionUA(ua_id, codEstSecc, codEstHV,
				codEstMat);
	}
	
	
	public List listarFichasSeccionTodas(Long id) throws DelegateException {
		return impl.listarFichasSeccionTodas(id);
	}
	
	
	public Long crearFichaUA2(Long unidad_id, Long seccion_id, Long ficha_id)
			throws DelegateException {
		return impl.crearFichaUA2(unidad_id, seccion_id, ficha_id);
	}
	
	
	public void borrarFichaUA(Long id) throws DelegateException {
		impl.borrarFichaUA(id);
	}
	
	
	public void indexInsertaFicha(Ficha fic, ModelFilterObject filter)
			throws DelegateException {
		impl.indexInsertaFicha(fic, filter);
	}

	
	public void indexBorraFicha(Long id) throws DelegateException {
		impl.indexBorraFicha(id);
	}
	
	public ModelFilterObject obtenerFilterObject(Ficha ficha)
			throws DelegateException {
		return impl.obtenerFilterObject(ficha);
	}

	
	public Hashtable getContenidos_web() throws DelegateException {
		return impl.getContenidos_web();
	}

	
	public void setContenidos_web(Hashtable contenidos_web) throws DelegateException {
		impl.setContenidos_web(contenidos_web);
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
	
	
	public void borrarFichasUAdeFicha(List<FichaUA> fichasUA) throws DelegateException {
		impl.borrarFichasUAdeFicha(fichasUA);
	}
	
	public Ficha obtenerFichaDeFichaUA(Long idFichaUA) throws DelegateException {
		return impl.obtenerFichaDeFichaUA(idFichaUA);
	}
	
}
