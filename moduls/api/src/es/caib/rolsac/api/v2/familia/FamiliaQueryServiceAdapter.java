package es.caib.rolsac.api.v2.familia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.familia.ejb.FamiliaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class FamiliaQueryServiceAdapter extends FamiliaDTO implements FamiliaQueryService {

    private FamiliaQueryServiceStrategy familiaQueryServiceStrategy;

    public void setFamiliaQueryServiceStrategy(FamiliaQueryServiceStrategy familiaQueryServiceStrategy) {
        this.familiaQueryServiceStrategy = familiaQueryServiceStrategy;
    }

    public FamiliaQueryServiceAdapter(FamiliaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public STRATEGY getStrategy() {
        return familiaQueryServiceStrategy instanceof FamiliaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumProcedimentsLocals() {
        return familiaQueryServiceStrategy.getNumProcedimentsLocals(id);
    }

    public int getNumIcones() {
        return familiaQueryServiceStrategy.getNumIcones(id);
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = familiaQueryServiceStrategy.llistarProcedimentsLocals(id, procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> procs = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO pDTO : llistaDTO) {
            procs.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), pDTO));
        }
        return procs;
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> llistaDTO = familiaQueryServiceStrategy.llistarIcones(id, iconaFamiliaCriteria);
        List<IconaFamiliaQueryServiceAdapter> icones = new ArrayList<IconaFamiliaQueryServiceAdapter>();
        for (IconaFamiliaDTO pDTO : llistaDTO) {
            icones.add((IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), pDTO));
        }
        return icones;
    }

}
