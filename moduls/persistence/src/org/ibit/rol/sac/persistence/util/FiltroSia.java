package org.ibit.rol.sac.persistence.util;


public class FiltroSia {
	/** Posibles estados : 0 , 1 o -1. **/
	public Integer estado;
	/** Cuantos elementos a mostrar por pantalla. **/
	public Integer numElementos;
	/** Tipo de acción. EXISTE o BORRADO. **/
	public Integer tipoAccion;
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
	 * @return the tipoAccion
	 */
	public Integer getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * @param tipoAccion the tipoAccion to set
	 */
	public void setTipoAccion(Integer tipoAccion) {
		this.tipoAccion = tipoAccion;
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
