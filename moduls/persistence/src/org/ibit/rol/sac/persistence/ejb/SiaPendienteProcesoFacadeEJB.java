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

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.ibit.rol.sac.persistence.util.FiltroSia;
import org.ibit.rol.sac.persistence.util.SiaCumpleDatos;
import org.ibit.rol.sac.persistence.util.SiaEnviableResultado;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.sia.SiaWS;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import es.caib.rolsac.utils.ResultadoBusqueda;
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
 * @ejb.transaction type="RequiresNew"
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
   	 * @param filtro     Filtro
   	 * @param pagina	 Pagina actual donde nos encontramos.
	 * @param cuantos    Cuantos elementos a obtener
	 * @param ordenCampo Marca el parámetro de orden
	 * @param ordenAsc 	 Marca si es ASC o DESC (ascendente o descendente)
	 * 
	 * @throws DelegateException
	 */
	public List<SiaPendiente> getSiaPendientesEnviar()  {
		log.debug("Obtener SIA pendientes enviar");

		final Session session = getSession();
		
		try 
		{
			log.debug("Obtener SIA pendientes enviar");

    		StringBuilder consulta = new StringBuilder("select sia from SiaPendiente as sia where sia.estado = :estado");
    		
	    	Query query = session.createQuery( consulta.toString() );
	    	query.setLong("estado", SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
	    	return query.list(); 

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA pendientes enviar OK");
    	}
	}
	

	/**
	 * /**
	 * Obtener SIA UAs.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 *
	 * @param pagina	 Pagina actual donde nos encontramos.
	 * @param cuantos    Cuantos elementos a obtener
	 * @param ordenCampo Marca el parámetro de orden
	 * @param ordenAsc 	 Marca si es ASC o DESC (ascendente o descendente)
	 * @return
	 */
	public ResultadoBusqueda getSiaUAs(final int pagina, final int cuantos, final String ordenCampo, final String ordenAsc) {
		log.debug("Obtiene las SIA UAs.");

		final Session session = getSession();
		final ResultadoBusqueda resultado = new ResultadoBusqueda();
		
		try {

    		final StringBuilder consultaSelect = new StringBuilder("select sia from SiaUA as sia ");
    		final StringBuilder consultaTotal = new StringBuilder("select count(sia) from SiaUA as sia ");
    		
    		final StringBuilder consulta = new StringBuilder();
    		//Indicamos el orden, por defecto es por la id descendente
    		if (ordenCampo == null || ordenCampo.isEmpty()) {
    			consulta.append(" order by sia.id desc");
    		} else {
    			consulta.append(" order by sia.");
    			consulta.append(ordenCampo);
    			if (ordenAsc == null || ordenAsc.isEmpty()) {
    				consulta.append(" asc ");
    			} else {
    				consulta.append(" ");
    				consulta.append(ordenAsc);
    			}
    		}
    		
    		
    		
    		//La select.
    		consultaSelect.append(consulta);
    		final Query query = session.createQuery( consultaSelect.toString() );
    		//Calculamos a partir de cuando hay que calcular.
    		final int primerResultado = new Integer(pagina).intValue() * cuantos;
			query.setFirstResult(primerResultado);
			query.setMaxResults(cuantos);
    		List<SiaUA> lista = (List<SiaUA>) query.list();
    		resultado.setListaResultados(lista);
    		
    		//El total.
    		//consultaTotal.append(consulta); //No hace falta el order by en los counts
    		final Query queryTotal = session.createQuery( consultaTotal.toString() );
    		Integer totalResultados = Integer.valueOf( queryTotal.uniqueResult().toString() );
    		resultado.setTotalResultados(totalResultados);
    		
    		return resultado;

    	} catch (HibernateException he) {

    		log.error("Error obteniendo SiaUA. Pagina:"+pagina+" Cuantos:"+cuantos+" Orden:"+ordenCampo+" OrdenAsc:"+ordenAsc, he);
    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA pendientes OK");
    		
    	}	
		
	}
	
	/**
	* Obtiene SIA pendientes a partir de un filtro. 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @param filtro
	 * 
	 * @throws DelegateException
	 */
	public ResultadoBusqueda getSiaPendientes(final FiltroSia filtro)  {
		log.debug("Obtener SIA pendientes");
		
		Session session = getSession();
		ResultadoBusqueda resultado = new ResultadoBusqueda();
		
		try {
			
			final StringBuilder consultaSelect = new StringBuilder("select sia from SiaPendiente as sia where 1 = 1 ");
    		final StringBuilder consultaTotal = new StringBuilder("select count(sia) from SiaPendiente as sia where 1 = 1 ");
    		
    		
    		if (filtro.getEstado() != null) {
    			consultaSelect.append(" and sia.estado = :estado");
    			consultaTotal.append(" and sia.estado = :estado");
    		}
    		
    		if (filtro.getIdElemento() != null) {
    			consultaSelect.append(" and sia.idElemento = :idElemento");
    			consultaTotal.append(" and sia.idElemento = :idElemento");
    		}
    		
    		if (filtro.getExiste() != null) {
    			consultaSelect.append(" and sia.existe = :existe");
    			consultaTotal.append(" and sia.existe = :existe");
    		}
    		
    		final StringBuilder consulta = new StringBuilder();
    		//Indicamos el orden, por defecto es por la id descendente
    		if (filtro.getOrdenCampo() == null || filtro.getOrdenCampo().isEmpty()) {
    			consulta.append(" order by sia.id desc");
    		} else {
    			consulta.append(" order by sia.");
    			consulta.append(filtro.getOrdenCampo());
    			if (filtro.getOrdenAsc() == null || filtro.getOrdenAsc().isEmpty()) {
    				consulta.append(" asc ");
    			} else {
    				consulta.append(" ");
    				consulta.append(filtro.getOrdenAsc());
    			}
    		}
    		
    		//La select.
    		consultaSelect.append(consulta);
    		final Query query = session.createQuery( consultaSelect.toString() );
    		//Calculamos a partir de cuando hay que calcular.
    		final int primerResultado = new Integer(filtro.getPagina()).intValue() * filtro.getNumElementos();
			query.setFirstResult(primerResultado);
			query.setMaxResults(filtro.getNumElementos());
			if (filtro.getEstado() != null) {
    			query.setLong("estado", filtro.getEstado());
    		}
    		if (filtro.getIdElemento() != null) {
    			query.setLong("idElemento", filtro.getIdElemento());
    		}
    		
    		if (filtro.getExiste() != null) {
    			query.setInteger("existe", filtro.getExiste());
    		}
    		List<Sia> lista = (List<Sia>) query.list();
    		resultado.setListaResultados(lista);
    		
    		//El total.
    		//consultaTotal.append(consulta); //No hace falta el order by en los counts
    		final Query queryTotal = session.createQuery( consultaTotal.toString() );
    		if (filtro.getEstado() != null) {
    			queryTotal.setLong("estado", filtro.getEstado());
    		}
    		if (filtro.getIdElemento() != null) {
    			queryTotal.setLong("idElemento", filtro.getIdElemento());
    		}
    		
    		if (filtro.getExiste() != null) {
    			queryTotal.setInteger("existe", filtro.getExiste());
    		}
    		Integer totalResultados = Integer.valueOf( queryTotal.uniqueResult().toString() );
    		resultado.setTotalResultados(totalResultados);
    		
    		return resultado;

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
	public ResultadoBusqueda getSiaProceso(final FiltroSia filtro) {
		log.debug("Obtener SIA Job");
		
		Session session = getSession();
		ResultadoBusqueda resultado = new ResultadoBusqueda();
		
		try {
    		
    		final StringBuilder consultaSelect = new StringBuilder("select sia from SiaJob as sia where 1 = 1 ");
    		final StringBuilder consultaTotal = new StringBuilder("select count(sia) from SiaJob as sia where 1 = 1 ");
    		
    		
    		if (filtro.getEstado() != null) {
    			consultaSelect.append(" and sia.estado = :estado");
    			consultaTotal.append(" and sia.estado = :estado");
    		}
    		
    		if (filtro.getIdElemento() != null) {
    			consultaSelect.append(" and sia.idElemento = :idElemento");
    			consultaTotal.append(" and sia.idElemento = :idElemento");
    		}
    		
    		if (filtro.getExiste() != null) {
    			consultaSelect.append(" and sia.existe = :existe");
    			consultaTotal.append(" and sia.existe = :existe");
    		}
    		
    		if (filtro.getTipo() != null) {
    			consultaSelect.append(" and sia.tipo = :tipo");
    			consultaTotal.append(" and sia.tipo = :tipo");
    		}
    		
    		
    		final StringBuilder consulta = new StringBuilder();
    		//Indicamos el orden, por defecto es por la id descendente
    		if (filtro.getOrdenCampo() == null || filtro.getOrdenCampo().isEmpty()) {
    			consulta.append(" order by sia.id desc");
    		} else {
    			consulta.append(" order by sia.");
    			consulta.append(filtro.getOrdenCampo());
    			if (filtro.getOrdenAsc() == null || filtro.getOrdenAsc().isEmpty()) {
    				consulta.append(" asc ");
    			} else {
    				consulta.append(" ");
    				consulta.append(filtro.getOrdenAsc());
    			}
    		}
    		
    		//La select.
    		consultaSelect.append(consulta);
    		final Query query = session.createQuery( consultaSelect.toString() );
    		//Calculamos a partir de cuando hay que calcular.
    		final int primerResultado = new Integer(filtro.getPagina()).intValue() * filtro.getNumElementos();
			query.setFirstResult(primerResultado);
			query.setMaxResults(filtro.getNumElementos());
			if (filtro.getEstado() != null) {
    			query.setLong("estado", filtro.getEstado());
    		}
    		if (filtro.getIdElemento() != null) {
    			query.setLong("idElemento", filtro.getIdElemento());
    		}
    		
    		if (filtro.getExiste() != null) {
    			query.setInteger("existe", filtro.getExiste());
    		}
    		
    		if (filtro.getTipo() != null) {
    			query.setString("tipo", filtro.getTipo());
    		}
    		
    		List<Sia> lista = (List<Sia>) query.list();
    		resultado.setListaResultados(lista);
    		
    		//El total.
    		//consultaTotal.append(consulta); //No hace falta el order by en los counts
    		final Query queryTotal = session.createQuery( consultaTotal.toString() );
    		if (filtro.getEstado() != null) {
    			queryTotal.setLong("estado", filtro.getEstado());
    		}
    		if (filtro.getIdElemento() != null) {
    			queryTotal.setLong("idElemento", filtro.getIdElemento());
    		}
    		
    		if (filtro.getExiste() != null) {
    			queryTotal.setInteger("existe", filtro.getExiste());
    		}
    		
    		if (filtro.getTipo() != null) {
    			queryTotal.setString("tipo", filtro.getTipo());
    		}
    		Integer totalResultados = Integer.valueOf( queryTotal.uniqueResult().toString() );
    		resultado.setTotalResultados(totalResultados);
    		
    		return resultado;

    	} catch (HibernateException he) {

    		throw new EJBException(he);

    	} finally {

    		close(session);
    		log.debug("Obtener SIA proceso OK");
    	}	
	}
	
	/***
	 * Genera SIA pendiente. Se ha simplificado el flujo. <br />
	 * Paso 1. borramos todo dato almacenado en Sia pendiente asociado a ese id elemento.<br /> 
	 * Paso 2. miramos si  es enviable (SiaUtils.isEnviable) o no:
	 * <ul>
	 * 		<li>SI es enviable, continuamos.
	 * 		<li>No es enviable y PARAMOS. Es no enviable porque no cumple una de las siguiente condiciones:
	 * 			<ul>
	 * 				<li>El procedimiento es visible</li>
	 * 				<li>La UA (organo resolutorio) o predecesor es visible</li>
	 * 				<li>La UA (organo resolutorio) o predecesor tiene DIR3</li>
	 * 				<li>La UA (organo resolutorio) o predecesor está en SiaUA</li>
	 * 			</ul>
	 * 
	 * 		</li>
	 * </ul>
	 *  Paso 3., miramos si le faltan datos:
	 *  <ul>
	 *  	<li>Si no le faltan datos, guardamos un sia pendiente como por ejecutar.</li>
	 *  	<li>Si le faltan datos, guardamos un sia pendiente como ya ejecutado (estado = -2) y el mensaje con el dato que le falta. </li>
	 *  </ul>
	 *  
	 *  Si no son de tipo procedimiento, se realiza el paso 1 y se inserta como pendiente.
	 *  
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @param siaPendiente
	 * @throws DelegateException
	 */
	public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente, ProcedimientoLocal iProcedimiento, Servicio iServicio) throws DelegateException  {
		log.debug("Generar SIA pendiente");
		
		Session session = getSession();
		
		try {
			
			//Paso 1. Se borran todos los que coincidan el id elemento.
			final FiltroSia filtro = new FiltroSia();
			filtro.setNumElementos(9999); filtro.setPagina(0);
			filtro.setIdElemento(siaPendiente.getIdElemento());
			List<SiaPendiente> pendientes =  (List<SiaPendiente>) this.getSiaPendientes(filtro).getListaResultados();
			
			for(SiaPendiente siaPendienteParBorrar: pendientes) {
				session.delete(siaPendienteParBorrar);
			}
			if (pendientes.size() > 0) {
				session.flush();
			}
			
			//Vemos si es de tipo procedimiento.
			if (SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo())) {
				//Paso 2. Checkeamos si es enviable, si no es enviable, no seguimos.
				
				ProcedimientoLocal procedimiento;
				if (iProcedimiento == null) {
					procedimiento = DelegateUtil.getProcedimientoDelegate().obtenerProcedimientoParaSolr(siaPendiente.getIdElemento(), null);
				} else {
					procedimiento = iProcedimiento;
				}
				SiaEnviableResultado siaEnviable = SiaUtils.isEnviable(procedimiento);
				
				if (siaEnviable.isNotificiarSIA()) {
					
					if (siaPendiente.getExiste() != null && siaPendiente.getExiste() == SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO) {
						//Si ha sido borrado, no se enviaba por el cumple datos.
						siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
						session.save(siaPendiente);
						session.flush();
					} else {
						//Paso 3. 
						SiaCumpleDatos cumpleDatos = SiaUtils.cumpleDatos(procedimiento);
						if (cumpleDatos.isCumpleDatos()) {
							siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
							session.save(siaPendiente);
							session.flush();
						} else {
							siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS);
							siaPendiente.setMensaje(cumpleDatos.getRespuesta());
							session.save(siaPendiente);
							session.flush();
						}
<<<<<<< HEAD
					}
				}
				
			} else if (SiaUtils.SIAPENDIENTE_TIPO_SERVICIO.equals(siaPendiente.getTipo())) {
				//Paso 2. Checkeamos si es enviable, si no es enviable, no seguimos.
				
				Servicio servicio;
				if (iServicio == null) {
					servicio = DelegateUtil.getServicioDelegate().obtenerServicioParaSolr(siaPendiente.getIdElemento(), null);
				} else {
					servicio = iServicio;
				}
				SiaEnviableResultado siaEnviable = SiaUtils.isEnviable(servicio);
				
				if (siaEnviable.isNotificiarSIA()) {
					
					if (siaPendiente.getExiste() != null && siaPendiente.getExiste() == SiaUtils.SIAPENDIENTE_SERVICIO_BORRADO) {
						//Si ha sido borrado, no se enviaba por el cumple datos.
						siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
						session.save(siaPendiente);
						session.flush();
					} else {
						//Paso 3. 
						SiaCumpleDatos cumpleDatos = SiaUtils.cumpleDatos(servicio);
						if (cumpleDatos.isCumpleDatos()) {
							siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
							session.save(siaPendiente);
							session.flush();
						} else {
							siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS);
							siaPendiente.setMensaje(cumpleDatos.getRespuesta());
							session.save(siaPendiente);
							session.flush();
						}
=======
>>>>>>> refs/remotes/origin/rolsac-1.5
					}
				}
				
			} else { //Es tipo UA o normativa. Por lo que directamente se escribe.
				siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CREADO);
				session.save(siaPendiente);
				session.flush();
			}
			
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
	 */
	public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente)  {
		log.debug("Actualizar SIA pendiente");

		Session session = getSession();
		
		try {
			if (siaPendiente.getMensaje() != null && siaPendiente.getMensaje().length() > 255) {
				siaPendiente.setMensaje(siaPendiente.getMensaje().substring(0, 253));
			}
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
   	 */
    public void borrarSiaPendiente(SiaPendiente siaPendiente) {
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
    		estados.add(SiaUtils.SIAJOB_ESTADO_EN_EJECUCION);
    		estados.add(SiaUtils.SIAJOB_ESTADO_CREADO);
    		
    		final Query query = getSession().createQuery("from SiaJob siaJob where siaJob.fechaFin is null AND (siaJob.estado is null OR siaJob.estado in (:lId)) ");
    		query.setParameterList("lId", estados, Hibernate.INTEGER);
    		List<SiaJob> jobs =  query.list();
    		for(SiaJob job : jobs) {
    			job.setFechaFin(new Date());
    			job.setEstado(SiaUtils.SIAJOB_ESTADO_ERROR_GRAVE);
    			
    			StringBuffer bufferDesc = SiaUtils.obtenerContenidoClob(job.getDescBreve());
    					
    			job.setDescBreve(Hibernate.createClob("Finalizado a la fuerza " + bufferDesc));
    			job.setDescripcion(Hibernate.createClob("Finalizado a la fuerza " + SiaUtils.obtenerContenidoClob(job.getDescripcion())));
    			
    			session.update(job);
    		} 
    		session.flush();
			return true;
	    } catch(Exception exception) {
			throw new EJBException(exception);
		} finally {
			close(session); 
		}
    }
    
    /**
     * Revisa si se está ejecutando algún job.
     * Se hacen 2 comprobaciones:
     * <ul>
     *  <lo>Primero si según el StdSchedulerFactory está activo.</lo>
     *  <lo>Segundo si sale en la bbdd alguno no cerrado (se verán los últimos 5) </lo>
     * </ul>
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return Booleano True si hay algo activo y false si no lo está.  
     * @throws SchedulerException 
   	 */
    public boolean checkJobsActivos() throws SchedulerException {
    	
    	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler(); 
    	boolean retorno = false;
    	
    	//Buscamos en el grupo por defecto 'DEFAULT' un nombre de job que coincida con el de 'siaJob'.	 
    	for (String jobName : scheduler.getJobNames(Scheduler.DEFAULT_GROUP)) {
    		  if ("SiaPendienteJob".equals(jobName)) {
    			  retorno = true;
   			  }
    	}
    	
    	if (!retorno) {
	    	//Se ha simplificado, se verán los últimos jobs ejecutados y, si alguno de ellos está sin fecha fin
	    	//  se da por hecho que se está ejecutando.
	    	List<SiaJob> jobs = getListJobs(5);
	    	for(SiaJob job : jobs) {
	    		if (job.getFechaFin() == null) {
	    			retorno = true;
	    		}
	    	}
    	}
    	return retorno;
    	
    	
    }
    
    
    /**
     * Borra todos los datos en sia pendiente.
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public void borrarSiaPendientes() {
    	final Session session = getSession();
    	try
    	{
    		session.delete("Select siaPdt from SiaPendiente siaPdt");
    		session.flush();			
	    } catch(Exception exception) {
			throw new EJBException(exception);
		} finally {
			close(session); 
		}
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

        final Session session = getSession();
        try {
            Criteria criteri = session.createCriteria(SiaJob.class);
            criteri.addOrder(Order.desc("id"));
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
	public SiaJob crearSiaJob(String tipo) {
		Session session = null;
		try
    	{
			 session = getSession();
			final SiaJob siaJob = new SiaJob();
	    	siaJob.setFechaIni(new Date());
	    	siaJob.setTipo(tipo);
	    	session.save(siaJob); 
			session.flush();
			
			return siaJob;
    	 } catch(Exception exception) {
 			throw new EJBException(exception);
 		} finally {
 			close (session);
 		}
	}
	
	
	 /**
     * Actualizar una sia job. 
     * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	 * @return   
   	 */
	public void actualizarSiaJob(SiaJob siaJob) {
		Session session  = null;
		try
    	{
			session = getSession();
			session.update(siaJob); 
			session.flush();
						
    	 } catch(Exception exception) {
 			throw new EJBException(exception);
 		}finally {
			close(session);
		}
	}
	
	
	/**
	 * Cerrando el pendiente job.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public void cerrarSiaJob(Long idSiaJob)  {
    	Session session = null;
    	try
    	{
    		session = getSession();
    		SiaJob siaJobNuevo = (SiaJob) session.get(SiaJob.class, idSiaJob);
    		siaJobNuevo.setFechaFin(new Date());
	    	session.update(siaJobNuevo); 
			session.flush();
	    } catch(Exception exception) {
			throw new EJBException(exception);
		} finally {
			close(session);
		}
    }
    
    
    /**
	 * Actualizar procedimiento.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public void actualizarProcedimiento(ProcedimientoLocal proc, SiaResultado resultado) {

    	final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
    	
		// Actualizamos procedimiento
    	try {
			if (resultado.isCorrecto()) {
				if (proc.getCodigoSIA() == null || proc.getCodigoSIA().isEmpty()) { 
					proc.setCodigoSIA(resultado.getCodSIA()); 
				}
				proc.setEstadoSIA(resultado.getEstadoSIA());
				proc.setFechaSIA(new Date());
				procDelegate.actualizarProcedimiento(proc);
			} 
    	} catch (Exception ex) {
    		log.error("Error actualizando información de SIA en el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    		throw new EJBException("Error actualizando información de SIA en el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    	}
		
	}
    
    
    /**
	 * Envia a SIA un procedimiento borrado.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 *
    public SiaResultado borradoProcedimiento(Long idProc, String idSIA) {   
    	try {
    		SiaResultado resultado = null; 
    		Sia sia = new Sia();
			sia.setIdSIA(idSIA);
			sia.setOperacion(SiaUtils.ESTADO_BAJA); 
			sia.setIdElemento(String.valueOf(idProc));
			resultado = SiaWS.enviarSIA(sia, true);
			return resultado;
    	} catch (Exception ex) {
    		log.error("Error enviando a SIA el procedimiento " + idProc + ": " + ex.getMessage(), ex);
    		throw new EJBException("Error enviando a SIA el procedimiento " + idProc + ": " + ex.getMessage(), ex);
    	}
    	
    }*/
    
    
    /**
  	 * Actualizar procedimiento.
  	 * 
 	 * @ejb.interface-method
 	 * @ejb.permission unchecked="true"
 	 *   
 	 */
    public void actualizarServicio(Servicio servicio, SiaResultado resultado) {

      	final ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
      	
  		// Actualizamos procedimiento
      	try {
  			if (resultado.isCorrecto()) {
  				if (servicio.getCodigoSIA() == null || servicio.getCodigoSIA().isEmpty()) { 
  					servicio.setCodigoSIA(resultado.getCodSIA()); 
  				}
  				servicio.setEstadoSIA(resultado.getEstadoSIA());
  				servicio.setFechaSIA(new Date());
  				servicioDelegate.actualizarServicio(servicio);
  			} 
      	} catch (Exception ex) {
      		log.error("Error actualizando información de SIA en el servicio " + servicio.getId() + ": " + ex.getMessage(), ex);
      		throw new EJBException("Error actualizando información de SIA en el servicio " + servicio.getId() + ": " + ex.getMessage(), ex);
      	}
  		
  	}
      
      
      /**
  	 * Envia a SIA un servicio borrado.
  	 * 
 	 * @ejb.interface-method
 	 * @ejb.permission unchecked="true"
 	 *   
 	 */
    public SiaResultado borradoServicio(Long idServicio, String idSIA) {   
      	try {
      		SiaResultado resultado = null; 
      		Sia sia = new Sia();
  			sia.setIdSIA(idSIA);
  			sia.setOperacion(SiaUtils.ESTADO_BAJA); 
  			sia.setIdElemento(String.valueOf(idServicio));
  			resultado = SiaWS.enviarSIA(sia, true);
  			return resultado;
      	} catch (Exception ex) {
      		log.error("Error enviando a SIA el servicio " + idServicio + ": " + ex.getMessage(), ex);
      		throw new EJBException("Error enviando a SIA el servicio " + idServicio + ": " + ex.getMessage(), ex);
      	}
      	
      }
      
    /**
	 * Graba SiaUA.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 * @param siaUA Sia UA
     * @throws Exception 
   	 */
    public void grabarSiaUA(final SiaUA siaUA)  {
    	
    	final Session session = getSession();
		
    	try {
	    	//Prepaso si es nueva. Tenemos que checkear si tiene DIR3 y que no tiene UA sucesora/precesora con SIA y no está ya introducido.
	    	if (siaUA.getId() == null) {
				//Si tiene DIR3.
	    		if (siaUA.getUnidadAdministrativa().getCodigoDIR3() == null || siaUA.getUnidadAdministrativa().getCodigoDIR3().trim().isEmpty()) {
					throw new EJBException("txt.sia.error.siaua.dir3");
				} 
				
				//Si ya está introducido
	    		final SiaUA siuaPropio = (SiaUA) session.createQuery("Select siaUA From SiaUA siaUA where siaUA.unidadAdministrativa.id = " + siaUA.getUnidadAdministrativa().getId()).uniqueResult();
	    		if (siuaPropio != null) {
	    			throw new EJBException("txt.sia.error.siaua.repetida");
	    		} 
	    		
	    		//Si predecesor ya existe.
				for (Object objectUA : siaUA.getUnidadAdministrativa().getPredecesores()) {
					final UnidadAdministrativa ua = (UnidadAdministrativa) objectUA;
					final SiaUA siaUAPre = (SiaUA) session.createQuery("Select siaUA From SiaUA siaUA where siaUA.unidadAdministrativa.id = " + ua.getId()).uniqueResult();
		    		if (siaUAPre != null) {
		    			throw new EJBException("txt.sia.error.siaua.predecesor");
		    		}
				} 
				
				//Si sucesor ya existe.
				for (Long idUASucesora : DelegateUtil.getUADelegate().obtenerHijosUnidadAdministrativa(siaUA.getUnidadAdministrativa().getId())) {
					final UnidadAdministrativa ua =  DelegateUtil.getUADelegate().obtenerUnidadAdministrativa(idUASucesora);
					final SiaUA siaUAPre = (SiaUA) session.createQuery("Select siaUA From SiaUA siaUA where siaUA.unidadAdministrativa.id = " + ua.getId()).uniqueResult();
		    		if (siaUAPre != null) {
		    			throw new EJBException("txt.sia.error.siaua.sucesora");
		    		}
				} 
			}
	    	
	    	session.saveOrUpdate(siaUA); 
			session.flush();
			
    	} catch (HibernateException e) {
    		throw new EJBException(e);
		} catch (DelegateException e) {
			throw new EJBException(e);
		} finally {
			close(session);
		}
    	 
    }
   
    /**
	 * Devuelve siaUA.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 * @param id id SIA UA
   	 */
    public SiaUA obtenerSiaUA(final Long id)  {
    	Session session = null;
    	try
    	{
    		session = getSession();
    		final SiaUA siaUA = (SiaUA) session.get(SiaUA.class, id);
    		return siaUA;
    	} catch(Exception exception) {
 			throw new EJBException(exception);
 		} finally {
 			close(session);
        }
    }
    
   /********************************
   	* 		Devuelve siaUA.
   	* 
  	* @ejb.interface-method
  	* @ejb.permission unchecked="true"
  	*   
  	* @param id id SIA UA
  	*/
    public SiaUA obtenerSiaUA(final UnidadAdministrativa ua) {
    	SiaUA siaUA = null;
    	Session session = null;
    	try {
    		session = getSession();
	       	final Query query = session.createQuery("Select siaUA from SiaUA siaUA where siaUA.unidadAdministrativa.id = "+ua.getId());
	       	siaUA = (SiaUA) query.uniqueResult();
    	} catch(Exception exception) {
    		throw new EJBException(exception);
    	} finally {
 			close(session);
        }
    	return siaUA;
    }
    
    /********************************
     * Borra elemento siaUA.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 * @param id id SIA UA
   	 */
    public void borrarSiaUA(final Long id)  {
    	 Session session = null;
    	try
    	{
    		session = getSession();
    		session.delete("from SiaUA as siaUA where siaUA.id = ?", id, Hibernate.LONG);
			session.flush();
    	 } catch(Exception exception) {
 			throw new EJBException(exception);
 		} finally {
 			close(session);
        }
    }
    
}

