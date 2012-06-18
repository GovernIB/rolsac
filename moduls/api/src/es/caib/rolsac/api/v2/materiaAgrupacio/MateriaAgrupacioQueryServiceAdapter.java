package es.caib.rolsac.api.v2.materiaAgrupacio;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.ejb.MateriaAgrupacioQueryServiceEJBStrategy;

public class MateriaAgrupacioQueryServiceAdapter extends MateriaAgrupacioDTO implements MateriaAgrupacioQueryService {

    private static Log log = LogFactory.getLog(MateriaAgrupacioQueryServiceAdapter.class);
    
    private MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy;
    
    public void setMateriaAgrupacioQueryServiceStrategy(MateriaAgrupacioQueryServiceStrategy materiaAgrupacioQueryServiceStrategy) {
        this.materiaAgrupacioQueryServiceStrategy = materiaAgrupacioQueryServiceStrategy;
    }

    public MateriaAgrupacioQueryServiceAdapter(MateriaAgrupacioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando MateriaAgrupacioQueryServiceAdapter.", e);
        }
    }

    private STRATEGY getStrategy() {
        return materiaAgrupacioQueryServiceStrategy instanceof MateriaAgrupacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() {
        if (this.getMateria() == null) {return null;}
        return (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaAgrupacioQueryServiceStrategy.obtenirMateria(this.getMateria()));
    }

    public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacio() {
        if (this.getAgrupacion() == null) {return null;}
        return (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), materiaAgrupacioQueryServiceStrategy.obtenirAgrupacioMateria(this.getAgrupacion()));
    }

}
