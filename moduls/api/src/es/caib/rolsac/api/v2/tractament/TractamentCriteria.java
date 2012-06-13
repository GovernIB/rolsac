package es.caib.rolsac.api.v2.tractament;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class TractamentCriteria extends BasicCriteria {

    private String codigoEstandar;

    // TraduccioTractament
    private String tipo;
    private String cargoM;
    private String tratamientoM;
    private String cargoF;
    private String tratamientoF;
    
    public String getCodigoEstandar() {
        return codigoEstandar;
    }
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCargoM() {
        return cargoM;
    }
    public void setCargoM(String cargoM) {
        this.cargoM = cargoM;
    }
    public String getTratamientoM() {
        return tratamientoM;
    }
    public void setTratamientoM(String tratamientoM) {
        this.tratamientoM = tratamientoM;
    }
    public String getCargoF() {
        return cargoF;
    }
    public void setCargoF(String cargoF) {
        this.cargoF = cargoF;
    }
    public String getTratamientoF() {
        return tratamientoF;
    }
    public void setTratamientoF(String tratamientoF) {
        this.tratamientoF = tratamientoF;
    }    
    
}
