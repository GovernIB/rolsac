package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.persistence.intf.BoletinFacade;
import org.ibit.rol.sac.persistence.intf.BoletinFacadeHome;
import org.ibit.rol.sac.persistence.util.BoletinFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular boletines.
 */
public class BoletinDelegate implements StatelessDelegate
{
	private static final long serialVersionUID = -4660868505069232251L;

	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	public Long grabarBoletin(Boletin boletin) throws DelegateException {
        try {
            return getFacade().grabarBoletin(boletin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public ResultadoBusqueda listarBoletines(int pagina, int resultats) throws DelegateException {
        try {
            return getFacade().listarBoletines(pagina, resultats);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public List listarBoletines() throws DelegateException {
		try {
			return getFacade().listarBoletines();
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}

	public Boletin obtenerBoletin(Long id) throws DelegateException {
		try {
			return getFacade().obtenerBoletin(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void borrarBoletin(Long id) throws DelegateException {
		try {
			getFacade().borrarBoletin(id);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */

	private Handle facadeHandle;

	private BoletinFacade getFacade() throws RemoteException {
		return (BoletinFacade) facadeHandle.getEJBObject();
	}

	protected BoletinDelegate() throws DelegateException {
		try {
			BoletinFacadeHome home = BoletinFacadeUtil.getHome();
			BoletinFacade remote = home.create();
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
