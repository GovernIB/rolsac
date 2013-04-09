package es.caib.rolsac.api.v2.fitxa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.ejb.FitxaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class FitxaQueryServiceAdapter extends FitxaDTO implements FitxaQueryService {

    private static final long serialVersionUID = 5648702326587801581L;

    private FitxaQueryServiceStrategy fitxaQueryServiceStrategy;

    public void setFitxaQueryServiceStrategy(FitxaQueryServiceStrategy fitxaQueryServiceStrategy) {
        this.fitxaQueryServiceStrategy = fitxaQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return fitxaQueryServiceStrategy instanceof FitxaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public FitxaQueryServiceAdapter(FitxaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public int getNumDocuments() throws QueryServiceException {
        try {
            return fitxaQueryServiceStrategy.getNumDocuments(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de documentos.", e);
        }
    }

    public int getNumEnllacos() throws QueryServiceException {
        try {
            return fitxaQueryServiceStrategy.getNumEnllacos(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de enlaces.", e);
        }
    }

    public int getNumFetsVitals() throws QueryServiceException {
        try {
            return fitxaQueryServiceStrategy.getNumFetsVitals(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de hechos vitales.", e);
        }
    }

    public int getNumUnitatsAdministratives() throws QueryServiceException {
        try {
            return fitxaQueryServiceStrategy.getNumUnitatsAdministratives(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de unidades administrativas.", e);
        }
    }

    public int getNumSeccions() throws QueryServiceException {
        try {
            return fitxaQueryServiceStrategy.getNumSeccions(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de seccions.", e);
        }
    }
    
    public List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) throws QueryServiceException {
        try {
            List<EnllacDTO> llistaDTO = fitxaQueryServiceStrategy.llistarEnllacos(getId(), enllacCriteria);
            List<EnllacQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EnllacQueryServiceAdapter>();
            for (EnllacDTO enllacDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), enllacDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "enlaces.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = fitxaQueryServiceStrategy.llistarUnitatsAdministratives(getId(), unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "unidades administrativas.", e);
        }
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = fitxaQueryServiceStrategy.llistarSeccions(getId(), seccioCriteria);
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO seccioDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones.", e);
        }
    }
    
    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException {
        try {
            List<DocumentDTO> llistaDTO = fitxaQueryServiceStrategy.llistarDocuments(getId(), documentCriteria);
            List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
            for (DocumentDTO documentDTO : llistaDTO) {            
                llistaQueryServiceAdapter.add((DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos.", e);
        }
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCritera) throws QueryServiceException {
        try {
            List<FetVitalDTO> llistaDTO = fitxaQueryServiceStrategy.llistarFetsVitals(getId(), fetVitalCritera);
            List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
            for (FetVitalDTO fetVitalDTO : llistaDTO) {            
                llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hechos vitales.", e);
        }
    }
    
    public ArxiuQueryServiceAdapter obtenirIcona() throws QueryServiceException {
        if (this.getIcono() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirIcona(this.getIcono()));
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "icono.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirImatge() throws QueryServiceException {
        if (this.getImagen() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirImatge(this.getImagen()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "imagen.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirBaner() throws QueryServiceException {
        if (this.getBaner() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), fitxaQueryServiceStrategy.obtenirBaner(this.getBaner()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "baner.", e);
        }
    }

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) throws QueryServiceException {
        try {
            List<PublicObjectiuDTO> llistaDTO = fitxaQueryServiceStrategy.llistarPublicsObjectius(getId(), poCriteria);
            List<PublicObjectiuQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PublicObjectiuQueryServiceAdapter>();
            for (PublicObjectiuDTO poDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), poDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

}