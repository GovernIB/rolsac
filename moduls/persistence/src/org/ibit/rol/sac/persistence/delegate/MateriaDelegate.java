package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

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

	    public ResultadoBusqueda listarMaterias(int pagina, int resultados) throws DelegateException {
	    	return impl.listarMaterias(pagina, resultados);
	    }
	    
		public List<Materia> listarMaterias() throws DelegateException {
			return impl.listarMaterias();
		}

		public List<Materia> listarMateriasFront() throws DelegateException {
			return impl.listarMateriasFront();
		}

		public List<Materia> listarMateriasFrontDestacadas(String lang)
				throws DelegateException {
			return impl.listarMateriasFrontDestacadas(lang);
		}

		public Materia obtenerMateria(Long id) throws DelegateException {
			return impl.obtenerMateria(id);
		}

		public boolean tieneProcedimientosOFichas(Long id)
				throws DelegateException {
			return impl.tieneProcedimientosOFichas(id);
		}

		public List<Materia> listarMateriasbyUA(Long ua)
				throws DelegateException {
			return impl.listarMateriasbyUA(ua);
		}

		public Set<MateriaAgrupacionM> obtenerGruposMateria(Long idmateria)
				throws DelegateException {
			return impl.obtenerGruposMateria(idmateria);
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

		public Set<Materia> obtenerMateriasCE(String[] codigosEstandarMateria)
				throws DelegateException {
			return impl.obtenerMateriasCE(codigosEstandarMateria);
		}

		public Materia obtenerMateriaCE(String codigosEstandarMateria)
				throws DelegateException {
			return impl.obtenerMateriaCE(codigosEstandarMateria);
		}

		public List<Materia> buscar(String busqueda, String idioma)
				throws DelegateException {
			return impl.buscar(busqueda, idioma);
		}

		public Materia obtenerMateriaFichasProced(Long id)
				throws DelegateException {
			return impl.obtenerMateriaFichasProced(id);
		}
}
