package es.caib.rolsac.api.v2.edifici.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceEJBStrategy implements EdificiQueryServiceStrategy {

    EdificiQueryServiceDelegate edificiQueryServiceDelegate; 

    public void setEdificiQueryServiceDelegate(EdificiQueryServiceDelegate edificiQueryServiceDelegate) {
        this.edificiQueryServiceDelegate = edificiQueryServiceDelegate;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return edificiQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUnitatsAdministratives(long id) throws StrategyException {
        try {
            return edificiQueryServiceDelegate.getNumUnitatsAdministratives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) throws StrategyException {
        try {
            return edificiQueryServiceDelegate.obtenirFotoPequenya(idFotoPequenya);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) throws StrategyException {
        try {
            return edificiQueryServiceDelegate.obtenirFotoGrande(idFotoGrande);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirPlano(Long idPlano) throws StrategyException {
        try {
            return edificiQueryServiceDelegate.obtenirPlano(idPlano);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJB.
	}

}
