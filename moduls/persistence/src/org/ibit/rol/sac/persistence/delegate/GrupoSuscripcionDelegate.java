package org.ibit.rol.sac.persistence.delegate;


import org.ibit.rol.sac.model.GrupoSuscripcion;
import org.ibit.rol.sac.persistence.intf.GrupoSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.GrupoSuscripcionFacadeHome;
import org.ibit.rol.sac.persistence.util.GrupoSuscripcionFacadeUtil;


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
public class GrupoSuscripcionDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
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
	
	public Long grabar(GrupoSuscripcion grupo)  throws DelegateException {
        try {
            return getFacade().grabar(grupo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public void borrarGrupo(Long id) throws DelegateException {
        try {
            getFacade().borrarGrupo(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public List listarGrupos() throws DelegateException {
        try {
            return getFacade().listarGrupos();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarCombo(Long idTipo) throws DelegateException {
    	try {
    		return getFacade().listarCombo(idTipo);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }


    public GrupoSuscripcion obtenerGrupo(Long id) throws DelegateException {
        try {
            return getFacade().obtenerGrupo(id);
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

    private GrupoSuscripcionFacade getFacade() throws RemoteException {
        return (GrupoSuscripcionFacade) facadeHandle.getEJBObject();
    }

    protected GrupoSuscripcionDelegate() throws DelegateException {
        try {
        	GrupoSuscripcionFacadeHome home = GrupoSuscripcionFacadeUtil.getHome();
        	GrupoSuscripcionFacade remote = home.create();
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
