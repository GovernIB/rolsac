package es.caib.rolsac.api.v2.iconaFamilia;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.ejb.IconaFamiliaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public class IconaFamiliaQueryServiceAdapter extends IconaFamiliaDTO implements IconaFamiliaQueryService {

    private static final long serialVersionUID = -7019212073058957306L;

    private IconaFamiliaQueryServiceStrategy iconaFamiliaQueryServiceStrategy;
    
    public void setIconaFamiliaQueryServiceStrategy(IconaFamiliaQueryServiceStrategy iconaFamiliaQueryServiceStrategy) {
        this.iconaFamiliaQueryServiceStrategy = iconaFamiliaQueryServiceStrategy;
    }

    public IconaFamiliaQueryServiceAdapter(IconaFamiliaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public STRATEGY getStrategy() {
        return iconaFamiliaQueryServiceStrategy instanceof IconaFamiliaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FamiliaQueryServiceAdapter obtenirFamilia() throws QueryServiceException {
        if (this.getFamilia() == null) {return null;}
        try {
            return (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirFamilia(this.getFamilia()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "familia.", e);
        }
    }

    public PerfilQueryServiceAdapter obtenirPerfil() throws QueryServiceException {
        if (this.getPerfil() == null) {return null;}
        try {
            return (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirPerfil(this.getPerfil()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "perfil.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), iconaFamiliaQueryServiceStrategy.obtenirIcona(this.getIcono()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }
    
}
