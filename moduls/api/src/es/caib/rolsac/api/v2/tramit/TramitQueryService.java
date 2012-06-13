package es.caib.rolsac.api.v2.tramit;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface TramitQueryService {

    int getNumDocumentsInformatius();

    int getNumFormularis();

    int getNumTaxes();

    ProcedimentQueryServiceAdapter obtenirProcediment();

    UnitatAdministrativaQueryServiceAdapter obtenirOrganCompetent();

    List<DocumentTramitQueryServiceAdapter> llistatDocumentsInformatius(DocumentTramitCriteria documentTramitCriteria);

    List<DocumentTramitQueryServiceAdapter> llistarFormularis(DocumentTramitCriteria documentTramitCriteria);

    List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria);

}
