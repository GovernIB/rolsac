package es.caib.rolsac.api.v2.servicio;

import java.util.List;

import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;

public interface ServicioQueryService {

    
    public int getNumNormatives() throws QueryServiceException;
    
    @Deprecated
    public int getNumNormativesLocals() throws QueryServiceException;
    
    @Deprecated
    public int getNumNormativesExternes() throws QueryServiceException;

    public int getNumMateries() throws QueryServiceException;

    public int getNumDocuments() throws QueryServiceException;

    public int getNumFetsVitals() throws QueryServiceException;

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException;

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria) throws QueryServiceException;

    public List<DocumentoServicioQueryServiceAdapter> llistarDocuments(DocumentoServicioCriteria documentoServicioCriteria) throws QueryServiceException;

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria poCriteria) throws QueryServiceException;
        
}
