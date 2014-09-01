package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.tractament.TractamentCriteria;
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public interface UnitatAdministrativaQueryServiceStrategy {

    public UnitatAdministrativaDTO obtenirPare(long idPare) throws StrategyException;

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) throws StrategyException;

    public TractamentDTO obtenirTractament(long idTract, TractamentCriteria tractamentCriteria) throws StrategyException;

    public List<UnitatAdministrativaDTO> llistarFilles(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;
    
    public List<UnitatAdministrativaDTO> llistarDescendents(long uaId) throws StrategyException;

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) throws StrategyException;

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) throws StrategyException;

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException;

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException;

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws StrategyException;

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) throws StrategyException;

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws StrategyException;
    
    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws StrategyException;

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException;

    public ArxiuDTO obtenirFotop(Long fotop) throws StrategyException;

    public ArxiuDTO obtenirFotog(Long fotog) throws StrategyException;

    public ArxiuDTO obtenirLogoh(Long logoh) throws StrategyException;

    public ArxiuDTO obtenirLogov(Long logov) throws StrategyException;

    public ArxiuDTO obtenirLogos(Long logos) throws StrategyException;

    public ArxiuDTO obtenirLogot(Long logot) throws StrategyException;

    public int getNumFilles(Long id) throws StrategyException;

    public int getNumEdificis(Long id) throws StrategyException;

    public int getNumPersonal(Long id) throws StrategyException;

    public int getNumNormatives(Long id) throws StrategyException;

    public int getNumProcediments(Long id) throws StrategyException;

    public int getNumTramits(Long id) throws StrategyException;

    public int getNumUsuaris(Long id) throws StrategyException;

    public int getNumFitxes(Long id) throws StrategyException;

    public int getNumSeccions(Long id) throws StrategyException;

    public int getNumMateries(Long id) throws StrategyException;

}
