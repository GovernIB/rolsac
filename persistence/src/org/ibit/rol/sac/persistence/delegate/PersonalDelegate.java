package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.persistence.intf.PersonalFacade;
import org.ibit.rol.sac.persistence.intf.PersonalFacadeHome;
import org.ibit.rol.sac.persistence.util.PersonalFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Business delegate para manipular Personal.
 */
public class PersonalDelegate implements StatelessDelegate{
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
    public Long grabarPersonal(Personal personal, Long unidAdmin_id) throws DelegateException {
       try {
           return getFacade().grabarPersonal(personal, unidAdmin_id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }

    public Personal obtenerPersonal(Long id) throws DelegateException {
       try {
           return getFacade().obtenerPersonal(id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }

    public List listarPersonal() throws DelegateException {
       try {
           return getFacade().listarPersonal();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }
    
    public List listarPersonalFiltro(Map parametros) throws DelegateException {
        try {
            return getFacade().listarPersonalFiltro(parametros);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
     }

    public Set listarPersonalUA(Long unidadAdmin_id) throws DelegateException {
       try {
           return getFacade().listarPersonalUA(unidadAdmin_id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }

    public void borrarPersonal(Long id) throws DelegateException {
       try {
           getFacade().borrarPersonal(id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

     private Handle facadeHandle;

    private PersonalFacade getFacade() throws RemoteException {
        return (PersonalFacade) facadeHandle.getEJBObject();
    }

    protected PersonalDelegate() throws DelegateException {
        try {
            PersonalFacadeHome home = PersonalFacadeUtil.getHome();
            PersonalFacade remote = home.create();
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
