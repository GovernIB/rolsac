package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.persistence.intf.SeccionFacade;
import org.ibit.rol.sac.persistence.intf.SeccionFacadeHome;
import org.ibit.rol.sac.persistence.util.SeccionFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Business delegate para manipular Secciones.
 */
public class SeccionDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== M�TODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long crearSeccion(Seccion seccion, Long padre_id)
            throws DelegateException {
        try {
            return getFacade().crearSeccion(seccion, padre_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void actualizarSeccion(Seccion seccion, Long padre_id)
            throws DelegateException {
        try {
            getFacade().actualizarSeccion(seccion, padre_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void actualizarOrdenFichasSeccion(Long id, Enumeration params, Map valores)
    		throws DelegateException {
    	try {
    		getFacade().actualizarOrdenFichasSeccion(id, params, valores);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }

    public void actualizarOrdenFichasSeccionHuecos(Long id)	throws DelegateException {
    	try {
    		getFacade().actualizarOrdenFichasSeccionHuecos(id);
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

    public Seccion obtenerSeccion(String codigo) throws DelegateException {
            try {
                return getFacade().obtenerSeccion(codigo);
            } catch (RemoteException e) {
                throw new DelegateException(e);
            }
        }


    public Seccion obtenerSeccionPorNombre(String nombre) throws DelegateException {
        try {
            return getFacade().obtenerSeccionPorNombre(nombre);
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

    public void borrarSeccion(Long id) throws DelegateException {
        try {
            getFacade().borrarSeccion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarSeccionesPadreUA(Long id_unidad) throws DelegateException {
        try {
            return getFacade().listarSeccionesPadreUA(id_unidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Seccion obtenerSeccionCE(final String codigosEstandar) throws DelegateException{
    	try {
            return getFacade().obtenerSeccionCE(codigosEstandar);
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
