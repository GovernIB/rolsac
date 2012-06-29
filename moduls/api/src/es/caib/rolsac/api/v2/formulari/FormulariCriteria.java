package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FormulariCriteria extends BasicCriteria {

    private static final long serialVersionUID = -4255727252667126060L;

    private String nombre;
    private String url;
    private String urlManual;

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

}
