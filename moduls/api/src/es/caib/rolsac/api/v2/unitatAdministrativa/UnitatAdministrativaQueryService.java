package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
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

    UnitatAdministrativaQueryServiceAdapter obtenirPare();

    EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial();

    TractamentQueryServiceAdapter obtenirTractament();
    
    List<UnitatAdministrativaQueryServiceAdapter> llistarFilles(UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria);

    List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria);

    List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria);

    List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria);

    List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria);

    List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria);

    List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria);
    
    List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria);

    List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria);

    int getNumFilles();
    
    int getNumEdificis();
    
    int getNumPersonal();
    
    int getNumNormatives();
    
    int getNumProcediments();
    
    int getNumTramits();
    
    int getNumUsuaris();
    
    int getNumFitxes();
    
    int getNumSeccions();
    
    int getNumMateries();    
    
    ArxiuQueryServiceAdapter obtenirFotop();
    
    ArxiuQueryServiceAdapter obtenirFotog();
    
    ArxiuQueryServiceAdapter obtenirLogoh();
    
    ArxiuQueryServiceAdapter obtenirLogov();
    
    ArxiuQueryServiceAdapter obtenirLogos();
    
    ArxiuQueryServiceAdapter obtenirLogot();
    
}
