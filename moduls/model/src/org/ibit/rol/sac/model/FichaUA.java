/* Generated by Together */

package org.ibit.rol.sac.model;


public class FichaUA implements ValueObject {
	
	
	//Constructores
	
	public FichaUA() {
		super();
	}
	
	public FichaUA(Long id) {
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

    public UnidadAdministrativa getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(UnidadAdministrativa unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public Seccion getSeccion() {
        return seccion;
    }

    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    private Long id;
    private UnidadAdministrativa unidadAdministrativa;
    private Ficha ficha;
    private Seccion seccion;
    private int orden;
    private int ordenseccion;
    
	public int getOrdenseccion() {
		return ordenseccion;
	}

	public void setOrdenseccion(int ordenseccion) {
		this.ordenseccion = ordenseccion;
	}
}
