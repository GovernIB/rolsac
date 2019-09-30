package org.ibit.rol.sac.model.filtro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * filtro generico para aplanar los filtros del api rest
 *
 * @author Indra
 *
 */

public class FiltroGenerico implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> filtros;
	private Map<String, String> columnasOrdenar;

	public static final String LANG_DEFECTO = getPropiedadlangDefecto("ca");
	public static final int SIZE_DEFECTO = getPropiedadIntegerPositivo("es.caib.rolsac.api.rest.pageSize", 30);
	public static final int PAGE_DEFECTO = getPropiedadIntegerPositivo("es.caib.rolsac.api.rest.pageNumber", 1);

	private final String ASCENDENTE = "ASC";
	private final String DESCENDENTE = "DESC";

	public final static String FILTRO_GENERICO_LANG = "lang";
	public final static String FILTRO_GENERICO_SIZE = "size";
	public final static String FILTRO_GENERICO_PAGE = "page";

	public final static String FILTRO_GENERICO_ID = "id";

	public final static String FILTRO_UA_CODIGO_UA_PADRE = "codigoUAPadre";
	public final static String FILTRO_UA_VALIDACION = "validacion";
	public final static String FILTRO_UA_CODIGO_SECCION = "codigoSeccion";
	public final static String FILTRO_UA_CODIGO_NORMATIVA = "codigoNormativa";
	public static final String FILTRO_UA_CODIGO_DIR3 = "codigoDir3";

	public static final String FILTRO_AFV_PUBLICO = "publico";

	public static final String FILTRO_DOC_ARCHIVO = "archivo";
	public static final String FILTRO_DOC_FICHA = "ficha";
	public static final String FILTRO_DOC_PROCEDIMIENTO = "procedimiento";

	public static final String FILTRO_DOC_TRAMITE_TRAMITE = "tramite";
	public static final String FILTRO_DOC_TRAMITE_TIPO_DOCUMENTO = "tipoDocumento";

	public static final String FILTRO_DOC_NORMATIVA_NORMATIVA = "normativa";

	public static final String FILTRO_EDIFICIO_UA = "codigoUA";

	public static final String FILTRO_ENLACES_FICHA = "codigoFicha";

	public static final String FILTRO_HV_AGRUPACION_HV = "codigoAgrupacionHechoVital";
	public static final String FILTRO_HV_FICHA = "ficha";

	public static final String FILTRO_FICHAS_ACTIVO = "activo";
	public static final String FILTRO_FICHAS_SECCION = "codigoSeccion";
	public static final String FILTRO_FICHAS_UA = "codigoUA";
	public static final String FILTRO_FICHAS_VALIDACION = "validacion";
	public static final String FILTRO_FICHAS_FECHA_PUBLICACION = "fechaPublicacion";
	public static final String FILTRO_FICHAS_HECHOS_VITALES = "codigoHechosVitales";
	public static final String FILTRO_FICHAS_PUBLICO_OBJETIVO = "codigoPublicoObjetivo";

	public static final String FILTRO_FICHASUA_SECCION = "codigoSeccion";
	public static final String FILTRO_FICHASUA_UA = "codigoUA";
	public static final String FILTRO_FICHASUA_FICHA = "codigoFicha";

	public static final String FILTRO_MATERIAS_AGRUPACIONMATERIAS = "codigoAgrupacionMaterias";
	public static final String FILTRO_MATERIAS_UA = "codigoUA";
	public static final String FILTRO_MATERIAS_FICHA = "codigoFicha";

	public static final String FILTRO_PERSONAL_UA = "codigoUA";

	public static final String FILTRO_PUBLICO_LISTA_CODIGOS = "codigosPO";

	public static final String FILTRO_SECCIONES_UA = "codigoUA";
	public static final String FILTRO_SECCIONES_CODIGO_ESTANDAR = "codigoEstandar";

	public static final String FILTRO_USUARIOS_UA = "codigoUA";

	public static final String FILTRO_NORMATIVA_UA = "codigoUA";
	public static final String FILTRO_NORMATIVA_FECHABOLETIN = "fechaBoletin";
	public static final String FILTRO_NORMATIVA_PROCEDIMIENTO = "codigoProcedimiento";
	public static final String FILTRO_NORMATIVA_SERVICIO = "codigoServicio";
	public static final String FILTRO_NORMATIVA_NUMERO_NORMA = "numeroNorma";
	public static final String FILTRO_NORMATIVA_FECHA_PUBLICACION = "fechaPublicacion";
	public static final String FILTRO_NORMATIVA_TIPO_PUBLICACION = "tipoPublicacion";
	public static final String FILTRO_NORMATIVA_TEXTO = "texto";

	public static final String FILTRO_PROCEDIMIENTO_UA = "codigoUA";
	public static final String FILTRO_PROCEDIMIENTO_UA_DIR3 = "codigoUADir3";
	public static final String FILTRO_PROCEDIMIENTO_UA_DESCENDIENTES = "buscarEnDescendientesUA";
	public static final String FILTRO_PROCEDIMIENTO_ACTIVO = "activo";
	public static final String FILTRO_PROCEDIMIENTO_ESTADO_UA = "estadoUA";
	public static final String FILTRO_PROCEDIMIENTO_AGRUPACION_HECHO_VITAL = "agrupacionHechoVital";
	public static final String FILTRO_PROCEDIMIENTO_PUBLICO = "publicoObjetivo";
	public static final String FILTRO_PROCEDIMIENTO_FAMILIA = "familia";
	public static final String FILTRO_PROCEDIMIENTO_AGRUPACION_MATERIA = "agrupacionMateria";
	public static final String FILTRO_PROCEDIMIENTO_MATERIA = "materia";
	public static final String FILTRO_PROCEDIMIENTO_TEXTOS = "textos";
	public static final String FILTRO_PROCEDIMIENTO_TITULO = "titulo";
	public static final String FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_DESDE = "fechaPublicacionDesde";
	public static final String FILTRO_PROCEDIMIENTO_FECHA_PUBLICACION_HASTA = "fechaPublicacionHasta";
	public static final String FILTRO_PROCEDIMIENTO_VIGENTE = "vigente";
	public static final String FILTRO_PROCEDIMIENTO_TELEMATICO = "telematico";
	public static final String FILTRO_PROCEDIMIENTO_CODIGO_SIA = "codigoSIA";
	public static final String FILTRO_PROCEDIMIENTO_ESTADO_SIA = "estadoSIA";
	public static final String FILTRO_PROCEDIMIENTO_FECHA_ACTUALIZACION_SIA = "actualizacionSIA";
	public static final String FILTRO_PROCEDIMIENTO_TRAMITE_TELEMATICO = "tramiteTelematico";
	public static final String FILTRO_PROCEDIMIENTO_VERSION_TRAMITE_TELEMATICO = "versionTramiteTelematico";
	public static final String FILTRO_PROCEDIMIENTO_COMUN = "comun";
	public static final String FILTRO_PROCEDIMIENTO_PLATAFORMA = "plataforma";

	public static final String FILTRO_PLATAFORMAS_CODIGO = "codigo";
	public static final String FILTRO_PLATAFORMAS_IDENTIFICADOR = "identificador";
	public static final String FILTRO_PLATAFORMAS_URL_ACCESO = "urlAcceso";
	public static final String FILTRO_PLATAFORMAS_DESCRIPCION = "descripcion";

	public static final String FILTRO_SERVICIOS_UA = "codigoUA";
	public static final String FILTRO_SERVICIOS_UA_DIR3 = "codigoUADir3";
	public static final String FILTRO_SERVICIOS_UA_DESCENDIENTES = "buscarEnDescendientesUA";
	public static final String FILTRO_SERVICIOS_ACTIVO = "activo";
	public static final String FILTRO_SERVICIOS_ESTADO_UA = "estadoUA";
	public static final String FILTRO_SERVICIOS_AGRUPACION_HECHO_VITAL = "agrupacionHechoVital";
	public static final String FILTRO_SERVICIOS_PUBLICO = "publicoObjetivo";
	public static final String FILTRO_SERVICIOS_FAMILIA = "familia";
	public static final String FILTRO_SERVICIOS_AGRUPACION_MATERIA = "agrupacionMateria";
	public static final String FILTRO_SERVICIOS_MATERIA = "materia";
	public static final String FILTRO_SERVICIOS_TEXTOS = "textos";
	public static final String FILTRO_SERVICIOS_TITULO = "titulo";
	public static final String FILTRO_SERVICIOS_PLATAFORMA = "plataforma";
	public static final String FILTRO_SERVICIOS_PLATAFORMA_CODIGO = "plataformaCodigo";
	public static final String FILTRO_SERVICIOS_PARAMETROS = "parametros";
	public static final String FILTRO_SERVICIOS_FECHA_PUBLICACION_DESDE = "fechaPublicacionDesde";
	public static final String FILTRO_SERVICIOS_FECHA_PUBLICACION_HASTA = "fechaPublicacionHasta";
	public static final String FILTRO_SERVICIOS_VIGENTE = "vigente";
	public static final String FILTRO_SERVICIOS_TELEMATICO = "telematico";
	public static final String FILTRO_SERVICIOS_CODIGO_SIA = "codigoSIA";
	public static final String FILTRO_SERVICIOS_ESTADO_SIA = "estadoSIA";
	public static final String FILTRO_SERVICIOS_FECHA_ACTUALIZACION_SIA = "actualizacionSIA";
	public static final String FILTRO_SERVICIOS_TRAMITE_TELEMATICO = "tramiteTelematico";
	public static final String FILTRO_SERVICIOS_VERSION_TRAMITE_TELEMATICO = "versionTramiteTelematico";
	public static final String FILTRO_SERVICIOS_COMUN = "comun";

	public static final String FILTRO_TRAMITE_UA = "codigoUA";
	public static final String FILTRO_TRAMITE_FASE = "fase";
	public static final String FILTRO_TRAMITE_PROCEDIMIENTO = "codigoProcedimiento";
	public static final String FILTRO_TRAMITE_TRAMITE_TELEMATICO = "codigoTramiteTelematico";
	public static final String FILTRO_TRAMITE_VERSION_TRAMITE_TELEMATICO = "versionTramiteTelematico";
	public static final String FILTRO_TRAMITE_URL_TRAMITE_TELEMATICO = "urlTramiteTelematico";
	public static final String FILTRO_TRAMITE_PLATAFORMA = "plataforma";
	public static final String FILTRO_TRAMITE_PLATAFORMA_CODIGO = "plataformaCodigo";
	public static final String FILTRO_TRAMITE_PARAMETROS = "parametros";

	public FiltroGenerico() {
		this.filtros = new HashMap<String, String>();
		this.columnasOrdenar = new HashMap<String, String>();
	}

	public void addFiltro(final String campo, final String valor) {
		this.filtros.put(campo, valor);
	}

	/**
	 * Retorna el valor del campo o null si no existe
	 *
	 * @param campo
	 * @return
	 */
	public String getValor(final String campo) {
		return this.filtros.get(campo);
	}

	public Set getCampos() {
		return this.filtros.keySet();
	}

	/**
	 * @return the filtros
	 */
	public Map<String, String> getFiltros() {
		return filtros;
	}

	/**
	 * @param filtros
	 *            the filtros to set
	 */
	public void setFiltros(final Map<String, String> filtros) {
		this.filtros = filtros;
	}

	public int getPage() {
		final String page = this.filtros.get(FILTRO_GENERICO_PAGE);
		int res = PAGE_DEFECTO;// valor por defecto
		if (!StringUtils.isEmpty(page)) {
			try {
				res = Integer.parseInt(page);
			} catch (final Exception e) {
				res = PAGE_DEFECTO;
			}
		}
		return res;
	}

	public void setPage(final Integer page) {
		if (page != null && page.intValue() > 0) {
			this.filtros.put(FILTRO_GENERICO_PAGE, page + "");
		}
	}

	public int getPageSize() {
		final String size = this.filtros.get(FILTRO_GENERICO_SIZE);
		int res = SIZE_DEFECTO;
		if (!StringUtils.isEmpty(size)) {
			try {
				res = Integer.parseInt(size);
			} catch (final Exception e) {
				res = SIZE_DEFECTO;
			}
		}
		return res;
	}

	public void setPageSize(final Integer size) {
		if (size != null && size.intValue() > 0) {
			this.filtros.put(FILTRO_GENERICO_SIZE, size + "");
		}
	}

	/**
	 * Retorna el filtro id
	 *
	 * @return 0 si no hay id y >0 si hay un filtro id
	 */
	public Long getId() {
		final String id = this.filtros.get(FILTRO_GENERICO_ID);
		Long res = new Long(0);
		if (!StringUtils.isEmpty(id)) {
			try {
				res = Long.parseLong(id);
			} catch (final Exception e) {
				res = new Long(0);
			}
		}
		return res;
	}

	public void setId(final Long id) {
		if (id != null && id.longValue() > 0) {
			this.filtros.put(FILTRO_GENERICO_ID, id + "");
		}
	}

	public String getLang() {
		String lang = this.filtros.get(FILTRO_GENERICO_LANG);
		if (lang == null || lang.length() != 2) {
			lang = LANG_DEFECTO; // idioma por defecto
		}
		return lang.toLowerCase();
	}

	public void setLang(final String lang) {
		if (lang != null && lang.length() == 2) {
			this.filtros.put(FILTRO_GENERICO_LANG, lang);
		}
	}

	/**
	 * @return the columnasOrdenar
	 */
	public Map<String, String> getColumnasOrdenar() {
		return columnasOrdenar;
	}

	/**
	 * @param columnasOrdenar
	 *            the columnasOrdenar to set
	 */
	public void setColumnasOrdenar(final Map<String, String> columnasOrdenar) {
		this.columnasOrdenar = columnasOrdenar;
	}

	/**
	 * Añade la ordenación por el campo en el orden indicado. Sera orden ascendente
	 * a no ser que se indique this.DESCENDENTE.
	 *
	 * @param campo
	 * @param orden
	 */
	public void addOrden(final String campo, final String orden) {
		this.columnasOrdenar.put(campo, orden.equals(this.DESCENDENTE) ? this.DESCENDENTE : this.ASCENDENTE);
	}

	/**
	 * Retorna la sentencia ORDER BY concatenando los diferentes
	 * prefijoEntidad+campo
	 *
	 * @param prefijoEntidad
	 *            indica la entidad / alias de que dependen los campos
	 * @return
	 */
	public String getOrdenSQL(final String prefijoEntidad) {
		final StringBuilder res = new StringBuilder();
		boolean primero = true;
		for (final Map.Entry<String, String> orden : columnasOrdenar.entrySet()) {
			if (primero) {
				res.append(" ORDER BY ");
				primero = false;
			} else {
				res.append(", ");
			}
			if (!StringUtils.isEmpty(prefijoEntidad)) {
				res.append(prefijoEntidad);
				res.append(".");
			}
			res.append(orden.getKey());
			res.append(" ");
			res.append(orden.getValue());
		}
		return res.toString();
	}

	private static Integer getPropiedadIntegerPositivo(final String propiedad, final Integer valorPorDefecto) {
		final String val = System.getProperty(propiedad);
		if (StringUtils.isEmpty(val)) {
			return valorPorDefecto;
		} else {
			Integer i = valorPorDefecto;
			try {
				i = Integer.parseInt(val);
				if (i < 0) {
					i = valorPorDefecto;
				}
			} catch (final Exception e) {
				i = valorPorDefecto;
			}

			return i;
		}
	}

	private static String getPropiedadlangDefecto(final String valorPorDefecto) {
		final String val = System.getProperty("es.caib.rolsac.api.v2.idiomaPerDefecte");
		if (StringUtils.isEmpty(val) || val.trim().length() != 2) {
			return valorPorDefecto;
		}
		return val.trim();

	}

}
