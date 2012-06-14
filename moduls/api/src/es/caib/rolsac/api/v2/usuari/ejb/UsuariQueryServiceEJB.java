package es.caib.rolsac.api.v2.usuari.ejb;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.HibernateUtils;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariCriteria;

public class UsuariQueryServiceEJB {

    private static String HQL_USUARI_CLASS = "Usuario";
    private static String HQL_USUARI_ALIAS = "usu";
    private static String HQL_UA_CLASS = HQL_USUARI_ALIAS + ".unidadesAdministrativas";
    private static String HQL_UA_ALIAS = "ua";
    private static String HQL_TRADUCCIONES_ALIAS = "trad";
    
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria uaCriteria) {
        List<UnitatAdministrativaDTO> uaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session sessio = null;

        try {
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, HQL_TRADUCCIONES_ALIAS, uaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, uaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            UsuariCriteria uc = new UsuariCriteria();
            uc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, uc);
            qb.extendCriteriaObjects(criteris);

            sessio = HibernateUtils.getSessionFactory().openSession();
            Query query = qb.createQuery(sessio);
            @SuppressWarnings("unchecked")
            List<UnidadAdministrativa> uaResult = (List<UnidadAdministrativa>) query.list();
            sessio.close();

            for (UnidadAdministrativa ua : uaResult) {
                uaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  ua, uaCriteria.getIdioma()));
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

        return uaDTOList;
    }

    public int getNumUnitatsAdministratives(long id) {
        List<CriteriaObject> criteris;
        Session sessio = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, new UnitatAdministrativaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_USUARI_CLASS, HQL_USUARI_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            UsuariCriteria uc = new UsuariCriteria();
            uc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(UsuariCriteria.class, HQL_USUARI_ALIAS, uc);
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
