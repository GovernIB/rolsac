package es.caib.rolsac.api.v2.rolsac;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaCriteria;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioCriteria;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoCriteria;
import es.caib.rolsac.api.v2.enllactelematico.EnllacTelematicoQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.plantilla.PlantillaCriteria;
import es.caib.rolsac.api.v2.plantilla.PlantillaQueryServiceAdapter;
import es.caib.rolsac.api.v2.plataforma.PlataformaCriteria;
import es.caib.rolsac.api.v2.plataforma.PlataformaQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.silencio.SilencioQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public interface RolsacQueryService {

	AgrupacioFetVitalQueryServiceAdapter obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws QueryServiceException;

	List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacionsFetsVitals(
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws QueryServiceException;

	AgrupacioMateriaQueryServiceAdapter obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria)
			throws QueryServiceException;

	List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacionsMateries(
			AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws QueryServiceException;

	ButlletiQueryServiceAdapter obtenirButlleti(ButlletiCriteria butlletiCriteria) throws QueryServiceException;

	List<ButlletiQueryServiceAdapter> llistarButlletins(ButlletiCriteria butlletiCriteria) throws QueryServiceException;

	CatalegDocumentsQueryServiceAdapter obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws QueryServiceException;

	List<CatalegDocumentsQueryServiceAdapter> llistarCatalegsDocuments(
			CatalegDocumentsCriteria catalegDocumentsCriteria) throws QueryServiceException;

	DocumentQueryServiceAdapter obtenirDocument(DocumentCriteria documentCriteria) throws QueryServiceException;

	List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) throws QueryServiceException;

	DocumentTramitQueryServiceAdapter obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException;

	List<DocumentTramitQueryServiceAdapter> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException;

	List<DocumentoNormativaQueryServiceAdapter> llistarDocumentoNormativa(
			DocumentoNormativaCriteria documentoNormativaCriteria) throws QueryServiceException;

	List<DocumentoServicioQueryServiceAdapter> llistarDocumentoServicio(
			DocumentoServicioCriteria documentoServicioCriteria) throws QueryServiceException;

	EdificiQueryServiceAdapter obtenirEdifici(EdificiCriteria edificiTramit) throws QueryServiceException;

	List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiTramit) throws QueryServiceException;

	EnllacQueryServiceAdapter obtenirEnllac(EnllacCriteria enllacCriteria) throws QueryServiceException;

	EnllacTelematicoQueryServiceAdapter obtenirEnllacTelematico(EnllacTelematicoCriteria enllacCriteria)
			throws QueryServiceException;

	List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) throws QueryServiceException;

	EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws QueryServiceException;

	List<EspaiTerritorialQueryServiceAdapter> llistarEspaisTerritorials(
			EspaiTerritorialCriteria espaiTerritorialCriteria) throws QueryServiceException;

	ExcepcioDocumentacioQueryServiceAdapter obtenirExcepcioDocumentacio(
			ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws QueryServiceException;

	List<ExcepcioDocumentacioQueryServiceAdapter> llistarExcepcionsDocumentacio(
			ExcepcioDocumentacioCriteria excepcioDocumentacioCritera) throws QueryServiceException;

	FamiliaQueryServiceAdapter obtenirFamilia(FamiliaCriteria familiaCriteria) throws QueryServiceException;

	List<FamiliaQueryServiceAdapter> llistarFamilies(FamiliaCriteria familiaCriteria) throws QueryServiceException;

	FetVitalQueryServiceAdapter obtenirFetVital(FetVitalCriteria fetVitalCriteria) throws QueryServiceException;

	PlataformaQueryServiceAdapter obtenirPlataforma(PlataformaCriteria plataformaCriteria) throws QueryServiceException;

	PlantillaQueryServiceAdapter obtenirPlantilla(PlantillaCriteria plantillaCriteria) throws QueryServiceException;

	List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws QueryServiceException;

	FitxaQueryServiceAdapter obtenirFitxa(FitxaCriteria fitxaCriteria) throws QueryServiceException;

	List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;

	FitxaUAQueryServiceAdapter obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) throws QueryServiceException;

	List<FitxaUAQueryServiceAdapter> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws QueryServiceException;

	FormulariQueryServiceAdapter obtenirFormulari(FormulariCriteria formulariCriteria) throws QueryServiceException;

	List<FormulariQueryServiceAdapter> llistarFormularis(FormulariCriteria formulariCriteria)
			throws QueryServiceException;

	IconaFamiliaQueryServiceAdapter obtenirIconaFamilia(IconaFamiliaCriteria iconaCriteria)
			throws QueryServiceException;

	List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria)
			throws QueryServiceException;

	IconaMateriaQueryServiceAdapter obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException;

	List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria)
			throws QueryServiceException;

	MateriaQueryServiceAdapter obtenirMateria(MateriaCriteria materiaCriteria) throws QueryServiceException;

	List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException;

	MateriaAgrupacioQueryServiceAdapter obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria)
			throws QueryServiceException;

	List<MateriaAgrupacioQueryServiceAdapter> llistarMateriesAgrupacions(
			MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws QueryServiceException;

	NormativaQueryServiceAdapter obtenirNormativa(NormativaCriteria normativaCriteria) throws QueryServiceException;

	List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria)
			throws QueryServiceException;

	PerfilQueryServiceAdapter obtenirPerfil(PerfilCriteria perfilCriteria) throws QueryServiceException;

	List<PerfilQueryServiceAdapter> llistarPerfils(PerfilCriteria perfilCriteria) throws QueryServiceException;

	PersonalQueryServiceAdapter obtenirPersonal(PersonalCriteria personalCriteria) throws QueryServiceException;

	List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) throws QueryServiceException;

	ProcedimentQueryServiceAdapter obtenirProcediment(ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException;

	List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria)
			throws QueryServiceException;

	int getNumProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException;

	ServicioQueryServiceAdapter obtenirServicio(ServicioCriteria servicioCriteria) throws QueryServiceException;

	List<ServicioQueryServiceAdapter> llistarServicios(ServicioCriteria servicioCriteria) throws QueryServiceException;

	int getNumServicios(ServicioCriteria servicioCriteria) throws QueryServiceException;

	PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException;

	List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria)
			throws QueryServiceException;

	TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException;

	List<TipusAfectacioQueryServiceAdapter> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria)
			throws QueryServiceException;

	SeccioQueryServiceAdapter obtenirSeccio(SeccioCriteria seccioCriteria) throws QueryServiceException;

	List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException;

	TaxaQueryServiceAdapter obtenirTaxa(TaxaCriteria taxaCriteria) throws QueryServiceException;

	List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) throws QueryServiceException;

	TipusQueryServiceAdapter obtenirTipus(TipusCriteria tipusCriteria) throws QueryServiceException;

	List<TipusQueryServiceAdapter> llistarTipus(TipusCriteria tipusCriteria) throws QueryServiceException;

	TramitQueryServiceAdapter obtenirTramit(TramitCriteria tramitCriteria) throws QueryServiceException;

	List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException;

	UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

	List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(
			UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException;

	UnitatMateriaQueryServiceAdapter obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException;

	List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria)
			throws QueryServiceException;

	UsuariQueryServiceAdapter obtenirUsuari(UsuariCriteria usuariCriteria) throws QueryServiceException;

	List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) throws QueryServiceException;

	IdiomaQueryServiceAdapter obtenirIdioma(IdiomaCriteria idiomaCriteria) throws QueryServiceException;

	List<IdiomaQueryServiceAdapter> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws QueryServiceException;

	// AfectacioQueryServiceAdapter obtenirAfectacio(AfectacioCriteria
	// afectacioCriteria) throws QueryServiceException;

	IniciacioQueryServiceAdapter obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException;

	List<IniciacioQueryServiceAdapter> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria)
			throws QueryServiceException;

	int getNumFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException;

	SilencioQueryServiceAdapter obtenirSilencio(Long codSilencio, String idioma) throws QueryServiceException;

}
