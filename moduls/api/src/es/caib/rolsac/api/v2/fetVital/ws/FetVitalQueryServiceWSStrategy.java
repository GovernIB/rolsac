package es.caib.rolsac.api.v2.fetVital.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class FetVitalQueryServiceWSStrategy implements FetVitalQueryServiceStrategy {

    FetVitalQueryServiceGateway gateway;
    
	public void setGateway(FetVitalQueryServiceGateway gateway) {
		this.gateway = gateway;		
	}
    
    public int getNumFitxes(long id) throws StrategyException {
    	try {
			return gateway.getNumFitxes(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}
    }
    
    public int getNumProcedimentsLocals(long id) throws StrategyException {
        try {
			return gateway.getNumProcedimentsLocals(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);		
		}
    }

    public int getNumFetsVitalsAgrupacionsFV(long id) throws StrategyException {
    	try {
			return gateway.getNumFetsVitalsAgrupacionsFV(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
    }	
	
    public ArxiuDTO getFotografia(long idFoto) throws StrategyException {
    	try {
			return gateway.getFotografia(idFoto);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
    }

    public ArxiuDTO getIcona(long idIcona) throws StrategyException {
        try {
			return gateway.getIcona(idIcona);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
    }

    public ArxiuDTO getIconaGran(long idIconaGran) throws StrategyException {
        try {
			return gateway.getIconaGran(idIconaGran);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
    }    
    
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
			return gateway.llistarFitxes(id, fitxaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

	public List<ProcedimentDTO> llistarProcedimentsLocals(long id,
			ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
			return gateway.llistarProcedimentsLocals(id, procedimentCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }    
    
    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id,
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {
        try {
			return gateway.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);		
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

}
