package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Destinatario;

public interface DestinatarioDelegateI {

	public abstract Long grabarDestinatario(Destinatario destinatario)
			throws DelegateException;

	public abstract Destinatario obtenerDestinatario(Long id)
			throws DelegateException;

	@SuppressWarnings("unchecked")
	public abstract List<Destinatario> listarDestinatarios()
			throws DelegateException;

	public abstract void borrarDestinatario(Long id) throws DelegateException;

}