package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class MateriaAgrupacioQueryServiceEJB {

    public MateriaDTO obtenirMateria(Long idMateria) {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId(String.valueOf(idMateria));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();        
        return ejb.obtenirMateria(materiaCriteria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) {
        AgrupacioMateriaCriteria agrupacioMateriaCriteria = new AgrupacioMateriaCriteria();
        agrupacioMateriaCriteria.setId(String.valueOf(idAgrupacion));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();        
        return ejb.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
    }

}
