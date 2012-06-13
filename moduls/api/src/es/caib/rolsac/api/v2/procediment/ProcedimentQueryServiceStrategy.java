package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface ProcedimentQueryServiceStrategy {

    int getNumTramits(long id);

    int getNumNormatives(long id, int tipus);

    int getNumMateries(long id);

    int getNumDocuments(long id);

    int getNumFetsVitals(long id);

    List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria);

    List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria);

    List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria);

    List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria);

    List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria);

}
