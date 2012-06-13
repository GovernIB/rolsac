package es.caib.rolsac.api.v2.usuari.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceStrategy;

public class UsuariQueryServiceEJBStrategy implements UsuariQueryServiceStrategy {

    UsuariQueryServiceDelegate delegate;
    UsuariQueryServiceLocator locator;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumUnitatsAdministratives(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
