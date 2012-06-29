package es.caib.rolsac.api.v2.general;

import java.io.Serializable;

public abstract class BasicCriteria implements Serializable {

    private static final long serialVersionUID = -6057984265599944708L;
    
    private String id;
    private String inici;
    private String tamany;
    private String ordenacio; // exemple: "dataActualitzacio ASC, nom DESC, id"
    private String idioma;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInici() {
        return inici;
    }

    public void setInici(String inici) {
        this.inici = inici;
    }

    public String getTamany() {
        return tamany;
    }

    public void setTamany(String tamany) {
        this.tamany = tamany;
    }

    public String getOrdenacio() {
        return ordenacio;
    }

    public void setOrdenacio(String ordenacio) {
        this.ordenacio = ordenacio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

}
