package org.ibit.rol.sac.model;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 01-jun-2007
 * Time: 11:55:28
 * (PORMAD)
 */
public class TraduccionPublicoObjetivo implements Traduccion {

    private String titulo;
    private String descripcion;
    private String palabrasclave;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String nombre) {
        this.titulo = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPalabrasclave() {
        return palabrasclave;
    }

    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }
}
