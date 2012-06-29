package es.caib.rolsac.api.v2.procediment.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class ProcedimentQueryServiceEJBStrategy implements ProcedimentQueryServiceStrategy {

    private ProcedimentQueryServiceDelegate procedimentQueryServiceDelegate;

    public void setProcedimentQueryServiceDelegate(ProcedimentQueryServiceDelegate procedimentQueryServiceDelegate) {
        this.procedimentQueryServiceDelegate = procedimentQueryServiceDelegate;
    }

    public int getNumTramits(long id) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.getNumTramits(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.getNumNormatives(id, tipus);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumMateries(long id) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.getNumMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumDocuments(long id) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.getNumDocuments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFetsVitals(long id) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.getNumFetsVitals(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.llistarTramits(id, tramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.llistarMateries(id, materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.llistarFetsVitals(id, fetsVitalsCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) throws StrategyException {
        try {
            return procedimentQueryServiceDelegate.llistarDocuments(id, documentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
