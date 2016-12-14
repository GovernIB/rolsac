package org.ibit.rol.sac.persistence.ejb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.ibit.rol.sac.persistence.delegate.TramiteDelegate;
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
	public void enviarTodos(SiaJob siaJob)   {
		log.debug("Enviar todos los procedimientos SIA ");
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		SiaResultado resultado = new SiaResultado();
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		
		int correctos = 0;
		int incorrectos = 0;
		try {
			List<Long> idProcedimientos = procDelegate.buscarIdsProcedimientos();
	    	for(Long idProcedimiento : idProcedimientos ) {
	    		try {
		    		if ( (correctos + incorrectos ) % 50 == 0) {
		    			StringBuffer resultadoBeta = new StringBuffer();
		    			resultadoBeta.append("Se han enviado "+(correctos+incorrectos)+" datos de un total de "+idProcedimientos.size()+" procedimientos. De los cuales: <br />");
		    			resultadoBeta.append(" - "+correctos+" datos correctos  <br />");
		    			resultadoBeta.append(" - "+incorrectos+" datos incorrectos  <br />");
		    			resultadoBeta.append("Estado: "+Math.abs((correctos+incorrectos)*100/idProcedimientos.size())+"%  <br />");
		    			
		    			siaJob.setDescBreve(Hibernate.createClob(resultadoBeta.toString()));
		    			siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
		    			actualizarJob(siaJob);
		    		}
	    		} catch (Exception e) {
					log.error("Error actualizando el jop.",e);					
				}
	    		
	    		try 
	    		{
					ProcedimientoLocal proc = procDelegate.obtenerProcedimientoParaSolr(idProcedimiento);
					resultadoDescripcion.append("   -- Vamos a indexar el procedimiento con id :"+idProcedimiento+" <br />");
					Sia  sia = obtenerSiaProc(proc);
					resultado = SiaWS.enviarSIA(sia); 
					
					if (resultado.isCorrecto()) {
						correctos++;
    					resultadoDescripcion.append("   ---- Se ha enviado a SIA correctamente. <br />");
    					resultadoDescripcion.append("   ---- CodigoSIA:"+resultado.getCodSIA()+". <br />");
    					resultadoDescripcion.append("   ---- EstadoSia:"+resultado.getCodSIA()+". <br />");
    					proc.setCodigoSIA(resultado.getCodSIA());
    					proc.setEstadoSIA(resultado.getEstadoSIA());
    					proc.setFechaSIA(new Date());				
    					procDelegate.actualizarProcedimiento(proc);	
					} else {
						incorrectos++;
						resultadoDescripcion.append("   -- Se ha enviado a SIA incorrectamente. <br />");
						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
					}	
				} catch (Exception e) {
					log.error("Error general enviando procedimiento ",e);
					incorrectos++;
					resultadoDescripcion.append("   -- Se ha enviado a SIA incorrectamente. <br />");
					resultadoDescripcion.append("   -- Mensaje:"+ExceptionUtils.getStackTrace(e)+" <br />");
				}
			}
		} catch (Exception exception) {
			log.error(exception);
			incorrectos++;
			resultadoDescripcion.append("   -- Error grave obteniendo los procedimientos. <br />");
			resultadoDescripcion.append("   -- Mensaje:"+ExceptionUtils.getStackTrace(exception)+" <br />");
		}
	
		
    	SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		resultadoDescripcionBreve.append("Se han enviado "+(correctos+incorrectos)+" datos. De los cuales: <br />");
    	resultadoDescripcionBreve.append(" - "+correctos+" datos correctos  <br />");
    	resultadoDescripcionBreve.append(" - "+incorrectos+" datos incorrectos  <br />");
    	resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
    	siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
		siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
		
    	
		if (resultado.getIncorrectos() == 0) {
			siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO);
		} else {
			siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES);
		}
    	
		actualizarJob(siaJob);
	}


	/** Genera la descripción 
	 * 
	 * @param formato
	 * @param cadenaFecha
	 * @param resultado
	 * @return descripción breve
	 */
	private StringBuffer obtenerDescripcion( SiaResultado resultado) {
		Date fecha = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		String cadenaFecha = formato.format(fecha);
		StringBuffer descripcionBreve = new StringBuffer();
		
		descripcionBreve.append("El proceso ha empezado a las " + cadenaFecha);
		descripcionBreve.append(" Se han enviado "+(resultado.getCorrectos() + resultado.getIncorrectos())+" datos <br />");
		descripcionBreve.append(resultado.getCorrectos() + " han sido correctos <br />");
		descripcionBreve.append(resultado.getIncorrectos() + " han sido incorrectos <br />");
		cadenaFecha = formato.format(fecha);
		descripcionBreve.append(" Finalizado hora "+ cadenaFecha);
		
		return descripcionBreve;
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
		Session session = null;
		
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		int correctos = 0;
		int incorrectos = 0;
        try 
        {
        		Calendar calendar = Calendar.getInstance();
        		resultadoDescripcion.append(" Vamos a comenzar a enviar pendientes. <br /> ");
        		resultadoDescripcionBreve.append(" Empezando a las "+formato.format(calendar.getTime())+"  <br />");
	        	
        		List<SiaPendiente> siaPendientes = siaPendienteDelegate.getSiaPendientesEnviar();
        		session = getSession();
        	
        		int i = 0;
        		SiaResultado resultado = new SiaResultado();
			
        		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        		resultadoDescripcion.append(" Hay "+siaPendientes.size()+" datos pendientes. <br /> ");
        		
	        	for(SiaPendiente siaPendiente : siaPendientes) 
	        	{
	        
	        		try 
	        		{
	        			if (i % 20 == 0) {
	        				session.flush();
	        			} 
	        			i++;
	        			if (SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo() )) {
	        				
	        				if (siaPendiente.getTipoAccion().compareTo(SiaUtils.SIAPENDIENTE_TIPO_ACCION_BORRADO) == 0) {
	        					
	        					resultadoDescripcion.append(" Vamos a enviar una baja de un procedimiento (id:"+siaPendiente.getIdElemento()+", idSIA:"+siaPendiente.getIdSia()+") borrado <br /> ");
		        				
	        					Sia sia = new Sia();
	        					sia.setIdSIA(siaPendiente.getIdSia().toString());
	        					sia.setOperacion(SiaUtils.ESTADO_BAJA); 
	        					resultado = SiaWS.enviarSIA(sia); 
	        					if (resultado.isCorrecto()) {
		    						siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
		    						correctos++;
		        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);	
		        					resultadoDescripcion.append("   -- Marcado como correcto <br />");
		    					} else {
		    						siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
		    						siaPendiente.setMensaje(resultado.getMensaje());
		    						incorrectos++;
		    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		    						resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
		    						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
		    					}
	        					
	        				} else {
	        					
	        					resultadoDescripcion.append(" Vamos a enviar una baja de un procedimiento (id:"+siaPendiente.getIdElemento()+", idSIA:"+siaPendiente.getIdSia()+") <br />");
		        				
	        					ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimiento(siaPendiente.getIdElemento());
	        					
	        					//GENERAR OBJETO SIA
	            				Sia  sia = obtenerSiaProc(procedimiento);
		    					
	            				try {
									resultado = SiaWS.enviarSIA(sia);
								} catch (Exception e) {
									log.error(e.getMessage());
								}
	            				
		    					if (resultado.isCorrecto()) {
		    						//Si es correcto, actualizamos el procedimiento
		    						procedimiento.setEstadoSIA(resultado.getEstadoSIA());
									procedimiento.setFechaSIA(new Date());
									procDelegate.actualizarProcedimiento(procedimiento);
									
									//Actualizamos el siaPendinete
									siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
		    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		    						
		    						correctos++;		
		    						
		    						resultadoDescripcion.append("   -- Marcado como correcto <br />");
		    					} else {
		    						//Actualizamos el siaPendinete
									siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
									siaPendiente.setMensaje(resultado.getMensaje());
		    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		        					
		        					incorrectos++;	
		        					resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
		    						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
		    					}
		    					
		    					
	        				}
	
	        				
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA.equals(siaPendiente.getTipo() )) {
	        				
	        				List <ProcedimientoLocal>listProc = procDelegate.listarProcedimientosUA(siaPendiente.getIdElemento());
	        				resultadoDescripcion.append(" Vamos a actualizar procedimientos asociados a una UA (idUA:"+siaPendiente.getIdElemento()+", idSIA:"+siaPendiente.getIdSia()+") <br />");
	        				
	        				boolean todosCorrectos = true;
	        				for (ProcedimientoLocal proc : listProc) {
	        					resultadoDescripcion.append("   -- Procedimiento asociado (idProc:"+proc.getId()+") <br />");
		        				
	        					//GENERAR OBJETO SIA
	        					Sia  sia = obtenerSiaProc(proc);
	        					
								resultado = obtenerResultadosEnvio (siaPendienteDelegate, 
																	resultado.getIncorrectos(),
																	siaPendiente, 
																	sia, 
																	siaJob);
							
								if (resultado.isCorrecto() ) {
		    						
									//Si es correcto, actualizamos el procedimiento
									proc.setEstadoSIA(resultado.getEstadoSIA());
		    						proc.setFechaSIA(new Date());
		    						procDelegate.actualizarProcedimiento(proc);
		    						
		    						resultadoDescripcion.append("   ---- Ejecutado correcto <br />");
			        				
		    					} else {
		    						
		    						todosCorrectos = false;
		    						resultadoDescripcion.append("   ---- Ejecutado incorrecto. Mensaje:"+resultado.getMensaje()+" <br />");
		    					}
	        					
							}
	        				
	        				if (todosCorrectos) {
	        					//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
	    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	    						
	    						correctos++;
	    						resultadoDescripcion.append("   -- Marcado como correcto <br />");
	    					} else {
	    						//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
								siaPendiente.setMensaje("Ha fallado indexando los procedimientos asociados a la UA");
	        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	        					
	        					incorrectos++;	
	        					resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
	    						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
	    					}
	        				
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_NORMATIVA.equals(siaPendiente.getTipo() )) {
	        				
	        				//OBTENER EL ID DE TODOS LAS PROCEDIMIENTOS AFECTADOS POR ESA NORMATIVA
	        				final List <ProcedimientoLocal>listProc = procDelegate.listarProcedimientosNormativa(siaPendiente.getIdElemento());
	        				boolean todosCorrectos = true;
	        				for (ProcedimientoLocal proc : listProc) {
	        					//GENERAR OBJETO SIA
	        					final Sia  sia = obtenerSiaProc(proc);
	        					resultado = obtenerResultadosEnvio(
										siaPendienteDelegate, resultado.getIncorrectos(),
										siaPendiente, sia, siaJob);
	        					
	        					if (resultado.isCorrecto() ) {
		    						
									//Si es correcto, actualizamos el procedimiento
									proc.setEstadoSIA(resultado.getEstadoSIA());
		    						proc.setFechaSIA(new Date());
									procDelegate.actualizarProcedimiento(proc);
									resultadoDescripcion.append("   ---- Ejecutado correcto <br />");
		    					} else {
		    						
		    						todosCorrectos = false;
		    						resultadoDescripcion.append("   ---- Ejecutado incorrecto. Mensaje:"+resultado.getMensaje()+" <br />");
		    					}
							}
	        				
	        				if (todosCorrectos) {
	        					//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
	    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	    						
	    						correctos++;		    						
	    						resultadoDescripcion.append("   -- Marcado como correcto <br />");
	    					} else {
	    						//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
								siaPendiente.setMensaje("Ha fallado indexando los procedimientos asociados a la normativa");
	        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	        					
	        					incorrectos++;		    	
	        					resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
	    						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
	    					}
	        			} 
        			
	        		} catch (Exception exception) {
	        			resultadoDescripcion.append("Ha fallado la indexación en pendiente con el mensaje." + ExceptionUtils.getStackTrace(exception)+"<br />");
	        			siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
						siaPendiente.setMensaje("Ha fallado indexando, error no controlado. Mensaje:"+ ExceptionUtils.getStackTrace(exception));
    					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
    					incorrectos++;
	        		}
	        	}        	
	        	
	        	if (incorrectos > 0) {
					siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES);
				} else {
					siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO);
				}
			 
	        	resultadoDescripcionBreve.append("Se han enviado "+(correctos+incorrectos)+" datos. De los cuales: <br />");
	        	resultadoDescripcionBreve.append(" - "+correctos+" datos correctos  <br />");
	        	resultadoDescripcionBreve.append(" - "+incorrectos+" datos incorrectos  <br />");
	        	resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
	        	siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
    			siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
    			
	        	actualizarJob(siaJob);
	        	
	        	session.flush();
        	
      	} catch (Exception e) {
			log.error(e);			
		}finally {
            if (session != null) {close(session);}
        }
        
	}



	private SiaResultado obtenerResultadosEnvio(
			final SiaPendienteProcesoDelegate siaPendienteDelegate,
			int erroneos, SiaPendiente siaPendiente, Sia sia, SiaJob siaJob)
			throws Exception {
		SiaResultado resultado = SiaWS.enviarSIA(sia);
		
		if (resultado.isCorrecto()) {
			siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
			siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);	
		} else {
			erroneos++;
			siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
			siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		}
		
		if (siaJob != null) {
			siaJob.setDescBreve(Hibernate.createClob(obtenerDescripcion(resultado).toString()));
			siaJob.setDescripcion(Hibernate.createClob(obtenerDescripcion(resultado).toString() 
					+ (resultado.getMensaje()!=null ? resultado.getMensaje() : "")));
		}
		return resultado;
	}



	private Sia obtenerSiaProc(ProcedimientoLocal procedimiento) throws DelegateException {
		Sia sia = new Sia();
		
		sia.setIdProc(procedimiento.getId().toString());
		TraduccionProcedimientoLocal trad = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		if(trad != null){			
			sia.setTitulo(trad.getNombre());
			sia.setDescripcion(trad.getResumen());
		}
		
		List<UnidadAdministrativa> predecesores = procedimiento.getUnidadAdministrativa().getPredecesores();
		for(UnidadAdministrativa predecesor : predecesores) {
			if (predecesor.getPadre() != null && predecesor.getCodigoDIR3() != null) {
				sia.setIdCent(predecesor.getCodigoDIR3());
				break;
			}
		}
		
		sia.setUaGest(procedimiento.getUnidadAdministrativa().getNombre());
	
		String[] destinatarios = new String[procedimiento.getPublicosObjetivo().size()];
		Set<PublicoObjetivo> publicoObjs = procedimiento.getPublicosObjetivo();
		int i=0;
		for (PublicoObjetivo pObj : publicoObjs) {
			destinatarios[i]= pObj.getId().toString();
		}
		
		sia.setIdDest(destinatarios);
		
		List<Tramite> tramites = procedimiento.getTramites();
		Integer nivelAdministrativo = 1;
		for (Tramite tramite : tramites) {
			if (tramite.getFase() == 1)  {
				
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
		
		String[] materias = new String[procedimiento.getMaterias().size()];
		i = 0;
		for (Materia mat : procedimiento.getMaterias()) {
			materias[i]  = mat.getId().toString();
			i++;

		}
		sia.setMaterias(materias);

		if (procedimiento.getIndicador() == null) {
			sia.setFiVia(SiaUtils.NO);
		} else {
			sia.setFiVia(procedimiento.getIndicador().equals("1")? SiaUtils.SI : SiaUtils.NO);
		}
		sia.setTipologia(SiaUtils.getTipologiaTramitacion());
		
		String tipoTramite = SiaUtils.TRAMITE_SERV;
		
		if (SiaUtils.getTipoActuacion() == SiaUtils.TIPO_TRAMITE_PROC){
			tipoTramite = SiaUtils.TRAMITE_PROC;
		}
		sia.setTipoTramite(tipoTramite);
		
		sia.setEnlaceWeb(SiaUtils.getUrl()+procedimiento.getId().toString());
		
		sia.setEstado(procedimiento.getEstadoSIA());
		
		if (SiaUtils.validaProcedimientoSIA(procedimiento) ) {
			
			//ALTA / MODIFICACION / REACTIVACION
			if (sia.getIdSIA() == null || sia.getIdSIA().isEmpty()) {
				sia.setOperacion(SiaUtils.ESTADO_ALTA);
			}else if (sia.getEstado().equals(SiaUtils.ESTADO_ALTA)) {
				sia.setOperacion(SiaUtils.ESTADO_MODIFICACION); 
			}else if (sia.getEstado().equals(SiaUtils.ESTADO_BAJA)) { 
				sia.setOperacion(SiaUtils.ESTADO_REACTIVACION); 
			}
			
						
		} else if (procedimiento.getCodigoSIA() != null && !procedimiento.getCodigoSIA().isEmpty() 
				&& sia.getEstado() != null && !sia.getEstado().equals(SiaUtils.ESTADO_BAJA)){
			
			sia.setOperacion(SiaUtils.ESTADO_BAJA); 

		}
		
		return sia;
	}
	

	/**
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * @ejb.transaction type="RequiresNew"
   	 *
   	 * Actualiza el job si está activo
     * @param actualizarJob
     * @param siaJob
     */
    public void actualizarJob(SiaJob siaJob) {
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
	

}
