package es.caib.rolsac.api.v2.usuari.ws;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceStrategy;

public class UsuariQueryServiceWebServiceStrategy implements UsuariQueryServiceStrategy {

    UsuariQueryServiceGateway gateway;

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
