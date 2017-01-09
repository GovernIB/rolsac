package org.ibit.rol.sac.persistence.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;

import net.sf.hibernate.Hibernate;

import org.apache.commons.lang.exception.ExceptionUtils;
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
	public void enviarTodos(SiaJob siaJob) throws Exception   {
		log.debug("Enviar todos los procedimientos SIA ");
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		
		int correctos = 0;
		int incorrectos = 0; int nulos = 0;
		try {
			List<Long> idProcedimientos = procDelegate.buscarIdsProcedimientos();
	    	for(Long idProcedimiento : idProcedimientos ) {
	    		
	    		//Para actualizar cada 50 cambios, el job con el estado.
	    		try {
		    		if ( (correctos + incorrectos + nulos ) % 50 == 0) {
		    			StringBuffer resultadoBeta = new StringBuffer();
		    			resultadoBeta.append("Se han enviado "+(correctos+incorrectos)+" datos de un total de "+idProcedimientos.size()+" procedimientos. De los cuales: <br />");
		    			resultadoBeta.append(" - "+correctos+" datos correctos  <br />");
		    			resultadoBeta.append(" - "+incorrectos+" datos incorrectos  <br />");
		    			resultadoBeta.append(" - "+nulos+" datos no enviables a SIA  <br />");
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
				if (SiaUtils.isEnviableSia(procedimiento)) {
		    		try 
		    		{
		    			SiaResultado siaResultado = enviarProcedimiento(procDelegate, procedimiento,  null, resultadoDescripcion,  correctos,  incorrectos);
		    			if (siaResultado.isCorrecto()) {
		    				correctos++;
		    			} else {
		    				incorrectos++;
		    			}
					} catch (Exception e) {
						log.error("Error general enviando procedimiento ",e);
						incorrectos++;
						resultadoDescripcion.append("   -- Se ha enviado a SIA incorrectamente. <br />");
						resultadoDescripcion.append("   -- Mensaje:"+ExceptionUtils.getStackTrace(e)+" <br />");
					}
	    		
	    		} else { nulos ++; } 
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
    	resultadoDescripcionBreve.append(" - "+nulos+" datos no enviables a SIA  <br />");
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
    		throw new Exception("Error actualizando el siajob");
    	}
	}


	/**
	 * Enviar procedimiento hacia SIA.
	 * @param procDelegate
	 * @param proc
	 * @param resultadoDescripcion
	 * @param correctos
	 * @param incorrectos
	 * @throws Exception
	 */
	private SiaResultado enviarProcedimiento(ProcedimientoDelegate procDelegate, 
									 ProcedimientoLocal proc,  
									 SiaPendiente siaPendiente,
										 StringBuffer resultadoDescripcion,  Integer correctos, Integer incorrectos) throws Exception {
		
		resultadoDescripcion.append("   -- Vamos a indexar el procedimiento con id :"+proc.getId()+" <br />");
		final Sia  sia = obtenerSiaProc(proc);
		final SiaResultado resultado = SiaWS.enviarSIA(sia); 
		
		if (resultado.isCorrecto()) {
			
			if (correctos != null) {
				correctos++;
			}
			resultadoDescripcion.append("   ---- Se ha enviado a SIA correctamente. <br />");
			resultadoDescripcion.append("   ---- CodigoSIA:"+resultado.getCodSIA()+". <br />");
			resultadoDescripcion.append("   ---- EstadoSia:"+resultado.getCodSIA()+". <br />");
			
			//Actualizamos el procedimiento
			proc.setCodigoSIA(resultado.getCodSIA());
			proc.setEstadoSIA(resultado.getEstadoSIA());
			proc.setFechaSIA(new Date());				
			procDelegate.actualizarProcedimiento(proc);	
			
			//Actualizamos el siaPendinete
			if (siaPendiente != null ) {
				SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate(); 
				siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
				siaPendiente.setFecIdx(new Date());
				siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
			}
			
		} else {
			if (incorrectos != null) {
				incorrectos++;
			}
			//Actualizamos el resultado descripcion
			resultadoDescripcion.append("   -- Se ha enviado a SIA incorrectamente. <br />");
			resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
			
			//Actualizamos el siaPendinete
			if (siaPendiente != null ) {
				SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate(); 
				siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
				siaPendiente.setMensaje(resultado.getMensaje());
				siaPendiente.setFecIdx(new Date());
				siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
			}
		}
		
		return resultado;
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
		
		StringBuffer resultadoDescripcion = new StringBuffer();
		StringBuffer resultadoDescripcionBreve = new StringBuffer();
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		Integer correctos = 0;
		Integer incorrectos = 0;
        try 
        {
        		Calendar calendar = Calendar.getInstance();
        		resultadoDescripcion.append(" Vamos a comenzar a enviar pendientes. <br /> ");
        		resultadoDescripcionBreve.append(" Empezando a las "+formato.format(calendar.getTime())+"  <br />");
	        	
        		List<SiaPendiente> siaPendientes = siaPendienteDelegate.getSiaPendientesEnviar();
        		
        		
        		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        		resultadoDescripcion.append(" Hay "+siaPendientes.size()+" datos pendientes. <br /> ");
        		
	        	for(SiaPendiente siaPendiente : siaPendientes) 
	        	{
	        
	        		SiaResultado resultado = new SiaResultado();
	    			try 
	        		{
	        			
	        			if (SiaUtils.SIAPENDIENTE_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo() )) {
	        				
	        				if (siaPendiente.getExiste().compareTo(SiaUtils.SIAPENDIENTE_PROCEDIMIENTO_BORRADO) == 0) {
	        					
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
	        					
	        					resultadoDescripcion.append(" Vamos a enviar info de un procedimiento (id:"+siaPendiente.getIdElemento()+", idSIA:"+siaPendiente.getIdSia()+") <br />");
		        				
	        					ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimientoParaSolr(siaPendiente.getIdElemento());
	        					
	        					boolean esEnviable = SiaUtils.isEnviableSia(procedimiento);
	        					if (esEnviable || (!esEnviable && procedimiento.getEstadoSIA() != null && SiaUtils.ESTADO_ALTA.equals(procedimiento.getEstadoSIA()))) {
	        				    	
	        						SiaResultado siaResultado = enviarProcedimiento(procDelegate, procedimiento,  siaPendiente, resultadoDescripcion,  correctos,  incorrectos);
	        						if (siaResultado.isCorrecto()) {
	        							correctos++;
	        						} else {
	        							incorrectos++;
	        						}
	        					} else {
	        						//Actualizamos el siaPendinete
									siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
									siaPendiente.setMensaje("No se ha enviado hacia SIA, no cumplía requisitos.");
									siaPendiente.setFecIdx(new Date());
		    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		    						resultadoDescripcion.append("   -- Marcado como correcto (no cumplia requisitos, no se ha enviado a SIA) <br />");
		    						correctos++;		
	        					}
		    					
		    					
	        				}
	        				
	        				
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA.equals(siaPendiente.getTipo() )) {
	        				
	        				List <ProcedimientoLocal>listProcedimientos = procDelegate.listarProcedimientosUA(siaPendiente.getIdElemento());
	        				resultadoDescripcion.append(" Vamos a actualizar procedimientos asociados a una UA (idUA:"+siaPendiente.getIdElemento()+", idSIA:"+siaPendiente.getIdSia()+") <br />");
	        				
	        				boolean todosCorrectos = true;
	        				for (ProcedimientoLocal procedimiento : listProcedimientos) {
	        					resultadoDescripcion.append("   -- Procedimiento asociado (idProc:"+procedimiento.getId()+") <br />");
		        				
	        					boolean esEnviable = SiaUtils.isEnviableSia(procedimiento);
	        					if (esEnviable || (!esEnviable && procedimiento.getEstadoSIA() != null && SiaUtils.ESTADO_ALTA.equals(procedimiento.getEstadoSIA()))) {
	        				    	
	        						SiaResultado siaResultado = enviarProcedimiento(procDelegate, procedimiento,  null, resultadoDescripcion,  null,  null);
	        						if (!siaResultado.isCorrecto()) {
	        							todosCorrectos = false;
	        						}
	        					} else {
	        						
	        						resultadoDescripcion.append("   ---- Ejecutado correcto (no cumplia requisitos, no se ha enviado a SIA) <br />");
	        					}
	        					
							}
	        				
	        				if (todosCorrectos) {
	        					//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
								siaPendiente.setFecIdx(new Date());
	    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	    						
	    						correctos++;
	    						resultadoDescripcion.append("   -- Marcado como correcto <br />");
	    					} else {
	    						//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
								siaPendiente.setMensaje("Ha fallado indexando los procedimientos asociados a la UA");
								siaPendiente.setFecIdx(new Date());
	        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	        					
	        					incorrectos++;	
	        					resultadoDescripcion.append("   -- Marcado como incorrecto <br />");
	    						resultadoDescripcion.append("   -- Mensaje:"+resultado.getMensaje()+" <br />");
	    					}
	        				
	        				
	        			} else if (SiaUtils.SIAPENDIENTE_TIPO_NORMATIVA.equals(siaPendiente.getTipo() )) {
	        				
	        				//OBTENER EL ID DE TODOS LAS PROCEDIMIENTOS AFECTADOS POR ESA NORMATIVA
	        				final List <ProcedimientoLocal>listProcedimientos = procDelegate.listarProcedimientosNormativa(siaPendiente.getIdElemento());
	        				boolean todosCorrectos = true;
	        				for (ProcedimientoLocal procedimiento : listProcedimientos) {
	        					
	        					boolean esEnviable = SiaUtils.isEnviableSia(procedimiento);
	        					if (esEnviable || (!esEnviable && procedimiento.getEstadoSIA() != null && SiaUtils.ESTADO_ALTA.equals(procedimiento.getEstadoSIA()))) {
	        				    	
	        						SiaResultado siaResultado = enviarProcedimiento(procDelegate, procedimiento,  null, resultadoDescripcion,  null,  null);        						
	        						if (!siaResultado.isCorrecto()) {
	        							todosCorrectos = false;
	        						}
	        					} else {
	        						resultadoDescripcion.append("   ---- Ejecutado correcto (no cumplia requisitos, no se ha enviado a SIA) <br />");
	        					}
							}
	        				
	        				if (todosCorrectos) {
	        					//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_CORRECTO);
								siaPendiente.setFecIdx(new Date());
	    						siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
	    						
	    						correctos++;		    						
	    						resultadoDescripcion.append("   -- Marcado como correcto <br />");
	    					} else {
	    						//Actualizamos el siaPendiente
								siaPendiente.setEstado(SiaUtils.SIAPENDIENTE_ESTADO_INCORRECTO);
								siaPendiente.setMensaje("Ha fallado indexando los procedimientos asociados a la normativa");
								siaPendiente.setFecIdx(new Date());
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
					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO_CON_ERRORES);
				} else {
					siaJob.setEstado(SiaUtils.SIAJOB_ESTADO_ENVIADO);
				}
			 
	        	resultadoDescripcionBreve.append("Se han enviado "+(correctos+incorrectos)+" datos. De los cuales: <br />");
	        	resultadoDescripcionBreve.append(" - "+correctos+" datos correctos  <br />");
	        	resultadoDescripcionBreve.append(" - "+incorrectos+" datos incorrectos  <br />");
	        	resultadoDescripcionBreve.append(" Finalizado a las "+formato.format(calendar.getTime())+"  <br />");
	        	siaJob.setDescBreve(Hibernate.createClob(resultadoDescripcionBreve.toString()));
    			siaJob.setDescripcion(Hibernate.createClob(resultadoDescripcion.toString()));
    			
	        	siaPendienteDelegate.actualizarSiaJob(siaJob);
	        	
        	
      	} catch (Exception e) {
			log.error(e);			
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
		if(trad != null){			
			sia.setTitulo(trad.getNombre());
			sia.setDescripcion(trad.getResumen());
		}
		
		sia.setIdCent(getIdCentro(procedimiento));
		
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
		
		for (Materia mat : procedimiento.getMaterias()) {
			if (mat.getCodigoSIA() != null) {
				if (!materias.contains(mat.getCodigoSIA().toString())) {
					materias.add(mat.getCodigoSIA().toString());
				}
			}
		}
		sia.setMaterias(materias.toArray(new String[materias.size()]));

		if (procedimiento.getIndicador() == null) {
			sia.setFiVia(SiaUtils.NO);
		} else {
			sia.setFiVia(procedimiento.getIndicador().equals("1")? SiaUtils.SI : SiaUtils.NO);
		}
		sia.setTipologia(SiaUtils.getTipologiaTramitacion());
		
		sia.setEnlaceWeb(SiaUtils.getUrl()+procedimiento.getId().toString());
		
		sia.setEstado(procedimiento.getEstadoSIA());
		
		if (SiaUtils.validaProcedimientoSIA(procedimiento) ) {
			
			//ALTA / MODIFICACION / REACTIVACION
			if (sia.getIdSIA() == null || sia.getIdSIA().isEmpty()) {
				sia.setOperacion(SiaUtils.ESTADO_ALTA);
			} else if (sia.getEstado().equals(SiaUtils.ESTADO_ALTA)) {
				sia.setOperacion(SiaUtils.ESTADO_MODIFICACION); 
			} else if (sia.getEstado().equals(SiaUtils.ESTADO_BAJA)) { 
				sia.setOperacion(SiaUtils.ESTADO_REACTIVACION); 
			}
			
						
		} else if (procedimiento.getCodigoSIA() != null && !procedimiento.getCodigoSIA().isEmpty() 
				&& sia.getEstado() != null && !sia.getEstado().equals(SiaUtils.ESTADO_BAJA)){
			
			sia.setOperacion(SiaUtils.ESTADO_BAJA); 

		}
		
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
}
