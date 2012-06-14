package es.caib.rolsac.api.v2.seccio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.ejb.SeccioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class SeccioQueryServiceAdapter extends SeccioDTO implements SeccioQueryService {

    private static Log log = LogFactory.getLog(SeccioQueryServiceAdapter.class);
    
    // @Injected
    private SeccioQueryServiceStrategy seccioQueryServiceStrategy;
    
    public void setSeccioQueryServiceStrategy(SeccioQueryServiceStrategy seccioQueryServiceStrategy) {
        this.seccioQueryServiceStrategy = seccioQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return seccioQueryServiceStrategy instanceof SeccioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public SeccioQueryServiceAdapter(SeccioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando SeccioQueryServiceAdapter.", e);
        }
    }
    
    public int getNumFilles() {
        return seccioQueryServiceStrategy.getNumFilles(id);
    }

    public int getNumFitxes() {
        return seccioQueryServiceStrategy.getNumFitxes(id);
    }

    /**
     * Llama a llistarPares y devuelve el tamaño de la lista. No es mas eficiente que ejecutar un .size()
     * a nivel de cliente.
     */
    public int getNumPares() {
        return seccioQueryServiceStrategy.getNumPares(id);
    }

    public int getNumUnitatsAdministratives() {
        return seccioQueryServiceStrategy.getNumUnitatsAdministratives(id);
    }
    
    public List<SeccioQueryServiceAdapter> llistarFilles(SeccioCriteria seccioCriteria) {
        List<SeccioDTO> llistaDTO = seccioQueryServiceStrategy.llistarFilles(id, seccioCriteria);
        List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
        for (SeccioDTO seccioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> llistaDTO = seccioQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
        List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
        for (FitxaDTO fitxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    /**
     * Ejecuta recursivamente obtenirPare() hasta llegar a la seccion raiz, construyendo la lista a devolver por el camino.
     */
    public List<SeccioQueryServiceAdapter> llistarPares() {
        List<SeccioDTO> llistaDTO = seccioQueryServiceStrategy.llistarPares(id);
        List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
        for (SeccioDTO seccioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = seccioQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public SeccioQueryServiceAdapter obtenirPare() {
        if (this.getPadre() == null) {return null;}
        return (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioQueryServiceStrategy.obtenirPare(this.getPadre()));
    }
    
}
