package es.caib.rolsac.api.v2.unitatAdministrativa.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceWSStrategy implements UnitatAdministrativaQueryServiceStrategy {

    UnitatAdministrativaQueryServiceGateway gateway;

    public void setGateway(UnitatAdministrativaQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public UnitatAdministrativaDTO obtenirPare(long idPare) throws StrategyException {
    	try {
    		return gateway.obtenirPare(idPare);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) throws StrategyException {
    	try {
    		return gateway.obtenirEspaiTerritorial(idEt);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public TractamentDTO obtenirTractament(long idTract) throws StrategyException {
    	try {
    		return gateway.obtenirTractament(idTract);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}    	
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
    	try {
    		return gateway.llistarFilles(id, unitatAdministrativaCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) throws StrategyException {
    	try {
    		return gateway.llistarEdificis(id, edificiCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) throws StrategyException {
    	try {
    		return gateway.llistarPersonal(id, personalCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException {
    	try {
    		return gateway.llistarNormatives(id, normativaCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
    	try {
    		return gateway.llistarProcediments(id, procedimentCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws StrategyException {
    	try {
    		return gateway.llistarTramits(id, tramitCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}    	
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) throws StrategyException {
    	try {
    		return gateway.llistarUsuaris(id, usuariCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}    	
    }

//    public List<FitxaUADTO> llistarTotesFitxes(long id, FitxaUACriteria fitxaUACriteria) throws StrategyException {
//    	try {
//    		return gateway.llistarTotesFitxes(id, fitxaUACriteria);
//    	} catch (RemoteException e) {
//    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
//    	}    	
//    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
    	try {
    		return gateway.llistarFitxes(id, fitxaCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}    	
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws StrategyException {
    	try {
    		return gateway.llistarSeccions(id, seccioCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
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

    public ArxiuDTO obtenirFotop(Long fotop) throws StrategyException {
    	try {
    		return gateway.obtenirFotop(fotop);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirFotog(Long fotog) throws StrategyException {
    	try {
    		return gateway.obtenirFotog(fotog);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} 
    }

    public ArxiuDTO obtenirLogoh(Long logoh) throws StrategyException {
    	try {
    		return gateway.obtenirLogoh(logoh);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirLogov(Long logov) throws StrategyException {
    	try {
    		return gateway.obtenirLogov(logov);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirLogos(Long logos) throws StrategyException {
    	try {
    		return gateway.obtenirLogos(logos);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirLogot(Long logot) throws StrategyException {
    	try {
    		return gateway.obtenirLogot(logot);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumFilles(Long id) throws StrategyException {
    	try {
    		return gateway.getNumFilles(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumEdificis(Long id) throws StrategyException {
    	try {
    		return gateway.getNumEdificis(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumPersonal(Long id) throws StrategyException {
    	try {
    		return gateway.getNumPersonal(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumNormatives(Long id) throws StrategyException {
    	try {
    		return gateway.getNumNormatives(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumProcediments(Long id) throws StrategyException {
    	try {
    		return gateway.getNumProcediments(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumTramits(Long id) throws StrategyException {
    	try {
    		return gateway.getNumTramits(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumUsuaris(Long id) throws StrategyException {
    	try {
    		return gateway.getNumUsuaris(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumFitxes(Long id) throws StrategyException {
    	try {
    		return gateway.getNumFitxes(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public int getNumSeccions(Long id) throws StrategyException {
    	try {
    		return gateway.getNumSeccions(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}    	
    }

    public int getNumMateries(Long id) throws StrategyException {
    	try {
    		return gateway.getNumMateries(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }
}
