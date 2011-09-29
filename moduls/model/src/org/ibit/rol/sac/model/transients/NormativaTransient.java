package org.ibit.rol.sac.model.transients;

import org.ibit.rol.sac.model.ValueObject;

public class NormativaTransient implements ValueObject {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private long id;
    private long numero;
    private String titulo;
    private String fecha;
    private String tipo;
	
	public NormativaTransient(long id, long numero, String titulo, String fecha, String tipo) {
	    super();
	    this.id = id;
	    this.numero = numero;
	    this.titulo = titulo;
	    this.fecha = fecha;
	    this.tipo = tipo;
	}

	
	/**
	 * Devuelve el valor de id.
	 *
	 * @return Valor de id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Guarda el valor de id.
	 *
	 * @param id Nuevo valor de id.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Devuelve el valor de numero.
	 *
	 * @return Valor de numero.
	 */
	public long getNumero() {
		return numero;
	}

	/**
	 * Guarda el valor de numero.
	 *
	 * @param numero Nuevo valor de numero.
	 */
	public void setNumero(long numero) {
		this.numero = numero;
	}



	/**
	 * Devuelve el valor de titulo.
	 *
	 * @return Valor de titulo.
	 */
	public String getTitulo() {
		return titulo;
	}


	/**
	 * Guarda el valor de titulo.
	 *
	 * @param titulo Nuevo valor de titulo.
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * Devuelve el valor de fecha.
	 *
	 * @return Valor de fecha.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Guarda el valor de fecha.
	 *
	 * @param fecha Nuevo valor de fecha.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el valor de tipo.
	 *
	 * @return Valor de tipo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Guarda el valor de tipo.
	 *
	 * @param tipo Nuevo valor de tipo.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
	
}
