package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

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
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceDelegate {

     // @Injected
    private UnitatAdministrativaQueryServiceEJBLocator  unitatAdministrativaQueryServiceLocator;

    public void setUnitatAdministrativaQueryServiceLocator(UnitatAdministrativaQueryServiceEJBLocator unitatAdministrativaQueryServiceLocator) {
        this.unitatAdministrativaQueryServiceLocator = unitatAdministrativaQueryServiceLocator;
    }
    
    public UnitatAdministrativaDTO obtenirPare(long idPare) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirPare(idPare);
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirEspaiTerritorial(idEt);
    }

    public TractamentDTO obtenirTractament(long idTract) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirTractament(idTract);
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarFilles(id, unitatAdministrativaCriteria);
    }

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarEdificis(id, edificiCriteria);
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarPersonal(id, personalCriteria);
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarNormatives(id, normativaCriteria);
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarProcediments(id, procedimentCriteria);
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarTramits(id, tramitCriteria);
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarUsuaris(id, usuariCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarFitxes(id, fitxaCriteria);
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarSeccions(id, seccioCriteria);
    }
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.llistarMateries(id, materiaCriteria);
    }

    public ArxiuDTO obtenirFotop(Long fotop) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirFotop(fotop);
    }

    public ArxiuDTO obtenirFotog(Long fotog) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirFotog(fotog);
    }

    public ArxiuDTO obtenirLogoh(Long logoh) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirLogoh(logoh);
    }

    public ArxiuDTO obtenirLogov(Long logov) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirLogov(logov);
    }

    public ArxiuDTO obtenirLogos(Long logos) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirLogos(logos);
    }

    public ArxiuDTO obtenirLogot(Long logot) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.obtenirLogot(logot);
    }

    public int getNumFilles(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumFilles(id);
    }

    public int getNumEdificis(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumEdificis(id);
    }

    public int getNumPersonal(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumPersonal(id);
    }

    public int getNumNormatives(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumNormatives(id);
    }

    public int getNumProcediments(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumProcediments(id);
    }

    public int getNumTramits(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumTramits(id);
    }

    public int getNumUsuaris(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumUsuaris(id);
    }

    public int getNumFitxes(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumFitxes(id);
    }

    public int getNumSeccions(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumSeccions(id);
    }

    public int getNumMateries(Long id) {
        UnitatAdministrativaQueryServiceEJB ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
        return ejb.getNumMateries(id);
    }

}
