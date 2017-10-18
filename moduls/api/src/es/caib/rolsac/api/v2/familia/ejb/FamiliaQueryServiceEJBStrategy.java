package es.caib.rolsac.api.v2.familia.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;

public class FamiliaQueryServiceEJBStrategy implements FamiliaQueryServiceStrategy {

    private FamiliaQueryServiceDelegate familiaQueryServiceDelegate;

    public void setFamiliaQueryServiceDelegate(FamiliaQueryServiceDelegate familiaQueryServiceDelegate) {
        this.familiaQueryServiceDelegate = familiaQueryServiceDelegate;
    }

    public int getNumProcedimentsLocals(long id) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.getNumProcedimentsLocals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public int getNumServicios(long id) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.getNumServicios(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumIcones(long id) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.getNumIcones(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.llistarServicios(id, servicioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
        try {
            return familiaQueryServiceDelegate.llistarIcones(id, iconaFamiliaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJB.
	}

}
