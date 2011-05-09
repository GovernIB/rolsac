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

    public List listarAuditoriasUnidadAdministrativa(Long unidad_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasUnidadAdministrativa(unidad_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasProcedimiento(Long proc_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimiento(proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasNormativa(Long norm_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasNormativa(norm_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasFicha(Long ficha_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasFicha(ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarAuditoriasHistorico(Long historico_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasHistorico(historico_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarHistoricosAuditorias(Date fechaIni, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarHistoricosAuditorias(fechaIni, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarAuditoriasProcedimientoPMA(Long proc_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasProcedimientoPMA(proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarAuditoriasFichaPMA(Long fic_id) throws DelegateException {
        try {
            return getFacade().listarAuditoriasFichaPMA(fic_id);
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
