package es.caib.rolsac.api.v2.fetVital;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface FetVitalQueryServiceStrategy {

    ArxiuDTO getFotografia(long idFoto);
    
    ArxiuDTO getIcona(long idIcona);
    
    ArxiuDTO getIconaGran(long idIconaGran);
    
    int getNumFitxes(long id);

    int getNumProcedimentsLocals(long id);

    int getNumFetsVitalsAgrupacionsFV(long id);

    List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria);

    List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria);

    List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id,
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);

}
