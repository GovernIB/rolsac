package es.caib.rolsac.api.v2.procediment.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceStrategy;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class ProcedimentQueryServiceWSStrategy implements ProcedimentQueryServiceStrategy {

	// @Injected
	ProcedimentQueryServiceGateway gateway;

	public void setGateway(ProcedimentQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public int getNumTramits(long id) throws StrategyException {
		try {
			return gateway.getNumTramits(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumNormatives(long id, TIPUS_NORMATIVA tipus)
			throws StrategyException {
		try {
			return gateway.getNumNormatives(id, tipus);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumMateries(long id) throws StrategyException {
		try {
			return gateway.getNumMateries(id);
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

	public int getNumFetsVitals(long id) throws StrategyException {
		try {
			return gateway.getNumFetsVitals(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria)
			throws StrategyException {
		try {
			return gateway.llistarTramits(id, tramitCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return gateway.llistarNormatives(id, normativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria) throws StrategyException {
		try {
			return gateway.llistarMateries(id, materiaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<FetVitalDTO> llistarFetsVitals(long id,
			FetVitalCriteria fetsVitalsCriteria) throws StrategyException {
		try {
			return gateway.llistarFetsVitals(id, fetsVitalsCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<DocumentDTO> llistarDocuments(long id,
			DocumentCriteria documentCriteria) throws StrategyException {
		try {
			return gateway.llistarDocuments(id, documentCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws StrategyException {
        try {
            return gateway.llistarPublicsObjectius(id, poCriteria);
        } catch (RemoteException e) {
            throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
