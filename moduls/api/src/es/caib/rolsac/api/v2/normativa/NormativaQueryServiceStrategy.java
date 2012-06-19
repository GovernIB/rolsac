package es.caib.rolsac.api.v2.normativa;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface NormativaQueryServiceStrategy {

    int getNumAfectades(long id);

    int getNumAfectants(long id);

    int getNumProcediments(long id);
    
    ButlletiDTO obtenirButlleti(long idButlleti);
    
    UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm);

    List<NormativaDTO> llistarAfectades(long id);

    List<NormativaDTO> llistarAfectants(long id);

    List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria);

    ArxiuDTO obtenirArxiuNormativa(Long idArchivo);

    List<AfectacioDTO> llistarAfectacionsAfectants(Long id);

    List<AfectacioDTO> llistarAfectacionsAfectades(Long id);

}
