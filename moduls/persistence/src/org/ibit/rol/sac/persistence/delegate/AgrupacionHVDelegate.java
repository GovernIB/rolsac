package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.persistence.intf.AgrupacionHVFacade;
import org.ibit.rol.sac.persistence.intf.AgrupacionHVFacadeHome;
import org.ibit.rol.sac.persistence.util.AgrupacionHVFacadeUtil;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate para manipular Agrupaciones Hechos Vitales.
 */
public class AgrupacionHVDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public AgrupacionHechoVital obtenerAgrupacionHV(Long id) throws DelegateException {
        try {
            return getFacade().obtenerAgrupacionHV(id);
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

	public ResultadoBusqueda listarAgrupacionesHVHechosVitales(int pagina, int resultats, String idioma) throws DelegateException {
        try {
            return getFacade().listarAgrupacionesHVHechosVitales(pagina, resultats, idioma);
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
