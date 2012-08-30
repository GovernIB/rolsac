package es.caib.rolsac.api.v2.rolsac.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import localhost.sacws_api.services.RolsacWS.RolsacQueryServiceEJBRemoteServiceLocator;
import localhost.sacws_api.services.RolsacWS.RolsacWSSoapBindingStub;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
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
import es.caib.rolsac.api.v2.general.DTOUtil;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
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
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
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

public class RolsacQueryServiceGateway {

	RolsacWSSoapBindingStub stub;
	RolsacQueryServiceEJBRemoteServiceLocator locator;

	public RolsacQueryServiceGateway() {
		try {
			locator = new RolsacQueryServiceEJBRemoteServiceLocator();

			stub = new RolsacWSSoapBindingStub(new URL(
					locator.getRolsacWSAddress()), null);

		} catch (RemoteException re) {
		} catch (MalformedURLException mue) {
		}
	}

	public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria)
			throws QueryServiceException, RemoteException {

		UsuariDTO usuariDTO = new UsuariDTO();
		usuariDTO = stub.obtenirUsuari(usuariCriteria);

		return usuariDTO;
	}

	public TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException, RemoteException {

		TipusAfectacioDTO tipusAfectacioDTO = new TipusAfectacioDTO();
		tipusAfectacioDTO = stub.obtenirTipusAfectacio(tipusAfectacioCriteria);

		return tipusAfectacioDTO;
	}
	
	public UnitatMateriaDTO obtenirUnitatMateria(
			UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException, RemoteException {

		UnitatMateriaDTO unitatMateriaDTO = new UnitatMateriaDTO();
		unitatMateriaDTO = stub.obtenirUnitatMateria(unitatMateriaCriteria);

		return unitatMateriaDTO;
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws QueryServiceException, RemoteException {

		UnitatAdministrativaDTO unitatAdministrativaDTO = new UnitatAdministrativaDTO();
		unitatAdministrativaDTO = stub
				.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);

		return unitatAdministrativaDTO;
	}

	public TramitDTO obtenirTramit(TramitCriteria tramitCriteria)
			throws QueryServiceException, RemoteException {

		TramitDTO tramitDTO = new TramitDTO();
		tramitDTO = stub.obtenirTramit(tramitCriteria);

		return tramitDTO;
	}

	public TipusDTO obtenirTipus(TipusCriteria tipusCriteria)
			throws QueryServiceException, RemoteException {

		TipusDTO tipusDTO = new TipusDTO();
		tipusDTO = stub.obtenirTipus(tipusCriteria);

		return tipusDTO;
	}

	public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria)
			throws QueryServiceException, RemoteException {

		SeccioDTO seccioDTO = new SeccioDTO();
		seccioDTO = stub.obtenirSeccio(seccioCriteria);

		return seccioDTO;
	}

	public PublicObjectiuDTO obtenirPublicObjectiu(
			PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException, RemoteException {

		PublicObjectiuDTO publicObjectiuDTO = new PublicObjectiuDTO();
		publicObjectiuDTO = stub.obtenirPublicObjectiu(publicObjectiuCriteria);

		return publicObjectiuDTO;
	}

	public ProcedimentDTO obtenirProcediment(
			ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException, RemoteException {

		ProcedimentDTO procedimentDTO = new ProcedimentDTO();
		procedimentDTO = stub.obtenirProcediment(procedimentCriteria);

		return procedimentDTO;
	}

	public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria)
			throws QueryServiceException, RemoteException {

		PersonalDTO personalDTO = new PersonalDTO();
		personalDTO = stub.obtenirPersonal(personalCriteria);

		return personalDTO;
	}

	public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria)
			throws QueryServiceException, RemoteException {

		PerfilDTO perfilDTO = new PerfilDTO();
		perfilDTO = stub.obtenirPerfil(perfilCriteria);

		return perfilDTO;
	}

	public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria)
			throws QueryServiceException, RemoteException {

		NormativaDTO normativaDTO = new NormativaDTO();
		normativaDTO = stub.obtenirNormativa(normativaCriteria);

		return normativaDTO;
	}

	public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria)
			throws QueryServiceException, RemoteException {

		MateriaDTO materiaDTO = new MateriaDTO();
		materiaDTO = stub.obtenirMateria(materiaCriteria);

		return materiaDTO;
	}

	public MateriaAgrupacioDTO obtenirMateriaAgrupacio(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws QueryServiceException, RemoteException {

		MateriaAgrupacioDTO materiaAgrupacioDTO = new MateriaAgrupacioDTO();
		materiaAgrupacioDTO = stub
				.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);

		return materiaAgrupacioDTO;
	}

	public IconaMateriaDTO obtenirIconaMateria(
			IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException, RemoteException {

		IconaMateriaDTO iconaMateriaDTO = new IconaMateriaDTO();
		iconaMateriaDTO = stub.obtenirIconaMateria(iconaMateriaCriteria);

		return iconaMateriaDTO;
	}

	public IconaFamiliaDTO obtenirIconaFamilia(
			IconaFamiliaCriteria iconaFamiliaCritera)
			throws QueryServiceException, RemoteException {

		IconaFamiliaDTO iconaFamiliaDTO = new IconaFamiliaDTO();
		iconaFamiliaDTO = stub.obtenirIconaFamilia(iconaFamiliaCritera);

		return iconaFamiliaDTO;
	}

	public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria)
			throws QueryServiceException, RemoteException {

		FetVitalDTO fetVitalDTO = new FetVitalDTO();
		fetVitalDTO = stub.obtenirFetVital(fetVitalCriteria);

		return fetVitalDTO;
	}

	public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws QueryServiceException, RemoteException {

		AgrupacioFetVitalDTO agrupacioFetVitalDTO = new AgrupacioFetVitalDTO();
		agrupacioFetVitalDTO = stub
				.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);

		return agrupacioFetVitalDTO;
	}

	public AgrupacioMateriaDTO obtenirAgrupacioMateria(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws QueryServiceException, RemoteException {

		AgrupacioMateriaDTO agrupacioMateriaDTO = new AgrupacioMateriaDTO();
		agrupacioMateriaDTO = stub
				.obtenirAgrupacioMateria(agrupacioMateriaCriteria);

		return agrupacioMateriaDTO;
	}

	public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria)
			throws QueryServiceException, RemoteException {

		ButlletiDTO butlletiDTO = new ButlletiDTO();
		butlletiDTO = stub.obtenirButlleti(butlletiCriteria);

		return butlletiDTO;
	}

	public DocumentDTO obtenirDocument(DocumentCriteria documentCriteria)
			throws QueryServiceException, RemoteException {

		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO = stub.obtenirDocument(documentCriteria);

		return documentDTO;
	}

	public DocumentTramitDTO obtenirDocumentTramit(
			DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException, RemoteException {

		DocumentTramitDTO documentTramitDTO = new DocumentTramitDTO();
		documentTramitDTO = stub.obtenirDocumentTramit(documentTramitCriteria);

		return documentTramitDTO;
	}

	public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria)
			throws QueryServiceException, RemoteException {

		EdificiDTO edificiDTO = new EdificiDTO();
		edificiDTO = stub.obtenirEdifici(edificiCriteria);

		return edificiDTO;
	}

	public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria)
			throws QueryServiceException, RemoteException {

		EnllacDTO enllacDTO = new EnllacDTO();
		enllacDTO = stub.obtenirEnllac(enllacCriteria);

		return enllacDTO;
	}

	public EspaiTerritorialDTO obtenirEspaiTerritorial(
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws QueryServiceException, RemoteException {

		EspaiTerritorialDTO espaiTerritorialDTO = new EspaiTerritorialDTO();
		espaiTerritorialDTO = stub
				.obtenirEspaiTerritorial(espaiTerritorialCriteria);

		return espaiTerritorialDTO;
	}

	public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria)
			throws QueryServiceException, RemoteException {

		FamiliaDTO familiaDTO = new FamiliaDTO();
		familiaDTO = stub.obtenirFamilia(familiaCriteria);

		return familiaDTO;
	}

	public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria)
			throws QueryServiceException, RemoteException {

		FitxaDTO fitxaDTO = new FitxaDTO();
		fitxaDTO = stub.obtenirFitxa(fitxaCriteria);

		return fitxaDTO;
	}

	public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria)
			throws QueryServiceException, RemoteException {

		FitxaUADTO fitxaUADTO = new FitxaUADTO();
		fitxaUADTO = stub.obtenirFitxaUA(fitxaUACriteria);

		return fitxaUADTO;
	}

	public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria)
			throws QueryServiceException, RemoteException {

		FormulariDTO formulariDTO = new FormulariDTO();
		formulariDTO = stub.obtenirFormulari(formulariCriteria);

		return formulariDTO;
	}

	public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria)
			throws QueryServiceException, RemoteException {

		TaxaDTO taxaDTO = new TaxaDTO();
		taxaDTO = stub.obtenirTaxa(taxaCriteria);

		return taxaDTO;
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws QueryServiceException, RemoteException {

		Object[] tmpLlista = null;
		List<AgrupacioFetVitalDTO> llistaAgrupacionsFetsVitals = null;
		
		tmpLlista = stub.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
		llistaAgrupacionsFetsVitals = new ArrayList<AgrupacioFetVitalDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			AgrupacioFetVitalDTO afvdto = (AgrupacioFetVitalDTO) DTOUtil.object2DTO(o, AgrupacioFetVitalDTO.class);							
			llistaAgrupacionsFetsVitals.add( afvdto );
		}
		return llistaAgrupacionsFetsVitals;
	}

	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<AgrupacioMateriaDTO> llistaAgrupacionsMateries = null;
		
		tmpLlista = stub.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
		llistaAgrupacionsMateries = new ArrayList<AgrupacioMateriaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			AgrupacioMateriaDTO amdto = (AgrupacioMateriaDTO) DTOUtil.object2DTO(o, AgrupacioMateriaDTO.class);							
			llistaAgrupacionsMateries.add( amdto );
		}
		return llistaAgrupacionsMateries;
	}

	public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<ButlletiDTO> llistaButlletins = null;
		
		tmpLlista = stub.llistarButlletins(butlletiCriteria);
		llistaButlletins = new ArrayList<ButlletiDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			ButlletiDTO bdto = (ButlletiDTO) DTOUtil.object2DTO(o, ButlletiDTO.class);							
			llistaButlletins.add( bdto );
		}
		return llistaButlletins;
	}

	public List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria)
			throws QueryServiceException, RemoteException {

		Object[] tmpLlista = null;
		List<DocumentDTO> llistaDocuments = null;
		
		tmpLlista = stub.llistarDocuments(documentCriteria);
		llistaDocuments = new ArrayList<DocumentDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			DocumentDTO ddto = (DocumentDTO) DTOUtil.object2DTO(o, DocumentDTO.class);							
			llistaDocuments.add( ddto );
		}
		return llistaDocuments;
		
	}

	public List<DocumentTramitDTO> llistarDocumentTramit(
			DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<DocumentTramitDTO> llistaDocumentsTramits = null;
		
		tmpLlista = stub.llistarDocumentTramit(documentTramitCriteria);
		llistaDocumentsTramits = new ArrayList<DocumentTramitDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			DocumentTramitDTO dtdto = (DocumentTramitDTO) DTOUtil.object2DTO(o, DocumentTramitDTO.class);							
			llistaDocumentsTramits.add( dtdto );
		}
		return llistaDocumentsTramits;
	}

	public List<EdificiDTO> llistarEdificis(EdificiCriteria edificCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<EdificiDTO> llistarEdificis = null;
		
		tmpLlista = stub.llistarEdificis(edificCriteria);
		llistarEdificis = new ArrayList<EdificiDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			EdificiDTO dtdto = (EdificiDTO) DTOUtil.object2DTO(o, EdificiDTO.class);							
			llistarEdificis.add( dtdto );
		}
		
		return llistarEdificis;
	}

	public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<EnllacDTO> llistaEnllacos = null;
		
		tmpLlista = stub.llistarEnllacos(enllacCriteria);
		llistaEnllacos = new ArrayList<EnllacDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			EnllacDTO dtdto = (EnllacDTO) DTOUtil.object2DTO(o, EnllacDTO.class);							
			llistaEnllacos.add( dtdto );
		}
		
		return llistaEnllacos;
	}

	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<EspaiTerritorialDTO> llistaEspaisTerritorials = null;
		
		tmpLlista = stub.llistarEspaisTerritorials(espaiTerritorialCriteria);
		llistaEspaisTerritorials = new ArrayList<EspaiTerritorialDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			EspaiTerritorialDTO etdto = (EspaiTerritorialDTO) DTOUtil.object2DTO(o, EspaiTerritorialDTO.class);							
			llistaEspaisTerritorials.add( etdto );
		}
		
		return llistaEspaisTerritorials;		
	}

	public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<FamiliaDTO> llistaFamilies = null;
		
		tmpLlista = stub.llistarFamilies(familiaCriteria);
		llistaFamilies = new ArrayList<FamiliaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FamiliaDTO fdto = (FamiliaDTO) DTOUtil.object2DTO(o, FamiliaDTO.class);							
			llistaFamilies.add( fdto );
		}
		
		return llistaFamilies;		
	}

	public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<FetVitalDTO> llistaFamilies = null;
		
		tmpLlista = stub.llistarFetsVitals(fetVitalCriteria);
		llistaFamilies = new ArrayList<FetVitalDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FetVitalDTO fdto = (FetVitalDTO) DTOUtil.object2DTO(o, FamiliaDTO.class);							
			llistaFamilies.add( fdto );
		}
		
		return llistaFamilies;		
	}

	public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<FitxaDTO> llistaFitxes = null;
		
		tmpLlista = stub.llistarFitxes(fitxaCriteria);
		llistaFitxes = new ArrayList<FitxaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FitxaDTO fdto = (FitxaDTO) DTOUtil.object2DTO(o, FitxaDTO.class);							
			llistaFitxes.add( fdto );
		}
		
		return llistaFitxes;		
	}

	public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<FitxaUADTO> llistaFitxesUA = null;
		
		tmpLlista = stub.llistarFitxesUA(fitxaUACriteria);
		llistaFitxesUA = new ArrayList<FitxaUADTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FitxaUADTO fuadto = (FitxaUADTO) DTOUtil.object2DTO(o, FitxaUADTO.class);							
			llistaFitxesUA.add( fuadto );
		}
		
		return llistaFitxesUA;		
	}

	public List<FormulariDTO> llistarFormularis(
			FormulariCriteria formulariCriteria) throws QueryServiceException,
			RemoteException {
		Object[] tmpLlista = null;
		List<FormulariDTO> llistaFormularis = null;
		
		tmpLlista = stub.llistarFormularis(formulariCriteria);
		llistaFormularis = new ArrayList<FormulariDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			FormulariDTO fdto = (FormulariDTO) DTOUtil.object2DTO(o, FormulariDTO.class);							
			llistaFormularis.add( fdto );
		}
		
		return llistaFormularis;		
	}

	public List<IconaFamiliaDTO> llistarIconesFamilies(
			IconaFamiliaCriteria iconaFamiliaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<IconaFamiliaDTO> llistaIconesFamilies = null;
		
		tmpLlista = stub.llistarIconesFamilies(iconaFamiliaCriteria);
		llistaIconesFamilies = new ArrayList<IconaFamiliaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			IconaFamiliaDTO fdto = (IconaFamiliaDTO) DTOUtil.object2DTO(o, IconaFamiliaDTO.class);							
			llistaIconesFamilies.add( fdto );
		}
		
		return llistaIconesFamilies;		
	}

	public List<IconaMateriaDTO> llistarIconesMateries(
			IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<IconaMateriaDTO> llistaIconesMateries = null;
		
		tmpLlista = stub.llistarIconesMateries(iconaMateriaCriteria);
		llistaIconesMateries = new ArrayList<IconaMateriaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			IconaMateriaDTO fdto = (IconaMateriaDTO) DTOUtil.object2DTO(o, IconaMateriaDTO.class);							
			llistaIconesMateries.add( fdto );
		}
		
		return llistaIconesMateries;		
		
	}

	public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<MateriaDTO> llistaMateries = null;
		
		tmpLlista = stub.llistarMateries(materiaCriteria);
		llistaMateries = new ArrayList<MateriaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			MateriaDTO mdto = (MateriaDTO) DTOUtil.object2DTO(o, MateriaDTO.class);							
			llistaMateries.add( mdto );
		}
		
		return llistaMateries;		
		
	}

	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<MateriaAgrupacioDTO> llistaMateriesAgrupacions = null;
		
		tmpLlista = stub.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
		llistaMateriesAgrupacions = new ArrayList<MateriaAgrupacioDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			MateriaAgrupacioDTO madto = (MateriaAgrupacioDTO) DTOUtil.object2DTO(o, MateriaAgrupacioDTO.class);							
			llistaMateriesAgrupacions.add( madto );
		}
		
		return llistaMateriesAgrupacions;		
	}

	public List<NormativaDTO> llistarNormatives(
			NormativaCriteria normativaCriteria) throws QueryServiceException,
			RemoteException {
		
		Object[] tmpLlista = null;
		List<NormativaDTO> llistaNormatives = null;
		
		tmpLlista = stub.llistarNormatives(normativaCriteria);
		llistaNormatives = new ArrayList<NormativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			NormativaDTO ndto = (NormativaDTO) DTOUtil.object2DTO(o, NormativaDTO.class);							
			llistaNormatives.add( ndto );
		}
		
		return llistaNormatives;		
	}

	public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<PerfilDTO> llistaPerfils = null;
		
		tmpLlista = stub.llistarPerfils(perfilCriteria);
		llistaPerfils = new ArrayList<PerfilDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			PerfilDTO pdto = (PerfilDTO) DTOUtil.object2DTO(o, PerfilDTO.class);							
			llistaPerfils.add( pdto );
		}
		
		return llistaPerfils;		
	}

	public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<PersonalDTO> llistaPersonal = null;
		
		tmpLlista = stub.llistarPersonal(personalCriteria);
		llistaPersonal = new ArrayList<PersonalDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			PersonalDTO pdto = (PersonalDTO) DTOUtil.object2DTO(o, PersonalDTO.class);							
			llistaPersonal.add( pdto );
		}
		
		return llistaPersonal;		
	}

	public List<ProcedimentDTO> llistarProcediments(
			ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<ProcedimentDTO> llistaProcediments = null;
		
		tmpLlista = stub.llistarProcediments(procedimentCriteria);
		llistaProcediments = new ArrayList<ProcedimentDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			ProcedimentDTO pdto = (ProcedimentDTO) DTOUtil.object2DTO(o, ProcedimentDTO.class);							
			llistaProcediments.add( pdto );
		}
		
		return llistaProcediments;		
	}

	public List<PublicObjectiuDTO> llistarPublicsObjectius(
			PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<PublicObjectiuDTO> llistaPublicsObjectius = null;
		
		tmpLlista = stub.llistarPublicsObjectius(publicObjectiuCriteria);
		llistaPublicsObjectius = new ArrayList<PublicObjectiuDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			PublicObjectiuDTO podto = (PublicObjectiuDTO) DTOUtil.object2DTO(o, PublicObjectiuDTO.class);							
			llistaPublicsObjectius.add( podto );
		}
		
		return llistaPublicsObjectius;		
		
	}

	public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<SeccioDTO> llistaSeccions = null;
		
		tmpLlista = stub.llistarSeccions(seccioCriteria);
		llistaSeccions = new ArrayList<SeccioDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			SeccioDTO sdto = (SeccioDTO) DTOUtil.object2DTO(o, SeccioDTO.class);							
			llistaSeccions.add( sdto );
		}
		
		return llistaSeccions;		
	}

	public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<TaxaDTO> llistaTaxes = null;
		
		tmpLlista = stub.llistarTaxes(taxaCriteria);
		llistaTaxes = new ArrayList<TaxaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			TaxaDTO tdto = (TaxaDTO) DTOUtil.object2DTO(o, TaxaDTO.class);							
			llistaTaxes.add( tdto );
		}
		
		return llistaTaxes;		

	}

	public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria)
			throws QueryServiceException, RemoteException {
		Object[] tmpLlista = null;
		List<TramitDTO> llistaTaxes = null;
		
		tmpLlista = stub.llistarTramit(tramitCriteria);
		llistaTaxes = new ArrayList<TramitDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			TramitDTO tdto = (TramitDTO) DTOUtil.object2DTO(o, TramitDTO.class);							
			llistaTaxes.add( tdto );
		}
		
		return llistaTaxes;		
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<UnitatAdministrativaDTO> llistaUnitatsAdministratives = null;
		
		tmpLlista = stub.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
		llistaUnitatsAdministratives = new ArrayList<UnitatAdministrativaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			UnitatAdministrativaDTO tdto = (UnitatAdministrativaDTO) DTOUtil.object2DTO(o, UnitatAdministrativaDTO.class);							
			llistaUnitatsAdministratives.add( tdto );
		}
		
		return llistaUnitatsAdministratives;		
	}

	public List<UnitatMateriaDTO> llistarUnitatsMateries(
			UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<UnitatMateriaDTO> llistaUnitatsMateries = null;
		
		tmpLlista = stub.llistarUnitatsMateries(unitatMateriaCriteria);
		llistaUnitatsMateries = new ArrayList<UnitatMateriaDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			UnitatMateriaDTO umdto = (UnitatMateriaDTO) DTOUtil.object2DTO(o, UnitatMateriaDTO.class);							
			llistaUnitatsMateries.add( umdto );
		}
		
		return llistaUnitatsMateries;
	}

	public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<UsuariDTO> llistaUsuaris = null;
		
		tmpLlista = stub.llistarUsuaris(usuariCriteria);
		llistaUsuaris = new ArrayList<UsuariDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			UsuariDTO umdto = (UsuariDTO) DTOUtil.object2DTO(o, UsuariDTO.class);							
			llistaUsuaris.add( umdto );
		}
		
		return llistaUsuaris;
	}

	public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<TipusDTO> llistaTipus = null;
		
		tmpLlista = stub.llistarTipus(tipusCriteria);
		llistaTipus = new ArrayList<TipusDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			TipusDTO tdto = (TipusDTO) DTOUtil.object2DTO(o, TipusDTO.class);							
			llistaTipus.add( tdto );
		}
		
		return llistaTipus;
		
	}

	public List<TipusAfectacioDTO> llistarTipusAfectacio(
			TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException, RemoteException {
		
		Object[] tmpLlista = null;
		List<TipusAfectacioDTO> llistaTipusAfectacio = null;
		
		tmpLlista = stub.llistarTipusAfectacio(tipusAfectacioCriteria);
		llistaTipusAfectacio = new ArrayList<TipusAfectacioDTO>( Arrays.asList(tmpLlista).size() );
		
		for (Object o : tmpLlista) { 				
			TipusAfectacioDTO tdto = (TipusAfectacioDTO) DTOUtil.object2DTO(o, TipusAfectacioDTO.class);							
			llistaTipusAfectacio.add( tdto );
		}
		
		return llistaTipusAfectacio;
	}
}
