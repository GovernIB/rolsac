package es.caib.rolsac.api.v2.tramit.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceEJBStrategy implements TramitQueryServiceStrategy {

    private TramitQueryServiceDelegate tramitQueryServiceDelegate;

    public void setTramitQueryServiceDelegate(TramitQueryServiceDelegate tramitQueryServiceDelegate) {
        this.tramitQueryServiceDelegate = tramitQueryServiceDelegate;
    }

    public int getNumDocumentsInformatius(long id) {
        return tramitQueryServiceDelegate.getNumDocumentsInformatius(id); 
    }

    public int getNumFormularis(long id) {
        return tramitQueryServiceDelegate.getNumFormularis(id);
    }

    public int getNumTaxes(long id) {
        return tramitQueryServiceDelegate.getNumTaxes(id);
    }
    
    public ProcedimentDTO obtenirProcediment(long idProc) {
        return tramitQueryServiceDelegate.obtenirProcediment(idProc);
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) {
        return tramitQueryServiceDelegate.obtenirOrganCompetent(idUa);
    }

    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
            DocumentTramitCriteria documentTramitCriteria) {
        return tramitQueryServiceDelegate.llistatDocumentsInformatius(id, documentTramitCriteria);
    }
    
    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) {
        return tramitQueryServiceDelegate.llistarFormularis(id, documentTramitCriteria);
    }

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) {
        return tramitQueryServiceDelegate.llistarTaxes(id, taxaCriteria);
    }

}
