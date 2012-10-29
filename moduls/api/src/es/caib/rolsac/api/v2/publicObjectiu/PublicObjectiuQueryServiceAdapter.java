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
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
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
            return publicObjectiuQueryServiceStrategy.getNumAgrupacions(getId());
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de agrupaciones.", e);
        }
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws QueryServiceException {
        try {
            List<AgrupacioFetVitalDTO> llistaDTO = publicObjectiuQueryServiceStrategy.llistarAgrupacions(getId(), agurpacioFetVitalCriteria);
            List<AgrupacioFetVitalQueryServiceAdapter> llistaAgrupacions = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
            for (AgrupacioFetVitalDTO afvDTO : llistaDTO) {
                llistaAgrupacions.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), afvDTO));
            }
            return llistaAgrupacions;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procediemntCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = publicObjectiuQueryServiceStrategy.llistarProcediments(getId(), procediemntCriteria);
            List<ProcedimentQueryServiceAdapter> llistaProcediments = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procDTO : llistaDTO) {
                llistaProcediments.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procDTO));
            }
            return llistaProcediments;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = publicObjectiuQueryServiceStrategy.llistarFitxes(getId(), fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaFitxes = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
                llistaFitxes.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
            }
            return llistaFitxes;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

}
