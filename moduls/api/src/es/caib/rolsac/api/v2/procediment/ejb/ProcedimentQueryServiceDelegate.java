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
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class ProcedimentQueryServiceDelegate {

    private ProcedimentQueryServiceEJBLocator procedimentQueryServiceLocator;
    
    public void setProcedimentQueryServiceLocator(ProcedimentQueryServiceEJBLocator procedimentQueryServiceLocator) {
        this.procedimentQueryServiceLocator = procedimentQueryServiceLocator;
    }
 
    public int getNumTramits(long id) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.getNumTramits(id);
    }

    public int getNumNormatives(long id, int tipus) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.getNumNormatives(id, tipus);
    }

    public int getNumMateries(long id) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.getNumMateries(id);
    }

    public int getNumDocuments(long id) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.getNumDocuments(id);
    }

    public int getNumFetsVitals(long id) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.getNumFetsVitals(id);
    }
    
    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.llistarTramits(id, tramitCriteria);
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.llistarNormatives(id, normativaCriteria);
    }
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.llistarMateries(id, materiaCriteria);
    }
 
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.llistarFetsVitals(id, fetVitalCriteria);
    }
    
    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        ProcedimentQueryServiceEJB ejb = procedimentQueryServiceLocator.getProcedimentQueryServiceEJB();
        return ejb.llistarDocuments(id, documentCriteria);
    }    
}
