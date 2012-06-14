package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface SeccioQueryService {

    int getNumFilles();

    int getNumFitxes();

    int getNumPares();
    
    int getNumUnitatsAdministratives();

    List<SeccioQueryServiceAdapter> llistarPares();

    List<SeccioQueryServiceAdapter> llistarFilles(SeccioCriteria seccioCriteria);

    List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria);
    
    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria);
    
    SeccioQueryServiceAdapter obtenirPare();

}
