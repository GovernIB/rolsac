package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Familia;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface FamiliaDelegateI
{
	public abstract Long grabarFamilia(Familia familia)
			throws DelegateException;
	
	public abstract ResultadoBusqueda listarFamilias(int pagina, int resultats, String idioma)
			throws DelegateException;
	
	public abstract List listarFamilias()
			throws DelegateException;
	
	public abstract Familia obtenerFamilia(Long id)
			throws DelegateException;
	
	public abstract void borrarFamilia(Long id)
			throws DelegateException;
	
}
