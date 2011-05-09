package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Idioma;

public interface IdiomaDelegateI {

	public abstract List listarLenguajes() throws DelegateException;

	public abstract String lenguajePorDefecto() throws DelegateException;

	public abstract List<Idioma> listarIdiomas() throws DelegateException;

	public abstract List listarLenguajesTraductor() throws DelegateException;;
	
	/**
	 * Devuelve el tiempo que tarda en listar los idiomas.<br/>
	 * @return long, tiempo en milisegundos
	 * @throws DelegateException
	 */
	public abstract long testeoHql() throws DelegateException;



}