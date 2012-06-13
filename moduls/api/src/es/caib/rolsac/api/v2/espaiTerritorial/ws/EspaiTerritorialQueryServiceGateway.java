package es.caib.rolsac.api.v2.espaiTerritorial.ws;

import java.util.List;

import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceGateway implements EspaiTerritorialQueryServiceStrategy {

    EspaiTerritorialQueryServiceGateway gateway;

    public int getNumFills() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumUnitatsAdministratives() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumAdministracionsRemotes() {
        // TODO Auto-generated method stub
        return 0;
    }

    public EspaiTerritorialDTO obtenirPare(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativa) {
        // TODO Auto-generated method stub
        return null;
    }

}
