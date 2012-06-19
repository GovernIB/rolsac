package es.caib.rolsac.api.v2.rolsac;

import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.estadistica.EstadisticaCriteria;
import es.caib.rolsac.api.v2.estadistica.EstadisticaQueryServiceAdapter;
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
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
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

    AgrupacioFetVitalQueryServiceAdapter obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);

    List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);

    AgrupacioMateriaQueryServiceAdapter obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

    List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

    ButlletiQueryServiceAdapter obtenirButlleti(ButlletiCriteria butlletiCriteria);

    List<ButlletiQueryServiceAdapter> llistarButlletins(ButlletiCriteria butlletiCriteria);

    DocumentQueryServiceAdapter obtenirDocument(DocumentCriteria documentCriteria);

    List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria);

    DocumentTramitQueryServiceAdapter obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria);

    List<DocumentTramitQueryServiceAdapter> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria);

    EdificiQueryServiceAdapter obtenirEdifici(EdificiCriteria edificiTramit);

    List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiTramit);

    EnllacQueryServiceAdapter obtenirEnllac(EnllacCriteria enllacCriteria);

    List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria);

    EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<EspaiTerritorialQueryServiceAdapter> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria);

    EstadisticaQueryServiceAdapter obtenirEstadistica(EstadisticaCriteria estadisticaCriteria);

    List<EstadisticaQueryServiceAdapter> llistarEstadistiques(EstadisticaCriteria estadisticaCriteria);

    FamiliaQueryServiceAdapter obtenirFamilia(FamiliaCriteria familiaCriteria);

    List<FamiliaQueryServiceAdapter> llistarFamilies(FamiliaCriteria familiaCriteria);

    FetVitalQueryServiceAdapter obtenirFetVital(FetVitalCriteria fetVitalCriteria);

    List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria);

    FitxaQueryServiceAdapter obtenirFitxa(FitxaCriteria fitxaCriteria);

    List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria);

    FitxaUAQueryServiceAdapter obtenirFitxaUA(FitxaUACriteria fitxaUACriteria);

    List<FitxaUAQueryServiceAdapter> llistarFitxesUA(FitxaUACriteria fitxaUACriteria);

    FormulariQueryServiceAdapter obtenirFormulari(FormulariCriteria formulariCriteria);

    List<FormulariQueryServiceAdapter> llistarFormularis(FormulariCriteria formulariCriteria);

    IconaFamiliaQueryServiceAdapter obtenirIconaFamilia(IconaFamiliaCriteria iconaCriteria);

    List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria);

    IconaMateriaQueryServiceAdapter obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria);

    List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria);

    MateriaQueryServiceAdapter obtenirMateria(MateriaCriteria materiaCriteria);

    List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria);

    MateriaAgrupacioQueryServiceAdapter obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria);

    List<MateriaAgrupacioQueryServiceAdapter> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria);

    NormativaQueryServiceAdapter obtenirNormativa(NormativaCriteria normativaCriteria);

    List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria);

    PerfilQueryServiceAdapter obtenirPerfil(PerfilCriteria perfilCriteria);

    List<PerfilQueryServiceAdapter> llistarPerfils(PerfilCriteria perfilCriteria);

    PersonalQueryServiceAdapter obtenirPersonal(PersonalCriteria personalCriteria);

    List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria);

    ProcedimentQueryServiceAdapter obtenirProcediment(ProcedimentCriteria procedimentCriteria);

    List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria);

    PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria);

    List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria);

    TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria);
    
    List<TipusAfectacioQueryServiceAdapter> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria);
    
    SeccioQueryServiceAdapter obtenirSeccio(SeccioCriteria seccioCriteria);

    List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria);

    TaxaQueryServiceAdapter obtenirTaxa(TaxaCriteria taxaCriteria);

    List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria);

    TipusQueryServiceAdapter obtenirTipus(TipusCriteria tipusCriteria);
    
    List<TipusQueryServiceAdapter> llistarTipus(TipusCriteria tipusCriteria);
    
    TramitQueryServiceAdapter obtenirTramit(TramitCriteria tramitCriteria);

    List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria);

    UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    UnitatMateriaQueryServiceAdapter obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria);

    List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria);

    UsuariQueryServiceAdapter obtenirUsuari(UsuariCriteria ususariCriteria);

    List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria);

}
