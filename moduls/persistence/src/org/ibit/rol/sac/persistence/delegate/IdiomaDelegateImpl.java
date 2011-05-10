package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.persistence.intf.IdiomaFacade;
import org.ibit.rol.sac.persistence.intf.IdiomaFacadeHome;
import org.ibit.rol.sac.persistence.util.IdiomaFacadeUtil;
import org.ibit.rol.sac.model.Idioma;

/**
 * Business delegate pera consultar idiomas.
 */
public class IdiomaDelegateImpl extends IdiomaDelegate implements  StatelessDelegate, IdiomaDelegateI {

    // Cache de lengaujes
    private List lenguajes = null;
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {
        return ((System.currentTimeMillis() - time) > maxtime);
    }

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IdiomaDelegateI#listarLenguajes()
	 */
    public List listarLenguajes() throws DelegateException {
        try {
            if (lenguajes == null || timeout(timeLen)) {
                lenguajes = getFacade().listarLenguajes();
                timeLen = System.currentTimeMillis();
            }
            return lenguajes;
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IdiomaDelegateI#lenguajePorDefecto()
	 */
    public String lenguajePorDefecto() throws DelegateException {
        try {
            if (porDefecto == null || timeout(timeDef)) {
                porDefecto = getFacade().lenguajePorDefecto();
                timeDef = System.currentTimeMillis();
            }
            return porDefecto;
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

     /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IdiomaDelegateI#listarIdiomas()
	 */
    public List<Idioma> listarIdiomas() throws DelegateException {
        try {
            return getFacade().listarIdiomas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    @Override
    public List listarLenguajesTraductor() throws DelegateException {
    	   try {
               return getFacade().listarLenguajesTraductor();
           } catch (RemoteException e) {
               throw new DelegateException(e);
           }
    }

     /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IdiomaDelegateI#testeoHql()
	 */
     public long testeoHql() throws DelegateException {
         try {
             return getFacade().testeoHql();
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
     }
     
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private IdiomaFacade getFacade() throws RemoteException {
        return (IdiomaFacade) facadeHandle.getEJBObject();
    }

    protected IdiomaDelegateImpl() throws DelegateException {
        try {
            IdiomaFacadeHome home = IdiomaFacadeUtil.getHome();
            IdiomaFacade remote = home.create();
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
