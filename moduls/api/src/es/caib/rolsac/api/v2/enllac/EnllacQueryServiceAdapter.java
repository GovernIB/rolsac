package es.caib.rolsac.api.v2.enllac;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class EnllacQueryServiceAdapter extends EnllacDTO implements EnllacQueryService {

    private static final long serialVersionUID = 6411086726548025434L;

    private EnllacQueryServiceStrategy enllacQueryServiceStrategy;

    public void setEnllacQueryServiceStrategy(EnllacQueryServiceStrategy enllacQueryServiceStrategy) {
        this.enllacQueryServiceStrategy = enllacQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return enllacQueryServiceStrategy instanceof EnllacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public EnllacQueryServiceAdapter(EnllacDTO dto) throws QueryServiceException {    
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public FitxaQueryServiceAdapter obtenirFitxa() throws QueryServiceException {
        if (this.getFicha() == null) {return null;}
        try {
            return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), enllacQueryServiceStrategy.obtenirFitxa(this.getFicha()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "ficha.", e);
        }
    }

    public ProcedimentQueryServiceAdapter obtenirProcediment() throws QueryServiceException {
        if (this.getProcedimiento() == null) {return null;}
        try {
            return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), enllacQueryServiceStrategy.obtenirProcediment(this.getProcedimiento()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "procedimiento.", e);
        }
    }

}