package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.util.FiltroSia;
import org.ibit.rol.sac.persistence.util.SiaUtils;

import es.caib.solr.api.exception.ExcepcionSolrApi;




/**
 * SessionBean para operar con envios SIA.
 *
 * @ejb.bean
 *  name="sac/persistence/SiaPendienteProcesoFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SiaPendienteProcesoFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SiaPendienteProcesoFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	

	
	/**
	 * Obtener SIA pendientes de enviar 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	
	 * @throws DelegateException
	 */
	public List<SiaPendiente> getSiaPendientesEnviar()  {
		log.debug("Obtener SIA pendientes enviar");

		Session session = getSession();
		
		try {

    		StringBuilder consulta = new StringBuilder("select sia from SiaPendiente as sia where sia.estado = :estado");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setCacheable(true);
    		query.setLong("estado", SiaUtils.SIAJOB_SIJ_ESTADO_CREADO);

    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA pendientes enviar OK");
    	}
	}
	
	
	/**
	* Obtiene SIA a partir de un filtro. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param filtro
	 * 
	 * @throws DelegateException
	 */
	public List<SiaPendiente> getSiaPendientes(final FiltroSia filtro)  {
		log.debug("Obtener SIA pendientes");
		
		Session session = getSession();
		
		try {

    		StringBuilder consulta = new StringBuilder("select sia from SiaPendiente as sia where sia.estado = :estado");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setCacheable(true);
    		query.setLong("estado", SiaUtils.SIAJOB_SIJ_ESTADO_CREADO);

    		query.setMaxResults(filtro.numElementos);
    		
    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA pendientes OK");
    	}	
		
	}
	
	
	/**
	 * Indexa todas las normativas. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param solrPendienteJob
	 * @throws ExcepcionSolrApi
	 * @throws DelegateException
	 */
	public List<SiaJob> getSiaProceso(final FiltroSia filtro) {
		log.debug("Obtener SIA proceso");
		
		Session session = getSession();
		
		try {

    		StringBuilder consulta = new StringBuilder("select sia from SiaJob as sia ");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setCacheable(true);
    		//query.setLong("estado", SiaUtils.SIAJOB_SIJ_ESTADO_CREADO);

    		query.setMaxResults(filtro.numElementos);
    		
    		return query.list();

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA proceso OK");
    	}	
	}
	
	/***
	  * Genera SIA pendiente 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param siaPendiente
	 * @throws DelegateException
	 */
	public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente)  {
		log.debug("Generar SIA pendiente");
		
		Session session = getSession();
		
		try {

    		siaPendiente.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_CREADO);
    		session.save(siaPendiente);
    		session.flush();
    		
    		return siaPendiente;

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Generar SIA pendiente OK");
    	}	
			
	}
	
	/**
	 * Actualiza SIA pendiente.
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 
   	 * @param siaPendiente
	 * 
	 * @throws DelegateException 
	 */
	public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente)  {
		log.debug("Actualizar SIA pendiente");

		Session session = getSession();
		
		try {

    		session.update(siaPendiente);
    		session.flush();
    		
    		return siaPendiente;

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Actualizar SIA pendiente OK");
    	}	
			
		
	}
   
    
	/**
	 * Borra SIA pendiente
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @param siaPendiente
     * @throws DelegateException 
   	 */
    public void borrarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException {
    	log.debug("Borrar SIA pendiente");

		Session session = getSession();
		
		try {
 		
    		session.delete(siaPendiente);
    		session.flush();


    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Borrar SIA pendiente OK");
    	}	 
    }

    /**
	 * Cerrando todos los jobs.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public Boolean cerrarJobs()  {
    	Session session = null;
    	try
    	{
    		session = getSession();
    		List<Integer> estados = new ArrayList<Integer>();
    		estados.add(SiaUtils.SIAJOB_SIJ_ESTADO_EN_EJECUCION);
    		estados.add(SiaUtils.SIAJOB_SIJ_ESTADO_CREADO);
    		
    		final Query query = getSession().createQuery("from SiaJob siaJob where siaJob.estado in  (:lId) ");
    		query.setParameterList("lId", estados, Hibernate.INTEGER);
    		List<SiaJob> jobs =  query.list();
    		for(SiaJob job : jobs) {
    			job.setFechaFin(new Date());
    			job.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ERROR_GRAVE);
    			
    			StringBuffer bufferDesc = SiaUtils.obtenerContenidoClob(job.getDescBreve());
    					
    			job.setDescBreve(Hibernate.createClob("Finalizado a la fuerza " + bufferDesc));
    			
    			session.update(job);
    		} 
    		session.flush();
			return true;
	    } catch(Exception exception) {
			throw new EJBException(exception);
		} finally {
			if (session != null) {
				close(session); 
			}
		}
    }
    
    /**
     * Revisa si se está ejecutando algún job.
     * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano True si hay algo activo y false si no lo está.  
   	 */
    public boolean checkJobsActivos() {
    	boolean retorno = false;
    	//Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de ellos está sin fecha fin
    	//  se da por hecho que se está ejecutando.
    	List<SiaJob> jobs = getListJobs(5);
    	for(SiaJob job : jobs) {
    		if (job.getFechaFin() == null) {
    			retorno = true;
    		}
    	}
    	return retorno;
    }
    
    /**
     * Lista todos los SiaJob según cuantos.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     *
     * @return Devuelve un listado de todos los SiaJobs.
     */
    public List<SiaJob> getListJobs(int cuantos) {

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SiaJob.class);
            criteri.addOrder(Order.desc("fechaIni"));
            criteri.setMaxResults(cuantos);
            
            return castList(SiaJob.class, criteri.list());

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    /**
     * Crear una sia pendiente job. 
     * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return SiaJob indicando si se envian todos los procesos pendientes .     *  
   	 */
	public SiaJob crearSiaJob() {
		try
    	{
			final Session session = getSession();
			final SiaJob siaJob = new SiaJob();
	    	siaJob.setFechaIni(new Date());
	    	session.save(siaJob); 
			session.flush();
			session.close();
			return siaJob;
    	 } catch(Exception exception) {
 			throw new EJBException(exception);
 		}
	}
	/**
	 * Cerrando el pendiente job.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public void cerrarSiaJob(SiaJob siaJob)  {
    	try
    	{
    		Session session = getSession();
	    	siaJob.setFechaFin(new Date());
	    	session.update(siaJob); 
			session.flush();
			session.close();
	    } catch(Exception exception) {
			throw new EJBException(exception);
		}
    }
}
