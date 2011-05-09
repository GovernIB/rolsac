package org.ibit.rol.sac.persistence.delegate;



import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.Handle;
import javax.naming.NamingException;


import org.ibit.rol.sac.model.TipoSuscripcion;
import org.ibit.rol.sac.persistence.util.TipoSuscripcionFacadeUtil;
import org.ibit.rol.sac.persistence.intf.TipoSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.TipoSuscripcionFacadeHome;
import org.ibit.rol.sac.model.Usuario;




/**
 * Business delegate para manipular Microsite.
 */
public class TipoSuscripcionDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarTipoSuscripcion(TipoSuscripcion site) throws DelegateException {
        try {
            return getFacade().grabarTipoSuscripcion(site);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public TipoSuscripcion obtenerTipoSuscripcion(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTipoSuscripcion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
 

    
    public List listarTiposSuscripcion() throws DelegateException {
        try {
            return getFacade().listarTiposSuscripcion();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    	
    	public List listarTiposSuscripcionFiltro(Usuario usu, Map param) throws DelegateException {
            try {
                return getFacade().listarTiposSuscripcionFiltro(usu, param);
            } catch (RemoteException e) {
                throw new DelegateException(e);
            }
        }
    	
    public void borrarTipoSuscripcion(Long id) throws DelegateException {
        try {
            getFacade().borrarTipoSuscripcion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    public Hashtable getParametros() throws DelegateException {
   	 try {
   		 return getFacade().getParametros();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
   }

   public void parametrosCons() throws DelegateException {
   	try {
   		getFacade().parametrosCons();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }
   
   public int getPagina() throws DelegateException {
   	try {
   		return getFacade().getPagina();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   public void setPagina(int pagina) throws DelegateException {
   	try {
   		getFacade().setPagina(pagina);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   public void setOrderby(String orderby) throws DelegateException {
   	try {
   		getFacade().setOrderby(orderby);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   public String getValorBD(String valor) throws DelegateException {
   	try {
   		return getFacade().getValorBD(valor);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }
   
   public void setFiltro(String valor) throws DelegateException {
   	try {
   		getFacade().setFiltro(valor);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }    

   


   
   public String getUsuarioEJB() throws DelegateException {
	   	try {
	   		return getFacade().getUsuarioEJB();
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
   }   
   
   
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TipoSuscripcionFacade getFacade() throws RemoteException {
        return (TipoSuscripcionFacade) facadeHandle.getEJBObject();
    }

    protected TipoSuscripcionDelegate() throws DelegateException {
        try {
        	TipoSuscripcionFacadeHome home = TipoSuscripcionFacadeUtil.getHome();
        	TipoSuscripcionFacade remote = home.create();
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



