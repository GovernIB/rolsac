package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface SeccioQueryServiceStrategy {

    public int getNumFilles(long id) throws StrategyException;

    public int getNumFitxes(long id) throws StrategyException;

    public int getNumPares(long id) throws StrategyException;

    public int getNumUnitatsAdministratives(long id) throws StrategyException;

    public List<SeccioDTO> llistarPares(long id) throws StrategyException;

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) throws StrategyException;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException;
    
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    public SeccioDTO obtenirPare(Long padre) throws StrategyException;
    
}
