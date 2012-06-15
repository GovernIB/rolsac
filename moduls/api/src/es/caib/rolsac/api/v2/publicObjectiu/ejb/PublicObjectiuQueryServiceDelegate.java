package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;

public class PublicObjectiuQueryServiceDelegate {

    private PublicObjectiuQueryServiceEJBLocator publicObjectiuQueryServiceLocator;

    public void setPublicObjectiuQueryServiceLocator(PublicObjectiuQueryServiceEJBLocator publicObjectiuQueryServiceLocator) {
        this.publicObjectiuQueryServiceLocator = publicObjectiuQueryServiceLocator;
    }
    
    public int getNumAgrupacions(long id) {
        PublicObjectiuQueryServcieEJB ejb = publicObjectiuQueryServiceLocator.getPublicObjectiuQueryServceEJB();
        return ejb.getNumAgrupacions(id);
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) {
        PublicObjectiuQueryServcieEJB ejb = publicObjectiuQueryServiceLocator.getPublicObjectiuQueryServceEJB();
        return ejb.llistarAgrupacions(id, agurpacioFetVitalCriteria);
    }

    
}
