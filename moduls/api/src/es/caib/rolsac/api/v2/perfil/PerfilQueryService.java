package es.caib.rolsac.api.v2.perfil;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;

public interface PerfilQueryService {

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException;

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException;

    public int getNumIconesFamilia() throws QueryServiceException;

    public int getNumIconesMateria() throws QueryServiceException;

}
