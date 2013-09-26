package org.ibit.rol.sac.persistence.delegate;

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
public class AuditoriaDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public List listarAuditoriasUnidadAdministrativa(Long idUnidad) throws DelegateException {
        try {
            return getFacade().listarAuditoriasUnidadAdministrativa(idUnidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasProcedimiento(Long idProcedimiento) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasNormativa(Long idNormativa) throws DelegateException {
        try {
            return getFacade().listarAuditoriasNormativa(idNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasFicha(Long idFicha) throws DelegateException {
        try {
            return getFacade().listarAuditoriasFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated  No se utiliza */
    public List listarAuditoriasHistorico(Long idHistorico) throws DelegateException {
        try {
            return getFacade().listarAuditoriasHistorico(idHistorico);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated  No se utiliza */
    public List listarHistoricosAuditorias(Date fechaIni, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarHistoricosAuditorias(fechaIni, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarAuditoriasProcedimientoPMA(Long idProcedimiento) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimientoPMA(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarAuditoriasFichaPMA(Long idFicha) throws DelegateException {
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
