package org.ibit.rol.sac.persistence.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SiaPendiente;
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
	
	public static final String SIAPENDIENTE_TIPO_PROCEDIMIENTO = "PROC";
	public static final String SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA = "UA";
	public static final String SIAPENDIENTE_TIPO_NORMATIVA = "NORM";
	
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
	 * Obtiene el id del departamento.
	 * @return
	 */
	public static String getIdDepartamento() {
		return System.getProperty("es.caib.rolsac.sia.departamento").trim();
	}
	
	/**
	 * Get usuario envio SIA.
	 * @return
	 */
	public static String getUsuarioEnvio() {
        return System.getProperty("es.caib.rolsac.sia.usuario.envio").trim();
    }
	
	/**
	 * Get password envio SIA.
	 * @return
	 */
	public static String getPasswordEnvio() {
        return System.getProperty("es.caib.rolsac.sia.pass.envio").trim();
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
	public static void marcarIndexacionPendiente(final String tipo, final Long idElemento, final Integer existe, final String idSia) throws DelegateException {
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
		siaPendienteProcesoDeletegate.generarSiaPendiente(siaPendiente);
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


	/***
	 * Comprueba si un procedimiento hay que enviarlo. Hay que tener en cuenta lo siguiente: 
	 * <p>
	 *   Los estados de un procedimiento son:
	 *   <ul>
	 *   	<li>Estado 1. Nunca se ha enviado (Procedimiento.estadoSIA == null).</li>
	 *   	<li>Estado 2. Alta en SIA  (Procedimiento.estadoSIA == 'A').</li>
	 *      <li>Estado 3. Baja en SIA   (Procedimiento.estadoSIA == 'B').</li>
	 *   </ul>
	 *   A partir de ahí, se valida la nueva información y puede pasar dos cosas:
	 *   <ul>
	 *   	<li>Debería estar en SIA. Activo en SIA. </li>
	 *      <li>Debería estar en SIA. Desactivado en SIA. </li>
	 *   </ul>
	 *   En total se producen 6 combinaciones.
	 *   <ul>
	 *   	<li>Estado 1 y Activo en  (Notificar Alta). </li>
	 *      <li>Estado 1 y Desactivado en SIA (NO HACER NADA). </li>
	 *   	<li>Estado 2 y Activo en SIA (Notificar Modificación). </li>
	 *      <li>Estado 2 y Desactivado en SIA (Notificar baja). </li>
	 *   	<li>Estado 3 y Activo en SIA (Notificar Reactivación). </li>
	 *      <li>Estado 3 y envío baja (NO HACER NADA). </li>
	 *   </ul>
	 *   Hay 2 combinaciones que no se debería hacer nada y que devolverán falsa, en el resto true.
	 *   
	 * </p>
	 * @param procedimiento
	 * @return
	 */
	public static SiaEnviableResultado isEnviableSia(ProcedimientoLocal procedimiento) {
		SiaEnviableResultado resultado = SiaUtils.validaProcedimientoSIA(procedimiento);
		
		
		if (resultado.isNotificiarSIA()) { //Si se notifica a SIA, se debe enviar un ALTA, MODIFICACION o REACTIVACIÓN.
			
			if (procedimiento.getCodigoSIA() == null || procedimiento.getCodigoSIA().isEmpty()) { //Si no tiene codigo SIA, ES UN ALTA!!!
				resultado.setOperacion(SiaUtils.ESTADO_ALTA);
			} else if (procedimiento.getEstadoSIA() != null && procedimiento.getEstadoSIA().equals(SiaUtils.ESTADO_BAJA)) {
				resultado.setOperacion(SiaUtils.ESTADO_REACTIVACION);  //Si tiene codigo SIA y está de BAJA, entonces es una reactivación
			} else {
				resultado.setOperacion(SiaUtils.ESTADO_MODIFICACION);  //Como ultima acción, modificación.
			}
			
		} else { //No tendría que estar activo en SIA.
					//Sólo se envia info a SIA cuando hay que dar de baja un procedimiento.
		
			if ( procedimiento.getEstadoSIA() != null &&  !SiaUtils.ESTADO_BAJA.equals(procedimiento.getEstadoSIA()) ) {
				//Si está dando 
				resultado.setNotificarSIA(true);
				resultado.setOperacion(SiaUtils.ESTADO_BAJA);
			}
		}
		
		return resultado;
	}

	/**
	 * Valida si un procedimiento es para enviar o no a SIA.
	 * Condiciones:
	 *  - Tiene organo resolutori
	 *  - Tiene materias.
	 *  - Tiene normativas
	 *  - Procedimiento visible
	 *  - El organo resolutori o alguno de los predecesores tiene código DIR3.
	 * @param procedimiento
	 * @return
	 */
	private static SiaEnviableResultado validaProcedimientoSIA(final ProcedimientoLocal procedimiento) {
		
		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);
		final String codigoIdCentro = obtenerCodigoIdCentro(procedimiento);
		resultado.setIdCentro(codigoIdCentro);
		
	    boolean servicio = procedimiento.getOrganResolutori() != null ;
	    if (!servicio) {
	    	resultado.setRespuesta("no tiene organo resolutori");	
	    	return resultado;
	    }
	    
	    boolean tieneMaterias=procedimiento.getMaterias().size() > 0;
	    if (!tieneMaterias) {
	    	resultado.setRespuesta("No tiene materias");	
	    	return resultado;
	    }
	    
	    boolean tieneNormativas=procedimiento.getNormativas().size() > 0;
	    if (!tieneNormativas) {
	    	resultado.setRespuesta("No tiene normativas");	
	    	return resultado;
	    }
	    
	    boolean encontradoTipo = false;
	    for (Normativa norm : procedimiento.getNormativas()) {
	    	if (norm.getTipo() != null && norm.getTipo().getTipoSia() != null) {
	    		encontradoTipo = true;
	    	}
	    }
	    if (!encontradoTipo) {
	    	resultado.setRespuesta("Ninguna de las normativas tiene asociado un tipo sia");	
	    	return resultado;
	    }
	    
	    if (!procedimiento.isVisible()) {
	    	resultado.setRespuesta("Procedimiento no visible");	
	    	return resultado;
	    }
	    
	    boolean isVisibleUA = isVisibleUA(procedimiento);
	    if (!isVisibleUA) {
	    	resultado.setRespuesta("La UA del organo resolutori o de algunos de sus predecesores es no visible.");	
	    	return resultado;
	    }

	    if (codigoIdCentro == null) {
	    	resultado.setRespuesta("No tiene código DIR ni el organo resolutori ni predecesores.");	
	    	return resultado;
	    }
	    
	    resultado.setNotificarSIA(true);
	    return resultado;
	    
	}

	/**
	 * Compruebo si todas las UAs son visible.
	 * @param procedimiento
	 * @return
	 */
	private static boolean isVisibleUA(ProcedimientoLocal procedimiento) {
		
		boolean visible = true;
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
			return "";
		}
		
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

		return codigoIdCentro;
	}
	
	
}

