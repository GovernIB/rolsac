package org.ibit.rol.sac.persistence.util;


public class FiltroSia {
	/** Posibles estados : 0 , 1 o -1. **/
	private Integer estado;
	/** Cuantos elementos a mostrar por pantalla. **/
	private Integer numElementos;
	/** Existe procedimiento o ha sido borrado. **/
	private Integer existe;
	/** Id del elemento. **/
	private Long idElemento;
	/** Tipo de ejecución. PDT (pendiente), TOT (Tots) o TMP (Actualización por timepo) **/
	private String tipo;

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
	
	
}
