package es.caib.rolsac.api.v2.fetVital;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface FetVitalQueryService {

    public int getNumFitxes() throws QueryServiceException;

    public int getNumProcedimentsLocals() throws QueryServiceException;

    public int getNumFetsVitalsAgrupacionsFV() throws QueryServiceException;

    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException;
    
    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarFetsVitalsAgrupacionsFV(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException;
}
