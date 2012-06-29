package es.caib.rolsac.api.v2.unitatMateria;

import java.io.Serializable;

public class UnitatMateriaDTO implements Serializable {

    private static final long serialVersionUID = 5040242948316050212L;

    protected long id;
    private Long materia;
    private Long unidad;
    private String unidadPrincipal;

    // TraduccioUnitatMateria
    private String urlUnidadMateria;

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
     * @return the materia
     */
    public Long getMateria() {
        return materia;
    }

    /**
     * @param materia the materia to set
     */
    public void setMateria(Long materia) {
        this.materia = materia;
    }

    /**
     * @return the unidad
     */
    public Long getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(Long unidad) {
        this.unidad = unidad;
    }

    /**
     * @return the unidadPrincipal
     */
    public String getUnidadPrincipal() {
        return unidadPrincipal;
    }

    /**
     * @param unidadPrincipal the unidadPrincipal to set
     */
    public void setUnidadPrincipal(String unidadPrincipal) {
        this.unidadPrincipal = unidadPrincipal;
    }

    /**
     * @return the urlUnidadMateria
     */
    public String getUrlUnidadMateria() {
        return urlUnidadMateria;
    }

    /**
     * @param urlUnidadMateria the urlUnidadMateria to set
     */
    public void setUrlUnidadMateria(String urlUnidadMateria) {
        this.urlUnidadMateria = urlUnidadMateria;
    }

    
}