package es.caib.rolsac.api.v2.iconaMateria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public class IconaMateriaQueryServiceAdapter extends IconaMateriaDTO implements IconaMateriaQueryService {

    private IconaMateriaQueryServiceStrategy iconaMateriaQueryServiceStrategy;
    
    public void setIconaMateriaQueryServiceStrategy(IconaMateriaQueryServiceStrategy iconaMateriaQueryServiceStrategy) {
        this.iconaMateriaQueryServiceStrategy = iconaMateriaQueryServiceStrategy;
    }

    public IconaMateriaQueryServiceAdapter(IconaMateriaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public STRATEGY getStrategy() {
        return iconaMateriaQueryServiceStrategy instanceof IconaMateriaQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() {
        if (this.getMateria() == null) {return null;}
        return (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirMateria(this.getMateria()));
    }

    public PerfilQueryServiceAdapter obtenirPerfil() {
        if (this.getPerfil() == null) {return null;}
        return (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirPerfil(this.getPerfil()));
    }

    public ArxiuQueryServiceAdapter obtenirIcona() {
        if (this.getIcono() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirIcona(this.getIcono()));
    }
    
}
