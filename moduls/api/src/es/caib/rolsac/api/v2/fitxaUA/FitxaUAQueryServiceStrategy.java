package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface FitxaUAQueryServiceStrategy {

    public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException;

    public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException;

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) throws StrategyException;

}