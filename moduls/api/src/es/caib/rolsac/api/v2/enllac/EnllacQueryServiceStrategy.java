package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface EnllacQueryServiceStrategy {

    FitxaDTO obtenirFitxa(long id, FitxaCriteria fitxaCriteria);

    ProcedimentDTO obtenirProcediment(long id, ProcedimentCriteria procedimentCriteria);

}
