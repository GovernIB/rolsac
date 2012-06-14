package es.caib.rolsac.api.v2.enllac;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EnllacQueryServiceAdapter extends EnllacDTO implements EnllacQueryService {

private static Log log = LogFactory.getLog(UnitatAdministrativaQueryServiceAdapter.class);
    
    private EnllacQueryServiceStrategy enllacQueryServiceStrategy;

    public void setEnllacQueryServiceStrategy(EnllacQueryServiceStrategy enllacQueryServiceStrategy) {
        this.enllacQueryServiceStrategy = enllacQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return enllacQueryServiceStrategy instanceof EnllacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public EnllacQueryServiceAdapter(EnllacDTO dto) {    
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando EnllacQueryServiceAdapter.", e);
        }
    }

    public FitxaQueryServiceAdapter obtenirFitxa() {
        if (this.getFicha() == null) {return null;}
        return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), enllacQueryServiceStrategy.obtenirFitxa(this.getFicha()));
    }

    public ProcedimentQueryServiceAdapter obtenirProcediment() {
        if (this.getProcedimiento() == null) {return null;}
        return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), enllacQueryServiceStrategy.obtenirProcediment(this.getProcedimiento()));
    }

}
