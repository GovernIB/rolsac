package es.caib.rolsac.api.v2.agrupacioMateria.ws;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceWebServiceStrategy implements AgrupacioMateriaQueryServiceStrategy {

    AgrupacioMateriaQueryServiceGateway gateway;

    public SeccioDTO obtenirSeccio(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacio(long id, MateriaAgrupacioCriteria materiaAgrupacioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public SeccioDTO obtenirSeccio(long idSeccio) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumMateries(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

}
