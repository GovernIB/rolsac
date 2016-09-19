package org.ibit.rol.sac.persistence.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.SolrFactory;
import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.exception.ExcepcionSolrApi;
import es.caib.solr.api.model.types.EnumAplicacionId;


/**
 * SessionBean para consultar idiomas.
 *
 * @ejb.bean
 *  name="sac/persistence/SolrPendienteFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SolrPendienteFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class SolrPendienteFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	

	 /**
     * Obtiene los parámetros de configuración del indexer
     * @return SolrIndexer
     * 
     */
	private SolrIndexer obtenerParamIndexer() {
		String url = System.getProperty("es.caib.rolsac.solr.url");
		String index = System.getProperty("es.caib.rolsac.solr.index");
		String user = System.getProperty("es.caib.rolsac.solr.user");
		String pass = System.getProperty("es.caib.rolsac.solr.pass");
		SolrIndexer solrIndexer = SolrFactory.getIndexer(url, index, EnumAplicacionId.ROLSAC, user, pass);
		return solrIndexer;
	}

    /**
     * Lista todos los SolrPendientes
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     *
     * @return Devuelve un listado de todos los SolrPendientes.
     */
    public List<SolrPendiente> getPendientes() {

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SolrPendiente.class);
            criteri.add(Expression.eq("resultado", 0));
            
            List<SolrPendiente> solrPendientes = castList(SolrPendiente.class, criteri.list());

            return solrPendientes;

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    
    
    /**
     * Lista todos los SolrPendientesJob según cuantos.
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     *
     * @return Devuelve un listado de todos los SolrPendientes.
     */
    public List<SolrPendienteJob> getListJobs(int cuantos) {

        Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SolrPendienteJob.class);
            criteri.addOrder(Order.desc("fechaIni"));
            criteri.setMaxResults(cuantos);
            
            return castList(SolrPendienteJob.class, criteri.list());

        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
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
    	List<SolrPendienteJob> jobs = getListJobs(5);
    	for(SolrPendienteJob job : jobs) {
    		if (job.getFechaFin() == null) {
    			retorno = true;
    		}
    	}
    	return retorno;
    }
    
    /**
     * Crear una solr pendiente job. 
     * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano indicando si se indexan todos los procesos pendientes .     *  
   	 */
	public SolrPendienteJob crearSorlPendienteJob() {
		try
    	{
			final Session session = getSession();
			final SolrPendienteJob solrpendienteJob = new SolrPendienteJob();
	    	solrpendienteJob.setFechaIni(new Date());
	    	session.save(solrpendienteJob); 
			session.flush();
			session.close();
			return solrpendienteJob;
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
    public void cerrarSorlPendienteJob(SolrPendienteJob solrpendienteJob)  {
    	try
    	{
    		Session session = getSession();
	    	solrpendienteJob.setFechaFin(new Date());
	    	session.update(solrpendienteJob); 
			session.flush();
			session.close();
	    } catch(Exception exception) {
			throw new EJBException(exception);
		}
    }
    
	
    
    /**
     * Método que se encarga de realizar las acciones segun si ha sido correcto o no.
     * @param solrpendiente
     * @param session
     * @param solrPendienteResultado
     * @throws HibernateException
     */
    private void resolverPendiente(final SolrPendiente solrpendiente,
    		final Session session, final SolrPendienteResultado solrPendienteResultado) throws HibernateException {
    	if (solrPendienteResultado != null) {
			if (solrPendienteResultado.isCorrecto()) {
				solrpendiente.setResultado(1);
				session.update(solrpendiente);
			} else {
				final Calendar fechaCalendar  = Calendar.getInstance();
				fechaCalendar.setTime(solrpendiente.getFechaCreacion());
				final Calendar hoyCalendar  = Calendar.getInstance();
				hoyCalendar.setTime(new Date());
				
				final int dias = hoyCalendar.get(Calendar.DATE) - fechaCalendar.get(Calendar.DATE);
				//Si hace 10 dias o + que se crea se marca como erronea porque no se ha podido endexar
				if ( dias >= 10){
					solrpendiente.setResultado(-1);
					solrpendiente.setMensajeError(solrPendienteResultado.getMensaje());
					session.update(solrpendiente);
				}else{
					log.error("No se ha podido realizar la operación (dias ejecutandose:"+dias+")con el registro : "+solrpendiente.getId());
				}
			}
    	}
	}



	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano indicando si se han borrado todas las caducadas .
   	 */
    public Boolean borrarCaducadas() {
    	
        try {
        	SolrIndexer solrIndexer = obtenerParamIndexer();
        	solrIndexer.desindexarCaducados();
        	
        } catch (ExcepcionSolrApi e) {
        	throw new EJBException(e);
		}
		return true;
    }
    
    /**
     * Comprueba si hay pendientes por ejecutar con la misma acción y elemento.
     * @param tipo
     * @param idElemento
     * @param accion
     * @return
     */
    private Boolean hayPendientes(String tipo, Long idElemento, Long accion) {
    	 final Session session = getSession();
         try 
         {
        	 final Criteria criteri = session.createCriteria(SolrPendiente.class);
        	 criteri.add(Expression.eq("resultado", 0));
        	 criteri.add(Expression.eq("tipo", tipo));
        	 criteri.add(Expression.eq("idElemento", idElemento));
        	 criteri.add(Expression.eq("accion", accion.intValue()));
        	 final int cuantos = criteri.list().size();
        	 if (cuantos == 0) {
	        	return false;
        	 } else {
	        	return true;
        	 }
         } catch (HibernateException he) {
             throw new EJBException(he);
         } finally {
             close(session);
         }   
    }
    
    
    /**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano indicando si se han grabado todos los solar pendientes .
   	 */
    public Boolean grabarSolrPendiente(String tipo, Long idElemento, Long accion) {

        Session session = getSession();
        try {
        	Boolean resultado;
        	//Si hay pendientes, no insertar
        	if (hayPendientes(tipo, idElemento, accion)){
        		resultado = true;
        	} else {
	            final SolrPendiente solrPendiente = new SolrPendiente();
	            solrPendiente.setTipo(tipo);
	            solrPendiente.setIdElemento(idElemento);
	            solrPendiente.setAccion(accion.intValue());
	            solrPendiente.setFechaCreacion(new java.util.Date()); //import java.util.Date;
	            solrPendiente.setResultado(0);
	            session.save(solrPendiente);
	            session.flush();
	            resultado = true;
        	}
            return resultado;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

 
    
    /**
     * Obtiene los pendientes para la paginación
     * 
     * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
     * @param pagina
     * @param resultados
     * @return
     */
    public ResultadoBusqueda getPendientes(int pagina, int resultados) {
    	
    	List<SolrPendiente> solrPendTotal = getPendientes();
    	ResultadoBusqueda resultadoBusqueda = new ResultadoBusqueda();
    	
    	int indiceDesde = pagina * resultados;
    	int indiceHasta = indiceDesde + resultados;
    	int tamanyoLista = solrPendTotal.size();
    	
    	if (indiceHasta > tamanyoLista) {
    		indiceHasta = tamanyoLista;
    	}
    		
    	resultadoBusqueda.setTotalResultados(solrPendTotal.size());
    	
    	if ( resultados < RESULTATS_CERCA_TOTS ) {
    		solrPendTotal = solrPendTotal.subList( indiceDesde, indiceHasta );    		
    	}
    	
    	resultadoBusqueda.setListaResultados(solrPendTotal);
    	
    	return resultadoBusqueda;

    	
    }

    
}
