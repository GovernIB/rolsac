package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceAdapter;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tractament.TractamentQueryServiceAdapter;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.ejb.UnitatAdministrativaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceAdapter;

public class UnitatAdministrativaQueryServiceAdapter extends UnitatAdministrativaDTO implements UnitatAdministrativaQueryService {

    private static final long serialVersionUID = -3568148291068148995L;

    private UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy;

    public void setUnitatAdministrativaQueryServiceStrategy(UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy) {
        this.unitatAdministrativaQueryServiceStrategy = unitatAdministrativaQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return unitatAdministrativaQueryServiceStrategy instanceof UnitatAdministrativaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public UnitatAdministrativaQueryServiceAdapter(UnitatAdministrativaDTO dto) throws QueryServiceException {    
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    public UnitatAdministrativaQueryServiceAdapter obtenirPare() throws QueryServiceException {
        if (this.getPadre() == null) {return null;}
        try {
            return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirPare(this.getPadre()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "padre.", e);
        }
       
    }

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial() throws QueryServiceException {
        if (this.getEspacioTerrit() == null) {return null;}
        try {
            return (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirEspaiTerritorial(this.getEspacioTerrit()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "espacio territorial.", e);
        }

    }

    public TractamentQueryServiceAdapter obtenirTractament() throws QueryServiceException {
        if (this.getTratamiento() == null) {return null;}
        try {
            return (TractamentQueryServiceAdapter) BeanUtils.getAdapter("tractament", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirTractament(this.getTratamiento()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tratamiento.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarFilles(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFilles(id, unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hijas.", e);
        }
    }

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) throws QueryServiceException {
        try {
            List<EdificiDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarEdificis(id, edificiCriteria);
            List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
            for (EdificiDTO edificiDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), edificiDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "edificios.", e);
        }
    }

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) throws QueryServiceException {
        try {
            List<PersonalDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarPersonal(id, personalCriteria);
            List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
            for (PersonalDTO personalDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), personalDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "personal.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarProcediments(id, procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException {
        try {
            List<TramitDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarTramits(id, tramitCriteria);
            List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
            for (TramitDTO tramitDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tramites.", e);
        }
    }

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) throws QueryServiceException {
        try {
            List<UsuariDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarUsuaris(id, usuariCriteria);
            List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
            for (UsuariDTO usuariDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), usuariDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarSeccions(id, seccioCriteria);
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO seccioDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones.", e);
        }
    }
    
    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarMateries(id, materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public int getNumFilles() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumFilles(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de hijas.", e);
        }
    }

    public int getNumEdificis() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumEdificis(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de edificios.", e);
        }
    }

    public int getNumPersonal() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumPersonal(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de personal.", e);
        }
    }

    public int getNumNormatives() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumNormatives(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }

    public int getNumProcediments() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumProcediments(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }

    public int getNumTramits() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumTramits(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de tramites.", e);
        }
    }

    public int getNumUsuaris() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumUsuaris(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de usuarios.", e);
        }
    }

    public int getNumFitxes() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumFitxes(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    public int getNumSeccions() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumSeccions(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de secciones.", e);
        }
    }
    
    public int getNumMateries() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumMateries(id);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de materias.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotop() throws QueryServiceException {
        if (this.getFotop() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotop(this.getFotop()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto pequenya.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotog() throws QueryServiceException {
        if (this.getFotog() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotog(this.getFotog()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto grande.", e);
        }        
    }

    public ArxiuQueryServiceAdapter obtenirLogoh() throws QueryServiceException {
        if (this.getLogoh() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogoh(this.getLogoh()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo horizontal.", e);
        }        
    }

    public ArxiuQueryServiceAdapter obtenirLogov() throws QueryServiceException {
        if (this.getLogov() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogov(this.getLogov()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo vertical.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirLogos() throws QueryServiceException {
        if (this.getLogos() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogos(this.getLogos()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo saludo horizontal.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirLogot() throws QueryServiceException {
        if (this.getLogot() == null) {return null;}
        try {
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogot(this.getLogot()));
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo saludo vertical.", e);
        }
    }

}
