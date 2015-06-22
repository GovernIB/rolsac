package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.persistence.intf.SeccionFacade;
import org.ibit.rol.sac.persistence.intf.SeccionFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaUAFichaIds;
import org.ibit.rol.sac.persistence.util.SeccionFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Secciones.
 */
public class SeccionDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public void actualizarSeccion(Seccion seccion, Long padre_id) throws DelegateException {
    	try {
    		getFacade().actualizarSeccion(seccion, padre_id);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public void subirOrden(Long idSeccion) throws DelegateException {
        try {
            getFacade().subirOrden(idSeccion);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Seccion obtenerSeccion(Long id) throws DelegateException {
        try {
            return getFacade().obtenerSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Seccion obtenerSeccionSinFichasUA(Long id) throws DelegateException {
        try {
            return getFacade().obtenerSeccionSinFichasUA(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public ResultadoBusqueda listarSeccionesRaiz(int pagina, int resultats, String idioma) throws DelegateException {
    	try {
    		return getFacade().listarSeccionesRaiz(pagina, resultats, idioma);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public List listarSeccionesRaiz() throws DelegateException {
        try {
            return getFacade().listarSeccionesRaiz();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarSecciones() throws DelegateException {
        try {
            return getFacade().listarSecciones();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarSeccionesRaizPerfil() throws DelegateException {
    	try {
    		return getFacade().listarSeccionesRaizPerfil();
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public List listarAntecesoresSeccion(Long id) throws DelegateException {
        try {
            return getFacade().listarAntecesoresSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarHijosSeccion(Long id) throws DelegateException {
        try {
            return getFacade().listarHijosSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarDescendienteSeccion(Long id) throws DelegateException {
	    try {
	        return getFacade().listarDescendienteSeccion(id);
	    } catch (RemoteException e) {
	        throw new DelegateException(e);
	    }
	}

    public void borrarSeccion(Long id) throws DelegateException {
        try {
            getFacade().borrarSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Seccion obtenerSeccionCE(final String codigosEstandar) throws DelegateException {
    	try {
            return getFacade().obtenerSeccionCE(codigosEstandar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void grabarSeccion(Seccion seccio, Long idPadre) throws DelegateException {
    	try {
    		if (seccio.getId() != null) {
    			getFacade().actualizarSeccion(seccio, idPadre);
    		} else {
    			getFacade().crearSeccion(seccio, idPadre);
    		}
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void reordenar(Long id, Integer ordenNuevo, Integer ordenAnterior) throws DelegateException {
    	try {
    		getFacade().reordenar(id, ordenNuevo, ordenAnterior);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
    public String obtenerCadenaFiltroSeccion() throws DelegateException {
    	try {
    		return getFacade().obtenerCadenaFiltroSeccion();
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
    
   public Seccion actualizarPerfilesGestorSeccion(Seccion seccion, List<Long> idsNuevosPerfiles) throws DelegateException {
     	try {
    		return getFacade().actualizarPerfilesGestorSeccion(seccion, idsNuevosPerfiles);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }
   
   public List<Seccion> listarSeccionesRaizPerfilGestor(Long idSeccio) throws DelegateException {
    	try {
   		return getFacade().listarSeccionesRaizPerfilGestor(idSeccio);
   	} catch (RemoteException e) {
   		throw new DelegateException(e);
   	}
   }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private SeccionFacade getFacade() throws RemoteException {
        return (SeccionFacade) facadeHandle.getEJBObject();
    }
    
    protected SeccionDelegate() throws DelegateException {
        try {
            SeccionFacadeHome home = SeccionFacadeUtil.getHome();
            SeccionFacade remote = home.create();
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
