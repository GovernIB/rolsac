package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.EspacioTerritorial;
import org.ibit.rol.sac.model.UnidadAdministrativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de espacios territoriales.
 *
 * @ejb.bean
 *  name="sac/api/EspaiTerritorialQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.espaiTerritorial.ejb.EspaiTerritorialQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class EspaiTerritorialQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -5681729364074567420L;

    private static Log log = LogFactory.getLog(EspaiTerritorialQueryServiceEJB.class);
    
    private static final String HQL_ET_CLASS = "EspacioTerritorial";
    private static final String HQL_ET_ALIAS = "et";
    private static final String HQL_UA_CLASS = HQL_ET_ALIAS + ".unidades";
    private static final String HQL_UA_ALIAS = "ua";
    private static final String HQL_ET_HIJOS_CLASS = HQL_ET_ALIAS + ".hijos";
    private static final String HQL_ET_HIJOS_ALIAS = "ua";
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene el numero de edificios hijo.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public int getNumFills(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_HIJOS_ALIAS, new EspaiTerritorialCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_ET_HIJOS_CLASS, HQL_ET_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_ET_HIJOS_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
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
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
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
     * Obtiene listado de edificios hijo.
     * @param id
     * @param espaiTerritorialCriteria
     * @return List<EspaiTerritorialDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        List<EspaiTerritorialDTO> espaiTerritorialDTO = new ArrayList<EspaiTerritorialDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

        try {          
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_HIJOS_ALIAS, HQL_TRADUCCIONES_ALIAS, espaiTerritorialCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_ET_HIJOS_CLASS, HQL_ET_HIJOS_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, espaiTerritorialCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<EspacioTerritorial> espaiTerritorialResult = (List<EspacioTerritorial>) query.list();

            for (EspacioTerritorial espaiTerritorial : espaiTerritorialResult) {
            	espaiTerritorialDTO.add((EspaiTerritorialDTO) BasicUtils.entityToDTO(EspaiTerritorialDTO.class,  espaiTerritorial, espaiTerritorialCriteria.getIdioma()));
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

        return espaiTerritorialDTO;
    }

    /**
     * Obtiene listado de unidades administrativas.
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
            entities.add(new FromClause(HQL_ET_CLASS, HQL_ET_ALIAS));
            entities.add(new FromClause(HQL_UA_CLASS, HQL_UA_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_UA_ALIAS, entities, unitatAdministrativaCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            EspaiTerritorialCriteria etc = new EspaiTerritorialCriteria();
            etc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(EspaiTerritorialCriteria.class, HQL_ET_ALIAS, etc);
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
     * Obtiene el edificio padre.
     * @param idPadre
     * @return EspaiTerritorialDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public EspaiTerritorialDTO obtenirPare(Long idPadre) {
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        EspaiTerritorialCriteria espaiTerritorialCriteria = new EspaiTerritorialCriteria();
        espaiTerritorialCriteria.setId(String.valueOf(idPadre));
        return ejb.obtenirEspaiTerritorial(espaiTerritorialCriteria);
    }

    /**
     * Obtiene el mapa.
     * @param idMapa
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirMapa(Long idMapa) {
        return getArxiuDTO(idMapa);
    }

    /**
     * Obtiene el logo.
     * @param idLogo
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirLogo(Long idLogo) {
        return getArxiuDTO(idLogo);
    }

}
