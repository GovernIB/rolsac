package es.caib.rolsac.api.v2.seccio.ejb;

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
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.Seccion;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.ejb.MateriaQueryServiceEJB;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceEJB {

    private static Log log = LogFactory.getLog(MateriaQueryServiceEJB.class);
    
    private static final String HQL_SECCIO_CLASS = "Seccion";
    private static final String HQL_SECCIO_ALIAS = "s";
    
    private static final String HQL_SECCIO_FITXA_CLASS = HQL_SECCIO_ALIAS + ".fichasUA";
    private static final String HQL_SECCIO_FITXA_ALIAS = "fua";
    
    private static final String HQL_FITXA_CLASS = HQL_SECCIO_FITXA_ALIAS + ".ficha";
    private static final String HQL_FITXA_ALIAS = "f";
    
    private static final String HQL_UA_CLASS = HQL_SECCIO_FITXA_ALIAS + ".unidadAdministrativa";
    private static final String HQL_UA_ALIAS = "f";
    
    private static final String HQL_FILLES_CLASS = HQL_SECCIO_ALIAS + ".hijos";
    private static final String HQL_FILLES_ALIAS = "sf";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public int getNumFilles(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_FILLES_ALIAS, new SeccioCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_FILLES_CLASS, HQL_FILLES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FILLES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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

    public int getNumFitxes(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FITXA_ALIAS, new FitxaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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

    public int getNumPares(long id) {
        return this.llistarPares(id).size();
    }

    public int getNumUnitatsAdministratives(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;
        
        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, new UnitatAdministrativaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
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
    
    public List<SeccioDTO> llistarPares(long id) {
        
        List<SeccioDTO> llistaPares = new ArrayList<SeccioDTO>();
        SeccioDTO pare = null;
        
        while ((pare = obtenirSeccioPare(id)) != null){
            
            llistaPares.add(pare);
            
            id = pare.getId();                        
            
        }

        return llistaPares;
        
    }
 
    private SeccioDTO obtenirSeccioPare(Long id) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        SeccioDTO fill = ejb.obtenirSeccio(seccioCriteria);
        if (fill.getPadre() == null){
            return null;
            } else {
                seccioCriteria.setId(String.valueOf(fill.getPadre()));
                return ejb.obtenirSeccio(seccioCriteria);   
            }        
    }
    
    public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria) {
        List<SeccioDTO> seccioDTOList = new ArrayList<SeccioDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            if (StringUtils.isBlank(seccioCriteria.getOrdenacio())) {
                seccioCriteria.setOrdenacio(HQL_FILLES_ALIAS + ".orden");
                }
            
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, seccioCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_FILLES_CLASS, HQL_FILLES_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FILLES_ALIAS, entities, seccioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Seccion> seccioResult = (List<Seccion>) query.list();

            for (Seccion seccio : seccioResult) {
                seccioDTOList.add((SeccioDTO) BasicUtils.entityToDTO(SeccioDTO.class,  seccio, seccioCriteria.getIdioma()));
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

        return seccioDTOList;
    }

    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {
        Set<FitxaDTO> fitxaDTOSet = new HashSet<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
            entities.add(new FromClause(HQL_FITXA_CLASS, HQL_FITXA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FITXA_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Ficha> fitxaResult = (List<Ficha>) query.list();

            for (Ficha fitxa : fitxaResult) {
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

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        Set<UnitatAdministrativaDTO> uaDTOSet = new HashSet<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_FILLES_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_SECCIO_CLASS, HQL_SECCIO_ALIAS));
            entities.add(new FromClause(HQL_SECCIO_FITXA_CLASS, HQL_SECCIO_FITXA_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            SeccioCriteria sc = new SeccioCriteria();
            sc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(SeccioCriteria.class, HQL_SECCIO_ALIAS, sc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> uaResult = (List<UnidadAdministrativa>) query.list();

            for (UnidadAdministrativa unitatAdministrativa : uaResult) {
                uaDTOSet.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unitatAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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
        return new ArrayList<UnitatAdministrativaDTO>(uaDTOSet);
    }
    
    public SeccioDTO obtenirPare(Long padre) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(padre));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirSeccio(seccioCriteria);
    }
    
}
