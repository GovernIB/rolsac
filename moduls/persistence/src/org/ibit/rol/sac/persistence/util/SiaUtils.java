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

	public static String getUsuario() {
        return System.getProperty("es.webcaib.rolsac.sia.usuario");
    }
	
	public static Long getTipoActuacion() {
        return getLongFromProperty("es.webcaib.rolsac.sia.tipoActuacion");
    }
	
	public static Long getTipologiaTramitacion() {
        return getLongFromProperty("es.webcaib.rolsac.sia.tipologiaTramitacion");
    }
	
	public static Long getAdministracion() {
        return getLongFromProperty("es.webcaib.rolsac.sia.administracion");
    }
	
	public static Long getComunidadAutonoma() {
        return getLongFromProperty("es.webcaib.rolsac.sia.comunidadautonoma");
    }
	
	public static String getDepartamento() {
        return System.getProperty("es.webcaib.rolsac.sia.idDepartamento");
    }
	
	public static String getCentroDirectivo() {
        return System.getProperty("es.webcaib.rolsac.sia.idCentroDirectivo");
    }
	
	public static String getUrl() {
        return System.getProperty("es.webcaib.rolsac.sia.url");
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
		StringBuffer buffer;
		BufferedReader reader = new BufferedReader(new InputStreamReader(clob.getAsciiStream()));
		read = null;
		buffer = new StringBuffer();
		
		while((read = reader.readLine()) != null ){
			buffer.append(read);
		}
		return buffer;
	}
}
