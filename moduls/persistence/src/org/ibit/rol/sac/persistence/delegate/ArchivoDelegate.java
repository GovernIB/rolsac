package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.intf.ArchivoFacade;
import org.ibit.rol.sac.persistence.intf.ArchivoFacadeHome;
import org.ibit.rol.sac.persistence.util.ArchivoFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import java.io.ByteArrayOutputStream;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class ArchivoDelegate  implements StatelessDelegate {

	
        
        	
	/* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

	public Archivo obtenerArchivo(Long id)  throws DelegateException {
        try {
            return getFacade().obtenerArchivo(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	
	/* ========================================================= */
    /* ================= M�TODOS DE NEGOCIO WEBCAIB ============ */
    /* ========================================================= */	
	
    public ByteArrayOutputStream getFitxer(Long id) throws DelegateException {
    	try {
    		return getFacade().getFitxer(id);
    	
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    	
    }
    

    public String getMime(Long id) throws DelegateException {    
    	try {
    		return getFacade().getMime(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}

    }
    

    public long getPes(Long id) throws DelegateException {       	
    	try {
    		return getFacade().getPes(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }    
    

    public String getNombre(Long id) throws DelegateException {
    	try {
    		return getFacade().getNombre(id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }	
	
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private ArchivoFacade getFacade() throws RemoteException {
        return (ArchivoFacade) facadeHandle.getEJBObject();
    }

    protected ArchivoDelegate() throws DelegateException {
        try {
        	ArchivoFacadeHome home = ArchivoFacadeUtil.getHome();
        	ArchivoFacade remote = home.create();
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
