package es.caib.rolsac.api.v2.enllac;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class EnllacCriteria extends BasicCriteria {

    private static final long serialVersionUID = 449769212331134907L;

    private String orden;

    // traducciones
    private String t_titulo;
    private String t_enlace;

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getT_titulo() {
        return t_titulo;
    }

    public void setT_titulo(String t_titulo) {
        this.t_titulo = t_titulo;
    }

    public String getT_enlace() {
        return t_enlace;
    }

    public void setT_enlace(String t_enlace) {
        this.t_enlace = t_enlace;
    }

}
