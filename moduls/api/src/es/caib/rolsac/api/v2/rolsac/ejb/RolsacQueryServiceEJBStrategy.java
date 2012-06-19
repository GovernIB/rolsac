package es.caib.rolsac.api.v2.rolsac.ejb;

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
import es.caib.rolsac.api.v2.rolsac.RolsacQueryServiceStrategy;
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

public class RolsacQueryServiceEJBStrategy implements RolsacQueryServiceStrategy {

    private RolsacQueryServiceDelegate rolsacQueryServiceDelegate;
    
    public void setRolsacQueryServiceDelegate(RolsacQueryServiceDelegate rolsacQueryServiceDelegate) {
        this.rolsacQueryServiceDelegate = rolsacQueryServiceDelegate;
    }

    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        return rolsacQueryServiceDelegate.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        return rolsacQueryServiceDelegate.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        return rolsacQueryServiceDelegate.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        return rolsacQueryServiceDelegate.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
    }

    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) {
        return rolsacQueryServiceDelegate.obtenirButlleti(butlletiCriteria);
    }

    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) {
        return rolsacQueryServiceDelegate.llistarButlletins(butlletiCriteria);
    }

    public DocumentDTO obtenirDocument(DocumentCriteria documentCriteria) {
        return rolsacQueryServiceDelegate.obtenirDocument(documentCriteria);
    }

    public List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria) {
        return rolsacQueryServiceDelegate.llistarDocuments(documentCriteria);
    }

    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        return rolsacQueryServiceDelegate.obtenirDocumentTramit(documentTramitCriteria);
    }

    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        return rolsacQueryServiceDelegate.llistarDocumentTramit(documentTramitCriteria);
    }

    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) {
        return rolsacQueryServiceDelegate.obtenirEdifici(edificiCriteria);
    }

    public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiCriteria) {
        return rolsacQueryServiceDelegate.llistarEdificis(edificiCriteria);
    }

    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) {
        return rolsacQueryServiceDelegate.obtenirEnllac(enllacCriteria);
    }

    public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) {
        return rolsacQueryServiceDelegate.llistarEnllacos(enllacCriteria);
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria) {
        return rolsacQueryServiceDelegate.obtenirEspaiTerritorial(espaiTerritorialCriteria);
    }

    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria) {
        return rolsacQueryServiceDelegate.llistarEspaisTerritorials(espaiTerritorialCriteria);
    }

    public EstadisticaDTO obtenirEstadistica(EstadisticaCriteria estadisticaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<EstadisticaDTO> llistarEstadistiques(EstadisticaCriteria estadisticaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) {
        return rolsacQueryServiceDelegate.obtenirFamilia(familiaCriteria);
    }

    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) {
        return rolsacQueryServiceDelegate.llistarFamilies(familiaCriteria);
    }

    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) {
        return rolsacQueryServiceDelegate.obtenirFetVital(fetVitalCriteria);
    }

    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        return rolsacQueryServiceDelegate.llistarFetsVitals(fetVitalCriteria);
    }

    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) {
        return rolsacQueryServiceDelegate.obtenirFitxa(fitxaCriteria);
    }

    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) {
        return rolsacQueryServiceDelegate.llistarFitxes(fitxaCriteria);
    }

    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) {
        return rolsacQueryServiceDelegate.obtenirFitxaUA(fitxaUACriteria);
    }

    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) {
        return rolsacQueryServiceDelegate.llistarFitxesUA(fitxaUACriteria);
    }

    public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria) {
        return rolsacQueryServiceDelegate.obtenirFormulari(formulariCriteria);
    }

    public List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria) {
        return rolsacQueryServiceDelegate.llistarFormularis(formulariCriteria);
    }

    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) {
        return rolsacQueryServiceDelegate.obtenirIconaFamilia(iconaFamiliaCriteria);
    }

    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria) {
        return rolsacQueryServiceDelegate.llistarIconesFamilies(iconaFamiliaCriteria);
    }

    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria) {
        return rolsacQueryServiceDelegate.obtenirIconaMateria(iconaMateriaCriteria);
    }

    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) {
        return rolsacQueryServiceDelegate.llistarIconesMateries(iconaMateriaCriteria);
    }

    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) {
        return rolsacQueryServiceDelegate.obtenirMateria(materiaCriteria);
    }

    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) {
        return rolsacQueryServiceDelegate.llistarMateries(materiaCriteria);
    }

    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria) {
        return rolsacQueryServiceDelegate.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
    }

    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria) {
        return rolsacQueryServiceDelegate.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
    }

    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) {
        return rolsacQueryServiceDelegate.obtenirNormativa(normativaCriteria);
    }

    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) {
        return rolsacQueryServiceDelegate.llistarNormatives(normativaCriteria);
    }

    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) {
        return rolsacQueryServiceDelegate.obtenirPerfil(perfilCriteria);
    }

    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) {
        return rolsacQueryServiceDelegate.llistarPerfils(perfilCriteria);
    }

    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) {
         return rolsacQueryServiceDelegate.obtenirPersonal(personalCriteria);
    }

    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) {
        return rolsacQueryServiceDelegate.llistarPersonal(personalCriteria);
    }

    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) {
        return rolsacQueryServiceDelegate.obtenirProcediment(procedimentCriteria);
    }

    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        return rolsacQueryServiceDelegate.llistarProcediments(procedimentCriteria);
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) {
        return rolsacQueryServiceDelegate.obtenirPublicObjectiu(publicObjectiuCriteria);
    }
    
    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) {
        return rolsacQueryServiceDelegate.llistarPublicsObjectius(publicObjectiuCriteria);
    }

    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) {
        return rolsacQueryServiceDelegate.obtenirSeccio(seccioCriteria);
    }

    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) {
        return rolsacQueryServiceDelegate.llistarSeccions(seccioCriteria);
    }

    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) {
        return rolsacQueryServiceDelegate.obtenirTaxa(taxaCriteria);
    }

    public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) {
        return rolsacQueryServiceDelegate.llistarTaxes(taxaCriteria);
    }

    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) {
        return rolsacQueryServiceDelegate.obtenirTramit(tramitCriteria);
    }

    public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) {
        return rolsacQueryServiceDelegate.llistarTramits(tramitCriteria);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return rolsacQueryServiceDelegate.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return rolsacQueryServiceDelegate.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
    }

    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria) {
        return rolsacQueryServiceDelegate.obtenirUnitatMateria(unitatMateriaCriteria);
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria) {
        return rolsacQueryServiceDelegate.llistarUnitatsMateries(unitatMateriaCriteria);
    }

    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) {
        return rolsacQueryServiceDelegate.obtenirUsuari(usuariCriteria);
    }

    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) {
        return rolsacQueryServiceDelegate.llistarUsuaris(usuariCriteria);
    }

    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) {
        return rolsacQueryServiceDelegate.obtenirTipus(tipusCriteria);
    }

    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) {
        return rolsacQueryServiceDelegate.llistarTipus(tipusCriteria);
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) {
        return rolsacQueryServiceDelegate.obtenirTipusAfectacio(tipusAfectacioCriteria);
    }

    public List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) {
        return rolsacQueryServiceDelegate.llistarTipusAfectacio(tipusAfectacioCriteria);
    }

}
