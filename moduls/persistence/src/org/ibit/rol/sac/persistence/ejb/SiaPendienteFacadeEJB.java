package org.ibit.rol.sac.persistence.ejb;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Materia;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.PublicoObjetivo;
import org.ibit.rol.sac.model.Sia;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.Traduccion;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.model.ws.SiaWS;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;
import org.ibit.rol.sac.persistence.util.SiaUtils;




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
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	
	 * @throws DelegateException
	 */
	public void enviarTodos() throws DelegateException  {
		log.debug("Enviar todos los procedimientos SIA ");
		
		final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
	
		StringBuffer descripcionBreve = new StringBuffer();
		SimpleDateFormat formato = new SimpleDateFormat("H:mm");
		Date fecha = new Date();
		String cadenaFecha = formato.format(fecha);
		
		int correctos = 0;
		int incorrectos = 0;
		
    	try {
    		SiaJob siaJob = siaPendienteDelegate.crearSiaJob();
			for(Long idProcedimiento : procDelegate.buscarIdsProcedimientos() ) {
				ProcedimientoLocal proc = procDelegate.obtenerProcedimiento(idProcedimiento);
				if (SiaUtils.validaProcedimientoSIA(proc) ) {
					
					Sia  sia = obtenerSiaProc(proc);
					//Enviar a SIA
					SiaResultado resultado = SiaWS.enviarSIA(sia); //ALTA / MODIFICACION / REACTIVACION
					if (resultado.isCorrecto()) {
						correctos++;
					} else {
						incorrectos++;
					}
					siaJob.getDescripcion().setString(0, resultado.toString());
								
				} else if (!proc.getCodigoSIA().isEmpty()){
					
					//Falta poner arriba en el if !proc.getEstadoSIA.equals("BAJA")
					//TODO enviar desasignacion 
					Sia  sia = obtenerSiaProc(proc);
					//Enviar a SIA
					SiaResultado resultado = SiaWS.enviarSIA(sia); //BAJA
					
					if (resultado.isCorrecto()) {
						correctos++;
					} else {
						incorrectos++;
					}
					siaJob.getDescripcion().setString(0, resultado.toString());
				}
			}
			descripcionBreve.append("El proceso ha empezado a las " + cadenaFecha);
			descripcionBreve.append("Se han enviado "+(correctos + incorrectos)+" datos \n");
			descripcionBreve.append(correctos + " han sido correctos \n");
			descripcionBreve.append(incorrectos + " han sido incorrectos \n");
			fecha= new Date();
			cadenaFecha = formato.format(fecha);
			descripcionBreve.append("Finalizado hora "+ cadenaFecha);
			
			siaJob.getDescBreve().setString(0L, descripcionBreve.toString());
			if (incorrectos == 0) {
				siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO);
			} else {
				siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES);
			}
	
			actualizarJob(siaJob);
			
		} catch (SQLException e) {
			log.debug("EnviarTodos KO");
		}
    	
    	
	}
	
	/**
	 * Obtener SIA pendientes de enviar 
   	 * @ejb.interface-method
   	 * @ejb.permission unchecked="true"
   	 * 
   	
	 * @throws DelegateException
	 */
	public void enviarPendientes()  {
		log.debug("Enviar todos los procedimientos SIA pendientes");

		
		final SiaPendienteProcesoDelegate siaPendienteDelegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		Session session = null;
		
        try {
        	
        	SiaJob siaJob = siaPendienteDelegate.crearSiaJob();
        	
        	List<SiaPendiente> siaPendientes = siaPendienteDelegate.getSiaPendientesEnviar();
        	session = getSession();
        	
			int i = 0;
			int erroneos = 0;
			final ProcedimientoDelegate procDelegate = DelegateUtil.getProcedimientoDelegate();
        	for(SiaPendiente siaPendiente : siaPendientes) {
        			
        			if (i % 20 == 0) {
        				session.flush();
        			} 
        			i++;
        			if (SiaUtils.SIAJOB_TIPO_PROCEDIMIENTO.equals(siaPendiente.getTipo() )) {
        				
        				ProcedimientoLocal procedimiento = procDelegate.obtenerProcedimiento(siaPendiente.getIdElemento());
        				//GENERAR OBJETO SIA
    					Sia  sia = obtenerSiaProc(procedimiento);
    					SiaResultado resultado = SiaWS.enviarSIA(sia);
    					if (resultado.isCorrecto()) {
    						siaPendiente.setEstado(SiaUtils.SIAJOB_SIP_ESTADO_CORRECTO);
        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);	
    					} else {
    						siaPendiente.setEstado(SiaUtils.SIAJOB_SIP_ESTADO_INCORRECTO);
        					siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
    					}
        				
        			} else if (SiaUtils.SIAJOB_TIPO_UNIDAD_ADMINISTRATIVA.equals(siaPendiente.getTipo() )) {
        				
        				List <ProcedimientoLocal>listProc = procDelegate.listarProcedimientosUA(siaPendiente.getIdElemento());
        				
        				for (ProcedimientoLocal proc : listProc) {
        					//GENERAR OBJETO SIA
        					Sia  sia = obtenerSiaProc(proc);
        					erroneos = obtenerResultadosEnvio(
									siaPendienteDelegate, erroneos,
									siaPendiente, sia);
        					
        					
						}
        				
        			} else if (SiaUtils.SIAJOB_TIPO_NORMATIVA.equals(siaPendiente.getTipo() )) {
        				//OBTENER EL ID DE TODOS LAS PROCEDIMIENTOS AFECTADOS POR ESA NORMATIVA
        				List <ProcedimientoLocal>listProc = procDelegate.listarProcedimientosNormativa(siaPendiente.getIdElemento());
        				for (ProcedimientoLocal proc : listProc) {
        					//GENERAR OBJETO SIA
        					Sia  sia = obtenerSiaProc(proc);
        					erroneos = obtenerResultadosEnvio(
									siaPendienteDelegate, erroneos,
									siaPendiente, sia);
						}
        			} 
        	}        	
        	
        	if (erroneos > 0) {
				siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES);
			} else {
				siaJob.setEstado(SiaUtils.SIAJOB_SIJ_ESTADO_ENVIADO);
			}
        	actualizarJob(siaJob);
        	
        	session.flush();
        	
        } catch (HibernateException he) {
            throw new EJBException(he);
        } catch (DelegateException e) {
        	log.error("Error en enviarTodos ", e);
		} finally {
            if (session != null) {close(session);}
           
        }
        
	}



	private int obtenerResultadosEnvio(
			final SiaPendienteProcesoDelegate siaPendienteDelegate,
			int erroneos, SiaPendiente siaPendiente, Sia sia)
			throws DelegateException {
		SiaResultado resultado = SiaWS.enviarSIA(sia);
		if (resultado.isCorrecto()) {
			siaPendiente.setEstado(SiaUtils.SIAJOB_SIP_ESTADO_CORRECTO);
			siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);	
		} else {
			erroneos++;
			siaPendiente.setEstado(SiaUtils.SIAJOB_SIP_ESTADO_INCORRECTO);
			siaPendienteDelegate.actualizarSiaPendiente(siaPendiente);
		}
		return erroneos;
	}



	private Sia obtenerSiaProc(ProcedimientoLocal procedimiento) {
		Sia sia = new Sia();
		sia.setIdProc(procedimiento.getId().toString());
		
		TraduccionProcedimientoLocal trad = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		sia.setTitulo(trad.getNombre());
		sia.setDescripcion(trad.getResumen());
		
		List<UnidadAdministrativa> predecesores = procedimiento.getUnidadAdministrativa().getPredecesores();
		for(UnidadAdministrativa predecesor : predecesores) {
			if (predecesor.getPadre() != null && predecesor.getCodigoDIR3() != null) {
				sia.setIdCent(predecesor.getCodigoDIR3());
				break;
			}
		}
		
		sia.setUaGest(procedimiento.getUnidadAdministrativa().getNombre());
		
		String destinatarios="";
		Set<PublicoObjetivo> publicoObjs = procedimiento.getPublicosObjetivo();
		for (PublicoObjetivo pObj : publicoObjs) {
			destinatarios+= pObj.getId().toString() +";";
		}
		sia.setIdDest(destinatarios);
		
		List<Tramite> tramites = procedimiento.getTramites();
		Integer nivelAdministrativo = 1;
		for (Tramite tramite : tramites) {
			if (tramite.getFase() == 1)  {
				
					if (tramite.getVersio() != null || !tramite.getUrlExterna().isEmpty()) {
						nivelAdministrativo = 4;
						break;
					}
				
					Set<DocumentTramit> docs = tramite.getDocsInformatius();
					docs.addAll(tramite.getDocsRequerits());
					for (DocumentTramit documentTramit : docs) {
						if (documentTramit.getTipus() == 1) {
							nivelAdministrativo=2;
						}
					}
			}
		}
		sia.setNivAdm(nivelAdministrativo.toString());
		
		
		String idNormativa="";
		String titNormativa="";
		for (Normativa norm : procedimiento.getNormativas()) {
			idNormativa += norm.getTipo().getTipoSia().toString()+";";
			titNormativa +=norm.getTraduccionTitulo()+";";
			
		}
		sia.setIdNorm(idNormativa);
		sia.setTiNorm(titNormativa);
		
		String idMateria="";
		for (Materia mat : procedimiento.getMaterias()) {
			idMateria+=mat.getId().toString()+";";
			
		}
		sia.setMaterias(idMateria);
		sia.setFiVia(procedimiento.getIndicador().equals("1")? "S":"N");
		
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
