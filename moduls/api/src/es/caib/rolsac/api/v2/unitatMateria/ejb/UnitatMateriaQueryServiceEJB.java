package es.caib.rolsac.api.v2.unitatMateria.ejb;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class UnitatMateriaQueryServiceEJB {

    public MateriaDTO obtenirMateria(Long idMateria) {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId(String.valueOf(idMateria));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirMateria(materiaCriteria);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) {
        UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
        uaCriteria.setId(String.valueOf(idUnitat));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(uaCriteria);
    }

}
