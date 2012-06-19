package es.caib.rolsac.api.v2.normativa.ws;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQuerySeviceWebServiceStrategy implements NormativaQueryServiceStrategy {

    NormativaQueryServiceGateway gateway;

    public NormativaDTO obtenirNormativa(long id, NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ButlletiDTO obtenirButlleti(long idButlleti) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<NormativaDTO> llistarAfectades(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<NormativaDTO> llistarAfectants(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumAfectades(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumAfectants(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumProcediments(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public TipusDTO obtenirTipus(long idTipus) {
        // TODO Auto-generated method stub
        return null;
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirArxiuNormativa(Long idArchivo) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
