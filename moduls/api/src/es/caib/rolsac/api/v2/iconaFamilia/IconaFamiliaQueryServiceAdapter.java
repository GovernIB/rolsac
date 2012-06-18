package es.caib.rolsac.api.v2.iconaFamilia;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public class IconaFamiliaQueryServiceAdapter extends IconaFamiliaDTO implements IconaFamiliaQueryService {

    private IconaFamiliaQueryServiceStrategy iconaFamiliaQueryServiceStrategy;
    
    public void setIconaFamiliaQueryServiceStrategy(IconaFamiliaQueryServiceStrategy iconaFamiliaQueryServiceStrategy) {
        this.iconaFamiliaQueryServiceStrategy = iconaFamiliaQueryServiceStrategy;
    }

    public IconaFamiliaQueryServiceAdapter(IconaFamiliaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public STRATEGY getStrategy() {
        return iconaFamiliaQueryServiceStrategy instanceof IconaFamiliaQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FamiliaQueryServiceAdapter obtenirFamilia() {
        if (this.getFamilia() == null) {return null;}
        return (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirFamilia(this.getFamilia()));
    }

    public PerfilQueryServiceAdapter obtenirPerfil() {
        if (this.getPerfil() == null) {return null;}
        return (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirPerfil(this.getPerfil()));
    }

    public ArxiuQueryServiceAdapter obtenirIcona() {
        if (this.getIcono() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirIcona(this.getIcono()));
    }
    
}
