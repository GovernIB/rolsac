package es.caib.rolsac.api.v2.materia.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;

public class MateriaQueryServiceWSStrategy implements
		MateriaQueryServiceStrategy {

	MateriaQueryServiceGateway gateway;

	public void setGateway(MateriaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public int getNumFitxes(long id) throws StrategyException {
		try {
			return gateway.getNumFitxes(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumAgrupacioMateries(long id) throws StrategyException {
		try {
			return gateway.getNumAgrupacioMateries(id);
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

	public int getNumUnitatsMateries(long id) throws StrategyException {
		try {
			return gateway.getNumUnitatsMateries(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumIcones(long id) throws StrategyException {
		try {
			return gateway.getNumIcones(id);
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

	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarFitxes(id, fitxaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}				
	}

	public List<AgrupacioMateriaDTO> llistarAgrupacioMateries(long id,
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarAgrupacioMateries( id, agrupacioMateriaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}								
	}

	public List<IconaMateriaDTO> llistarIconesMateries(long id,
			IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
		try {
			return gateway.llistarIconesMateries(id, iconaMateriaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}								
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}										
	}

	public List<UnitatMateriaDTO> llistarUnitatsMateria(long id,
			UnitatMateriaCriteria unitatMateriaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarUnitatsMateria(id, unitatMateriaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}								
	}
}
