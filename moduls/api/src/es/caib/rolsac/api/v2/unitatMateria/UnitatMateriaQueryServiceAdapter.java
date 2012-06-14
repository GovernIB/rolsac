package es.caib.rolsac.api.v2.unitatMateria;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.ejb.UnitatMateriaQueryServiceEJBStrategy;

public class UnitatMateriaQueryServiceAdapter extends UnitatMateriaDTO implements UnitatMateriaQueryService {

    private static Log log = LogFactory.getLog(UnitatMateriaQueryServiceAdapter.class);
    
    UnitatMateriaQueryServiceStrategy unitatMateriaQueryServiceStrategy;
    
    public void setUnitatMateriaQueryServiceStrategy(UnitatMateriaQueryServiceStrategy unitatMateriaQueryServiceStrategy) {
        this.unitatMateriaQueryServiceStrategy = unitatMateriaQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return unitatMateriaQueryServiceStrategy instanceof UnitatMateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public UnitatMateriaQueryServiceAdapter(UnitatMateriaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando UnitatMateriaQueryServiceAdapter.", e);
        }
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() {
        if (this.getMateria() == null) {return null;}
        return (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), unitatMateriaQueryServiceStrategy.obtenirMateria(this.getMateria()));
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() {
        if (this.getUnidad() == null) {return null;}
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatMateriaQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidad()));
    }

}
