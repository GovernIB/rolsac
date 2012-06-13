package es.caib.rolsac.api.v2.documentTramit;

public class DocumentTramitDTO {

    protected Long id;
    private Long tramit;
    private Long ordre;
    private Integer tipus;

    // TraduccioDocumentTramit
    private String titulo;
    private String descripcion;
    private Long archivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTramit() {
        return tramit;
    }

    public void setTramit(Long tramit) {
        this.tramit = tramit;
    }

    public Long getOrdre() {
        return ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    public Integer getTipus() {
        return tipus;
    }

    public void setTipus(Integer tipus) {
        this.tipus = tipus;
    }

    public Long getArchivo() {
        return archivo;
    }

    public void setArchivo(Long archivo) {
        this.archivo = archivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
