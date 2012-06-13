package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Materia;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceEJB {

    private static Log log = LogFactory.getLog(AgrupacioMateriaQueryServiceEJB.class);
    
    private static final String HQL_AGRUPACION_MATERIA_CLASS = "AgrupacionMateria";
    private static final String HQL_AGRUPACION_MATERIA_ALIAS = "am";
    private static final String HQL_MATERIA_AGRUPACION_CLASS = HQL_AGRUPACION_MATERIA_ALIAS + ".materiasAgrupacionM";
    private static final String HQL_MATERIA_AGRUPACION_ALIAS = "ma";
    private static final String HQL_MATERIA_CLASS = HQL_MATERIA_AGRUPACION_ALIAS + ".materia";
    private static final String HQL_MATERIA_ALIAS = "m";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
            
    public SeccioDTO obtenirSeccio(long idSeccio) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(idSeccio));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirSeccio(seccioCriteria);
    }

    public List<MateriaDTO> llistarMateries(long id, MateriaCriteria materiaCriteria) {
        List<MateriaDTO> materiaDTOList = new ArrayList<MateriaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        if (StringUtils.isBlank(materiaCriteria.getOrdenacio())) {
            materiaCriteria.setOrdenacio(HQL_MATERIA_AGRUPACION_ALIAS + ".orden");
            }
      
        try {            
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, HQL_TRADUCCIONES_ALIAS, materiaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACION_MATERIA_CLASS, HQL_AGRUPACION_MATERIA_ALIAS));            
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, materiaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioMateriaCriteria amc = new AgrupacioMateriaCriteria();
            amc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACION_MATERIA_ALIAS, amc);
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

    public int getNumMateries(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(MateriaCriteria.class, HQL_MATERIA_ALIAS, new MateriaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACION_MATERIA_CLASS, HQL_AGRUPACION_MATERIA_ALIAS));            
            entities.add(new FromClause(HQL_MATERIA_AGRUPACION_CLASS, HQL_MATERIA_AGRUPACION_ALIAS));
            entities.add(new FromClause(HQL_MATERIA_CLASS, HQL_MATERIA_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(HQL_MATERIA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioMateriaCriteria amc = new AgrupacioMateriaCriteria();
            amc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioMateriaCriteria.class, HQL_AGRUPACION_MATERIA_ALIAS, amc);
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
