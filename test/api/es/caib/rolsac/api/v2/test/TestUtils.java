package es.caib.rolsac.api.v2.test;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

public final class TestUtils {
    
    // Cambiar a STRATEGY.EJB para que todos los tests se ejecuten con EJB en vez de por WebService.
    public final static STRATEGY DEFAULT_STRATEGY = STRATEGY.WS;
    
    public final static RolsacQueryService getRolsacQueryService() {
        return getRolsacQueryService(TestUtils.DEFAULT_STRATEGY);
    }
    
    public final static RolsacQueryService getRolsacQueryService(STRATEGY strategy) {
        return (RolsacQueryService) BeanUtils.getAdapter("rolsac", strategy);
    }

}
