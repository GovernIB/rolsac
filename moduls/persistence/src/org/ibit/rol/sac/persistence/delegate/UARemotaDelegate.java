package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.AdministracionRemota;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.persistence.intf.UARemotaFacade;
import org.ibit.rol.sac.persistence.intf.UARemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.UARemotaFacadeUtil;

import es.caib.rolsac.lucene.model.ModelFilterObject;

/**
 * Business delegate para manipular fichas.
 */
public class UARemotaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public Long grabarUARemota(UnidadAdministrativaRemota uaRemota, Long idPadre , String ceTratamiento ,String[] ceMaterias) throws DelegateException{
		try {
            return getFacade().grabarUARemota(uaRemota, idPadre, ceTratamiento,ceMaterias);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public Long grabarUARemota(final String idRemoto, UnidadAdministrativaRemota uaRemota, Long idPadre , String ceTratamiento ,String[] ceMaterias) throws DelegateException{
		try {
            return getFacade().grabarUARemota(idRemoto, uaRemota, idPadre, ceTratamiento,ceMaterias);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public UnidadAdministrativaRemota obtenerUARemota(final String idRemoto, final Long idExtUA) throws DelegateException{
		try {
            return getFacade().obtenerUARemota(idRemoto,idExtUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public UnidadAdministrativaRemota obtenerUARemota(Long id) throws DelegateException{
		try {
            return getFacade().obtenerUARemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
    
	
	public AdministracionRemota obtenerAdministracionRemota(final String idRemoto) throws DelegateException{
		try {
            return getFacade().obtenerAdministracionRemota(idRemoto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public void borrarUARemota(final String idRemoto, final Long idExtUA) throws DelegateException{
		try {
            getFacade().borrarUARemota(idRemoto,idExtUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public void borrarUnidadAdministrativaRemota(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativaRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    public void borrarUnidadAdministrativaRaizRemota(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadAdministrativaRaizRemota(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void indexInsertaUARemota(UnidadAdministrativa ua, ModelFilterObject filter) throws DelegateException {
        try {
            getFacade().indexInsertaUARemota(ua, filter);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public EdificioRemoto obtenerEdificioRemoto(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerEdificioRemoto(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public EdificioRemoto obtenerEdificioRemoto(String idRemoto,Long idExtEdificio) throws DelegateException {
        try {
            return getFacade().obtenerEdificioRemoto(idRemoto, idExtEdificio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Long grabarEdificioRemoto(EdificioRemoto edificioRemoto) throws DelegateException {
        try {
            return getFacade().grabarEdificioRemoto(edificioRemoto);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Edificio> obtenerEdificiosUA(Long ua) throws DelegateException {
        try {
            return getFacade().obtenerEdificiosUA(ua);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarEdificioRemoto(Long id) throws DelegateException {
        try {
            getFacade().borrarEdificioRemoto(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    public void borrarEdificioRemoto(String idRemoto,Long idExt) throws DelegateException {
        try {
            getFacade().borrarEdificioRemoto(idRemoto,idExt);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    public void eliminarUnidad(String idRemoto,Long idEdif, Long idUA) throws DelegateException {
        try {
            getFacade().eliminarUnidad(idRemoto,idEdif,idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        } catch (Exception e) {
			e.printStackTrace();
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private UARemotaFacade getFacade() throws RemoteException {
        return (UARemotaFacade) facadeHandle.getEJBObject();
    }
    
    protected UARemotaDelegate() throws DelegateException {
        try {
            UARemotaFacadeHome home = UARemotaFacadeUtil.getHome();
            UARemotaFacade remote = home.create();
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
