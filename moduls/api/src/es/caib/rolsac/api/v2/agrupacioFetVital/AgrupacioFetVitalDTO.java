package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.io.Serializable;


public class AgrupacioFetVitalDTO implements Serializable {
    
    private static final long serialVersionUID = 1293918957324592014L;

    protected long id;
    private Long foto;
    private Long iconoGrande;
    private Long icono;
    private String codigoEstandar;
    private Long publico;

    // TraduccioAgrupacioFetVital
    private String nombre;
    private String descripcion;
    private String palabrasclave;
    private Long distribComp;
    private Long contenido;
    private Long normativa;
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
     * @return the publico
     */
    public Long getPublico() {
        return publico;
    }
    /**
     * @param publico the publico to set
     */
    public void setPublico(Long publico) {
        this.publico = publico;
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
     * @return the distribComp
     */
    public Long getDistribComp() {
        return distribComp;
    }
    /**
     * @param distribComp the distribComp to set
     */
    public void setDistribComp(Long distribComp) {
        this.distribComp = distribComp;
    }
    /**
     * @return the contenido
     */
    public Long getContenido() {
        return contenido;
    }
    /**
     * @param contenido the contenido to set
     */
    public void setContenido(Long contenido) {
        this.contenido = contenido;
    }
    /**
     * @return the normativa
     */
    public Long getNormativa() {
        return normativa;
    }
    /**
     * @param normativa the normativa to set
     */
    public void setNormativa(Long normativa) {
        this.normativa = normativa;
    }

}
