package es.caib.rolsac.api.v2.document;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface DocumentQueryServiceStrategy {

    public FitxaDTO obtenirFitxa(long id) throws StrategyException;

    public ProcedimentDTO obtenirProcediment(long id) throws StrategyException;
    
    public ArxiuDTO obtenirArxiu(long id) throws StrategyException;

}
