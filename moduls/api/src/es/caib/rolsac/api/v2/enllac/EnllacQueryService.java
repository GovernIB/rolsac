package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public interface EnllacQueryService {

    FitxaQueryServiceAdapter obtenirFitxa();

    ProcedimentQueryServiceAdapter obtenirProcediment();

}
