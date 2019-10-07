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
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoCriteria;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoDTO;
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
import es.caib.rolsac.api.v2.plataforma.PlataformaCriteria;
import es.caib.rolsac.api.v2.plataforma.PlataformaDTO;
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

	public void setRolsacQueryServiceLocator(final RolsacQueryServiceEJBLocator rolsacQueryServiceLocator) {
		this.rolsacQueryServiceLocator = rolsacQueryServiceLocator;
	}

	public CatalegDocumentsDTO obtenirCatalegDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirCatalegDocuments(catalegDocumentsCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(final CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarCatalegsDocuments(catalegDocumentsCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirExcepcioDocumentacio(excepcioDocumentacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			final ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ProcedimentDTO obtenirProcediment(final ProcedimentCriteria procedimentCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirProcediment(procedimentCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ProcedimentDTO> llistarProcediments(final ProcedimentCriteria procedimentCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarProcediments(procedimentCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumProcediments(final ProcedimentCriteria procedimentCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.getNumProcediments(procedimentCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ServicioDTO obtenirServicio(final ServicioCriteria servicioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirServicio(servicioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServicioDTO> llistarServicios(final ServicioCriteria servicioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarServicios(servicioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumServicios(final ServicioCriteria servicioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.getNumServicios(servicioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public MateriaDTO obtenirMateria(final MateriaCriteria materiaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirMateria(materiaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<MateriaDTO> llistarMateries(final MateriaCriteria materiaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarMateries(materiaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public TramitDTO obtenirTramit(final TramitCriteria tramitCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirTramit(tramitCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TramitDTO> llistarTramits(final TramitCriteria tramitCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarTramit(tramitCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(final UnitatAdministrativaCriteria uaCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirUnitatAdministrativa(uaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(final UnitatAdministrativaCriteria uaCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarUnitatsAdministratives(uaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public PlataformaDTO obtenirPlataforma(final PlataformaCriteria plataformaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirPlataforma(plataformaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public FetVitalDTO obtenirFetVital(final FetVitalCriteria fetVitalCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirFetVital(fetVitalCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<FetVitalDTO> llistarFetsVitals(final FetVitalCriteria fetVitalCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarFetsVitals(fetVitalCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public FitxaDTO obtenirFitxa(final FitxaCriteria fitxaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirFitxa(fitxaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<FitxaDTO> llistarFitxes(final FitxaCriteria fitxaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarFitxes(fitxaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public NormativaDTO obtenirNormativa(final NormativaCriteria normativaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirNormativa(normativaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<NormativaDTO> llistarNormatives(final NormativaCriteria normativaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarNormatives(normativaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public PersonalDTO obtenirPersonal(final PersonalCriteria personalCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirPersonal(personalCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PersonalDTO> llistarPersonal(final PersonalCriteria personalCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarPersonal(personalCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public DocumentTramitDTO obtenirDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirDocumentTramit(documentTramitCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocumentTramitDTO> llistarDocumentTramit(final DocumentTramitCriteria documentTramitCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarDocumentTramit(documentTramitCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<DocumentoNormativaDTO> llistarDocumentoNormativa(
			final DocumentoNormativaCriteria documentoNormativaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarDocumentoNormativa(documentoNormativaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<DocumentoServicioDTO> llistarDocumentoServicio(
			final DocumentoServicioCriteria documentoServicioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarDocumentoServicio(documentoServicioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public UsuariDTO obtenirUsuari(final UsuariCriteria usuariCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirUsuari(usuariCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<UsuariDTO> llistarUsuaris(final UsuariCriteria usuariCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarUsuaris(usuariCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public IdiomaDTO obtenirIdioma(final IdiomaCriteria idiomaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirIdioma(idiomaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<IdiomaDTO> llistarIdiomes(final IdiomaCriteria idiomaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarIdiomes(idiomaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public TaxaDTO obtenirTaxa(final TaxaCriteria taxaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirTaxa(taxaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TaxaDTO> llistarTaxes(final TaxaCriteria taxaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarTaxes(taxaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(final AgrupacioFetVitalCriteria afvCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirAgrupacioFetVital(afvCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(final AgrupacioFetVitalCriteria afvCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarAgrupacionsFetsVitals(afvCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public AgrupacioMateriaDTO obtenirAgrupacioMateria(final AgrupacioMateriaCriteria amCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirAgrupacioMateria(amCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(final AgrupacioMateriaCriteria amCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarAgrupacionsMateries(amCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ButlletiDTO obtenirButlleti(final ButlletiCriteria butlletiCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirButlleti(butlletiCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ButlletiDTO> llistarButlletins(final ButlletiCriteria butlletiCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarButlletins(butlletiCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public DocumentDTO obtenirDocument(final DocumentCriteria docCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirDocument(docCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<DocumentDTO> llistarDocuments(final DocumentCriteria docCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarDocuments(docCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public EdificiDTO obtenirEdifici(final EdificiCriteria edificiCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirEdifici(edificiCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<EdificiDTO> llistarEdificis(final EdificiCriteria edificiCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarEdificis(edificiCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public EnllacDTO obtenirEnllac(final EnllacCriteria enllacCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirEnllac(enllacCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public EnllacTelematicoDTO obtenirEnllacTelematico(final EnllacTelematicoCriteria enllacCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirEnllacTelematico(enllacCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<EnllacDTO> llistarEnllacos(final EnllacCriteria enllacCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarEnllacos(enllacCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public EspaiTerritorialDTO obtenirEspaiTerritorial(final EspaiTerritorialCriteria etCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirEspaiTerritorial(etCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(final EspaiTerritorialCriteria etCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarEspaisTerritorials(etCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public FamiliaDTO obtenirFamilia(final FamiliaCriteria familiaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirFamilia(familiaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<FamiliaDTO> llistarFamilies(final FamiliaCriteria familiaCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarFamilies(familiaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public PublicObjectiuDTO obtenirPublicObjectiu(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirPublicObjectiu(publicObjectiuCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PublicObjectiuDTO> llistarPublicsObjectius(final PublicObjectiuCriteria publicObjectiuCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarPublicsObjectius(publicObjectiuCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public FitxaUADTO obtenirFitxaUA(final FitxaUACriteria fitxaUACriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirFitxaUA(fitxaUACriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<FitxaUADTO> llistarFitxesUA(final FitxaUACriteria fitxaUACriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarFitxesUA(fitxaUACriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public FormulariDTO obtenirFormulari(final FormulariCriteria formulariCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirFormulari(formulariCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<FormulariDTO> llistarFormularis(final FormulariCriteria formulariCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarFormularis(formulariCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public IconaFamiliaDTO obtenirIconaFamilia(final IconaFamiliaCriteria ifCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirIconaFamilia(ifCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<IconaFamiliaDTO> llistarIconesFamilies(final IconaFamiliaCriteria ifCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarIconesFamilies(ifCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public IconaMateriaDTO obtenirIconaMateria(final IconaMateriaCriteria imCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirIconaMateria(imCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<IconaMateriaDTO> llistarIconesMateries(final IconaMateriaCriteria imCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarIconesMateries(imCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(final MateriaAgrupacioCriteria maCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirMateriaAgrupacio(maCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(final MateriaAgrupacioCriteria maCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarMateriesAgrupacions(maCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public PerfilDTO obtenirPerfil(final PerfilCriteria perfilCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirPerfil(perfilCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PerfilDTO> llistarPerfils(final PerfilCriteria perfilCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarPerfils(perfilCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public SeccioDTO obtenirSeccio(final SeccioCriteria seccioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirSeccio(seccioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SeccioDTO> llistarSeccions(final SeccioCriteria seccioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarSeccions(seccioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public UnitatMateriaDTO obtenirUnitatMateria(final UnitatMateriaCriteria umCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirUnitatMateria(umCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<UnitatMateriaDTO> llistarUnitatsMateries(final UnitatMateriaCriteria umCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarUnitatsMateries(umCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public TipusDTO obtenirTipus(final TipusCriteria tipusCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirTipus(tipusCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipusDTO> llistarTipus(final TipusCriteria tipusCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarTipus(tipusCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public TipusAfectacioDTO obtenirTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirTipusAfectacio(tipusAfectacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipusAfectacioDTO> llistarTipusAfectacio(final TipusAfectacioCriteria tipusAfectacioCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarTipusAfectacio(tipusAfectacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<IniciacioDTO> llistarTipusIniciacions(final IniciacioCriteria iniciacioCriteria)
			throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.llistarTipusIniciacions(iniciacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public IniciacioDTO obtenirTipusIniciacio(final IniciacioCriteria iniciacioCriteria) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirTipusIniciacio(iniciacioCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	@SuppressWarnings("unchecked")
	public int getNumFitxes(final FitxaCriteria fitxaCriteria) throws DelegateException {

		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.getNumFitxes(fitxaCriteria);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public SilencioDTO obtenirSilenci(final Long codSilencio, final String idioma) throws DelegateException {
		try {
			final RolsacQueryServiceEJBRemote ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
			return ejb.obtenirSilenci(codSilencio, idioma);
		} catch (final LocatorException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
		} catch (final RemoteException e) {
			throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

}
