package org.ibit.rol.sac.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class NormativaDTO implements ValueObject, Comparable {

	private static final long serialVersionUID = 3258125847574821172L;
	
	private long id;
    private long numero;
    private String titulo;
    private String fecha;
    private String fecha_boletin;
    private String tipo;
    private String tipologia;
    private Date fechaDate; //para el compareTo
    private Boolean caducat;
	
    public NormativaDTO() {
    	super();
    }
    
	public NormativaDTO(long id, long numero, String titulo, Date fecha, Date fecha_boletin, String tipo, String tipologia, Boolean caducat) {
		
	    super();
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    
	    this.id = id;
	    this.numero = numero;
	    this.titulo = titulo;
	    this.fecha = fecha != null ? df.format(fecha) : "";
	    this.fechaDate = fecha;
	    this.tipo = tipo;
	    this.tipologia = tipologia;
	    this.fecha_boletin = fecha_boletin != null ? df.format(fecha_boletin) : "";
	    this.caducat = caducat;
	}

	/**
	 * Función de comparación para permitir la ordenación de listas de NormativaDTO.
	 * 
	 * @param o Objeto a comparar. Se espera NormativaDTO.
	 * 
	 * @return -1, 0, 1 si el objeto es menor, igual o mayor que el pasado por parámetro.
	 */
	public int compareTo(Object o) {
		NormativaDTO cmp = (NormativaDTO)o;
		if (cmp == null)
			cmp = new NormativaDTO();
			
		if (this.fechaDate == null) {
			if (cmp.fechaDate != null)
				return -1;
			else
				return 0;
		}
		else if (cmp.fechaDate == null)
			return 1;
		
		else if (this.fechaDate.equals(cmp.fechaDate)) {
			return 0;
		}
		else if (this.fechaDate.before(cmp.fechaDate)) {
			return -1;
		}
		else if (this.fechaDate.after(cmp.fechaDate)) {
			return 1;
		} else
			return 0;
	}
	
		
	
	/**
	 * Devuelve el valor de fecha_boletin.
	 *
	 * @return Valor de fecha_boletin.
	 */
	public String getFecha_boletin() {
		return fecha_boletin;
	}

	/**
	 * Guarda el valor de fecha_boletin.
	 *
	 * @param fecha_boletin Nuevo valor de fecha_boletin.
	 */
	public void setFecha_boletin(String fecha_boletin) {
		this.fecha_boletin = fecha_boletin;
	}

	/**
	 * Devuelve el valor de tipologia.
	 *
	 * @return Valor de tipologia.
	 */
	public String getTipologia() {
		return tipologia;
	}

	/**
	 * Guarda el valor de tipologia.
	 *
	 * @param tipologia Nuevo valor de tipologia.
	 */
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
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
	 * Devuelve el valor de fechaDate.
	 *
	 * @return Valor de fechaDate.
	 */
	public Date getFechaDate() {
		return fechaDate;
	}

	/**
	 * Guarda el valor de fechaDate.
	 *
	 * @param fechaDate Nuevo valor de fechaDate.
	 */
	public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
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

	public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}

	
}
