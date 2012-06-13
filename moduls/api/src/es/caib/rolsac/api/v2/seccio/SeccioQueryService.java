package es.caib.rolsac.api.v2.seccio;

import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryService;

public interface SeccioQueryService {

    int getNumFilles();

    int getNumFitxes();

    int getNumPares();

    int getNumArrels();

    List<SeccioQueryService> llistarPares(SeccioCriteria seccioCriteria);

    List<SeccioQueryService> llistarArrels(SeccioCriteria seccioCriteria);

    List<SeccioQueryService> llistarFilles(SeccioCriteria seccioCriteria);

    List<FitxaUAQueryService> llistarFitxesUA(FitxaUACriteria fitxaUACriteria);

}
