package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface EspaiTerritorialQueryService {

    int getNumFills();

    int getNumUnitatsAdministratives();

    EspaiTerritorialQueryServiceAdapter obtenirPare();

    List<EspaiTerritorialQueryServiceAdapter> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativa);
    
    ArxiuQueryServiceAdapter obtenirMapa();
    
    ArxiuQueryServiceAdapter obtenirLogo();

}
