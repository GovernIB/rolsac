package es.caib.rolsac.api.v2.seccio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceEJBStrategy implements SeccioQueryServiceStrategy {

    private SeccioQueryServiceDelegate seccioQueryServiceDelegate;    

    public void setSeccioQueryServiceDelegate(SeccioQueryServiceDelegate seccioQueryServiceDelegate) {
        this.seccioQueryServiceDelegate = seccioQueryServiceDelegate;
    }

    public int getNumFilles(long id) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.getNumFilles(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFitxes(long id) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.getNumFitxes(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumPares(long id) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.getNumPares(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.getNumUnitatsAdministratives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<SeccioDTO> llistarPares(long id) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.llistarPares(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.llistarFilles(id, seccioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.llistarFitxes(id, fitxaCriteria, fitxaUACriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria, FitxaUACriteria fitxaUACriteria) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria, fitxaUACriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public SeccioDTO obtenirPare(Long padre) throws StrategyException {
        try {
            return seccioQueryServiceDelegate.obtenirPare(padre);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en ejbs.
	}

}
