package org.ibit.rol.sac.model;


public class FichaResumenUA implements ValueObject, Comparable {
	
	
	//Constructores
	
	public FichaResumenUA() {
		super();
	}
	
	public FichaResumenUA(Long id) {
		super();
		this.id = id;
	}
	
	//get & set
	

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUa() {
        return this.idUa;
    }

    public void setIdUa(Long unidadAdministrativa) {
        this.idUa = unidadAdministrativa;
    }

    public FichaResumen getFicha() {
        return ficha;
    }

    public void setFicha(FichaResumen ficha) {
        this.ficha = ficha;
    }

    public Long getIdSeccio() {
        return idSeccio;
    }

    public void setIdSeccio(Long seccion) {
        this.idSeccio = seccion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    private Long id;
    private Long idUa;
    private FichaResumen ficha;
    private Long idSeccio;
    private int orden;
    private int ordenseccion;
    
	public int getOrdenseccion() {
		return ordenseccion;
	}

	public void setOrdenseccion(int ordenseccion) {
		this.ordenseccion = ordenseccion;
	}

	public int compareTo(Object o) {
		if (o == null)
			return 1;
		
		if ( this.getOrden() == ((FichaResumenUA) o).getOrden() )
			return 0;
		else if (this.getOrden() < ((FichaResumenUA) o).getOrden())
			return -1;
		else
			return 1;		
	}
}
