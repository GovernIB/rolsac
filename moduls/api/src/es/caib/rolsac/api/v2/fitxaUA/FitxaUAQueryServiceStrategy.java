package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface FitxaUAQueryServiceStrategy {

    FitxaDTO obtenirFitxa(long idFitxa);

    SeccioDTO obtenirSeccio(long idSeccio);

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa);

}
