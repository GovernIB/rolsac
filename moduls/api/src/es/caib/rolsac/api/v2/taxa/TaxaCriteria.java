package es.caib.rolsac.api.v2.taxa;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class TaxaCriteria extends BasicCriteria {

    private static final long serialVersionUID = 9195538151902286606L;
    
    private String orden;
    private String tramit;

    // TaduccioTaxa
    private String t_descripcio;
    private String t_codificacio;
    private String t_formaPagament;
    /**
     * @return the orden
     */
    public String getOrden() {
        return orden;
    }
    /**
     * @param orden the orden to set
     */
    public void setOrden(String orden) {
        this.orden = orden;
    }
    /**
     * @return the tramit
     */
    public String getTramit() {
        return tramit;
    }
    /**
     * @param tramit the tramit to set
     */
    public void setTramit(String tramit) {
        this.tramit = tramit;
    }
    /**
     * @return the t_descripcio
     */
    public String getT_descripcio() {
        return t_descripcio;
    }
    /**
     * @param t_descripcio the t_descripcio to set
     */
    public void setT_descripcio(String t_descripcio) {
        this.t_descripcio = t_descripcio;
    }
    /**
     * @return the t_codificacio
     */
    public String getT_codificacio() {
        return t_codificacio;
    }
    /**
     * @param t_codificacio the t_codificacio to set
     */
    public void setT_codificacio(String t_codificacio) {
        this.t_codificacio = t_codificacio;
    }
    /**
     * @return the t_formaPagament
     */
    public String getT_formaPagament() {
        return t_formaPagament;
    }
    /**
     * @param t_formaPagament the t_formaPagament to set
     */
    public void setT_formaPagament(String t_formaPagament) {
        this.t_formaPagament = t_formaPagament;
    }
    
}
