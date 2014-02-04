package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular materias.
 * 
 * 
 * ejaen@dgtic  - u92770
 * Classe desacoplada del standard EJB per permetre unit testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError).
 * 
 * Recordar: per fer aquestes modificacions cal modificar la classe DelegateUtil. 
 * 
 */

 public class MateriaDelegate {

	MateriaDelegateI impl;

	public MateriaDelegateI getImpl() {
		return impl;
	}	

	public void setImpl(MateriaDelegateI impl) {
		this.impl = impl;
	}
	
	public Long grabarMateria(Materia materia) throws DelegateException {
		return impl.grabarMateria(materia);
	}

    public ResultadoBusqueda listarMaterias(int pagina, int resultados, String lang) throws DelegateException {
    	return impl.listarMaterias(pagina, resultados, lang);
    }
    
	public List<Materia> listarMaterias() throws DelegateException {
		return impl.listarMaterias();
	}
	
	public Materia obtenerMateria(Long id) throws DelegateException {
		return impl.obtenerMateria(id);
	}
	
	public List<IconoMateria> obtenerIconosPerfil(Long id) throws DelegateException {
		return impl.obtenerIconosPerfil(id);
	}
	
	public void borrarMateria(Long id) throws DelegateException {
		impl.borrarMateria(id);
	}

	public Archivo obtenerDistribComp(Long id, String lang,
			boolean useDefault) throws DelegateException {
		return impl.obtenerDistribComp(id, lang, useDefault);
	}

	public Archivo obtenerNormativa(Long id, String lang, boolean useDefault)
			throws DelegateException {
		return impl.obtenerNormativa(id, lang, useDefault);
	}

	public Archivo obtenerContenido(Long id, String lang, boolean useDefault)
			throws DelegateException {
		return impl.obtenerContenido(id, lang, useDefault);
	}

	public Archivo obtenerFoto(Long id) throws DelegateException {
		return impl.obtenerFoto(id);
	}

	public Archivo obtenerIcono(Long id) throws DelegateException {
		return impl.obtenerIcono(id);
	}
	
	public Archivo obtenerIconoGrande(Long id) throws DelegateException {
		return impl.obtenerIconoGrande(id);
	}
	
	public List<Materia> obtenerMateriasPorIDs(String ids, String idioma)
			throws DelegateException {
		return impl.obtenerMateriasPorIDs(ids, idioma);
	}
	
	public List<UnidadAdministrativa> listarUAsMateria(Long id)
			throws DelegateException {
		return impl.listarUAsMateria(id);
	}
		
}
