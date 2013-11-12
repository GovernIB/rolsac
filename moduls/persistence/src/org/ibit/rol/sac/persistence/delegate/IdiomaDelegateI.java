package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Idioma;

public interface IdiomaDelegateI
{
	public abstract List<String> listarLenguajes()
			throws DelegateException;
	
	public abstract String lenguajePorDefecto()
			throws DelegateException;
	
	public abstract List<String> listarLenguajesTraductor()
			throws DelegateException;
	
}
