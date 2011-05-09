package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.persistence.intf.TratamientoFacade;
import org.ibit.rol.sac.persistence.intf.TratamientoFacadeHome;
import org.ibit.rol.sac.persistence.util.TratamientoFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Tratamientos.
 */
public class TratamientoDelegate implements StatelessDelegate{
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

    public List listarTratamientos() throws DelegateException {
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
    
    public Tratamiento obtenerTratamientoCE(final String codigosEstandar) throws DelegateException{
    	try {
            return getFacade().obtenerTratamientoCE(codigosEstandar);
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
