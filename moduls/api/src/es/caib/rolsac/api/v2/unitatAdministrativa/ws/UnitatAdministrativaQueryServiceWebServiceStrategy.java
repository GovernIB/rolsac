package es.caib.rolsac.api.v2.unitatAdministrativa.ws;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
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
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceWebServiceStrategy implements UnitatAdministrativaQueryServiceStrategy {

    UnitatAdministrativaQueryServiceGateway gateway;

    public UnitatAdministrativaDTO obtenirPare(long idPare) {
        // TODO Auto-generated method stub
        return null;
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) {
        // TODO Auto-generated method stub
        return null;
    }

    public TractamentDTO obtenirTractament(long idTract) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaUADTO> llistarTotesFitxes(long id, FitxaUACriteria fitxaUACriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirFotop(Long fotop) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirFotog(Long fotog) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirLogoh(Long logoh) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirLogov(Long logov) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirLogos(Long logos) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirLogot(Long logot) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumFilles(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumEdificis(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumPersonal(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumNormatives(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumProcediments(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumTramits(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumUsuaris(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumFitxes(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumSeccions(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumMateries(Long id) {
        // TODO Auto-generated method stub
        return 0;
    }


}
