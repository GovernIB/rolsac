package es.caib.rolsac.api.v2.tramit;

import java.util.Date;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class TramitCriteria extends BasicCriteria {
    
    private String fase;
    private String orden;
    private String versio;
    private String validacio;
    
    private Date dataCaducitat;
    private Date dataPublicacio;
    private Date dataActualitzacio;

    private String codiVuds;
    private String descCodiVuds;
    private String idTraTel;
    private String urlExterna;
    private String dataActualitzacioVuds;
    
    // TraduccioTramit
    private String t_nombre;
    private String t_descripcion;
    private String t_documentacion;
    private String t_requisits;
    private String t_observaciones;
    private String t_plazos;
    private String t_lugar;
    /**
     * @return the fase
     */
    public String getFase() {
        return fase;
    }
    /**
     * @param fase the fase to set
     */
    public void setFase(String fase) {
        this.fase = fase;
    }
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
     * @return the versio
     */
    public String getVersio() {
        return versio;
    }
    /**
     * @param versio the versio to set
     */
    public void setVersio(String versio) {
        this.versio = versio;
    }
    /**
     * @return the validacio
     */
    public String getValidacio() {
        return validacio;
    }
    /**
     * @param validacio the validacio to set
     */
    public void setValidacio(String validacio) {
        this.validacio = validacio;
    }
    /**
     * @return the dataCaducitat
     */
    public Date getDataCaducitat() {
        return dataCaducitat;
    }
    /**
     * @param dataCaducitat the dataCaducitat to set
     */
    public void setDataCaducitat(Date dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }
    /**
     * @return the dataPublicacio
     */
    public Date getDataPublicacio() {
        return dataPublicacio;
    }
    /**
     * @param dataPublicacio the dataPublicacio to set
     */
    public void setDataPublicacio(Date dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
    }
    /**
     * @return the dataActualitzacio
     */
    public Date getDataActualitzacio() {
        return dataActualitzacio;
    }
    /**
     * @param dataActualitzacio the dataActualitzacio to set
     */
    public void setDataActualitzacio(Date dataActualitzacio) {
        this.dataActualitzacio = dataActualitzacio;
    }
    /**
     * @return the codiVuds
     */
    public String getCodiVuds() {
        return codiVuds;
    }
    /**
     * @param codiVuds the codiVuds to set
     */
    public void setCodiVuds(String codiVuds) {
        this.codiVuds = codiVuds;
    }

    /**
     * @return the descCodiVuds
     */
    public String getDescCodiVuds() {
        return descCodiVuds;
    }
    /**
     * @param descCodiVuds the descCodiVuds to set
     */
    public void setDescCodiVuds(String descCodiVuds) {
        this.descCodiVuds = descCodiVuds;
    }
    /**
     * @return the idTraTel
     */
    public String getIdTraTel() {
        return idTraTel;
    }
    /**
     * @param idTraTel the idTraTel to set
     */
    public void setIdTraTel(String idTraTel) {
        this.idTraTel = idTraTel;
    }
    /**
     * @return the urlExterna
     */
    public String getUrlExterna() {
        return urlExterna;
    }
    /**
     * @param urlExterna the urlExterna to set
     */
    public void setUrlExterna(String urlExterna) {
        this.urlExterna = urlExterna;
    }
    /**
     * @return the dataActualitzacioVuds
     */
    public String getDataActualitzacioVuds() {
        return dataActualitzacioVuds;
    }
    /**
     * @param dataActualitzacioVuds the dataActualitzacioVuds to set
     */
    public void setDataActualitzacioVuds(String dataActualitzacioVuds) {
        this.dataActualitzacioVuds = dataActualitzacioVuds;
    }
    /**
     * @return the t_nombre
     */
    public String getT_nombre() {
        return t_nombre;
    }
    /**
     * @param t_nombre the t_nombre to set
     */
    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }
    /**
     * @return the t_descripcion
     */
    public String getT_descripcion() {
        return t_descripcion;
    }
    /**
     * @param t_descripcion the t_descripcion to set
     */
    public void setT_descripcion(String t_descripcion) {
        this.t_descripcion = t_descripcion;
    }
    /**
     * @return the t_documentacion
     */
    public String getT_documentacion() {
        return t_documentacion;
    }
    /**
     * @param t_documentacion the t_documentacion to set
     */
    public void setT_documentacion(String t_documentacion) {
        this.t_documentacion = t_documentacion;
    }
    /**
     * @return the t_requisits
     */
    public String getT_requisits() {
        return t_requisits;
    }
    /**
     * @param t_requisits the t_requisits to set
     */
    public void setT_requisits(String t_requisits) {
        this.t_requisits = t_requisits;
    }
    /**
     * @return the t_plazos
     */
    public String getT_plazos() {
        return t_plazos;
    }
    /**
     * @param t_plazos the t_plazos to set
     */
    public void setT_plazos(String t_plazos) {
        this.t_plazos = t_plazos;
    }
    public String getT_observaciones() {
        return t_observaciones;
    }
    public void setT_observaciones(String t_observaciones) {
        this.t_observaciones = t_observaciones;
    }
    /**
     * @return the t_lugar
     */
    public String getT_lugar() {
        return t_lugar;
    }
    /**
     * @param t_lugar the t_lugar to set
     */
    public void setT_lugar(String t_lugar) {
        this.t_lugar = t_lugar;
    }
    
}
