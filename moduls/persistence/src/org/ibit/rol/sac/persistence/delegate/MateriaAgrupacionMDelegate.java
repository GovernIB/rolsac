package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.persistence.intf.MateriaAgrupacionMFacade;
import org.ibit.rol.sac.persistence.intf.MateriaAgrupacionMFacadeHome;
import org.ibit.rol.sac.persistence.util.MateriaAgrupacionMFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular MateriaAgrupacionM.
 */
public class MateriaAgrupacionMDelegate implements StatelessDelegate
{
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */
	
	public ResultadoBusqueda listarAgrupacionMaterias(int pagina, int resultats) throws DelegateException
	{
		try {
			return getFacade().listarAgrupacionMaterias(pagina, resultats);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* ========================================================= */
	/* ======================== REFERENCIA AL FACADE  ========== */
	/* ========================================================= */
	
	private Handle facadeHandle;
	
    private MateriaAgrupacionMFacade getFacade() throws RemoteException {
        return (MateriaAgrupacionMFacade) facadeHandle.getEJBObject();
    }
    
    protected MateriaAgrupacionMDelegate() throws DelegateException {
        try {
        	MateriaAgrupacionMFacadeHome home = MateriaAgrupacionMFacadeUtil.getHome();
        	MateriaAgrupacionMFacade remote = home.create();
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
