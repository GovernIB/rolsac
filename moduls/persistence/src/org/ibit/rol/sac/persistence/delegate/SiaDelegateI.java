package org.ibit.rol.sac.persistence.delegate;



public interface SiaDelegateI {

	public void enviarTodos() throws DelegateException;

	public void enviarPendientes() throws DelegateException;
		
}
