package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryService;

public interface EnllacQueryService {

    FitxaQueryService obtenirFitxa(FitxaCriteria fitxaCriteria);

    ProcedimentQueryService obtenirProcediment(ProcedimentCriteria procedimentCriteria);

}
