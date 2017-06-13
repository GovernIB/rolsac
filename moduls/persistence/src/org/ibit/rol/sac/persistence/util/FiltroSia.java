package org.ibit.rol.sac.persistence.util;

/**
 * Filtro SIA.
 * @author slromero
 *
 */
public class FiltroSia {
	/** Posibles estados : 0 , 1, -1 o -2. **/
	private Integer estado;
	/** Cuantos elementos a mostrar por pantalla. **/
	private Integer numElementos;
	/** Existe procedimiento o ha sido borrado. **/
	private Integer existe;
	/** Id del elemento. **/
	private Long idElemento;
	/** Tipo de ejecución. PDT (pendiente), TOT (Tots) o TMP (Actualización por timepo) **/
	private String tipo;
	/** Pagina. **/
	private Integer pagina;
	/** Orden campo. */
	private String ordenCampo;
	/** Orden ASC / DESC. **/
	private String ordenAsc;
	
	/**
	 * @return the estado
	 */
	public Integer getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	/**
	 * @return the numElementos
	 */
	public Integer getNumElementos() {
		return numElementos;
	}

	/**
	 * @param numElementos the numElementos to set
	 */
	public void setNumElementos(Integer numElementos) {
		this.numElementos = numElementos;
	}

	/**
	 * @return the existe
	 */
	public Integer getExiste() {
		return existe;
	}

	/**
	 * @param existe the existe to set
	 */
	public void setExiste(Integer existe) {
		this.existe = existe;
	}

	/**
	 * @return the idElemento
	 */
	public Long getIdElemento() {
		return idElemento;
	}

	/**
	 * @param idElemento the idElemento to set
	 */
	public void setIdElemento(Long idElemento) {
		this.idElemento = idElemento;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the pagina
	 */
	public Integer getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	/**
	 * @return the ordenCampo
	 */
	public String getOrdenCampo() {
		return ordenCampo;
	}

	/**
	 * @param ordenCampo the ordenCampo to set
	 */
	public void setOrdenCampo(String ordenCampo) {
		this.ordenCampo = ordenCampo;
	}

	/**
	 * @return the ordenAsc
	 */
	public String getOrdenAsc() {
		return ordenAsc;
	}

	/**
	 * @param ordenAsc the ordenAsc to set
	 */
	public void setOrdenAsc(String ordenAsc) {
		this.ordenAsc = ordenAsc;
	}
	
	
}
