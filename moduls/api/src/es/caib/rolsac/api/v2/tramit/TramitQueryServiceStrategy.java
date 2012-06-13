package es.caib.rolsac.api.v2.tramit;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface TramitQueryServiceStrategy {
    
    int getNumDocumentsInformatius(long id);

    int getNumFormularis(long id);

    int getNumTaxes(long id);

    ProcedimentDTO obtenirProcediment(long id);

    UnitatAdministrativaDTO obtenirOrganCompetent(long id);

    List<DocumentTramitDTO> llistatDocumentsInformatius(long id, DocumentTramitCriteria documentTramitCriteria);

    List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria);

    List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria);

}
