package es.caib.rolsac.api.v2.formulari;

import java.io.Serializable;

public class FormulariDTO implements Serializable {

    private static final long serialVersionUID = -8379706035358866465L;

    protected Long id;
    protected String nombre;
    protected String url;
    protected String urlManual;

    protected Long archivo; // Archivo
    protected Long manual; // Archivo
    protected Long tramite; // Tramite

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlManual() {
        return urlManual;
    }

    public void setUrlManual(String urlManual) {
        this.urlManual = urlManual;
    }

    public Long getArchivo() {
        return archivo;
    }

    public void setArchivo(Long archivo) {
        this.archivo = archivo;
    }

    public Long getManual() {
        return manual;
    }

    public void setManual(Long manual) {
        this.manual = manual;
    }

    public Long getTramite() {
        return tramite;
    }

    public void setTramite(Long tramite) {
        this.tramite = tramite;
    }

}
