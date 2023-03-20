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

	/** Devuevle el texto **/
	public static final String getLiteralMantieneEstadoInterna(final boolean catalan) {
		final String idioma = catalan ? "ca" : "es";
		return getProperty("es.caib.rolsac.procServ.mantieneEstadoInterna." + idioma);
	}

	/** Info de email **/
	public static final boolean isEmailTest() {
		final String emailActivo = getProperty("es.caib.rolsac.procServ.email.test");
		boolean activo;
		if (emailActivo != null && emailActivo.equals("S")) {
			activo = true;
		} else {
			activo = false;
		}
		return activo;
	}

	public static final String getEmailTo() {
		return getProperty("es.caib.rolsac.procServ.email.test.to");
	}

	public static final String getEmailFrom() {
		return getProperty("es.caib.rolsac.procServ.email.test.from");
	}

	public static final String getEmailProcTitulo(final String nombre) {
		return getProperty("es.caib.rolsac.procServ.email.proc.titulo").replace("{0}", nombre);
	}

	public static final String getEmailProcContenido(final String usuario, final String mensaje,
			final String idEntidad) {
		return getProperty("es.caib.rolsac.procServ.email.proc.contenido").replace("{0}", usuario)
				.replace("{1}", mensaje).replace("{2}", idEntidad);
	}

	public static final String getEmailServTitulo(final String nombre) {
		return getProperty("es.caib.rolsac.procServ.email.srv.titulo").replace("{0}", nombre);
	}

	public static final String getEmailServContenido(final String usuario, final String mensaje,
			final String idEntidad) {
		return getProperty("es.caib.rolsac.procServ.email.srv.contenido").replace("{0}", usuario)
				.replace("{1}", mensaje).replace("{1}", idEntidad).replace("{2}", idEntidad);
	}

	/** Devuevle el texto cuando eres supervisor y lo revisas **/
	public static final String getLiteralFlujoActualizadoSupervisor(final boolean catalan) {
		final String idioma = catalan ? "ca" : "es";
		return getProperty("es.caib.rolsac.procServ.revisado." + idioma);
	}

	/** Devuevle el texto cuando eres supervisor y lo revisas **/
	public static final String getLiteralFlujoActualizadoSupervisorConErrores(final boolean catalan) {
		final String idioma = catalan ? "ca" : "es";
		return getProperty("es.caib.rolsac.procServ.revisadoConErrores." + idioma);
	}

	public static final String getLiteralValidacionActualizadoSupervisor(final boolean catalan, final String val1,
			final String val2) {
		final String idioma = catalan ? "ca" : "es";
		final String literal = getProperty("es.caib.rolsac.procServ.cambioValidacion." + idioma);
		return literal.replace("{0}", val1).replace("{1}", val2);
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

	public static String getUrlProcedimientos(final String id) {
		return getProperty("es.caib.rolsac.procServ.proc.url").replace("{0}", id);
	}

	public static String getUrlServicios(final String id) {
		return getProperty("es.caib.rolsac.procServ.serv.url").replace("{0}", id);
	}
	
	
	
	public enum EnumPublicoObjetivo 
	{
	    PERSONAS("personas"), 
	    EMPRESAS("empresas"), 
	    ADMINISTRACION("administracion"), 
	    INTERNO("interno");
	 
	    private String valor;
	 
	    EnumPublicoObjetivo(String valor) {
	        this.valor = valor;
	    }
	 
	    public String toString() {
	        return valor;
	    }
	}
	
	/**
	 * Funcion que retorna el identificador numerico (id de BBDD) del publico objetivo
	 * @param publico ('personas','administracion','empresas','interno')
	 * @return
	 */
	public static Long getpublicoObjetivo(final EnumPublicoObjetivo po) {
		try {
		return Long.parseLong(getProperty("es.caib.rolsac.publico.objetivo." + po.toString()));
			
		} catch (Exception e) {
			return (long) 0;
		}
	}
	
	
}
