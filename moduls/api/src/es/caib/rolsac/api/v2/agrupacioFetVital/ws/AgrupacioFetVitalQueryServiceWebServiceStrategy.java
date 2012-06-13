package es.caib.rolsac.api.v2.agrupacioFetVital.ws;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.AgrupacioFetVitalQueryServiceDelegate;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class AgrupacioFetVitalQueryServiceWebServiceStrategy implements AgrupacioFetVitalQueryServiceStrategy {

    AgrupacioFetVitalQueryServiceDelegate delegate;

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO getFotografia(long idFoto) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO getIcona(long idIcona) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumFetsVitals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) {
        // TODO Auto-generated method stub
        return null;
    }
}
