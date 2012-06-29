package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class DocumentTramitCriteria extends BasicCriteria {

    private static final long serialVersionUID = -1813934494875985723L;

    private String tramit;
    private String tipus;
    private String orden;

    // TraduccioDocumentTramit
    private String t_titulo;
    private String t_descripcion;

    public String getT_titulo() {
        return t_titulo;
    }

    public void setT_titulo(String t_titulo) {
        this.t_titulo = t_titulo;
    }

    public String getT_descripcion() {
        return t_descripcion;
    }

    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }

    public String getTramit() {
        return tramit;
    }

    public void setTramit(String tramit) {
        this.tramit = tramit;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

}
