package es.caib.rolsac.api.v2.tramit.ws;

import java.util.Date;
import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceWebServiceStrategy implements TramitQueryServiceStrategy {

    // @Injected
    TramitQueryServiceGateway gateway;

    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
            DocumentTramitCriteria documentTramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumDocumentsInformatius(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFormularis(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumTaxes(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public Date getDataCaducitat(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getTitol(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public ProcedimentDTO obtenirProcediment(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long id) {
        // TODO Auto-generated method stub
        return null;
    }


    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
