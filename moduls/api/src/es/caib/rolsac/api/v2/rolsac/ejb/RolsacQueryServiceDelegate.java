package es.caib.rolsac.api.v2.rolsac.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.intf.RolsacQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;

public class RolsacQueryServiceDelegate {

    private RolsacQueryServiceEJBLocator rolsacQueryServiceLocator;

    public void setRolsacQueryServiceLocator(RolsacQueryServiceEJBLocator rolsacQueryServiceLocator) {
        this.rolsacQueryServiceLocator = rolsacQueryServiceLocator;
    }
    
    public CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirCatalegDocuments(catalegDocumentsCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<CatalegDocumentsDTO> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarCatalegsDocuments(catalegDocumentsCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirExcepcioDocumentacio(excepcioDocumentacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    
    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirProcediment(procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarProcediments(procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumProcediments(ProcedimentCriteria procedimentCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.getNumProcediments(procedimentCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public ServicioDTO obtenirServicio(ServicioCriteria servicioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirServicio(servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<ServicioDTO> llistarServicios(ServicioCriteria servicioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarServicios(servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public int getNumServicios(ServicioCriteria servicioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.getNumServicios(servicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirMateria(materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarMateries(materiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirTramit(tramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarTramit(tramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria uaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirUnitatAdministrativa(uaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria uaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarUnitatsAdministratives(uaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirFetVital(fetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarFetsVitals(fetVitalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirFitxa(fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarFitxes(fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirNormativa(normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarNormatives(normativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirPersonal(personalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarPersonal(personalCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirDocumentTramit(documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarDocumentTramit(documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    
    public List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria documentoNormativaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarDocumentoNormativa(documentoNormativaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public List<DocumentoServicioDTO> llistarDocumentoServicio(DocumentoServicioCriteria documentoServicioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarDocumentoServicio(documentoServicioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
            
    	
    
    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirUsuari(usuariCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarUsuaris(usuariCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public IdiomaDTO obtenirIdioma(IdiomaCriteria idiomaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirIdioma(idiomaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarIdiomes(idiomaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirTaxa(taxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarTaxes(taxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria afvCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirAgrupacioFetVital(afvCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria afvCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarAgrupacionsFetsVitals(afvCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria amCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirAgrupacioMateria(amCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria amCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarAgrupacionsMateries(amCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirButlleti(butlletiCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarButlletins(butlletiCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public DocumentDTO obtenirDocument(DocumentCriteria docCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirDocument(docCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<DocumentDTO> llistarDocuments(DocumentCriteria docCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarDocuments(docCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirEdifici(edificiCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarEdificis(edificiCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirEnllac(enllacCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarEnllacos(enllacCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria etCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirEspaiTerritorial(etCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria etCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarEspaisTerritorials(etCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirFamilia(familiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarFamilies(familiaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirPublicObjectiu(publicObjectiuCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarPublicsObjectius(publicObjectiuCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
   
    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirFitxaUA(fitxaUACriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarFitxesUA(fitxaUACriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirFormulari(formulariCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarFormularis(formulariCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria ifCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirIconaFamilia(ifCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria ifCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarIconesFamilies(ifCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria imCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirIconaMateria(imCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria imCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarIconesMateries(imCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria maCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirMateriaAgrupacio(maCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria maCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarMateriesAgrupacions(maCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirPerfil(perfilCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarPerfils(perfilCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirSeccio(seccioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarSeccions(seccioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria umCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirUnitatMateria(umCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria umCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarUnitatsMateries(umCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirTipus(tipusCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarTipus(tipusCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirTipusAfectacio(tipusAfectacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarTipusAfectacio(tipusAfectacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.llistarTipusIniciacions(iniciacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws DelegateException {
        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.obtenirTipusIniciacio(iniciacioCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    @SuppressWarnings("unchecked")
    public int getNumFitxes(FitxaCriteria fitxaCriteria) throws DelegateException {

        try {
            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
            return ejb.getNumFitxes(fitxaCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

	public SilencioDTO obtenirSilenci(Long codSilencio, String idioma) throws DelegateException {
		 try {
	            RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
	            return ejb.obtenirSilenci(codSilencio,idioma);
	        } catch (LocatorException e) {
	            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
	        } catch (RemoteException e) {
	            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
	        }
	}
    
}
