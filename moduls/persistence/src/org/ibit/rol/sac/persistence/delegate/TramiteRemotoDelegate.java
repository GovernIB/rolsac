package org.ibit.rol.sac.persistence.delegate;


import org.ibit.rol.sac.model.EdificioRemoto;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.persistence.intf.TramiteRemotoFacade;
import org.ibit.rol.sac.persistence.intf.TramiteRemotoFacadeHome;
import org.ibit.rol.sac.persistence.util.TramiteRemotoFacadeUtil;
import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular tramites remotos.
 */
public class TramiteRemotoDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
	
	public Long grabarTramiteRemoto(final TramiteRemoto tramiteRemoto,final Long idProc,final Long idOC)  throws DelegateException{
		try {
            return getFacade().grabarTramiteRemoto(tramiteRemoto,idProc,idOC);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public Long grabarTramiteRemoto(final TramiteRemoto tramiteRemoto,Long idOC)  throws DelegateException{
		try {
            return getFacade().grabarTramiteRemoto(tramiteRemoto,idOC);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public List<Tramite> obtenerTramitesProcedimiento(Long idProcedimiento) throws DelegateException{
		try {
            return getFacade().obtenerTramitesProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
    public TramiteRemoto obtenerTramiteRemoto(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerTramiteRemoto(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public TramiteRemoto obtenerTramiteRemoto(String idRemoto,Long idExtTramite) throws DelegateException {
        try {
            return getFacade().obtenerTramiteRemoto(idRemoto, idExtTramite);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarTramiteRemoto(Long id) throws DelegateException {
        try {
            getFacade().borrarTramiteRemoto(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void borrarTramiteRemoto(final String idRemoto ,final Long idExt) throws DelegateException {
        try {
            getFacade().borrarTramiteRemoto(idRemoto,idExt);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TramiteRemotoFacade getFacade() throws RemoteException {
        return (TramiteRemotoFacade) facadeHandle.getEJBObject();
    }

    protected TramiteRemotoDelegate() throws DelegateException {
        try {
        	TramiteRemotoFacadeHome home = TramiteRemotoFacadeUtil.getHome();
        	TramiteRemotoFacade remote = home.create();
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
