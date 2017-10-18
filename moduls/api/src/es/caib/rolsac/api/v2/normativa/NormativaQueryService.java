package es.caib.rolsac.api.v2.normativa;

import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface NormativaQueryService {
    
    public static enum TIPUS_NORMATIVA {TOTES};

    public int getNumAfectades() throws QueryServiceException;

    public int getNumAfectants() throws QueryServiceException;

    public int getNumProcediments() throws QueryServiceException;
    
    public int getNumServicios() throws QueryServiceException;
    
    public List<NormativaQueryServiceAdapter> llistarAfectades() throws QueryServiceException;

    public List<NormativaQueryServiceAdapter> llistarAfectants() throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;
    
    public List<ServicioQueryServiceAdapter> llistarServicios(ServicioCriteria servicioCriteria) throws QueryServiceException;
    
    public ButlletiQueryServiceAdapter obtenirButlleti() throws QueryServiceException;
    
    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException;
    
    public List<DocumentoNormativaQueryServiceAdapter> llistarDocumentoNormativa(DocumentoNormativaCriteria documentoNormativaCriteria) throws QueryServiceException;
    
    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectades() throws QueryServiceException;
    
    public List<AfectacioQueryServiceAdapter> llistarAfectacionsAfectants() throws QueryServiceException;

}
