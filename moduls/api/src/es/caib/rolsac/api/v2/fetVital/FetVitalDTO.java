package es.caib.rolsac.api.v2.fetVital;

import java.io.Serializable;

public class FetVitalDTO implements Serializable {
    
    private static final long serialVersionUID = 8927551632923256635L;

    protected long id;
    private int orden;
    private Long icono;
    private Long iconoGrande;
    private Long foto;
    private String codigoEstandar;
    
    //traduccion    
    private String nombre;
    private String descripcion;
    private String palabrasclave;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }
    /**
     * @param orden the orden to set
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
     * @param codigoEstandar the codigoEstandar to set
     */
    public void setCodigoEstandar(String codigoEstandar) {
        this.codigoEstandar = codigoEstandar;
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
    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

}
