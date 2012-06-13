package es.caib.rolsac.api.v2.espaiTerritorial;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class EspaiTerritorialCriteria extends BasicCriteria {

    private String nivel; // Integer
    private String coordenadas;

    // traducciones
    private String t_nombre;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getT_nombre() {
        return t_nombre;
    }

    public void setT_nombre(String t_nombre) {
        this.t_nombre = t_nombre;
    }

}
