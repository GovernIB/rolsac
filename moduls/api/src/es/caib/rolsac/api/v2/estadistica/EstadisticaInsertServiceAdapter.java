package es.caib.rolsac.api.v2.estadistica;

import es.caib.rolsac.api.v2.exception.InsertServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class EstadisticaInsertServiceAdapter implements EstadisticaInsertService {
    
    private EstadisticaInsertServiceStrategy estadisticaInsertServiceStrategy;

    private String rolsacUrl;
    
    public EstadisticaInsertServiceAdapter() {
		super();	
	}
    
    public EstadisticaInsertServiceAdapter(String url) {
		super();	
		this.rolsacUrl = url;
	}
    
    public void setUrl(String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;	
		if (this.estadisticaInsertServiceStrategy != null) {
			this.estadisticaInsertServiceStrategy.setUrl(rolsacUrl);
		}
	}

	public void setEstadisticaInsertServiceStrategy(EstadisticaInsertServiceStrategy strategy) {
        this.estadisticaInsertServiceStrategy = strategy;
        if (this.estadisticaInsertServiceStrategy != null && rolsacUrl != null) {
        	this.estadisticaInsertServiceStrategy.setUrl(rolsacUrl);
        }
    }

    public boolean gravarEstadisticaFitxa(long fitxaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaFitxa(fitxaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaMateria(long materiaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaMateria(materiaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaNormativa(long normativaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaNormativa(normativaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaProcediment(long procedimentId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaProcediment(procedimentId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }

    public boolean gravarEstadisticaUnitatAdministrativa(long uaId) throws InsertServiceException {
        try {
            return estadisticaInsertServiceStrategy.gravarEstadisticaUnitatAdministrativa(uaId);
        } catch (StrategyException e) {
            throw new InsertServiceException(e);
        }
    }
    
}
