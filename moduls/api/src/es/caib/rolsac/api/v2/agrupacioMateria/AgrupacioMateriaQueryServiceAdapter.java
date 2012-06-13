package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioMateria.ejb.AgrupacioMateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;

public class AgrupacioMateriaQueryServiceAdapter extends AgrupacioMateriaDTO implements AgrupacioMateriaQueryService {

    private AgrupacioMateriaQueryServiceStrategy agrupacioMateriaQueryServiceStrategy;

    public void setAgrupacioMateriaQueryServiceStrategy(AgrupacioMateriaQueryServiceStrategy agrupacioMateriaQueryServiceStrategy) {
        this.agrupacioMateriaQueryServiceStrategy = agrupacioMateriaQueryServiceStrategy;
    }

    public AgrupacioMateriaQueryServiceAdapter(AgrupacioMateriaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return agrupacioMateriaQueryServiceStrategy instanceof AgrupacioMateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public SeccioQueryServiceAdapter obtenirSeccio() {
        if (this.getSeccion() == null) {return null;}
        SeccioDTO dto = agrupacioMateriaQueryServiceStrategy.obtenirSeccio(this.getSeccion());
        return (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), dto);
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) {
        List<MateriaDTO> llistaDTO = agrupacioMateriaQueryServiceStrategy.llistarMateries(id, materiaCriteria);
        List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
        for (MateriaDTO materiaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public int getNumMateries() {        
        return agrupacioMateriaQueryServiceStrategy.getNumMateries(id);
    }

}
