package es.caib.rolsac.api.v2.usuari;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface UsuariQueryService {

    public int getNumUnitatsAdministratives() throws QueryServiceException;

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

}
