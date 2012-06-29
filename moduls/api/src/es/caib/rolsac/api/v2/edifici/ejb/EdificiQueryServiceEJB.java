package es.caib.rolsac.api.v2.edifici.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de edificios.
 *
 * @ejb.bean
 *  name="sac/api/EdificiQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.edifici.ejb.EdificiQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class EdificiQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 5177935038618338812L;

    private static Log log = LogFactory.getLog(EdificiQueryServiceEJB.class);

    private static final String HQL_EDIFICI_CLASS = "Edificio";
    private static final String HQL_EDIFICI_ALIAS = "ed";
    
    private static final String HQL_UA_CLASS = HQL_EDIFICI_ALIAS + ".unidadesAdministrativas";
    private static final String HQL_UA_ALIAS = "ua";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene el listado de unidades administrativas.
     * @param id
     * @param unitatAdministrativaCriteria
     * @return List<UnitatAdministrativaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    @SuppressWarnings("unchecked")
    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        List<UnitatAdministrativaDTO> unitatADministrativaDTOList = new ArrayList<UnitatAdministrativaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {          
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, HQL_TRADUCCIONES_ALIAS, unitatAdministrativaCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            EdificiCriteria ec = new EdificiCriteria();
            ec.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, ec);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<UnidadAdministrativa> unitatAdministrativaResult = (List<UnidadAdministrativa>) query.list();
            for (UnidadAdministrativa unitatAdministrativa : unitatAdministrativaResult) {
                unitatADministrativaDTOList.add((UnitatAdministrativaDTO) BasicUtils.entityToDTO(UnitatAdministrativaDTO.class,  unitatAdministrativa, unitatAdministrativaCriteria.getIdioma()));
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

        return unitatADministrativaDTOList;
    }

    /**
     * Obtiene el numero de unidades administrativas.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumUnitatsAdministratives(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;

        try {            
            criteris = BasicUtils.parseCriterias(UnitatAdministrativaCriteria.class, HQL_UA_ALIAS, new UnitatAdministrativaCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_EDIFICI_CLASS, HQL_EDIFICI_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            EdificiCriteria ec = new EdificiCriteria();
            ec.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EdificiCriteria.class, HQL_EDIFICI_ALIAS, ec);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats  = ((Integer) query.uniqueResult()).intValue();
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
     * Obtiene la foto pequenya
     * @param idFotoPequenya
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) {
        return EJBUtils.getArxiuDTO(idFotoPequenya);
    }

    /**
     * Obtiene la foto grande
     * @param idFotoGrande
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) {
        return EJBUtils.getArxiuDTO(idFotoGrande);
    }

    /**
     * Obtiene el plano
     * @param idPlano
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirPlano(Long idPlano) {
       return EJBUtils.getArxiuDTO(idPlano);
    }

}
