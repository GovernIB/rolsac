package es.caib.rolsac.api.v2.perfil;

import java.io.Serializable;

public class PerfilDTO implements Serializable {

    private static final long serialVersionUID = -6785991404231347176L;

    protected Long id;
    protected String codigoEstandard;
    protected String pathIconografia;

    // traducible
    private String nombre;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoEstandard() {
        return codigoEstandard;
    }

    public void setCodigoEstandard(String codigoEstandard) {
        this.codigoEstandard = codigoEstandard;
    }

    public String getPathIconografia() {
        return pathIconografia;
    }

    public void setPathIconografia(String pathIconografia) {
        this.pathIconografia = pathIconografia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
