package es.caib.rolsac.api.v2.arxiu;

import java.io.Serializable;


public class ArxiuDTO implements Serializable {

    private static final long serialVersionUID = -8690224615791914641L;

    private long id;
    private String mime;
    private String nombre;
    private long peso;
    
    
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
     * @return the mime
     */
    public String getMime() {
        return mime;
    }
    /**
     * @param mime the mime to set
     */
    public void setMime(String mime) {
        this.mime = mime;
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
     * @return the peso
     */
    public long getPeso() {
        return peso;
    }
    /**
     * @param peso the peso to set
     */
    public void setPeso(long peso) {
        this.peso = peso;
    }
	
}
