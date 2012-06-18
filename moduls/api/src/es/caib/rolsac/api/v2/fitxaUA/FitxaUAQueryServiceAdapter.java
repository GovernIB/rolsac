package es.caib.rolsac.api.v2.fitxaUA;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.ejb.FitxaUAQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaUAQueryServiceAdapter extends FitxaUADTO implements FitxaUAQueryService {

    private static Log log = LogFactory.getLog(FitxaUAQueryServiceAdapter.class);
    
    FitxaUAQueryServiceStrategy fitxaUAQueryServiceStrategy;    
    
    public void setFitxaUAQueryServiceStrategy(FitxaUAQueryServiceStrategy fitxaUAQueryServiceStrategy) {
        this.fitxaUAQueryServiceStrategy = fitxaUAQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return fitxaUAQueryServiceStrategy instanceof FitxaUAQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FitxaUAQueryServiceAdapter(FitxaUADTO dto) {        
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando FitxaUAQueryServiceAdapter.", e);
        }
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() {
        if (this.getUnidadAdministrativa() == null) {return null;}
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), fitxaUAQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa()));
    }

    public FitxaQueryServiceAdapter obtenirFitxa() {
        if (this.getFicha()== null) {return null;}
        return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaUAQueryServiceStrategy.obtenirFitxa(this.getFicha()));
    }

    public SeccioQueryServiceAdapter obtenirSeccio() {
        if (this.getSeccion() == null) {return null;}
        return (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), fitxaUAQueryServiceStrategy.obtenirSeccio(this.getSeccion()));
    }

}
