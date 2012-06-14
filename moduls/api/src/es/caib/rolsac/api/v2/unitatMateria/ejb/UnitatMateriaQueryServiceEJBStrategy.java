package es.caib.rolsac.api.v2.unitatMateria.ejb;

import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceStrategy;

public class UnitatMateriaQueryServiceEJBStrategy implements UnitatMateriaQueryServiceStrategy {

    UnitatMateriaQueryServiceDelegate unitatMateriaQueryServiceDelegate;    
    
    public void setUnitatMateriaQueryServiceDelegate(UnitatMateriaQueryServiceDelegate unitatMateriaQueryServiceDelegate) {
        this.unitatMateriaQueryServiceDelegate = unitatMateriaQueryServiceDelegate;
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) {
        return unitatMateriaQueryServiceDelegate.obtenirUnitatAdministrativa(idUnitat);
    }

    public MateriaDTO obtenirMateria(Long idMateria) {
        return unitatMateriaQueryServiceDelegate.obtenirMateria(idMateria);
    }

}
