package es.caib.rolsac.api.v2.fetVital;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface FetVitalQueryServiceStrategy {

    public ArxiuDTO getDistribuciCompetencial(long idDistribuciCompetencial) throws StrategyException;
    
    public ArxiuDTO getNormativa(long idNormativa) throws StrategyException;
    
    public ArxiuDTO getContingut(long idContingut) throws StrategyException;
    
    public int getNumFitxes(long id) throws StrategyException;

    public int getNumProcedimentsLocals(long id) throws StrategyException;

    public int getNumFetsVitalsAgrupacionsFV(long id) throws StrategyException;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException;

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException;

	public void setUrl(String url);

}
