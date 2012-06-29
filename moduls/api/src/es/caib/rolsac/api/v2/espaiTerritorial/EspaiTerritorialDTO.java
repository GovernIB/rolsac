package es.caib.rolsac.api.v2.espaiTerritorial;

import java.io.Serializable;

public class EspaiTerritorialDTO implements Serializable {

    private static final long serialVersionUID = 3604649889101253134L;

    protected Long id;
    private Integer nivel;
    private Long mapa;
    private Long logo;
    private String coordenadas;
    private Long padre;

    // traducciones
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Long getMapa() {
        return mapa;
    }

    public void setMapa(Long mapa) {
        this.mapa = mapa;
    }

    public Long getLogo() {
        return logo;
    }

    public void setLogo(Long logo) {
        this.logo = logo;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Long getPadre() {
        return padre;
    }

    public void setPadre(Long padre) {
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
