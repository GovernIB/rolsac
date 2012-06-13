package es.caib.rolsac.api.v2.estadistica.ejb;

public class EstadisticaInsertServiceDelegate {
    
    private EstadisticaInsertServiceEJBLocator estadisticaInsertServiceLocator;
    
    public void setEstadisticaInsertServiceLocator(EstadisticaInsertServiceEJBLocator locator) {
        this.estadisticaInsertServiceLocator = locator;
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaFitxa(fitxaId);
    }
    
    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
    }
    
    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
    }
    
    public boolean gravarEstadisticaMateria(long materiaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaMateria(materiaId);
    }
    
    public boolean gravarEstadisticaNormativa(long normativaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaNormativa(normativaId);
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaProcediment(procedimentId);
    }
    
    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) {
        EstadisticaInsertServiceEJB ejb = estadisticaInsertServiceLocator.getEstadisticaInsertServieceEJB();
        return ejb.gravarEstadisticaUnitatAdministrativa(uaId);
    }
    
}
