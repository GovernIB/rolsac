package es.caib.rolsac.api.v2.enllac;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.ejb.UnitatAdministrativaQueryServiceEJBStrategy;

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

    public FitxaQueryService obtenirFitxa(FitxaCriteria fitxaCriteria) {
        FitxaDTO dto = enllacQueryServiceStrategy.obtenirFitxa(id, fitxaCriteria);
        return new FitxaQueryServiceAdapter(dto);
    }

    public ProcedimentQueryService obtenirProcediment(ProcedimentCriteria procedimentCriteria) {

        ProcedimentDTO dto = enllacQueryServiceStrategy.obtenirProcediment(id, procedimentCriteria);
        return new ProcedimentQueryServiceAdapter(dto);
    }

}
