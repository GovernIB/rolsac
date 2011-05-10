package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.EnvioSuscripcion;
import org.ibit.rol.sac.model.HistoricoEnvio;
import org.ibit.rol.sac.persistence.intf.EnvioSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.EnvioSuscripcionFacadeHome;
import org.ibit.rol.sac.persistence.intf.HistoricoEnvioFacade;
import org.ibit.rol.sac.persistence.intf.HistoricoEnvioFacadeHome;
import org.ibit.rol.sac.persistence.util.EnvioSuscripcionFacadeUtil;
import org.ibit.rol.sac.persistence.util.HistoricoEnvioFacadeUtil;


import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Business delegate para manipular materias.
 */
public class HistoricoEnvioDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public void init() throws DelegateException {
        try {
        	getFacade().init();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }	
	
    public void init(Long id) throws DelegateException {
        try {
        	getFacade().init(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	

    public List listarHistoricos() throws DelegateException {
    	try {
    		return getFacade().listarHistoricos();
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
	
    public Long grabarHistoricoEnvio(HistoricoEnvio historico)  throws DelegateException {
        try {
            return getFacade().grabarHistoricoEnvio(historico);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    


    public List listarEnvios() throws DelegateException {
        try {
            return getFacade().listarEnvios();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }


    public HistoricoEnvio obtenerHistoricoEnvio(Long id) throws DelegateException {
        try {
            return getFacade().obtenerHistoricoEnvio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public HistoricoEnvio obtenerUltimoHistoricoEnvio() throws DelegateException {
    	try {
    		return getFacade().obtenerUltimoHistoricoEnvio();
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }

    public void borrarHistoricoEnvio(Long id) throws DelegateException {
        try {
            getFacade().borrarHistoricoEnvio(id);
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

      public void setOrderby2(String orderby) throws DelegateException {
   	   	try {
   	   		getFacade().setOrderby2(orderby);
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
      
      public String getWhere() throws DelegateException {
   	   	try {
   	   		return getFacade().getWhere();
   	       } catch (RemoteException e) {
   	           throw new DelegateException(e);
   	       }
   	   }
   	   
      public void setWhere(String valor) throws DelegateException {
   	   	try {
   	   		getFacade().setWhere(valor);
   	       } catch (RemoteException e) {
   	           throw new DelegateException(e);
   	       }
   	   }   

      public int getTampagina() throws DelegateException {
   	   	try {
   	   		return getFacade().getTampagina();
   	       } catch (RemoteException e) {
   	           throw new DelegateException(e);
   	       }
   	   }
   	   
      public void setTampagina(int tampagina) throws DelegateException {
   	   	try {
   	   		getFacade().setTampagina(tampagina);
   	       } catch (RemoteException e) {
   	           throw new DelegateException(e);
   	       }
      } 
   


    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private HistoricoEnvioFacade getFacade() throws RemoteException {
        return (HistoricoEnvioFacade) facadeHandle.getEJBObject();
    }

    protected HistoricoEnvioDelegate() throws DelegateException {
        try {
        	HistoricoEnvioFacadeHome home = HistoricoEnvioFacadeUtil.getHome();
        	HistoricoEnvioFacade remote = home.create();
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
