package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public class IconaFamiliaQueryServiceEJBStrategy implements IconaFamiliaQueryServiceStrategy {

    IconaFamiliaQueryServiceLocator locator;
    IconaFamiliaQueryServiceDelegate delegate;

    public FamiliaDTO obtenirFamilia(long id, FamiliaCriteria familiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
