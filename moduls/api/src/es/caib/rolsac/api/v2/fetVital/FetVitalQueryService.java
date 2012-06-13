package es.caib.rolsac.api.v2.fetVital;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface FetVitalQueryService {

    int getNumFitxes();

    int getNumProcedimentsLocals();

    int getNumFetsVitalsAgrupacionsFV();

    ArxiuQueryServiceAdapter getFotografia();
    
    ArxiuQueryServiceAdapter getIcona();
    
    ArxiuQueryServiceAdapter getIconaGran();
    
    List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria);

    List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria);

    List<AgrupacioFetVitalQueryServiceAdapter> llistarFetsVitalsAgrupacionsFV(
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);
}
