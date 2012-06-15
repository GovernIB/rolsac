package es.caib.rolsac.api.v2.familia.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.ProcedimientoLocal;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

public class FamiliaQueryServiceEJB {

    private static final String HQL_FAMILIA_CLASS = "Familia";
    private static final String HQL_FAMILIA_ALIAS = "fam";
    private static final String HQL_PROCEDIMENT_CLASS = HQL_FAMILIA_ALIAS + ".procedimientosLocales";
    private static final String HQL_PROCEDIMENT_ALIAS = "proc";
    private static final String HQL_ICONA_CLASS = HQL_FAMILIA_ALIAS + ".iconos";
    private static final String HQL_ICONA_ALIAS = "ico";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public int getNumProcedimentsLocals(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMENT_ALIAS, new ProcedimentCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMENT_CLASS, HQL_PROCEDIMENT_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMENT_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = (Integer) query.uniqueResult();
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

    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
        List<ProcedimentDTO> procedimentDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(ProcedimentCriteria.class, HQL_PROCEDIMENT_ALIAS, HQL_TRADUCCIONES_ALIAS, procedimentCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_PROCEDIMENT_CLASS, HQL_PROCEDIMENT_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_PROCEDIMENT_ALIAS, entities, procedimentCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<ProcedimientoLocal> procResult = (List<ProcedimientoLocal>) query.list();
            sessio.close();

            for (ProcedimientoLocal proc : procResult) {
                procedimentDTOList.add((ProcedimentDTO) BasicUtils.entityToDTO(ProcedimentDTO.class, proc, procedimentCriteria.getIdioma()));
            }
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

    public int getNumIcones(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_ALIAS, new IconaFamiliaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_ICONA_CLASS, HQL_ICONA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            numResultats  = (Integer) query.uniqueResult();
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
    
    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> iconaDTOList = new ArrayList<IconaFamiliaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONA_ALIAS, iconaFamiliaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_ICONA_CLASS, HQL_ICONA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<IconoFamilia> iconaResult = (List<IconoFamilia>) query.list();
            sessio.close();

            for (IconoFamilia icona : iconaResult) {
                iconaDTOList.add((IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icona, iconaFamiliaCriteria.getIdioma()));
            }
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

        return iconaDTOList;
    }
    
}
