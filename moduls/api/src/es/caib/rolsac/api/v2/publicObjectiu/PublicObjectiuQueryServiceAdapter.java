package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.PublicObjectiuQueryServiceEJBStrategy;

public class PublicObjectiuQueryServiceAdapter extends PublicObjectiuDTO implements PublicObjectiuQueryService {

    private static final long serialVersionUID = -4162899172250812685L;

    private PublicObjectiuQueryServiceStrategy publicObjectiuQueryServiceStrategy;

    public void setPublicObjectiuQueryServiceStrategy(PublicObjectiuQueryServiceStrategy publicObjectiuQueryServiceStrategy) {
        this.publicObjectiuQueryServiceStrategy = publicObjectiuQueryServiceStrategy;
    }

    public PublicObjectiuQueryServiceAdapter(PublicObjectiuDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public STRATEGY getStrategy() {
        return publicObjectiuQueryServiceStrategy instanceof PublicObjectiuQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumAgrupacions() throws QueryServiceException {
        try {
            return publicObjectiuQueryServiceStrategy.getNumAgrupacions(id);
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de agrupaciones.", e);
        }
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws QueryServiceException {
        try {
            List<AgrupacioFetVitalDTO> llistaDTO = publicObjectiuQueryServiceStrategy.llistarAgrupacions(id, agurpacioFetVitalCriteria);
            List<AgrupacioFetVitalQueryServiceAdapter> llistaAgrupacions = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
            for (AgrupacioFetVitalDTO afvDTO : llistaDTO) {
                llistaAgrupacions.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), afvDTO));
            }
            return llistaAgrupacions;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones.", e);
        }
    }

}
