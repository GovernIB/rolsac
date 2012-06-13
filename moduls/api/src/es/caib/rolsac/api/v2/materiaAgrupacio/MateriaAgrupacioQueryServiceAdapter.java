package es.caib.rolsac.api.v2.materiaAgrupacio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;

public class MateriaAgrupacioQueryServiceAdapter extends MateriaAgrupacioDTO implements MateriaAgrupacioQueryService {

    private MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy;
    
    public void setMateriaAgrupacioQueryServiceStrategy(MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy) {
        this.materiaAgrupacioQueryServiceStrategy = materiaAgrupacioQueryServiceStrategy;
    }

    public MateriaAgrupacioQueryServiceAdapter(MateriaAgrupacioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public AgrupacioMateriaQueryService obtenirAgrupacio(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
