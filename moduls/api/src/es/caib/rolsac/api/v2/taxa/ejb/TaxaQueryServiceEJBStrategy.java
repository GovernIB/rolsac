package es.caib.rolsac.api.v2.taxa.ejb;

import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceEJBStrategy implements TaxaQueryServiceStrategy {

    TaxaQueryServiceDelegate taxaQueryServiceDelegate;
    
    public void setTaxaQueryServiceDelegate(TaxaQueryServiceDelegate taxaQueryServiceDelegate) {
        this.taxaQueryServiceDelegate = taxaQueryServiceDelegate;
    }

    public TramitDTO obtenirTramit(long idTramit) {
        return taxaQueryServiceDelegate.obtenirTramit(idTramit);
    }

}
