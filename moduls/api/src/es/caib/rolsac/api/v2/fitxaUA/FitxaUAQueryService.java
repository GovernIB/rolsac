package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryService;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public interface FitxaUAQueryService {

    UnitatAdministrativaQueryService obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    FitxaQueryService obtenirFitxa(FitxaCriteria fitxaCriteria);

    SeccioQueryService obtenirSeccio(SeccioCriteria seccioCriteria);

}
