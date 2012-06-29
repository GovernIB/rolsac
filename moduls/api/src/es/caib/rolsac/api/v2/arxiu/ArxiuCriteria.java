package es.caib.rolsac.api.v2.arxiu;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class ArxiuCriteria extends BasicCriteria {
    
    private static final long serialVersionUID = -1662860986267078700L;

    private String mime;
    private String nombre;
    private String peso;
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
    public String getPeso() {
        return peso;
    }
    /**
     * @param peso the peso to set
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }    
    
    
}
