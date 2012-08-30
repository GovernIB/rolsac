package es.caib.rolsac.api.v2.agrupacioFetVital.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public class AgrupacioFetVitalQueryServiceWSStrategy implements AgrupacioFetVitalQueryServiceStrategy {

    AgrupacioFetVitalQueryServiceGateway gateway;
    
    public void setGateway( AgrupacioFetVitalQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }

    public ArxiuDTO getFotografia(long idFoto) throws StrategyException {
    	try {
			return gateway.getFotografia(idFoto);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);		}
    }

    public ArxiuDTO getIcona(long idIcona) throws StrategyException {
    	try {
			return gateway.getIcona(idIcona);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);		}
    }

    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException {
        try {
        	return gateway.getIconaGran(idIconaGran);
        } catch (RemoteException e) {
        	throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        }
    }    
    
    public int getNumFetsVitals(long id) throws StrategyException {
        try {
        	return gateway.getNumFetsVitals(id);
        } catch (RemoteException e) {
        	throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) throws StrategyException {
    	try {
    		return gateway.obtenirPublicObjectiu(idPublic);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }
    
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) throws StrategyException {
        try {
			return gateway.llistarFetsVitals(id, fetVitalCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
    }
    
}