package es.caib.rolsac.api.v2.perfil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.ejb.PerfilQueryServiceEJBStrategy;

public class PerfilQueryServiceAdapter extends PerfilDTO implements PerfilQueryService {

    private static final long serialVersionUID = -34492244035570364L;

    private PerfilQueryServiceStrategy perfilQueryServiceStrategy;

    public void setPerfilQueryServiceStrategy(PerfilQueryServiceStrategy perfilQueryServiceStrategy) {
        this.perfilQueryServiceStrategy = perfilQueryServiceStrategy;
    }
    
    private String url;
    public void setUrl(String url) {
    	this.url = url;
		if ( this.perfilQueryServiceStrategy != null) {
			 this.perfilQueryServiceStrategy.setUrl(url);
		}
	}

    public PerfilQueryServiceAdapter(PerfilDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    public STRATEGY getStrategy() {
        return perfilQueryServiceStrategy instanceof PerfilQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException {
        try {
            List<IconaFamiliaDTO> llistaDTO = perfilQueryServiceStrategy.llistarIconesFamilia(getId(), iconaFamiliaCriteria);
            List<IconaFamiliaQueryServiceAdapter> icones = new ArrayList<IconaFamiliaQueryServiceAdapter>();
            for (IconaFamiliaDTO iDTO : llistaDTO) {
                IconaFamiliaQueryServiceAdapter iqsa = (IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), iDTO);
                if (iqsa != null && url != null) {
                	iqsa.setUrl(url);
                }
            	icones.add(iqsa);
            }
            return icones;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos familia.", e);
        }
    }

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateria(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException {
        try {
            List<IconaMateriaDTO> llistaDTO = perfilQueryServiceStrategy.llistarIconesMateria(getId(), iconaMateriaCriteria);
            List<IconaMateriaQueryServiceAdapter> icones = new ArrayList<IconaMateriaQueryServiceAdapter>();
            for (IconaMateriaDTO iDTO : llistaDTO) {
            	IconaMateriaQueryServiceAdapter iqsa = (IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), iDTO);
            	if (iqsa != null && url != null) {
            		iqsa.setUrl(url);
            	}	
            	icones.add(iqsa);
            }
            return icones;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos materia.", e);
        }
    }

    public int getNumIconesFamilia() throws QueryServiceException {
        try {
            return perfilQueryServiceStrategy.getNumIconesFamilia(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de iconos familia.", e);
        }
    }

    public int getNumIconesMateria() throws QueryServiceException {
        try {
            return perfilQueryServiceStrategy.getNumIconesMateria(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de iconos materias.", e);
        }
    }

	
}
