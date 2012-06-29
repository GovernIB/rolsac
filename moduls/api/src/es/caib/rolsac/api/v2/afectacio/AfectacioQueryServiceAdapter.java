package es.caib.rolsac.api.v2.afectacio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.afectacio.ejb.AfectacioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;

public class AfectacioQueryServiceAdapter extends AfectacioDTO implements AfectacioQueryService {

    private static final long serialVersionUID = -8581221764027130058L;

    AfectacioQueryServiceStrategy afectacioQueryServiceStrategy;

    public void setAfectacioQueryServiceStrategy(AfectacioQueryServiceStrategy afectacioQueryServiceStrategy) {
        this.afectacioQueryServiceStrategy = afectacioQueryServiceStrategy;
    }

    public AfectacioQueryServiceAdapter(AfectacioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return afectacioQueryServiceStrategy instanceof AfectacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public NormativaQueryServiceAdapter obtenirAfectant() throws QueryServiceException {
        try {
            return (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), afectacioQueryServiceStrategy.obtenirAfectant(this.getAfectante()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa afectante.", e);
        }
    }

    public NormativaQueryServiceAdapter obtenirNormativa() throws QueryServiceException {
        try {
            return (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), afectacioQueryServiceStrategy.obtenirNormativa(this.getNormativa()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
        }
    }

    public TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio() throws QueryServiceException {
        try {
            return (TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio", getStrategy(), afectacioQueryServiceStrategy.obtenirTipusAfectacio(this.getTipoAfectacion()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tipo de afectacion.", e);
        }
    }  
    
}
