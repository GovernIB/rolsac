package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class AgrupacioFetVitalQueryServiceEJBStrategy implements AgrupacioFetVitalQueryServiceStrategy {

    private AgrupacioFetVitalQueryServiceDelegate agrupacioFetVitalQueryServiceDelegate;
    
    public void setAgrupacioFetVitalQueryServiceDelegate(AgrupacioFetVitalQueryServiceDelegate agrupacioFetVitalQueryServiceDelegate) {
        this.agrupacioFetVitalQueryServiceDelegate = agrupacioFetVitalQueryServiceDelegate;
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) throws StrategyException {        
        try {
            return agrupacioFetVitalQueryServiceDelegate.obtenirPublicObjectiu(idPublic);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) throws StrategyException {
        try {
            return agrupacioFetVitalQueryServiceDelegate.llistarFetsVitals(id, fetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO getFotografia(long idFoto) throws StrategyException {
        try {
            return agrupacioFetVitalQueryServiceDelegate.getFotografia(idFoto);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO getIcona(long idIcona) throws StrategyException {
        try {
            return agrupacioFetVitalQueryServiceDelegate.getIcona(idIcona);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException {
        try {
            return agrupacioFetVitalQueryServiceDelegate.getIconaGran(idIconaGran);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFetsVitals(long id) throws StrategyException {
        try {
            return agrupacioFetVitalQueryServiceDelegate.getNumFetsVitals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
}
