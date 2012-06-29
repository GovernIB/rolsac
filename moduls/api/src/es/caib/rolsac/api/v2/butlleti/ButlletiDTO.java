package es.caib.rolsac.api.v2.butlleti;

import java.io.Serializable;

public class ButlletiDTO implements Serializable {

    private static final long serialVersionUID = -4883456388924607306L;

    protected Long id;
    protected String nombre;
    protected String enlace;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

}
