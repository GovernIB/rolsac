package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public interface EspaiTerritorialQueryService {

    int getNumFills();

    int getNumUnitatsAdministratives();

    EspaiTerritorialQueryService obtenirPare(EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<EspaiTerritorialQueryService> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativa);
    
    
//    private Archivo mapa;
//    private Archivo logo

}
