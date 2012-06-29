package es.caib.rolsac.api.v2.familia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.ejb.FamiliaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class FamiliaQueryServiceAdapter extends FamiliaDTO implements FamiliaQueryService {

    private static final long serialVersionUID = 8116659115966486561L;

    private FamiliaQueryServiceStrategy familiaQueryServiceStrategy;

    public void setFamiliaQueryServiceStrategy(FamiliaQueryServiceStrategy familiaQueryServiceStrategy) {
        this.familiaQueryServiceStrategy = familiaQueryServiceStrategy;
    }

    public FamiliaQueryServiceAdapter(FamiliaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public STRATEGY getStrategy() {
        return familiaQueryServiceStrategy instanceof FamiliaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumProcedimentsLocals() throws QueryServiceException {
        try {
            return familiaQueryServiceStrategy.getNumProcedimentsLocals(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public int getNumIcones() throws QueryServiceException {
        try {
            return familiaQueryServiceStrategy.getNumIcones(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de iconos.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = familiaQueryServiceStrategy.llistarProcedimentsLocals(id, procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> procs = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO pDTO : llistaDTO) {
                procs.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), pDTO));
            }
            return procs;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIcones(IconaFamiliaCriteria iconaFamiliaCriteria) throws QueryServiceException {
        try {
            List<IconaFamiliaDTO> llistaDTO = familiaQueryServiceStrategy.llistarIcones(id, iconaFamiliaCriteria);
            List<IconaFamiliaQueryServiceAdapter> icones = new ArrayList<IconaFamiliaQueryServiceAdapter>();
            for (IconaFamiliaDTO pDTO : llistaDTO) {
                icones.add((IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), pDTO));
            }
            return icones;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iconos.", e);
        }
    }

}
