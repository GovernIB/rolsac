package es.caib.rolsac.api.v2.tipusAfectacio;

import java.io.Serializable;

public class TipusAfectacioDTO implements Serializable {
    
    private static final long serialVersionUID = -6030803213788092655L;

    protected long id;
    
    private String nombre;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
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
