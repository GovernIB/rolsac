package es.caib.rolsac.api.v2.materiaAgrupacio;

public class MateriaAgrupacioDTO {

    protected Long id;
    private Long materia;
    private Long agrupacion;
    private Integer orden;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMateria() {
        return materia;
    }

    public void setMateria(Long materia) {
        this.materia = materia;
    }

    public Long getAgrupacion() {
        return agrupacion;
    }

    public void setAgrupacion(Long agrupacion) {
        this.agrupacion = agrupacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}
