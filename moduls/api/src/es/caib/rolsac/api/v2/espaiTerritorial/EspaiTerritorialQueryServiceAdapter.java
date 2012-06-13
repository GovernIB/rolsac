package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.espaiTerritorial.ejb.EspaiTerritorialQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public class EspaiTerritorialQueryServiceAdapter extends EspaiTerritorialDTO implements EspaiTerritorialQueryService {

    // @Injected
    EspaiTerritorialQueryServiceStrategy espaiTerritorialQueryServiceStrategy;

    public EspaiTerritorialQueryServiceAdapter() {
        // FIXME: don't harcode the EspaiTerritorialQueryServiceEJBStrategy.
        espaiTerritorialQueryServiceStrategy= new EspaiTerritorialQueryServiceEJBStrategy();
    }
    
    public EspaiTerritorialQueryServiceAdapter(EspaiTerritorialDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

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

    public EspaiTerritorialQueryService obtenirPare(EspaiTerritorialCriteria espaiTerritorialCriteria) {

        EspaiTerritorialDTO dto = espaiTerritorialQueryServiceStrategy.obtenirPare(id, espaiTerritorialCriteria);
        return new EspaiTerritorialQueryServiceAdapter(dto);
    }

    public List<EspaiTerritorialQueryService> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativa) {
        // TODO Auto-generated method stub
        return null;
    }

}
