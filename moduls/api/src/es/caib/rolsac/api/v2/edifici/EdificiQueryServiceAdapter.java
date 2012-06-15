package es.caib.rolsac.api.v2.edifici;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.ejb.EdificiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class EdificiQueryServiceAdapter extends EdificiDTO implements EdificiQueryService {

    private static Log log = LogFactory.getLog(EdificiQueryServiceAdapter.class);
    
    EdificiQueryServiceStrategy edificiQueryServiceStrategy;
    
    public void setEdificiQueryServiceStrategy(EdificiQueryServiceStrategy edificiQueryServiceStrategy) {
        this.edificiQueryServiceStrategy = edificiQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return edificiQueryServiceStrategy instanceof EdificiQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public EdificiQueryServiceAdapter(EdificiDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando EdificiQueryServiceAdapter.", e);
        }
    }

    public int getNumUnitatsAdministratives() {
        return edificiQueryServiceStrategy.getNumUnitatsAdministratives(id);
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = edificiQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public ArxiuQueryServiceAdapter obtenirFotoPequenya() {
        if (this.getFotoPequenya() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirFotoPequenya(this.getFotoPequenya()));
    }

    public ArxiuQueryServiceAdapter obtenirFotoGrande() {
        if (this.getFotoGrande() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirFotoGrande(this.getFotoGrande()));
    }

    public ArxiuQueryServiceAdapter obtenirPlano() {
        if (this.getPlano() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), edificiQueryServiceStrategy.obtenirPlano(this.getPlano()));
    }

}