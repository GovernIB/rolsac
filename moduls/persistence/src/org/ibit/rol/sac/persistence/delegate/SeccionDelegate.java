package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.webcaib.LinkModel;
import org.ibit.rol.sac.persistence.intf.SeccionFacade;
import org.ibit.rol.sac.persistence.intf.SeccionFacadeHome;
import org.ibit.rol.sac.persistence.util.SeccionFacadeUtil;

/**
 * Business delegate para manipular Secciones.
 */
public class SeccionDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
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

    
    //WEBCAIB//
    public Collection getPares ( Long codi, String idioma ) throws DelegateException{
    	try {
            return getFacade().getPares(codi, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection getFills ( Long codi, String idioma ) throws DelegateException {
    	try {
            return getFacade().getFills(codi, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public Collection getArrels ( String idioma ) throws DelegateException {
    	try {
            return getFacade().getArrels(idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public Collection getLinks ( Long codi, int inicial, int tamany, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinks(codi, inicial, tamany, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection getLinksByOrderRange ( Long codi, int ordre_i, int ordre_f, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByOrderRange(codi, ordre_i, ordre_f, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    public Collection getLinksByOrderRangeAndUO ( Long codi, int ordre_i, int ordre_f, String coduo, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByOrderRangeAndUO(codi, ordre_i, ordre_f, coduo, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Collection getLinksByUO ( Long codi, Long coduo, int inicial, int tamany, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByUO(codi, coduo, inicial, tamany, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }    
    
    public Collection getLinksByUORSS ( Long codi, Long coduo, int inicial, int tamany, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByUORSS(codi, coduo, inicial, tamany, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection getLinksByMat ( Long codi, Long codmat, int inicial, int tamany, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByMat(codi, codmat, inicial, tamany, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Collection getLinksByMat ( Long codmat, int inicial, int tamany, String idioma, int valida, Date fultenv ) throws DelegateException {
    	try {
            return getFacade().getLinksByMat(codmat, inicial, tamany, idioma, valida, fultenv);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    } 
    
    public Collection getLinksByMatForo ( Long codi, Long codmat, int inicial, int tamany, String idioma, int valida ) throws DelegateException {
    	try {
            return getFacade().getLinksByMatForo(codi, codmat, inicial, tamany, idioma, valida);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }  
    
    public LinkModel getLink(Long codi, String idioma, int valida, String previ) throws DelegateException {
    	try {
            return getFacade().getLink(codi, idioma, valida, previ);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }
    
    public Integer getNumLinks(Long codi) throws DelegateException {
    	try {
            return getFacade().getNumLinks(codi);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }    
    
    public Integer getNumLinksByUO(Long codi, String coduo) throws DelegateException {
    	try {
            return getFacade().getNumLinksByUO(codi, coduo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }    
    
    public Integer getNumLinksByMat(Long codi, String materia) throws DelegateException {
    	try {
            return getFacade().getNumLinksByMat(codi, materia);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }    
    
    public String getData(Long codiFitxa) throws DelegateException {
    	try {
            return getFacade().getData(codiFitxa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }    	
    }     
    
    public void grabarSeccion(Seccion seccio, Long idPadre) throws DelegateException {
    	try {
    		
    		if (seccio.getId() != null) getFacade().actualizarSeccion(seccio, idPadre);
    		else getFacade().crearSeccion(seccio, idPadre);
    		
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
