package org.ibit.rol.sac.model;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 01-jun-2007
 * Time: 11:55:28
 * (PORMAD)
 */
public class TraduccionAgrupacionHV implements Traduccion {

    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String palabrasclave;

    public String getPalabrasclave() {
        return palabrasclave;
    }

    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }
}
