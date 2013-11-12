package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.FichaRemotaFacade;
import org.ibit.rol.sac.persistence.intf.FichaRemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.FichaRemotaFacadeUtil;
import org.ibit.rol.sac.model.FichaRemota;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativaRemota;
import org.ibit.rol.sac.model.ws.FichaUATransferible;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.util.Set;
import java.util.List;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular fichas.
 */
public class FichaRemotaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	public Long grabarFichaRemota(final String idRemoto, FichaRemota fichaRemota, FichaUATransferible[] fichasUAT, String[] ceMaterias, String[] ceHechos)  throws DelegateException{
		try {
            return getFacade().grabarFichaRemota(idRemoto, fichaRemota, fichasUAT, ceMaterias, ceHechos);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public Long grabarFichaRemota(FichaRemota fichaRemota, Long idUnidad, Long idSeccion, String[] ceMaterias, String[] ceHechos) throws DelegateException{
		try {
            return getFacade().grabarFichaRemota(fichaRemota,idUnidad,idSeccion,ceMaterias,ceHechos);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public FichaRemota obtenerFichaRemota(final String idRemoto, final Long idExtFicha) throws DelegateException {
		try {
            return getFacade().obtenerFichaRemota(idRemoto,idExtFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public FichaRemota obtenerFichaRemota(final Long idExtFicha, final Long idAdmin) throws DelegateException {
		try {
            return getFacade().obtenerFichaRemota(idExtFicha,idAdmin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public void borrarFichaRemota(final String idRemoto, final Long idExtFicha) throws DelegateException{
		try {
            getFacade().borrarFichaRemota(idRemoto, idExtFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public void borrarFichaUA(final String idRemoto, Long idFicha, Long idUA, String codEst) throws DelegateException {
		try {
            getFacade().borrarFichaUA(idRemoto,idFicha,idUA,codEst);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	/* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
	
    private Handle facadeHandle;
    
    private FichaRemotaFacade getFacade() throws RemoteException {
        return (FichaRemotaFacade) facadeHandle.getEJBObject();
    }
    
    protected FichaRemotaDelegate() throws DelegateException {
        try {
            FichaRemotaFacadeHome home = FichaRemotaFacadeUtil.getHome();
            FichaRemotaFacade remote = home.create();
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
