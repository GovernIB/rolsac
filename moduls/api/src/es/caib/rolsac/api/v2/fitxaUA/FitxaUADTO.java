package es.caib.rolsac.api.v2.fitxaUA;


public class FitxaUADTO {

    protected Long id;
    private Long unidadAdministrativa;
    private Long ficha;
    private Long seccion;
    private Integer orden;
    private Integer ordenseccion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(Long unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    public Long getFicha() {
        return ficha;
    }

    public void setFicha(Long ficha) {
        this.ficha = ficha;
    }

    public Long getSeccion() {
        return seccion;
    }

    public void setSeccion(Long seccion) {
        this.seccion = seccion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getOrdenseccion() {
        return ordenseccion;
    }

    public void setOrdenseccion(Integer ordenseccion) {
        this.ordenseccion = ordenseccion;
    }

}
