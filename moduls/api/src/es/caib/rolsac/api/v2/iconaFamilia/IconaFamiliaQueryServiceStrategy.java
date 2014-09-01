package es.caib.rolsac.api.v2.iconaFamilia;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public interface IconaFamiliaQueryServiceStrategy {

    public FamiliaDTO obtenirFamilia(long id) throws StrategyException;
    public PerfilDTO obtenirPerfil(long id) throws StrategyException;
    public ArxiuDTO obtenirIcona(long id) throws StrategyException;

}
