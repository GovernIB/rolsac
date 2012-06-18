package es.caib.rolsac.api.v2.rolsac;

import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.estadistica.EstadisticaCriteria;
import es.caib.rolsac.api.v2.estadistica.EstadisticaQueryServiceAdapter;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUADTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceAdapter;
import es.caib.rolsac.api.v2.formulari.FormulariCriteria;
import es.caib.rolsac.api.v2.formulari.FormulariDTO;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceAdapter;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioCriteria;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaCriteria;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class RolsacQueryServiceAdapter implements RolsacQueryService {

    private RolsacQueryServiceStrategy rolsacQueryServiceStrategy;
    
    public void setRolsacQueryServiceStrategy(RolsacQueryServiceStrategy rolsacQueryServiceStrategy) {
        this.rolsacQueryServiceStrategy = rolsacQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return rolsacQueryServiceStrategy instanceof RolsacQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public ProcedimentQueryServiceAdapter obtenirProcediment(ProcedimentCriteria procedimentCriteria) {
        ProcedimentDTO dto = rolsacQueryServiceStrategy.obtenirProcediment(procedimentCriteria);
        return (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), dto);
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarProcediments(procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO procedimentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public AgrupacioFetVitalQueryServiceAdapter obtenirAgrupacioFetVital(
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        AgrupacioFetVitalDTO dto = rolsacQueryServiceStrategy.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        return (AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), dto);
    }

    public List<AgrupacioFetVitalQueryServiceAdapter> llistarAgrupacionsFetsVitals(
            AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
        List<AgrupacioFetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioFetVitalQueryServiceAdapter>();
        for (AgrupacioFetVitalDTO afvDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AgrupacioFetVitalQueryServiceAdapter) BeanUtils.getAdapter("agrupacioFetVital", getStrategy(), afvDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        AgrupacioMateriaDTO dto = rolsacQueryServiceStrategy.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
        return (AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), dto);
    }

    public List<AgrupacioMateriaQueryServiceAdapter> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) {
        List<AgrupacioMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
        List<AgrupacioMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<AgrupacioMateriaQueryServiceAdapter>();
        for (AgrupacioMateriaDTO amDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((AgrupacioMateriaQueryServiceAdapter) BeanUtils.getAdapter("agrupacioMateria", getStrategy(), amDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public ButlletiQueryServiceAdapter obtenirButlleti(ButlletiCriteria butlletiCriteria) {
        ButlletiDTO dto = rolsacQueryServiceStrategy.obtenirButlleti(butlletiCriteria);
        return (ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), dto);
    }

    public List<ButlletiQueryServiceAdapter> llistarButlletins(ButlletiCriteria butlletiCriteria) {
        List<ButlletiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarButlletins(butlletiCriteria);
        List<ButlletiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ButlletiQueryServiceAdapter>();
        for (ButlletiDTO butlletiDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ButlletiQueryServiceAdapter) BeanUtils.getAdapter("butlleti", getStrategy(), butlletiDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public DocumentQueryServiceAdapter obtenirDocument(DocumentCriteria documentCriteria) {
        DocumentDTO dto = rolsacQueryServiceStrategy.obtenirDocument(documentCriteria);
        return (DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), dto);
    }

    public List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria) {
        List<DocumentDTO> llistaDTO = rolsacQueryServiceStrategy.llistarDocuments(documentCriteria);
        List<DocumentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentQueryServiceAdapter>();
        for (DocumentDTO documentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((DocumentQueryServiceAdapter) BeanUtils.getAdapter("document", getStrategy(), documentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public DocumentTramitQueryServiceAdapter obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        DocumentTramitDTO dto = rolsacQueryServiceStrategy.obtenirDocumentTramit(documentTramitCriteria);
        return (DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), dto);
    }

    public List<DocumentTramitQueryServiceAdapter> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) {
        List<DocumentTramitDTO> llistaDTO = rolsacQueryServiceStrategy.llistarDocumentTramit(documentTramitCriteria);
        List<DocumentTramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
        for (DocumentTramitDTO documentTramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((DocumentTramitQueryServiceAdapter) BeanUtils.getAdapter("documentTramit", getStrategy(), documentTramitDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public EdificiQueryServiceAdapter obtenirEdifici(EdificiCriteria edificiCriteria) {
        EdificiDTO dto = rolsacQueryServiceStrategy.obtenirEdifici(edificiCriteria);
        return (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), dto);
    }

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) {
        List<EdificiDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEdificis(edificiCriteria);
        List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
        for (EdificiDTO edificiDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), edificiDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public EnllacQueryServiceAdapter obtenirEnllac(EnllacCriteria enllacCriteria) {
        EnllacDTO dto = rolsacQueryServiceStrategy.obtenirEnllac(enllacCriteria);
        return (EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), dto);
    }

    public List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria) {
        List<EnllacDTO> llistaDTO = rolsacQueryServiceStrategy.llistarEnllacos(enllacCriteria);
        List<EnllacQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EnllacQueryServiceAdapter>();
        for (EnllacDTO enllacDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EnllacQueryServiceAdapter) BeanUtils.getAdapter("enllac", getStrategy(), enllacDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria) {
        EspaiTerritorialDTO dto = rolsacQueryServiceStrategy.obtenirEspaiTerritorial(espaiTerritorialCriteria);
        return (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), dto);
    }

    public List<EspaiTerritorialQueryServiceAdapter> llistarEspaisTerritorials(
            EspaiTerritorialCriteria espaiTerritorialCriteria) {
        List<EspaiTerritorialDTO> llistaDTO = rolsacQueryServiceStrategy
                .llistarEspaisTerritorials(espaiTerritorialCriteria);
        List<EspaiTerritorialQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EspaiTerritorialQueryServiceAdapter>();
        for (EspaiTerritorialDTO espaiTerritorialDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), espaiTerritorialDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public EstadisticaQueryServiceAdapter obtenirEstadistica(EstadisticaCriteria estadisticaCriteria) {
        // TODO: De momento no se sabe si es necesario.
        return null;
    }

    public List<EstadisticaQueryServiceAdapter> llistarEstadistiques(EstadisticaCriteria estadisticaCriteria) {
        // TODO: De momento no se sabe si es necesario.
        return null;
    }

    public FamiliaQueryServiceAdapter obtenirFamilia(FamiliaCriteria familiaCriteria) {
        FamiliaDTO dto = rolsacQueryServiceStrategy.obtenirFamilia(familiaCriteria);
        return (FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), dto);
    }

    public List<FamiliaQueryServiceAdapter> llistarFamilies(FamiliaCriteria familiaCriteria) {
        List<FamiliaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFamilies(familiaCriteria);
        List<FamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FamiliaQueryServiceAdapter>();
        for (FamiliaDTO familiaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FamiliaQueryServiceAdapter) BeanUtils.getAdapter("familia", getStrategy(), familiaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public FetVitalQueryServiceAdapter obtenirFetVital(FetVitalCriteria fetVitalCriteria) {
        FetVitalDTO dto = rolsacQueryServiceStrategy.obtenirFetVital(fetVitalCriteria);
        return (FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), dto);
    }

    public List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) {
        List<FetVitalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFetsVitals(fetVitalCriteria);
        List<FetVitalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FetVitalQueryServiceAdapter>();
        for (FetVitalDTO fetVitalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FetVitalQueryServiceAdapter) BeanUtils.getAdapter("fetVital", getStrategy(), fetVitalDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public FitxaQueryServiceAdapter obtenirFitxa(FitxaCriteria fitxaCriteria) {
        FitxaDTO dto = rolsacQueryServiceStrategy.obtenirFitxa(fitxaCriteria);
        return (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), dto);
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxes(fitxaCriteria);
        List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
        for (FitxaDTO fitxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public FitxaUAQueryServiceAdapter obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) {
        FitxaUADTO dto = rolsacQueryServiceStrategy.obtenirFitxaUA(fitxaUACriteria);
        return (FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA", getStrategy(), dto);
    }

    public List<FitxaUAQueryServiceAdapter> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) {
        List<FitxaUADTO> llistaDTO = rolsacQueryServiceStrategy.llistarFitxesUA(fitxaUACriteria);
        List<FitxaUAQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaUAQueryServiceAdapter>();
        for (FitxaUADTO fitxaUADTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaUAQueryServiceAdapter) BeanUtils.getAdapter("fitxaUA", getStrategy(), fitxaUADTO));
        }
        return llistaQueryServiceAdapter;
    }

    public FormulariQueryServiceAdapter obtenirFormulari(FormulariCriteria formulariCriteria) {
        FormulariDTO dto = rolsacQueryServiceStrategy.obtenirFormulari(formulariCriteria);
        return (FormulariQueryServiceAdapter) BeanUtils.getAdapter("formulari", getStrategy(), dto);
    }

    public List<FormulariQueryServiceAdapter> llistarFormularis(FormulariCriteria formulariCriteria) {
        List<FormulariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarFormularis(formulariCriteria);
        List<FormulariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FormulariQueryServiceAdapter>();
        for (FormulariDTO formDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FormulariQueryServiceAdapter) BeanUtils.getAdapter("formulari", getStrategy(), formDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public IconaFamiliaQueryServiceAdapter obtenirIconaFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) {
        IconaFamiliaDTO dto = rolsacQueryServiceStrategy.obtenirIconaFamilia(iconaFamiliaCriteria);
        return (IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), dto);
    }

    public List<IconaFamiliaQueryServiceAdapter> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIconesFamilies(iconaFamiliaCriteria);
        List<IconaFamiliaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaFamiliaQueryServiceAdapter>();
        for (IconaFamiliaDTO ifDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((IconaFamiliaQueryServiceAdapter) BeanUtils.getAdapter("iconaFamilia", getStrategy(), ifDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public IconaMateriaQueryServiceAdapter obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria) {
        IconaMateriaDTO dto = rolsacQueryServiceStrategy.obtenirIconaMateria(iconaMateriaCriteria);
        return (IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), dto);
    }

    public List<IconaMateriaQueryServiceAdapter> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarIconesMateries(iconaMateriaCriteria);
        List<IconaMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IconaMateriaQueryServiceAdapter>();
        for (IconaMateriaDTO imDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((IconaMateriaQueryServiceAdapter) BeanUtils.getAdapter("iconaMateria", getStrategy(), imDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public MateriaQueryServiceAdapter obtenirMateria(MateriaCriteria materiaCriteria) {
        MateriaDTO dto = rolsacQueryServiceStrategy.obtenirMateria(materiaCriteria);
        return (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), dto);
    }

    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) {
        List<MateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarMateries(materiaCriteria);
        List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
        for (MateriaDTO materiaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public MateriaAgrupacioQueryServiceAdapter obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria) {
        MateriaAgrupacioDTO dto = rolsacQueryServiceStrategy.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
        return (MateriaAgrupacioQueryServiceAdapter) BeanUtils.getAdapter("materiaAgrupacio", getStrategy(), dto);
    }

    public List<MateriaAgrupacioQueryServiceAdapter> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria) {
        List<MateriaAgrupacioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
        List<MateriaAgrupacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaAgrupacioQueryServiceAdapter>();
        for (MateriaAgrupacioDTO maDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((MateriaAgrupacioQueryServiceAdapter) BeanUtils.getAdapter("materiaAgrupacio", getStrategy(), maDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public NormativaQueryServiceAdapter obtenirNormativa(NormativaCriteria normativaCriteria) {
        NormativaDTO dto = rolsacQueryServiceStrategy.obtenirNormativa(normativaCriteria);
        return (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), dto);
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarNormatives(normativaCriteria);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public PerfilQueryServiceAdapter obtenirPerfil(PerfilCriteria perfilCriteria) {
        PerfilDTO dto = rolsacQueryServiceStrategy.obtenirPerfil(perfilCriteria);
        return (PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), dto);
    }

    public List<PerfilQueryServiceAdapter> llistarPerfils(PerfilCriteria perfilCriteria) {
        List<PerfilDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPerfils(perfilCriteria);
        List<PerfilQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PerfilQueryServiceAdapter>();
        for (PerfilDTO perfilDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((PerfilQueryServiceAdapter) BeanUtils.getAdapter("perfil", getStrategy(), perfilDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public PersonalQueryServiceAdapter obtenirPersonal(PersonalCriteria personalCriteria) {
        PersonalDTO dto = rolsacQueryServiceStrategy.obtenirPersonal(personalCriteria);
        return (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), dto);
    }

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) {
        List<PersonalDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPersonal(personalCriteria);
        List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
        for (PersonalDTO personalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), personalDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public PublicObjectiuQueryServiceAdapter obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) {
        PublicObjectiuDTO dto = rolsacQueryServiceStrategy.obtenirPublicObjectiu(publicObjectiuCriteria);
        return (PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), dto);
    }

    public List<PublicObjectiuQueryServiceAdapter> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) {
        List<PublicObjectiuDTO> llistaDTO = rolsacQueryServiceStrategy.llistarPublicsObjectius(publicObjectiuCriteria);
        List<PublicObjectiuQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PublicObjectiuQueryServiceAdapter>();
        for (PublicObjectiuDTO poDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((PublicObjectiuQueryServiceAdapter) BeanUtils.getAdapter("publicObjectiu", getStrategy(), poDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public SeccioQueryServiceAdapter obtenirSeccio(SeccioCriteria seccioCriteria) {
        SeccioDTO dto = rolsacQueryServiceStrategy.obtenirSeccio(seccioCriteria);
        return (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), dto);
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) {
        List<SeccioDTO> llistaDTO = rolsacQueryServiceStrategy.llistarSeccions(seccioCriteria);
        List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
        for (SeccioDTO poDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), poDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public TaxaQueryServiceAdapter obtenirTaxa(TaxaCriteria taxaCriteria) {
        TaxaDTO dto = rolsacQueryServiceStrategy.obtenirTaxa(taxaCriteria);
        return (TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), dto);
    }

    public List<TaxaQueryServiceAdapter> llistarTaxes(TaxaCriteria taxaCriteria) {
        List<TaxaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTaxes(taxaCriteria);
        List<TaxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TaxaQueryServiceAdapter>();
        for (TaxaDTO taxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TaxaQueryServiceAdapter) BeanUtils.getAdapter("taxa", getStrategy(), taxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public TipusQueryServiceAdapter obtenirTipus(TipusCriteria tipusCriteria) {
        TipusDTO dto = rolsacQueryServiceStrategy.obtenirTipus(tipusCriteria);
        return (TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus", getStrategy(), dto);
    }

    public List<TipusQueryServiceAdapter> llistarTipus(TipusCriteria tipusCriteria) {
        List<TipusDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTipus(tipusCriteria);
        List<TipusQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TipusQueryServiceAdapter>();
        for (TipusDTO tipusDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TipusQueryServiceAdapter) BeanUtils.getAdapter("tipus", getStrategy(), tipusDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public TramitQueryServiceAdapter obtenirTramit(TramitCriteria tramitCriteria) {
        TramitDTO dto = rolsacQueryServiceStrategy.obtenirTramit(tramitCriteria);
        return (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), dto);
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) {
        List<TramitDTO> llistaDTO = rolsacQueryServiceStrategy.llistarTramits(tramitCriteria);
        List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
        for (TramitDTO tramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        UnitatAdministrativaDTO dto = rolsacQueryServiceStrategy.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO uaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), uaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public UnitatMateriaQueryServiceAdapter obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria) {
        UnitatMateriaDTO dto = rolsacQueryServiceStrategy.obtenirUnitatMateria(unitatMateriaCriteria);
        return (UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), dto);
    }

    public List<UnitatMateriaQueryServiceAdapter> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria) {
        List<UnitatMateriaDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUnitatsMateries(unitatMateriaCriteria);
        List<UnitatMateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatMateriaQueryServiceAdapter>();
        for (UnitatMateriaDTO umDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatMateriaQueryServiceAdapter) BeanUtils.getAdapter("unitatMateria", getStrategy(), umDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public UsuariQueryServiceAdapter obtenirUsuari(UsuariCriteria usuariCriteria) {
        UsuariDTO dto = rolsacQueryServiceStrategy.obtenirUsuari(usuariCriteria);
        return (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), dto);
    }

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) {
        List<UsuariDTO> llistaDTO = rolsacQueryServiceStrategy.llistarUsuaris(usuariCriteria);
        List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
        for (UsuariDTO uDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), uDTO));
        }
        return llistaQueryServiceAdapter;
    }

}
