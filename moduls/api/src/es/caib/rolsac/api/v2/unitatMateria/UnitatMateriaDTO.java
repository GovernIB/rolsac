package es.caib.rolsac.api.v2.unitatMateria;

public class UnitatMateriaDTO {

    protected Long id;
    private String unidadPrincipal;

    // TraduccioUnitatMateria
    private String urlUnidadMateria;

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