package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.StatelessDelegate;
import org.ibit.rol.sac.persistence.intf.AgrupacionHVFacade;
import org.ibit.rol.sac.persistence.intf.AgrupacionHVFacadeHome;
import org.ibit.rol.sac.persistence.util.AgrupacionHVFacadeUtil;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Agrupaciones Hechos Vitales.
 */
public class AgrupacionHVDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarAgrupacionHV(AgrupacionHechoVital hechov) throws DelegateException {
        try {
            return getFacade().grabarAgrupacionHV(hechov);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<AgrupacionHechoVital> listarAgrupacionHV() throws DelegateException {
        try {
            return getFacade().listarAgrupacionHV();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public AgrupacionHechoVital obtenerAgrupacionHV(Long id) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionHV(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public AgrupacionHechoVital obtenerAgrupacionHVPorNombre(String nombre) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionHVPorNombre(nombre);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarAgrupacionHV(Long id) throws DelegateException {
        try {
            getFacade().borrarAgrupacionHV(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Archivo obtenerFoto(Long id) throws DelegateException {
    	try {
            return getFacade().obtenerFoto(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerIcono(Long id) throws DelegateException {
    	try {
            return getFacade().obtenerIcono(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Archivo obtenerIconoGrande(Long id) throws DelegateException {
    	try {
            return getFacade().obtenerIconoGrande(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<HechoVitalAgrupacionHV> listarAgrupacionesHVHechosVitales() throws DelegateException {
        try {
            return getFacade().listarAgrupacionesHVHechosVitales();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<HechoVitalAgrupacionHV> listarAgrupacionesHVPorPublicoObjetivo(Long idPubObj) throws DelegateException {
        try {
            return getFacade().listarAgrupacionesHVPorPublicoObjetivo(idPubObj);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<AgrupacionHechoVital> buscar(final String busqueda, final String idioma) throws DelegateException {
        try {
            return getFacade().buscar(busqueda, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Long guardarAgrupacionHV(AgrupacionHechoVital hechov, List<HechoVitalAgrupacionHV> llistaFetsVitalsOld) throws DelegateException {
    	 try {
             return getFacade().guardarAgrupacionHV(hechov,llistaFetsVitalsOld);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
		
	}
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private AgrupacionHVFacade getFacade() throws RemoteException {
        return (AgrupacionHVFacade) facadeHandle.getEJBObject();
    }

    protected AgrupacionHVDelegate() throws DelegateException {
        try {
            AgrupacionHVFacadeHome home = AgrupacionHVFacadeUtil.getHome();
            AgrupacionHVFacade remote = home.create();
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
