package es.caib.rolsac.api.v2.familia;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FamiliaCriteria extends BasicCriteria {

    private static final long serialVersionUID = 4100965318007021541L;

    // traducciones
    private String t_nombre;
    private String t_descripcion;

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
