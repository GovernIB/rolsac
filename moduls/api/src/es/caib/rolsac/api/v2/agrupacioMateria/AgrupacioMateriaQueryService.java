package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.List;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;

public interface AgrupacioMateriaQueryService {

    SeccioQueryServiceAdapter obtenirSeccio();

    List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria);

    int getNumMateries();
}
