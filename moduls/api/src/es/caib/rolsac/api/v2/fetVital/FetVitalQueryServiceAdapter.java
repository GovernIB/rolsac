package es.caib.rolsac.api.v2.fetVital;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.ejb.FetVitalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class FetVitalQueryServiceAdapter extends FetVitalDTO implements FetVitalQueryService {

    private static final long serialVersionUID = 4199084795372562785L;

    private FetVitalQueryServiceStrategy fetVitalQueryServiceStrategy;

    public void setFetVitalQueryServiceStrategy(FetVitalQueryServiceStrategy fetVitalQueryServiceStrategy) {
        this.fetVitalQueryServiceStrategy = fetVitalQueryServiceStrategy;
    }
    
    private String url;
    public void setRolsacUrl(String url) {
    	this.url = url;
		if (this.fetVitalQueryServiceStrategy != null) {
			this.fetVitalQueryServiceStrategy.setUrl(url);
		}
	}

    public FetVitalQueryServiceAdapter(FetVitalDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return fetVitalQueryServiceStrategy instanceof FetVitalQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumFitxes() throws QueryServiceException {
        try {
            return fetVitalQueryServiceStrategy.getNumFitxes(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    public int getNumProcedimentsLocals() throws QueryServiceException {
        try {
            return fetVitalQueryServiceStrategy.getNumProcedimentsLocals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public int getNumFetsVitalsAgrupacionsFV() throws QueryServiceException {
        try {
            return fetVitalQueryServiceStrategy.getNumFetsVitalsAgrupacionsFV(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de agrupaciones.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarFitxes(getId(), fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
            	FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO);
            	if (url != null && fqsa != null) {
            		fqsa.setRolsacUrl(url);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarProcedimentsLocals(getId(), procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
            	ProcedimentQueryServiceAdapter pqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO);
            	if (pqsa != null && url != null) {
            		pqsa.setRolsacUrl(url);
            	}
            	llistaQueryServiceAdapter.add(pqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarFetsVitalsAgrupacionsFV(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException {
        try {
            List<AgrupacioFetVitalDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarFetsVitalsAgrupacionsFV(getId(), agrupacioFetVitalCriteria);
            List<AgrupacioFetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
            for (AgrupacioFetVitalDTO agrupacioFetVitalDTO : llistaDTO) {
            	AgrupacioFetVitalQueryServiceAdapter pqsa = (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), agrupacioFetVitalDTO);
            	if (pqsa != null && url != null) {
            		pqsa.setRolsacUrl(url);
            	}
            	llistaQueryServiceAdapter.add(pqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public ArxiuQueryServiceAdapter getDistribuciCompetencial() throws QueryServiceException {        
        if (this.getFoto() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getDistribuciCompetencial(this.getFoto());
            ArxiuQueryServiceAdapter pqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (pqsa != null && url != null) {
        		pqsa.setRolsacUrl(url);
        	}
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "distribucioCompetencial.", e);
        }
    }

    public ArxiuQueryServiceAdapter getNormativa() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getNormativa(this.getIcono());
            ArxiuQueryServiceAdapter pqsa=  (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (pqsa != null && url != null) {
        		pqsa.setRolsacUrl(url);
        	}
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

    public ArxiuQueryServiceAdapter getContingut() throws QueryServiceException {
        if (this.getIconoGrande() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getContingut(this.getIconoGrande());
            ArxiuQueryServiceAdapter pqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (pqsa != null && url != null) {
        		pqsa.setRolsacUrl(url);
        	}
            return pqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

	

}