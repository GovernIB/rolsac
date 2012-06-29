package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tractament.TractamentQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public interface UnitatAdministrativaQueryService {

    public UnitatAdministrativaQueryServiceAdapter obtenirPare() throws QueryServiceException;

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial() throws QueryServiceException;

    public TractamentQueryServiceAdapter obtenirTractament() throws QueryServiceException;
    
    public List<UnitatAdministrativaQueryServiceAdapter> llistarFilles(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) throws QueryServiceException;

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) throws QueryServiceException;

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException;

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException;

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) throws QueryServiceException;

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;
    
    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException;

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;

    public int getNumFilles() throws QueryServiceException;
    
    public int getNumEdificis() throws QueryServiceException;
    
    public int getNumPersonal() throws QueryServiceException;
    
    public int getNumNormatives() throws QueryServiceException;
    
    public int getNumProcediments() throws QueryServiceException;
    
    public int getNumTramits() throws QueryServiceException;
    
    public int getNumUsuaris() throws QueryServiceException;
    
    public int getNumFitxes() throws QueryServiceException;
    
    public int getNumSeccions() throws QueryServiceException;
    
    public int getNumMateries() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirFotop() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirFotog() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirLogoh() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirLogov() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirLogos() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirLogot() throws QueryServiceException;
    
}
