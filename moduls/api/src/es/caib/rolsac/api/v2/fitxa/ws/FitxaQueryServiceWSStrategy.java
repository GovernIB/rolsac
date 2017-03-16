package es.caib.rolsac.api.v2.fitxa.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaQueryServiceWSStrategy implements FitxaQueryServiceStrategy {

	FitxaQueryServiceGateway gateway;

	public void setGateway(FitxaQueryServiceGateway gateway)
			throws StrategyException {
		this.gateway = gateway;
	}	
	
	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}
	
	public int getNumUnitatsAdministratives(long id) throws StrategyException {
		try {
			return gateway.getNumUnitatsAdministratives(id);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
	
	public int getNumDocuments(long id) throws StrategyException {
		try {
			return gateway.getNumDocuments(id);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}	
	
	public int getNumEnllacos(long id) throws StrategyException {
		try {
			return gateway.getNumEnllacos(id);			
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

	public int getNumSeccions(long id) throws StrategyException {
		try {
			return gateway.getNumSeccions(id);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ArxiuDTO obtenirIcona(Long icono) throws StrategyException {
		try {
			return gateway.obtenirIcona(icono);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ArxiuDTO obtenirImatge(Long imagen) throws StrategyException {
		try {
			return gateway.obtenirImatge(imagen);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ArxiuDTO obtenirBaner(Long baner) throws StrategyException {
		try {
			return gateway.obtenirBaner(baner);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) throws StrategyException {
		try {
			return gateway.llistarEnllacos(id, enllacCriteria);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<DocumentDTO> llistarDocuments(long id,
			DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return gateway.llistarDocuments(id, documentCriteria);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<FetVitalDTO> llistarFetsVitals(long id,
			FetVitalCriteria fetVitalCriteria) throws StrategyException {
		try {
			return gateway.llistarFetsVitals(id, fetVitalCriteria);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<SeccioDTO> llistarSeccions(long id,
			SeccioCriteria seccioCriteria) throws StrategyException {
		try {
			return gateway.llistarSeccions(id, seccioCriteria);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<FitxaUADTO> llistarFitxesUA(long id,
			FitxaUACriteria fitxaUACriteria) throws StrategyException {
		try {
			return gateway.llistarFitxesUA(id, fitxaUACriteria);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria)
            throws StrategyException {
        try {
            return gateway.llistarPublicsObjectius(id, poCriteria);         
        } catch (RemoteException e) {
            throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        } catch (APIException e) {
        	throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
        }
    }

	public int getNumMateries(long id) throws StrategyException {
		try {
			return gateway.getNumMateries(id);			
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException {
		try {
            return gateway.llistarMateries(id, materiaCriteria);         
        } catch (RemoteException e) {
            throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        } catch (APIException e) {
        	throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
        }
	}

	
	
}
