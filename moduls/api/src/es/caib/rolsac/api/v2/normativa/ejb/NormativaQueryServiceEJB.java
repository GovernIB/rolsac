package es.caib.rolsac.api.v2.normativa.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Tipo;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiCriteria;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tipus.TipusCriteria;
import es.caib.rolsac.api.v2.tipus.TipusDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceEJB {

    private static Log log = LogFactory.getLog(NormativaQueryServiceEJB.class);
    
    private static final String HQL_TIPUS_CLASS = "Tipo";
    private static final String HQL_TIPUS_ALIAS = "T";

    private static final String HQL_NORMATIVA_CLASS = "Normativa";
    private static final String HQL_NORMATIVA_ALIAS = "n";
        
    private static final String HQL_PROCEDIMIENTOS_LOCALES_CLASS = HQL_NORMATIVA_ALIAS + ".procedimientos";
    private static final String HQL_PROCEDIMIENTOS_LOCALES_ALIAS = "p";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public ButlletiDTO obtenirButlleti(long idButlleti) {
        ButlletiCriteria butlletiCriteria = new ButlletiCriteria();
        butlletiCriteria.setId(String.valueOf(idButlleti));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirButlleti(butlletiCriteria);        
    }

    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectades(long id) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        Session sessio = null;
        NormativaDTO dto;

        try {

            sessio = HibernateUtils.getSessionFactory().openSession();
            
            Query query = sessio.createQuery("SELECT DISTINCT nafec FROM NormativaLocal AS n, n.traducciones AS trad LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE INDEX(trad) = :ca and n.id = :id ");
            query.setParameter("ca", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);
            
            List<NormativaLocal> normativaLocalResult = (List<NormativaLocal>) query.list();                    

            for (NormativaLocal normativa : normativaLocalResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }

            query = sessio.createQuery("SELECT DISTINCT nafec FROM NormativaExterna AS n, n.traducciones AS trad LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE INDEX(trad) = :ca and n.id = :id ");
            query.setParameter("ca", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);

            List<NormativaExterna> normativaExternaResult = (List<NormativaExterna>) query.list();
            
            for (NormativaExterna normativa : normativaExternaResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(false);
                normativaDTOList.add(dto);
            }

            sessio.close();

        } catch (HibernateException e) {
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

    public int getNumAfectades(long id) {
        Session sessio = null;
        int numResultats = 0;

        try {

            sessio = HibernateUtils.getSessionFactory().openSession();
            
            Query query = sessio.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaLocal AS n LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE n.id = :id ");            
            query.setParameter("id", id);
            
            numResultats = ((Integer) query.uniqueResult()).intValue();                    


            query = sessio.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaExterna AS n LEFT JOIN n.afectadas AS afec LEFT JOIN afec.normativa AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);

            numResultats += ((Integer) query.uniqueResult()).intValue();

            sessio.close();

        } catch (HibernateException e) {
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
    
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarAfectants(long id) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        Session sessio = null;
        NormativaDTO dto;

        try {

            sessio = HibernateUtils.getSessionFactory().openSession();
            
            Query query = sessio.createQuery("SELECT DISTINCT nafec FROM NormativaLocal AS n, n.traducciones AS trad LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE INDEX(trad) = :ca and n.id = :id ");
            query.setParameter("ca", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);
            
            List<NormativaLocal> normativaLocalResult = (List<NormativaLocal>) query.list();                    

            for (NormativaLocal normativa : normativaLocalResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }

            query = sessio.createQuery("SELECT DISTINCT nafec FROM NormativaExterna AS n, n.traducciones AS trad LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE INDEX(trad) = :ca and n.id = :id ");
            query.setParameter("ca", BasicUtils.getDefaultLanguage());
            query.setParameter("id", id);

            List<NormativaExterna> normativaExternaResult = (List<NormativaExterna>) query.list();
            
            for (NormativaExterna normativa : normativaExternaResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class,  normativa, BasicUtils.getDefaultLanguage());
                dto.setLocal(false);
                normativaDTOList.add(dto);
            }

            sessio.close();

        } catch (HibernateException e) {
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
    
    public int getNumAfectants(long id) {
        Session sessio = null;
        int numResultats = 0;

        try {

            sessio = HibernateUtils.getSessionFactory().openSession();
            
            Query query = sessio.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaLocal AS n LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);
            
            numResultats = ((Integer) query.uniqueResult()).intValue();                    


            query = sessio.createQuery("SELECT COUNT(DISTINCT nafec) FROM NormativaExterna AS n LEFT JOIN n.afectantes AS afec LEFT JOIN afec.afectante AS nafec WHERE n.id = :id ");
            query.setParameter("id", id);

            numResultats += ((Integer) query.uniqueResult()).intValue();

            sessio.close();

        } catch (HibernateException e) {
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
    
    public List<ProcedimentDTO> llistarProcediments(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

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

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>) query.list();

            for (ProcedimientoLocal procediment : procedimentsResult) {
                procedimentsDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class,  procediment, procedimentCriteria.getIdioma()));
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

        return procedimentsDTOList;
    }

    public int getNumProcediments(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
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

    public TipusDTO obtenirTipus(long idTipus) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        TipusDTO tipusDTO = null;
        Session sessio = null;
        TipusCriteria tipusCriteria = new TipusCriteria();
        tipusCriteria.setId(String.valueOf(idTipus));
        
        try {
            criteris = BasicUtils.parseCriterias(TipusCriteria.class, HQL_TIPUS_ALIAS, HQL_TRADUCCIONES_ALIAS, tipusCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_TIPUS_CLASS, HQL_TIPUS_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_TIPUS_ALIAS, entities, tipusCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            Tipo tipus = (Tipo) query.uniqueResult();
            sessio.close();

            if (tipus != null) {
                tipusDTO = (TipusDTO) BasicUtils.entityToDTO(
                        TipusDTO.class, 
                        tipus,
                        tipusCriteria.getIdioma());
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

        return tipusDTO;
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUniAdm));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
    }

    public ArxiuDTO obtenirArxiuNormativa(long idArchivo) {
        return EJBUtils.getArxiuDTO(idArchivo);
    }

}
