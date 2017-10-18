package es.caib.rolsac.api.v2.familia.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;

import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;

/**
 * SessionBean para consultas de familia.
 *
 * @ejb.bean
 *  name="sac/api/FamiliaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.familia.ejb.FamiliaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class FamiliaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 1557458996303347630L;
    
    private static Log log = LogFactory.getLog(FamiliaQueryServiceEJB.class);

    private static final String HQL_FAMILIA_CLASS = "Familia";
    private static final String HQL_FAMILIA_ALIAS = "fam";
    private static final String HQL_PROCEDIMENT_CLASS = HQL_FAMILIA_ALIAS + ".procedimientosLocales";
    private static final String HQL_PROCEDIMENT_ALIAS = "proc";
    private static final String HQL_SERVICIO_CLASS = HQL_FAMILIA_ALIAS + ".servicios";
    private static final String HQL_SERVICIO_ALIAS = "serv";
    private static final String HQL_ICONA_CLASS = HQL_FAMILIA_ALIAS + ".iconos";
    private static final String HQL_ICONA_ALIAS = "ico";
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
     * Obtiene el numero de procedimientos.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public int getNumProcedimentsLocals(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats  = getNumberResults(query);
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
     * Obtiene el numero de servicios.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public int getNumServicios(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
        int numResultats = 0;

        try {
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, new ServicioCriteria());
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SERVICIO_ALIAS, entities, null, null, true);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats  = getNumberResults(query);
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
     * Obtiene el listado de procedimientos.
     * @param id
     * @param procedimentCriteria
     * @return List<ProcedimentDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ProcedimentDTO> llistarProcedimentsLocals(long id, ProcedimentCriteria procedimentCriteria) {
    	
        List<ProcedimentDTO> procedimentsDTOList = new ArrayList<ProcedimentDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
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

            session = getSession();
            Query query = qb.createQuery(session);
            List<ProcedimientoLocal> procedimentsResult = (List<ProcedimientoLocal>)query.list();
            
            for (ProcedimientoLocal procediment : procedimentsResult) {
                					
				procedimentsDTOList.add(
					(ProcedimentDTO)BasicUtils.entityToDTO(
						ProcedimentDTO.class, 
						procediment, 
						procedimentCriteria.getIdioma()
					)
				);
					                
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

        return procedimentsDTOList;
        
    }
    
    /**
     * Obtiene el listado de servicios.
     * @param id
     * @param servicioCriteria
     * @return List<ServicioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<ServicioDTO> llistarServicios(long id, ServicioCriteria servicioCriteria) {
    	
        List<ServicioDTO> serviciosDTOList = new ArrayList<ServicioDTO>();
        List<CriteriaObject> criteris;
        Session session = null;
        
        try {
        	
            criteris = BasicUtils.parseCriterias(ServicioCriteria.class, HQL_SERVICIO_ALIAS, HQL_TRADUCCIONES_ALIAS, servicioCriteria);
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_FAMILIA_CLASS, HQL_FAMILIA_ALIAS));
            entities.add(new FromClause(HQL_SERVICIO_CLASS, HQL_SERVICIO_ALIAS));
            QueryBuilder qb = new QueryBuilder(HQL_SERVICIO_ALIAS, entities, servicioCriteria.getIdioma(), HQL_TRADUCCIONES_ALIAS);
            qb.extendCriteriaObjects(criteris);
            
            FamiliaCriteria fc = new FamiliaCriteria();
            fc.setId(String.valueOf(id));
            criteris = BasicUtils.parseCriterias(FamiliaCriteria.class, HQL_FAMILIA_ALIAS, fc);
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Servicio> serviciosResult = (List<Servicio>)query.list();
            
            for (Servicio servicio : serviciosResult) {
                					
				serviciosDTOList.add(
					(ServicioDTO)BasicUtils.entityToDTO(
						ServicioDTO.class, 
						servicio, 
						servicioCriteria.getIdioma()
					)
				);
					                
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

        return serviciosDTOList;
        
    }

    /**
     * Obtiene el numero de iconos.
     * @param id
     * @return int
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */ 
    public int getNumIcones(long id) {
        List<CriteriaObject> criteris;
        Session session = null;
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

            session = getSession();
            Query query = qb.createQuery(session);
            numResultats = getNumberResults(query);
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
     * Obtiene el listado de iconos.
     * @param id
     * @param iconaFamiliaCriteria
     * @return List<IconaFamiliaDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IconaFamiliaDTO> llistarIcones(long id, IconaFamiliaCriteria iconaFamiliaCriteria) {
        List<IconaFamiliaDTO> iconaDTOList = new ArrayList<IconaFamiliaDTO>();
        List<CriteriaObject> criteris;
        Session session = null;

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

            session = getSession();
            Query query = qb.createQuery(session);
            List<IconoFamilia> iconaResult = (List<IconoFamilia>) query.list();
            for (IconoFamilia icona : iconaResult) {
                iconaDTOList.add((IconaFamiliaDTO) BasicUtils.entityToDTO(IconaFamiliaDTO.class, icona, iconaFamiliaCriteria.getIdioma()));
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

        return iconaDTOList;
    }
    
}
