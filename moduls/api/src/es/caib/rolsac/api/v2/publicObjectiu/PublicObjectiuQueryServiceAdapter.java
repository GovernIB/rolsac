package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.PublicObjectiuQueryServiceEJBStrategy;

public class PublicObjectiuQueryServiceAdapter extends PublicObjectiuDTO implements PublicObjectiuQueryService {

    private PublicObjectiuQueryServiceStrategy publicObjectiuQueryServiceStrategy;

    public void setPublicObjectiuQueryServiceStrategy(PublicObjectiuQueryServiceStrategy publicObjectiuQueryServiceStrategy) {
        this.publicObjectiuQueryServiceStrategy = publicObjectiuQueryServiceStrategy;
    }

    public PublicObjectiuQueryServiceAdapter(PublicObjectiuDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public STRATEGY getStrategy() {
        return publicObjectiuQueryServiceStrategy instanceof PublicObjectiuQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumAgrupacions() {
        return publicObjectiuQueryServiceStrategy.getNumAgrupacions(id);
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> llistaDTO = publicObjectiuQueryServiceStrategy.llistarAgrupacions(id, agurpacioFetVitalCriteria);
        List<AgrupacioFetVitalQueryServiceAdapter> llistaAgrupacions = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
        for (AgrupacioFetVitalDTO afvDTO : llistaDTO) {
            llistaAgrupacions.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), afvDTO));
        }
        return llistaAgrupacions;
    }

}
