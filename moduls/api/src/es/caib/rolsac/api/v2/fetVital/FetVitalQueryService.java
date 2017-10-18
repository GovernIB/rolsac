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
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceAdapter;

public interface FetVitalQueryService {

    public int getNumFitxes() throws QueryServiceException;

    public int getNumProcedimentsLocals() throws QueryServiceException;

    public int getNumServicios() throws QueryServiceException;

    public int getNumFetsVitalsAgrupacionsFV() throws QueryServiceException;

    public ArxiuQueryServiceAdapter getDistribuciCompetencial() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getNormativa() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter getContingut() throws QueryServiceException;
    
    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

    public List<ServicioQueryServiceAdapter> llistarServicios(ServicioCriteria servicioCriteria) throws QueryServiceException;

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarFetsVitalsAgrupacionsFV(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException;
}
