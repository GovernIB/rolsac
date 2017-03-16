package es.caib.rolsac.api.v2.tramit;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface TramitQueryServiceStrategy {
    
    public int getNumDocumentsInformatius(long id) throws StrategyException;

    public int getNumFormularis(long id) throws StrategyException;

    public int getNumTaxes(long id) throws StrategyException;

    public ProcedimentDTO obtenirProcediment(long id) throws StrategyException;

    public UnitatAdministrativaDTO obtenirOrganCompetent(long id) throws StrategyException;

    public List<DocumentTramitDTO> llistatDocumentsInformatius(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException;

    public List<DocumentTramitDTO> llistarFormularis(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException;

    public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria) throws StrategyException;

	public void setUrl(String url);

}
