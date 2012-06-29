package es.caib.rolsac.api.v2.document;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;

public interface DocumentQueryService {

    public FitxaQueryService obtenirFitxa() throws QueryServiceException;

    public ProcedimentQueryService obtenirProcediment() throws QueryServiceException;

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException;
}
