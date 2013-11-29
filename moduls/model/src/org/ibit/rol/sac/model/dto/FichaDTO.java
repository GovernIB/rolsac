package org.ibit.rol.sac.model.dto;

import java.util.Date;
import java.util.GregorianCalendar;

import org.ibit.rol.sac.model.Validacion;
import org.ibit.rol.sac.model.ValueObject;

/**
 * Bean que representa a una ficha. Modificado para (PORMAD)
 */

public class FichaDTO implements ValueObject {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String titulo;
	private String fechaPublicacion;
	private String fechaCaducidad;
	private String fechaActualizacion;
	private Boolean caducat;
	private Long ordre;
	private Integer validacion;
	private Date caducidad;
	private Date publicacion;
	
	public Long getOrdre() { 
		return ordre; 
	}
  
	public void setOrdre( Long ordre ) {
		this.ordre = ordre;	   
	}
	
	public FichaDTO() {
		super();
	}
	
	public FichaDTO(long id, String titulo, Integer orden, Boolean caducat) {
		
		super();
		
		this.id = id;
		this.titulo = titulo;
		this.ordre = orden.longValue();
		this.caducat = caducat;
	}
	
	public FichaDTO(long id, String titulo, Integer orden, Integer validacion) {
		
		super();
		
		//Revisar
		
		this.id = id;
		this.titulo = titulo;
		this.ordre = orden.longValue();
		this.validacion = validacion;
		this.caducat = !this.isCaducado();
		
	}
	
	public FichaDTO(long id, String titulo, String fechaPublicacion, String fechaCaducidad, String fechaActualizacion, Boolean caducat) {
		
		super();
		
		this.id = id;
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.fechaCaducidad = fechaCaducidad;
		this.fechaActualizacion = fechaActualizacion;
		this.caducat = caducat;
	}
	
	public FichaDTO(long id, String titulo, Integer orden, Integer validacion, Date fechaCaducidad, Date fechaPublicacion) {
		
		super();
		
		this.id = id;
		this.titulo = titulo;
		this.ordre = orden.longValue();
		this.validacion = validacion;
		this.caducidad = fechaCaducidad;
		this.publicacion = fechaPublicacion;
		this.caducat = !this.isCaducado();
		
	}
	
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }    
    
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

	public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public void setCaducat(Boolean caducat) {
		this.caducat = caducat;
	}

	public Boolean getCaducat() {
		return caducat;
	}
	
	public Integer getValidacion() {
		return validacion;
	}

	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}
	
	public Date getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	public Date getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Date publicacion) {
		this.publicacion = publicacion;
	}

	public boolean isCaducado() {
		
		/*GregorianCalendar dataActual = new GregorianCalendar(); 
		Date fechaCaducidad = ( this.caducidad != null ? this.caducidad : null );
		Date fechaPublicacion = ( this.publicacion !=  null ? this.publicacion : null );
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean visible;

		try {
			
			if ( this.getFechaCaducidad() != null )
				fechaCaducidad = sdf.parse( this.getFechaCaducidad() );
			
			if ( this.getFechaPublicacion() != null )
				fechaPublicacion = sdf.parse( this.getFechaPublicacion() );
			
			
		} catch (ParseException e) {
			
			return false;
			
		}

		Boolean esPublic = Validacion.PUBLICA.equals( this.getValidacion() );
		Boolean noCaducat = ( this.getFechaCaducidad() != null && fechaCaducidad.after( dataActual.getTime() ) ) || this.getFechaCaducidad() == null;
		Boolean esPublicat = ( this.getFechaPublicacion() != null && fechaPublicacion.before( dataActual.getTime() ) ) || this.getFechaPublicacion() == null;

		if (esPublic && noCaducat && esPublicat) {
			visible = true;
		} else {			
			visible = false;
		}
		return visible;*/
		
		GregorianCalendar dataActual = new GregorianCalendar(); 
		Boolean visible;

		Boolean esPublic = Validacion.PUBLICA.equals(this.getValidacion());
		Boolean noCaducat = (this.caducidad != null && this.caducidad.after(dataActual.getTime())) || this.caducidad == null;
		Boolean esPublicat =  (this.publicacion != null && this.publicacion.before(dataActual.getTime())) || this.publicacion == null;

		if (esPublic && noCaducat && esPublicat) {
			visible = Boolean.TRUE;
		} else {
			visible = Boolean.FALSE;
		}
		return visible;
		
	}
}