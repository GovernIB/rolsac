package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Auditoria;
import org.ibit.rol.sac.persistence.intf.AuditoriaFacade;
import org.ibit.rol.sac.persistence.intf.AuditoriaFacadeHome;
import org.ibit.rol.sac.persistence.util.AuditoriaFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/**
 * Business delegate para manipular Auditorias.
 */
public class AuditoriaDelegate implements StatelessDelegate
{
    /** Serial version UID. **/
	private static final long serialVersionUID = 1L;

	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public List<Auditoria> listarAuditoriasUnidadAdministrativa(Long idUnidad) throws DelegateException {
        try {
            return getFacade().listarAuditoriasUnidadAdministrativa(idUnidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List<Auditoria> listarAuditoriasProcedimiento(Long idProcedimiento) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Auditoria> listarAuditoriasServicio(Long idServicio) throws DelegateException {
        try {
            return getFacade().listarAuditoriasServicio(idServicio);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List<Auditoria> listarAuditoriasNormativa(Long idNormativa) throws DelegateException {
        try {
            return getFacade().listarAuditoriasNormativa(idNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List<Auditoria> listarAuditoriasFicha(Long idFicha) throws DelegateException {
        try {
            return getFacade().listarAuditoriasFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Auditoria> listarAuditoriasProcedimientoPMA(Long idProcedimiento) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimientoPMA(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Auditoria> listarAuditoriasFichaPMA(Long idFicha) throws DelegateException {
        try {
            return getFacade().listarAuditoriasFichaPMA(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private AuditoriaFacade getFacade() throws RemoteException {
        return (AuditoriaFacade) facadeHandle.getEJBObject();
    }

    protected AuditoriaDelegate() throws DelegateException {
        try {
            AuditoriaFacadeHome home = AuditoriaFacadeUtil.getHome();
            AuditoriaFacade remote = home.create();
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
