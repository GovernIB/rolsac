package es.caib.rolsac.api.v2.butlleti;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.butlleti.ejb.ButlletiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;

public class ButlletiQueryServiceAdapter extends ButlletiDTO implements ButlletiQueryService {

    private static final long serialVersionUID = 4959651600237903757L;

    private ButlletiQueryServiceStrategy butlletiQueryServiceStrategy;
    
    public void setButlletiQueryServiceStrategy(ButlletiQueryServiceStrategy butlletiQueryServiceStrategy) {
        this.butlletiQueryServiceStrategy = butlletiQueryServiceStrategy;
    }
    
    private String url;
    public void setRolsacUrl(String url) {
    	this.url = url;
		if (this.butlletiQueryServiceStrategy != null) {
			this.butlletiQueryServiceStrategy.setUrl(url);
		}
	}

    public ButlletiQueryServiceAdapter(ButlletiDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return butlletiQueryServiceStrategy instanceof ButlletiQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumNormatives() throws QueryServiceException {
        try {
            return butlletiQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }

    public int getNumNormativesLocals() throws QueryServiceException {
        try {
            return butlletiQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.LOCAL);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas locales.", e);
        }
    }

    public int getNumNormativesExternes() throws QueryServiceException {
        try {
            return butlletiQueryServiceStrategy.getNumNormatives(getId(), TIPUS_NORMATIVA.EXTERNA);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas externas.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = butlletiQueryServiceStrategy.llistarNormatives(getId(), normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter nqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (nqsa != null && url != null) {
            		nqsa.setRolsacUrl(url);
            	}
                llistaQueryServiceAdapter.add(nqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

	

}
