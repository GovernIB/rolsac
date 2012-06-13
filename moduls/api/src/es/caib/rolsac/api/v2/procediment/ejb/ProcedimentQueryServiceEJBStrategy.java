package es.caib.rolsac.api.v2.procediment.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class ProcedimentQueryServiceEJBStrategy implements ProcedimentQueryServiceStrategy {

    private ProcedimentQueryServiceDelegate procedimentQueryServiceDelegate;

    public void setProcedimentQueryServiceDelegate(ProcedimentQueryServiceDelegate procedimentQueryServiceDelegate) {
        this.procedimentQueryServiceDelegate = procedimentQueryServiceDelegate;
    }

    public int getNumTramits(long id) {
        return procedimentQueryServiceDelegate.getNumTramits(id);
    }

    public int getNumNormatives(long id, int tipus) {
        return procedimentQueryServiceDelegate.getNumNormatives(id, tipus);
    }

    public int getNumMateries(long id) {
        return procedimentQueryServiceDelegate.getNumMateries(id);
    }

    public int getNumDocuments(long id) {
        return procedimentQueryServiceDelegate.getNumDocuments(id);
    }

    public int getNumFetsVitals(long id) {
        return procedimentQueryServiceDelegate.getNumFetsVitals(id);
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        return procedimentQueryServiceDelegate.llistarTramits(id, tramitCriteria);
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        return procedimentQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        return procedimentQueryServiceDelegate.llistarMateries(id, materiaCriteria);
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria) {
        return procedimentQueryServiceDelegate.llistarFetsVitals(id, fetsVitalsCriteria);
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        return procedimentQueryServiceDelegate.llistarDocuments(id, documentCriteria);
    }

}
