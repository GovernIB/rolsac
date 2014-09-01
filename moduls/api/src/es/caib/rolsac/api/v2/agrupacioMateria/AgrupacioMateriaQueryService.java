package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;

public interface AgrupacioMateriaQueryService {

    public SeccioQueryServiceAdapter obtenirSeccio() throws QueryServiceException;

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;

    public int getNumMateries() throws QueryServiceException;
}
