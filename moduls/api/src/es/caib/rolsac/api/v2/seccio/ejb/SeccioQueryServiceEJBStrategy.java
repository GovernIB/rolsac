package es.caib.rolsac.api.v2.seccio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceEJBStrategy implements SeccioQueryServiceStrategy {

    private SeccioQueryServiceDelegate seccioQueryServiceDelegate;    

    public void setSeccioQueryServiceDelegate(SeccioQueryServiceDelegate seccioQueryServiceDelegate) {
        this.seccioQueryServiceDelegate = seccioQueryServiceDelegate;
    }

    public int getNumFilles(long id) {
        return seccioQueryServiceDelegate.getNumFilles(id);
    }

    public int getNumFitxes(long id) {
        return seccioQueryServiceDelegate.getNumFitxes(id);
    }

    public int getNumPares(long id) {
        return seccioQueryServiceDelegate.getNumPares(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        return seccioQueryServiceDelegate.getNumUnitatsAdministratives(id);
    }

    public List<SeccioDTO> llistarPares(long id) {
        return seccioQueryServiceDelegate.llistarPares(id);
    }

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
        return seccioQueryServiceDelegate.llistarFilles(id, seccioCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        return seccioQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return seccioQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public SeccioDTO obtenirPare(Long padre) {
        return seccioQueryServiceDelegate.obtenirPare(padre);
    }

}
