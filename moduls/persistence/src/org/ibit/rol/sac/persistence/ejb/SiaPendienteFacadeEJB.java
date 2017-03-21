package org.ibit.rol.sac.persistence.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.EstadoProcesoSIA;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.ResultadoSiaPendiente;
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
	public void ejbCreate() throws CreateException {

		super.ejbCreate();
	}
	
	
	/**
	 * Obtener SIA pendientes de enviar 
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 */
	public void enviarTodos(SiaJob siaJob)  {
		log.debug("Enviar todos los procedimientos SIA ");
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		
		// Buscamos procedimientos a revisar
		List<Long> idProcedimientos = null;
		try {
			idProcedimientos = procDelegate.buscarIdsProcedimientos();
		} catch (DelegateException e1) {
			log.error("Error recuperando lista de procedimientos", e1);
			throw new EJBException("Error recuperando lista de procedimientos", e1);
		}
		
		// Inicializamos vble para establecer estado proceso
		EstadoProcesoSIA estadoProceso = new EstadoProcesoSIA();
		estadoProceso.setFechaInicio(new Date());
		estadoProceso.setTotal(idProcedimientos.size());
	
		// Recorremos procedimientos y enviamos a SIA
		try {
			
	    	for(Long idProcedimiento : idProcedimientos ) {
	    		
	    		//Obtenemos el procedimiento y vemos is es enviable a SIA.
	    		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento);
	    		SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
	    		
	    		SiaResultado siaResultado = null;
				if (siaEnviableResultado.isNotificiarSIA()) {
		    		try {
		    			// Enviamos a SIA
		    			enviarProcedimiento(procedimiento);		    						    		
					} catch (Exception e) {
						log.error("Error enviando procedimiento " + procedimiento.getId(), e);
						siaResultado = new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(e));												
					}	    	
	    		} else {
	    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NULO, siaEnviableResultado.getRespuesta());		
	    		}
				
				// Actualizamos estado proceso
				actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso);
				
				// Guarda estado proceso
	    		guardarEstadoProcesoSIA(siaJob, estadoProceso);
	    		
			}
	    	
		} catch (Exception exception) {
			log.error("Error no controlado enviando procedimientos: " + exception.getMessage(), exception);		
			// Actualizamos estado proceso (error general)
			actualizaEstadoProceso(null,  new SiaResultado(SiaResultado.RESULTADO_ERROR, ExceptionUtils.getStackTrace(exception)), estadoProceso);			
		}
	
		
		// Finalizamos proceso y guardamos
		estadoProceso.setFechaFin(new Date());		
		guardarEstadoProcesoSIA(siaJob, estadoProceso);
		    	
	}


	private SiaResultado enviarProcedimiento(
			ProcedimientoLocal proc) throws Exception {
		
		// Enviamos a SIA
		SiaResultado resultado = null;
    	try {
			// Obtenemos info para enviar a SIA
	    	final Sia sia = obtenerSiaProc(proc);
			// Enviamos a SIA
			resultado = SiaWS.enviarSIA(sia, false);
    	} catch (Exception ex) {
    		log.error("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    		throw new Exception("Error enviando a SIA el procedimiento " + proc.getId() + ": " + ex.getMessage(), ex);
    	}
		
    	// Actualizamos procedimiento
    	final SiaPendienteProcesoDelegate siaDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		siaDelegate.actualizarProcedimiento(proc, resultado);
		
		return resultado;
	}

	
	private void actualizaEstadoProceso(Long procId, SiaResultado siaResultado,
			EstadoProcesoSIA estadoProceso) {
		
		if (siaResultado.isNulo()) {
			estadoProceso.addNulo();
			estadoProceso.addLineaDetalle("   ---- Procedimiento: " + procId + " no cumple requisitos para enviar a SIA: " +  siaResultado.getMensaje() + "<br />");
		} else if (siaResultado.isCorrecto()) {
			estadoProceso.addCorrecto();
			estadoProceso.addLineaDetalle("   ---- Procedimiento: " + procId + " <br />");
			estadoProceso.addLineaDetalle("   ---- Se ha enviado a SIA correctamente. <br />");
			estadoProceso.addLineaDetalle("   ---- CodigoSIA:"+siaResultado.getCodSIA()+". <br />");
			estadoProceso.addLineaDetalle("   ---- EstadoSia:"+siaResultado.getEstadoSIA()+". <br />");		    						    				
		} else {
			if (procId == null) {
				estadoProceso.addIncorrecto();
				estadoProceso.addLineaDetalle("   ---- Error no controlado. <br />");
				estadoProceso.addLineaDetalle("   ---- Mensaje:"+siaResultado.getMensaje()+" <br />");
			} else {
				estadoProceso.addIncorrecto();
				estadoProceso.addLineaDetalle("   ---- Procedimiento: " + procId + " <br />");
				estadoProceso.addLineaDetalle("   ---- Se ha enviado a SIA incorrectamente. <br />");
				estadoProceso.addLineaDetalle("   ---- Mensaje:"+siaResultado.getMensaje()+" <br />");		    						
			}
		}
		
	}


	private void guardarEstadoProcesoSIA(SiaJob siaJob,
			EstadoProcesoSIA estadoProceso) {
		
		int correctos = estadoProceso.getCorrectos();
		int incorrectos = estadoProceso.getIncorrectos();
		int nulos = estadoProceso.getNulos();
		int total = estadoProceso.getTotal();
		
		
		
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		
		try {
			
			// Si no esta finalizado, actualizamos cada 50
			if ( estadoProceso.getFechaFin() != null ||  (correctos + incorrectos + nulos ) % 50 == 0) {
    			StringBuffer resultadoBeta = new StringBuffer();
    			resultadoBeta.append("Iniciado a las " + formato.format(estadoProceso.getFechaInicio()) + "<br />");
    			if (total != -1) {
    				resultadoBeta.append("Se han enviado "+(correctos+incorrectos)+" datos de un total de "+ total +" procedimientos. De los cuales: <br />");
    			} else {
    				resultadoBeta.append("Se han enviado "+(correctos+incorrectos)+" datos. De los cuales: <br />");
    			}
    			resultadoBeta.append(" - "+correctos+" datos correctos  <br />");
    			resultadoBeta.append(" - "+incorrectos+" datos incorrectos  <br />");
    			resultadoBeta.append(" - "+nulos+" datos no enviables a SIA  <br />");
    			    			
    			if (estadoProceso.getFechaFin() == null) {
    				resultadoBeta.append("Estado: "+Math.abs((correctos+incorrectos+nulos)*100/total)+"%  <br />");
    			} else {
    				resultadoBeta.append("Finalizado a las " + formato.format(estadoProceso.getFechaFin()) + "<br />");
    				siaJob.setFechaFin(estadoProceso.getFechaFin());
    				if (incorrectos == 0) {
    					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO);
    				} else {
    					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO_CON_ERRORES);
    				}
    			}
    			
    			// TODO PASAR A STRING + CLOB
    			siaJob.setDescBreve(Hibernate.createClob(resultadoBeta.toString()));
    			siaJob.setDescripcion(Hibernate.createClob(estadoProceso.getDetalle()));
    			
    			final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
    			siaPendienteDelegate.actualizarSiaJob(siaJob);
	        	
    		}
		} catch (Exception e) {
			log.error("Error actualizando el job.",e);					
		}
		
	}
	
	/**
	 * Revisa los procedimientos que podrían estar en SIA y les envía un cambio si es necesario.
	 * @throws Exception 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
	 */
	public void revisarProcedimientosPorTiempo(SiaJob siaJob)    {
		
		// TODO NO IMPLEMENTADO. QUITAR??
		
		/*
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		List<Long> listProcedimientos = null;
		int correctos = 0;
		int incorrectos = 0;
		int nulos = 0;
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		
		try {
			listProcedimientos = procDelegate.getProcedimientosEstadoSIAAlterado();
		} catch (DelegateException e) {
			log.error(e);	
			resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
			resultadoDescripcionBreve.append(" Error obteniendo los procedimientos");
			siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
			siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcionBreve.toString()));
			try {
				siaPendienteDelegate.actualizarSiaJob(siaJob);
			} catch (DelegateException e2) {
				log.error(e2);	
			}
			return;
		}
		resultadoDescripcionBreve.append(" Empezando a las "+formato.format(calendar.getTime())+"  <br />");
    	
		//Recorremos todas las ids.
		for(Long idProcedimiento : listProcedimientos) {
			try
			{
				
				ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento);
				SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
				if (siaEnviableResultado.isNotificiarSIA()) {
					
					resultadoDescripcion.append(" Se va a enviar el procedimiento hacia SIA como un "+siaEnviableResultado.getOperacion()+" <br /> ");
					SiaResultado siaResultado = enviarProcedimiento(procDelegate, procedimiento,  null, resultadoDescripcion,  correctos,  incorrectos);
					if (siaResultado.isCorrecto()) {
						resultadoDescripcion.append("   -- Marcado como correcto (no cumplia requisitos, no se ha enviado a SIA) <br />");
						correctos++;		
					} else {
						incorrectos++;
						resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
						resultadoDescripcion.append("   -- Mensaje:"+siaResultado.getMensaje()+" <br />");
					}
					
				} else {
					//No se debe hacer nada.
					nulos++;
					resultadoDescripcion.append("   -- Marcado como nulo (no enviable) "+siaEnviableResultado.getRespuesta()+". <br />");
				}
				
				
			} catch (Exception exception) {
				resultadoDescripcion.append("Ha fallado la indexación en pendiente con el mensaje." + ExceptionUtils.getStackTrace(exception)+"<br />");
    			incorrectos++;
			}
		}
		
		if (incorrectos > 0) {
			siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO_CON_ERRORES);
		} else {
			siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO);
		}
	 
    	resultadoDescripcionBreve.append("Se han enviado "+(correctos+incorrectos)+" datos. De los cuales: <br />");
    	resultadoDescripcionBreve.append(" - "+correctos+" datos correctos  <br />");
    	resultadoDescripcionBreve.append(" - "+incorrectos+" datos incorrectos  <br />");
    	resultadoDescripcionBreve.append(" - "+nulos+" datos nulos  <br />");
    	resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
    	siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
		siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
    	try {
			siaPendienteDelegate.actualizarSiaJob(siaJob);
		} catch (DelegateException e) {
			log.error(e);	
		}
		*/
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
		log.debug("Enviar todos los procedimientos SIA pendientes");

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
	        		
	        		try 
	        		{
	        			if (SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo() )) {
	        				resultadoPendiente = enviarPendienteTipoProcedimiento(estadoProceso, siaPendiente);	        				
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA.equals(siaPendiente.getTipo() )) {
	        				resultadoPendiente = enviarPendienteTipoUA(estadoProceso, siaPendiente);        						        			
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_NORMATIVA.equals(siaPendiente.getTipo() )) {
	        				resultadoPendiente = enviarPendienteTipoNormativa(estadoProceso, siaPendiente);	        				
	        			} 
	        		} catch (Exception exception) {
	        			log.error("Error no controlado para elemento pendiente " + siaPendiente.getId(), exception);
	        			// Indicamos resultado elemento
	        			resultadoPendiente = new ResultadoSiaPendiente(false, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + ExceptionUtils.getStackTrace(exception));
	        			// Actualiza estado proceso
	        			actualizaEstadoProceso(null, new SiaResultado(SiaResultado.RESULTADO_ERROR, "Error no controlado para elemento pendiente " + siaPendiente.getId() + ": " + exception.getMessage()), estadoProceso);
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


	private ResultadoSiaPendiente enviarPendienteTipoNormativa(
			EstadoProcesoSIA estadoProceso, SiaPendiente siaPendiente)
			throws Exception {
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		
		ResultadoSiaPendiente resultadoPendiente;
		// Obtiene procs asociados a normativa
		final List <ProcedimientoLocal>listProcedimientos = procDelegate.listarProcedimientosNormativa(siaPendiente.getIdElemento());
		
		boolean todosCorrectos = true;
		String idsNoCorrectos = "";
		for (ProcedimientoLocal procedimiento : listProcedimientos) {
			
			SiaResultado siaResultado = null;
			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
			
			// Verifica si envia a SIA
			if (siaEnviableResultado.isNotificiarSIA()) {
				siaResultado = enviarProcedimiento(procedimiento);        						
				if (!siaResultado.isCorrecto()) {
					todosCorrectos = false;
					idsNoCorrectos += " " + procedimiento.getId();
				}
			}  else {
    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NULO, siaEnviableResultado.getRespuesta());		
    		}
			
			// Actualizar estado proceso
			actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso);
			
		}
		
		// Indicamos resultado elemento
		if (todosCorrectos) {
			resultadoPendiente = new ResultadoSiaPendiente(true, null);
		} else {
			resultadoPendiente = new ResultadoSiaPendiente(false, "Procedimientos con error: " + idsNoCorrectos);
		}
		return resultadoPendiente;
	}


	private ResultadoSiaPendiente enviarPendienteTipoUA(
			EstadoProcesoSIA estadoProceso, SiaPendiente siaPendiente)
			throws Exception {
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		
		ResultadoSiaPendiente resultadoPendiente;
		// Obtiene lista procs asociados a la UA
		List <ProcedimientoLocal>listProcedimientos = procDelegate.listarProcedimientosUA(siaPendiente.getIdElemento());
		
		boolean todosCorrectos = true;
		String idsNoCorrectos = "";
		for (ProcedimientoLocal procedimiento : listProcedimientos) {
			
			SiaResultado siaResultado = null;
			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
			
			// Verifica si envia a SIA
			if (siaEnviableResultado.isNotificiarSIA()) {
		    	siaResultado = enviarProcedimiento(procedimiento);	        	
				if (!siaResultado.isCorrecto()) {
					todosCorrectos = false;
					idsNoCorrectos += " " + procedimiento.getId();
				}
			} else {
    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NULO, siaEnviableResultado.getRespuesta());		
    		}
			
			// Actualizar estado proceso
			actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso);
			
		}
		
		// Indicamos resultado elemento
		if (todosCorrectos) {
			resultadoPendiente = new ResultadoSiaPendiente(true, null);
		} else {
			resultadoPendiente = new ResultadoSiaPendiente(false, "Procedimientos con error: " + idsNoCorrectos);
		}
		return resultadoPendiente;
	}


	private ResultadoSiaPendiente enviarPendienteTipoProcedimiento(
			EstadoProcesoSIA estadoProceso, SiaPendiente siaPendiente)
			throws Exception {
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		
		ResultadoSiaPendiente resultadoPendiente;
		// Procedimiento borrado en Rolsac
		if (siaPendiente.getExiste().compareTo(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO) == 0) {
			
			// Envia a SIA el borrado
			SiaResultado siaResultado = borradoProcedimiento(siaPendiente.getIdElemento(), siaPendiente.getIdSia().toString());
			
			// Actualizar estado proceso
			actualizaEstadoProceso(siaPendiente.getIdElemento(), siaResultado, estadoProceso);
			
			// Indicamos resultado elemento
			if (siaResultado.isCorrecto()) {
				resultadoPendiente = new ResultadoSiaPendiente(true, null);
			} else {
				resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
			}
			
				        					
		} else {
			
			ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(siaPendiente.getIdElemento());
			
			SiaResultado siaResultado = null;
			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
			
			// Verificamos si se puede enviar a SIA
			if (siaEnviableResultado.isNotificiarSIA()) {
				siaResultado = enviarProcedimiento(procedimiento);	        						
			}  else {
    			siaResultado = new SiaResultado(SiaResultado.RESULTADO_NULO, siaEnviableResultado.getRespuesta());		
    		}
			
			// Actualizar estado proceso
			actualizaEstadoProceso(procedimiento.getId(), siaResultado, estadoProceso);
			
			// Indicamos resultado elemento
			if (siaResultado.isCorrecto()) {
				resultadoPendiente = new ResultadoSiaPendiente(true, null);
			} else {
				resultadoPendiente = new ResultadoSiaPendiente(false, siaResultado.getMensaje());
			}
				        					
		}
		return resultadoPendiente;
	}



	private SiaResultado borradoProcedimiento(Long idProc, String idSIA) throws Exception {
		SiaResultado resultado = null; 
		Sia sia = new Sia();
		sia.setIdSIA(idSIA);
		sia.setOperacion(SiaUtils.ESTADO_BAJA); 
		sia.setIdProc(String.valueOf(idProc));
		try {
			resultado = SiaWS.enviarSIA(sia, true);
		} catch(Exception exception) {
			log.error("Se ha producido un error enviando el dato a SIA de un borrado de procedimiento " + idProc, exception);
			throw new Exception("Se ha producido un error enviando el dato a SIA de un borrado de procedimiento " + idProc, exception);
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
		    			
		    			siaJob.setDescBreve(Hibernate.createClob(resultadoBeta.toString()));
		    			siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
		    			final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		    			siaPendienteDelegate.actualizarSiaJob(siaJob);
			        	
		    		}
	    		} catch (Exception e) {
					log.error("Error actualizando el jop.",e);					
	    		}
	    		
	    		//Obtenemos el procedimiento y vemos is es enviable a SIA.
	    		ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento);
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
    	siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
		siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
		
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
		private Sia obtenerSiaProc(ProcedimientoLocal procedimiento) throws Exception {
			Sia sia = new Sia();
			SiaEnviableResultado siaEnviableResultado = SiaUtils.isEnviableSia(procedimiento);
			
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
			
			sia.setIdCent(siaEnviableResultado.getIdCentro());
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
			sia.setOperacion(siaEnviableResultado.getOperacion());
						
			//Se ha tenido que poner aquí (y se ha simplificado) pq se produce un error al enviar una modificación.
			if (SiaUtils.ESTADO_ALTA.equals(sia.getOperacion())) {
				sia.setTipoTramite(SiaUtils.TRAMITE_PROC);			
			} else {
				sia.setTipoTramite(null);
			}
			
			return sia;
		}
	    
		
		
}
