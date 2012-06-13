package es.caib.rolsac.api.v2.estadistica.ejb;

import es.caib.rolsac.api.v2.estadistica.EstadisticaCriteria;
import es.caib.rolsac.api.v2.estadistica.EstadisticaQueryServiceStrategy;

public class EstadisticaQueryServiceEJBStrategy implements EstadisticaQueryServiceStrategy {

    EstadisticaQueryServiceDelegate delegate;
    EstadisticaQueryServiceLocator locator;

    public void addAccess(long id, EstadisticaCriteria estadisticaCriteria) {
        // TODO Auto-generated method stub

    }

}
