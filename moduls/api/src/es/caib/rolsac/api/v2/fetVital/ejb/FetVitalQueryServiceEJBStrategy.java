package es.caib.rolsac.api.v2.fetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceEJBStrategy implements FetVitalQueryServiceStrategy {

    private FetVitalQueryServiceDelegate fetVitalQueryServiceDelegate;
    
    public void setFetVitalQueryServiceDelegate(FetVitalQueryServiceDelegate fetVitalQueryServiceDelegate) {
        this.fetVitalQueryServiceDelegate = fetVitalQueryServiceDelegate;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.llistarProcedimentsLocals(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFitxes(long id) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getNumFitxes(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumProcedimentsLocals(long id) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getNumProcedimentsLocals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getNumFetsVitalsAgrupacionsFV(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO getDistribuciCompetencial(long idDistribuciCompetencial) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getDistribuciCompetencial(idDistribuciCompetencial);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getNormativa(long idNormativa) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getNormativa(idNormativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getContingut(long idContingut) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getContingut(idContingut);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No se utiliza en EJBs.
	}

}
