package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Iniciacion;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface IniciacionDelegateI {

	public abstract Iniciacion obtenerIniciacion(Long id)
			throws DelegateException;

	public abstract ResultadoBusqueda listarIniciacion(int pagina, int resultats, String idioma) throws DelegateException;
	
	public abstract List listarIniciacion() throws DelegateException;

	public abstract Long grabarIniciacion(Iniciacion tipo)
			throws DelegateException;

	public abstract void borrarIniciacion(Long id) throws DelegateException;
	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

}