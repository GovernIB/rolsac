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
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getFotografia(this.getFoto()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "fotografia.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIcona(this.getIcono()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

    public ArxiuQueryServiceAdapter getIconaGran() throws QueryServiceException {
        if (this.getIconoGrande() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIconaGran(this.getIconoGrande()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono grande.", e);
        }
    }
    
    public int getNumFitxes() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumFitxes(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    public int getNumAgrupacioMateries() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumAgrupacioMateries(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de agrupaciones materia.", e);
        }
    }

    public int getNumProcedimentsLocals() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumProcedimentsLocals(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public int getNumUnitatsMateries() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumUnitatsMateries(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades materia.", e);
        }
    }

    public int getNumIcones() throws QueryServiceException {
        try {
            return materiaQueryServiceStrategy.getNumIcones(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de iconos.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = materiaQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacioMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException {
        try {
            List<AgrupacioMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
            List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
            for (AgrupacioMateriaDTO agrupacioMateriaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), agrupacioMateriaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = materiaQueryServiceStrategy.llistarProcedimentsLocals(id, procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws QueryServiceException {
        try {
            List<UnitatMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsMateria(id, unitatMateriaCriteria);
            List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
            for (UnitatMateriaDTO unitatMateriaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), unitatMateriaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }
    
    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws QueryServiceException {
        try {
            List<IconaMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarIconesMateries(id, iconaMateriaCriteria);
            List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
            for (IconaMateriaDTO iconaMateriaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), iconaMateriaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "agrupaciones materia.", e);
        }
    }

}
