package es.caib.rolsac.api.v2.estadistica.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.estadistica.ejb.intf.EstadisticaInsertServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;

public class EstadisticaInsertServiceDelegate {
    
    private EstadisticaInsertServiceEJBLocator estadisticaInsertServiceLocator;
    
    public void setEstadisticaInsertServiceLocator(EstadisticaInsertServiceEJBLocator locator) {
        this.estadisticaInsertServiceLocator = locator;
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) throws DelegateException {
        EstadisticaInsertServiceEJBRemote ejb;
        try {
            ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaFitxa(fitxaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaMateria(long materiaId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaMateria(materiaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaNormativa(long normativaId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaNormativa(normativaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaProcediment(procedimentId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaServicio(long servicioId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaServicio(servicioId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) throws DelegateException {
        try {
            EstadisticaInsertServiceEJBRemote ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServiceEJB();
            return ejb.gravarEstadisticaUnitatAdministrativa(uaId);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
