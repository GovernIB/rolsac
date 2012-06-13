package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public interface ProcedimentQueryService {

    // Test
    int getNumTramits();

    int getNumNormatives();
    
    int getNumNormativesLocals();
    
    int getNumNormativesExternes();

    int getNumMateries();

    int getNumDocuments();

    int getNumFetsVitals();

    List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria);

    List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria);

    List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria);

    List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria);

    List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria);

}
