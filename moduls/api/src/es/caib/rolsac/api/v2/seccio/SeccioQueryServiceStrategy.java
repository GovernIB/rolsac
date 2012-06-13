package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;

public interface SeccioQueryServiceStrategy {

    int getNumFilles(long id);

    int getNumFitxes(long id);

    int getNumPares(long id);

    int getNumArrels(long id);

    List<SeccioDTO> llistarPares(long id, SeccioCriteria seccioCriteria);

    List<SeccioDTO> llistarArrels(long id, SeccioCriteria seccioCriteria);

    List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria);

    List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria);
    
}
