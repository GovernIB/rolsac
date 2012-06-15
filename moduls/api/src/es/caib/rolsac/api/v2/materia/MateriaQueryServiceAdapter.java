package es.caib.rolsac.api.v2.materia;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
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

    private static Log log = LogFactory.getLog(MateriaQueryServiceAdapter.class);
    
    private MateriaQueryServiceStrategy materiaQueryServiceStrategy;

    public void setMateriaQueryServiceStrategy(MateriaQueryServiceStrategy materiaQueryServiceStrategy) {
        this.materiaQueryServiceStrategy = materiaQueryServiceStrategy;
    }

    public MateriaQueryServiceAdapter(MateriaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando MateriaQueryServiceAdapter.", e);
        }
    }

    private STRATEGY getStrategy() {
        return materiaQueryServiceStrategy instanceof MateriaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter getFotografia() {
        if (this.getFoto() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getFotografia(this.getFoto()));
    }

    public ArxiuQueryServiceAdapter getIcona() {
        if (this.getIcono() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIcona(this.getIcono()));
    }

    public ArxiuQueryServiceAdapter getIconaGran() {
        if (this.getIconoGrande() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), materiaQueryServiceStrategy.getIconaGran(this.getIconoGrande()));
    }
    
    public int getNumFitxes() {
       return materiaQueryServiceStrategy.getNumFitxes(id);
    }

    public int getNumAgrupacioMateries() {
        return materiaQueryServiceStrategy.getNumAgrupacioMateries(id);
    }

    public int getNumProcedimentsLocals() {
        return materiaQueryServiceStrategy.getNumProcedimentsLocals(id);
    }

    public int getNumUnitatsMateries() {
        return materiaQueryServiceStrategy.getNumUnitatsMateries(id);
    }

    public int getNumIcones() {
       return materiaQueryServiceStrategy.getNumIcones(id);
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> llistaDTO = materiaQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
        List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
        for (FitxaDTO fitxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacioMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        List<AgrupacioMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarAgrupacioMateries(id, agrupacioMateriaCriteria);
        List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
        for (AgrupacioMateriaDTO agrupacioMateriaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), agrupacioMateriaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = materiaQueryServiceStrategy.llistarProcedimentsLocals(id, procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO procedimentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateria(UnitatMateriaCriteria unitatMateriaCriteria) {
        List<UnitatMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsMateria(id, unitatMateriaCriteria);
        List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
        for (UnitatMateriaDTO unitatMateriaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), unitatMateriaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = materiaQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> llistaDTO = materiaQueryServiceStrategy.llistarIconesMateries(id, iconaMateriaCriteria);
        List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
        for (IconaMateriaDTO iconaMateriaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), iconaMateriaDTO));
        }
        return llistaQueryServiceAdapter;
    }

}
