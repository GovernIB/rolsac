package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mgonzalez
 * Date: 14-jun-2007
 * Time: 8:24:49
 * Clase que representa la información traducida a transferir de una UA(PORMAD)
 */
public class TraduccionUATransferible extends AbstractTraduccion implements Serializable {


   //TraduccionUA
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    private String nombre;
    private String presentacion;
    private String abreviatura;
    //traduccionUA
}
