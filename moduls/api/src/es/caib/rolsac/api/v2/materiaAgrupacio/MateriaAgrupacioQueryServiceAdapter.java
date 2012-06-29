package es.caib.rolsac.api.v2.materiaAgrupacio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.ejb.MateriaAgrupacioQueryServiceEJBStrategy;

public class MateriaAgrupacioQueryServiceAdapter extends MateriaAgrupacioDTO implements MateriaAgrupacioQueryService {

    private static final long serialVersionUID = 7026670225281721790L;

    private MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy;
    
    public void setMateriaAgrupacioQueryServiceStrategy(MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy) {
        this.materiaAgrupacioQueryServiceStrategy = materiaAgrupacioQueryServiceStrategy;
    }

    public MateriaAgrupacioQueryServiceAdapter(MateriaAgrupacioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return materiaAgrupacioQueryServiceStrategy instanceof MateriaAgrupacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() throws QueryServiceException {
        if (this.getMateria() == null) {return null;}
        try {
            return (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaAgrupacioQueryServiceStrategy.obtenirMateria(this.getMateria()));
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }

    public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacio() throws QueryServiceException {
        if (this.getAgrupacion() == null) {return null;}
        try {
            return (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), materiaAgrupacioQueryServiceStrategy.obtenirAgrupacioMateria(this.getAgrupacion()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "agrupacion.", e);
        }
    }

}
