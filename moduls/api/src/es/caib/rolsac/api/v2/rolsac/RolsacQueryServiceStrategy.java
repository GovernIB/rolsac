package es.caib.rolsac.api.v2.rolsac;

import java.util.List;

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
import es.caib.rolsac.api.v2.estadistica.EstadisticaCriteria;
import es.caib.rolsac.api.v2.estadistica.EstadisticaDTO;
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

public interface RolsacQueryServiceStrategy {

    AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);

    List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria);

    AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

    List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

    ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria);

    List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria);

    DocumentDTO obtenirDocument(DocumentCriteria documentCriteria);

    List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria);

    DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria);

    List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria docuemntTramitCriteria);

    EdificiDTO obtenirEdifici(EdificiCriteria edificiTramit);

    List<EdificiDTO> llistarEdificis(EdificiCriteria edificiTramit);

    EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria);

    List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria);

    EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria);

    EstadisticaDTO obtenirEstadistica(EstadisticaCriteria estadisticaCriteria);

    List<EstadisticaDTO> llistarEstadistiques(EstadisticaCriteria estadisticaCriteria);

    FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria);

    List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria);

    FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria);

    List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria);

    FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria);

    List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria);

    FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria);

    List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria);

    FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria);

    List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria);

    IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria iconaCriteria);

    List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria);

    IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria);

    List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria);

    MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria);

    List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria);

    MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria);

    List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria);

    NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria);

    List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria);

    PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria);

    List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria);

    PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria);

    List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria);

    ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria);

    List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria);

    PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria);

    List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria);

    SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria);

    List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria);

    TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria);

    List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria);

    TramitDTO obtenirTramit(TramitCriteria tramitCriteria);

    List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria);

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria);

    List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria);

    UsuariDTO obtenirUsuari(UsuariCriteria ususariCriteria);

    List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria);

    TipusDTO obtenirTipus(TipusCriteria tipusCriteria);

    List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria);

    TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria);

    List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria);

}
