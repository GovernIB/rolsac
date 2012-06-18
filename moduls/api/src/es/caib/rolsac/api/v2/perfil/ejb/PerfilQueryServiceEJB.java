package es.caib.rolsac.api.v2.perfil.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.IconoMateria;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

public class PerfilQueryServiceEJB {

    private static final String HQL_PERFIL_CLASS = "PerfilCiudadano";
    private static final String HQL_PERFIL_ALIAS = "perf";
    private static final String HQL_ICONES_FAMILIA_CLASS = HQL_PERFIL_ALIAS + ".iconosFamilia";
    private static final String HQL_ICONES_FAMILIA_ALIAS = "icoFam";
    private static final String HQL_ICONES_MATERIA_CLASS = HQL_PERFIL_ALIAS + ".iconosMateria";
    private static final String HQL_ICONES_MATERIA_ALIAS = "icoMat";
    
    public List<IconaFamiliaDTO> llistarIconesFamilia(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> iconaDTOList = new ArrayList<IconaFamiliaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONES_FAMILIA_CLASS, iconaFamiliaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
            entities.add(new FromClause(HQL_ICONES_FAMILIA_CLASS, HQL_ICONES_FAMILIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONES_FAMILIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            PerfilCriteria pc = new PerfilCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
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

    public List<IconaMateriaDTO> llistarIconesMateria(long id, IconaMateriaCriteria iconaMateriaCriteria) {
        List<IconaMateriaDTO> iconaDTOList = new ArrayList<IconaMateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONES_FAMILIA_CLASS, iconaMateriaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
            entities.add(new FromClause(HQL_ICONES_MATERIA_CLASS, HQL_ICONES_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONES_MATERIA_ALIAS, entities, null, null);
            qb.extendCriteriaObjects(criteris);
            
            PerfilCriteria pc = new PerfilCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<IconoMateria> iconaResult = (List<IconoMateria>) query.list();
            sessio.close();

            for (IconoMateria icona : iconaResult) {
                iconaDTOList.add((IconaMateriaDTO) BasicUtils.entityToDTO(IconaMateriaDTO.class, icona, iconaMateriaCriteria.getIdioma()));
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

    public int getNumIconesFamilia(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(IconaFamiliaCriteria.class, HQL_ICONES_FAMILIA_ALIAS, new IconaFamiliaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
            entities.add(new FromClause(HQL_ICONES_FAMILIA_CLASS, HQL_ICONES_FAMILIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONES_FAMILIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            PerfilCriteria pc = new PerfilCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
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

    public int getNumIconesMateria(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(IconaMateriaCriteria.class, HQL_ICONES_FAMILIA_ALIAS, new IconaMateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PERFIL_CLASS, HQL_PERFIL_ALIAS));
            entities.add(new FromClause(HQL_ICONES_MATERIA_CLASS, HQL_ICONES_MATERIA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ICONES_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            PerfilCriteria pc = new PerfilCriteria();
            pc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(PerfilCriteria.class, HQL_PERFIL_ALIAS, pc);
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
    
}
