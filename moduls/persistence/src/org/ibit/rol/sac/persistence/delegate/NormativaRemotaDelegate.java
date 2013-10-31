package org.ibit.rol.sac.persistence.delegate;


import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.NormativaRemota;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.TramiteRemoto;
import org.ibit.rol.sac.persistence.intf.NormativaRemotaFacade;
import org.ibit.rol.sac.persistence.intf.NormativaRemotaFacadeHome;
import org.ibit.rol.sac.persistence.util.NormativaRemotaFacadeUtil;
import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;


/**
 *  @deprecated No se usa
 * Business delegate para manipular normativas remotas.
 */
public class NormativaRemotaDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */


    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
	
	/** @deprecated No se usa */
	 public Long grabarNormativaRemota(final NormativaRemota normativaRemota)  throws DelegateException{
		try {
            return getFacade().grabarNormativaRemota(normativaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	 /** @deprecated No se usa */
    public NormativaRemota obtenerNormativaRemota(Long idExterno,Long idUaRemota) throws DelegateException {
        try {
            return getFacade().obtenerNormativaRemota(idExterno, idUaRemota);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated No se usa */
    public List<Normativa> obtenerNormativasProcedimiento(Long idProcedimiento) throws DelegateException{
		try {
            return getFacade().obtenerNormativasProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
    private Handle facadeHandle;

    private NormativaRemotaFacade getFacade() throws RemoteException {
        return (NormativaRemotaFacade) facadeHandle.getEJBObject();
    }

    protected NormativaRemotaDelegate() throws DelegateException {
        try {
            NormativaRemotaFacadeHome home = NormativaRemotaFacadeUtil.getHome();
            NormativaRemotaFacade remote = home.create();
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
