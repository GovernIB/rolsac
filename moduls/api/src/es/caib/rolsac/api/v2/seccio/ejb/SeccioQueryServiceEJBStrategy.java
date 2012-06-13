package es.caib.rolsac.api.v2.seccio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceStrategy;

public class SeccioQueryServiceEJBStrategy implements SeccioQueryServiceStrategy {

    private SeccioQueryServiceDelegate seccioQueryServiceDelegate;    

    public void setSeccioQueryServiceDelegate(SeccioQueryServiceDelegate seccioQueryServiceDelegate) {
        this.seccioQueryServiceDelegate = seccioQueryServiceDelegate;
    }

    public int getNumFilles(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFitxes(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumPares(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumArrels(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<SeccioDTO> llistarPares(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioDTO> llistarArrels(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaUADTO> llistarFitxesUA(long id, FitxaUACriteria fitxaUACriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
