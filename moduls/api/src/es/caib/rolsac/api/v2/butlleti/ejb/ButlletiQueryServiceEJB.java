package es.caib.rolsac.api.v2.butlleti.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.NormativaExterna;
import org.ibit.rol.sac.model.NormativaLocal;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.normativa.co.NormativaByButlletiCriteria;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de boletines.
 *
 * @ejb.bean
 *  name="sac/api/ButlletiQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.butlleti.ejb.ButlletiQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class ButlletiQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -6284803742651680522L;

    private static Log log = LogFactory.getLog(ButlletiQueryServiceEJB.class);
    
    private static final String HQL_BUTLLETI_ALIAS = "b";
    private static final String HQL_NORMATIVAS_LOCAL_CLASS = "NormativaLocal";
    private static final String HQL_NORMATIVAS_EXTERNA_CLASS = "NormativaExterna";
    private static final String HQL_NORMATIVAS_ALIAS = "n";
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
     * Obtiene el numero de normativas del boletin.
     * @param id
     * @param tipus
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public int getNumNormatives(long id, long tipus) {
        List<CriteriaObject> criteris = new ArrayList<CriteriaObject>();
        Session session = null;    
        QueryBuilder qb = null;
        Query query = null;
        int numResultats = 0;
        
        try {
            session = getSession(); 
//            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, new NormativaCriteria());
            CriteriaObject normativaByButlletiCO = new NormativaByButlletiCriteria(HQL_BUTLLETI_ALIAS);
            normativaByButlletiCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByButlletiCO);
            List<FromClause> entities = new ArrayList<FromClause>();

            if (tipus == TIPUS_NORMATIVA.TOTES.ordinal() || tipus == TIPUS_NORMATIVA.LOCAL.ordinal()){
                entities.add(new FromClause(HQL_NORMATIVAS_LOCAL_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".boletin", HQL_BUTLLETI_ALIAS));            
                qb = new QueryBuilder("n", entities, null, null, true);
                qb.extendCriteriaObjects(criteris);                            
                query = qb.createQuery(session);
                numResultats = getNumberResults(query);
            }
            if (tipus == TIPUS_NORMATIVA.TOTES.ordinal() || tipus == TIPUS_NORMATIVA.EXTERNA.ordinal()){
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVAS_EXTERNA_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".boletin", HQL_BUTLLETI_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVAS_ALIAS, entities, null, null, true);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                numResultats += getNumberResults(query);
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

        return numResultats;
    }
    
    /**
     * Obtiene el listado de normativas del boletin.
     * @param id
     * @param normativaCriteria
     * @return List<NormativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    @SuppressWarnings("unchecked")
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        List<NormativaDTO> normativaDTOList = new ArrayList<NormativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        boolean incluirExternas = (normativaCriteria.getIncluirExternas() == null)? false : normativaCriteria.getIncluirExternas();
        normativaCriteria.setIncluirExternas(null); // Para evitar que se parsee como los demas criterias

        try {
            criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
            CriteriaObject normativaByButlletiCO = new NormativaByButlletiCriteria(HQL_BUTLLETI_ALIAS);
            normativaByButlletiCO.parseCriteria(String.valueOf(id));
            criteris.add(normativaByButlletiCO);
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_NORMATIVAS_LOCAL_CLASS, HQL_NORMATIVAS_ALIAS));
            entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".boletin", HQL_BUTLLETI_ALIAS));            
            QueryBuilder qb = new QueryBuilder("n", entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            session = getSession();
            Query query = qb.createQuery(session);
            List<NormativaLocal> normativasLocalesResult = (List<NormativaLocal>) query.list();
            List<NormativaExterna> normativasExternasResult = null;
            if (incluirExternas) {
                criteris = BasicUtils.parseCriterias(NormativaCriteria.class, HQL_NORMATIVAS_ALIAS, HQL_TRADUCCIONES_ALIAS, normativaCriteria);
                criteris.add(normativaByButlletiCO);
                entities = new ArrayList<FromClause>();
                entities.add(new FromClause(HQL_NORMATIVAS_EXTERNA_CLASS, HQL_NORMATIVAS_ALIAS));
                entities.add(new FromClause(HQL_NORMATIVAS_ALIAS + ".boletin", HQL_BUTLLETI_ALIAS));            
                qb = new QueryBuilder(HQL_NORMATIVAS_ALIAS, entities, normativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
                qb.extendCriteriaObjects(criteris);
                query = qb.createQuery(session);
                normativasExternasResult = (List<NormativaExterna>) query.list();
            }

            NormativaDTO dto;
            for (NormativaLocal normativa : normativasLocalesResult) {
                dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                dto.setLocal(true);
                normativaDTOList.add(dto);
            }
            if (incluirExternas) {
                for (NormativaExterna normativa : normativasExternasResult) {
                    dto = (NormativaDTO) BasicUtils.entityToDTO(NormativaDTO.class, normativa, normativaCriteria.getIdioma());
                    dto.setLocal(false);
                    normativaDTOList.add(dto);
                }
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

        return normativaDTOList;
    }
}