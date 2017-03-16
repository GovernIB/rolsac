package es.caib.rolsac.api.v2.materia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.ejb.MateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;

public class MateriaQueryServiceAdapter extends MateriaDTO implements MateriaQueryService {

    private static final long serialVersionUID = 7660704883577571663L;

    private MateriaQueryServiceStrategy materiaQueryServiceStrategy;

    public void setMateriaQueryServiceStrategy(MateriaQueryServiceStrategy materiaQueryServiceStrategy) {
        this.materiaQueryServiceStrategy = materiaQueryServiceStrategy;
    }
    
    private String url;
    public void setUrl(String url) {
    	this.url = url;
    	if (this.materiaQueryServiceStrategy != null) {
			this.materiaQueryServiceStrategy.setUrl(url);
		}
	}

    public MateriaQueryServiceAdapter(MateriaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return materiaQueryServiceStrategy instanceof MateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException {
        if (this.getFoto() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa =  (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getFotografia(this.getFoto()));
        	if (aqsa != null && url != null) {
        		aqsa.setUrl(url);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "fotografia.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIcona(this.getIcono()));
            if (aqsa != null && url != null) {
        		aqsa.setUrl(url);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException {
        if (this.getIconoGrande() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa =  (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIconaGran(this.getIconoGrande()));
            if (aqsa != null && url != null) {
        		aqsa.setUrl(url);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono grande.", e);
        }
    }
    
    public int getNumFitxes() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumFitxes(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    public int getNumAgrupacioMateries() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumAgrupacioMateries(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de agrupaciones materia.", e);
        }
    }

    public int getNumProcedimentsLocals() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumProcedimentsLocals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public int getNumUnitatsMateries() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumUnitatsMateries(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades materia.", e);
        }
    }

    public int getNumIcones() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumIcones(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de iconos.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = materiaQueryServiceStrategy.llistarFitxes(getId(),fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
            	FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO);
            	if (fqsa != null && url != null) {
            		fqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacioMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
        try {
            List<AgrupacioMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarAgrupacioMateries(getId(),agrupacioMateriaCriteria);
            List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
            for (AgrupacioMateriaDTO agrupacioMateriaDTO : llistaDTO) {
            	AgrupacioMateriaQueryServiceAdapter fqsa = (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), agrupacioMateriaDTO);
                if (fqsa != null && url != null) {
            		fqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = materiaQueryServiceStrategy.llistarProcedimentsLocals(getId(),procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
            	ProcedimentQueryServiceAdapter fqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO);
                if (fqsa != null && url != null) {
            		fqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException {
        try {
            List<UnitatMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsMateria(getId(),unitatMateriaCriteria);
            List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
            for (UnitatMateriaDTO unitatMateriaDTO : llistaDTO) {
            	UnitatMateriaQueryServiceAdapter uqsa = (UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), unitatMateriaDTO);
            	if (uqsa != null && url != null) {
            		uqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(uqsa );
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsAdministratives(getId(),unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            	UnitatAdministrativaQueryServiceAdapter uqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO);
            	if 	(uqsa != null && url != null) {
            		uqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(uqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }
    
    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException {
        try {
            List<IconaMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarIconesMateries(getId(),iconaMateriaCriteria);
            List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
            for (IconaMateriaDTO iconaMateriaDTO : llistaDTO) {
            	IconaMateriaQueryServiceAdapter uqsa = (IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), iconaMateriaDTO);
            	if 	(uqsa != null && url != null) {
            		uqsa.setUrl(url);
            	}
            	llistaQueryServiceAdapter.add(uqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

	

}
