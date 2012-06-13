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
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceEJBStrategy implements UnitatAdministrativaQueryServiceStrategy {

    private UnitatAdministrativaQueryServiceDelegate unitatAdministrativaQueryServiceDelegate;    
    
    public void setUnitatAdministrativaQueryServiceDelegate(UnitatAdministrativaQueryServiceDelegate unitatAdministrativaQueryServiceDelegate) {
        this.unitatAdministrativaQueryServiceDelegate = unitatAdministrativaQueryServiceDelegate;
    }

    public UnitatAdministrativaDTO obtenirPare(long idPare) {
       return unitatAdministrativaQueryServiceDelegate.obtenirPare(idPare);
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) {
        return unitatAdministrativaQueryServiceDelegate.obtenirEspaiTerritorial(idEt);
    }

    public TractamentDTO obtenirTractament(long idTract) {
        return unitatAdministrativaQueryServiceDelegate.obtenirTractament(idTract);
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarFilles(id, unitatAdministrativaCriteria);
    }

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarEdificis(id, edificiCriteria);
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarPersonal(id, personalCriteria);
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarProcediments(id, procedimentCriteria);
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarTramits(id, tramitCriteria);
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarUsuaris(id, usuariCriteria);
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarSeccions(id, seccioCriteria);
    }
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        return unitatAdministrativaQueryServiceDelegate.llistarMateries(id, materiaCriteria);
    }

    public ArxiuDTO obtenirFotop(Long fotop) {
        return unitatAdministrativaQueryServiceDelegate.obtenirFotop(fotop);
    }

    public ArxiuDTO obtenirFotog(Long fotog) {
        return unitatAdministrativaQueryServiceDelegate.obtenirFotog(fotog);
    }

    public ArxiuDTO obtenirLogoh(Long logoh) {
        return unitatAdministrativaQueryServiceDelegate.obtenirLogoh(logoh);
    }

    public ArxiuDTO obtenirLogov(Long logov) {
        return unitatAdministrativaQueryServiceDelegate.obtenirLogov(logov);
    }

    public ArxiuDTO obtenirLogos(Long logos) {
        return unitatAdministrativaQueryServiceDelegate.obtenirLogos(logos);
    }

    public ArxiuDTO obtenirLogot(Long logot) {
        return unitatAdministrativaQueryServiceDelegate.obtenirLogot(logot);
    }

    public int getNumFilles(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumFilles(id);
    }

    public int getNumEdificis(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumEdificis(id);
    }

    public int getNumPersonal(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumPersonal(id);
    }

    public int getNumNormatives(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumNormatives(id);
    }

    public int getNumProcediments(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumProcediments(id);
    }

    public int getNumTramits(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumTramits(id);
    }

    public int getNumUsuaris(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumUsuaris(id);
    }

    public int getNumFitxes(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumFitxes(id);
    }

    public int getNumSeccions(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumSeccions(id);
    }

    public int getNumMateries(Long id) {
        return unitatAdministrativaQueryServiceDelegate.getNumMateries(id);
    }

}
