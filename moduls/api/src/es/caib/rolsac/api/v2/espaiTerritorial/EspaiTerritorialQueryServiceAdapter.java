package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EspaiTerritorialQueryServiceAdapter extends EspaiTerritorialDTO implements EspaiTerritorialQueryService {

    private static Log log = LogFactory.getLog(EspaiTerritorialQueryServiceAdapter.class);
    
    // @Injected
    EspaiTerritorialQueryServiceStrategy espaiTerritorialQueryServiceStrategy;
    
    public void setEspaiTerritorialQueryServiceStrategy(EspaiTerritorialQueryServiceStrategy espaiTerritorialQueryServiceStrategy) {
        this.espaiTerritorialQueryServiceStrategy = espaiTerritorialQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return espaiTerritorialQueryServiceStrategy instanceof EspaiTerritorialQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public EspaiTerritorialQueryServiceAdapter(EspaiTerritorialDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando EspaiTerritorialQueryServiceAdapter.", e);
        }
    }

    public int getNumFills() {
        return espaiTerritorialQueryServiceStrategy.getNumFills(id);
    }

    public int getNumUnitatsAdministratives() {
        return espaiTerritorialQueryServiceStrategy.getNumUnitatsAdministratives(id);
    }

    public EspaiTerritorialQueryServiceAdapter obtenirPare() {
        if (this.getPadre() == null) {return null;}
        return (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirPare(this.getPadre()));
    }

    public List<EspaiTerritorialQueryServiceAdapter> llistarFills(EspaiTerritorialCriteria espaiTerritorialCriteria) {
        List<EspaiTerritorialDTO> llistaDTO = espaiTerritorialQueryServiceStrategy.llistarFills(id, espaiTerritorialCriteria);
        List<EspaiTerritorialQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EspaiTerritorialQueryServiceAdapter>();
        for (EspaiTerritorialDTO espaiTerritorialDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(),espaiTerritorialDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
            List<UnitatAdministrativaDTO> llistaDTO = espaiTerritorialQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaQueryServiceAdapter;
    }

    public ArxiuQueryServiceAdapter obtenirMapa() {
        if (this.getMapa() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirMapa(this.getMapa()));
    }
    
    public ArxiuQueryServiceAdapter obtenirLogo() {
        if (this.getLogo() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), espaiTerritorialQueryServiceStrategy.obtenirLogo(this.getLogo()));
    }
    
}
