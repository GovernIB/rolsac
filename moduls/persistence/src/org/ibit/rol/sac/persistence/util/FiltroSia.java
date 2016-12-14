package org.ibit.rol.sac.persistence.util;


public class FiltroSia {
	/** Posibles estados : 0 , 1 o -1. **/
	public Integer estado;
	/** Cuantos elementos a mostrar por pantalla. **/
	public Integer numElementos;
	/** Existe procedimiento o ha sido borrado. **/
	public Integer existe;
	/** Id del elemento. **/
	public Long idElemento;

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
	
	
	
	
}
