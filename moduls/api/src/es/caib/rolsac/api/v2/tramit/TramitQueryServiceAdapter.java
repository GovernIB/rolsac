package es.caib.rolsac.api.v2.tramit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.ejb.TramitQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class TramitQueryServiceAdapter extends TramitDTO implements TramitQueryService {

    private static final long serialVersionUID = 2549215493827336923L;

    private TramitQueryServiceStrategy tramitQueryServiceStrategy;

    public void setTramitQueryServiceStrategy(TramitQueryServiceStrategy tramitQueryServiceStrategy) {
        this.tramitQueryServiceStrategy = tramitQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (this.tramitQueryServiceStrategy != null) {
			this.tramitQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}    

    public TramitQueryServiceAdapter(TramitDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return tramitQueryServiceStrategy instanceof TramitQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumDocumentsInformatius() throws QueryServiceException {
        try {
            return tramitQueryServiceStrategy.getNumDocumentsInformatius(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de documentos informativos.", e);
        }
    }

    public int getNumFormularis() throws QueryServiceException {
        try {
            return tramitQueryServiceStrategy.getNumFormularis(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de formularios.", e);
        }
    }

    public int getNumTaxes() throws QueryServiceException {
        try {
            return tramitQueryServiceStrategy.getNumTaxes(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de tasas.", e);
        }
    }

    public ProcedimentQueryServiceAdapter obtenirProcediment() throws QueryServiceException {
        if (this.getProcedimiento() == null) {return null;}
        try {
            ProcedimentDTO dto = tramitQueryServiceStrategy.obtenirProcediment(this.getProcedimiento());
            ProcedimentQueryServiceAdapter dqsa=  (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
            if (dqsa != null && rolsacUrl != null) {
        		dqsa.setRolsacUrl(rolsacUrl);
        	}
        	return dqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
        }
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirOrganCompetent() throws QueryServiceException {
        if (this.getOrganCompetent() == null) {return null;}
        try {
            UnitatAdministrativaDTO dto = tramitQueryServiceStrategy.obtenirOrganCompetent(this.getOrganCompetent());
            UnitatAdministrativaQueryServiceAdapter dqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
            if (dqsa != null && rolsacUrl != null) {
        		dqsa.setRolsacUrl(rolsacUrl);
        	}
        	return dqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "organo competente.", e);
        }
    }

    public List<DocumentTramitQueryServiceAdapter> llistatDocumentsInformatius(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException {
        try {
            List<DocumentTramitDTO> llistaDTO = tramitQueryServiceStrategy.llistatDocumentsInformatius(getId(), documentTramitCriteria);
            List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
            for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            	DocumentTramitQueryServiceAdapter dqsa = (DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO);
            	if (dqsa != null && rolsacUrl != null) {
            		dqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(dqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos informativos.", e);
        }
    }

    public List<DocumentTramitQueryServiceAdapter> llistarFormularis(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException {
        try {
            List<DocumentTramitDTO> llistaDTO = tramitQueryServiceStrategy.llistarFormularis(getId(), documentTramitCriteria);
            List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
            for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            	DocumentTramitQueryServiceAdapter dqsa = (DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO);
            	if (dqsa != null && rolsacUrl != null) {
            		dqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(dqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "formularios.", e);
        }
    }
    
    public List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) throws QueryServiceException {
        try {
            List<TaxaDTO> llistaDTO = tramitQueryServiceStrategy.llistarTaxes(getId(), taxaCriteria);
            List<TaxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TaxaQueryServiceAdapter>();
            for (TaxaDTO taxaDTO : llistaDTO) {
            	TaxaQueryServiceAdapter dqsa = (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), taxaDTO);
            	if (dqsa != null && rolsacUrl != null) {
            		dqsa.setRolsacUrl(rolsacUrl);
            	}
            	 llistaQueryServiceAdapter.add(dqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tasas.", e);
        }
    }

    
	

}
