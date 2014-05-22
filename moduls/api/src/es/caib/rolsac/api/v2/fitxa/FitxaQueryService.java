package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface FitxaQueryService {

    public int getNumDocuments() throws QueryServiceException;

    public int getNumEnllacos() throws QueryServiceException;

    public int getNumFetsVitals() throws QueryServiceException;

    public int getNumUnitatsAdministratives() throws QueryServiceException;

    public int getNumSeccions() throws QueryServiceException;
    
    public int getNumMateries() throws QueryServiceException;
    
    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;
    
    public List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) throws QueryServiceException;

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException;

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCritera) throws QueryServiceException;
    
    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException;
    
    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirIcona() throws QueryServiceException;

    public ArxiuQueryServiceAdapter obtenirImatge() throws QueryServiceException;

    public ArxiuQueryServiceAdapter obtenirBaner() throws QueryServiceException;

}
