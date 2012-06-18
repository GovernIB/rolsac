package es.caib.rolsac.api.v2.fetVital;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;

public class FetVitalQueryServiceAdapter extends FetVitalDTO implements FetVitalQueryService {

    private FetVitalQueryServiceStrategy fetVitalQueryServiceStrategy;

    public void setFetVitalQueryServiceStrategy(FetVitalQueryServiceStrategy fetVitalQueryServiceStrategy) {
        this.fetVitalQueryServiceStrategy = fetVitalQueryServiceStrategy;
    }

    public FetVitalQueryServiceAdapter(FetVitalDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return fetVitalQueryServiceStrategy instanceof FetVitalQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumFitxes() {
        return fetVitalQueryServiceStrategy.getNumFitxes(id);
    }

    public int getNumProcedimentsLocals() {
        return fetVitalQueryServiceStrategy.getNumProcedimentsLocals(id);
    }

    public int getNumFetsVitalsAgrupacionsFV() {
        return fetVitalQueryServiceStrategy.getNumFetsVitalsAgrupacionsFV(id);
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
        List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
        for (FitxaDTO fitxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcedimentsLocals(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarProcedimentsLocals(id, procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO procedimentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarFetsVitalsAgrupacionsFV(
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> llistaDTO = fetVitalQueryServiceStrategy.llistarFetsVitalsAgrupacionsFV(id, agrupacioFetVitalCriteria);
        List<AgrupacioFetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
        for (AgrupacioFetVitalDTO agrupacioFetVitalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), agrupacioFetVitalDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public ArxiuQueryServiceAdapter getFotografia() {        
        if (this.getFoto() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getFotografia(this.getFoto());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

    public ArxiuQueryServiceAdapter getIcona() {
        if (this.getIcona() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getIcona(this.getIcono());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

    public ArxiuQueryServiceAdapter getIconaGran() {
        if (this.getIconaGran() == null) {return null;}
        ArxiuDTO dto = (ArxiuDTO) fetVitalQueryServiceStrategy.getIconaGran(this.getIconoGrande());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }

}
