package org.ibit.rol.sac.model.dto;

import java.io.Serializable;

public class FichaUADTO implements Serializable {

	private static final long serialVersionUID = -1227937193155485933L;

	private Long id;
	private Long idUA;	
	private Long idSec;	
	private Long idFic;
	private String nombreUA;
    private String nombreSec;
    private String nombreFic;
	private int orden;
    private int ordenseccion;
	
	public FichaUADTO() {
		super();
	}
	
	public FichaUADTO(Long id, Long idUA, String nombreUA, Long idSec, String nombreSec, Long idFic, String nombreFic, int orden, int ordenseccion) {
		super();
		this.id = id;
		this.idUA = idUA;		
		this.idSec = idSec;
		this.idFic = idFic;
		this.nombreUA = nombreUA;
        this.nombreSec = nombreSec;
        this.nombreFic = nombreFic;
		this.orden = orden;
		this.ordenseccion = ordenseccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getIdUA() {
        return idUA;
    }

    public void setIdUA(Long idUA) {
        this.idUA = idUA;
    }
    
    public Long getIdSec() {
        return idSec;
    }

    public void setIdSec(Long idSec) {
        this.idSec = idSec;
    }
    
    public Long getIdFic() {
        return idFic;
    }

    public void setIdFic(Long idFic) {
        this.idFic = idFic;
    }
    
    public void setNombreFic(String nombreFic) {
        this.nombreFic = nombreFic;
    }
    
    public String getNombreFic() {
        return nombreFic;
    }
    
    public void setNombreUA(String nombreUA) {
        this.nombreUA = nombreUA;
    }
    
    public String getNombreUA() {
        return nombreUA;
    }

    public void setNombreSec(String nombreSec) {
        this.nombreSec = nombreSec;
    }
    
    public String getNombreSec() {
        return nombreSec;
    }
    
    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
    
    public int getOrdenseccion() {
        return ordenseccion;
    }

    public void setOrdenseccion(int ordenseccion) {
        this.ordenseccion = ordenseccion;
    }
    
}
