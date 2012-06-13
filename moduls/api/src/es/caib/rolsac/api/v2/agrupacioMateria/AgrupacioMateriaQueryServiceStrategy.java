package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.List;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public interface AgrupacioMateriaQueryServiceStrategy {

    SeccioDTO obtenirSeccio(long idSeccio);

    List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria);

    int getNumMateries(long id);

}
