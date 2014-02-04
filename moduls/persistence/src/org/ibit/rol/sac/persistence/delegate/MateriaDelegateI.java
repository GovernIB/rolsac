package org.ibit.rol.sac.persistence.delegate;

import java.util.List;
import java.util.Set;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.IconoMateria;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface MateriaDelegateI
{
	public abstract Long grabarMateria(Materia materia)
			throws DelegateException;
	
	public abstract ResultadoBusqueda listarMaterias(int pagina, int resultados, String lang)
			throws DelegateException;
	
	public abstract List<Materia> listarMaterias()
			throws DelegateException;
	
	public abstract Materia obtenerMateria(Long id)
			throws DelegateException;
	
	public abstract List<IconoMateria> obtenerIconosPerfil(Long id)
			throws DelegateException;
	
	public abstract void borrarMateria(Long id)
			throws DelegateException;
	
	public abstract Archivo obtenerDistribComp(Long id, String lang, boolean useDefault)
			throws DelegateException;
	
	public abstract Archivo obtenerNormativa(Long id, String lang, boolean useDefault)
			throws DelegateException;
	
	public abstract Archivo obtenerContenido(Long id, String lang, boolean useDefault)
			throws DelegateException;
	
	public abstract Archivo obtenerFoto(Long id)
			throws DelegateException;
	
	public abstract Archivo obtenerIcono(Long id)
			throws DelegateException;
	
	public abstract Archivo obtenerIconoGrande(Long id)
			throws DelegateException;
	
	public abstract List<Materia> obtenerMateriasPorIDs(String ids, String idioma)
			throws DelegateException;
	
	public abstract List<UnidadAdministrativa> listarUAsMateria(Long id)
			throws DelegateException;
	
}
