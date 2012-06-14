package es.caib.rolsac.api.v2.seccio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;


public class SeccioQueryServiceDelegate {
    
    private SeccioQueryServiceEJBLocator  seccioQueryServiceLocator;

    public void setSeccioQueryServiceLocator(SeccioQueryServiceEJBLocator seccioQueryServiceLocator) {
        this.seccioQueryServiceLocator = seccioQueryServiceLocator;
    }

    public int getNumFilles(long id) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.getNumFilles(id);
    }

    public int getNumFitxes(long id) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.getNumFitxes(id);
    }

    public int getNumPares(long id) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.getNumPares(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.getNumUnitatsAdministratives(id);
    }

    public List<SeccioDTO> llistarPares(long id) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.llistarPares(id);
    }

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.llistarFilles(id, seccioCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.llistarFitxes(id, fitxaCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public SeccioDTO obtenirPare(Long padre) {
        SeccioQueryServiceEJB ejb = seccioQueryServiceLocator.getSeccioQueryServiceEJB();
        return ejb.obtenirPare(padre);
    }


}
