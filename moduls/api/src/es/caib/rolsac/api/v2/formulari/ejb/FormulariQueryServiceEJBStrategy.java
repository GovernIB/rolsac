package es.caib.rolsac.api.v2.formulari.ejb;

import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceEJBStrategy implements FormulariQueryServiceStrategy {

    FormulariQueryServiceDelegate delegate;
    FormulariQueryServiceLocator locator;

    public TramitDTO obtenirTramit(long id, TramitCriteria tramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getTitol(long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
