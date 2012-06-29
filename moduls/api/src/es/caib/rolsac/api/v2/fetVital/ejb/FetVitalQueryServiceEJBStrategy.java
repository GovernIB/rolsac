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

    public ArxiuDTO getFotografia(long idFoto) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getFotografia(idFoto);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getIcona(long idIcona) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getIcona(idIcona);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException {
        try {
            return fetVitalQueryServiceDelegate.getIconaGran(idIconaGran);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
