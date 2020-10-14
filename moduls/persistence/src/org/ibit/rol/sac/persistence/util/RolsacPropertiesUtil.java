package org.ibit.rol.sac.persistence.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	 * funcion que retorna el email del administrador de la UA
	 * si codigoUA =null  retorna el email por defecto (es.caib.rolsac.defecto.correo.normativas si existe) o vacio si no existe
	 *
	 * @return
	 */
	public static String getEmailAdmin(String codigoUA) {
		String res = "";
		String cua=codigoUA==null?"defecto":codigoUA;
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
