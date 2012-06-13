package es.caib.rolsac.api.v2.estadistica;

public class EstadisticaInsertServiceAdapter implements EstadisticaInsertService {
    
    private EstadisticaInsertServiceStrategy estadisticaInsertServiceStrategy;

    public void setEstadisticaInsertServiceStrategy(EstadisticaInsertServiceStrategy strategy) {
        this.estadisticaInsertServiceStrategy = strategy;
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaFitxa(fitxaId);
    }

    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
    }

    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
    }

    public boolean gravarEstadisticaMateria(long materiaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaMateria(materiaId);
    }

    public boolean gravarEstadisticaNormativa(long normativaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaNormativa(normativaId);
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaProcediment(procedimentId);
    }

    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) {
        return estadisticaInsertServiceStrategy.gravarEstadisticaUnitatAdministrativa(uaId);
    }
    
}
