package es.caib.rolsac.api.v2.normativa;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface NormativaQueryServiceStrategy {

    public int getNumAfectades(long id) throws StrategyException;

    public int getNumAfectants(long id) throws StrategyException;

    public int getNumProcediments(long id) throws StrategyException;
    
    public int getNumServicios(long id) throws StrategyException;
    
    public ButlletiDTO obtenirButlleti(long idButlleti) throws StrategyException;
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) throws StrategyException;

    public List<NormativaDTO> llistarAfectades(long id) throws StrategyException;

    public List<NormativaDTO> llistarAfectants(long id) throws StrategyException;

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) throws StrategyException;

    public List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria DocumentoNormativaCriteria) throws StrategyException;
    	
    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) throws StrategyException;

    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) throws StrategyException;

	public void setUrl(String url);

}
