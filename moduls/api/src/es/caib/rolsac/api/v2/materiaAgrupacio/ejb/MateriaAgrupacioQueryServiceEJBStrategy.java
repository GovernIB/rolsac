package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceStrategy;

public class MateriaAgrupacioQueryServiceEJBStrategy implements MateriaAgrupacioQueryServiceStrategy {

    private MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate;

    public void setMateriaAgrupacioQueryServiceDelegate(MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate) {
        this.materiaAgrupacioQueryServiceDelegate = materiaAgrupacioQueryServiceDelegate;
    }

    public MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
