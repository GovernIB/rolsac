package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface EnllacQueryService {

    public FitxaQueryServiceAdapter obtenirFitxa() throws QueryServiceException;

    public ProcedimentQueryServiceAdapter obtenirProcediment() throws QueryServiceException;

}
