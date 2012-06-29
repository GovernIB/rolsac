package es.caib.rolsac.api.v2.tramit;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface TramitQueryService {

    public int getNumDocumentsInformatius() throws QueryServiceException;

    public int getNumFormularis() throws QueryServiceException;

    public int getNumTaxes() throws QueryServiceException;

    public ProcedimentQueryServiceAdapter obtenirProcediment() throws QueryServiceException;

    public UnitatAdministrativaQueryServiceAdapter obtenirOrganCompetent() throws QueryServiceException;

    public List<DocumentTramitQueryServiceAdapter> llistatDocumentsInformatius(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException;

    public List<DocumentTramitQueryServiceAdapter> llistarFormularis(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException;

    public List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) throws QueryServiceException;

}
