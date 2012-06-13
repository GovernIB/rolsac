package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface FitxaUAQueryServiceStrategy {

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    FitxaDTO obtenirFitxa(long id, FitxaCriteria fitxaCriteria);

    SeccioDTO obtenirSeccio(long id, SeccioCriteria seccioCriteria);

}
