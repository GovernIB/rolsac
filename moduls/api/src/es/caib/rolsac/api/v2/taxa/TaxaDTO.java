package es.caib.rolsac.api.v2.taxa;

public class TaxaDTO {

    protected long id;
    private Long orden;
    private Long tramit;

    // TaduccioTaxa
    private String descripcio;
    private String codificacio;
    private String formaPagament;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Long getTramit() {
        return tramit;
    }

    public void setTramit(Long tramit) {
        this.tramit = tramit;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getCodificacio() {
        return codificacio;
    }

    public void setCodificacio(String codificacio) {
        this.codificacio = codificacio;
    }

    public String getFormaPagament() {
        return formaPagament;
    }

    public void setFormaPagament(String formaPagament) {
        this.formaPagament = formaPagament;
    }

}
