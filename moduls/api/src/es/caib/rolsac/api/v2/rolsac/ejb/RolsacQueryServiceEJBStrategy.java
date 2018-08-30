package es.caib.rolsac.api.v2.rolsac.ejb;

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
import es.caib.rolsac.api.v2.exception.DelegateException;
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
import es.caib.rolsac.api.v2.rolsac.RolsacQueryServiceStrategy;
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

public class RolsacQueryServiceEJBStrategy implements RolsacQueryServiceStrategy {
	
	private RolsacQueryServiceDelegate rolsacQueryServiceDelegate;
	
	public void setUrl(String url) {
		// Para EJB no necesario		
	}

    public void setRolsacQueryServiceDelegate(RolsacQueryServiceDelegate rolsacQueryServiceDelegate) {
        this.rolsacQueryServiceDelegate = rolsacQueryServiceDelegate;
    }

    public AgrupacioFetVitalDTO obtenirAgrupacioFetVital(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirAgrupacioFetVital(agrupacioFetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AgrupacioFetVitalDTO> llistarAgrupacionsFetsVitals(AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarAgrupacionsFetsVitals(agrupacioFetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<AgrupacioMateriaDTO> llistarAgrupacionsMateries(AgrupacioMateriaCriteria agrupacioMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarAgrupacionsMateries(agrupacioMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ButlletiDTO obtenirButlleti(ButlletiCriteria butlletiCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirButlleti(butlletiCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ButlletiDTO> llistarButlletins(ButlletiCriteria butlletiCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarButlletins(butlletiCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public CatalegDocumentsDTO obtenirCatalegDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException {
    	try {
    		return rolsacQueryServiceDelegate.obtenirCatalegDocuments(catalegDocumentsCriteria);    		
    	} catch (DelegateException e) {
    		throw new StrategyException(e);
    	}
    }
    
    public List<CatalegDocumentsDTO> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentsCriteria) throws StrategyException {
    	try {
    		return rolsacQueryServiceDelegate.llistarCatalegsDocuments(catalegDocumentsCriteria);
    	} catch (DelegateException e) {
    		throw new StrategyException(e);
    	}
    }

    public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(ExcepcioDocumentacioCriteria catalegDocumentsCriteria) throws StrategyException {
    	try {
    		return rolsacQueryServiceDelegate.obtenirExcepcioDocumentacio(catalegDocumentsCriteria);    		
    	} catch (DelegateException e) {
    		throw new StrategyException(e);
    	}
    }
    
    public List<ExcepcioDocumentacioDTO> llistarExcepcionsDocumentacio(ExcepcioDocumentacioCriteria excepcioDocumentacioCriteria) throws StrategyException {
    	try {
    		return rolsacQueryServiceDelegate.llistarExcepcionsDocumentacio(excepcioDocumentacioCriteria);
    	} catch (DelegateException e) {
    		throw new StrategyException(e);
    	}
    }
    
    public DocumentDTO obtenirDocument(DocumentCriteria documentCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirDocument(documentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentDTO> llistarDocuments(DocumentCriteria documentCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarDocuments(documentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public DocumentTramitDTO obtenirDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirDocumentTramit(documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<DocumentTramitDTO> llistarDocumentTramit(DocumentTramitCriteria documentTramitCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarDocumentTramit(documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<DocumentoNormativaDTO> llistarDocumentoNormativa(DocumentoNormativaCriteria documentoNormativaCriteria) throws StrategyException {
    	     try {
            return rolsacQueryServiceDelegate.llistarDocumentoNormativa(documentoNormativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    
    public List<DocumentoServicioDTO> llistarDocumentoServicio(DocumentoServicioCriteria documentoServicioCriteria) throws StrategyException {
    	try {
	       return rolsacQueryServiceDelegate.llistarDocumentoServicio(documentoServicioCriteria);
	   } catch (DelegateException e) {
	       throw new StrategyException(e);
	   }
	}

    public EdificiDTO obtenirEdifici(EdificiCriteria edificiCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirEdifici(edificiCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<EdificiDTO> llistarEdificis(EdificiCriteria edificiCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarEdificis(edificiCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public EnllacDTO obtenirEnllac(EnllacCriteria enllacCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirEnllac(enllacCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<EnllacDTO> llistarEnllacos(EnllacCriteria enllacCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarEnllacos(enllacCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirEspaiTerritorial(espaiTerritorialCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<EspaiTerritorialDTO> llistarEspaisTerritorials(EspaiTerritorialCriteria espaiTerritorialCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarEspaisTerritorials(espaiTerritorialCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public FamiliaDTO obtenirFamilia(FamiliaCriteria familiaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirFamilia(familiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FamiliaDTO> llistarFamilies(FamiliaCriteria familiaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarFamilies(familiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public FetVitalDTO obtenirFetVital(FetVitalCriteria fetVitalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirFetVital(fetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FetVitalDTO> llistarFetsVitals(FetVitalCriteria fetVitalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarFetsVitals(fetVitalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public FitxaDTO obtenirFitxa(FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirFitxa(fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FitxaDTO> llistarFitxes(FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarFitxes(fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public FitxaUADTO obtenirFitxaUA(FitxaUACriteria fitxaUACriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirFitxaUA(fitxaUACriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FitxaUADTO> llistarFitxesUA(FitxaUACriteria fitxaUACriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarFitxesUA(fitxaUACriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public FormulariDTO obtenirFormulari(FormulariCriteria formulariCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirFormulari(formulariCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<FormulariDTO> llistarFormularis(FormulariCriteria formulariCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarFormularis(formulariCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public IconaFamiliaDTO obtenirIconaFamilia(IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirIconaFamilia(iconaFamiliaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IconaFamiliaDTO> llistarIconesFamilies(IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarIconesFamilies(iconaFamiliaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public IconaMateriaDTO obtenirIconaMateria(IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirIconaMateria(iconaMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IconaMateriaDTO> llistarIconesMateries(IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarIconesMateries(iconaMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public MateriaDTO obtenirMateria(MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirMateria(materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<MateriaDTO> llistarMateries(MateriaCriteria materiaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarMateries(materiaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public MateriaAgrupacioDTO obtenirMateriaAgrupacio(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirMateriaAgrupacio(materiaAgrupacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<MateriaAgrupacioDTO> llistarMateriesAgrupacions(MateriaAgrupacioCriteria materiaAgrupacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarMateriesAgrupacions(materiaAgrupacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public NormativaDTO obtenirNormativa(NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirNormativa(normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<NormativaDTO> llistarNormatives(NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarNormatives(normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public PerfilDTO obtenirPerfil(PerfilCriteria perfilCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirPerfil(perfilCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PerfilDTO> llistarPerfils(PerfilCriteria perfilCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarPerfils(perfilCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public PersonalDTO obtenirPersonal(PersonalCriteria personalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirPersonal(personalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PersonalDTO> llistarPersonal(PersonalCriteria personalCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarPersonal(personalCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ProcedimentDTO obtenirProcediment(ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirProcediment(procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ProcedimentDTO> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarProcediments(procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public Integer getNumProcediments(ProcedimentCriteria procedimentCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.getNumProcediments(procedimentCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public ServicioDTO obtenirServicio(ServicioCriteria servicioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirServicio(servicioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<ServicioDTO> llistarServicios(ServicioCriteria servicioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarServicios(servicioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public Integer getNumServicios(ServicioCriteria servicioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.getNumServicios(servicioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public PublicObjectiuDTO obtenirPublicObjectiu(PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirPublicObjectiu(publicObjectiuCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<PublicObjectiuDTO> llistarPublicsObjectius(PublicObjectiuCriteria publicObjectiuCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarPublicsObjectius(publicObjectiuCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public SeccioDTO obtenirSeccio(SeccioCriteria seccioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirSeccio(seccioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<SeccioDTO> llistarSeccions(SeccioCriteria seccioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarSeccions(seccioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TaxaDTO obtenirTaxa(TaxaCriteria taxaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirTaxa(taxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TaxaDTO> llistarTaxes(TaxaCriteria taxaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarTaxes(taxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TramitDTO obtenirTramit(TramitCriteria tramitCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirTramit(tramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TramitDTO> llistarTramits(TramitCriteria tramitCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarTramits(tramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarUnitatsAdministratives(unitatAdministrativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public UnitatMateriaDTO obtenirUnitatMateria(UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirUnitatMateria(unitatMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UnitatMateriaDTO> llistarUnitatsMateries(UnitatMateriaCriteria unitatMateriaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarUnitatsMateries(unitatMateriaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public UsuariDTO obtenirUsuari(UsuariCriteria usuariCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirUsuari(usuariCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<UsuariDTO> llistarUsuaris(UsuariCriteria usuariCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarUsuaris(usuariCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public IdiomaDTO obtenirIdioma(IdiomaCriteria idiomaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirIdioma(idiomaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    
    public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarIdiomes(idiomaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TipusDTO obtenirTipus(TipusCriteria tipusCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirTipus(tipusCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TipusDTO> llistarTipus(TipusCriteria tipusCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarTipus(tipusCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirTipusAfectacio(tipusAfectacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<TipusAfectacioDTO> llistarTipusAfectacio(TipusAfectacioCriteria tipusAfectacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarTipusAfectacio(tipusAfectacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.obtenirTipusIniciacio(iniciacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.llistarTipusIniciacions(iniciacioCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public Integer getNumFitxes(FitxaCriteria fitxaCriteria) throws StrategyException {
        try {
            return rolsacQueryServiceDelegate.getNumFitxes(fitxaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public SilencioDTO obtenirSilenci(Long codSilencio, String idioma)
			throws StrategyException {
		try {
            return rolsacQueryServiceDelegate.obtenirSilenci(codSilencio, idioma);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
	}

}
