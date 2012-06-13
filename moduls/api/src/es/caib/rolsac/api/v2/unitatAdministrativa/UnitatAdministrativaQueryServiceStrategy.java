package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
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
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public interface UnitatAdministrativaQueryServiceStrategy {

    UnitatAdministrativaDTO obtenirPare(long idPare);

    EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt);

    TractamentDTO obtenirTractament(long idTract);

    List<UnitatAdministrativaDTO> llistarFilles(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria);

    List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria);

    List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria);

    List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria);

    List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria);

    List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria);

    List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria);
    
    List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria);

    List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria);

    ArxiuDTO obtenirFotop(Long fotop);

    ArxiuDTO obtenirFotog(Long fotog);

    ArxiuDTO obtenirLogoh(Long logoh);

    ArxiuDTO obtenirLogov(Long logov);

    ArxiuDTO obtenirLogos(Long logos);

    ArxiuDTO obtenirLogot(Long logot);

    int getNumFilles(Long id);

    int getNumEdificis(Long id);

    int getNumPersonal(Long id);

    int getNumNormatives(Long id);

    int getNumProcediments(Long id);

    int getNumTramits(Long id);

    int getNumUsuaris(Long id);

    int getNumFitxes(Long id);

    int getNumSeccions(Long id);

    int getNumMateries(Long id);

}
