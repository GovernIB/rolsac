package org.ibit.rol.sac.persistence.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;

public class SiaUtils {
	private static Log log = LogFactory.getLog(SiaUtils.class);
	
	public static final Integer SIAJOB_SIJ_ESTADO_CREADO  = 0;
	public static final Integer SIAJOB_SIJ_ESTADO_EN_EJECUCION  = 1;
	public static final Integer SIAJOB_SIJ_ESTADO_ENVIADO  = 2;
	public static final Integer SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES = 3;
	public static final Integer SIAJOB_SIJ_ESTADO_ERROR_GRAVE  = -1;
	
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

	public static final Integer SIAPENDIENTE_TIPO_ACCION_EXISTE = 1;
	public static final Integer SIAPENDIENTE_TIPO_ACCION_BORRADO = 0;
	
	/**
	 * Valida si un procedimiento es para enviar o no a SIA.
	 * @param procedimiento
	 * @return
	 */
	public static Boolean validaProcedimientoSIA(ProcedimientoLocal procedimiento) {
		
	    boolean servicio = procedimiento.getServicioResponsable() != null ;
	    boolean tieneMaterias=procedimiento.getMaterias().size() > 0;
	    boolean tieneNormativas=procedimiento.getNormativas().size() > 0;
	    
	    return procedimiento.isVisible() && servicio && tieneMaterias && tieneNormativas;
	}
	
	/**
	 * Obtiene un long de una propiedad.
	 * @param property
	 * @return
	 */
	private static Long getLongFromProperty(String property) {
        try {
            return Long.valueOf(System.getProperty(property));
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
            return Integer.valueOf(System.getProperty(property));
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
        return System.getProperty("es.caib.rolsac.sia.url");
    }
	
	/**
	 * Get usuario envio SIA.
	 * @return
	 */
	public static String getUsuarioEnvio() {
        return System.getProperty("es.caib.rolsac.sia.usuario.envio");
    }
	
	/**
	 * Get password envio SIA.
	 * @return
	 */
	public static String getPasswordEnvio() {
        return System.getProperty("es.caib.rolsac.sia.pass.envio");
    }
	
	/**
	 * Get url envio SIA.
	 * @return
	 */
	public static String getUrlEnvio() {
        return System.getProperty("es.caib.rolsac.sia.url.envio");
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
	 * @param tipoAccion
	 */
	public static void marcarIndexacionPendiente(final String tipo, final Long idElemento, final Integer tipoAccion) throws DelegateException {
		SiaPendienteProcesoDelegate siaPendienteProcesoDeletegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		SiaPendiente siaPendiente = new SiaPendiente();
		siaPendiente.setEstado(SIAPENDIENTE_ESTADO_CREADO);
		siaPendiente.setFecAlta(new Date());
		siaPendiente.setIdElemento(idElemento);
		siaPendiente.setTipoAccion(tipoAccion);
		siaPendiente.setTipo(tipo);
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
	
}
