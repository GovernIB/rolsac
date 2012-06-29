package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface EnllacQueryServiceStrategy {

    public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException;

    public ProcedimentDTO obtenirProcediment(long idProcediment) throws StrategyException;

}
