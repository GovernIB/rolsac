package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Estadistica;
import org.ibit.rol.sac.model.dto.FechaHistoricoDTO;
import org.ibit.rol.sac.persistence.intf.EstadisticaFacade;
import org.ibit.rol.sac.persistence.intf.EstadisticaFacadeHome;
import org.ibit.rol.sac.persistence.util.EstadisticaFacadeUtil;

/**
 * Business delegate para manipular Estadisticas.
 */
public class EstadisticaDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
	
    public void grabarEstadisticaUnidadAdministrativa(Long idUnidad) throws DelegateException {
        try {
            getFacade().grabarEstadisticaUnidadAdministrativa(idUnidad);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void grabarEstadisticaNormativa(Long idNormativa) throws DelegateException {
        try {
            getFacade().grabarEstadisticaNormativa(idNormativa);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void grabarEstadisticaProcedimiento(Long idProcedimiento) throws DelegateException {
        try {
            getFacade().grabarEstadisticaProcedimiento(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void grabarEstadisticaFicha(Long idFicha) throws DelegateException {
        try {
            getFacade().grabarEstadisticaFicha(idFicha);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarEstadisticasUnidad(Long idUnidad, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasUnidad(idUnidad, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarEstadisticasNormativa(Long idNormativa, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasNormativa(idNormativa, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarEstadisticasProcedimiento(Long idProcedimiento, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasProcedimiento(idProcedimiento, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarEstadisticasFicha(Long idFicha, Date fechaInicio, Date fechaFin, Long idMat, Long idUA) throws DelegateException {
        try {
            return getFacade().listarEstadisticasFicha(idFicha, fechaInicio, fechaFin, idMat, idUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List listarEstadisticasMateria(Long idMateria, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasMateria(idMateria, fechaInicio, fechaFin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<FechaHistoricoDTO> listarUltimasModificaciones(Date fechaInicio, Date fechaFin, Integer numeroRegistros, List<Long> listaIdUA) throws DelegateException {
        try {
            return getFacade().listarUltimasModificaciones(fechaInicio, fechaFin, numeroRegistros, listaIdUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Integer> resumenOperativa(Date fechaInicio, Date fechaFin, Integer tipoOperacion, List<Long> listaIdUA) throws DelegateException {
        try {
            return getFacade().resumenOperativa(fechaInicio, fechaFin, tipoOperacion, listaIdUA);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public List<Estadistica> listarEstadisticasListaUnidadAdministrativaId(List<Long> listaIdUA, Date fechaInicio, Date fechaFin) throws DelegateException {
        try {
            return getFacade().listarEstadisticasListaUnidadAdministrativaId(listaIdUA, fechaInicio, fechaFin);
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
