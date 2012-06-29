package es.caib.rolsac.api.v2.tipus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.ejb.TipusQueryServiceEJBStrategy;

public class TipusQueryServiceAdapter extends TipusDTO implements TipusQueryService {

    private static final long serialVersionUID = -3711169892115342110L;

    TipusQueryServiceStrategy tipusQueryServiceStrategy;   
    
    public void setTipusQueryServiceStrategy(TipusQueryServiceStrategy tipusQueryServiceStrategy) {
        this.tipusQueryServiceStrategy = tipusQueryServiceStrategy;
    }

    public TipusQueryServiceAdapter(TipusDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return tipusQueryServiceStrategy instanceof TipusQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumNormatives() throws QueryServiceException {
        try {
            return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.TOTES);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }

    public int getNumNormativesLocals() throws QueryServiceException {
        try {
            return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.LOCAL);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas locales.", e);
        }
    }

    public int getNumNormativesExternes() throws QueryServiceException {
        try {
            return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.EXTERNA);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas externas.", e);
        }
    }    
    
    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = tipusQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }
    
}
