package es.caib.rolsac.api.v2.taxa.ejb;

import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceDelegate {

    private TaxaQueryServiceEJBLocator taxaQueryServiceLocator;
    
    public void setTaxaQueryServiceLocator(TaxaQueryServiceEJBLocator taxaQueryServiceLocator) {
        this.taxaQueryServiceLocator = taxaQueryServiceLocator;
    }
        
    public TramitDTO obtenirTramit(long idTramit) {
        TaxaQueryServiceEJB ejb = taxaQueryServiceLocator.getTaxaQueryServiceEJB();
        return ejb.obtenirTramit(idTramit);
    }


}
