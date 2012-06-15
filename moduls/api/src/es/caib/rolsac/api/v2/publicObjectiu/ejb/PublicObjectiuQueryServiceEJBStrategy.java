package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceStrategy;

public class PublicObjectiuQueryServiceEJBStrategy implements PublicObjectiuQueryServiceStrategy {

    private PublicObjectiuQueryServiceDelegate publicObjectiuQueryServiceDelegate;

    public void setPublicObjectiuQueryServiceDelegate(PublicObjectiuQueryServiceDelegate publicObjectiuQueryServiceDelegate) {
        this.publicObjectiuQueryServiceDelegate = publicObjectiuQueryServiceDelegate;
    }
        
    public int getNumAgrupacions(long id) {
        return publicObjectiuQueryServiceDelegate.getNumAgrupacions(id);
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) {
        return publicObjectiuQueryServiceDelegate.llistarAgrupacions(id, agurpacioFetVitalCriteria);
    }

}
