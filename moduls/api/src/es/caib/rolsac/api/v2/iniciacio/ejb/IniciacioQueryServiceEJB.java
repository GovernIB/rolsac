package es.caib.rolsac.api.v2.iniciacio.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Iniciacion;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.query.FromClause;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.QueryBuilderException;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de ROLSAC.
 *
 * @ejb.bean
 *  name="sac/api/IniciacioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.rolsac.ejb.IniciacioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class IniciacioQueryServiceEJB extends HibernateEJB {

	private static final long serialVersionUID = 1320206905650174683L;
	
	private static Log log = LogFactory.getLog(RolsacQueryServiceEJB.class);
	
	private static final String HQL_TRADUCCIONES_ALIAS = "trad";
	private static final String HQL_INICIACIO_CLASS = "Iniciacion";
    private static final String HQL_INICIACIO_ALIAS = "inici";
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene lista de tipos de iniciación.
     * @param iniciacioCriteria
     * @return List<IniciacioDTO>
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) {
    	
        List<CriteriaObject> criteris;
        List<IniciacioDTO> iniciacioDTOList = new ArrayList<IniciacioDTO>();
        Session session = null;

        try {
        	
            criteris = BasicUtils.parseCriterias(
	        		IniciacioCriteria.class, 
	        		HQL_INICIACIO_ALIAS, 
	        		HQL_TRADUCCIONES_ALIAS, 
	        		iniciacioCriteria
            );
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
            		HQL_INICIACIO_ALIAS, 
                    entities, 
                    iniciacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS
            );
            
            
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            List<Iniciacion> iniciacioResult = (List<Iniciacion>)query.list();
            
            for (Iniciacion iniciacion: iniciacioResult) {
                iniciacioDTOList.add((IniciacioDTO)BasicUtils.entityToDTO(
                		IniciacioDTO.class, 
                		iniciacion,
                        iniciacioCriteria.getIdioma()));
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return iniciacioDTOList;
        
    }
    
    /**
     * Obtiene un tipo de iniciación.
     * @param iniciacioCriteria
     * @return IniciacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    @SuppressWarnings("unchecked")
    public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) {
    	
        List<CriteriaObject> criteris;
        IniciacioDTO iniciacioDTO = null;
        Session session = null;

        try {
        	
            criteris = BasicUtils.parseCriterias(
	        		IniciacioCriteria.class, 
	        		HQL_INICIACIO_ALIAS, 
	        		HQL_TRADUCCIONES_ALIAS, 
	        		iniciacioCriteria
            );
            
            List<FromClause> entities = new ArrayList<FromClause>();
            entities.add(new FromClause(HQL_INICIACIO_CLASS, HQL_INICIACIO_ALIAS));
            
            QueryBuilder qb = new QueryBuilder(
            		HQL_INICIACIO_ALIAS, 
                    entities, 
                    iniciacioCriteria.getIdioma(),
                    HQL_TRADUCCIONES_ALIAS
            );
            
            
            qb.extendCriteriaObjects(criteris);

            session = getSession();
            Query query = qb.createQuery(session);
            Iniciacion iniciacion = (Iniciacion)query.uniqueResult();
            
            if (iniciacion != null) {
            	
				iniciacioDTO = (IniciacioDTO)BasicUtils.entityToDTO(
						IniciacioDTO.class, 
						iniciacion,
						iniciacioCriteria.getIdioma()
				);
            	
            }
            
        } catch (HibernateException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (CriteriaObjectParseException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } catch (QueryBuilderException e) {
        	
            log.error(e);
            throw new EJBException(e);
            
        } finally {
        	
            close(session);
            
        }

        return iniciacioDTO;
        
    }

}
