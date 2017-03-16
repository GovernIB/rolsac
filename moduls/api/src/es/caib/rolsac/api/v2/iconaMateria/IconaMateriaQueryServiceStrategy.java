package es.caib.rolsac.api.v2.iconaMateria;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public interface IconaMateriaQueryServiceStrategy {

    public MateriaDTO obtenirMateria(long id) throws StrategyException;

    public PerfilDTO obtenirPerfil(long id) throws StrategyException;

    public ArxiuDTO obtenirIcona(long id) throws StrategyException;

	public void setUrl(String url);

}
