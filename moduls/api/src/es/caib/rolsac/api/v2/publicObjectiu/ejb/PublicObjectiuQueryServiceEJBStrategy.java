package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceStrategy;

public class PublicObjectiuQueryServiceEJBStrategy implements PublicObjectiuQueryServiceStrategy {

    PublicObjectiuQueryServiceDelegate delegate;
    PublicObjectiuQueryServiceLocator locator;

    public int getNumAgrupacions(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
