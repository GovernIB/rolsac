package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Edificio;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.Personal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.Tratamiento;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Usuario;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.edifici.EdificiDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.co.NormativaByUnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.personal.PersonalCriteria;
import es.caib.rolsac.api.v2.personal.PersonalDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.tractament.TractamentCriteria;
import es.caib.rolsac.api.v2.tractament.TractamentDTO;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;
import es.caib.rolsac.api.v2.usuari.UsuariDTO;



public class UnitatAdministrativaQueryServiceEJB {

    private static Log log = LogFactory.getLog(UnitatAdministrativaQueryServiceEJB.class);
    
    private static final String HQL_UA_CLASS = "UnidadAdministrativa";
    private static final String HQL_UA_ALIAS = "ua";
    
    private static final String HQL_UA_HIJOS_CLASS = HQL_UA_ALIAS + ".hijos";
    private static final String HQL_UA_HIJOS_ALIAS = "hua";
    
    private static final String HQL_EDIFICI_CLASS = HQL_UA_ALIAS + ".edificios";
    private static final String HQL_EDIFICI_ALIAS = "ed";

    private static final String HQL_PERSONAL_CLASS = HQL_UA_ALIAS + ".personal";
    private static final String HQL_PERSONAL_ALIAS = "per";

    private static final String HQL_NORMATIVA_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVA_ALIAS = "n";
    
    private static final String HQL_PROCEDIMIENTO_CLASS = HQL_UA_ALIAS + ".procedimientos";
    private static final String HQL_PROCEDIMIENTO_ALIAS = "p";
    
    private static final String HQL_TRAMIT_CLASS = HQL_UA_ALIAS + ".tramites";
    private static final String HQL_TRAMIT_ALIAS = "t";
    
    private static final String HQL_UNITAT_MATERIA_CLASS = HQL_UA_ALIAS + ".unidadesMaterias";
    private static final String HQL_UNITAT_MATERIA_ALIAS = "um";
    
    private static final String HQL_MATERIA_CLASS = HQL_UNITAT_MATERIA_ALIAS + ".materia";
    private static final String HQL_MATERIA_ALIAS = "um";
    
    private static final String HQL_USUARI_CLASS = HQL_UA_ALIAS + ".usuarios";
    private static final String HQL_USUARI_ALIAS = "usu";
    
    private static final String HQL_FITXA_UA_CLASS = HQL_UA_ALIAS + ".fichasUA";
    private static final String HQL_FITXA_UA_ALIAS = "fua";
    
    private static final String HQL_FITXA_CLASS = HQL_FITXA_UA_ALIAS + ".ficha";
    private static final String HQL_FITXA_ALIAS = "f";
    
    private static final String HQL_SECCION_CLASS = HQL_FITXA_UA_ALIAS + ".seccion";
    private static final String HQL_SECCION_ALIAS = "s";
    
    private static final String HQL_TRATAMIENTO_CLASS = "Tratamiento";
    private static final String HQL_TRATAMIENTO_ALIAS = "t";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";

    public UnitatAdministrativaDTO obtenirPare(long idPare) {
        UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
        uaCriteria.setId(String.valueOf(idPare));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(uaCriteria);
    }

    public EspaiTerritorialDTO obtenirEspaiTerritorial(long idEt) {
        EspaiTerritorialCriteria etCriteria = new EspaiTerritorialCriteria();
        etCriteria.setId(String.valueOf(idEt));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirEspaiTerritorial(etCriteria);
    }

    public TractamentDTO obtenirTractament(long idTract) {
        List<CriteriaObject> criteris;
        TractamentDTO tractDTO = null;
        TractamentCriteria tractamentCriteria = new TractamentCriteria();
        Session sessio = null;

        try {
            tractamentCriteria.setId(String.valueOf(idTract));
            criteris = BasicUtils.parseCriterias(TractamentCriteria.class, HQL_TRATAMIENTO_ALIAS, HQL_TRADUCCIONES_ALIAS, tractamentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TRATAMIENTO_CLASS, HQL_TRATAMIENTO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_TRATAMIENTO_ALIAS, entities, tractamentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Tratamiento tractament = (Tratamiento) query.uniqueResult();
            sessio.close();

            if (tractament != null) {
                tractDTO = (TractamentDTO) BasicUtils.entityToDTO(TractamentDTO.class, tractament, tractamentCriteria.getIdioma());
            }
        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace();
                }
            }
        }

        return tractDTO;
    }

    public List<UnitatAdministrativaDTO> llistarFilles(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        
        List<UnitatAdministrativaDTO> unitatAdministrativaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_UA_HIJOS_CLASS, HQL_UA_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_HIJOS_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();

            for (UnidadAdministrativa unidadAdministrativa : unitatAdministrativaResult) {
                unitatAdministrativaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unidadAdministrativa, unitatAdministrativaCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return unitatAdministrativaDTOList;
        
    }

    public List<EdificiDTO> llistarEdificis(long id, EdificiCriteria edificiCriteria) {
        List<EdificiDTO> edificiDTOList = new ArrayList<EdificiDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS, edificiCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, edificiCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Edificio> edificiResult = (List<Edificio>) query.list();

            for (Edificio edifici : edificiResult) {
                edificiDTOList.add((EdificiDTO) BasicUtils.entityToDTO(EdificiDTO.class,  edifici, edificiCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return edificiDTOList;
    }

    public List<PersonalDTO> llistarPersonal(long id, PersonalCriteria personalCriteria) {
        List<PersonalDTO> personalDTOList = new ArrayList<PersonalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, personalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Personal> personalResult = (List<Personal>) query.list();

            for (Personal personal : personalResult) {
                personalDTOList.add((PersonalDTO) BasicUtils.entityToDTO(PersonalDTO.class, personal, null));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return personalDTOList;
    }
 
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<FromClause> entities = new ArrayList<FromClause>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            
            sessio = HibernateUtils.getSessionFactory().openSession();
            
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            CriteriaObject normativaByUACO = new NormativaByUnitatAdministrativaCriteria(HQL_UA_ALIAS);
            normativaByUACO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByUACO);
            
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".unidadAdministrativa", HQL_UA_ALIAS));  
            QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);
            
            Query query = qb.createQuery(sessio);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();

            sessio.close();

            NormativaDTO dto;
            for (NormativaLocal normativa : normativasLocalesResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }

        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return normativaDTOList;
    }

    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<ProcedimientoLocal> procedimentResult = (List<ProcedimientoLocal>) query.list();

            for (ProcedimientoLocal procediment : procedimentResult) {
                procedimentDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class,  procediment, procedimentCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return procedimentDTOList;
    }

    public List<TramitDTO> llistarTramits(long id, TramitCriteria tramitCriteria) {
        List<TramitDTO> tramitDTOList = new ArrayList<TramitDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, HQL_TRADUCCIONES_ALIAS, tramitCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, tramitCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Tramite> tramitResult = (List<Tramite>) query.list();

            for (Tramite tramit : tramitResult) {
                tramitDTOList.add((TramitDTO) BasicUtils.entityToDTO(TramitDTO.class,  tramit, tramitCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return tramitDTOList;
    }

    public List<UsuariDTO> llistarUsuaris(long id, UsuariCriteria usuariCriteria) {
        List<UsuariDTO> usuariDTOList = new ArrayList<UsuariDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, usuariCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Usuario> usuarioResult = (List<Usuario>) query.list();

            for (Usuario usuario : usuarioResult) {
                usuariDTOList.add((UsuariDTO) BasicUtils.entityToDTO(UsuariDTO.class, usuario, null));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuariDTOList;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        Set<FitxaDTO> fitxaDTOSet = new HashSet<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            
            if (StringUtils.isBlank(fitxaCriteria.getOrdenacio())) {
                fitxaCriteria.setOrdenacio(HQL_FITXA_UA_ALIAS + ".orden");
                }
            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Ficha> fichaResult = (List<Ficha>) query.list();

            for (Ficha fitxa : fichaResult) {
                fitxaDTOSet.add((FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class,  fitxa, fitxaCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<FitxaDTO>(fitxaDTOSet);
    }

    public List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria) {
        Set<SeccioDTO> seccioDTOSet = new HashSet<SeccioDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            

            if (StringUtils.isBlank(seccioCriteria.getOrdenacio())) {
                seccioCriteria.setOrdenacio(HQL_FITXA_UA_ALIAS + ".ordenseccion");
                }
            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, HQL_TRADUCCIONES_ALIAS, seccioCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, seccioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Seccion> seccionResult = (List<Seccion>) query.list();

            for (Seccion seccio : seccionResult) {
                seccioDTOSet.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class,  seccio, seccioCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ArrayList<SeccioDTO>(seccioDTOSet);
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Materia> materiaResult = (List<Materia>) query.list();

            for (Materia materia : materiaResult) {
                materiaDTOList.add((MateriaDTO) BasicUtils.entityToDTO(MateriaDTO.class,  materia, materiaCriteria.getIdioma()));
            }
            
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return materiaDTOList;
    }

    public ArxiuDTO obtenirFotop(Long fotop) {
        return EJBUtils.getArxiuDTO(fotop);
    }

    public ArxiuDTO obtenirFotog(Long fotog) {
        return EJBUtils.getArxiuDTO(fotog);
    }

    public ArxiuDTO obtenirLogoh(Long logoh) {
        return EJBUtils.getArxiuDTO(logoh);
    }

    public ArxiuDTO obtenirLogov(Long logov) {
        return EJBUtils.getArxiuDTO(logov);
    }

    public ArxiuDTO obtenirLogos(Long logos) {
        return EJBUtils.getArxiuDTO(logos);
    }

    public ArxiuDTO obtenirLogot(Long logot) {
        return EJBUtils.getArxiuDTO(logot);
    }

    public int getNumFilles(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, new UnitatAdministrativaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_UA_HIJOS_CLASS, HQL_UA_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_HIJOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumEdificis(Long id) {
        
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, HQL_TRADUCCIONES_ALIAS, new EdificiCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_EDIFICI_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumPersonal(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(PersonalCriteria.class, HQL_PERSONAL_ALIAS, new PersonalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_PERSONAL_CLASS, HQL_PERSONAL_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PERSONAL_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumProcediments(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTO_ALIAS, new ProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTO_CLASS, HQL_PROCEDIMIENTO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTO_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumNormatives(long id) {        
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        List<FromClause> entities = new ArrayList<FromClause>();
        
        try {            

            sessio = HibernateUtils.getSessionFactory().openSession();
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, new NormativaCriteria());
            CriteriaObject normativaByUACO = new NormativaByUnitatAdministrativaCriteria(HQL_UA_ALIAS);
            normativaByUACO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByUACO);
                        
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVA_ALIAS + ".unidadAdministrativa", HQL_UA_ALIAS));  
            
            QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            Query query = qb.createQuery(sessio);
            numResultats = ((Integer) query.uniqueResult()).intValue();

            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);
            
            sessio.close();

        } catch (HibernateException e) {
            log.error(e);
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            log.error(e);
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            log.error(e);
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    log.error(e);
                    e.printStackTrace(); // TODO: delete me.
                }
            }
        }

        return numResultats;
    }

    public int getNumTramits(Long id) {
        
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(TramitCriteria.class, HQL_TRAMIT_ALIAS, new TramitCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_TRAMIT_CLASS, HQL_TRAMIT_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_TRAMIT_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumUsuaris(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {           
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, new UsuariCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_USUARI_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumFitxes(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, new FitxaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumSeccions(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {
            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCION_ALIAS, new SeccioCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_UA_CLASS, HQL_FITXA_UA_ALIAS));
            entities.add(new FromClause(HQL_SECCION_CLASS, HQL_SECCION_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SECCION_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }

    public int getNumMateries(Long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, new MateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            entities.add(new FromClause(HQL_UNITAT_MATERIA_CLASS, HQL_UNITAT_MATERIA_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
                       
            UnitatAdministrativaCriteria uac = new UnitatAdministrativaCriteria();
            uac.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, uac);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
            sessio.close();
            
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (CriteriaObjectParseException e) {
            e.printStackTrace();
        } catch (QueryBuilderException e) {
            e.printStackTrace();
        } finally {
            if (sessio != null && sessio.isOpen()) {
                try {
                    sessio.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }

        return numResultats;
    }
 
}
