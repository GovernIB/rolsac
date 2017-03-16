package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceEJBStrategy implements EspaiTerritorialQueryServiceStrategy {

    EspaiTerritorialQueryServiceDelegate espaiTerritorialQueryServiceDelegate;

    public void setEspaiTerritorialQueryServiceDelegate(EspaiTerritorialQueryServiceDelegate espaiTerritorialQueryServiceDelegate) {
        this.espaiTerritorialQueryServiceDelegate = espaiTerritorialQueryServiceDelegate;
    }

    public int getNumFills(long id) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.getNumFills(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.getNumUnitatsAdministratives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.llistarFills(id, espaiTerritorialCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativa) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.obtenirPare(idPadre);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirMapa(Long idMapa) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.obtenirMapa(idMapa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirLogo(Long idLogo) throws StrategyException {
        try {
            return espaiTerritorialQueryServiceDelegate.obtenirLogo(idLogo);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJB.
	}

}
