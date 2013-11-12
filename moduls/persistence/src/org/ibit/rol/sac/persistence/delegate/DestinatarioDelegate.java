package org.ibit.rol.sac.persistence.delegate;

import java.util.List;

import org.ibit.rol.sac.model.Destinatario;

import es.caib.rolsac.utils.ResultadoBusqueda;


public class DestinatarioDelegate
{
	DestinatarioDelegateI impl;

	public DestinatarioDelegateI getImpl() {
		return impl;
	}
	
	public void setImpl(DestinatarioDelegateI impl) {
		this.impl = impl;
    }
	
	public void borrarDestinatario(Long id) throws DelegateException {
		impl.borrarDestinatario(id);
	}
	
	public Long grabarDestinatario(Destinatario destinatario) throws DelegateException {
		return impl.grabarDestinatario(destinatario);
	}
    
	public ResultadoBusqueda listarDestinatarios(int pagina, int resultats) throws DelegateException {
		return impl.listarDestinatarios(pagina, resultats);
	}
	
	public List<Destinatario> listarDestinatarios() throws DelegateException {
		return impl.listarDestinatarios();
	}
	
	public Destinatario obtenerDestinatario(Long id) throws DelegateException {
		return impl.obtenerDestinatario(id);
    }
    
}
