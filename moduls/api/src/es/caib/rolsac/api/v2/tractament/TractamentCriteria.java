package es.caib.rolsac.api.v2.tractament;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class TractamentCriteria extends BasicCriteria {

    private static final long serialVersionUID = -5893947759159245588L;

    private String codigoEstandar;

    // TraduccioTractament
    private String t_tipo;
    private String t_cargoM;
    private String t_tratamientoM;
    private String t_cargoF;
    private String t_tratamientoF;
    
    public String getCodigoEstandar() {
        return codigoEstandar;
    }
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }
    public String getT_tipo() {
        return t_tipo;
    }
    public void setT_tipo(String t_tipo) {
        this.t_tipo = t_tipo;
    }
    public String getT_cargoM() {
        return t_cargoM;
    }
    public void setT_cargoM(String t_cargoM) {
        this.t_cargoM = t_cargoM;
    }
    public String getT_tratamientoM() {
        return t_tratamientoM;
    }
    public void setT_tratamientoM(String t_tratamientoM) {
        this.t_tratamientoM = t_tratamientoM;
    }
    public String getT_cargoF() {
        return t_cargoF;
    }
    public void setT_cargoF(String t_cargoF) {
        this.t_cargoF = t_cargoF;
    }
    public String getT_tratamientoF() {
        return t_tratamientoF;
    }
    public void setT_tratamientoF(String t_tratamientoF) {
        this.t_tratamientoF = t_tratamientoF;
    }
    
}
