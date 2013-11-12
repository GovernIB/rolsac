package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Destinatario;

import es.caib.rolsac.utils.ResultadoBusqueda;

public interface DestinatarioDelegateI
{
	public abstract Long grabarDestinatario(Destinatario destinatario)
			throws DelegateException;
	
	public abstract Destinatario obtenerDestinatario(Long id)
			throws DelegateException;
	
	public abstract ResultadoBusqueda listarDestinatarios(int pagina, int resultats)
			throws DelegateException;
	
	@SuppressWarnings("unchecked")
	public abstract List<Destinatario> listarDestinatarios()
			throws DelegateException;
	
	public abstract void borrarDestinatario(Long id)
			throws DelegateException;
	
}
