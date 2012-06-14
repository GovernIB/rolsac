package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface EnllacQueryServiceStrategy {

    FitxaDTO obtenirFitxa(long idFitxa);

    ProcedimentDTO obtenirProcediment(long idProcediment);

}
