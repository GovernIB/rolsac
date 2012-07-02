package es.caib.rolsac.api.v2.estadistica.ejb;

import es.caib.rolsac.api.v2.estadistica.EstadisticaInsertServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class EstadisticaInsertServiceEJBStrategy implements EstadisticaInsertServiceStrategy {

    private EstadisticaInsertServiceDelegate estadisticaInsertServiceDelegate;

    public void setEstadisticaInsertServiceDelegate(EstadisticaInsertServiceDelegate delegate) {
        this.estadisticaInsertServiceDelegate = delegate;
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaFitxa(fitxaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaMateria(long materiaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaMateria(materiaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaNormativa(long normativaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaNormativa(normativaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaProcediment(procedimentId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) throws StrategyException {
        try {
            return estadisticaInsertServiceDelegate.gravarEstadisticaUnitatAdministrativa(uaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
}
