package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.AgrupacionHechoVital;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

public class PublicObjectiuQueryServcieEJB {

    private static final String HQL_PUBLIC_OBJECTIU_CLASS = "PublicoObjetivo";
    private static final String HQL_PUBLIC_OBJECTIU_ALIAS = "po";
    private static final String HQL_AGRUPACIO_FV_CLASS = HQL_PUBLIC_OBJECTIU_ALIAS + ".agrupaciones";
    private static final String HQL_AGRUPACIO_FV_ALIAS = "afv";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public int getNumAgrupacions(long idPO) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FV_ALIAS, new AgrupacioFetVitalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));
            entities.add(new FromClause(HQL_AGRUPACIO_FV_CLASS, HQL_AGRUPACIO_FV_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FV_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            PublicObjectiuCriteria poc = new PublicObjectiuCriteria();
            poc.setId(String.valueOf(idPO));
            criteris = BasicUtils.parseCriterias(PublicObjectiuCriteria.class, HQL_PUBLIC_OBJECTIU_ALIAS, poc);
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

    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long idPO, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> agrupacionDTOList = new ArrayList<AgrupacioFetVitalDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FV_ALIAS, HQL_TRADUCCIONES_ALIAS, agrupacioFetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_PUBLIC_OBJECTIU_CLASS, HQL_PUBLIC_OBJECTIU_ALIAS));
            entities.add(new FromClause(HQL_AGRUPACIO_FV_CLASS, HQL_AGRUPACIO_FV_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_AGRUPACIO_FV_ALIAS, entities, agrupacioFetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            PublicObjectiuCriteria poc = new PublicObjectiuCriteria();
            poc.setId(String.valueOf(idPO));
            criteris = BasicUtils.parseCriterias(PublicObjectiuCriteria.class, HQL_PUBLIC_OBJECTIU_ALIAS, poc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<AgrupacionHechoVital> afvResult = (List<AgrupacionHechoVital>) query.list();
            sessio.close();

            for (AgrupacionHechoVital afv : afvResult) {
                agrupacionDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(AgrupacioFetVitalDTO.class, afv, agrupacioFetVitalCriteria.getIdioma()));
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

        return agrupacionDTOList;
    }
    
}
