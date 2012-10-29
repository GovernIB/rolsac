package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface FitxaQueryServiceStrategy {

    public int getNumDocuments(long id) throws StrategyException;

    public int getNumEnllacos(long id) throws StrategyException;

    public int getNumFetsVitals(long id) throws StrategyException;

    public int getNumUnitatsAdministratives(long id) throws StrategyException;
    
    public int getNumSeccions(long id) throws StrategyException;
    
    public List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria) throws StrategyException;

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws StrategyException;

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera) throws StrategyException;

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws StrategyException;
    
    public List<PublicObjectiuDTO> llistarPublicsObjectius(long id, PublicObjectiuCriteria poCriteria) throws StrategyException;

    public ArxiuDTO obtenirIcona(Long icono) throws StrategyException;

    public ArxiuDTO obtenirImatge(Long imagen) throws StrategyException;

    public ArxiuDTO obtenirBaner(Long baner) throws StrategyException;

}
