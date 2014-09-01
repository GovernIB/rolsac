package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public interface ProcedimentQueryService {

    public int getNumTramits() throws QueryServiceException;

    public int getNumNormatives() throws QueryServiceException;
    
    public int getNumNormativesLocals() throws QueryServiceException;
    
    public int getNumNormativesExternes() throws QueryServiceException;

    public int getNumMateries() throws QueryServiceException;

    public int getNumDocuments() throws QueryServiceException;

    public int getNumFetsVitals() throws QueryServiceException;

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException;

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException;

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria) throws QueryServiceException;

    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException;

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) throws QueryServiceException;
        
}
