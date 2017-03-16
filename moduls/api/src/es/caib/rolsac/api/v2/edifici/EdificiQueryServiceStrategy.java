package es.caib.rolsac.api.v2.edifici;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface EdificiQueryServiceStrategy {

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    public int getNumUnitatsAdministratives(long id) throws StrategyException;

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) throws StrategyException;

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) throws StrategyException;

    public ArxiuDTO obtenirPlano(Long idPlano) throws StrategyException;

	public void setUrl(String url);

}
