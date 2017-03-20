package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Order;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.util.FiltroSia;
import org.ibit.rol.sac.persistence.util.SiaEnviableResultado;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.sia.SiaWS;

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
   	
	 * @throws DelegateException
	 */
	public List<SiaPendiente> getSiaPendientesEnviar()  {
		log.debug("Obtener SIA pendientes enviar");

		Session session = getSession();
		
		try {

    		StringBuilder consulta = new StringBuilder("select sia from SiaPendiente as sia where sia.estado = :estado");
    		
    		Query query = session.createQuery( consulta.toString() );
    		query.setLong("estado", SiaUtils.SIAJOB_ESTADO_CREADO);
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

    		StringBuilder consulta = new StringBuilder("select sia from SiaPendiente as sia where 1 = 1 ");
    		
    		if (filtro.getEstado() != null) {
    			consulta.append(" and sia.estado = :estado");
    		}
    		
    		if (filtro.getIdElemento() != null) {
    			consulta.append(" and sia.idElemento = :idElemento");
    		}
    		
    		if (filtro.getExiste() != null) {
    			consulta.append(" and sia.existe = :existe");
    		}
    		consulta.append(" order by sia.id desc");
    		
    		Query query = session.createQuery( consulta.toString() );
    		
    		if (filtro.getEstado() != null) {
    			query.setLong("estado", filtro.getEstado());
    		}
    		if (filtro.getIdElemento() != null) {
    			query.setLong("idElemento", filtro.getIdElemento());
    		}
    		
    		if (filtro.getExiste() != null) {
    			query.setInteger("existe", filtro.getExiste());
    		}
    		
    		
    		if (filtro.numElementos != null) {
    			query.setMaxResults(filtro.numElementos);
    		}
    		
    		
    		
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

    		StringBuilder consulta = new StringBuilder("select sia from SiaJob as sia order by sia.id desc");
    		
    		Query query = session.createQuery( consulta.toString() );
    		
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

			//Si es de tipo borrado, hay que borrar las acciones pendientes de tipo accion existe.
			if (SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO.compareTo(siaPendiente.getExiste()) == 0) {
				
				List<SiaPendiente> pendientes = getPendientesSinEjecutar(siaPendiente.getIdElemento());
				for(SiaPendiente siaPendienteParBorrar: pendientes) {
					session.delete(siaPendienteParBorrar);
				}
				
				//Guardamos
				siaPendiente.setEstado(SiaUtils.SIAJOB_ESTADO_CREADO);
	    		session.save(siaPendiente);
	    		session.flush();
			} else {
				//Si es de tipo creado, revisar si ya existe un pendiente.
				FiltroSia filtro = new FiltroSia();
				filtro.setEstado(SiaUtils.SIAJOB_ESTADO_CREADO);
				filtro.setExiste(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE);
				filtro.setIdElemento(siaPendiente.getIdElemento());
				List<SiaPendiente> pendientes = this.getSiaPendientes(filtro);
			
				//Si no hay datos, creamos.
				if (pendientes.size() == 0) {
					siaPendiente.setEstado(SiaUtils.SIAJOB_ESTADO_CREADO);
					session.save(siaPendiente);
					session.flush();
				}
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
	 * Obtiene las pendientes de ejecutar de si existe.
	 * @param idElemento
	 * @return
	 */
	private List<SiaPendiente> getPendientesSinEjecutar(Long idElemento) {
		final FiltroSia filtro = new FiltroSia();
		filtro.setEstado(SiaUtils.SIAJOB_ESTADO_CREADO);
		filtro.setExiste(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE);
		filtro.setIdElemento(idElemento);
		return this.getSiaPendientes(filtro);
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
     * @throws DelegateException 
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
		try
    	{
			final Session session = getSession();
			final SiaJob siaJob = new SiaJob();
	    	siaJob.setFechaIni(new Date());
	    	siaJob.setTipo(tipo);
	    	session.save(siaJob); 
			session.flush();
			session.close();
			return siaJob;
    	 } catch(Exception exception) {
 			throw new EJBException(exception);
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
		try
    	{
			final Session session = getSession();
			session.update(siaJob); 
			session.flush();
			session.close();			
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
    public void cerrarSiaJob(Long idSiaJob)  {
    	try
    	{
    		Session session = getSession();
    		SiaJob siaJobNuevo = (SiaJob) session.get(SiaJob.class, idSiaJob);
    		siaJobNuevo.setFechaFin(new Date());
	    	session.update(siaJobNuevo); 
			session.flush();
			session.close();
	    } catch(Exception exception) {
			throw new EJBException(exception);
		}
    }
    
    
    /**
	 * Envia a SIA.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public SiaResultado enviarProcedimiento(ProcedimientoLocal proc) {

    	final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
    	
    	// Enviamos a SIA
    	SiaResultado resultado = null; 
    	try {
			// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaProc(proc);
			// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false);
    	} catch (Exception ex) {
    		log.error("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    		throw new EJBException("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    	}

		// Actualizamos procedimiento
    	try {
			if (resultado.isCorrecto()) {
				proc.setCodigoSIA(resultado.getCodSIA());
				proc.setEstadoSIA(resultado.getEstadoSIA());
				proc.setFechaSIA(new Date());
				procDelegate.actualizarProcedimiento(proc);
			} 
    	} catch (Exception ex) {
    		log.error("Error actualizando información de SIA en el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    		throw new EJBException("Error actualizando información de SIA en el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    	}
		
		return resultado;
	}
    
    
    /**
	 * Envia a SIA un procedimiento borrado.
	 * 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 *   
   	 */
    public SiaResultado borradoProcedimiento(Long idProc, String idSIA) {   
    	try {
    		SiaResultado resultado = null; 
    		Sia sia = new Sia();
			sia.setIdSIA(idSIA);
			sia.setOperacion(SiaUtils.ESTADO_BAJA); 
			sia.setIdProc(String.valueOf(idProc));
			resultado = SiaWS.enviarSIA(sia, true);
			return resultado;
    	} catch (Exception ex) {
    		log.error("Error enviando a SIA el procedimiento " + idProc + ": " + ex.getMessage(), ex);
    		throw new EJBException("Error enviando a SIA el procedimiento " + idProc + ": " + ex.getMessage(), ex);
    	}
    	
    }
    
    
    /**
	 * Obtiene el objeto SIA a partir del procedimiento.
	 * 
	 * @param procedimiento
	 * @return
	 * @throws Exception
	 */
	private Sia obtenerSiaProc(ProcedimientoLocal procedimiento) throws Exception {
		Sia sia = new Sia();
		
		sia.setIdProc(procedimiento.getId().toString());
		if (procedimiento.getCodigoSIA() != null) {
			sia.setIdSIA(procedimiento.getCodigoSIA().toString());
		}
		TraduccionProcedimientoLocal trad = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		if(trad == null){
			trad = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
			if (trad != null) {
				sia.setTitulo(trad.getNombre());
				sia.setDescripcion(trad.getResumen());
			}
		} else {
			sia.setTitulo(trad.getNombre());
			sia.setDescripcion(trad.getResumen());
		}
		
		sia.setIdCent(getIdCentro(procedimiento));
		sia.setIdDepartamento(SiaUtils.getIdDepartamento());
		if (procedimiento.getUnidadAdministrativa().getTraduccion("es") != null && ((TraduccionUA)  procedimiento.getUnidadAdministrativa().getTraduccion("es")).getNombre() != null) {
			sia.setUaGest(((TraduccionUA)  procedimiento.getUnidadAdministrativa().getTraduccion("es")).getNombre());
		} else {
			sia.setUaGest(((TraduccionUA)  procedimiento.getUnidadAdministrativa().getTraduccion("ca")).getNombre());
		}
		
		String[] destinatarios = new String[procedimiento.getPublicosObjetivo().size()];
		Set<PublicoObjetivo> publicoObjs = procedimiento.getPublicosObjetivo();
		int i = 0;
		for (PublicoObjetivo pObj : publicoObjs) {
			switch(pObj.getId().intValue()) {
				case 200:
					destinatarios[i] = "1";
					i++;
					break;
				case 201:
					destinatarios[i] = "2";
					i++;
					break;
				case 202:
					destinatarios[i] = "3";
					i++;
					break;
			}
		}
		
		sia.setIdDest(destinatarios);
		
		List<Tramite> tramites = procedimiento.getTramites();
		Integer nivelAdministrativo = 1;
		for (Tramite tramite : tramites) {
			if (tramite != null && tramite.getFase() == 1)  {
					if (tramite.getVersio() != null || 
							(tramite.getUrlExterna() != null && !tramite.getUrlExterna().isEmpty())) {
						nivelAdministrativo = 4;
						break;
					}
				
					//Revisar no inicializa la lista dentro de tramite aunq en el servicio que obtiene proc si lo hace...
					TramiteDelegate tramDelegate = DelegateUtil.getTramiteDelegate();
					Tramite tram = tramDelegate.obtenerTramite(tramite.getId());
					
					Set<DocumentTramit> docs = tram.getDocsInformatius();
					docs.addAll(tram.getDocsRequerits());
					
					for (DocumentTramit documentTramit : docs) {
						if (documentTramit.getTipus() == 1) {
							nivelAdministrativo=2;
						}
					}
			}
		}
		sia.setNivAdm(nivelAdministrativo.toString());

		sia.setNormativas(procedimiento.getNormativas());
		
		final List<String> materias = new ArrayList<String>();
		
		if (procedimiento.getMaterias() != null){			
			for (Materia mat : procedimiento.getMaterias()) {
				if (mat.getCodigoSIA() != null) {
					if (!materias.contains(mat.getCodigoSIA().toString())) {
						materias.add(mat.getCodigoSIA().toString());
					}
				}
			}
			sia.setMaterias(materias.toArray(new String[materias.size()]));
		}

		if (procedimiento.getIndicador() == null) {
			sia.setFiVia(SiaUtils.NO);
		} else {
			sia.setFiVia(procedimiento.getIndicador().equals("1")? SiaUtils.SI : SiaUtils.NO);
		}
		sia.setTipologia(SiaUtils.getTipologiaTramitacion());
		
		sia.setEnlaceWeb(SiaUtils.getUrl()+procedimiento.getId().toString());
		
		sia.setEstado(procedimiento.getEstadoSIA());
		
		//Obtenemos el tipo de operación a partir de validar el procedimiento.
		SiaEnviableResultado siaEnviableResultado = SiaUtils.validaProcedimientoSIA(procedimiento);
		sia.setOperacion(siaEnviableResultado.getOperacion());
					
		//Se ha tenido que poner aquí (y se ha simplificado) pq se produce un error al enviar una modificación.
		if (SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
			sia.setTipoTramite(SiaUtils.TRAMITE_PROC);			
		} else {
			sia.setTipoTramite(null);
		}
		
		return sia;
	}
    
	
	/**
	 * Método que encuentra el id centro a partir del propio elemento y de los predecesores.
	 * Es importante tener en cuenta que hay un nivel (por propiedades) y que el propio elemento no está
	 *   dentro de las uas.
	 * @param procedimiento
	 * @return
	 * @throws Exception 
	 */
	private String getIdCentro(ProcedimientoLocal procedimiento) throws Exception {
		int nivel = SiaUtils.getLevelSIA();
		int i;
		if ( procedimiento.getServicioResponsable().getPredecesores().size() > nivel) {
			i = nivel - 1;
		} else {
			if (procedimiento.getUnidadAdministrativa().getCodigoDIR3() != null) {
				return procedimiento.getUnidadAdministrativa().getCodigoDIR3();
			} 
			if (procedimiento.getOrganResolutori().getPredecesores().size() == 0) { 
				throw new Exception("No existe ningún elemento ni predecesor con DIR3."); 
			}
			i = procedimiento.getOrganResolutori().getPredecesores().size() - 1;
		}
		for(; i < procedimiento.getOrganResolutori().getPredecesores().size() && i>=0; i--) {
			UnidadAdministrativa predecesor = (UnidadAdministrativa) procedimiento.getOrganResolutori().getPredecesores().get(i);
			if (predecesor.getPadre() != null && predecesor.getCodigoDIR3() != null) {
				return predecesor.getCodigoDIR3();
			}
		}
		
		throw new Exception("No existe ningún elemento ni predecesor con DIR3..");
	}
}

