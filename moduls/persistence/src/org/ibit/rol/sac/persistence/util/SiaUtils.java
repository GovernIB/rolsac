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
import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SiaUA;
import org.ibit.rol.sac.model.TraduccionNormativa;
import org.ibit.rol.sac.model.TraduccionProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionServicio;
import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.SiaPendienteProcesoDelegate;

public class SiaUtils {
	private static Log log = LogFactory.getLog(SiaUtils.class);

	public static final Integer SIAJOB_ESTADO_CREADO = 0;
	public static final Integer SIAJOB_ESTADO_EN_EJECUCION = 1;
	public static final Integer SIAJOB_ESTADO_ENVIADO = 2;
	public static final Integer SIAJOB_ESTADO_ENVIADO_CON_ERRORES = 3;
	public static final Integer SIAJOB_ESTADO_ERROR_GRAVE = -1;

	public static final Integer SIAPENDIENTE_ESTADO_CREADO = 0;
	public static final Integer SIAPENDIENTE_ESTADO_CORRECTO = 1;
	public static final Integer SIAPENDIENTE_ESTADO_INCORRECTO = -1;
	public static final Integer SIAPENDIENTE_ESTADO_NO_CUMPLE_DATOS = -2;

	public static final String SIAPENDIENTE_TIPO_PROCEDIMIENTO = "PROC";
	public static final String SIAPENDIENTE_TIPO_SERVICIO = "SERV";
	// Se quitan por los nuevos cambios: public static final String
	// SIAPENDIENTE_TIPO_UNIDAD_ADMINISTRATIVA = "UA";
	// Se quitan por los nuevos cambios: public static final String
	// SIAPENDIENTE_TIPO_NORMATIVA = "NORM";

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

	/*
	 * public static final String SIAPDT_ESTADO_INDEXAR = 0; public static final
	 * String SIAPDT_ESTADO_DESINDEXAR = 1;
	 */
	private static final String ERROR_MESSAGE = "Error obteniendo la propiedad ";

	public static final Integer SIAPENDIENTE_PROCEDIMIENTO_EXISTE = 1;
	public static final Integer SIAPENDIENTE_PROCEDIMIENTO_BORRADO = 0;
	public static final Integer SIAPENDIENTE_SERVICIO_EXISTE = 1;
	public static final Integer SIAPENDIENTE_SERVICIO_BORRADO = 0;

	/** El nombre cuando se crea el job de quartz. **/
	public static final String SIA_JOB_QUARTZ_NAME = "siaJob";

	/**
	 * Obtiene un long de una propiedad.
	 *
	 * @param property
	 * @return
	 */
	private static Long getLongFromProperty(final String property) {
		try {
			return Long.valueOf(System.getProperty(property).trim());
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + property, e);
			return null;
		}
	}

	/**
	 * btiene un entero de una propiedad.
	 *
	 * @param property
	 * @return
	 */
	private static Integer getIntFromProperty(final String property) {
		try {
			return Integer.valueOf(System.getProperty(property).trim());
		} catch (final Exception e) {
			log.error(ERROR_MESSAGE + property, e);
			return 0;
		}
	}

	/**
	 * Get tipo actuacion envio SIA.
	 *
	 * @return
	 */
	public static Integer getTipoActuacion() {
		return getIntFromProperty("es.caib.rolsac.sia.tipoActuacion");
	}

	/**
	 * Get tipologia envio SIA.
	 *
	 * @return
	 */
	public static Integer getTipologiaTramitacion(final boolean esInterno, final boolean esComun) {

		int res;
		if (esInterno) {
			if (esComun) {
				res = TIPOLOGIA_INTERNO_COMUN;
			} else {
				res = TIPOLOGIA_INTERNO_ESPECIFICO;
			}
		} else {
			if (esComun) {
				res = TIPOLOGIA_EXTERNO_COMUN;
			} else {
				res = TIPOLOGIA_EXTERNO_ESPECIFICO;
			}
		}

		return res;
	}

	/**
	 * Get administracion envio SIA.
	 *
	 * @return
	 */
	public static Long getAdministracion() {
		return getLongFromProperty("es.caib.rolsac.sia.administracion");
	}

	/**
	 * Get url envio SIA.
	 *
	 * @return
	 */
	public static String getUrlProcedimiento(final boolean esInterno) {
		if (esInterno) {
			return System.getProperty("es.caib.rolsac.sia.url.procedimiento.interno").trim();
		} else {
			return System.getProperty("es.caib.rolsac.sia.url").trim() + "tramite/";
		}

	}

	public static String getUrlServicio(final boolean esInterno) {
		if (esInterno) {
			return System.getProperty("es.caib.rolsac.sia.url.servicio.interno").trim();
		} else {
			return System.getProperty("es.caib.rolsac.sia.url").trim() + "servicio/";
		}

	}

	/**
	 * Get url envio SIA.
	 *
	 * @return
	 */
	public static String getUrlEnvio() {
		return System.getProperty("es.caib.rolsac.sia.url.envio").trim();
	}

	/**
	 * Get level SIA.
	 *
	 * @return
	 */
	public static Integer getLevelSIA() {
		final String level = System.getProperty("es.caib.rolsac.sia.centro.level");
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
	public static StringBuffer obtenerContenidoClob(final Clob clob) throws SQLException, IOException {
		String read;
		final StringBuffer buffer = new StringBuffer();
		if (clob != null) {

			final BufferedReader reader = new BufferedReader(new InputStreamReader(clob.getAsciiStream()));
			read = null;

			while ((read = reader.readLine()) != null) {
				buffer.append(read);
			}
		}
		return buffer;
	}

	/**
	 * Se encarga de crear una pendiente SIA según los datos de entrada.
	 *
	 * @param tipo
	 * @param idElemento
	 * @param existe
	 */
	public static void marcarIndexacionPendiente(final String tipo, final Long idElemento, final Integer existe,
			final String idSia, final ProcedimientoLocal procedimiento) throws DelegateException {
		final SiaPendienteProcesoDelegate siaPendienteProcesoDeletegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		final SiaPendiente siaPendiente = new SiaPendiente();
		siaPendiente.setEstado(SIAPENDIENTE_ESTADO_CREADO);
		siaPendiente.setFecAlta(new Date());
		siaPendiente.setIdElemento(idElemento);
		siaPendiente.setExiste(existe);
		siaPendiente.setTipo(tipo);
		final SiaUA siaUA = SiaUtils.obtenerSiaUA(procedimiento);
		siaPendiente.setSiaUA(siaUA);
		if (idSia != null && !idSia.isEmpty()) {
			siaPendiente.setIdSia(Long.valueOf(idSia));
		}
		siaPendienteProcesoDeletegate.generarSiaPendiente(siaPendiente, procedimiento, null);
	}

	/**
	 * Se encarga de crear una pendiente SIA según los datos de entrada.
	 *
	 * @param tipo
	 * @param idElemento
	 * @param existe
	 */
	public static void marcarIndexacionPendienteServicio(final String tipo, final Long idElemento, final Integer existe,
			final String idSia, final Servicio servicio) throws DelegateException {
		final SiaPendienteProcesoDelegate siaPendienteProcesoDeletegate = DelegateUtil.getSiaPendienteProcesoDelegate();
		final SiaPendiente siaPendiente = new SiaPendiente();
		siaPendiente.setEstado(SIAPENDIENTE_ESTADO_CREADO);
		siaPendiente.setFecAlta(new Date());
		siaPendiente.setIdElemento(idElemento);
		siaPendiente.setExiste(existe);
		siaPendiente.setTipo(tipo);
		final SiaUA siaUA = SiaUtils.obtenerSiaUA(servicio);
		siaPendiente.setSiaUA(siaUA);
		if (idSia != null && !idSia.isEmpty()) {
			siaPendiente.setIdSia(Long.valueOf(idSia));
		}
		siaPendienteProcesoDeletegate.generarSiaPendiente(siaPendiente, null, servicio);
	}

	/**
	 * Comprueba si está activo el envío hacia SIA.
	 *
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
	 * Comprueba si está activo el envío hacia SIA.
	 *
	 * @return
	 */
	public static boolean isActivoDebug() {
		final String activarEnvio = System.getProperty("es.caib.rolsac.sia.activarDebug");
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
	 *
	 * @return
	 */
	public static Integer getTiempoReintento() {
		return getIntFromProperty("es.caib.rolsac.sia.tiempo.reintento");
	}

	/**
	 * Comprueba si un procedimiento es enviable a SIA. Checks: - Es visible. - Es
	 * visible UA. - Tiene codigo DIR3. - Está en SiaUA.
	 *
	 * @param procedimiento
	 * @return
	 */
	public static SiaEnviableResultado isEnviable(final ProcedimientoLocal procedimiento) {
		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);
		final StringBuilder mensajeError = new StringBuilder();

		// Si el procedimiento que se pasa es nulo, tiene que salir.
		if (procedimiento == null) {
			resultado.setRespuesta("El procediment és nul.");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		// #427 Actualizado el isEnviable para que si una normativa tiene alguna
		// normativa con datos no validos, entonces no enviar pase lo que pase.
		if (procedimiento.getNormativas() != null) {
			for (final Normativa normativa : procedimiento.getNormativas()) {
				if (normativa.getDatosValidos() == null || !normativa.getDatosValidos()) {
					resultado.setRespuesta("Té alguna normativa no vàlida.");
					resultado.setIdCentro("");
					resultado.setNotificarSIA(false);
					return resultado;
				}
			}
		}

		// Es visible.
		final boolean esVisible = procedimiento.isVisible();
		if (!esVisible) {
			mensajeError.append("El procediment no és visible.");
		}

		// Es visible UA.
		final boolean isVisibleUA = isVisibleUA(procedimiento);
		if (!isVisibleUA) {
			mensajeError.append("La unitat de l'òrgan resolutori o d'alguns dels seus predecessors és no visible.");
		}

		// Tiene código centro.
		boolean tieneCodigoCentro;
		if (procedimiento.isComun()) {
			tieneCodigoCentro = true;
		} else {
			final String codigoIdCentro = obtenerCodigoIdCentro(procedimiento);
			if (codigoIdCentro == null) {
				tieneCodigoCentro = false;
				mensajeError.append("No té codi DIR ni l'òrgan resolutori ni predecessors.");
			} else {
				tieneCodigoCentro = true;
				resultado.setIdCentro(codigoIdCentro);
			}
		}

		// Comprobamos
		// Si cumple los 3 checks.
		// Si nunca ha estado en SIA --> es enviable a SIA como Alta (A)
		// Si esta de baja --> es enviable a SIA como Reactivación (R)
		// Si no.................. --> es enviable a SIA como modificación (M)
		// Si no cumple alguno de los 34 checks.
		// Si está de baja o nunca ha estado en SIA ---> NO es enviable
		// Si no ..... --> es enviable a SIA como baja.
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
				resultado.setNotificarSIA(true);
				resultado.setOperacion(SiaUtils.ESTADO_BAJA);
			}
		}

		return resultado;
	}

	/**
	 * Comprueba si un procedimiento es enviable a SIA. Checks: - Es visible. - Es
	 * visible UA. - Tiene codigo DIR3. - Está en SiaUA.
	 *
	 * @param servicio
	 * @return
	 */
	public static SiaEnviableResultado isEnviable(final Servicio servicio) {
		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);
		final StringBuilder mensajeError = new StringBuilder();

		// Si el procedimiento que se pasa es nulo, tiene que salir.
		if (servicio == null) {
			resultado.setRespuesta("El procediment és nul.");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		// Es visible.
		final boolean esVisible = servicio.isVisible();
		if (!esVisible) {
			mensajeError.append("El procediment no és visible.");
		}

		// Es visible UA.
		final boolean isVisibleUA = isVisibleUA(servicio);
		if (!isVisibleUA) {
			mensajeError.append("La unitat de l'òrgan resolutori o d'alguns dels seus predecessors és no visible.");
		}

		// Tiene código centro.
		boolean tieneCodigoCentro;
		if (servicio.isComun()) {
			tieneCodigoCentro = true;
		} else {
			final String codigoIdCentro = obtenerCodigoIdCentro(servicio);
			if (codigoIdCentro == null) {
				tieneCodigoCentro = false;
				mensajeError.append("No té codi DIR ni l'òrgan resolutori ni predecessors.");
			} else {
				tieneCodigoCentro = true;
				resultado.setIdCentro(codigoIdCentro);
			}
		}
		// Comprobamos
		// Si cumple los 3 checks.
		// Si nunca ha estado en SIA --> es enviable a SIA como Alta (A)
		// Si esta de baja --> es enviable a SIA como Reactivación (R)
		// Si no.................. --> es enviable a SIA como modificación (M)
		// Si no cumple alguno de los 34 checks.
		// Si está de baja o nunca ha estado en SIA ---> NO es enviable
		// Si no ..... --> es enviable a SIA como baja.
		if (esVisible && tieneCodigoCentro && isVisibleUA) {
			resultado.setNotificarSIA(true);
			if (servicio.getEstadoSIA() == null) {
				resultado.setOperacion(SiaUtils.ESTADO_ALTA);
			} else {
				if (SiaUtils.ESTADO_BAJA.equals(servicio.getEstadoSIA())) {
					resultado.setOperacion(SiaUtils.ESTADO_REACTIVACION);
				} else {
					resultado.setOperacion(SiaUtils.ESTADO_MODIFICACION);
				}
			}
		} else {
			if (servicio.getEstadoSIA() == null || SiaUtils.ESTADO_BAJA.equals(servicio.getEstadoSIA())) {
				resultado.setNotificarSIA(false);
				resultado.setRespuesta(mensajeError.toString());
			} else {
				// Sin código SiaUA no se puede enviar
				resultado.setNotificarSIA(true);
				resultado.setOperacion(SiaUtils.ESTADO_BAJA);
			}
		}

		return resultado;
	}

	/**
	 * Comprueba si le falta algún dato. Condiciones: - Tiene materias (se comprueba
	 * si está activo). - Tiene normativas y uno de tipo SIA (se comprueba si está
	 * activo). - Tiene descripción. - Tiene resumen. - Depende de una UA asociada a
	 * una entidad raiz. - No está asociado directamente a la entidad raíz.
	 *
	 * @param procedimiento
	 * @param siaEnviableResultado
	 * @param activo
	 *            Indica si se envia el servicio como activo o no activo (el botón
	 *            para enviar a SIA sin estar visible)
	 * @return
	 */
	public static SiaCumpleDatos cumpleDatos(final ProcedimientoLocal procedimiento,
			final SiaEnviableResultado siaEnviableResultado, final boolean activo) {
		final SiaCumpleDatos resultado = new SiaCumpleDatos(false);
		final StringBuffer mensajeError = new StringBuffer();

		if (procedimiento == null) {
			resultado.setRespuesta("El procediment està nul.");
			resultado.setCumpleDatos(false);
			return resultado;
		}

		boolean tieneSiaUA;
		final SiaUA siaUA = obtenerSiaUA(procedimiento);
		if (siaUA == null) {
			tieneSiaUA = false;
			mensajeError.append("El procediment no té associat a una entitat arrel.");
		} else {
			tieneSiaUA = true;
			resultado.setSiaUA(siaUA);
		}

		boolean noAsociadoSiaUA = true;
		// No se comprueba si está asociado a la Raíz si es comun
		if (!procedimiento.isComun() && tieneSiaUA) {
			final String codigoDir3IdCentro = obtenerCodigoIdCentro(procedimiento);
			final String codigoDir3SiaUA = siaUA.getUnidadAdministrativa().getCodigoDIR3();
			if (codigoDir3SiaUA.equals(codigoDir3IdCentro)) {
				mensajeError.append("El procedimiento esta asociado directamente a la entidad raiz.");
				noAsociadoSiaUA = false;
			}
		}

		boolean tieneNombre, tieneResumen, tieneMaterias, tieneNormativas, encontradoTipo;
		if (siaEnviableResultado.getOperacion() != null
				&& SiaUtils.ESTADO_BAJA.equals(siaEnviableResultado.getOperacion())) {
			// En caso de baja, no hace falta comprobar ni normativas, ni materia, ni si
			// tiene tipo, ni nombre ni resumen.
			// Eso si, sin siaUA, es imposible enviar una baja.
			tieneMaterias = true;
			tieneNormativas = true;
			encontradoTipo = true;
			tieneResumen = true;
			tieneNombre = true;
		} else {

			final String nombre = getNombreProcedimiento(procedimiento);

			if (StringUtils.isBlank(nombre)) {
				mensajeError.append("El procediment no té títol.");
				tieneNombre = false;
			} else {
				tieneNombre = true;
				resultado.setNombre(nombre);
			}

			final String resumen = getResumenProcedimiento(procedimiento);
			if (StringUtils.isBlank(resumen)) {
				mensajeError.append("El procediment no té resum.");
				tieneResumen = false;
			} else {
				tieneResumen = true;
				resultado.setResumen(resumen);
			}

			tieneMaterias = procedimiento.getMaterias().size() > 0;
			if (!tieneMaterias && activo) {
				mensajeError.append("No té matèries.");
			}

			tieneNormativas = procedimiento.getNormativas().size() > 0;
			if (!tieneNormativas && activo) {
				mensajeError.append("No té normatives.");
			}

			encontradoTipo = false;
			if (procedimiento.getNormativas().size() > 0 && activo) {
				for (final Normativa norm : procedimiento.getNormativas()) {
					if (norm != null && norm.isVisible() && norm.getTipo() != null
							&& norm.getTipo().getTipoSia() != null) {
						encontradoTipo = true;
					}
				}
			}

			if (!encontradoTipo) {
				mensajeError.append("Cap de les normatives es visible o té associat un tipus sia.");
			}
		}

		/** Si cumple todos los datos ok, sino incrustamos el mensaje de error. **/
		if (tieneMaterias && tieneNormativas && encontradoTipo && tieneNombre && tieneResumen && tieneSiaUA
				&& noAsociadoSiaUA) {
			resultado.setCumpleDatos(true);
		} else {
			resultado.setCumpleDatos(false);
			resultado.setRespuesta(mensajeError.toString());
		}

		return resultado;
	}

	/**
	 * Comprueba si le falta algún dato. Condiciones: - Tiene materias. - Tiene
	 * normativas y uno de tipo SIA (se comprueba si está activo). - Tiene
	 * descripción. - Tiene resumen. - Depende de una UA asociada a una entidad
	 * raiz. - No está asociado directamente a la entidad raíz.
	 *
	 * @param servicio
	 * @param siaEnviableResultado
	 * @param activo
	 *            Indica si se envia el servicio como activo o no activo (el botón
	 *            para enviar a SIA sin estar visible)
	 *
	 * @return
	 */
	public static SiaCumpleDatos cumpleDatos(final Servicio servicio, final SiaEnviableResultado siaEnviableResultado,
			final boolean activo) {
		final SiaCumpleDatos resultado = new SiaCumpleDatos(false);
		final StringBuffer mensajeError = new StringBuffer();

		if (servicio == null) {
			resultado.setRespuesta("El servei està nul.");
			resultado.setCumpleDatos(false);
			return resultado;
		}

		boolean tieneSiaUA;
		final SiaUA siaUA = obtenerSiaUA(servicio);
		if (siaUA == null) {
			tieneSiaUA = false;
			mensajeError.append("El servei no té associat a una entitat arrel.");
		} else {
			tieneSiaUA = true;
			resultado.setSiaUA(siaUA);
		}

		boolean noAsociadoSiaUA = true;
		// No se comprueba si está asociado a la Raíz si es comun
		if (!servicio.isComun() && tieneSiaUA) {
			final String codigoDir3IdCentro = obtenerCodigoIdCentro(servicio);
			final String codigoDir3SiaUA = siaUA.getUnidadAdministrativa().getCodigoDIR3();
			if (codigoDir3SiaUA.equals(codigoDir3IdCentro)) {
				mensajeError.append("El servicio esta asociado directamente a la entidad raiz.");
				noAsociadoSiaUA = false;
			}
		}

		boolean tieneNombre, tieneResumen, tieneMaterias, tieneNormativas = true, encontradoTipo;
		if (siaEnviableResultado.getOperacion() != null
				&& SiaUtils.ESTADO_BAJA.equals(siaEnviableResultado.getOperacion())) {
			// En caso de baja, no hace falta comprobar ni normativas, ni materia, ni si
			// tiene tipo, ni nombre ni resumen.
			// Eso si, sin siaUA, es imposible enviar una baja.
			tieneMaterias = true;
			tieneNormativas = true;
			encontradoTipo = true;
			tieneResumen = true;
			tieneNombre = true;
		} else {

			if (servicio.getMaterias() == null) {
				tieneMaterias = false;
			} else {
				tieneMaterias = servicio.getMaterias().size() > 0;
			}
			if (!tieneMaterias && activo) { // Solo mirar si está activo
				mensajeError.append("No té matèries.");
			}

			encontradoTipo = true; // En los servicios no es obligatorio.
			/*
			 * boolean encontradoTipo = false; if (servicio.getNormativas().size() > 0) {
			 * for (Normativa norm : servicio.getNormativas()) { if (norm != null &&
			 * norm.isVisible() &&norm.getTipo() != null && norm.getTipo().getTipoSia() !=
			 * null) { encontradoTipo = true; } } }
			 */

			if (!encontradoTipo) {
				mensajeError.append("Cap de les normatives es visible o té associat un tipus sia.");
			}

			final String nombre = getNombreServicio(servicio);
			if (StringUtils.isBlank(nombre)) {
				mensajeError.append("El servicio no té títol.");
				tieneNombre = false;
			} else {
				tieneNombre = true;
				resultado.setNombre(nombre);
			}

			final String resumen = getResumenServicio(servicio);
			if (StringUtils.isBlank(resumen)) {
				mensajeError.append("El servicio no té resum.");
				tieneResumen = false;
			} else {
				tieneResumen = true;
				resultado.setResumen(resumen);
			}
		}

		/** Si cumple todos los datos ok, sino incrustamos el mensaje de error. **/
		if (tieneMaterias && tieneNormativas && encontradoTipo && tieneNombre && tieneResumen && tieneSiaUA
				&& noAsociadoSiaUA) {
			resultado.setCumpleDatos(true);
		} else {
			resultado.setCumpleDatos(false);
			resultado.setRespuesta(mensajeError.toString());
		}

		return resultado;
	}

	/**
	 * Compruebo si todas las UAs son visible.
	 *
	 * @param procedimiento
	 * @return
	 */
	private static boolean isVisibleUA(final ProcedimientoLocal procedimiento) {

		boolean visible = true;
		if (procedimiento == null || procedimiento.getOrganResolutori() == null) {
			visible = false;
		} else {
			if (!procedimiento.getOrganResolutori().getValidacion().equals(Validacion.PUBLICA)) {

				visible = false;

			} else {
				// Recorremos sus predecesores
				for (final Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
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
	 * Compruebo si todas las UAs son visible.
	 *
	 * @param servicio
	 * @return
	 */
	private static boolean isVisibleUA(final Servicio servicio) {

		boolean visible = true;
		if (servicio == null || servicio.getOrganoInstructor() == null) {
			visible = false;
		} else {
			if (!servicio.getOrganoInstructor().getValidacion().equals(Validacion.PUBLICA)) {

				visible = false;

			} else {
				// Recorremos sus predecesores
				for (final Object oua : servicio.getOrganoInstructor().getPredecesores()) {
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
	private static String obtenerCodigoIdCentro(final ProcedimientoLocal procedimiento) {

		String codigoIdCentro = null;

		if (procedimiento != null && procedimiento.getOrganResolutori() != null) {

			if (procedimiento.getOrganResolutori().getCodigoDIR3() == null) {

				// Recorremos sus predecesores
				for (int i = procedimiento.getOrganResolutori().getPredecesores().size() - 1; i >= 0; i--) {
					// for(Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
					final UnidadAdministrativa ua = (UnidadAdministrativa) procedimiento.getOrganResolutori()
							.getPredecesores().get(i);
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
	 * @param servicio
	 * @return
	 */
	private static String obtenerCodigoIdCentro(final Servicio servicio) {

		String codigoIdCentro = null;

		if (servicio != null && servicio.getOrganoInstructor() != null) {

			if (servicio.getOrganoInstructor().getCodigoDIR3() == null) {

				// Recorremos sus predecesores
				for (int i = servicio.getOrganoInstructor().getPredecesores().size() - 1; i >= 0; i--) {
					// for(Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
					final UnidadAdministrativa ua = (UnidadAdministrativa) servicio.getOrganoInstructor()
							.getPredecesores().get(i);
					if (ua.getCodigoDIR3() != null) {
						codigoIdCentro = ua.getCodigoDIR3();
						break;
					}
				}

			} else {
				codigoIdCentro = servicio.getOrganoInstructor().getCodigoDIR3();
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
	private static SiaUA obtenerSiaUA(final ProcedimientoLocal procedimiento) {

		SiaUA siaUA;
		try {
			if (procedimiento == null || procedimiento.getOrganResolutori() == null) {
				siaUA = null;
			} else {

				final SiaPendienteProcesoDelegate siaPendienteProceso = DelegateUtil.getSiaPendienteProcesoDelegate();
				siaUA = siaPendienteProceso.obtenerSiaUA(procedimiento.getOrganResolutori());

				if (siaUA == null) {
					// Recorremos sus predecesores (este predecesor va al reves, empieza desde más
					// arriba hacia abajo pero nos conviene para encontrar antes a la raiz)
					for (final Object oua : procedimiento.getOrganResolutori().getPredecesores()) {
						final UnidadAdministrativa ua = (UnidadAdministrativa) oua;
						siaUA = siaPendienteProceso.obtenerSiaUA(ua);
						if (siaUA != null) {
							break;
						}
					}
				}
			}
		} catch (final Exception exception) {
			log.error("Error obteniendo la siaUA", exception);
			siaUA = null;
		}

		return siaUA;
	}

	/**
	 * Devuelve el código DIR3 de la UA, si es nulo, es que ninguno lo tiene.
	 *
	 * @param servicio
	 * @return
	 */
	private static SiaUA obtenerSiaUA(final Servicio servicio) {

		SiaUA siaUA;
		try {
			if (servicio == null || servicio.getOrganoInstructor() == null) {
				siaUA = null;
			} else {

				final SiaPendienteProcesoDelegate siaPendienteProceso = DelegateUtil.getSiaPendienteProcesoDelegate();
				siaUA = siaPendienteProceso.obtenerSiaUA(servicio.getOrganoInstructor());

				if (siaUA == null) {
					// Recorremos sus predecesores
					for (final Object oua : servicio.getOrganoInstructor().getPredecesores()) {
						final UnidadAdministrativa ua = (UnidadAdministrativa) oua;
						siaUA = siaPendienteProceso.obtenerSiaUA(ua);
						if (siaUA != null) {
							break;
						}
					}
				}
			}
		} catch (final Exception exception) {
			log.error("Error obteniendo la siaUA", exception);
			siaUA = null;
		}

		return siaUA;
	}

	/**
	 * Compara 2 procedimientos y miran si son la misma SiaUA.
	 *
	 * @param procedimiento
	 * @return
	 */
	public static boolean mismaSiaUA(final ProcedimientoLocal procedimiento, final ProcedimientoLocal procedimiento2) {
		boolean retorno;
		final SiaUA siaUA1 = obtenerSiaUA(procedimiento);
		final SiaUA siaUA2 = obtenerSiaUA(procedimiento2);
		if (siaUA1 == null && siaUA2 == null) { // Si los 2 son nulos, son iguales
			retorno = true;
		} else if (siaUA2 == null || siaUA1 == null) { // Si uno de los 2 es nulo, son distintos (el otro seguro que no
														// es nulo)
			retorno = false;
		} else {
			if (siaUA1.getId().compareTo(siaUA2.getId()) == 0) {
				retorno = true;
			} else {
				retorno = false;
			}
		}

		return retorno;
	}

	/**
	 * Obtiene el resumen del procedimiento.
	 *
	 * @param procedimiento
	 * @return
	 */
	public static String getResumenProcedimiento(final ProcedimientoLocal procedimiento) {
		final TraduccionProcedimientoLocal tradEs = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		final TraduccionProcedimientoLocal tradCa = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
		String resumen = null;
		if (tradEs != null && StringUtils.isNotBlank(tradEs.getResumen())) {
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
	 * Obtiene el resumen del servicio.
	 *
	 * @param servicio
	 * @return
	 */
	public static String getResumenServicio(final Servicio servicio) {
		final TraduccionServicio tradEs = (TraduccionServicio) servicio.getTraduccion("es");
		final TraduccionServicio tradCa = (TraduccionServicio) servicio.getTraduccion("ca");
		String resumen = null;
		if (tradEs != null && StringUtils.isNotBlank(tradEs.getObjeto())) {
			resumen = tradEs.getObjeto();
		} else if (tradCa != null) {
			resumen = tradCa.getObjeto();
		}
		if (resumen != null && resumen.length() >= 4000) {
			resumen = resumen.substring(0, 3999);
		}
		return resumen;
	}

	/**
	 * Calcula el nombre del procedimiento.
	 *
	 * @param procedimiento
	 * @return
	 */
	public static String getNombreProcedimiento(final ProcedimientoLocal procedimiento) {
		final TraduccionProcedimientoLocal tradEs = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("es");
		final TraduccionProcedimientoLocal tradCa = (TraduccionProcedimientoLocal) procedimiento.getTraduccion("ca");
		String nombre = null;
		if (tradEs != null && StringUtils.isNotBlank(tradEs.getNombre())) {
			nombre = tradEs.getNombre();
		} else if (tradCa != null) {
			nombre = tradCa.getNombre();
		}
		return nombre;
	}

	/**
	 * Calcula el nombre del procedimiento.
	 *
	 * @param servicio
	 * @return
	 */
	public static String getNombreServicio(final Servicio servicio) {
		final TraduccionServicio tradEs = (TraduccionServicio) servicio.getTraduccion("es");
		final TraduccionServicio tradCa = (TraduccionServicio) servicio.getTraduccion("ca");
		String nombre = null;
		if (tradEs != null && StringUtils.isNotBlank(tradEs.getNombre())) {
			nombre = tradEs.getNombre();
		} else if (tradCa != null) {
			nombre = tradCa.getNombre();
		}
		return nombre;
	}

	/**
	 * Calcula el nombre de la normativa.
	 *
	 * @param normativa
	 * @return
	 */
	public static String getNombreNormativa(final Normativa normativa) {
		final TraduccionNormativa tradEs = (TraduccionNormativa) normativa.getTraduccion("es");
		final TraduccionNormativa tradCa = (TraduccionNormativa) normativa.getTraduccion("ca");
		String nombre = null;
		if (tradEs != null && StringUtils.isNotBlank(tradEs.getTitulo())) {
			nombre = tradEs.getTitulo();
		} else if (tradCa != null) {
			nombre = tradCa.getTitulo();
		}
		return nombre;
	}

	/**
	 * Comprueba si es enviable en modo No Activo hacia SIA. Se comprueba lo básico,
	 * es decir, que tiene SiaUA, DIR3 y no coinciden ambos.
	 *
	 * @param procedimiento
	 * @return
	 */
	public static SiaEnviableResultado isEnviableNoActivo(final ProcedimientoLocal procedimiento) {

		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);

		final boolean visibleUA = SiaUtils.isVisibleUA(procedimiento);
		if (!visibleUA) {
			resultado.setRespuesta("txt.sia.error.raiz.novisible");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final String codigoDir3IdCentro = SiaUtils.obtenerCodigoIdCentro(procedimiento);
		if (codigoDir3IdCentro == null || codigoDir3IdCentro.isEmpty()) {
			resultado.setRespuesta("txt.sia.error.centre.nodir3");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final SiaUA siaUA = SiaUtils.obtenerSiaUA(procedimiento);
		if (siaUA == null) {
			resultado.setRespuesta("txt.sia.error.raiz.noexisten");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final String codigoDir3SiaUA = siaUA.getUnidadAdministrativa().getCodigoDIR3();
		if (!procedimiento.isComun() && codigoDir3SiaUA.equals(codigoDir3IdCentro)) {
			resultado.setRespuesta("txt.sia.error.proc.raizdirecta");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		if (procedimiento.getCodigoSIA() == null || procedimiento.getCodigoSIA().isEmpty()) {
			resultado.setOperacion(SiaUtils.ESTADO_ALTA);
		} else {
			resultado.setOperacion(SiaUtils.ESTADO_MODIFICACION);
		}

		resultado.setNotificarSIA(true);
		return resultado;

	}

	/**
	 * Comprueba si es enviable en modo No Activo hacia SIA. Se comprueba lo básico,
	 * es decir, que tiene SiaUA, DIR3 y no coinciden ambos.
	 *
	 * @param servicio
	 * @return
	 */
	public static SiaEnviableResultado isEnviableNoActivo(final Servicio servicio) {

		final SiaEnviableResultado resultado = new SiaEnviableResultado(false);
		final boolean visibleUA = SiaUtils.isVisibleUA(servicio);
		if (!visibleUA) {
			resultado.setRespuesta("txt.sia.error.raiz.novisible");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final String codigoDir3IdCentro = SiaUtils.obtenerCodigoIdCentro(servicio);
		if (codigoDir3IdCentro == null || codigoDir3IdCentro.isEmpty()) {
			resultado.setRespuesta("txt.sia.error.centre.nodir3");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final SiaUA siaUA = SiaUtils.obtenerSiaUA(servicio);
		if (siaUA == null) {
			resultado.setRespuesta("txt.sia.error.raiz.noexisten");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		final String codigoDir3SiaUA = siaUA.getUnidadAdministrativa().getCodigoDIR3();
		if (!servicio.isComun() && codigoDir3SiaUA.equals(codigoDir3IdCentro)) {
			resultado.setRespuesta("txt.sia.error.serv.raizdirecta");
			resultado.setIdCentro("");
			resultado.setNotificarSIA(false);
			return resultado;
		}

		if (servicio.getCodigoSIA() == null || servicio.getCodigoSIA().isEmpty()) {
			resultado.setOperacion(SiaUtils.ESTADO_ALTA);
		} else {
			resultado.setOperacion(SiaUtils.ESTADO_MODIFICACION);
		}

		resultado.setNotificarSIA(true);
		return resultado;

	}

	public static boolean isActivoSiaComunes() {
		boolean activo = false;
		try {
			final Boolean comunActivo = Boolean.valueOf(System.getProperty("es.caib.rolsac.sia.activarenviocomun"));
			if (comunActivo != null && comunActivo) {
				activo = true;
			}
		} catch (final Exception e) {
			log.error("Error en comunes : " + e.getMessage(), e);
		}
		return activo;
	}
}
