package es.caib.rolsac.api.v2.tramit.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
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

    public int getNumDocumentsInformatius(long id) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.getNumDocumentsInformatius(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        } 
    }

    public int getNumFormularis(long id) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.getNumFormularis(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumTaxes(long id) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.getNumTaxes(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ProcedimentDTO obtenirProcediment(long idProc) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.obtenirProcediment(idProc);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public UnitatAdministrativaDTO obtenirOrganCompetent(long idUa) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.obtenirOrganCompetent(idUa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.llistatDocumentsInformatius(id, documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.llistarFormularis(id, documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) throws StrategyException {
        try {
            return tramitQueryServiceDelegate.llistarTaxes(id, taxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}

}
