package es.caib.rolsac.api.v2.unitatMateria.ejb;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceStrategy;

public class UnitatMateriaQueryServiceEJBStrategy implements UnitatMateriaQueryServiceStrategy {

    UnitatMateriaQueryServiceLocator locator;
    UnitatMateriaQueryServiceDelegate delegate;

    public MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
