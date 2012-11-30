package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Destinatario;
import org.ibit.rol.sac.persistence.intf.DestinatarioFacade;
import org.ibit.rol.sac.persistence.intf.DestinatarioFacadeHome;
import org.ibit.rol.sac.persistence.util.DestinatarioFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 12-jul-2007
 * Time: 9:22:36
 * To change this template use File | Settings | File Templates.
 */
public class DestinatarioDelegateImpl implements  StatelessDelegate, DestinatarioDelegateI{

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.DestinatarioDelegateI#grabarDestinatario(org.ibit.rol.sac.model.Destinatario)
	 */
    public Long grabarDestinatario(Destinatario destinatario) throws DelegateException {
        try {
            return getFacade().grabarDestinatario(destinatario);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.DestinatarioDelegateI#obtenerDestinatario(java.lang.Long)
	 */
    public Destinatario obtenerDestinatario(Long id) throws DelegateException {
        try {
            return getFacade().obtenerDestinatario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarDestinatarios(int pagina, int resultats) throws DelegateException {
    	try {
    		return getFacade().listarDestinatarios(pagina, resultats);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.DestinatarioDelegateI#listarDestinatarios()
	 */
    @SuppressWarnings("unchecked")
	public List<Destinatario> listarDestinatarios() throws DelegateException {
        try {
            return getFacade().listarDestinatarios();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.DestinatarioDelegateI#borrarDestinatario(java.lang.Long)
	 */
    public void borrarDestinatario(Long id) throws DelegateException {
        try {
            getFacade().borrarDestinatario(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private DestinatarioFacade getFacade() throws RemoteException {
        return (DestinatarioFacade) facadeHandle.getEJBObject();
    }

    protected DestinatarioDelegateImpl() throws DelegateException {
        try {
            DestinatarioFacadeHome home = DestinatarioFacadeUtil.getHome();
            DestinatarioFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
}
