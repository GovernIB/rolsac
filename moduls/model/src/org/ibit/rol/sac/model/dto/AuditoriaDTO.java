
package org.ibit.rol.sac.model.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ibit.rol.sac.model.ValueObject;

public class AuditoriaDTO implements ValueObject, Comparable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6947594123395033731L;

	public AuditoriaDTO(Long id, String usuario, int codigoOperacion, String tituloOperacion, Date fecha) {
		super();
	    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    
	    this.id = id;
	    this.usuario = usuario;
	    this.codigoOperacion = codigoOperacion;
	    this.tituloOperacion = tituloOperacion;
	    this.fecha = fecha != null ? df.format(fecha) : "";
	    this.fechaDate = fecha;
		
	}

	public AuditoriaDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodigoOperacion() {
        return codigoOperacion;
    }

    public void setCodigoOperacion(int codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public void setFechaDate(Date fechaDate) {
		this.fechaDate = fechaDate;
	}

	public Date getFechaDate() {
		return fechaDate;
	}

	public void setTituloOperacion(String tituloOperacion) {
		this.tituloOperacion = tituloOperacion;
	}

	public String getTituloOperacion() {
		return tituloOperacion;
	}

	private Long id;
    private String usuario;
    private String fecha;
    private Date fechaDate;
    private int codigoOperacion;
    private String tituloOperacion;

	/**
	 * Función de comparación para permitir la ordenación de listas de AuditoriaDTO.
	 * 
	 * @param o Objeto a comparar. Se espera AuditoriaDTO.
	 * 
	 * @return -1, 0, 1 si el objeto es menor, igual o mayor que el pasado por parámetro.
	 */
	public int compareTo(Object o) {
		AuditoriaDTO cmp = (AuditoriaDTO)o;
		if (cmp == null)
			cmp = new AuditoriaDTO();
			
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
	

}
