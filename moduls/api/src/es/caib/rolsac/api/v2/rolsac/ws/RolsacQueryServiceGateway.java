package es.caib.rolsac.api.v2.rolsac.ws;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import org.apache.axis.AxisFault;

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
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.APIException;
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
import es.caib.rolsac.api.v2.general.ConfiguracioServeis;
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
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
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

public class RolsacQueryServiceGateway {

	RolsacWSSoapBindingStub stub;

	public RolsacQueryServiceGateway() {
		
		try {
			stub = new RolsacWSSoapBindingStub(
				new URL(ConfiguracioServeis.getUrlServei(ConfiguracioServeis.NOM_SERVEI_ROLSAC)),
				null
			);
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setUrl(String url) {
		try {
			if(url != null && !url.isEmpty()){				
				stub = new RolsacWSSoapBindingStub(
						new URL(url + ConfiguracioServeis.NOM_SERVEI_ROLSAC),
						null
						);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
	
	public ServicioDTO obtenirServicio(
			ServicioCriteria servicioCriteria)
			throws QueryServiceException, RemoteException {

		ServicioDTO servicioDTO = new ServicioDTO();
		servicioDTO = stub.obtenirServicio(servicioCriteria);

		return servicioDTO;
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

	public CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria )
			throws QueryServiceException, RemoteException {

		CatalegDocumentsDTO catalegDocumentsDTO = new CatalegDocumentsDTO();
		catalegDocumentsDTO = stub.obtenirCatalegDocuments(catalegDocumentsCriteria);

		return catalegDocumentsDTO;
	}
	
	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacioDTO(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria )
			throws QueryServiceException, RemoteException {

		ExcepcioDocumentacioDTO excepcioDocumentacioDTO = new ExcepcioDocumentacioDTO();
		excepcioDocumentacioDTO = stub.obtenirExcepcioDocumentacio(excepcioDocumentacioCriteria);

		return excepcioDocumentacioDTO;
	}
	
	public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException, RemoteException {

		IniciacioDTO iniciacioDTO = new IniciacioDTO();
		iniciacioDTO = stub.obtenirTipusIniciacio(iniciacioCriteria);

		return iniciacioDTO;
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws QueryServiceException, RemoteException {

		return Arrays.asList(stub.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria));
	}

	public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarAgrupacionsMateries(agrupacioMateriaCriteria));
	}

	public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarButlletins(butlletiCriteria));
	}

	public List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria)
			throws QueryServiceException, RemoteException {

		return Arrays.asList(stub.llistarDocuments(documentCriteria));
	}

	public List<DocumentTramitDTO> llistarDocumentTramit(
			DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarDocumentTramit(documentTramitCriteria));
	}
	
    public List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria idNormativa) throws QueryServiceException, RemoteException {
    	return Arrays.asList(stub.llistarDocumentoNormativa(idNormativa));
    }

	public List<EdificiDTO> llistarEdificis(EdificiCriteria edificCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarEdificis(edificCriteria));
	}

	public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarEnllacos(enllacCriteria));
	}

	public List<EspaiTerritorialDTO> llistarEspaisTerritorials(
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarEspaisTerritorials(espaiTerritorialCriteria));
	}

	public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarFamilies(familiaCriteria));
	}

	public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarFetsVitals(fetVitalCriteria));
	}

	public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarFitxes(fitxaCriteria));
	}

	public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria)
			throws QueryServiceException, RemoteException {
	
		return Arrays.asList(stub.llistarFitxesUA(fitxaUACriteria));
	}

	public List<FormulariDTO> llistarFormularis(
			FormulariCriteria formulariCriteria) throws QueryServiceException,
			RemoteException {
		
		return Arrays.asList(stub.llistarFormularis(formulariCriteria));
	}

	public List<IconaFamiliaDTO> llistarIconesFamilies(
			IconaFamiliaCriteria iconaFamiliaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarIconesFamilies(iconaFamiliaCriteria));
	}

	public List<IconaMateriaDTO> llistarIconesMateries(
			IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarIconesMateries(iconaMateriaCriteria));
		
	}

	public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarMateries(materiaCriteria));
		
	}

	public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarMateriesAgrupacions(materiaAgrupacioCriteria));
	}

	public List<NormativaDTO> llistarNormatives(
			NormativaCriteria normativaCriteria) throws QueryServiceException,
			RemoteException {
		
		return Arrays.asList(stub.llistarNormatives(normativaCriteria));
	}

	public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarPerfils(perfilCriteria));
	}

	public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarPersonal(personalCriteria));
	}

	public List<ProcedimentDTO> llistarProcediments(
			ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarProcediments(procedimentCriteria));	
	}

	public int getNumProcediments(
            ProcedimentCriteria procedimentCriteria)
            throws QueryServiceException, RemoteException {
        
        return stub.getNumProcediments(procedimentCriteria);    
    }
	
	public List<ServicioDTO> llistarServicios(
			ServicioCriteria servicioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarServicios(servicioCriteria));	
	}

	public int getNumServicios(
			ServicioCriteria servicioCriteria)
            throws QueryServiceException, RemoteException {
        
        return stub.getNumServicios(servicioCriteria);    
    }
	
	public List<PublicObjectiuDTO> llistarPublicsObjectius(
			PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarPublicsObjectius(publicObjectiuCriteria));
		
	}

	public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarSeccions(seccioCriteria));
	}

	public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarTaxes(taxaCriteria));
	}

	public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarTramit(tramitCriteria));
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarUnitatsAdministratives(unitatAdministrativaCriteria));
	}

	public List<UnitatMateriaDTO> llistarUnitatsMateries(
			UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarUnitatsMateries(unitatMateriaCriteria));
	}

	public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarUsuaris(usuariCriteria));
	}
	
	public IdiomaDTO obtenirIdioma(IdiomaCriteria idiomaCriteria)
			throws QueryServiceException, RemoteException {

		IdiomaDTO idiomaDTO = new IdiomaDTO();
		idiomaDTO = stub.obtenirIdioma(idiomaCriteria);

		return idiomaDTO;
	}
	
	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria)
			throws QueryServiceException, RemoteException {
		
		List<IdiomaDTO> listaIdiomas = Arrays.asList(stub.llistarIdiomes(idiomaCriteria));
		return listaIdiomas;
	}

	public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarTipus(tipusCriteria));
	}

	public List<TipusAfectacioDTO> llistarTipusAfectacio(
			TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarTipusAfectacio(tipusAfectacioCriteria));
	}
	
	public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(
			ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria));
	}
	
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(
			CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarCatalegsDocuments(catalegDocumentsCriteria));
	}
	
	public List<IniciacioDTO> llistarTipusIniciacions(
			IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException, RemoteException {
		
		return Arrays.asList(stub.llistarTipusIniciacions(iniciacioCriteria));
	}

    public int getNumFitxes(
            FitxaCriteria fitxaCriteria)
            throws QueryServiceException, RemoteException {

        return stub.getNumFitxes(fitxaCriteria);
    }

	public SilencioDTO obtenirSilenci(Long codSilencio, String idioma)  throws QueryServiceException, RemoteException {
		
		return stub.obtenirSilenci(codSilencio, idioma);
	}
	
}
