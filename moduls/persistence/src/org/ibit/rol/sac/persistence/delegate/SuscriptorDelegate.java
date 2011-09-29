package org.ibit.rol.sac.persistence.delegate;




import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.persistence.intf.SuscriptorFacade;
import org.ibit.rol.sac.persistence.intf.SuscriptorFacadeHome;
import org.ibit.rol.sac.persistence.util.SuscriptorFacadeUtil;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Business delegate para manipular materias.
 */
public class SuscriptorDelegate implements StatelessDelegate {

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
	
    public void init(Long id, String estado, String grupo) throws DelegateException {
        try {
        	getFacade().init(id, estado, grupo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public Long grabarSuscriptor(Suscriptor suscriptor)  throws DelegateException {
        try {
            return getFacade().grabarSuscriptor(suscriptor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public void borrarSuscriptor(Long id) throws DelegateException {
        try {
            getFacade().borrarSuscriptor(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public List listar() throws DelegateException {
        try {
            return getFacade().listar();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarPorEstado(String estado, Long idGrupo) throws DelegateException {
        try {
            return getFacade().listarPorEstado(estado,idGrupo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarPorEstadoTipo(String idtipo, String estado, Long idGrupo) throws DelegateException {
        try {
            return getFacade().listarPorEstadoTipo(idtipo, estado, idGrupo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    public List listarPorMail(String mail) throws DelegateException {
        try {
            return getFacade().listarPorMail(mail);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarPorEstadoCombinacion(String estado, String combinacion) throws DelegateException {
        try {
            return getFacade().listarPorEstadoCombinacion(estado, combinacion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Set actualizaResumenTemas(Long idTipo) throws DelegateException {
    	try {
    		return getFacade().actualizaResumenTemas(idTipo);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }

    public List<Suscriptor> getSuscriptoresByTipo(Long idTipo) throws DelegateException {
    	try {
    		return getFacade().getSuscriptoresByTipo(idTipo);
    		
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }

    
    public Set recuperaCombinaciones(Long idTipo) throws DelegateException {
    	try {
    		return getFacade().recuperaCombinaciones(idTipo);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    
    public Suscriptor obtenerSuscriptor(Long id) throws DelegateException {
        try {
            return getFacade().obtenerSuscriptor(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Suscriptor obtenerSuscriptor(Long tipo , String correo) throws DelegateException {
        try {
            return getFacade().obtenerSuscriptor(tipo, correo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /**
     * Obtiene un suscriptor a partir del correo.<br/>
     * En caso de no existir, devuelve <em>null</em>.
     * @param correo, String con el mail del suscriptor
     * @return Suscriptor
     * @throws DelegateException
     */
    public Suscriptor obtenerSuscriptorbyMail(String correo) throws DelegateException {
        try {
            return getFacade().obtenerSuscriptorbyMail(correo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Suscriptor obtenerSuscriptorbyMailEstado(String correo,String estado) throws DelegateException {
        try {
            return getFacade().obtenerSuscriptorbyMailEstado(correo,estado);
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
    
        public boolean existeSuscriptor(String email, Long idTipo) throws DelegateException {
         	try {
         		return getFacade().existeSuscriptor(email, idTipo);
             } catch (RemoteException e) {
                 throw new DelegateException(e);
             }
         }  
        
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private SuscriptorFacade getFacade() throws RemoteException {
        return (SuscriptorFacade) facadeHandle.getEJBObject();
    }

    protected SuscriptorDelegate() throws DelegateException {
        try {
        	SuscriptorFacadeHome home = SuscriptorFacadeUtil.getHome();
        	SuscriptorFacade remote = home.create();
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
