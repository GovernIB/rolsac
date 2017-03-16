package es.caib.rolsac.api.v2.iconaMateria;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaMateria.ejb.IconaMateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;

public class IconaMateriaQueryServiceAdapter extends IconaMateriaDTO implements IconaMateriaQueryService {

    private static final long serialVersionUID = -2056286844910133057L;

    private IconaMateriaQueryServiceStrategy iconaMateriaQueryServiceStrategy;
    
    public void setIconaMateriaQueryServiceStrategy(IconaMateriaQueryServiceStrategy iconaMateriaQueryServiceStrategy) {
        this.iconaMateriaQueryServiceStrategy = iconaMateriaQueryServiceStrategy;
    }
    
    private String url;
    public void setUrl(String url) {
    	this.url = url;
		if (this.iconaMateriaQueryServiceStrategy != null) {
			this.iconaMateriaQueryServiceStrategy.setUrl(url);
		}
	}

    public IconaMateriaQueryServiceAdapter(IconaMateriaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public STRATEGY getStrategy() {
        return iconaMateriaQueryServiceStrategy instanceof IconaMateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public MateriaQueryServiceAdapter obtenirMateria() throws QueryServiceException {
        if (this.getMateria() == null) {return null;}
        try {
        	MateriaQueryServiceAdapter mqsa =  (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirMateria(this.getMateria()));
        	if (mqsa != null && url != null) {
        		mqsa.setUrl(url);
        	}
        	return mqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "materia.", e);
        }
    }

    public PerfilQueryServiceAdapter obtenirPerfil() throws QueryServiceException {
        if (this.getPerfil() == null) {return null;}
        try {
        	PerfilQueryServiceAdapter pqsa = (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirPerfil(this.getPerfil()));
        	if (pqsa != null && url != null) {
        		pqsa.setUrl(url);
        	}
        	return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "perfil.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter pqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), iconaMateriaQueryServiceStrategy.obtenirIcona(this.getIcono()));
         	if (pqsa != null && url != null) {
        		pqsa.setUrl(url);
        	}
        	return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

	
    
}
