package es.caib.rolsac.api.v2.normativa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceDelegate {

    private NormativaQueryServiceEJBLocator normativaQueryServiceLocator;
    
    public void setNormativaQueryServiceLocator(NormativaQueryServiceEJBLocator normativaQueryServiceLocator) {
        this.normativaQueryServiceLocator = normativaQueryServiceLocator;
    }

    public ButlletiDTO obtenirButlleti(long idButlleti) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();        
        return ejb.obtenirButlleti(idButlleti);
    }

    public List<NormativaDTO> llistarAfectades(long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.llistarAfectades(id);
    }

    public int getNumAfectades(long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.getNumAfectades(id);
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.llistarProcediments(id, procedimentCriteria);
    }

    public List<NormativaDTO> llistarAfectants(long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.llistarAfectants(id);
    }

    public int getNumAfectants(long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.getNumAfectants(id);
    }

    public int getNumProcediments(long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.getNumProcediments(id);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.obtenirUnitatAdministrativa(idUniAdm);
    }
    
    public ArxiuDTO obtenirArxiuNormativa(long idArchivo) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.obtenirArxiuNormativa(idArchivo);
    }

    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.llistarAfectacionsAfectants(id);
    }

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) {
        NormativaQueryServiceEJB ejb = normativaQueryServiceLocator.getNormativaQueryServiceEJB();
        return ejb.llistarAfectacionsAfectades(id);
    }

}
