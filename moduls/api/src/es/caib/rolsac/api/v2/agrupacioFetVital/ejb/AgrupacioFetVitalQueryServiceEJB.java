package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.HechoVital;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuCriteria;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de agrupaciones de hechos vitales.
 *
 * @ejb.bean
 *  name="sac/api/AgrupacioFetVitalQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.agrupacioFetVital.ejb.AgrupacioFetVitalQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class AgrupacioFetVitalQueryServiceEJB extends HibernateEJB {
    
    private static final long serialVersionUID = 8205914479251988375L;
    
    private static Log log = LogFactory.getLog(AgrupacioFetVitalQueryServiceEJB.class);

    private static final String HQL_AGRUPACIO_FET_VITAL_CLASS = "AgrupacionHechoVital";
    private static final String HQL_AGRUPACIO_FET_VITAL_ALIAS = "afv";
    
    private static final String HQL_FET_VITAL_AGRUPACIO_CLASS = HQL_AGRUPACIO_FET_VITAL_ALIAS + ".hechosVitalesAgrupacionHV";
    private static final String HQL_FET_VITAL_AGRUPACIO_ALIAS = "fva";    
    
    private static final String HQL_FET_VITAL_CLASS = HQL_FET_VITAL_AGRUPACIO_ALIAS + ".hechoVital";
    private static final String HQL_FET_VITAL_ALIAS = "fv";
    
    private static final String HQL_TRADUCCIONES_ALIAS = "trad";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la fotografia.
     * @param idFoto
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */ 
    public ArxiuDTO getFotografia(long idFoto) {
        return EJBUtils.getArxiuDTO(idFoto);
    }
    
    /**
     * Obtiene el icono.
     * @param idIcona
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO getIcona(long idIcona) {
        return EJBUtils.getArxiuDTO(idIcona);
    }
    
    /**
     * Obtiene el icono grande.
     * @param idIconaGran
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO getIconaGran(long idIconaGran) {
        return EJBUtils.getArxiuDTO(idIconaGran);
    }

    /**
     * Obtiene el publico objectivo.
     * @param idPublic
     * @return PublicObjectiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    public PublicObjectiuDTO obtenirPublicObjectiu(long idPublic) {
        PublicObjectiuCriteria publicObjectiuCriteria = new PublicObjectiuCriteria();
        publicObjectiuCriteria.setId(String.valueOf(idPublic));
        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirPublicObjectiu(publicObjectiuCriteria);
    }

    /**
     * Obtiene el numero de hechos vitales.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    public int getNumFetsVitals(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, new FetVitalCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));             
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));  
            QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioFetVitalCriteria afvc = new AgrupacioFetVitalCriteria();
            afvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, afvc);
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
     * Obtiene listado de hechos vitales.
     * @param id
     * @param fetVitalCriteria
     * @return List<FetVitalDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    @SuppressWarnings("unchecked")
    public List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria) {
        List<FetVitalDTO> fetVitalDTOList = new ArrayList<FetVitalDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        if (StringUtils.isBlank(fetVitalCriteria.getOrdenacio())) {
            fetVitalCriteria.setOrdenacio(HQL_FET_VITAL_AGRUPACIO_ALIAS + ".orden");
        }
        try {            
            criteris = BasicUtils.parseCriterias(FetVitalCriteria.class, HQL_FET_VITAL_ALIAS, HQL_TRADUCCIONES_ALIAS, fetVitalCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_AGRUPACIO_FET_VITAL_CLASS, HQL_AGRUPACIO_FET_VITAL_ALIAS));
            entities.add(new FromClause(HQL_FET_VITAL_AGRUPACIO_CLASS, HQL_FET_VITAL_AGRUPACIO_ALIAS));             
            entities.add(new FromClause(HQL_FET_VITAL_CLASS, HQL_FET_VITAL_ALIAS));                       
            QueryBuilder qb = new QueryBuilder(HQL_FET_VITAL_ALIAS, entities, fetVitalCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            AgrupacioFetVitalCriteria afvc = new AgrupacioFetVitalCriteria();
            afvc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(AgrupacioFetVitalCriteria.class, HQL_AGRUPACIO_FET_VITAL_ALIAS, afvc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<HechoVital> fetsVitalsResult = (List<HechoVital>) query.list();

            for (HechoVital fetVital : fetsVitalsResult) {
                fetVitalDTOList.add((FetVitalDTO) BasicUtils.entityToDTO(FetVitalDTO.class,  fetVital, fetVitalCriteria.getIdioma()));
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

        return fetVitalDTOList;
    }

}
