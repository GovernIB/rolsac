package es.caib.rolsac.api.v2.fetVital.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

public class FetVitalQueryServiceEJB {
    
    private static Log log = LogFactory.getLog(FetVitalQueryServiceEJB.class);

    private static final String HQL_FET_VITAL_CLASS = "HechoVital";
    private static final String HQL_FET_VITAL_ALIAS = "fv";
    
    private static final String HQL_FICHAS_CLASS = HQL_FET_VITAL_ALIAS + ".fichas";
    private static final String HQL_FICHAS_ALIAS = "f"; 
    
    private static final String HQL_FET_VITAL_AGRUPACIO_CLASS = HQL_FET_VITAL_ALIAS + ".hechosVitalesAgrupacionHV";
    private static final String HQL_FET_VITAL_AGRUPACIO_ALIAS = "fva";
    
    private static final String HQL_AGRUPACIO_FET_VITAL_CLASS = HQL_FET_VITAL_AGRUPACIO_ALIAS + ".agrupacion";
    private static final String HQL_AGRUPACIO_FET_VITAL_ALIAS = "afv";
    
    private static final String HQL_FET_VITAL_PROCEDIMENT_CLASS = HQL_FET_VITAL_ALIAS + ".hechosVitalesProcedimientos";
    private static final String HQL_FET_VITAL_PROCEDIMENT_ALIAS = "fvp";
    
    private static final String HQL_PROCEDIMIENTOS_LOCALES_CLASS = HQL_FET_VITAL_PROCEDIMENT_ALIAS + ".procedimiento";
    private static final String HQL_PROCEDIMIENTOS_LOCALES_ALIAS = "p"; 
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria) {        
        List<FitxaDTO> fitxesDTOList = new ArrayList<FitxaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHAS_ALIAS, HQL_TRADUCCIONES_ALIAS, fitxaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FICHAS_CLASS, HQL_FICHAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FICHAS_ALIAS, entities, fitxaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<Ficha> fitxesResult = (List<Ficha>) query.list();            

            for (Ficha fitxa : fitxesResult) {
                fitxesDTOList.add((FitxaDTO) BasicUtils.entityToDTO(FitxaDTO.class,  fitxa, fitxaCriteria.getIdioma()));
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

        return fitxesDTOList;
    }

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_PROCEDIMENT_CLASS, HQL_FET_VITAL_PROCEDIMENT_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));            
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
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

    public List<AgrupacioFetVitalDTO> llistarFetsVitalsAgrupacionsFV(long id, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> agrupacioFetVitalDTOList = new ArrayList<AgrupacioFetVitalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {            
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, HQL_TRADUCCIONES_ALIAS, agrupacioFetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));            
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FET_VITAL_ALIAS, entities, agrupacioFetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<AgrupacionHechoVital> agrupacioFetsVitalsResult = (List<AgrupacionHechoVital>) query.list();

            for (AgrupacionHechoVital agrupacioFetsVitals : agrupacioFetsVitalsResult) {
                agrupacioFetVitalDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(AgrupacioFetVitalDTO.class,  agrupacioFetsVitals, agrupacioFetVitalCriteria.getIdioma()));
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

        return agrupacioFetVitalDTOList;
    }

    public int getNumFitxes(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(FitxaCriteria.class, HQL_FICHAS_ALIAS, new FitxaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FICHAS_CLASS, HQL_FICHAS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_FICHAS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
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

    public int getNumProcedimentsLocals(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMIENTOS_LOCALES_ALIAS, new ProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_PROCEDIMENT_CLASS, HQL_FET_VITAL_PROCEDIMENT_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMIENTOS_LOCALES_CLASS, HQL_PROCEDIMIENTOS_LOCALES_ALIAS));  
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMIENTOS_LOCALES_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
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

    public int getNumFetsVitalsAgrupacionsFV(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, new AgrupacioFetVitalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));            
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FET_VITAL_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FetVitalCriteria fvc = new FetVitalCriteria();
            fvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, fvc);
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
    
    public ArxiuDTO getFotografia(long idFoto) {
        return EJBUtils.getArxiuDTO(idFoto);
    }
    
    public ArxiuDTO getIcona(long idIcona) {
        return EJBUtils.getArxiuDTO(idIcona);
    }
    
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return EJBUtils.getArxiuDTO(idIconaGran);
    }
    
}
