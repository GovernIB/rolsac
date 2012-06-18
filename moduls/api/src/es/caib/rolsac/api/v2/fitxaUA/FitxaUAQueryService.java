package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface FitxaUAQueryService {

    UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa();

    FitxaQueryServiceAdapter obtenirFitxa();

    SeccioQueryServiceAdapter obtenirSeccio();

}
