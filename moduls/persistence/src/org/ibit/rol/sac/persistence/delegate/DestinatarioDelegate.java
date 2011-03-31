package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.DestinatarioFacade;
import org.ibit.rol.sac.persistence.intf.DestinatarioFacadeHome;
import org.ibit.rol.sac.persistence.util.DestinatarioFacadeUtil;
import org.ibit.rol.sac.model.Destinatario;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;


public class DestinatarioDelegate {

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

	public Long grabarDestinatario(Destinatario destinatario)
			throws DelegateException {
		return impl.grabarDestinatario(destinatario);
    }
    
	public List<Destinatario> listarDestinatarios() throws DelegateException {
		return impl.listarDestinatarios();
        }

	public Destinatario obtenerDestinatario(Long id) throws DelegateException {
		return impl.obtenerDestinatario(id);
    }
    
}
