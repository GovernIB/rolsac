package org.ibit.rol.sac.persistence.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.EstadoProcesoSIA;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.ResultadoSiaPendiente;
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.TraduccionUA;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.ServicioDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
import org.ibit.rol.sac.persistence.util.SiaCumpleDatos;
import org.ibit.rol.sac.persistence.util.SiaEnviableResultado;
import org.ibit.rol.sac.persistence.util.SiaUtils;
import org.ibit.rol.sac.persistence.ws.sia.SiaWS;



/**
 * SessionBean para operar con envios SIA.
 *
 * @ejb.bean
 *  name="sac/persistence/SiaPendienteFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.SiaPendienteFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="NotSupported"
 */
public abstract class SiaPendienteFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 2372863171398481198L;

	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	@Override
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	
	
	/**
	 * Obtener SIA pendientes de enviar 
	 * @throws DelegateException 
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 */
	public void enviarTodos(SiaJob siaJob) throws DelegateException  {
		log.debug("Enviar todos los procedimientos/servicios SIA ");
		
		//Prepaso, borramos todos los pendintes.
  		DelegateUtil.getSiaPendienteProcesoDelegate().borrarSiaPendientes(); 
  		
		
  		// Buscamos procedimientos a revisar
		List<Long> idProcedimientos = null;
		try {
			idProcedimientos = DelegateUtil.getProcedimientoDelegate().buscarIdsProcedimientos();
		} catch (DelegateException e1) {
			log.error("Error recuperando lista de procedimientos", e1);
			throw new EJBException("Error recuperando lista de procedimientos", e1);
		}
		
		// Buscamos servicios a revisar
		List<Long> idServicios = null;
		try {
			idServicios = DelegateUtil.getServicioDelegate().buscarIdsServicios();
		} catch (DelegateException e1) {
			log.error("Error recuperando lista de servicios", e1);
			throw new EJBException("Error recuperando lista de servicios", e1);
		}
		
		// Inicializamos vble para establecer estado proceso
		EstadoProcesoSIA estadoProceso = new EstadoProcesoSIA();
		estadoProceso.setFechaInicio(new Date());
		estadoProceso.setTotal(idProcedimientos.size() +  idServicios.size() );
		
		
		estadoProceso = enviarTodosProcedimientos(idProcedimientos, siaJob, estadoProceso);
		estadoProceso = enviarTodosServicios(idServicios, siaJob, estadoProceso);

		
		// Finalizamos proceso y guardamos
		estadoProceso.setFechaFin(new Date());		
		guardarEstadoProcesoSIA(siaJob, estadoProceso);
		    	
	}
	
	/**
	 * Indexar procedimientos.
	 * @param siaJob
	 * @param estadoProceso
	 * @return
	 */
	private EstadoProcesoSIA enviarTodosProcedimientos(List<Long> idProcedimientos, SiaJob siaJob, EstadoProcesoSIA estadoProceso) {
		
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		// Recorremos procedimientos y enviamos a SIA
				try {
					
			    	for(Long idProcedimiento : idProcedimientos ) {
			    		
			    		try 
			    		{
				    		//Obtenemos el procedimiento y vemos is es enviable a SIA.
				    		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento, null);
			    			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(procedimiento);
				    		
				    		SiaResultado siaResultado = null;
							if (siaEnviableResultado.isNotificiarSIA()) {
								SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(procedimiento, siaEnviableResultado, true);
								if (siaCumpleDatos.isCumpleDatos()) {
						    		try {
						    			// Enviamos a SIA
						    			siaResultado = enviarProcedimiento(procedimiento);		    						    		
									} catch (Exception e) {
										log.error("Error enviando procedimiento " + procedimiento.getId(), e);
										siaResultado = new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(e));												
									}	   
								} else {
									//Guardamos un SIA Pendiente como que no cumple datos (ultima pestanya)
									SiaPendiente siaPendiente = new SiaPendiente();
									siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS);
									siaPendiente.setExiste(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE);
									siaPendiente.setFecAlta(new Date());
									siaPendiente.setFecIdx(new Date());
									siaPendiente.setIdElemento(idProcedimiento);
									siaPendiente.setTipo(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO);
									siaPendiente.setMensaje(siaCumpleDatos.getRespuesta());
									DelegateUtil.getSiaPendienteProcesoDelegate().generarSiaPendiente(siaPendiente, procedimiento,  null);
									siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_CUMPLE_DATOS, siaCumpleDatos.getRespuesta());
								}
				    		} else {
				    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_ENVIABLE, siaEnviableResultado.getRespuesta());		
				    		}
							
							// Actualizamos estado proceso
							actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso, "procedimiento");
			    		
							// Guarda estado proceso
				    		guardarEstadoProcesoSIA(siaJob, estadoProceso);
			    		} catch (Exception exception) {
			    			log.error("Error no controlado enviando procedimiento (id:"+idProcedimiento+"): " + exception.getMessage(), exception);
			    		}
			    		
					}
			    	
				} catch (Exception exception) {
					log.error("Error no controlado enviando procedimientos: " + exception.getMessage(), exception);		
					// Actualizamos estado proceso (error general)
					actualizaEstadoProceso(null,  new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(exception)), estadoProceso, "procedimiento");			
				}
				return estadoProceso;
	}
	
	
	/**
	 * Indexar procedimientos.
	 * @param siaJob
	 * @param estadoProceso
	 * @return
	 */
	private EstadoProcesoSIA enviarTodosServicios(List<Long> idServicios, SiaJob siaJob, EstadoProcesoSIA estadoProceso) {
		
		
		final ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
		
		// Recorremos servicios y enviamos a SIA
				try {
					
			    	for(Long idServicio : idServicios ) {
			    		
			    		try 
			    		{
				    		//Obtenemos el servicio y vemos is es enviable a SIA.
				    		Servicio servicio = servicioDelegate.obtenerServicioParaSolr(idServicio, null);
			    			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(servicio);
				    		
				    		SiaResultado siaResultado = null;
							if (siaEnviableResultado.isNotificiarSIA()) {
								SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(servicio, siaEnviableResultado, true);
								if (siaCumpleDatos.isCumpleDatos()) {
						    		try {
						    			// Enviamos a SIA
						    			siaResultado = enviarServicio(servicio);		    						    		
									} catch (Exception e) {
										log.error("Error enviando servicio " + servicio.getId(), e);
										siaResultado = new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(e));												
									}	   
								} else {
									//Guardamos un SIA Pendiente como que no cumple datos (ultima pestaña)
									SiaPendiente siaPendiente = new SiaPendiente();
									siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS);
									siaPendiente.setExiste(SiaUtils.SIAPENDIENTE_SERVICIO_EXISTE);
									siaPendiente.setFecAlta(new Date());
									siaPendiente.setFecIdx(new Date());
									siaPendiente.setIdElemento(idServicio);
									siaPendiente.setTipo(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO);
									siaPendiente.setMensaje(siaCumpleDatos.getRespuesta());
									DelegateUtil.getSiaPendienteProcesoDelegate().generarSiaPendiente(siaPendiente,  null, servicio);
									siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_CUMPLE_DATOS, siaCumpleDatos.getRespuesta());
								}
				    		} else {
				    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_ENVIABLE, siaEnviableResultado.getRespuesta());		
				    		}
							
							// Actualizamos estado proceso
							actualizaEstadoProceso(servicio.getId(), siaResultado, estadoProceso, "servicio");
			    		
							// Guarda estado proceso
				    		guardarEstadoProcesoSIA(siaJob, estadoProceso);
			    		} catch (Exception exception) {
			    			log.error("Error no controlado enviando servicio (id:"+idServicio+"): " + exception.getMessage(), exception);
			    		}
			    		
					}
			    	
				} catch (Exception exception) {
					log.error("Error no controlado enviando servicio: " + exception.getMessage(), exception);		
					// Actualizamos estado proceso (error general)
					actualizaEstadoProceso(null,  new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(exception)), estadoProceso, "servicio");			
				}
				return estadoProceso;
	}


	private SiaResultado enviarProcedimiento(
			ProcedimientoLocal proc) throws Exception {
		
		// Enviamos a SIA
		SiaResultado resultado = null;
    	try {
			// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaProcedimiento(proc);
	    	
	    	// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false, false);
			
			// Actualizamos procedimiento
	    	final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
			siaDelegate.actualizarProcedimiento(proc, resultado);
			
    	} catch (Exception ex) {
    		if (ex.getMessage() != null && ex.getMessage().contains("0167:")) {
    			//El código 0167 es porque ya ha sido dado de baja.
    			resultado = new SiaResultado();
    			resultado.setResultado(SiaResultado.RESULTADO_OK);				
    		} else { 
    			log.error("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
	    		throw new Exception("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    		}
    	}
		
    	
		return resultado;
	}
	
	/**
	 * Envia procedimiento como no activo.
	 * 
	 * @param proc
	 * @return
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public SiaResultado enviarProcedimientoNoActivo(ProcedimientoLocal proc) throws DelegateException   {
		
		// Enviamos a SIA
		SiaResultado resultado = null;
    	try {
    		
    		// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaProcedimientoNoActivo(proc);
	    	
	    	// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false, true);
			
			// Actualizamos procedimiento
	    	final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
	    	resultado.setEstadoSIA("B"); //Se marca como baja
			siaDelegate.actualizarProcedimiento(proc, resultado);
			
    	} catch (Exception ex) {
    		resultado = new SiaResultado();
    		resultado.setCorrectos(0);
			resultado.setResultado(SiaResultado.RESULTADO_ERROR); 
			resultado.setMensaje(ExceptionUtils.getStackTrace(ex));
    	}
    	
		return resultado;
	}
	
	private SiaResultado enviarServicio(Servicio servicio) throws Exception {
		
		// Enviamos a SIA
		SiaResultado resultado = null;
    	try {
			// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaServicio(servicio);
	    	
	    	// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false, false);
			
			// Actualizamos servicio
	    	final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
			siaDelegate.actualizarServicio(servicio, resultado);
			
    	} catch (Exception ex) {
    		if (ex.getMessage() != null && ex.getMessage().contains("0167:")) {
    			//El código 0167 es porque ya ha sido dado de baja.
    			resultado = new SiaResultado();
    			resultado.setResultado(SiaResultado.RESULTADO_OK);				
    		} else { 
    			log.error("Error enviando a SIA el servicio " + servicio.getId() + ": " + ex.getMessage(), ex);
	    		throw new Exception("Error enviando a SIA el servicio " + servicio.getId() + ": " + ex.getMessage(), ex);
    		}
    	}
		
    	
		return resultado;
	}


	/**
	 * Envia servicio como no activo
	 * @param servicio
	 * @return
	 * @throws Exception
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 */
	public SiaResultado enviarServicioNoActivo(Servicio servicio) throws DelegateException  {
		
		// Enviamos a SIA
		SiaResultado resultado = null;
    	try {
			// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaServicioNoActivo(servicio);
	    	
	    	// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false, true);
			
			// Actualizamos servicio
	    	final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
	    	resultado.setEstadoSIA("B"); //Se marca como baja
			siaDelegate.actualizarServicio(servicio, resultado);
			
    	} catch (Exception ex) {
    		resultado = new SiaResultado();
			resultado.setResultado(SiaResultado.RESULTADO_ERROR); 
			resultado.setMensaje(ExceptionUtils.getStackTrace(ex));
    	}
		
    	
		return resultado;
	}

	
	private void actualizaEstadoProceso(Long id, SiaResultado siaResultado, EstadoProcesoSIA estadoProceso, String literalTabla ) {
		
		if (siaResultado == null || estadoProceso == null) {
			//Para evitar cualquier error se incluye, no debería entrar.
			estadoProceso.addLineaDetalle("   ---- "+literalTabla+": " + id + ", revisar.");
			return;
		} else if (siaResultado.isNoCumpleDatos()) {
			estadoProceso.addNoCumpleDatos();
			estadoProceso.addLineaDetalle("   ---- "+literalTabla+": " + id + " s'hauria d'enviar però falten dades: " +  siaResultado.getMensaje() + "<br />");
		} else if (siaResultado.isNoEnviable()) {
			estadoProceso.addNoEnviable();
			estadoProceso.addLineaDetalle("   ---- "+literalTabla+": " + id + " no és visible i no s'ha d'enviar a SIA: " +  siaResultado.getMensaje() + "<br />");
		} else if (siaResultado.isCorrecto()) {
			estadoProceso.addCorrecto(); 
			estadoProceso.addLineaDetalle("   ---- "+literalTabla+": " + id + " <br />");
			estadoProceso.addLineaDetalle("   ---- S'ha enviat a SIA correctament. <br />");
			estadoProceso.addLineaDetalle("   ---- CodiSIA:"+siaResultado.getCodSIA()+". <br />");
			estadoProceso.addLineaDetalle("   ---- EstatSia:"+siaResultado.getEstadoSIA()+". <br />");
			if (siaResultado.getOperacion() != null) {
				estadoProceso.addLineaDetalle("   ---- Operació:"+siaResultado.getOperacion()+". <br />");
			}
		} else {
			if (id == null) {
				estadoProceso.addIncorrecto();
				estadoProceso.addLineaDetalle("   ---- Error no controlat. <br />");
				estadoProceso.addLineaDetalle("   ---- Missatge:"+siaResultado.getMensaje()+" <br />");
				if (siaResultado.getOperacion() != null) {
						estadoProceso.addLineaDetalle("   ---- Operació:"+siaResultado.getOperacion()+". <br />");
				}
			} else {
				estadoProceso.addIncorrecto();
				estadoProceso.addLineaDetalle("   ---- "+literalTabla+": " + id + " <br />");
				estadoProceso.addLineaDetalle("   ---- S'ha enviat a SIA incorrectament. <br />");
				estadoProceso.addLineaDetalle("   ---- Missatge:"+siaResultado.getMensaje());
				if (!siaResultado.getMensaje().contains("<br")) {
					estadoProceso.addLineaDetalle(" <br />");
				}
				if (siaResultado.getOperacion() != null) {
					estadoProceso.addLineaDetalle("   ---- Operació:"+siaResultado.getOperacion()+". <br />");
				}
			}
		}
		estadoProceso.addLineaDetalle("<br />");
		
	}


	private void guardarEstadoProcesoSIA(SiaJob siaJob,
			EstadoProcesoSIA estadoProceso) {
		
		int correctos = estadoProceso.getCorrectos();
		int incorrectos = estadoProceso.getIncorrectos();
		//int nulos = estadoProceso.getNulos();
		
		int noCumpleDatos = estadoProceso.getNoCumpleDatos();
		int noEnviable = estadoProceso.getNoEnviable();
		int total = estadoProceso.getTotal();
		
		
		
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		
		try {
			
			// Si no esta finalizado, actualizamos cada 50
			if ( estadoProceso.getFechaFin() != null ||  (correctos + incorrectos + noCumpleDatos + noEnviable ) % 50 == 0) {
    			StringBuffer resultadoBeta = new StringBuffer();
    			resultadoBeta.append("Iniciat a les " + formato.format(estadoProceso.getFechaInicio()) + "<br />");
    			if (total != -1) {
    				resultadoBeta.append("S'han intentat enviar "+(correctos+incorrectos)+" procediments d'un total de "+ total +" procediments disponibles: <br />");
    			} else {
    				resultadoBeta.append("S'han intentat enviar  "+(correctos+incorrectos)+" procediments : <br />");
    			}
    			resultadoBeta.append(" - "+correctos+" s'han enviat correctament <br />");
    			resultadoBeta.append(" - "+incorrectos+" han donat errors al enviar <br />");
    			resultadoBeta.append(" - "+noEnviable+" no s&oacute;n visibles i no s'han d'enviar  <br />");
    			resultadoBeta.append(" - "+noCumpleDatos+" s'haurien d'enviar per&ograve; falten dades  <br />");
    			    			
    			if (estadoProceso.getFechaFin() == null) {
    				resultadoBeta.append("Estat: "+Math.abs((correctos+incorrectos+noEnviable+noCumpleDatos)*100/total)+"%  <br />");
    			} else {
    				resultadoBeta.append("Finalitzat a les " + formato.format(estadoProceso.getFechaFin()) + "<br />");
    				siaJob.setFechaFin(estadoProceso.getFechaFin());
    				if (incorrectos == 0) {
    					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO);
    				} else {
    					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO_CON_ERRORES);
    				}
    			}
    			
    			// TODO PASAR A STRING + CLOB
    			//siaJob.setDescBreve(Hibernate.createClob(resultadoBeta.toString()));
    			//siaJob.setDescripcion(Hibernate.createClob(estadoProceso.getDetalle()));
    			siaJob.setDescBreve(resultadoBeta.toString());
    			siaJob.setDescripcion(estadoProceso.getDetalle());
    			
    			final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
    			siaPendienteDelegate.actualizarSiaJob(siaJob);
	        	
    		}
		} catch (Exception e) {
			log.error("Error actualizando el job.",e);					
		}
		
	}
	
	/**
	 * Revisa los procedimientos que podrian estar en SIA y les envia un cambio si es necesario.
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 */
	public void revisarProcedimientosPorTiempo(SiaJob siaJob)    {
		
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final ServicioDelegate servDelegate = DelegateUtil.getServicioDelegate();
		
		try {
			List<Long> 		listProcedimientos = procDelegate.getProcedimientosEstadoSIAAlterado();
			if (listProcedimientos != null) {
				for(Long idProcedimiento : listProcedimientos) {
					SiaUtils.marcarIndexacionPendiente(SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO, idProcedimiento, SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_EXISTE, null, null);
				}
			}
			
			
			List<Long>      listServicios = servDelegate.getServiciosEstadoSIAAlterado();
			if (listServicios != null) {
				for (Long idServicio : listServicios) {
					SiaUtils.marcarIndexacionPendienteServicio(SiaUtils.SIAPENDIENTE_TIPO_SERVICIO, idServicio, SiaUtils.SIAPENDIENTE_SERVICIO_EXISTE, null, null);
					
				}
			}
		} catch(Exception e) {
			log.error(e);	
			StringBuffer resultadoDescripcionBreve = new StringBuffer();
			resultadoDescripcionBreve.append(" Error obteniendo los procedimientos y servicios caducados. Mensaje:" + e.getMessage());
			//siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
			//siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcionBreve.toString()));
			siaJob.setDescBreve(resultadoDescripcionBreve.toString());
			siaJob.setDescripcion(resultadoDescripcionBreve.toString());
			try {
				siaPendienteDelegate.actualizarSiaJob(siaJob);
			} catch (DelegateException e2) {
				log.error(e2);	
			}
			return;
		}
		
	}
	
	/**
	 * Obtener SIA pendientes de enviar 
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	
	 * @throws DelegateException
	 */
	public void enviarPendientes(SiaJob siaJob)  {
		log.debug("Enviar los procedimientos SIA pendientes");

		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		
		// Recuperamos pendientes
		List<SiaPendiente> siaPendientes = null;
		 try {
			siaPendientes = siaPendienteDelegate.getSiaPendientesEnviar();
		 } catch (Exception exception) {
			 log.error("Error recuperando pendientes", exception);
			 throw new EJBException("Error recuperando pendientes", exception);
		 }
		
		// Inicializamos vble para establecer estado proceso
		EstadoProcesoSIA estadoProceso = new EstadoProcesoSIA();
		estadoProceso.setFechaInicio(new Date());
		estadoProceso.setTotal(-1); // No sabemos cuantos puede haber
		
		// Enviamos pendientes a SIA
		try  {
        		
			for(SiaPendiente siaPendiente : siaPendientes)
			{
	        		ResultadoSiaPendiente resultadoPendiente =  null;
	        		
	        		if (SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo())) {
		        		try 
		        		{
		        			resultadoPendiente = enviarPendienteTipoProcedimiento(estadoProceso, siaPendiente);	        			 
		        		} catch (Exception exception) {
		        			log.error("Error no controlado para elemento pendiente " + siaPendiente.getId(), exception);
		        			// Indicamos resultado elemento
		        			resultadoPendiente = new ResultadoSiaPendiente(false, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + ExceptionUtils.getStackTrace(exception));
		        			// Actualiza estado proceso
		        			actualizaEstadoProceso(null, new SiaResultado(SiaResultado.RESULTADO_ERROR, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + ExceptionUtils.getStackTrace(exception)), estadoProceso, "procedimiento");
		        		}
	        		} else if (SiaUtils.SIAPENDIENTE_TIPO_SERVICIO.equals(siaPendiente.getTipo())) {
		        		try 
		        		{
		        			resultadoPendiente = enviarPendienteTipoServicio(estadoProceso, siaPendiente);	        			 
		        		} catch (Exception exception) {
		        			log.error("Error no controlado para elemento pendiente " + siaPendiente.getId(), exception);
		        			// Indicamos resultado elemento
		        			resultadoPendiente = new ResultadoSiaPendiente(false, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + ExceptionUtils.getStackTrace(exception));
		        			// Actualiza estado proceso
		        			actualizaEstadoProceso(null, new SiaResultado(SiaResultado.RESULTADO_ERROR, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + ExceptionUtils.getStackTrace(exception)), estadoProceso, "servicio");
		        		}
	        		} else {
	        			resultadoPendiente = new ResultadoSiaPendiente(false, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": No se tiene conocimiento del Siapendiente.TIPO");	        			
	        		}
	    			
	    			// Actualiza informacion elemento pendiente y planifica reintento
	    			if (resultadoPendiente.isCorrecto()) {
						siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);			        					
					} else {
						int dias = calcularDias(siaPendiente);
						if (dias > SiaUtils.getTiempoReintento()){		    							
							siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
						}
						siaPendiente.setMensaje(resultadoPendiente.getMensaje());
						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);		    						
					}
	    			
	        	}
			
	        	// Actualiza informacion job
				estadoProceso.setFechaFin(new Date());
				guardarEstadoProcesoSIA(siaJob, estadoProceso);
				        	
        	
      	} catch (Exception e) {
      		log.error("Error no controlado: " + e.getMessage(), e);
      		throw new EJBException("Error no controlado: " + e.getMessage(), e);
						
      	}
	}

	/** 
	 * Enviar SIA de tipo de procedimiento.
	 * Antes había tipo Normativa y tipoUA pero se ha simplificado y sólo hay uno.
	 *  
	 * @param estadoProceso
	 * @param siaPendiente
	 * @return
	 * @throws Exception
	 */
	private ResultadoSiaPendiente enviarPendienteTipoProcedimiento(
			EstadoProcesoSIA estadoProceso, SiaPendiente siaPendiente)
			throws Exception {
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		ResultadoSiaPendiente resultadoPendiente;
		// Procedimiento borrado en Rolsac
		if (siaPendiente.getExiste().compareTo(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO) == 0) {
			
			// Envia a SIA el borrado
			SiaResultado siaResultado = borradoProcedimiento(siaPendiente.getIdElemento(), siaPendiente.getIdSia().toString(), siaPendiente.getSiaUA());
			
			// Actualizar estado proceso
			actualizaEstadoProceso(siaPendiente.getIdElemento(), siaResultado, estadoProceso, "procedimiento");
			
			// Indicamos resultado elemento
			if (siaResultado.isCorrecto()) {
				resultadoPendiente = new ResultadoSiaPendiente(true, null);
			} else {
				resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
			}
			
				        					
		} else {
			
				ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(siaPendiente.getIdElemento(), null);
				if (procedimiento == null) {
					resultadoPendiente = new ResultadoSiaPendiente(false, "Procedimiento nulo");				
				} else {
				
					SiaResultado siaResultado = null; 
					SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(procedimiento);
					
					// Verificamos si se puede enviar a SIA
					if (siaEnviableResultado.isNotificiarSIA()) {
						SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(procedimiento, siaEnviableResultado, true);
						if (siaCumpleDatos.isCumpleDatos()) {
							siaResultado = enviarProcedimiento(procedimiento);
						} else {
							siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_CUMPLE_DATOS, siaCumpleDatos.getRespuesta());
						}
					}  else {
		    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_ENVIABLE, siaEnviableResultado.getRespuesta());		
		    		}
					
					// Actualizar estado proceso
					actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso, "procedimiento");
					
					// Indicamos resultado elemento
					if (siaResultado.isCorrecto()) {
						resultadoPendiente = new ResultadoSiaPendiente(true, null);
					} else {
						resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
					}
				}
				        					
		}
		return resultadoPendiente;
	}
	
	
	/** 
	 * Enviar SIA de tipo de procedimiento.
	 * Antes había tipo Normativa y tipoUA pero se ha simplificado y sólo hay uno.
	 *  
	 * @param estadoProceso
	 * @param siaPendiente
	 * @return
	 * @throws Exception
	 */
	private ResultadoSiaPendiente enviarPendienteTipoServicio(
			EstadoProcesoSIA estadoProceso, SiaPendiente siaPendiente)
			throws Exception {
		
		final ServicioDelegate servicioDelegate = DelegateUtil.getServicioDelegate();
		
		ResultadoSiaPendiente resultadoPendiente;
		// Procedimiento borrado en Rolsac
		if (siaPendiente.getExiste().compareTo(SiaUtils.SIAPENDIENTE_SERVICIO_BORRADO) == 0) {
			
			// Envia a SIA el borrado
			SiaResultado siaResultado = borradoServicio(siaPendiente.getIdElemento(), siaPendiente.getIdSia().toString(), siaPendiente.getSiaUA());
			
			// Actualizar estado proceso
			actualizaEstadoProceso(siaPendiente.getIdElemento(), siaResultado, estadoProceso, "servicio");
			
			// Indicamos resultado elemento
			if (siaResultado.isCorrecto()) {
				resultadoPendiente = new ResultadoSiaPendiente(true, null);
			} else {
				resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
			}
			
				        					
		} else {
			
				Servicio servicio = servicioDelegate.obtenerServicioParaSolr(siaPendiente.getIdElemento(), null);
				if (servicio == null) {
					resultadoPendiente = new ResultadoSiaPendiente(false, "Servicio nulo");				
				} else {
				
					SiaResultado siaResultado = null; 
					SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(servicio);
					
					// Verificamos si se puede enviar a SIA
					if (siaEnviableResultado.isNotificiarSIA()) {
						SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(servicio, siaEnviableResultado, true);
						if (siaCumpleDatos.isCumpleDatos()) {
							siaResultado = enviarServicio(servicio);
						} else {
							siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_CUMPLE_DATOS, siaCumpleDatos.getRespuesta());
						}
					}  else {
		    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NO_ENVIABLE, siaEnviableResultado.getRespuesta());		
		    		}
					
					// Actualizar estado proceso
					actualizaEstadoProceso(servicio.getId(), siaResultado, estadoProceso, "servicio");
					
					// Indicamos resultado elemento
					if (siaResultado.isCorrecto()) {
						resultadoPendiente = new ResultadoSiaPendiente(true, null);
					} else {
						resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
					}
				}
				        					
		}
		return resultadoPendiente;
	}



	private SiaResultado borradoProcedimiento(Long idProc, String idSIA, SiaUA siaUA) throws Exception {
		SiaResultado resultado = null; 
		Sia sia = new Sia();
		sia.setIdSIA(idSIA);
		sia.setOperacion(SiaUtils.ESTADO_BAJA); 
		sia.setIdElemento(String.valueOf(idProc));
		if (siaUA != null) {
			sia.setUsuario(siaUA.getUsuario());
			sia.setPassword(siaUA.getContrasenya());
		}

		try {
			resultado = SiaWS.enviarSIA(sia, true, false);
		} catch(Exception exception) {
			log.error("Se ha producido un error enviando el dato a SIA de un borrado de procedimiento " + idProc, exception);
			throw new Exception("Se ha producido un error enviando el dato a SIA de un borrado de procedimiento " + idProc, exception);
		}
		return resultado;
	}
	
	private SiaResultado borradoServicio(Long idServ, String idSIA, SiaUA siaUA) throws Exception {
		SiaResultado resultado = null; 
		Sia sia = new Sia();
		sia.setIdSIA(idSIA);
		sia.setOperacion(SiaUtils.ESTADO_BAJA); 
		sia.setIdElemento(String.valueOf(idServ));
		if (siaUA != null) {
			sia.setUsuario(siaUA.getUsuario());
			sia.setPassword(siaUA.getContrasenya());
		}
		try {
			resultado = SiaWS.enviarSIA(sia, true, false);
		} catch(Exception exception) {
			log.error("Se ha producido un error enviando el dato a SIA de un borrado de servicio " + idServ, exception);
			throw new Exception("Se ha producido un error enviando el dato a SIA de un borrado de servicio " + idServ, exception);
		}
		return resultado;
	}



	/**
	 * Calcula los dias desde la fecha de alta hasta hoy
	 * 
	 * @param siaPendiente
	 * @return
	 */
	private int calcularDias(SiaPendiente siaPendiente) {
		int dias=(int) ((new Date().getTime()-siaPendiente.getFecAlta().getTime())/86400000);
		return dias;
	}

	
	

	

	
	
	/**
	 * Obtener SIA pendientes de enviar 
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	
	 * @throws DelegateException
	 */
	public void info(SiaJob siaJob) {
		log.debug("Obtiene la info de todos los procedimientos y extrae la información SIA.");

		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		
		int correctos = 0;
		int incorrectos = 0;
		try {
			List<Long> idProcedimientos = procDelegate.buscarIdsProcedimientos();
	    	for(Long idProcedimiento : idProcedimientos ) {
	    		
	    		//Para actualizar cada 50 cambios, el job con el estado.
	    		try {
		    		if ( (correctos + incorrectos ) % 50 == 0) {
		    			StringBuffer resultadoBeta = new StringBuffer();
		    			resultadoBeta.append("Se han revisado info de "+(correctos+incorrectos)+" datos de un total de "+idProcedimientos.size()+" procedimientos. De los cuales: <br />");
		    			resultadoBeta.append(" - "+correctos+" datos correctos  <br />");
		    			resultadoBeta.append(" - "+incorrectos+" datos incorrectos  <br />");
		    			resultadoBeta.append("Estado: "+Math.abs((correctos+incorrectos)*100/idProcedimientos.size())+"%  <br />");
		    			
		    			//siaJob.setDescBreve(Hibernate.createClob(resultadoBeta.toString()));
		    			//siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
		    			siaJob.setDescBreve(resultadoBeta.toString());
		    			siaJob.setDescripcion(resultadoDescripcion.toString());
		    			final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		    			siaPendienteDelegate.actualizarSiaJob(siaJob);
			        	
		    		}
	    		} catch (Exception e) {
					log.error("Error actualizando el jop.",e);					
	    		}
	    		
	    		//Obtenemos el procedimiento y vemos is es enviable a SIA.
	    		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento, null);
				log.debug("El procedimiento para obtener el info " + procedimiento);
	    		//TODO info SIA. Pendiente de ver si merece la pena seguir con este método.
	    		/*
	    		if (SiaUtils.isEnviableSia(procedimiento)) {
		    		try 
		    		{
		    			enviarProcedimiento(procDelegate, procedimiento,  null, resultadoDescripcion,  correctos,  incorrectos);						
					} catch (Exception e) {
						log.error("Error general enviando procedimiento ",e);
						incorrectos++;
						resultadoDescripcion.append("   -- Se ha enviado a SIA incorrectamente. <br />");
						resultadoDescripcion.append("   -- Mensaje:"+ExceptionUtils.getStackTrace(e)+" <br />");
					}
	    		
	    		}*/ 
			}
		} catch (Exception exception) {
			log.error(exception);
			incorrectos++;
			resultadoDescripcion.append("   -- Error grave obteniendo los procedimientos. <br />");
			resultadoDescripcion.append("   -- Mensaje:"+ExceptionUtils.getStackTrace(exception)+" <br />");
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		resultadoDescripcionBreve.append("Se han revisado info de "+(correctos+incorrectos)+" datos. De los cuales: <br />");
    	resultadoDescripcionBreve.append(" - "+correctos+" datos correctos  <br />");
    	resultadoDescripcionBreve.append(" - "+incorrectos+" datos incorrectos  <br />");
    	resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
    	siaJob.setFechaFin(new Date());
    	//siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
    	//siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
    	siaJob.setDescBreve(resultadoDescripcionBreve.toString());
		siaJob.setDescripcion(resultadoDescripcion.toString());
		
    	if (incorrectos == 0) {
			siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO);
		} else {
			siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO_CON_ERRORES);
		}
    	
    	try {
    		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
    		siaPendienteDelegate.actualizarSiaJob(siaJob);
    	} catch(Exception exception) {
    		log.debug("Error intentando actualizar el siajob. Mensaje:" +exception.getMessage() ,exception);
    		
    	}
	}
	
	 	/**
		 * Obtiene el objeto SIA a partir del procedimiento.
		 * 
		 * @param procedimiento
		 * @return
		 * @throws Exception
		 */
		private Sia obtenerSiaProcedimiento(ProcedimientoLocal procedimiento) throws Exception {
			final SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(procedimiento);
			final SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(procedimiento, siaEnviableResultado, true);
			return obtenerSiaProcedimiento(procedimiento, siaEnviableResultado, siaCumpleDatos);
		}
		
		/**
		 * Obtiene el objeto SIA a partir del procedimiento.
		 * 
		 * @param procedimiento
		 * @return
		 * @throws Exception
		 */
		private Sia obtenerSiaProcedimientoNoActivo(ProcedimientoLocal procedimiento) throws Exception {
			final SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableNoActivo(procedimiento);
			final SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(procedimiento, siaEnviableResultado, false);
			return obtenerSiaProcedimiento(procedimiento, siaEnviableResultado, siaCumpleDatos);
		}
		
		/**
		 * Nucleo encargado de obtener el objeto SIA a partir de los datos que se les pasa.
		 * @param procedimiento
		 * @param siaEnviableResultado
		 * @param siaCumpleDatos
		 * @return
		 * @throws Exception
		 */
		private Sia obtenerSiaProcedimiento(ProcedimientoLocal procedimiento, SiaEnviableResultado siaEnviableResultado, SiaCumpleDatos siaCumpleDatos) throws Exception {
			final Sia sia = new Sia();
			sia.setIdElemento(procedimiento.getId().toString());
			if (procedimiento.getCodigoSIA() != null) {
				sia.setIdSIA(procedimiento.getCodigoSIA().toString());
			}
			
			sia.setTitulo( siaCumpleDatos.getNombre());
			
			sia.setDescripcion( siaCumpleDatos.getResumen()); 
			
			
			sia.setIdCent(siaEnviableResultado.getIdCentro());
			sia.setIdDepartamento(siaCumpleDatos.getSiaUA().getUnidadAdministrativa().getCodigoDIR3());
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
			
			final List<Tramite> tramites = procedimiento.getTramites();
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
			
			sia.setEnlaceWeb(SiaUtils.getUrlProcedimiento()+procedimiento.getId().toString());
			
			sia.setEstado(procedimiento.getEstadoSIA());
			
			//Obtenemos el tipo de operacion a partir de validar el procedimiento.
			sia.setOperacion(siaEnviableResultado.getOperacion());
						
			//Se ha tenido que poner aqui (y se ha simplificado) pq se produce un error al enviar una modificacion.
			if (SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
				sia.setTipoTramite(SiaUtils.TRAMITE_PROC);			
			} else {
				sia.setTipoTramite(null);
			}
			
			sia.setUsuario(siaCumpleDatos.getSiaUA().getUsuario());
			sia.setPassword(siaCumpleDatos.getSiaUA().getContrasenya());
			sia.setTipoTramite("P");
			return sia;
		}
		
		/**
		 * Obtiene el objeto SIA como no activo a partir del servicio.
		 * 
		 * @param servicio
		 * @return
		 * @throws Exception
		*/
		private Sia obtenerSiaServicioNoActivo(Servicio servicio) throws Exception {
			final SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableNoActivo(servicio);
			final SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(servicio, siaEnviableResultado, false);
			return obtenerSiaServicio( servicio,  siaEnviableResultado,  siaCumpleDatos) ;
		}
		
		/**
		 * Obtiene el objeto SIA a partir del procedimiento.
		 * 
		 * @param servicio
		 * @return
		 * @throws Exception
		 */
		private Sia obtenerSiaServicio(Servicio servicio) throws Exception {
			final SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviable(servicio);
			final SiaCumpleDatos siaCumpleDatos = SiaUtils.cumpleDatos(servicio, siaEnviableResultado, true);
			return obtenerSiaServicio( servicio,  siaEnviableResultado,  siaCumpleDatos) ;
		}	
		
		/**
		 * Nucleo del obtener SIA a partir de servicio, SiaEnviableResultado y SiaCumpleDatos.
		 * 
		 * @param servicio
		 * @return
		 * @throws Exception
		 */
		private Sia obtenerSiaServicio(Servicio servicio, SiaEnviableResultado siaEnviableResultado, SiaCumpleDatos siaCumpleDatos) throws Exception {
			final Sia sia = new Sia();
			sia.setIdElemento(servicio.getId().toString());
			if (servicio.getCodigoSIA() != null) {
				sia.setIdSIA(servicio.getCodigoSIA().toString());
			}
			
			sia.setTitulo( siaCumpleDatos.getNombre());
			
			sia.setDescripcion( siaCumpleDatos.getResumen()); 
			
			
			sia.setIdCent(siaEnviableResultado.getIdCentro());
			sia.setIdDepartamento(siaCumpleDatos.getSiaUA().getUnidadAdministrativa().getCodigoDIR3());
			if (servicio.getOrganoInstructor().getTraduccion("es") != null && ((TraduccionUA)  servicio.getOrganoInstructor().getTraduccion("es")).getNombre() != null) {
				sia.setUaGest(((TraduccionUA)  servicio.getOrganoInstructor().getTraduccion("es")).getNombre());
			} else {
				sia.setUaGest(((TraduccionUA)  servicio.getOrganoInstructor().getTraduccion("ca")).getNombre());
			}
			
			String[] destinatarios = new String[servicio.getPublicosObjetivo().size()];
			Set<PublicoObjetivo> publicoObjs = servicio.getPublicosObjetivo();
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
			
			sia.setNormativas(servicio.getNormativas());
			
			final List<String> materias = new ArrayList<String>();
			
			if (servicio.getMaterias() != null){			
				for (Materia mat : servicio.getMaterias()) {
					if (mat.getCodigoSIA() != null) {
						if (!materias.contains(mat.getCodigoSIA().toString())) {
							materias.add(mat.getCodigoSIA().toString());
						}
					}
				}
				sia.setMaterias(materias.toArray(new String[materias.size()]));
			}

			sia.setTipologia(SiaUtils.getTipologiaTramitacion());
			
			sia.setEnlaceWeb(SiaUtils.getUrlServicio()+servicio.getId().toString());
			
			sia.setEstado(servicio.getEstadoSIA());
			
			//Obtenemos el tipo de operación a partir de validar el servicio.
			sia.setOperacion(siaEnviableResultado.getOperacion());
						
			//Se ha tenido que poner aquí (y se ha simplificado) pq se produce un error al enviar una modificación.
			if (SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
				sia.setTipoTramite(SiaUtils.TRAMITE_SERV);			
			} else {
				sia.setTipoTramite(null);
			}
			
			sia.setUsuario(siaCumpleDatos.getSiaUA().getUsuario());
			sia.setPassword(siaCumpleDatos.getSiaUA().getContrasenya());
			
			if (servicio.getTramiteUrl() != null && servicio.getTramiteUrl() != null) {
				sia.setNivAdm("4");
			} else {
				sia.setNivAdm("1");	
			}

			sia.setTipoTramite("S");
			return sia;
		}
		
}
