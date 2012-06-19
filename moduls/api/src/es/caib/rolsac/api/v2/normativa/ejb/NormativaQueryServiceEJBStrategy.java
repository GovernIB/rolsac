package es.caib.rolsac.api.v2.normativa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceEJBStrategy implements NormativaQueryServiceStrategy {

    private NormativaQueryServiceDelegate normativaQueryServiceDelegate;

    public void setNormativaQueryServiceDelegate(NormativaQueryServiceDelegate normativaQueryServiceDelegate) {
        this.normativaQueryServiceDelegate = normativaQueryServiceDelegate;
    }

    public NormativaQueryServiceEJBStrategy(){
        normativaQueryServiceDelegate = new NormativaQueryServiceDelegate();
    }

    public ButlletiDTO obtenirButlleti(long idButlleti) {
        return normativaQueryServiceDelegate.obtenirButlleti(idButlleti);
    }

    public List<NormativaDTO> llistarAfectades(long id) {        
        return normativaQueryServiceDelegate.llistarAfectades(id);
    }

    public List<NormativaDTO> llistarAfectants(long id) {
        return normativaQueryServiceDelegate.llistarAfectants(id);
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        return normativaQueryServiceDelegate.llistarProcediments(id, procedimentCriteria);
    }

    public int getNumAfectades(long id) {
        return normativaQueryServiceDelegate.getNumAfectades(id);
    }

    public int getNumAfectants(long id) {
        return normativaQueryServiceDelegate.getNumAfectants(id);
    }

    public int getNumProcediments(long id) {
        return normativaQueryServiceDelegate.getNumProcediments(id);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) {
        return normativaQueryServiceDelegate.obtenirUnitatAdministrativa(idUniAdm);
    }

    public ArxiuDTO obtenirArxiuNormativa(Long idArchivo) {
        return normativaQueryServiceDelegate.obtenirArxiuNormativa(idArchivo);
    }

    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) {
        return normativaQueryServiceDelegate.llistarAfectacionsAfectants(id);
    }

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) {
        return normativaQueryServiceDelegate.llistarAfectacionsAfectades(id);
    }

}
