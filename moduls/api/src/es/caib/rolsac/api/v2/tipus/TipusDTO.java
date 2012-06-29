package es.caib.rolsac.api.v2.tipus;

import java.io.Serializable;

public class TipusDTO implements Serializable {

    private static final long serialVersionUID = -742241361300097986L;

    protected Long id;
    private String nombre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

}
