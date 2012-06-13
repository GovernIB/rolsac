package es.caib.rolsac.api.v2.tramit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class TramitQueryServiceAdapter extends TramitDTO implements TramitQueryService {

    private TramitQueryServiceStrategy tramitQueryServiceStrategy;

    public void setTramitQueryServiceStrategy(TramitQueryServiceStrategy tramitQueryServiceStrategy) {
        this.tramitQueryServiceStrategy = tramitQueryServiceStrategy;
    }

    public TramitQueryServiceAdapter(TramitDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return tramitQueryServiceStrategy instanceof TramitQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public int getNumDocumentsInformatius() {
        return tramitQueryServiceStrategy.getNumDocumentsInformatius(id);
    }

    public int getNumFormularis() {
        return tramitQueryServiceStrategy.getNumFormularis(id);
    }

    public int getNumTaxes() {
        return tramitQueryServiceStrategy.getNumTaxes(id);
    }

    public ProcedimentQueryServiceAdapter obtenirProcediment() {
        if (this.getProcedimiento() == null) {return null;}
        ProcedimentDTO dto = tramitQueryServiceStrategy.obtenirProcediment(this.getProcedimiento());
        return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirOrganCompetent() {
        if (this.getOrganCompetent() == null) {return null;}
        UnitatAdministrativaDTO dto = tramitQueryServiceStrategy.obtenirOrganCompetent(this.getOrganCompetent());
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
    }

    public List<DocumentTramitQueryServiceAdapter> llistatDocumentsInformatius(DocumentTramitCriteria documentTramitCriteria) {
        List<DocumentTramitDTO> llistaDTO = tramitQueryServiceStrategy.llistatDocumentsInformatius(id, documentTramitCriteria);
        List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
        for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<DocumentTramitQueryServiceAdapter> llistarFormularis(DocumentTramitCriteria documentTramitCriteria) {
        List<DocumentTramitDTO> llistaDTO = tramitQueryServiceStrategy.llistarFormularis(id, documentTramitCriteria);
        List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
        for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) {
        List<TaxaDTO> llistaDTO = tramitQueryServiceStrategy.llistarTaxes(id, taxaCriteria);
        List<TaxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TaxaQueryServiceAdapter>();
        for (TaxaDTO taxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), taxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

}
