package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface MateriaDelegateI {

	public abstract Long grabarMateria(Materia materia)
			throws DelegateException;

    public abstract ResultadoBusqueda listarMaterias(int pagina, int resultados) throws DelegateException;
    
	public abstract List<Materia> listarMaterias() throws DelegateException;

	public abstract List<Materia> listarMateriasFront() throws DelegateException;

	public abstract List<Materia> listarMateriasFrontDestacadas(String lang)
			throws DelegateException;

	public abstract Materia obtenerMateria(Long id) throws DelegateException;

	public abstract boolean tieneProcedimientosOFichas(Long id)
			throws DelegateException;

	public abstract List<Materia> listarMateriasbyUA(Long ua)
			throws DelegateException;

	public abstract Set<MateriaAgrupacionM> obtenerGruposMateria(Long idmateria)
			throws DelegateException;

	public abstract void borrarMateria(Long id) throws DelegateException;

	public abstract Archivo obtenerDistribComp(Long id, String lang,
			boolean useDefault) throws DelegateException;

	public abstract Archivo obtenerNormativa(Long id, String lang,
			boolean useDefault) throws DelegateException;

	public abstract Archivo obtenerContenido(Long id, String lang,
			boolean useDefault) throws DelegateException;

	public abstract Archivo obtenerFoto(Long id) throws DelegateException;

	public abstract Archivo obtenerIcono(Long id) throws DelegateException;

	public abstract Archivo obtenerIconoGrande(Long id)
			throws DelegateException;

	public abstract Set<Materia> obtenerMateriasCE(
			final String[] codigosEstandarMateria) throws DelegateException;

	public abstract Materia obtenerMateriaCE(final String codigosEstandarMateria)
			throws DelegateException;

	public abstract List<Materia> buscar(final String busqueda,
			final String idioma) throws DelegateException;

	public abstract Materia obtenerMateriaFichasProced(Long id)
			throws DelegateException;
	
}