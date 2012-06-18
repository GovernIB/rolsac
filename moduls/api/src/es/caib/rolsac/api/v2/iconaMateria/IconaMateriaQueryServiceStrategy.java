package es.caib.rolsac.api.v2.iconaMateria;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public interface IconaMateriaQueryServiceStrategy {

    public MateriaDTO obtenirMateria(long id);

    public PerfilDTO obtenirPerfil(long id);

    public ArxiuDTO obtenirIcona(long id);

}
