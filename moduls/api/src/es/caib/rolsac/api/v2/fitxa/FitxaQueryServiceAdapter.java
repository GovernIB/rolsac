package es.caib.rolsac.api.v2.fitxa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.ejb.FitxaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaQueryServiceAdapter extends FitxaDTO implements FitxaQueryService {

    private static Log log = LogFactory.getLog(FitxaQueryServiceAdapter.class);
    
    private FitxaQueryServiceStrategy fitxaQueryServiceStrategy;

    public void setFitxaQueryServiceStrategy(FitxaQueryServiceStrategy fitxaQueryServiceStrategy) {
        this.fitxaQueryServiceStrategy = fitxaQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return fitxaQueryServiceStrategy instanceof FitxaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FitxaQueryServiceAdapter(FitxaDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando FitxaQueryServiceAdapter.", e);
        }
    }

    public int getNumDocuments() {
        return fitxaQueryServiceStrategy.getNumDocuments(id);
    }

    public int getNumEnllacos() {
        return fitxaQueryServiceStrategy.getNumEnllacos(id);
    }

    public int getNumFetsVitals() {
        return fitxaQueryServiceStrategy.getNumFetsVitals(id);
    }

    public int getNumUnitatsAdministratives() {
        return fitxaQueryServiceStrategy.getNumUnitatsAdministratives(id);
    }

    public int getNumSeccions() {
        return fitxaQueryServiceStrategy.getNumSeccions(id);
    }
    
    public List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) {
        List<EnllacDTO> llistaDTO = fitxaQueryServiceStrategy.llistarEnllacos(id, enllacCriteria);
        List<EnllacQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EnllacQueryServiceAdapter>();
        for (EnllacDTO enllacDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), enllacDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = fitxaQueryServiceStrategy.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) {
        List<SeccioDTO> llistaDTO = fitxaQueryServiceStrategy.llistarSeccions(id, seccioCriteria);
        List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
        for (SeccioDTO seccioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) {
        List<DocumentDTO> llistaDTO = fitxaQueryServiceStrategy.llistarDocuments(id, documentCriteria);
        List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
        for (DocumentDTO documentDTO : llistaDTO) {            
            llistaQueryServiceAdapter.add((DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCritera) {
        List<FetVitalDTO> llistaDTO = fitxaQueryServiceStrategy.llistarFetsVitals(id, fetVitalCritera);
        List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
        for (FetVitalDTO fetVitalDTO : llistaDTO) {            
            llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public ArxiuQueryServiceAdapter obtenirIcona() {
        if (this.getIcono() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirIcona(this.getIcono()));
    }

    public ArxiuQueryServiceAdapter obtenirImatge() {
        if (this.getImagen() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirImatge(this.getImagen()));
    }

    public ArxiuQueryServiceAdapter obtenirBaner() {
        if (this.getBaner() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirBaner(this.getBaner()));
    }

}
