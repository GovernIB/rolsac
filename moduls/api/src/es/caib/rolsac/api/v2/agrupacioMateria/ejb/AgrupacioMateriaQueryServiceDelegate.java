package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceDelegate {

    private AgrupacioMateriaQueryServiceEJBLocator agrupacioMateriaQueryServiceLocator;
    
    public void setAgrupacioMateriaQueryServiceLocator(AgrupacioMateriaQueryServiceEJBLocator agrupacioMateriaQueryServiceLocator) {
        this.agrupacioMateriaQueryServiceLocator = agrupacioMateriaQueryServiceLocator;
    }

    public SeccioDTO obtenirSeccio(long idSeccio) {
        AgrupacioMateriaQueryServiceEJB ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
        return ejb.obtenirSeccio(idSeccio);
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        AgrupacioMateriaQueryServiceEJB ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
        return ejb.llistarMateries(id, materiaCriteria);
    }

    public int getNumMateries(long id) {
        AgrupacioMateriaQueryServiceEJB ejb = agrupacioMateriaQueryServiceLocator.getAgrupacioMateriaQueryServiceEJB();
        return ejb.getNumMateries(id);
    }

}
