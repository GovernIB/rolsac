package es.caib.rolsac.api.v2.tramit.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceDelegate {

    private TramitQueryServiceEJBLocator tramitQueryServiceLocator;
    
    public void setTramitQueryServiceLocator(TramitQueryServiceEJBLocator tramitQueryServiceLocator) {
        this.tramitQueryServiceLocator = tramitQueryServiceLocator;
    }

    public int getNumDocumentsInformatius(long id) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.getNumDocumentsInformatius(id); 
    }

    public int getNumFormularis(long id) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.getNumFormularis(id);
    }

    public int getNumTaxes(long id) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.getNumTaxes(id);
    }
    
    public ProcedimentDTO obtenirProcediment(long idProc) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.obtenirProcediment(idProc);
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.obtenirOrganCompetent(idUa);
    }

    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
            DocumentTramitCriteria documentTramitCriteria) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.llistatDocumentsInformatius(id, documentTramitCriteria);
    }
    
    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.llistarFormularis(id, documentTramitCriteria);
    }

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) {
        TramitQueryServiceEJB ejb = tramitQueryServiceLocator.getTramitQueryServiceEJB();
        return ejb.llistarTaxes(id, taxaCriteria);
    }
}
