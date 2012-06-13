package es.caib.rolsac.api.v2.estadistica.ejb;

import es.caib.rolsac.api.v2.estadistica.EstadisticaInsertServiceStrategy;

public class EstadisticaInsertServiceEJBStrategy implements EstadisticaInsertServiceStrategy {

    private EstadisticaInsertServiceDelegate estadisticaInsertServiceDelegate;

    public void setEstadisticaInsertServiceDelegate(EstadisticaInsertServiceDelegate delegate) {
        this.estadisticaInsertServiceDelegate = delegate;
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaFitxa(fitxaId);
    }

    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
    }

    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
    }

    public boolean gravarEstadisticaMateria(long materiaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaMateria(materiaId);
    }

    public boolean gravarEstadisticaNormativa(long normativaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaNormativa(normativaId);
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaProcediment(procedimentId);
    }

    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) {
        return estadisticaInsertServiceDelegate.gravarEstadisticaUnitatAdministrativa(uaId);
    }
    
}
