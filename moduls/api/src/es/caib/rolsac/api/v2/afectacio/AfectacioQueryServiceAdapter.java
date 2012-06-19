package es.caib.rolsac.api.v2.afectacio;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.afectacio.ejb.AfectacioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;

public class AfectacioQueryServiceAdapter extends AfectacioDTO implements AfectacioQueryService {

    private static Log log = LogFactory.getLog(AfectacioQueryServiceAdapter.class);
    
    AfectacioQueryServiceStrategy afectacioQueryServiceStrategy;

    public void setAfectacioQueryServiceStrategy(AfectacioQueryServiceStrategy afectacioQueryServiceStrategy) {
        this.afectacioQueryServiceStrategy = afectacioQueryServiceStrategy;
    }

    public AfectacioQueryServiceAdapter(AfectacioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando AfectacioQueryServiceAdapter.", e);
        }
    }

    private STRATEGY getStrategy() {
        return afectacioQueryServiceStrategy instanceof AfectacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public NormativaQueryServiceAdapter obtenirAfectant() {
        return (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), afectacioQueryServiceStrategy.obtenirAfectant(this.getAfectante()));
    }

    public NormativaQueryServiceAdapter obtenirNormativa() {
        return (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), afectacioQueryServiceStrategy.obtenirNormativa(this.getNormativa()));
    }

    public TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio() {
        return (TipusAfectacioQueryServiceAdapter) BeanUtils.getAdapter("tipusAfectacio", getStrategy(), afectacioQueryServiceStrategy.obtenirTipusAfectacio(this.getTipoAfectacion()));
    }  
    
}
