package es.caib.rolsac.api.v2.publicObjectiu;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryService;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.PublicObjectiuQueryServiceEJBStrategy;

public class PublicObjectiuQueryServiceAdapter extends PublicObjectiuDTO implements PublicObjectiuQueryService {

    PublicObjectiuQueryServiceStrategy publicObjectiuQueryServiceStrategy;

    public PublicObjectiuQueryServiceAdapter() {
        // FIXME: don't harcode the ProcedimentQueryServiceEJBStrategy.
        publicObjectiuQueryServiceStrategy = new PublicObjectiuQueryServiceEJBStrategy();
    }
    
    public PublicObjectiuQueryServiceAdapter(PublicObjectiuDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public int getNumAgrupacions() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<AgrupacioFetVitalQueryService> llistarAgrupacions(AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
