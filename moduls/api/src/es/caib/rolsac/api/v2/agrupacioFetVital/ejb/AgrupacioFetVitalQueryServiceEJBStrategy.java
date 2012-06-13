package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class AgrupacioFetVitalQueryServiceEJBStrategy implements AgrupacioFetVitalQueryServiceStrategy {

    private AgrupacioFetVitalQueryServiceDelegate agrupacioFetVitalQueryServiceDelegate;
    
    public void setAgrupacioFetVitalQueryServiceDelegate(AgrupacioFetVitalQueryServiceDelegate agrupacioFetVitalQueryServiceDelegate) {
        this.agrupacioFetVitalQueryServiceDelegate = agrupacioFetVitalQueryServiceDelegate;
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) {        
        return agrupacioFetVitalQueryServiceDelegate.obtenirPublicObjectiu(idPublic);
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        return agrupacioFetVitalQueryServiceDelegate.llistarFetsVitals(id, fetVitalCriteria);
    }

    public ArxiuDTO getFotografia(long idFoto) {
        return agrupacioFetVitalQueryServiceDelegate.getFotografia(idFoto);
    }

    public ArxiuDTO getIcona(long idIcona) {
        return agrupacioFetVitalQueryServiceDelegate.getIcona(idIcona);
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        return agrupacioFetVitalQueryServiceDelegate.getIconaGran(idIconaGran);
    }

    public int getNumFetsVitals(long id) {
        return agrupacioFetVitalQueryServiceDelegate.getNumFetsVitals(id);
    }
}
