package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceStrategy;

public class MateriaAgrupacioQueryServiceEJBStrategy implements MateriaAgrupacioQueryServiceStrategy {

    private MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate;

    public void setMateriaAgrupacioQueryServiceDelegate(MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate) {
        this.materiaAgrupacioQueryServiceDelegate = materiaAgrupacioQueryServiceDelegate;
    }

    public MateriaDTO obtenirMateria(Long idMateria) {
        return materiaAgrupacioQueryServiceDelegate.obtenirMateria(idMateria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) {
        return materiaAgrupacioQueryServiceDelegate.obtenirAgrupacioMateria(idAgrupacion);
    }
}
