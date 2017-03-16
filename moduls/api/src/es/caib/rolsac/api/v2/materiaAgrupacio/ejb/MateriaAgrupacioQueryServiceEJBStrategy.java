package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceStrategy;

public class MateriaAgrupacioQueryServiceEJBStrategy implements MateriaAgrupacioQueryServiceStrategy {

    private MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate;

    public void setMateriaAgrupacioQueryServiceDelegate(MateriaAgrupacioQueryServiceDelegate materiaAgrupacioQueryServiceDelegate) {
        this.materiaAgrupacioQueryServiceDelegate = materiaAgrupacioQueryServiceDelegate;
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException {
        try {
            return materiaAgrupacioQueryServiceDelegate.obtenirMateria(idMateria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) throws StrategyException {
        try {
            return materiaAgrupacioQueryServiceDelegate.obtenirAgrupacioMateria(idAgrupacion);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}
}
