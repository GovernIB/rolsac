package es.caib.rolsac.api.v2.normativa;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface NormativaQueryServiceStrategy {

    public int getNumAfectades(long id) throws StrategyException;

    public int getNumAfectants(long id) throws StrategyException;

    public int getNumProcediments(long id) throws StrategyException;
    
    public ButlletiDTO obtenirButlleti(long idButlleti) throws StrategyException;
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) throws StrategyException;

    public List<NormativaDTO> llistarAfectades(long id) throws StrategyException;

    public List<NormativaDTO> llistarAfectants(long id) throws StrategyException;

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public ArxiuDTO obtenirArxiuNormativa(Long idArchivo) throws StrategyException;

    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) throws StrategyException;

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) throws StrategyException;

}
