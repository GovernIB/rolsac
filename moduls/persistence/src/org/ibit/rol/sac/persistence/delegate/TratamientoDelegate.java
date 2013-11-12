package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.persistence.intf.TratamientoFacade;
import org.ibit.rol.sac.persistence.intf.TratamientoFacadeHome;
import org.ibit.rol.sac.persistence.util.TratamientoFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Tratamientos.
 */
public class TratamientoDelegate implements StatelessDelegate{
		
	private static final long serialVersionUID = -3796251379077427483L;

	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
    public Long grabarTratamiento(Tratamiento tratamiento) throws DelegateException {
        try {
            return getFacade().grabarTratamiento(tratamiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Tratamiento obtenerTratamiento(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTratamiento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public ResultadoBusqueda listarTratamientos(int pagina, int resultats) throws DelegateException {
    	try {
    		return getFacade().listarTratamientos(pagina, resultats);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public List<Tratamiento> listarTratamientos() throws DelegateException {
        try {
            return getFacade().listarTratamientos();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public boolean tieneUnidades(Long id) throws DelegateException {
        try {
            return getFacade().tieneUnidades(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarTratamiento(Long id) throws DelegateException {
        try {
            getFacade().borrarTratamiento(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

     private Handle facadeHandle;

    private TratamientoFacade getFacade() throws RemoteException {
        return (TratamientoFacade) facadeHandle.getEJBObject();
    }

    protected TratamientoDelegate() throws DelegateException {
        try {
            TratamientoFacadeHome home = TratamientoFacadeUtil.getHome();
            TratamientoFacade remote = home.create();
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
