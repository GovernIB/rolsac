package es.caib.rolsac.api.v2.butlleti;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class ButlletiCriteria extends BasicCriteria {

    private String nombre;
    private String enlace;

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
