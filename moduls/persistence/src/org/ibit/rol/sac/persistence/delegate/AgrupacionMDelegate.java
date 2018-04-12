package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.intf.AgrupacionMFacade;
import org.ibit.rol.sac.persistence.intf.AgrupacionMFacadeHome;
import org.ibit.rol.sac.persistence.util.AgrupacionMFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Agrupaciones Materias.
 */
public class AgrupacionMDelegate implements StatelessDelegate {
	
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public AgrupacionMateria obtenerAgrupacionM(Long id) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionMaterias(id);
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
    
    public Long guardarAgrupacionM(AgrupacionMateria agrMateria, List<MateriaAgrupacionM> listaMateriasAsignadas) throws DelegateException {
        try {
            return getFacade().guardarAgrupacionMaterias(agrMateria, listaMateriasAsignadas);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<MateriaAgrupacionM> obtenerMateriasAgrupacion(Long id) throws DelegateException {
    	
    	try {
            return getFacade().obtenerMateriasAgrupacion(id);
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

	public ResultadoBusqueda consultaAgrupacionMaterias(FiltroGenerico filtro) throws DelegateException {
	  	try {
            return getFacade().consultaAgrupacionMaterias(filtro);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
}
