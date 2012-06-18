package es.caib.rolsac.api.v2.iconaMateria;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public interface IconaMateriaQueryService {

    public MateriaQueryServiceAdapter obtenirMateria();

    public PerfilQueryServiceAdapter obtenirPerfil();
    
    public ArxiuQueryServiceAdapter obtenirIcona();

}
