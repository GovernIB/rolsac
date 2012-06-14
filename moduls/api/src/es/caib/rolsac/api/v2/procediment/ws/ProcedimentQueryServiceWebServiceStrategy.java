package es.caib.rolsac.api.v2.procediment.ws;

import java.util.List;

import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
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

public class ProcedimentQueryServiceWebServiceStrategy implements ProcedimentQueryServiceStrategy {

    // @Injected
    ProcedimentQueryServiceGateway gateway;

    public String getTitol(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumTramits(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumMateries(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumDocuments(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFetsVitals(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getTipus(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetsVitalsCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
