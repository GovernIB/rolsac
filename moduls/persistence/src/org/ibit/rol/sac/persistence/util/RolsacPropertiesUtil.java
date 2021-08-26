package org.ibit.rol.sac.persistence.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Validacion;

public class RolsacPropertiesUtil {

	/** Constructor vacio. **/
	private RolsacPropertiesUtil() {
		// Constructor vacio
	}

	/** LOG. **/
	private static Log log = LogFactory.getLog(RolsacPropertiesUtil.class);

	/** Mensaje de error. **/
	private static final String ERROR_MESSAGE = "Error obteniendo la propiedad ";

	/** Devuelve un valor de una propiedad. **/
	private static String getProperty(final String property) {
		try {
			return System.getProperty(property);
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + property, e);
			return null;
		}
	}

	/** Devuelve un valor de una propiedad como entero. **/
	private static int getIntFromProperty(final String property) {
		try {
			return Integer.parseInt(System.getProperty(property));
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + property, e);
			return 0;
		}
	}

	/** Devuelve si hay control de proporciones. **/
	public static final boolean getControlProporciones() {
		final String proporciones = getProperty("es.caib.rolsac.fitxa.control.proporcions");
		boolean activo;
		if (proporciones != null && proporciones.equals("S")) {
			activo = true;
		} else {
			activo = false;
		}
		return activo;
	}

	/** Devuelve el texto para finalidad. **/
	public static final String getLopdFinalidad(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.lopd.finalidad.ca");
		} else {
			texto = getProperty("es.caib.rolsac.lopd.finalidad.es");
		}
		return texto;
	}

	/** Devuelve el texto para destinatario. **/
	public static final String getLopdDestinatario(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.lopd.destinatario.ca");
		} else {
			texto = getProperty("es.caib.rolsac.lopd.destinatario.es");
		}
		return texto;
	}

	/** Devuelve el texto para responsable de procs/servicios comunes. **/
	public static final String getLopdResponsableComun(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.lopd.responsable.comun.ca");
		} else {
			texto = getProperty("es.caib.rolsac.lopd.responsable.comun.es");
		}
		return texto;
	}

	/** Devuelve el texto para responsable de procs/servicios comunes. **/
	public static final String getLopdPlantilla(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.lopd.plantilla.ca");
		} else {
			texto = getProperty("es.caib.rolsac.lopd.plantilla.es");
		}
		return texto;
	}

	/** Devuelve el texto para derechos. **/
	public static final String getLopdDerechos(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.lopd.derechos.ca");
		} else {
			texto = getProperty("es.caib.rolsac.lopd.derechos.es");
		}
		return texto;
	}

	/** Devuelve el texto de UA para procedimientos/servicios comunes. **/
	public static final String getUAComun(final boolean catalan) {
		String texto;
		if (catalan) {
			texto = getProperty("es.caib.rolsac.comun.ua.ca");
		} else {
			texto = getProperty("es.caib.rolsac.comun.ua.es");
		}
		return texto;
	}

	/** Devuelve el texto segun la acción requerida. **/
	public static final String getLiteralFlujoAccion(final Long accion, final boolean catalan) {
		String literal;
		final String idioma = catalan ? "ca" : "es";
		if (accion.compareTo(Validacion.ACCION_PUBLICAR) == 0) {
			literal = "es.caib.rolsac.procServ.accion.publicar." + idioma;
		} else if (accion.compareTo(Validacion.ACCION_REPUBLICAR) == 0) {
			literal = "es.caib.rolsac.procServ.accion.republicar." + idioma;
		} else if (accion.compareTo(Validacion.ACCION_ELIMINAR) == 0) {
			literal = "es.caib.rolsac.procServ.accion.eliminar." + idioma;
		} else if (accion.compareTo(Validacion.ACCION_CERRAR) == 0) {
			literal = "es.caib.rolsac.procServ.accion.cerrar." + idioma;
		} else {
			literal = null;
		}
		return getProperty(literal);
	}

	/** Devuevle el texto **/
	public static final String getLiteralFlujoEstado(final Integer estado, final boolean catalan) {
		String literal;
		final String idioma = catalan ? "ca" : "es";
		if (estado.compareTo(Validacion.INTERNA) == 0) {
			literal = "es.caib.rolsac.procServ.estado.interno." + idioma;
		} else if (estado.compareTo(Validacion.PUBLICA) == 0) {
			literal = "es.caib.rolsac.procServ.estado.publico." + idioma;
		} else if (estado.compareTo(Validacion.RESERVA) == 0) {
			literal = "es.caib.rolsac.procServ.estado.reserva." + idioma;
		} else if (estado.compareTo(Validacion.BAJA) == 0) {
			literal = "es.caib.rolsac.procServ.estado.baja." + idioma;
		} else {
			literal = null;
		}
		return getProperty(literal);
	}

	/** Los campos de email **/
	public static final String getEmailUser() {
		return getProperty("es.caib.rolsac.procServ.email.user");
	}

	public static final String getEmailPass() {
		return getProperty("es.caib.rolsac.procServ.email.pass");
	}

	public static final String getEmailSmtp() {
		return getProperty("es.caib.rolsac.procServ.email.stmp");
	}

	public static final String getEmailPort() {
		return getProperty("es.caib.rolsac.procServ.email.port");
	}

	/** Devuevle el texto cuando eres supervisor y lo revisas **/
	public static final String getLiteralFlujoActualizadoSupervisor(final boolean catalan) {
		final String idioma = catalan ? "ca" : "es";
		return getProperty("es.caib.rolsac.procServ.revisado." + idioma);
	}

	public static final int getAltoIcono() {
		return getIntFromProperty("es.caib.rolsac.fitxa.icona.altura");
	}

	public static final int getAnchoIcono() {
		return getIntFromProperty("es.caib.rolsac.fitxa.icona.amplada");
	}

	public static final int getAltoBanner() {
		return getIntFromProperty("es.caib.rolsac.fitxa.banner.altura");
	}

	public static final int getAnchoBanner() {
		return getIntFromProperty("es.caib.rolsac.fitxa.banner.amplada");
	}

	public static final int getAltoImagen() {
		return getIntFromProperty("es.caib.rolsac.fitxa.imatge.altura");
	}

	public static final int getAnchoImagen() {
		return getIntFromProperty("es.caib.rolsac.fitxa.imatge.amplada");
	}

	/**
	 * funcion que retorna el código del público objetivo interno
	 *
	 * @return
	 */
	public static String getPropiedadPOInternoUrlProc() {
		String res = "";
		try {
			res = System.getProperty("es.caib.rolsac.procser.interno.url.procedimientos");
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + "es.caib.rolsac.procser.interno.url.procedimientos", e);
			res = "";
		}
		return res == null ? "" : res;
	}

	/**
	 * funcion que retorna el código del público objetivo interno
	 *
	 * @return
	 */
	public static String getPropiedadPOInternoUrlSer() {
		String res = "";

		try {
			res = System.getProperty("es.caib.rolsac.procser.interno.url.servicios");
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + "es.caib.rolsac.procser.interno.url.servicios", e);
			res = "";
		}
		return res == null ? "" : res;
	}

	/**
	 * funcion que retorna el email del administrador de la UA si codigoUA =null
	 * retorna el email por defecto (es.caib.rolsac.defecto.correo.normativas si
	 * existe) o vacio si no existe
	 *
	 * @return
	 */
	public static String getEmailAdmin(final String codigoUA) {
		String res = "";
		final String cua = codigoUA == null ? "defecto" : codigoUA;
		String property = "es.caib.rolsac.codigoUA.correo.normativas";
		property = property.replace("codigoUA", cua);

		try {
			res = getProperty(property);
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + property, e);
			res = "";
		}
		return res == null ? "" : res;
	}
}
