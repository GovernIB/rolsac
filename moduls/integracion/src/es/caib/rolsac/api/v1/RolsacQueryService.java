package es.caib.rolsac.api.v1;

import org.ibit.rol.sac.model.Archivo;

/**
 * Interfaz del servicio de exportación.
 * 
 */
public interface RolsacQueryService
{

	@SuppressWarnings("unchecked")
	// public static Class[] types = new Class[]{UnidadAdministrativa.class,
	// Personal.class, Edificio.class, Materia.class, Procedimiento.class };
	public static Class[] types = new Class[] { UnidadAdministrativaDTO.class };

	/**
	 * Metodo que devuelve una unidad administrativa determinada. a partir del codigo
	 * estandar de la misma.
	 * 
	 * @param codigo_estandar
	 *            Codigo estandar de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Una unidad administrativa.
	 */
	public UnidadAdministrativaDTO obtenerUnidadAdministrativaPorCodigoEstandar(
			String codigo_estandar, String idioma);

	/**
	 * Metodo que devuelve una unidad administrativa determinada.
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Una unidad administrativa.
	 */
	public UnidadAdministrativaDTO obtenerUnidadAdministrativa(Long id, String idioma);

	/**
	 * Metodo que devuelve un array con las personas que pertenecen a una determinada
	 * unidad administrativa.
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @return Un array de objetos de clase PersonalDTO.
	 */
	public PersonalDTO[] listarPersonasByUnidadAdm(Long id);

	/**
	 * Metodo que devuelve un array con las ids de las personas que pertenecen a una
	 * determinada unidad administrativa.
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @return Un array de objetos de clase Long.
	 */
	public Long[] listarIdsPersonasByUnidadAdm(Long id);

	/**
	 * Metodo que devuelve una persona determinada.
	 * 
	 * @param id
	 *            Identificador de la persona.
	 * @return Una persona (objeto de la clase PersonalDTO).
	 */
	public PersonalDTO obtenerPersona(Long id);

	/**
	 * Metodo que devuelve un array con los edificios que pertenecen a una determinada
	 * unidad administrativa. En este método, los edificios retornados no incluyen las
	 * unidades administrativas
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un array de objetos de clase EdificioDTO.
	 */
	public EdificioDTO[] listarEdificiosByUnidadAdm(Long id, String idioma);

	/**
	 * Metodo que devuelve un edificio determinado.
	 * 
	 * @param id
	 *            Identificador del edificio.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un edificio.
	 */
	public EdificioDTO obtenerEdificio(Long id, String idioma);

	/**
	 * Metodo que devuelve un array con las materias de una determinada unidad
	 * administrativa.
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un array de objetos de clase MateriaDTO.
	 */
	public MateriaDTO[] listarMateriasByUnidadAdm(Long id, String idioma);

	/**
	 * Metodo que devuelve una materia determinada.
	 * 
	 * @param id
	 *            Identificador de la materia.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Una materia.
	 */
	public MateriaDTO obtenerMateria(Long id, String idioma);

	/**
	 * Metodo que devuelve un array con los procedimientos de una determinada unidad
	 * administrativa.
	 * 
	 * @param id
	 *            Identificador de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un array de objetos de clase ProcedimientoDTO.
	 */
	public ProcedimientoDTO[] listarProcedimientosByUnidadAdm(Long id, String idioma);

	/**
	 * Metodo que devuelve un array con los procedimientos de una determinada unidad
	 * administrativa con respecto a una materia.
	 * 
	 * @param materia_id
	 *            Identificador de la materia
	 * @param ua_id
	 *            Identificador de la unidad administrativa.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un array de objetos de clase ProcedimientoDTO.
	 */
	public ProcedimientoDTO[] listarProcedimientosByMateria(Long materia_id,
			Long ua_id, String idioma);

	/**
	 * Metodo que devuelve un proceimiento determinado.
	 * 
	 * @param id
	 *            Identificador del procedimiento.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un procedimiento.
	 */
	public ProcedimientoDTO obtenerProcedimiento(Long id, String idioma);

	/**
	 * Metodo que devuelve un proceimiento determinado.
	 * 
	 * @param id
	 *            Identificador del procedimiento.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un procedimiento.
	 */
	public ProcedimientoCompletoDTO obtenerProcedimientoCompleto(Long id, String idioma);

	/**
	 * Metodo que devuelve un array con los procedimientos que contengan un texto
	 * determinado
	 * 
	 * @param codigo_estandar
	 *            Codigo estandar de la unidad administrativa.
	 * @param texto
	 *            Cadena de texto a buscar.
	 * @param idioma
	 *            Idioma en el que se devolveran los resultados ("ca", "es", ...)
	 * @return Un array de objetos de clase ProcedimientoDTO.
	 */
	public ProcedimientoDTO[] listarProcedimientosByTexto(String codigo_estandar,
			String texto, String idioma);

	/**
	 * Metodo que devuelve un archivo determinado.
	 * 
	 * @param id
	 *            Identificador del archivo.
	 * @return Un procedimiento.
	 */
	public Archivo obtenerArchivo(Long id);

}