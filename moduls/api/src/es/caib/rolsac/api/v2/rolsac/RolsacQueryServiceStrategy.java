package es.caib.rolsac.api.v2.rolsac;

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
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
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

public interface RolsacQueryServiceStrategy {

	void setUrl(String url);
	
    AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException;

    List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException;

    AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException;

    List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException;

    ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) throws StrategyException;

    List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) throws StrategyException;

    CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException; 
    
    List<CatalegDocumentsDTO> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException;
    
    DocumentDTO obtenirDocument(DocumentCriteria documentCriteria) throws StrategyException;

    List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria) throws StrategyException;

    DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws StrategyException;

    List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria docuemntTramitCriteria) throws StrategyException;

    List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria documentoNormativaCriteria) throws StrategyException;
    
    List<DocumentoServicioDTO> llistarDocumentoServicio(DocumentoServicioCriteria documentoServicioCriteria) throws StrategyException;
        
    EdificiDTO obtenirEdifici(EdificiCriteria edificiTramit) throws StrategyException;

    List<EdificiDTO> llistarEdificis(EdificiCriteria edificiTramit) throws StrategyException;

    EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) throws StrategyException;

    List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) throws StrategyException;

    EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException;

    List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException;

    ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException;
    
    List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException;
    
    FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) throws StrategyException;

    List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) throws StrategyException;

    FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) throws StrategyException;

    List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws StrategyException;

    FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) throws StrategyException;

    List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) throws StrategyException;

    FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) throws StrategyException;

    List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws StrategyException;

    FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria) throws StrategyException;

    List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria) throws StrategyException;

    IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria iconaCriteria) throws StrategyException;

    List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException;

    IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException;

    List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException;

    MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) throws StrategyException;

    List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) throws StrategyException;

    MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws StrategyException;

    List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws StrategyException;

    NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) throws StrategyException;

    List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) throws StrategyException;

    PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) throws StrategyException;

    List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) throws StrategyException;

    PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) throws StrategyException;

    List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) throws StrategyException;

    ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) throws StrategyException;

    List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws StrategyException;
    
    Integer getNumProcediments(ProcedimentCriteria procedimentCriteria) throws StrategyException;

    ServicioDTO obtenirServicio(ServicioCriteria servicioCriteria) throws StrategyException;

    List<ServicioDTO> llistarServicios(ServicioCriteria servicioCriteria) throws StrategyException;
    
    Integer getNumServicios(ServicioCriteria servicioCriteria) throws StrategyException;

    PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException;

    List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException;

    SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) throws StrategyException;

    List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) throws StrategyException;

    TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) throws StrategyException;

    List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) throws StrategyException;

    TramitDTO obtenirTramit(TramitCriteria tramitCriteria) throws StrategyException;

    List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) throws StrategyException;

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException;

    UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException;

    List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException;

    UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) throws StrategyException;

    List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) throws StrategyException;
    
    IdiomaDTO obtenirIdioma(IdiomaCriteria idiomaCriteria) throws StrategyException;
    
    List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException;

    TipusDTO obtenirTipus(TipusCriteria tipusCriteria) throws StrategyException;

    List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) throws StrategyException;

    TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws StrategyException;

    List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws StrategyException;
    
    IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws StrategyException;
    
    List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws StrategyException;

    Integer getNumFitxes(FitxaCriteria fitxaCriteria) throws StrategyException;
    
    SilencioDTO obtenirSilenci(Long codSilencio, String idioma) throws StrategyException, QueryServiceException;

}
