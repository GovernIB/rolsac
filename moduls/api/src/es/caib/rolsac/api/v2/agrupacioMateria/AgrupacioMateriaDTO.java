package es.caib.rolsac.api.v2.agrupacioMateria;


public class AgrupacioMateriaDTO {

    protected Long id;
    private String codigoEstandar;
    private Long seccion;
	
    //Traduccion
    private String nombre;

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
     * @return the seccion
     */
    public Long getSeccion() {
        return seccion;
    }

    /**
     * @param seccion the seccion to set
     */
    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }
    
}
