package es.caib.rolsac.api.v2.fitxaUA;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FitxaUACriteria extends BasicCriteria {

    private String orden;
    private String ordenseccion;

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getOrdenseccion() {
        return ordenseccion;
    }

    public void setOrdenseccion(String ordenseccion) {
        this.ordenseccion = ordenseccion;
    }

}
