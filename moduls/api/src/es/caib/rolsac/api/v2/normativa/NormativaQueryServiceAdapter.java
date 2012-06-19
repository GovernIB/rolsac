package es.caib.rolsac.api.v2.normativa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class NormativaQueryServiceAdapter extends NormativaDTO implements NormativaQueryService {

    private NormativaQueryServiceStrategy normativaQueryServiceStrategy;

    public void setNormativaQueryServiceStrategy(NormativaQueryServiceStrategy normativaQueryServiceStrategy) {
        this.normativaQueryServiceStrategy = normativaQueryServiceStrategy;
    }

    public NormativaQueryServiceAdapter(NormativaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return normativaQueryServiceStrategy instanceof NormativaQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ArxiuQueryServiceAdapter obtenirArxiuNormativa(){
        if (this.getArchivo() == null) {return null;}
        ArxiuDTO dto = normativaQueryServiceStrategy.obtenirArxiuNormativa(this.getArchivo());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }
    
    public int getNumAfectades() {
        return normativaQueryServiceStrategy.getNumAfectades(id);
    }

    public int getNumAfectants() {
        return normativaQueryServiceStrategy.getNumAfectants(id);
    }

    public int getNumProcediments() {
        return normativaQueryServiceStrategy.getNumProcediments(id);
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectades(){
        List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectades(id);
        List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
        for (AfectacioDTO afectacioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectades() {
        List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectades(id);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectants(){
        List<AfectacioDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectacionsAfectants(id);
        List<AfectacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AfectacioQueryServiceAdapter>();
        for (AfectacioDTO afectacioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AfectacioQueryServiceAdapter) BeanUtils.getAdapter("afectacio", getStrategy(), afectacioDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<NormativaQueryServiceAdapter> llistarAfectants() {
        List<NormativaDTO> llistaDTO = normativaQueryServiceStrategy.llistarAfectants(id);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = normativaQueryServiceStrategy.llistarProcediments(id, procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO procedimentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public ButlletiQueryServiceAdapter obtenirButlleti() {
        return (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), normativaQueryServiceStrategy.obtenirButlleti(this.getBoletin()));
    }
    
    
    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() {
        if (this.getUnidadAdministrativa() == null) {return null;}
        UnitatAdministrativaDTO dto = (UnitatAdministrativaDTO) normativaQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa());
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
    }

}
