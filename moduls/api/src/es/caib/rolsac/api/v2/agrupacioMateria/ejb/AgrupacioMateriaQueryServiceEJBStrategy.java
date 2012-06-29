package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceEJBStrategy implements AgrupacioMateriaQueryServiceStrategy {

    private AgrupacioMateriaQueryServiceDelegate agrupacioMateriaQueryServiceDelegate;

    public void setAgrupacioMateriaQueryServiceDelegate(AgrupacioMateriaQueryServiceDelegate agrupacioMateriaQueryServiceDelegate) {
        this.agrupacioMateriaQueryServiceDelegate = agrupacioMateriaQueryServiceDelegate;
    }

    public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException {
        try {
            return agrupacioMateriaQueryServiceDelegate.obtenirSeccio(idSeccio);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return agrupacioMateriaQueryServiceDelegate.llistarMateries(id, materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumMateries(long id) throws StrategyException {
        try {
            return agrupacioMateriaQueryServiceDelegate.getNumMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
