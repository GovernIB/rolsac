package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.AgrupacionHechoVital;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de publico objetivo.
 *
 * @ejb.bean
 *  name="sac/api/PublicObjectiuQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.publicObjectiu.ejb.PublicObjectiuQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class PublicObjectiuQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 9104777103395221122L;

    private static Log log = LogFactory.getLog(PublicObjectiuQueryServiceEJB.class);
    
    private static final String HQL_PUBLIC_OBJECTIU_CLASS = "PublicoObjetivo";
    private static final String HQL_PUBLIC_OBJECTIU_ALIAS = "po";
    private static final String HQL_AGRUPACIO_FV_CLASS = HQL_PUBLIC_OBJECTIU_ALIAS + ".agrupaciones";
    private static final String HQL_AGRUPACIO_FV_ALIAS = "afv";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene el numero de agrupaciones.
     * @param idPO
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumAgrupacions(long idPO) {
        List<CriteriaObject> criteris;
        Session session = null;
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

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats  = (Integer) query.uniqueResult();
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
     * Obtiene listado de agrupaciones.
     * @param idPO
     * @param agrupacioFetVitalCriteria
     * @return List<AgrupacioFetVitalDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<AgrupacioFetVitalDTO> llistarAgrupacions(long idPO, AgrupacioFetVitalCriteria agrupacioFetVitalCriteria) {
        List<AgrupacioFetVitalDTO> agrupacionDTOList = new ArrayList<AgrupacioFetVitalDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<AgrupacionHechoVital> afvResult = (List<AgrupacionHechoVital>) query.list();
            for (AgrupacionHechoVital afv : afvResult) {
                agrupacionDTOList.add((AgrupacioFetVitalDTO) BasicUtils.entityToDTO(AgrupacioFetVitalDTO.class, afv, agrupacioFetVitalCriteria.getIdioma()));
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

        return agrupacionDTOList;
    }
    
}
