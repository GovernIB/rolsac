package es.caib.rolsac.api.v2.tramit;

import java.util.Date;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class TramitDTO extends EntitatRemota {

    protected long id;
    private int fase;
    private Long orden;
    private Integer versio;
    private Long validacio;
    private Long organCompetent;
    private Long procedimiento;

    private Date dataCaducitat;
    private Date dataPublicacio;
    private Date dataActualitzacio;

    private String codiVuds;
    private String descCodiVuds;
    private String idTraTel;
    private String urlExterna;
    private String dataActualitzacioVuds;

    // TraduccioTramit

    private String nombre;
    private String descripcion;
    private String documentacion;
    private String requisits;
    private String plazos;
    private String observaciones;
    private String lugar;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the fase
     */
    public int getFase() {
        return fase;
    }

    /**
     * @param fase
     *            the fase to set
     */
    public void setFase(int fase) {
        this.fase = fase;
    }

    /**
     * @return the orden
     */
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }

    /**
     * @return the versio
     */
    public Integer getVersio() {
        return versio;
    }

    /**
     * @param versio
     *            the versio to set
     */
    public void setVersio(Integer versio) {
        this.versio = versio;
    }

    /**
     * @return the validacio
     */
    public Long getValidacio() {
        return validacio;
    }

    /**
     * @param validacio
     *            the validacio to set
     */
    public void setValidacio(Long validacio) {
        this.validacio = validacio;
    }

    /**
     * @return the dataCaducitat
     */
    public Date getDataCaducitat() {
        return dataCaducitat;
    }

    /**
     * @param dataCaducitat
     *            the dataCaducitat to set
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
     * @param dataPublicacio
     *            the dataPublicacio to set
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
     * @param dataActualitzacio
     *            the dataActualitzacio to set
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
     * @param codiVuds
     *            the codiVuds to set
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
     * @param descCodiVuds
     *            the descCodiVuds to set
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
     * @param idTraTel
     *            the idTraTel to set
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
     * @param urlExterna
     *            the urlExterna to set
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
     * @param dataActualitzacioVuds
     *            the dataActualitzacioVuds to set
     */
    public void setDataActualitzacioVuds(String dataActualitzacioVuds) {
        this.dataActualitzacioVuds = dataActualitzacioVuds;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre
     *            the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the documentacion
     */
    public String getDocumentacion() {
        return documentacion;
    }

    /**
     * @param documentacion
     *            the documentacion to set
     */
    public void setDocumentacion(String documentacion) {
        this.documentacion = documentacion;
    }

    /**
     * @return the requisits
     */
    public String getRequisits() {
        return requisits;
    }

    /**
     * @param requisits
     *            the requisits to set
     */
    public void setRequisits(String requisits) {
        this.requisits = requisits;
    }

    /**
     * @return the plazos
     */
    public String getPlazos() {
        return plazos;
    }

    /**
     * @param plazos
     *            the plazos to set
     */
    public void setPlazos(String plazos) {
        this.plazos = plazos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar
     *            the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the organCompetent
     */
    public Long getOrganCompetent() {
        return organCompetent;
    }

    /**
     * @param organCompetent
     *            the organCompetent to set
     */
    public void setOrganCompetent(Long organCompetent) {
        this.organCompetent = organCompetent;
    }

    /**
     * @return the procedimiento
     */
    public Long getProcedimiento() {
        return procedimiento;
    }

    /**
     * @param procedimiento
     *            the procedimiento to set
     */
    public void setProcedimiento(Long procedimiento) {
        this.procedimiento = procedimiento;
    }

}
