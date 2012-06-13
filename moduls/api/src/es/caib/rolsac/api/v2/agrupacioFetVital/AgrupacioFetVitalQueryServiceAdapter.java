package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.AgrupacioFetVitalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

public class AgrupacioFetVitalQueryServiceAdapter extends AgrupacioFetVitalDTO implements AgrupacioFetVitalQueryService {

    private static Log log = LogFactory.getLog(AgrupacioFetVitalQueryServiceAdapter.class);
    
    private AgrupacioFetVitalQueryServiceStrategy agrupacioFetVitalQueryServiceStrategy;
    
    public void setAgrupacioFetVitalQueryServiceStrategy(AgrupacioFetVitalQueryServiceStrategy agrupacioFetVitalQueryServiceStrategy) {
        this.agrupacioFetVitalQueryServiceStrategy = agrupacioFetVitalQueryServiceStrategy;
    }

    public AgrupacioFetVitalQueryServiceAdapter(AgrupacioFetVitalDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // TODO: delete me.
            log.error("Error instanciando AgrupacioFetVitalQuerServiceAdapter.", e);
        }
    }
    
    private STRATEGY getStrategy() {
        return agrupacioFetVitalQueryServiceStrategy instanceof AgrupacioFetVitalQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter getFotografia() {
        if (this.getFoto() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getFotografia(this.getFoto());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

    public ArxiuQueryServiceAdapter getIcona() {
        if (this.getIcono() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getIcona(this.getIcono());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

    public ArxiuQueryServiceAdapter getIconaGran() {
        if (this.getIconoGrande() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) agrupacioFetVitalQueryServiceStrategy.getIconaGran(this.getIconoGrande());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }
    
    public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu() {
        if (this.getPublico() == null) {return null;}
        PublicObjectiuDTO dto = (PublicObjectiuDTO) agrupacioFetVitalQueryServiceStrategy.obtenirPublicObjectiu(this.getPublico());
        return (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), dto);
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        List<FetVitalDTO> llistaDTO = agrupacioFetVitalQueryServiceStrategy.llistarFetsVitals(id, fetVitalCriteria);
        List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
        for (FetVitalDTO fetVitalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public int getNumFetsVitals() {       
        return agrupacioFetVitalQueryServiceStrategy.getNumFetsVitals(id);
    }
    
}