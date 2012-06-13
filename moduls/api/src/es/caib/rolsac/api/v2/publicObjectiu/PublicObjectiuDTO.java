package es.caib.rolsac.api.v2.publicObjectiu;

public class PublicObjectiuDTO {

    protected long id;
    private int orden;
    private String codigoEstandar;

    // TraduccioPublicObjectiu
    private String titulo;
    private String descripcion;
    private String palabrasclave;

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
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden
     *            the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the codigoEstandar
     */
    public String getCodigoEstandar() {
        return codigoEstandar;
    }

    /**
     * @param codigoEstandar
     *            the codigoEstandar to set
     */
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
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

    /**
     * @return the palabrasclave
     */
    public String getPalabrasclave() {
        return palabrasclave;
    }

    /**
     * @param palabrasclave
     *            the palabrasclave to set
     */
    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }

}
