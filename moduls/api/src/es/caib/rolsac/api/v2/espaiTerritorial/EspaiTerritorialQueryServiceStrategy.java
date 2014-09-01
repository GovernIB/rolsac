package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface EspaiTerritorialQueryServiceStrategy {

    public int getNumFills(long id) throws StrategyException;

    public int getNumUnitatsAdministratives(long id) throws StrategyException;

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativa) throws StrategyException;

    public ArxiuDTO obtenirMapa(Long idMapa) throws StrategyException;

    public ArxiuDTO obtenirLogo(Long idLogo) throws StrategyException;

    public EspaiTerritorialDTO obtenirPare(Long idPadre) throws StrategyException;
    
}
