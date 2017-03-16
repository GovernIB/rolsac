package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceStrategy;

public class PublicObjectiuQueryServiceEJBStrategy implements PublicObjectiuQueryServiceStrategy {

    private PublicObjectiuQueryServiceDelegate publicObjectiuQueryServiceDelegate;

    public void setPublicObjectiuQueryServiceDelegate(PublicObjectiuQueryServiceDelegate publicObjectiuQueryServiceDelegate) {
        this.publicObjectiuQueryServiceDelegate = publicObjectiuQueryServiceDelegate;
    }
        
    public int getNumAgrupacions(long id) throws StrategyException {
        try {
            return publicObjectiuQueryServiceDelegate.getNumAgrupacions(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id, AgrupacioFetVitalCriteria agurpacioFetVitalCriteria) throws StrategyException {
        try {
            return publicObjectiuQueryServiceDelegate.llistarAgrupacions(id, agurpacioFetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return publicObjectiuQueryServiceDelegate.llistarProcediments(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return publicObjectiuQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}

}
