package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface FitxaUAQueryService {

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException;

    public FitxaQueryServiceAdapter obtenirFitxa() throws QueryServiceException;

    public SeccioQueryServiceAdapter obtenirSeccio() throws QueryServiceException;

}
