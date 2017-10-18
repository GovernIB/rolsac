package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.ArrayList;
import java.util.List;

import es.caib.rolsac.api.v2.fitxaUA.FitxaUACriteria;

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
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.servicio.ServicioQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.tractament.TractamentCriteria;
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
    
    private String rolsacUrl;
    
    private UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy;

    public UnitatAdministrativaQueryServiceAdapter() {
    	 super();
    }
    
    public UnitatAdministrativaQueryServiceAdapter(String rolsacUrl) {
     	super();
     	this.rolsacUrl = rolsacUrl;
     	
    }
    	 
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
    	if (unitatAdministrativaQueryServiceStrategy != null) {
    		unitatAdministrativaQueryServiceStrategy.setUrl(rolsacUrl);
    	}
    }
    	 
    public void setUnitatAdministrativaQueryServiceStrategy(UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy) {
        this.unitatAdministrativaQueryServiceStrategy = unitatAdministrativaQueryServiceStrategy;      
        if (this.unitatAdministrativaQueryServiceStrategy != null && rolsacUrl != null) {
        	this.unitatAdministrativaQueryServiceStrategy.setUrl(rolsacUrl);
        }
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
        if (this.getPadre() == null || this.getIdioma() == null) {return null;}
        try {
        	UnitatAdministrativaQueryServiceAdapter uqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirPare(this.getPadre(), this.getIdioma()));
        	if (uqsa != null && rolsacUrl != null) {
        		uqsa.setRolsacUrl(rolsacUrl);
        	}
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "padre.", e);
        }
       
    }

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial() throws QueryServiceException {
        if (this.getEspacioTerrit() == null) {return null;}
        try {
        	EspaiTerritorialQueryServiceAdapter uqsa = (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirEspaiTerritorial(this.getEspacioTerrit()));
            if (uqsa != null && rolsacUrl != null) {
        		uqsa.setRolsacUrl(rolsacUrl);
        	}
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "espacio territorial.", e);
        }

    }

    public TractamentQueryServiceAdapter obtenirTractament(TractamentCriteria tractamentCriteria) throws QueryServiceException {
        if (this.getTratamiento() == null) {return null;}
        try {
        	TractamentQueryServiceAdapter uqsa = (TractamentQueryServiceAdapter) BeanUtils.getAdapter("tractament", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirTractament(this.getTratamiento(), tractamentCriteria));
            /*if (uqsa != null && rolsacUrl != null) {
        		uqsa.setRolsacUrl(rolsacUrl);
        	}*/
        	return uqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tratamiento.", e);
        }
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarFilles(UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws QueryServiceException {
        try {
            List<UnitatAdministrativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFilles(getId(), unitatAdministrativaCriteria);
            List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
            for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            	UnitatAdministrativaQueryServiceAdapter tqsa = (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO);
            	if (tqsa != null && rolsacUrl != null) {
            		tqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(tqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "hijas.", e);
        }
    }

    public List<Long> llistarDescendents() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.llistarDescendents(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "descendientes.", e);
        }
    }

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) throws QueryServiceException {
        try {
            List<EdificiDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarEdificis(getId(), edificiCriteria);
            List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
            for (EdificiDTO edificiDTO : llistaDTO) {
            	EdificiQueryServiceAdapter eqsa = (EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), edificiDTO);
            	if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "edificios.", e);
        }
    }

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) throws QueryServiceException {
        try {
            List<PersonalDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarPersonal(getId(), personalCriteria);
            List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
            for (PersonalDTO personalDTO : llistaDTO) {
            	PersonalQueryServiceAdapter eqsa = (PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), personalDTO);
            	if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "personal.", e);
        }
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException {
        try {
            List<NormativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarNormatives(getId(), normativaCriteria);
            List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
            for (NormativaDTO normativaDTO : llistaDTO) {
            	NormativaQueryServiceAdapter eqsa = (NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO);
            	if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "normativas.", e);
        }
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) throws QueryServiceException {
        try {
            List<ProcedimentDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarProcediments(getId(), procedimentCriteria);
            List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
            for (ProcedimentDTO procedimentDTO : llistaDTO) {
            	ProcedimentQueryServiceAdapter eqsa = (ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO);
            	if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "procedimientos.", e);
        }
    }

    public List<ServicioQueryServiceAdapter> llistarServicios(ServicioCriteria servicioCriteria) throws QueryServiceException {
        try {
            List<ServicioDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarServicios(getId(), servicioCriteria);
            List<ServicioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ServicioQueryServiceAdapter>();
            for (ServicioDTO servicioDTO : llistaDTO) {
            	ServicioQueryServiceAdapter eqsa = (ServicioQueryServiceAdapter) BeanUtils.getAdapter("servicio", getStrategy(), servicioDTO);
            	if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "servicios.", e);
        }
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) throws QueryServiceException {
        try {
            List<TramitDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarTramits(getId(), tramitCriteria);
            List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
            for (TramitDTO tramitDTO : llistaDTO) {
            	TramitQueryServiceAdapter eqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO);
    			if (eqsa != null && rolsacUrl != null) {
            		eqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(eqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "tramites.", e);
        }
    }

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) throws QueryServiceException {
        try {
            List<UsuariDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarUsuaris(getId(), usuariCriteria);
            List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
            for (UsuariDTO usuariDTO : llistaDTO) {
            	UsuariQueryServiceAdapter uqsa = (UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), usuariDTO);
            	if (uqsa != null && rolsacUrl != null) {
            		uqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(uqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "usuarios.", e);
        }
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria, FitxaUACriteria fitxaUACriteria) throws QueryServiceException {
        try {
            List<FitxaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFitxes(getId(), fitxaCriteria, fitxaUACriteria);
            List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
            for (FitxaDTO fitxaDTO : llistaDTO) {
            	FitxaQueryServiceAdapter fqsa = (FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "fichas.", e);
        }
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) throws QueryServiceException {
        try {
            List<SeccioDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarSeccions(getId(), seccioCriteria);
            List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
            for (SeccioDTO seccioDTO : llistaDTO) {
            	SeccioQueryServiceAdapter fqsa = (SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "secciones.", e);
        }
    }
    
    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) throws QueryServiceException {
        try {
            List<MateriaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarMateries(getId(), materiaCriteria);
            List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
            for (MateriaDTO materiaDTO : llistaDTO) {
            	MateriaQueryServiceAdapter fqsa = (MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO);
            	if (fqsa != null && rolsacUrl != null) {
            		fqsa.setRolsacUrl(rolsacUrl);
            	}
            	llistaQueryServiceAdapter.add(fqsa);
            }
            return llistaQueryServiceAdapter;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "materias.", e);
        }
    }

    public int getNumFilles() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumFilles(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de hijas.", e);
        }
    }

    public int getNumEdificis() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumEdificis(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de edificios.", e);
        }
    }

    public int getNumPersonal() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumPersonal(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de personal.", e);
        }
    }

    public int getNumNormatives() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumNormatives(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de normativas.", e);
        }
    }

    public int getNumProcediments() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumProcediments(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de procedimientos.", e);
        }
    }
    
    public int getNumServicios() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumServicios(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de servicios.", e);
        }
    }

    public int getNumTramits() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumTramits(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de tramites.", e);
        }
    }

    public int getNumUsuaris() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumUsuaris(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de usuarios.", e);
        }
    }

    public int getNumFitxes() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumFitxes(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de fichas.", e);
        }
    }

    public int getNumSeccions() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumSeccions(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de secciones.", e);
        }
    }
    
    public int getNumMateries() throws QueryServiceException {
        try {
            return unitatAdministrativaQueryServiceStrategy.getNumMateries(getId());
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "numero de materias.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotop() throws QueryServiceException {
        if (this.getFotop() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotop(this.getFotop()));
        	if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto pequenya.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirFotog() throws QueryServiceException {
        if (this.getFotog() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotog(this.getFotog()));
            if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "foto grande.", e);
        }        
    }

    public ArxiuQueryServiceAdapter obtenirLogoh() throws QueryServiceException {
        if (this.getLogoh() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogoh(this.getLogoh()));
            if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo horizontal.", e);
        }        
    }

    public ArxiuQueryServiceAdapter obtenirLogov() throws QueryServiceException {
        if (this.getLogov() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogov(this.getLogov()));
            if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo vertical.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirLogos() throws QueryServiceException {
        if (this.getLogos() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogos(this.getLogos()));
            if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo saludo horizontal.", e);
        }
    }

    public ArxiuQueryServiceAdapter obtenirLogot() throws QueryServiceException {
        if (this.getLogot() == null) {return null;}
        try {
        	ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogot(this.getLogot()));
            if (aqsa != null && rolsacUrl != null) {
        		aqsa.setRolsacUrl(rolsacUrl);
        	}
        	return aqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "logo saludo vertical.", e);
        }
    }

}
