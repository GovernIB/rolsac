package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.SilencioAdm;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface SilencioAdmDelegateI
{
	public abstract String grabarSilencioAdm(SilencioAdm silencio, boolean edicion)
			throws DelegateException;
	
	public abstract ResultadoBusqueda listarSilencioAdm(int pagina, int resultats, String idioma)
			throws DelegateException;
	
	public abstract List listarSilencioAdm()
			throws DelegateException;
	
	public abstract SilencioAdm obtenerSilencioAdm(String codigo)
			throws DelegateException;
	
	public abstract void borrarSilencioAdm(String codigo)
			throws DelegateException;
	
}
