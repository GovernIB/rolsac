package es.caib.rolsac.api.v2.unitatMateria.ejb;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceStrategy;

public class UnitatMateriaQueryServiceEJBStrategy implements UnitatMateriaQueryServiceStrategy {

    UnitatMateriaQueryServiceDelegate unitatMateriaQueryServiceDelegate;    
    
    public void setUnitatMateriaQueryServiceDelegate(UnitatMateriaQueryServiceDelegate unitatMateriaQueryServiceDelegate) {
        this.unitatMateriaQueryServiceDelegate = unitatMateriaQueryServiceDelegate;
    }
    
    public void setUrl(String url) {
    	//Los ejbs no necesitan. 
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws StrategyException {
        try {
            return unitatMateriaQueryServiceDelegate.obtenirUnitatAdministrativa(idUnitat);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException {
        try {
            return unitatMateriaQueryServiceDelegate.obtenirMateria(idMateria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
