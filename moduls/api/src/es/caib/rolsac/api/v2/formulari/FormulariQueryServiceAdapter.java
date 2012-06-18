package es.caib.rolsac.api.v2.formulari;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.formulari.ejb.FormulariQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class FormulariQueryServiceAdapter extends FormulariDTO implements FormulariQueryService {

    private static Log log = LogFactory.getLog(FormulariQueryServiceAdapter.class);
    
    FormulariQueryServiceStrategy formulariQueryServiceStrategy;
    
    public void setFormulariQueryServiceStrategy(FormulariQueryServiceStrategy formulariQueryServiceStrategy) {
        this.formulariQueryServiceStrategy = formulariQueryServiceStrategy;
    }

    public FormulariQueryServiceAdapter(FormulariDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando FormulariQueryServiceAdapter.", e);
        }
    }

    private STRATEGY getStrategy() {
        return formulariQueryServiceStrategy instanceof FormulariQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter obtenirArchivo() {
        if (this.getArchivo() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), formulariQueryServiceStrategy.obtenirArchivo(this.getArchivo()));
    }
    
    public ArxiuQueryServiceAdapter obtenirManual() {
        if (this.getManual() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), formulariQueryServiceStrategy.obtenirManual(this.getManual()));
    }
    
    public TramitQueryServiceAdapter obtenirTramit() {
        if (this.getTramite() == null) {return null;}
        return (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), formulariQueryServiceStrategy.obtenirTramit(this.getTramite()));        
    }

}
