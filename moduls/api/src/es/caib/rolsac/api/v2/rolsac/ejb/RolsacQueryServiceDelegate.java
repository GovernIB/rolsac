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

    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirProcediment(procedimentCriteria);
    }

    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarProcediments(procedimentCriteria);
    }
    
    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirMateria(materiaCriteria);
    }
    
    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarMateries(materiaCriteria);
    }
    
    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirTramit(tramitCriteria);
    }
    
    public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarTramit(tramitCriteria);
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria uaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirUnitatAdministrativa(uaCriteria);
    }
    
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria uaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(uaCriteria);
    }
    
    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirFetVital(fetVitalCriteria);
    }
    
    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarFetsVitals(fetVitalCriteria);
    }
    
    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirFitxa(fitxaCriteria);
    }
    
    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarFitxes(fitxaCriteria);
    }
    
    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirNormativa(normativaCriteria);
    }
    
    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarNormatives(normativaCriteria);
    }
    
    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirPersonal(personalCriteria);
    }
    
    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarPersonal(personalCriteria);
    }
    
    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirDocumentTramit(documentTramitCriteria);
    }
    
    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarDocumentTramit(documentTramitCriteria);
    }
    
    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirUsuari(usuariCriteria);
    }
    
    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarUsuaris(usuariCriteria);
    }
    
    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirTaxa(taxaCriteria);
    }
    
    public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarTaxes(taxaCriteria);
    }

    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria afvCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirAgrupacioFetVital(afvCriteria);
    }
    
    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria afvCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarAgrupacionsFetsVitals(afvCriteria);
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria amCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirAgrupacioMateria(amCriteria);
    }
    
    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria amCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarAgrupacionsMateries(amCriteria);
    }
    
    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirButlleti(butlletiCriteria);
    }
    
    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarButlletins(butlletiCriteria);
    }
    
    public DocumentDTO obtenirDocument(DocumentCriteria docCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirDocument(docCriteria);
    }
    
    public List<DocumentDTO> llistarDocuments(DocumentCriteria docCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarDocuments(docCriteria);
    }
    
    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirEdifici(edificiCriteria);
    }
    
    public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarEdificis(edificiCriteria);
    }
    
    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirEnllac(enllacCriteria);
    }
    
    public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarEnllacos(enllacCriteria);
    }
    
    public EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria etCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirEspaiTerritorial(etCriteria);
    }
    
    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria etCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarEspaisTerritorials(etCriteria);
    }
    
    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirFamilia(familiaCriteria);
    }
    
    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarFamilies(familiaCriteria);
    }
    
    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirPublicObjectiu(publicObjectiuCriteria);
    }
    
    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarPublicsObjectius(publicObjectiuCriteria);
    }
   
    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirFitxaUA(fitxaUACriteria);
    }
    
    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarFitxesUA(fitxaUACriteria);
    }

    public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirFormulari(formulariCriteria);
    }
    
    public List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarFormularis(formulariCriteria);
    }
    
    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria ifCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirIconaFamilia(ifCriteria);
    }
    
    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria ifCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarIconesFamilies(ifCriteria);
    }
    
    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria imCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirIconaMateria(imCriteria);
    }
    
    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria imCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarIconesMateries(imCriteria);
    }
    
    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria maCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirMateriaAgrupacio(maCriteria);
    }
    
    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria maCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarMateriesAgrupacions(maCriteria);
    }
    
    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirPerfil(perfilCriteria);
    }
    
    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarPerfils(perfilCriteria);
    }
    
    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirSeccio(seccioCriteria);
    }
    
    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarSeccions(seccioCriteria);
    }
    
    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria umCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirUnitatMateria(umCriteria);
    }
    
    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria umCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarUnitatsMateries(umCriteria);
    }

    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.obtenirTipus(tipusCriteria);
    }

    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) {
        RolsacQueryServiceEJB ejb = rolsacQueryServiceLocator.getRolsacQueryServiceEJB();
        return ejb.llistarTipus(tipusCriteria);
    }
    
}
