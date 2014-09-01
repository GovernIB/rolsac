package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.AgrupacioFetVitalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

public class AgrupacioFetVitalQueryServiceAdapter extends AgrupacioFetVitalDTO implements AgrupacioFetVitalQueryService {

    private static final long serialVersionUID = 2967490419327366641L;

    private AgrupacioFetVitalQueryServiceStrategy agrupacioFetVitalQueryServiceStrategy;
    
    public void setAgrupacioFetVitalQueryServiceStrategy(AgrupacioFetVitalQueryServiceStrategy agrupacioFetVitalQueryServiceStrategy) {
        this.agrupacioFetVitalQueryServiceStrategy = agrupacioFetVitalQueryServiceStrategy;
    }

    public AgrupacioFetVitalQueryServiceAdapter(AgrupacioFetVitalDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return agrupacioFetVitalQueryServiceStrategy instanceof AgrupacioFetVitalQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException {
        if (this.getFoto() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getFotografia(this.getFoto());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }

    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getIcona(this.getIcono());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }

    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException {
        if (this.getIconoGrande() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getIconaGran(this.getIconoGrande());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }
    
    public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu() throws QueryServiceException {
        if (this.getPublico() == null) {return null;}
        try {
            PublicObjectiuDTO dto = (PublicObjectiuDTO) agrupacioFetVitalQueryServiceStrategy.obtenirPublicObjectiu(this.getPublico());
            return (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws QueryServiceException {
        try {
            List<FetVitalDTO> llistaDTO = agrupacioFetVitalQueryServiceStrategy.llistarFetsVitals(getId(), fetVitalCriteria);
            List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
            for (FetVitalDTO fetVitalDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }

    public int getNumFetsVitals() throws QueryServiceException {       
        try {
            return agrupacioFetVitalQueryServiceStrategy.getNumFetsVitals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(e);
        }
    }
    
}