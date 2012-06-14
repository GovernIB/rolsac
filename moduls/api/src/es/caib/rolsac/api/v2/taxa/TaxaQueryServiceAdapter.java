package es.caib.rolsac.api.v2.taxa;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.ejb.MateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.taxa.ejb.TaxaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class TaxaQueryServiceAdapter extends TaxaDTO implements TaxaQueryService {

    private TaxaQueryServiceStrategy taxaQueryServiceStrategy;    
    
    public void setTaxaQueryServiceStrategy(TaxaQueryServiceStrategy taxaQueryServiceStrategy) {
        this.taxaQueryServiceStrategy = taxaQueryServiceStrategy;
    }

    public TaxaQueryServiceAdapter(TaxaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    private STRATEGY getStrategy() {
        return taxaQueryServiceStrategy instanceof TaxaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public TramitQueryServiceAdapter obtenirTramit() {

        if (this.getTramit() == null) {return null;}
        return (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), taxaQueryServiceStrategy.obtenirTramit(this.getTramit()));
    }

}
