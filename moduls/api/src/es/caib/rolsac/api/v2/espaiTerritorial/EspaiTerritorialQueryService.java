package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface EspaiTerritorialQueryService {

    public int getNumFills() throws QueryServiceException;

    public int getNumUnitatsAdministratives() throws QueryServiceException;

    public EspaiTerritorialQueryServiceAdapter obtenirPare() throws QueryServiceException;

    public List<EspaiTerritorialQueryServiceAdapter> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException;

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativa) throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirMapa() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirLogo() throws QueryServiceException;

}
