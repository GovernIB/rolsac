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
                llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
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
                llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
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
                llistaQueryServiceAdapter.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), agrupacioFetVitalDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public ArxiuQueryServiceAdapter getFotografia() throws QueryServiceException {        
        if (this.getFoto() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getFotografia(this.getFoto());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "fotografia.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getIcona(this.getIcono());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException {
        if (this.getIconoGrande() == null) {return null;}
        try {
            ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getIconaGran(this.getIconoGrande());
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

}