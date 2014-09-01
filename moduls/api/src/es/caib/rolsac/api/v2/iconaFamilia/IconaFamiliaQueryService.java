package es.caib.rolsac.api.v2.iconaFamilia;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public interface IconaFamiliaQueryService {

    public FamiliaQueryServiceAdapter obtenirFamilia() throws QueryServiceException;
    public PerfilQueryServiceAdapter obtenirPerfil() throws QueryServiceException;
    public ArxiuQueryServiceAdapter obtenirIcona() throws QueryServiceException;

}
