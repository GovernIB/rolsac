package org.ibit.rol.sac.model.dto;

import java.text.DateFormat;
import java.text.ParseException;
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
    private String boletin;
    private String tipo;
    private String tipologia;
    private Date fechaDate; //para el compareTo
    private Boolean vigente;
    private String registro;
    private String numNormativa;
    private String color;
	
    public NormativaDTO() {
    	super();
    }

	public NormativaDTO(long id, long numero, String titulo, Date fecha, Date fecha_boletin, String boletin, String tipo, String tipologia, Boolean vigente, String numNormativa, String color) {
		
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
	    this.setBoletin(boletin);
	    this.vigente = vigente;
	    this.numNormativa = numNormativa;
	    this.color = color;
	}

	public NormativaDTO(long id, long numero, String titulo, Date fecha, Date fecha_boletin, String tipo, String tipologia, Boolean vigente, String registro) {
		this(id, numero, titulo, fecha, fecha_boletin, null, tipo, tipologia, vigente, "", "");
		this.registro = registro;
	}

	/**
	 * Función de comparación para permitir la ordenación de listas de NormativaDTO.
	 * 
	 * @param o Objeto a comparar. Se espera NormativaDTO.
	 * 
	 * @return -1, 0, 1 si el objeto es menor, igual o mayor que el pasado por par�metro.
	 */
	public int compareTo(Object o) {
		
		//Comparador para fecha bolet�n
		NormativaDTO cmp = (NormativaDTO)o;
		if (cmp == null)
			cmp = new NormativaDTO();
			
		if (this.fecha_boletin == null) {
			if (cmp.fecha_boletin != null)
				return -1;
			else
				return 0;
		}
		else if (cmp.fecha_boletin == null)
			return 1;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
					
			Date fechaBoletinA = sdf.parse(this.fecha_boletin);
			Date fechaBoletinB = sdf.parse(cmp.fecha_boletin);
		
			if (fechaBoletinA.equals(fechaBoletinB))
				return 0;
			else if (fechaBoletinA.before(fechaBoletinB))
				return -1;
			else if (fechaBoletinA.after(fechaBoletinB))
				return 1;			
			
		} catch (ParseException e) {
            return 0;				
		}	
		
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

	public String getBoletin() {
        return boletin;
    }

    public void setBoletin(String boletin) {
        this.boletin = boletin;
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

	/**
	 * Set vigente.
	 * @param vigente
	 */
	public void setVigente(Boolean vigente) {
		this.vigente = vigente;
	}

	/**
	 * Get vigente.
	 * @return vigente.
	 */
	public Boolean getVigente() {
		return vigente;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRegistro() {
		return registro;
	}

	/**
	 * @return the numNormativa
	 */
	public String getNumNormativa() {
		return numNormativa;
	}

	/**
	 * @param numNormativa the numNormativa to set
	 */
	public void setNumNormativa(String numNormativa) {
		this.numNormativa = numNormativa;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
}
