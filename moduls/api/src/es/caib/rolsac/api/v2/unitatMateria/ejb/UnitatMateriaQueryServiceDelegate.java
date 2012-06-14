package es.caib.rolsac.api.v2.unitatMateria.ejb;

import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;



public class UnitatMateriaQueryServiceDelegate {
    
    private UnitatMateriaQueryServiceEJBLocator unitatMateriaQueryServiceLocator;

    public void setUnitatMateriaQueryServiceLocator(UnitatMateriaQueryServiceEJBLocator unitatMateriaQueryServiceLocator) {
        this.unitatMateriaQueryServiceLocator = unitatMateriaQueryServiceLocator;
    }

    public MateriaDTO obtenirMateria(Long idMateria) {
        UnitatMateriaQueryServiceEJB ejb = unitatMateriaQueryServiceLocator.getUnitatMateriaQueryServiceEJB();
        return ejb.obtenirMateria(idMateria);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) {
        UnitatMateriaQueryServiceEJB ejb = unitatMateriaQueryServiceLocator.getUnitatMateriaQueryServiceEJB();
        return ejb.obtenirUnitatAdministrativa( idUnitat);
    }

    

}
