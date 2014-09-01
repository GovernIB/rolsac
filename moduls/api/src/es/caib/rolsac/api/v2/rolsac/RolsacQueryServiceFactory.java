package es.caib.rolsac.api.v2.rolsac;

import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceDelegate;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJBLocator;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJBStrategy;

// TODO: delete this class and use Spring DI.
public class RolsacQueryServiceFactory {

    private RolsacQueryServiceFactory() {
    }

    public static RolsacQueryService newInstance() {
        RolsacQueryServiceDelegate delegate = new RolsacQueryServiceDelegate();
        delegate.setRolsacQueryServiceLocator(new RolsacQueryServiceEJBLocator());

        RolsacQueryServiceEJBStrategy strategy = new RolsacQueryServiceEJBStrategy();
        strategy.setRolsacQueryServiceDelegate(delegate);

        RolsacQueryServiceAdapter rqs = new RolsacQueryServiceAdapter();
        rqs.setRolsacQueryServiceStrategy(strategy);

        return rqs;
    }

}
