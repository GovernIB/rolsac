package es.caib.rolsac.api.v2.edifici.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceEJBStrategy implements EdificiQueryServiceStrategy {

    EdificiQueryServiceDelegate delegate;
    EdificiQueryServiceLocator locator;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
