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

    public ArxiuDTO getFotografia(long idFoto) throws StrategyException;
    
    public ArxiuDTO getIcona(long idIcona) throws StrategyException;
    
    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException;
    
    public int getNumFitxes(long id) throws StrategyException;

    public int getNumProcedimentsLocals(long id) throws StrategyException;

    public int getNumFetsVitalsAgrupacionsFV(long id) throws StrategyException;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException;

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException;

}
