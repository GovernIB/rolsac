/* Generated by Together */

package org.ibit.rol.sac.model;

import java.util.Set;

public class Boletin implements ValueObject {

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

    public Set getNormativas() {
        return normativas;
    }

    public void setNormativas(Set normativas) {
        this.normativas = normativas;
    }

    private Long id;
    private String nombre;
    private String enlace;
    private Set normativas;
}
