package es.caib.rolsac.api.v2.normativa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceEJBStrategy implements NormativaQueryServiceStrategy {

    private NormativaQueryServiceDelegate normativaQueryServiceDelegate;

    public void setNormativaQueryServiceDelegate(NormativaQueryServiceDelegate normativaQueryServiceDelegate) {
        this.normativaQueryServiceDelegate = normativaQueryServiceDelegate;
    }

    public NormativaQueryServiceEJBStrategy(){
        normativaQueryServiceDelegate = new NormativaQueryServiceDelegate();
    }

    public ButlletiDTO obtenirButlleti(long idButlleti) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.obtenirButlleti(idButlleti);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<NormativaDTO> llistarAfectades(long id) throws StrategyException {        
        try {
            return normativaQueryServiceDelegate.llistarAfectades(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<NormativaDTO> llistarAfectants(long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.llistarAfectants(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.llistarProcediments(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumAfectades(long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.getNumAfectades(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumAfectants(long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.getNumAfectants(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumProcediments(long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.getNumProcediments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.obtenirUnitatAdministrativa(idUniAdm);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirArxiuNormativa(Long idArchivo) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.obtenirArxiuNormativa(idArchivo);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.llistarAfectacionsAfectants(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) throws StrategyException {
        try {
            return normativaQueryServiceDelegate.llistarAfectacionsAfectades(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No se necesita para EJB.
	}

}
