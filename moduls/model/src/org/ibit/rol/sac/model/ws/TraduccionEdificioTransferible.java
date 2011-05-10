package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

public class TraduccionEdificioTransferible extends AbstractTraduccion implements Serializable {


	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private String descripcion;

}
