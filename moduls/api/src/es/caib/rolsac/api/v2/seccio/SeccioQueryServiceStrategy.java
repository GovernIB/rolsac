package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface SeccioQueryServiceStrategy {

    int getNumFilles(long id);

    int getNumFitxes(long id);

    int getNumPares(long id);

    int getNumUnitatsAdministratives(long id);

    List<SeccioDTO> llistarPares(long id);    

    List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria);

    List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria);
    
    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    SeccioDTO obtenirPare(Long padre);
    
}
