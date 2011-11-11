package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.persistence.intf.EstadisticaFacade;
import org.ibit.rol.sac.persistence.intf.EstadisticaFacadeHome;
import org.ibit.rol.sac.persistence.util.EstadisticaFacadeUtil;

/**
 * Business delegate para manipular Estadisticas.
 */
public class EstadisticaDelegate implements StatelessDelegate {
     /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public void grabarEstadisticaUnidadAdministrativa(Long unidad_id) throws DelegateException {
        try {
            getFacade().grabarEstadisticaUnidadAdministrativa(unidad_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaNormativa(Long norm_id) throws DelegateException {
        try {
            getFacade().grabarEstadisticaNormativa(norm_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaProcedimiento(Long proc_id) throws DelegateException {
        try {
            getFacade().grabarEstadisticaProcedimiento(proc_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaFicha(Long ficha_id) throws DelegateException {
        try {
            getFacade().grabarEstadisticaFicha(ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaMateria(Long mat_id) throws DelegateException {
        try {
            getFacade().grabarEstadisticaMateria(mat_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarEstadisticasUnidad(Long unidad_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasUnidad(unidad_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarEstadisticasNormativa(Long norm_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasNormativa(norm_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarEstadisticasProcedimiento(Long proc_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasProcedimiento(proc_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarEstadisticasFicha(Long fic_id, Date fechaInicio, Date fechaFin, Long idMat, Long idUA) throws DelegateException {
        try {
            return getFacade().listarEstadisticasFicha(fic_id, fechaInicio, fechaFin, idMat, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarEstadisticasMateria(Long mat_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasMateria(mat_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarNormativasMasVisitadas(Long unidad_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarNormativasMasVisitadas(unidad_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarProcedimientosMasVisitados(Long unidad_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarProcedimientosMasVisitados(unidad_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarFichasMasVisitadas(Long unidad_id, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarFichasMasVisitadas(unidad_id, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaFichaPorMateria(Long ficha_id, Long idMat) throws DelegateException {
        try {
            getFacade().grabarEstadisticaFichaPorMateria(ficha_id, idMat);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void grabarEstadisticaFichaPorUA(Long ficha_id, Long idUA) throws DelegateException {
        try {
            getFacade().grabarEstadisticaFichaPorUA(ficha_id, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public Map<Timestamp, Object> listarUltimasModificaciones(Date fechaInicio, Date fechaFin, Integer numeroRegistros, UnidadAdministrativa unidadAdministrativa) throws DelegateException {
        try {
            return getFacade().listarUltimasModificaciones(fechaInicio, fechaFin, numeroRegistros, unidadAdministrativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Integer> resumenOperativa(Date fechaInicio, Date fechaFin, Integer tipoOperacion, Long idUnidadAdministrativa) throws DelegateException {
        try {
            return getFacade().resumenOperativa(fechaInicio, fechaFin, tipoOperacion, idUnidadAdministrativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private EstadisticaFacade getFacade() throws RemoteException {
        return (EstadisticaFacade) facadeHandle.getEJBObject();
    }

    protected EstadisticaDelegate() throws DelegateException {
        try {
            EstadisticaFacadeHome home = EstadisticaFacadeUtil.getHome();
            EstadisticaFacade remote = home.create();
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
