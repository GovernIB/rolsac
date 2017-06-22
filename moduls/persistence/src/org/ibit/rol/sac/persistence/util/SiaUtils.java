package org.ibit.rol.sac.persistence.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;

public class SiaUtils {
	private static Log log = LogFactory.getLog(SiaUtils.class);
	
	public static final Integer SIAJOB_ESTADO_CREADO  = 0;
	public static final Integer SIAJOB_ESTADO_EN_EJECUCION  = 1;
	public static final Integer SIAJOB_ESTADO_ENVIADO  = 2;
	public static final Integer SIAJOB_ESTADO_ENVIADO_CON_ERRORES = 3;
	public static final Integer SIAJOB_ESTADO_ERROR_GRAVE  = -1;
	
	public static final Integer SIAPENDIENTE_ESTADO_CREADO     = 0;
	public static final Integer SIAPENDIENTE_ESTADO_CORRECTO   = 1;
	public static final Integer SIAPENDIENTE_ESTADO_INCORRECTO = -1;
	public static final Integer SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS = -2;
	
	public static final String SIAPENDIENTE_TIPO_PROCEDIMIENTO = "PROC";
	//Se quitan por los nuevos cambios: public static final String SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA = "UA";
	//Se quitan por los nuevos cambios: public static final String SIAPENDIENTE_TIPO_NORMATIVA = "NORM";
	
	public static final String ESTADO_BAJA = "B";
	public static final String ESTADO_ALTA = "A";
	public static final String ESTADO_MODIFICACION = "M";
	public static final String ESTADO_REACTIVACION = "AC";
	
	public static final Integer TIPOLOGIA_INTERNO_COMUN = 1;
	public static final Integer TIPOLOGIA_INTERNO_ESPECIFICO = 2;
	public static final Integer TIPOLOGIA_EXTERNO_COMUN = 3;
	public static final Integer TIPOLOGIA_EXTERNO_ESPECIFICO = 4;
	
	public static final Integer TIPO_TRAMITE_PROC = 1;
	
	public static final String TRAMITE_PROC = "P";
	public static final String TRAMITE_SERV = "S";
	
	public static final String SI = "S";
	public static final String NO = "N";
	
	/*public static final String SIAPDT_ESTADO_INDEXAR = 0;
	public static final String SIAPDT_ESTADO_DESINDEXAR = 1;
	*/
	private static final String ERROR_MESSAGE = "Error obteniendo la propiedad ";

	public static final Integer SIAPENDIENTE_PROCEDIMIENTO_EXISTE = 1;
	public static final Integer SIAPENDIENTE_PROCEDIMIENTO_BORRADO = 0;

	/** El nombre cuando se crea el job de quartz. **/
	public static final String SIA_JOB_QUARTZ_NAME = "siaJob";
	
	
	/**
	 * Obtiene un long de una propiedad.
	 * @param property
	 * @return
	 */
	private static Long getLongFromProperty(String property) {
        try {
            return Long.valueOf(System.getProperty(property).trim());
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return null;
        }
    }
	
	/**
	 *btiene un entero de una propiedad.
	 * @param property
	 * @return
	 */
	private static Integer getIntFromProperty(String property) {
        try {
            return Integer.valueOf(System.getProperty(property).trim());
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return 0;
        }
    }

	/**
	 * Get tipo actuacion envio SIA.
	 * @return
	 */
	public static Integer getTipoActuacion() {
        return getIntFromProperty("es.caib.rolsac.sia.tipoActuacion");
    }
	
	/**
	 * Get tipologia envio SIA.
	 * @return
	 */
	public static Integer getTipologiaTramitacion() {
        return getIntFromProperty("es.caib.rolsac.sia.tipologiaTramitacion");
    }
	
	/**
	 * Get administracion envio SIA.
	 * @return
	 */
	public static Long getAdministracion() {
        return getLongFromProperty("es.caib.rolsac.sia.administracion");
    }
	
	/**
	 * Get url envio SIA.
	 * @return
	 */
	public static String getUrl() {
        return System.getProperty("es.caib.rolsac.sia.url").trim();
    }
		
	
	/**
	 * Get url envio SIA.
	 * @return
	 */
	public static String getUrlEnvio() {
        return System.getProperty("es.caib.rolsac.sia.url.envio").trim();
    }
	
	/**
	 * Get level SIA. 
	 * @return
	 */
	public static Integer getLevelSIA() {
		 String level = System.getProperty("es.caib.rolsac.sia.centro.level");
		 if (level == null) {
			 return 3;
		 } else {
			 return Integer.valueOf(level.trim());
		 }
	}
	
	/**
	 * @param clob
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public static StringBuffer obtenerContenidoClob(Clob clob)
			throws SQLException, IOException {
		String read;
		StringBuffer buffer = new StringBuffer();
		if (clob != null){
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(clob.getAsciiStream()));
			read = null;
			
			while((read = reader.readLine()) != null ){
				buffer.append(read);
			}
		}
		return buffer;
	}

	/**
	 * Se encarga de crear una pendiente SIA según los datos de entrada.
	 * @param tipo
	 * @param idElemento
	 * @param existe
	 */
	public static void marcarIndexacionPendiente(final String tipo, final Long idElemento, final Integer existe, final String idSia, final ProcedimientoLocal procedimiento) throws DelegateException {
		SiaPendienteProcesoDelegate siaPendienteProcesoDeletegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		SiaPendiente siaPendiente = new SiaPendiente();
		siaPendiente.setEstado(SIAPENDIENTE_ESTADO_CREADO);
		siaPendiente.setFecAlta(new Date());
		siaPendiente.setIdElemento(idElemento);
		siaPendiente.setExiste(existe);
		siaPendiente.setTipo(tipo);
		if (idSia != null && !idSia.isEmpty()) {
			siaPendiente.setIdSia(Long.valueOf(idSia));
		}
		siaPendienteProcesoDeletegate.generarSiaPendiente(siaPendiente, procedimiento);
	}

	/**
	 * Comprueba si está activo el envío hacia SIA.
	 * @return
	 */
	public static boolean isActivoEnvio() {
		final String activarEnvio = System.getProperty("es.caib.rolsac.sia.activarEnvio");
		boolean activo;
		if (activarEnvio != null && "S".equals(activarEnvio)) {
			activo = true;
		} else {
			activo = false;
		}
		return activo;
	}
	
	/**
	 * Get tipologia envio SIA.
	 * @return
	 */
	public static Integer getTiempoReintento() {
        return getIntFromProperty("es.caib.rolsac.sia.tiempo.reintento");
    }


	/**
	 * Comprueba si un procedimiento es enviable a SIA.
	 * Checks:
	 * 	- Es visible.
	 *  - Es visible UA.
	 *  - Tiene codigo DIR3.
	 *  - Está en SiaUA.
	 * 
	 * @param procedimiento
	 * @return
	 */
	public static SiaEnviableResultado isEnviable (final ProcedimientoLocal procedimiento) {
		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);
		final StringBuffer mensajeError = new StringBuffer();

		//Si el procedimiento que se pasa es nulo, tiene que salir.
		if (procedimiento == null) {
			resultado.setRespuesta("El procedimiento está nulo.");	
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
	    	return resultado;
		}
		
		//Es visible.
		final boolean esVisible = procedimiento.isVisible();
		if (!esVisible) {
			mensajeError.append("El procedimiento no es visible.");
		}
		
		//Es visible UA.
	    boolean isVisibleUA = isVisibleUA(procedimiento);
	    if (!isVisibleUA) {
	    	mensajeError.append("La UA del organo resolutori o de algunos de sus predecesores es no visible.");	
	    }
		
		//Tiene código centro.
		boolean tieneCodigoCentro;
		final String codigoIdCentro = obtenerCodigoIdCentro(procedimiento);
		if (codigoIdCentro == null) {
			tieneCodigoCentro = false;
			mensajeError.append("No tiene código DIR ni el organo resolutori ni predecesores.");
	    } else {
	    	tieneCodigoCentro = true;	
	    	resultado.setIdCentro(codigoIdCentro);
	    }
		
		
		
	    //Comprobamos
	    //Si cumple los 3 checks.
	    //    Si nunca ha estado en SIA --> es enviable a SIA como Alta (A)
	    //    Si esta de baja           --> es enviable a SIA como Reactivación (R)
	    //    Si no..................   --> es enviable a SIA como modificación (M)
	    //Si no cumple alguno de los 34 checks.
	    //    Si está de baja o nunca ha estado en SIA ---> NO es enviable
	    //    Si no .....								--> es enviable a SIA como baja.
	    if (esVisible && tieneCodigoCentro && isVisibleUA) {
	    	resultado.setNotificarSIA(true);
		    if (procedimiento.getEstadoSIA() == null) {
		    	resultado.setOperacion(SiaUtils.ESTADO_ALTA);
		    } else {
		    	if (SiaUtils.ESTADO_BAJA.equals(procedimiento.getEstadoSIA())) {
		    		resultado.setOperacion(SiaUtils.ESTADO_REACTIVACION);
		    	} else {
		    		resultado.setOperacion(SiaUtils.ESTADO_MODIFICACION);
		    	}
		    }
	    } else {
	    	if (procedimiento.getEstadoSIA() == null || SiaUtils.ESTADO_BAJA.equals(procedimiento.getEstadoSIA())) {
	    		resultado.setNotificarSIA(false);
	    		resultado.setRespuesta(mensajeError.toString());
	    	} else {
	    		//Sin código SiaUA no se puede enviar
    			resultado.setNotificarSIA(false);    			
	    	}
	    }
	    
		return resultado;
	}
	
	/**
	 * Comprueba si le falta algún dato. 
	 * Condiciones:
	 *  - Tiene materias.
	 *  - Tiene normativas y uno de tipo SIA.
	 *  - Tiene descripción.
	 *  - Tiene resumen.
	 *  - Tiene SiaUA
	 *  
	 * @param procedimiento
	 * @return
	 */
	public static SiaCumpleDatos cumpleDatos(final ProcedimientoLocal procedimiento) {
		final SiaCumpleDatos resultado = new SiaCumpleDatos(false);
		final StringBuffer mensajeError = new StringBuffer();

		if (procedimiento == null) {
			resultado.setRespuesta("El procedimiento está nulo.");
			resultado.setCumpleDatos(false);
			return resultado;
		}
		
	    boolean tieneMaterias=procedimiento.getMaterias().size() > 0;
	    if (!tieneMaterias) {
	    	mensajeError.append("No tiene materias.");	
	    }
	    
	    boolean tieneNormativas=procedimiento.getNormativas().size() > 0;
	    if (!tieneNormativas) {
	    	mensajeError.append("No tiene normativas.");	
	    }
	    
	    boolean encontradoTipo = false;
	    if (procedimiento.getNormativas().size() > 0) {
		    for (Normativa norm : procedimiento.getNormativas()) {
		    	if (norm.getTipo() != null && norm.getTipo().getTipoSia() != null) {
		    		encontradoTipo = true;
		    	}
		    }
	    }
	    
	    if (!encontradoTipo) {
	    	mensajeError.append("Ninguna de las normativas tiene asociado un tipo sia.");
	    }
	    
	    
	    final String nombre = getNombreProcedimiento(procedimiento);
	    boolean tieneNombre;
	    if (StringUtils.isBlank(nombre)) {
	    	mensajeError.append("El procedimiento no tiene titulo.");	
	    	tieneNombre = false;
	    } else {
	    	tieneNombre = true;
	    	resultado.setNombre(nombre);
	    }
	    
	    final String resumen = getResumenProcedimiento(procedimiento);
	    boolean tieneResumen;
	    if (StringUtils.isBlank(resumen)) {
	    	mensajeError.append("El procedimiento no tiene resumen.");	
	    	tieneResumen = false;
	    } else {
	    	tieneResumen = true;
	    	resultado.setResumen(resumen);
	    }
	    
	    boolean tieneSiaUA;
  		final SiaUA siaUA = obtenerSiaUA(procedimiento);
  	    if (siaUA == null) {
  			tieneSiaUA = false;	
  			mensajeError.append("No tiene una asociada una UA multientidad.");
  	    } else {
  	    	tieneSiaUA = true;
  	    	resultado.setSiaUA(siaUA);
  	    }
	    
	    /** Si cumple todos los datos ok, sino incrustamos el mensaje de error. **/
	    if (tieneMaterias && tieneNormativas && encontradoTipo && tieneNombre && tieneResumen && tieneSiaUA) {
	    	resultado.setCumpleDatos(true);	    	
	    } else {
	    	resultado.setCumpleDatos(false);
	    	resultado.setRespuesta(mensajeError.toString());
	    }
	    
	    return resultado;
	}
	
	
	/**
	 * Compruebo si todas las UAs son visible.
	 * @param procedimiento
	 * @return
	 */
	private static boolean isVisibleUA(ProcedimientoLocal procedimiento) {
		
		boolean visible = true;
		if (procedimiento == null || procedimiento.getOrganResolutori() == null) {
			visible = false;
		} else {
			if (!procedimiento.getOrganResolutori().getValidacion().equals(Validacion.PUBLICA)) {
				
				visible = false;
				
			} else {
				//Recorremos sus predecesores
		    	for(Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
		    		final UnidadAdministrativa ua = (UnidadAdministrativa) oua;
		    		if (!ua.getValidacion().equals(Validacion.PUBLICA)) {
		    			visible = false;
		    			break;
		    		}
		    	}
			}
		}
		
		return visible;
	}

	/**
	 * Devuelve el código DIR3 de la UA, si es nulo, es que ninguno lo tiene.
	 * 
	 * @param procedimiento
	 * @return
	 */
	private static String obtenerCodigoIdCentro(ProcedimientoLocal procedimiento) {
		
		String codigoIdCentro = null;
		
		if (procedimiento == null || procedimiento.getOrganResolutori() == null) {
			codigoIdCentro = "";
		} else {
		
			if (procedimiento.getOrganResolutori().getCodigoDIR3() == null) {
		    	
		    	//Recorremos sus predecesores
		    	for(Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
		    		UnidadAdministrativa ua = (UnidadAdministrativa) oua;
		    		if (ua.getCodigoDIR3() != null) {
		    			codigoIdCentro = ua.getCodigoDIR3();
		    			break;
		    		}
		    	}
		    	
		    } else {
		    	codigoIdCentro = procedimiento.getOrganResolutori().getCodigoDIR3();
		    }
		}

		return codigoIdCentro;
	}
	
	
	/**
	 * Devuelve el código DIR3 de la UA, si es nulo, es que ninguno lo tiene.
	 * 
	 * @param procedimiento
	 * @return
	 */
	private static SiaUA obtenerSiaUA(ProcedimientoLocal procedimiento) {
		
		SiaUA siaUA;
		try {
			if (procedimiento == null || procedimiento.getOrganResolutori() == null) {
				siaUA = null;
			} else {
			
				SiaPendienteProcesoDelegate siaPendienteProceso = DelegateUtil.getSiaPendienteProcesoDelegate();
				siaUA = siaPendienteProceso.obtenerSiaUA(procedimiento.getOrganResolutori()); 
				
				if (siaUA == null) {
					//Recorremos sus predecesores
			    	for(final Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
			    		final UnidadAdministrativa ua = (UnidadAdministrativa) oua;
			    		siaUA = siaPendienteProceso.obtenerSiaUA(ua); 
			    		if (siaUA != null) {
			    			break;
			    		}
			    	}
				}
			}
		} catch (Exception exception) {
			log.error("Error obteniendo la siaUA", exception);
			siaUA = null;
		}
		
		return siaUA;
	}
	
	/**
	 * Obtiene el resumen del procedimiento.
	 * @param procedimiento
	 * @return
	 */
	public static String getResumenProcedimiento(ProcedimientoLocal procedimiento) {
		final TraduccionProcedimientoLocal tradEs = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		final TraduccionProcedimientoLocal tradCa = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
		String resumen = null;
		if (tradEs != null  && StringUtils.isNotBlank(tradEs.getResumen())) {
			resumen = tradEs.getResumen();
		} else if (tradCa != null) {
			resumen = tradCa.getResumen();
		}
		if (resumen != null && resumen.length() >= 4000) {
			resumen = resumen.substring(0, 3999);
		}
		return resumen;
	}

	/**
	 * Calcula el nombre del procedimiento. 
	 * @param procedimiento
	 * @return
	 */
	 public static String getNombreProcedimiento(ProcedimientoLocal procedimiento) {
		 final TraduccionProcedimientoLocal tradEs = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		 final TraduccionProcedimientoLocal tradCa = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
		 String nombre = null;
		 if (tradEs != null  && StringUtils.isNotBlank(tradEs.getNombre())) {
			nombre = tradEs.getNombre();
		 } else if (tradCa != null ){
			nombre = tradCa.getNombre();
		 }
		 return nombre;	
	}

	 /**
	 * Calcula el nombre de la normativa. 
	 * @param normativa
	 * @return
	 */
	 public static String getNombreNormativa(Normativa normativa) {
		final TraduccionNormativa tradEs = (TraduccionNormativa) normativa.getTraduccion("es");
		final TraduccionNormativa tradCa = (TraduccionNormativa) normativa.getTraduccion("ca");
		String nombre = null;
		if (tradEs != null  && StringUtils.isNotBlank(tradEs.getTitulo())) {
			nombre = tradEs.getTitulo();
		} else if (tradCa != null ){
			nombre = tradCa.getTitulo();
		}
		return nombre;	
	}
}


