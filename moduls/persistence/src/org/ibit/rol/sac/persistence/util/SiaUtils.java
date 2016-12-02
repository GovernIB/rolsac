package org.ibit.rol.sac.persistence.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Clob;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.ProcedimientoLocal;

public class SiaUtils {
	private static Log log = LogFactory.getLog(SiaUtils.class);
	
	public static final Integer SIAJOB_SIJ_ESTADO_CREADO  = 0;
	public static final Integer SIAJOB_SIJ_ESTADO_EN_EJECUCION  = 1;
	public static final Integer SIAJOB_SIJ_ESTADO_ENVIADO  = 2;
	public static final Integer SIAJOB_SIJ_ESTADO_ENVIADO_CON_ERRORES = 3;
	public static final Integer SIAJOB_SIJ_ESTADO_ERROR_GRAVE  = -1;
	
	public static final Integer SIAJOB_SIP_ESTADO_CREADO     = 0;
	public static final Integer SIAJOB_SIP_ESTADO_CORRECTO   = 1;
	public static final Integer SIAJOB_SIP_ESTADO_INCORRECTO = -1;
	
	public static final String SIAJOB_TIPO_PROCEDIMIENTO = "PROC";
	public static final String SIAJOB_TIPO_UNIDAD_ADMINISTRATIVA = "UA";
	public static final String SIAJOB_TIPO_NORMATIVA = "NORM";
	
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
	
	private static final String ERROR_MESSAGE = "Error obteniendo la propiedad ";
	
	public static Boolean validaProcedimientoSIA(ProcedimientoLocal procedimiento) {
		
	    boolean servicio = procedimiento.getServicioResponsable() != null ;
	    boolean tieneMaterias=procedimiento.getMaterias().size() > 0;
	    boolean tieneNormativas=procedimiento.getNormativas().size() > 0;
	    
	    return procedimiento.isVisible() && servicio && tieneMaterias && tieneNormativas;
	}
	
	private static Long getLongFromProperty(String property) {
        try {
            return Long.parseLong(System.getProperty(property));
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return null;
        }
    }
	
	private static int getIntFromProperty(String property) {
        try {
            return Integer.parseInt(System.getProperty(property));
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return 0;
        }
    }

	public static Integer getTipoActuacion() {
        return getIntFromProperty("es.caib.rolsac.sia.tipoActuacion");
    }
	
	public static Integer getTipologiaTramitacion() {
        return getIntFromProperty("es.caib.rolsac.sia.tipologiaTramitacion");
    }
	
	public static Long getAdministracion() {
        return getLongFromProperty("es.caib.rolsac.sia.administracion");
    }
	
	public static String getUrl() {
        return System.getProperty("es.caib.rolsac.sia.url");
    }
	
	public static String getUsuarioEnvio() {
        return System.getProperty("es.caib.rolsac.sia.usuario.envio");
    }
	
	public static String getPasswordEnvio() {
        return System.getProperty("es.caib.rolsac.sia.pass.envio");
    }
	
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
	
	
	
	
	
}
