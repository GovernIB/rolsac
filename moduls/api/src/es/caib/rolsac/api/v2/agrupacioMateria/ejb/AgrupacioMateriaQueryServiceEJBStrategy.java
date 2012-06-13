package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceEJBStrategy implements AgrupacioMateriaQueryServiceStrategy {

    private AgrupacioMateriaQueryServiceDelegate agrupacioMateriaQueryServiceDelegate;

    public void setAgrupacioMateriaQueryServiceDelegate(AgrupacioMateriaQueryServiceDelegate agrupacioMateriaQueryServiceDelegate) {
        this.agrupacioMateriaQueryServiceDelegate = agrupacioMateriaQueryServiceDelegate;
    }

    public SeccioDTO obtenirSeccio(long idSeccio) {
        return agrupacioMateriaQueryServiceDelegate.obtenirSeccio(idSeccio);
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        return agrupacioMateriaQueryServiceDelegate.llistarMateries(id, materiaCriteria);
    }

    public int getNumMateries(long id) {
        return agrupacioMateriaQueryServiceDelegate.getNumMateries(id);
    }

}
