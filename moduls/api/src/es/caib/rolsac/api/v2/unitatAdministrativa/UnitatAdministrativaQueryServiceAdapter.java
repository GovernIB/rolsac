package es.caib.rolsac.api.v2.unitatAdministrativa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceAdapter;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceAdapter;
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

public class UnitatAdministrativaQueryServiceAdapter extends UnitatAdministrativaDTO implements
        UnitatAdministrativaQueryService {

    private static Log log = LogFactory.getLog(UnitatAdministrativaQueryServiceAdapter.class);
    
    private UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy;

    public void setUnitatAdministrativaQueryServiceStrategy(UnitatAdministrativaQueryServiceStrategy unitatAdministrativaQueryServiceStrategy) {
        this.unitatAdministrativaQueryServiceStrategy = unitatAdministrativaQueryServiceStrategy;
    }

    private STRATEGY getStrategy() {
        return unitatAdministrativaQueryServiceStrategy instanceof UnitatAdministrativaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

    public UnitatAdministrativaQueryServiceAdapter(UnitatAdministrativaDTO dto) {    
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando UnitatAdministrativaQueryServiceAdapter.", e);
        }
    }
    
    public UnitatAdministrativaQueryServiceAdapter obtenirPare() {
        if (this.getPadre() == null) {return null;}
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirPare(this.getPadre()));
       
    }

    public EspaiTerritorialQueryServiceAdapter obtenirEspaiTerritorial() {
        if (this.getEspacioTerrit() == null) {return null;}
        return (EspaiTerritorialQueryServiceAdapter) BeanUtils.getAdapter("espaiTerritorial", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirEspaiTerritorial(this.getEspacioTerrit()));

    }

    public TractamentQueryServiceAdapter obtenirTractament() {
        if (this.getTratamiento() == null) {return null;}
        return (TractamentQueryServiceAdapter) BeanUtils.getAdapter("tractament", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirTractament(this.getTratamiento()));
    }

    public List<UnitatAdministrativaQueryServiceAdapter> llistarFilles(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        
        List<UnitatAdministrativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFilles(id, unitatAdministrativaCriteria);
        List<UnitatAdministrativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UnitatAdministrativaQueryServiceAdapter>();
        for (UnitatAdministrativaDTO unitatAdministrativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), unitatAdministrativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<EdificiQueryServiceAdapter> llistarEdificis(EdificiCriteria edificiCriteria) {
        List<EdificiDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarEdificis(id, edificiCriteria);
        List<EdificiQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<EdificiQueryServiceAdapter>();
        for (EdificiDTO edificiDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((EdificiQueryServiceAdapter) BeanUtils.getAdapter("edifici", getStrategy(), edificiDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<PersonalQueryServiceAdapter> llistarPersonal(PersonalCriteria personalCriteria) {
        List<PersonalDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarPersonal(id, personalCriteria);
        List<PersonalQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<PersonalQueryServiceAdapter>();
        for (PersonalDTO personalDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((PersonalQueryServiceAdapter) BeanUtils.getAdapter("personal", getStrategy(), personalDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) {
        List<NormativaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarNormatives(id, normativaCriteria);
        List<NormativaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<NormativaQueryServiceAdapter>();
        for (NormativaDTO normativaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((NormativaQueryServiceAdapter) BeanUtils.getAdapter("normativa", getStrategy(), normativaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<ProcedimentQueryServiceAdapter> llistarProcediments(ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarProcediments(id, procedimentCriteria);
        List<ProcedimentQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<ProcedimentQueryServiceAdapter>();
        for (ProcedimentDTO procedimentDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((ProcedimentQueryServiceAdapter) BeanUtils.getAdapter("procediment", getStrategy(), procedimentDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<TramitQueryServiceAdapter> llistarTramits(TramitCriteria tramitCriteria) {
        List<TramitDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarTramits(id, tramitCriteria);
        List<TramitQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<TramitQueryServiceAdapter>();
        for (TramitDTO tramitDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), tramitDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<UsuariQueryServiceAdapter> llistarUsuaris(UsuariCriteria usuariCriteria) {
        List<UsuariDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarUsuaris(id, usuariCriteria);
        List<UsuariQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<UsuariQueryServiceAdapter>();
        for (UsuariDTO usuariDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((UsuariQueryServiceAdapter) BeanUtils.getAdapter("usuari", getStrategy(), usuariDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<FitxaQueryServiceAdapter> llistarFitxes(FitxaCriteria fitxaCriteria) {
        List<FitxaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarFitxes(id, fitxaCriteria);
        List<FitxaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<FitxaQueryServiceAdapter>();
        for (FitxaDTO fitxaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((FitxaQueryServiceAdapter) BeanUtils.getAdapter("fitxa", getStrategy(), fitxaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria) {
        List<SeccioDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarSeccions(id, seccioCriteria);
        List<SeccioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<SeccioQueryServiceAdapter>();
        for (SeccioDTO seccioDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((SeccioQueryServiceAdapter) BeanUtils.getAdapter("seccio", getStrategy(), seccioDTO));
        }
        return llistaQueryServiceAdapter;
    }
    
    public List<MateriaQueryServiceAdapter> llistarMateries(MateriaCriteria materiaCriteria) {
        List<MateriaDTO> llistaDTO = unitatAdministrativaQueryServiceStrategy.llistarMateries(id, materiaCriteria);
        List<MateriaQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<MateriaQueryServiceAdapter>();
        for (MateriaDTO materiaDTO : llistaDTO) {
            llistaQueryServiceAdapter.add((MateriaQueryServiceAdapter) BeanUtils.getAdapter("materia", getStrategy(), materiaDTO));
        }
        return llistaQueryServiceAdapter;
    }

    public int getNumFilles() {
        return unitatAdministrativaQueryServiceStrategy.getNumFilles(id);
    }

    public int getNumEdificis() {
        return unitatAdministrativaQueryServiceStrategy.getNumEdificis(id);
    }

    public int getNumPersonal() {
        return unitatAdministrativaQueryServiceStrategy.getNumPersonal(id);
    }

    public int getNumNormatives() {
        return unitatAdministrativaQueryServiceStrategy.getNumNormatives(id);
    }

    public int getNumProcediments() {
        return unitatAdministrativaQueryServiceStrategy.getNumProcediments(id);
    }

    public int getNumTramits() {
        return unitatAdministrativaQueryServiceStrategy.getNumTramits(id);
    }

    public int getNumUsuaris() {
        return unitatAdministrativaQueryServiceStrategy.getNumUsuaris(id);
    }

    public int getNumFitxes() {
        return unitatAdministrativaQueryServiceStrategy.getNumFitxes(id);
    }

    public int getNumSeccions() {
        return unitatAdministrativaQueryServiceStrategy.getNumSeccions(id);
    }
    
    public int getNumMateries() {
        return unitatAdministrativaQueryServiceStrategy.getNumMateries(id);
    }

    public ArxiuQueryServiceAdapter obtenirFotop() {
        if (this.getFotop() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotop(this.getFotop()));
    }

    public ArxiuQueryServiceAdapter obtenirFotog() {
        if (this.getFotog() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirFotog(this.getFotog()));        
    }

    public ArxiuQueryServiceAdapter obtenirLogoh() {
        if (this.getLogoh() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogoh(this.getLogoh()));        
    }

    public ArxiuQueryServiceAdapter obtenirLogov() {
        if (this.getLogov() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogov(this.getLogov()));
    }

    public ArxiuQueryServiceAdapter obtenirLogos() {
        if (this.getLogos() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogos(this.getLogos()));
    }

    public ArxiuQueryServiceAdapter obtenirLogot() {
        if (this.getLogot() == null) {return null;}
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), unitatAdministrativaQueryServiceStrategy.obtenirLogot(this.getLogot()));
    }

}
