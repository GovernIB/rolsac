package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;


public class AgrupacioFetVitalQueryServiceDelegate {
    
    private AgrupacioFetVitalQueryServiceEJBLocator agrupacioFetVitalQueryServiceLocator;
    
    public void setAgrupacioFetVitalQueryServiceLocator(AgrupacioFetVitalQueryServiceEJBLocator agrupacioFetVitalQueryServiceLocator) {
        this.agrupacioFetVitalQueryServiceLocator = agrupacioFetVitalQueryServiceLocator;
    }

    public ArxiuDTO getFotografia(long idFoto) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.getFotografia(idFoto);
    }

    public ArxiuDTO getIcona(long idIcona) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.getIcona(idIcona);
    }

    public ArxiuDTO getIconaGran(long idIconaGran) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.getIconaGran(idIconaGran);
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.obtenirPublicObjectiu(idPublic);
    }
    
    public int getNumFetsVitals(long id) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.getNumFetsVitals(id);
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        AgrupacioFetVitalQueryServiceEJB ejb = agrupacioFetVitalQueryServiceLocator.getAgrupacioFetVitalQueryServiceEJB();
        return ejb.llistarFetsVitals(id, fetVitalCriteria);
    }
    
    
}