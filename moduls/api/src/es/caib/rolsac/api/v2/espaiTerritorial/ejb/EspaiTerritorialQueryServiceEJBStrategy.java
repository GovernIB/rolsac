package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceEJBStrategy implements EspaiTerritorialQueryServiceStrategy {

    EspaiTerritorialQueryServiceDelegate delegate;
    EspaiTerritorialQueryServiceLocator locator;

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
