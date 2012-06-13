package es.caib.rolsac.api.v2.procediment;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ejb.ProcedimentQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

/**
 * Adapta el ProcedimientoDTO a un Objecte amb dades i metodes
 * 
 */
public class ProcedimentQueryServiceAdapter extends ProcedimentDTO implements ProcedimentQueryService {

    private ProcedimentQueryServiceStrategy procedimentQueryServiceStrategy;

    public void setProcedimentQueryServiceStrategy(ProcedimentQueryServiceStrategy procedimentQueryServiceStrategy) {
        this.procedimentQueryServiceStrategy = procedimentQueryServiceStrategy;
    }

    public ProcedimentQueryServiceAdapter(ProcedimentDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return procedimentQueryServiceStrategy instanceof ProcedimentQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public int getNumTramits() {
        return procedimentQueryServiceStrategy.getNumTramits(id);
    }

    public int getNumNormatives() {
        return procedimentQueryServiceStrategy.getNumNormatives(id, 0);
    }
    
    public int getNumNormativesLocals() {
        return procedimentQueryServiceStrategy.getNumNormatives(id, 1);
    }
    
    public int getNumNormativesExternes() {
        return procedimentQueryServiceStrategy.getNumNormatives(id, 2);
    }    
    
    public int getNumMateries() {
        return procedimentQueryServiceStrategy.getNumMateries(id);
    }

    public int getNumDocuments() {
        return procedimentQueryServiceStrategy.getNumDocuments(id);
    }

    public int getNumFetsVitals() {
        return procedimentQueryServiceStrategy.getNumFetsVitals(id);
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) {
        List<TramitDTO> llistaDTO = procedimentQueryServiceStrategy.llistarTramits(id, tramitCriteria);
        List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
        for (TramitDTO tramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) {
        List<MateriaDTO> llistaDTO = procedimentQueryServiceStrategy.llistarMateries(id, materiaCriteria);
        List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
        for (MateriaDTO materiaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> llistaDTO = procedimentQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetsVitalsCriteria) {
        List<FetVitalDTO> llistaDTO = procedimentQueryServiceStrategy.llistarFetsVitals(id, fetsVitalsCriteria);
        List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
        for (FetVitalDTO fetVitalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) {
        List<DocumentDTO> llistaDTO = procedimentQueryServiceStrategy.llistarDocuments(id, documentCriteria);
        List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
        for (DocumentDTO documentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO));
        }
        return llistaQueryServiceAdapter;
    }

}
