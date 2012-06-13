package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryService;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.ejb.SeccioQueryServiceEJBStrategy;

public class SeccioQueryServiceAdapter extends SeccioDTO implements SeccioQueryService {

    private static Log log = LogFactory.getLog(SeccioQueryServiceAdapter.class);
    
    // @Injected
    private SeccioQueryServiceStrategy seccioQueryServiceStrategy;
    
    public void setSeccioQueryServiceStrategy(SeccioQueryServiceStrategy seccioQueryServiceStrategy) {
        this.seccioQueryServiceStrategy = seccioQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return seccioQueryServiceStrategy instanceof SeccioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public SeccioQueryServiceAdapter(SeccioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando SeccioQueryServiceAdapter.", e);
        }
    }
    
    public int getNumFilles() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFitxes() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<SeccioQueryService> llistarFilles(SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaUAQueryService> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumPares() {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumArrels() {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<SeccioQueryService> llistarPares(SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioQueryService> llistarArrels(SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
