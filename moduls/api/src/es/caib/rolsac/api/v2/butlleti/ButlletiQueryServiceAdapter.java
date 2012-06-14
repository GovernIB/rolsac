package es.caib.rolsac.api.v2.butlleti;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.butlleti.ejb.ButlletiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;

public class ButlletiQueryServiceAdapter extends ButlletiDTO implements ButlletiQueryService {

    private ButlletiQueryServiceStrategy butlletiQueryServiceStrategy;
    
    public void setButlletiQueryServiceStrategy(ButlletiQueryServiceStrategy butlletiQueryServiceStrategy) {
        this.butlletiQueryServiceStrategy = butlletiQueryServiceStrategy;
    }

    public ButlletiQueryServiceAdapter(ButlletiDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    private STRATEGY getStrategy() {
        return butlletiQueryServiceStrategy instanceof ButlletiQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumNormatives() {
        return butlletiQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.TOTES);
    }

    public int getNumNormativesLocals() {
        return butlletiQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.LOCAL);
    }

    public int getNumNormativesExternes() {
        return butlletiQueryServiceStrategy.getNumNormatives(id, TIPUS_NORMATIVA.EXTERNA);
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> llistaDTO = butlletiQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

}
