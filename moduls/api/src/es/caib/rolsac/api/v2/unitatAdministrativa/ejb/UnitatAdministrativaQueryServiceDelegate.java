package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
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
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.ejb.intf.UnitatAdministrativaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class UnitatAdministrativaQueryServiceDelegate {

    private UnitatAdministrativaQueryServiceEJBLocator  unitatAdministrativaQueryServiceLocator;

    public void setUnitatAdministrativaQueryServiceLocator(UnitatAdministrativaQueryServiceEJBLocator unitatAdministrativaQueryServiceLocator) {
        this.unitatAdministrativaQueryServiceLocator = unitatAdministrativaQueryServiceLocator;
    }

    public UnitatAdministrativaDTO obtenirPare(long idPare) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirPare(idPare);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirEspaiTerritorial(idEt);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public TractamentDTO obtenirTractament(long idTract, TractamentCriteria tractamentCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirTractament(idTract, tractamentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarFilles(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarFilles(id, unitatAdministrativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Long> llistarDescendents(long uaId) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return Arrays.asList(ejb.llistarDescendents(uaId));
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarEdificis(id, edificiCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarPersonal(id, personalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarNormatives(id, normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarProcediments(id, procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarTramits(id, tramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarUsuaris(id, usuariCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarFitxes(id, fitxaCriteria, fitxaUACriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarSeccions(id, seccioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.llistarMateries(id, materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirFotop(Long fotop) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirFotop(fotop);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirFotog(Long fotog) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirFotog(fotog);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirLogoh(Long logoh) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirLogoh(logoh);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirLogov(Long logov) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirLogov(logov);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirLogos(Long logos) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirLogos(logos);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirLogot(Long logot) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.obtenirLogot(logot);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFilles(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumFilles(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumEdificis(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumEdificis(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumPersonal(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumPersonal(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumNormatives(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumNormatives(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumProcediments(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumProcediments(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumTramits(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumTramits(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumUsuaris(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumUsuaris(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumFitxes(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumFitxes(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumSeccions(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumSeccions(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public int getNumMateries(Long id) throws DelegateException {
        try {
            UnitatAdministrativaQueryServiceEJBRemote ejb = unitatAdministrativaQueryServiceLocator.getUnitatAdministrativaQueryServiceEJB();
            return ejb.getNumMateries(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

}
