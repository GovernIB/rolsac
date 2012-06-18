package es.caib.rolsac.api.v2.tipus;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.ejb.TipusQueryServiceEJBStrategy;

public class TipusQueryServiceAdapter extends TipusDTO implements TipusQueryService {

    private static Log log = LogFactory.getLog(TipusQueryServiceAdapter.class);
    
    TipusQueryServiceStrategy tipusQueryServiceStrategy;   
    
    public void setTipusQueryServiceStrategy(TipusQueryServiceStrategy tipusQueryServiceStrategy) {
        this.tipusQueryServiceStrategy = tipusQueryServiceStrategy;
    }

    public TipusQueryServiceAdapter(TipusDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando TipusQueryServiceAdapter.", e);
        }
    }
    
    private STRATEGY getStrategy() {
        return tipusQueryServiceStrategy instanceof TipusQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumNormatives() {
        return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.TOTES);
    }

    public int getNumNormativesLocals() {
        return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.LOCAL);
    }

    public int getNumNormativesExternes() {
        return tipusQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.EXTERNA);
    }    
    
    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> llistaDTO = tipusQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
}
