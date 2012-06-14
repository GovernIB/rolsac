package es.caib.rolsac.api.v2.seccio.ws;

import java.util.List;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceWebServiceStrategy implements SeccioQueryServiceStrategy {

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

    public int getNumUnitatsAdministratives(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<SeccioDTO> llistarPares(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public SeccioDTO obtenirPare(Long padre) {
        // TODO Auto-generated method stub
        return null;
    }

}
