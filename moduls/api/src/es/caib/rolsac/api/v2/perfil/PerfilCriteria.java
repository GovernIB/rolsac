package es.caib.rolsac.api.v2.perfil;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class PerfilCriteria extends BasicCriteria {

    private String codigoEstandard;
    private String pathIconografia;

    // traducible
    private String t_nombre;
    private String t_descripcion;

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

    public String getT_nombre() {
        return t_nombre;
    }

    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }

    public String getT_descripcion() {
        return t_descripcion;
    }

    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

}
