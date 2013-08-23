package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
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

    public UnitatAdministrativaDTO obtenirPare(long idPare) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirPare(idPare);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirEspaiTerritorial(idEt);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TractamentDTO obtenirTractament(long idTract) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirTractament(idTract);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarFilles(id, unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarDescendents(long uaId) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarDescendents(uaId);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    
    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarEdificis(id, edificiCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarPersonal(id, personalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarProcediments(id, procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarTramits(id, tramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarUsuaris(id, usuariCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarFitxes(id, fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarSeccions(id, seccioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.llistarMateries(id, materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirFotop(Long fotop) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirFotop(fotop);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirFotog(Long fotog) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirFotog(fotog);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirLogoh(Long logoh) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirLogoh(logoh);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirLogov(Long logov) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirLogov(logov);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirLogos(Long logos) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirLogos(logos);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ArxiuDTO obtenirLogot(Long logot) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.obtenirLogot(logot);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFilles(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumFilles(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumEdificis(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumEdificis(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumPersonal(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumPersonal(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumNormatives(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumNormatives(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumProcediments(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumProcediments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumTramits(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumTramits(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumUsuaris(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumUsuaris(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumFitxes(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumFitxes(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumSeccions(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumSeccions(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumMateries(Long id) throws StrategyException {
        try {
            return unitatAdministrativaQueryServiceDelegate.getNumMateries(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
