package es.caib.rolsac.api.v2.document;

import es.caib.rolsac.api.v2.general.EntitatRemota;

public class DocumentDTO extends EntitatRemota {

    protected Long id;
    private Long orden;

    private Long ficha;
    private Long procedimiento;
    // private Long archivo; // deprecated

    // TraduccioDocument
    private String titulo;
    private String descripcion;
    private Long archivo;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the orden
     */
    public Long getOrden() {
        return orden;
    }

    /**
     * @param ordre
     *            the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public Long getFicha() {
        return ficha;
    }

    public void setFicha(Long ficha) {
        this.ficha = ficha;
    }

    public Long getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Long procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Long getArchivo() {
        return archivo;
    }

    public void setArchivo(Long archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo
     *            the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

}
