package es.caib.rolsac.api.v2.normativa.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Afectacion;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de normativas.
 *
 * @ejb.bean
 *  name="sac/api/NormativaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.normativa.ejb.NormativaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class NormativaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -5411864487528239486L;

    private static Log log = LogFactory.getLog(NormativaQueryServiceEJB.class);

    private static final String HQL_NORMATIVA_CLASS = "Normativa";
    private static final String HQL_NORMATIVA_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVA_EXTERNA_CLASS = "NormativaExterna";    
    private static final String HQL_NORMATIVA_ALIAS = "n";
    private static final String HQL_PROCEDIMIENTOS_LOCALES_CLASS = HQL_NORMATIVA_ALIAS + ".procedimientos";
    private static final String HQL_PROCEDIMIENTOS_LOCALES_ALIAS = "p";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene el boletin.
     * @param idButlleti
     * @return ButlletiDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ButlletiDTO obtenirButlleti(long idButlleti) {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setId(String.valueOf(idButlleti));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirButlleti(butlletiCriteria);        
    }

    /**
     * Obtiene lista de normativas afectadas.
     * @param id
     * @return List<NormativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectades(long id) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        Session session = null;
        NormativaDTO dto;
        try {
            session = getSession();
            
            Query query = session.createQuery("SELECT DISTINCT nafec FROM NormativaLocal AS n, n.traducciones AS trad LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE INDEX(trad) = :idioma and n.id = :id ");
            query.setParameter("idioma", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);
            
            List<NormativaLocal> normativaLocalResult = (List<NormativaLocal>) query.list();                    
            for (NormativaLocal normativa : normativaLocalResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }

            query = session.createQuery("SELECT DISTINCT nafec FROM NormativaExterna AS n, n.traducciones AS trad LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE INDEX(trad) = :idioma and n.id = :id ");
            query.setParameter("idioma", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);

            List<NormativaExterna> normativaExternaResult = (List<NormativaExterna>) query.list();
            for (NormativaExterna normativa : normativaExternaResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(false);
                normativaDTOList.add(dto);
            }
        } catch (HibernateException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return normativaDTOList;
    }

    /**
     * Obtiene numero de normativas afectadas.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumAfectades(long id) {
        Session session = null;
        int numResultats = 0;
        try {
            session = getSession();
            
            Query query = session.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaLocal AS n LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE n.id = :id ");            
            query.setParameter("id", id);
            numResultats = ((Integer) query.uniqueResult()).intValue();                    

            query = session.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaExterna AS n LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);
            numResultats = getNumberResults(query);
        } catch (HibernateException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return numResultats;
    }
    
    /**
     * Obtiene lista de normativas afectantes.
     * @param id
     * @return List<NormativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectants(long id) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        Session session = null;
        NormativaDTO dto;
        try {
            session = getSession();
            
            Query query = session.createQuery("SELECT DISTINCT nafec FROM NormativaLocal AS n, n.traducciones AS trad LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE INDEX(trad) = :idioma and n.id = :id ");
            query.setParameter("idioma", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);

            List<NormativaLocal> normativaLocalResult = (List<NormativaLocal>) query.list();                    
            for (NormativaLocal normativa : normativaLocalResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }

            query = session.createQuery("SELECT DISTINCT nafec FROM NormativaExterna AS n, n.traducciones AS trad LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE INDEX(trad) = :idioma and n.id = :id ");
            query.setParameter("idioma", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);

            List<NormativaExterna> normativaExternaResult = (List<NormativaExterna>) query.list();
            for (NormativaExterna normativa : normativaExternaResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(false);
                normativaDTOList.add(dto);
            }
        } catch (HibernateException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return normativaDTOList;
    }
    
    /**
     * Obtiene numero de normativas afectantes.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumAfectants(long id) {
        Session session = null;
        int numResultats = 0;
        try {
            session = getSession();
            
            Query query = session.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaLocal AS n LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);
            numResultats = ((Integer) query.uniqueResult()).intValue();                    

            query = session.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaExterna AS n LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);
            numResultats = getNumberResults(query);
        } catch (HibernateException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return numResultats;
    }
    
    /**
     * Obtiene lista de procedimientos
     * @param id
     * @param procedimentCriteria
     * @return List<ProcedimentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {            
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_CLASS, HQL_NORMATIVA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            NormativaCriteria nc = new NormativaCriteria();
            nc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, nc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>) query.list();
            for (ProcedimientoLocal procediment : procedimentsResult) {
                procedimentsDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class,  procediment, procedimentCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
        } catch (QueryBuilderException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return procedimentsDTOList;
    }

    /**
     * Obtiene el numero de procedimientos
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumProcediments(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;
        try {
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, new ProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_CLASS, HQL_NORMATIVA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            NormativaCriteria nc = new NormativaCriteria();
            nc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, nc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats = getNumberResults(query);
        } catch (HibernateException e) {
            log.error(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
        } catch (QueryBuilderException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return numResultats;
    }

    /**
     * Obtiene la unidad administrativa
     * @param idUniAdm
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUniAdm));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
    }

    /**
     * Obtiene el archivo de la normativa
     * @param idArxiu
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirArxiuNormativa(long idArxiu) {
        return getArxiuDTO(idArxiu);
    }

    /**
     * Obtiene la lista de afectaciones afectantes.
     * @param id
     * @return AfectacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AfectacioDTO> llistarAfectacionsAfectants(Long id) {
        List<AfectacioDTO> afectacioDTOList = new ArrayList<AfectacioDTO>();
        List<Afectacion> afectants = null;
        List<CriteriaObject> criteris;
        Session session = null;
        
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias
        normativaCriteria.setId(String.valueOf(id));       

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            NormativaLocal normativaLocal = (NormativaLocal) query.uniqueResult();
            if (normativaLocal != null) {
                afectants = new ArrayList<Afectacion>(normativaLocal.getAfectantes());
            } else {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, normativaCriteria);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                NormativaExterna normativaExterna = (NormativaExterna) query.uniqueResult();
                if (normativaExterna != null) {
                    afectants = new ArrayList<Afectacion>(normativaExterna.getAfectantes());
                }
            }
            
            for (Afectacion afec: afectants){
                afectacioDTOList.add((AfectacioDTO) BasicUtils.entityToDTO(AfectacioDTO.class,  afec, normativaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
        } catch (QueryBuilderException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return afectacioDTOList;
    }

    /**
     * Obtiene la lista de afectaciones afectadas.
     * @param id
     * @return AfectacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AfectacioDTO> llistarAfectacionsAfectades(Long id) {
        List<AfectacioDTO> afectacioDTOList = new ArrayList<AfectacioDTO>();
        List<Afectacion> afectades = null;
        List<CriteriaObject> criteris;
        Session session = null;
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias
        normativaCriteria.setId(String.valueOf(id));       
        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, normativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVA_LOCAL_CLASS, HQL_NORMATIVA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            NormativaLocal normativaLocal = (NormativaLocal) query.uniqueResult();
            if (normativaLocal != null) {
                afectades = new ArrayList<Afectacion>(normativaLocal.getAfectadas());
            } else {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVA_ALIAS, normativaCriteria);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVA_EXTERNA_CLASS, HQL_NORMATIVA_ALIAS));
                qb = new QueryBuilder(HQL_NORMATIVA_ALIAS, entities, null, null);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                NormativaExterna normativaExterna = (NormativaExterna) query.uniqueResult();
                if (normativaExterna != null) {
                    afectades = new ArrayList<Afectacion>(normativaExterna.getAfectadas());
                }
            }
                        
            for (Afectacion afec: afectades){
                afectacioDTOList.add((AfectacioDTO) BasicUtils.entityToDTO(AfectacioDTO.class,  afec, normativaCriteria.getIdioma()));
            }
        } catch (HibernateException e) {
            log.error(e);
        } catch (CriteriaObjectParseException e) {
            log.error(e);
        } catch (QueryBuilderException e) {
            log.error(e);
        } finally {
            close(session);
        }

        return afectacioDTOList;
    }

}
