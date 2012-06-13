package es.caib.rolsac.api.v2.materia;



public class MateriaDTO {

    protected long id;
    private String codiHita;
    private String codigoEstandar;
    private boolean destacada;

    private Long icono;
    private Long iconoGrande;
    private Long foto;
    
    // TraduccioMateria
    
    private String nombre;
    private String descripcion;
    private String palabrasclave;  
    

    /**
     * @return the codiHita
     */
    public String getCodiHita() {
        return codiHita;
    }
    /**
     * @param codiHita the codiHita to set
     */
    public void setCodiHita(String codiHita) {
        this.codiHita = codiHita;
    }
    /**
     * @return the codigoEstandar
     */
    public String getCodigoEstandar() {
        return codigoEstandar;
    }
    /**
     * @param codigoEstandar the codigoEstandar to set
     */
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
    }
    /**
     * @return the destacada
     */
    public boolean isDestacada() {
        return destacada;
    }
    /**
     * @param destacada the destacada to set
     */
    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * @param nombre the nombre to set
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
     * @param descripcion the descripcion to set
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
     * @param palabrasclave the palabrasclave to set
     */
    public void setPalabrasclave(String palabrasclave) {
        this.palabrasclave = palabrasclave;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * @return the icono
     */
    public Long getIcono() {
        return icono;
    }
    /**
     * @param icono the icono to set
     */
    public void setIcono(Long icono) {
        this.icono = icono;
    }
    /**
     * @return the iconoGrande
     */
    public Long getIconoGrande() {
        return iconoGrande;
    }
    /**
     * @param iconoGrande the iconoGrande to set
     */
    public void setIconoGrande(Long iconoGrande) {
        this.iconoGrande = iconoGrande;
    }
    /**
     * @return the foto
     */
    public Long getFoto() {
        return foto;
    }
    /**
     * @param foto the foto to set
     */
    public void setFoto(Long foto) {
        this.foto = foto;
    }
  
    
}
