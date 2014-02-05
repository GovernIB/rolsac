package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Idioma;

public interface IdiomaDelegateI {

	public abstract List<String> listarLenguajes()
			throws DelegateException;

	public abstract String lenguajePorDefecto()
			throws DelegateException;

	public abstract List<String> listarLenguajesTraductor()
			throws DelegateException;

	public abstract List<Idioma> listarIdiomas()
	        throws DelegateException;

	public abstract Idioma obtenerIdioma(String lang)
        throws DelegateException;

	public abstract void grabarIdioma(Idioma idioma)
	    throws DelegateException;

	public abstract void borrarIdioma(String lang)
        throws DelegateException;

	public abstract void reordenar(String lang, Integer ordenNuevo, Integer ordenAnterior)
        throws DelegateException;

}
