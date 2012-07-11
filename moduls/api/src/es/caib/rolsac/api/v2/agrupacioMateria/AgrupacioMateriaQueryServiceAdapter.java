package es.caib.rolsac.api.v2.agrupacioMateria;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioMateria.ejb.AgrupacioMateriaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;

public class AgrupacioMateriaQueryServiceAdapter extends AgrupacioMateriaDTO implements AgrupacioMateriaQueryService {

    private static final long serialVersionUID = 3791427026414519459L;

    private AgrupacioMateriaQueryServiceStrategy agrupacioMateriaQueryServiceStrategy;

    public void setAgrupacioMateriaQueryServiceStrategy(AgrupacioMateriaQueryServiceStrategy agrupacioMateriaQueryServiceStrategy) {
        this.agrupacioMateriaQueryServiceStrategy = agrupacioMateriaQueryServiceStrategy;
    }

    public AgrupacioMateriaQueryServiceAdapter(AgrupacioMateriaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return agrupacioMateriaQueryServiceStrategy instanceof AgrupacioMateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public SeccioQueryServiceAdapter obtenirSeccio() throws QueryServiceException {
        if (this.getSeccion() == null) {return null;}
        try {
            SeccioDTO dto = agrupacioMateriaQueryServiceStrategy.obtenirSeccio(this.getSeccion());
            return (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "seccion.", e);
        }
        
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = agrupacioMateriaQueryServiceStrategy.llistarMateries(id, materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public int getNumMateries() throws QueryServiceException {        
        try {
            return agrupacioMateriaQueryServiceStrategy.getNumMateries(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de materias.", e);
        }
    }

}