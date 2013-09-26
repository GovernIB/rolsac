package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.StatelessDelegate;
import org.ibit.rol.sac.persistence.intf.AgrupacionMFacade;
import org.ibit.rol.sac.persistence.intf.AgrupacionMFacadeHome;
import org.ibit.rol.sac.persistence.util.AgrupacionMFacadeUtil;
import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Agrupaciones Materias.
 */
public class AgrupacionMDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	/** @deprecated  Se utiliza desde el back antiguo */
    public Long grabarAgrupacionM(AgrupacionMateria agrMateria) throws DelegateException {
        try {
            return getFacade().guardarAgrupacionMaterias(agrMateria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated  Se utiliza desde el back antiguo */
    @SuppressWarnings("unchecked")
	public List<AgrupacionMateria> listarAgrupacionM() throws DelegateException {
        try {
            return getFacade().listarAgrupacionM();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public AgrupacionMateria obtenerAgrupacionM(Long id) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionMaterias(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated  No se utiliza */
    public AgrupacionMateria obtenerAgrupacionMPorNombre(String nombre) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionMPorNombre(nombre);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarAgrupacionM(Long id) throws DelegateException {
        try {
            getFacade().borrarAgrupacionM(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /** @deprecated  No se utiliza */
    @SuppressWarnings("unchecked")
	public List<MateriaAgrupacionM> listarAgrupacionesMMaterias() throws DelegateException {
        try {
            return getFacade().listarAgrupacionesMMaterias();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated  No se utiliza */
    @SuppressWarnings("unchecked")
    public List<AgrupacionMateria> buscar(final String nombre, final String idioma) throws DelegateException {
    	try {
            return getFacade().buscar(nombre, idioma);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private AgrupacionMFacade getFacade() throws RemoteException {
        return (AgrupacionMFacade) facadeHandle.getEJBObject();
    }

    protected AgrupacionMDelegate() throws DelegateException {
        try {
            AgrupacionMFacadeHome home = AgrupacionMFacadeUtil.getHome();
            AgrupacionMFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Long guardarAgrupacionM(AgrupacionMateria agrMateria, List<MateriaAgrupacionM> listaMateriasAsignadas) throws DelegateException {
        try {
            return getFacade().guardarAgrupacionMaterias(agrMateria, listaMateriasAsignadas);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
}
