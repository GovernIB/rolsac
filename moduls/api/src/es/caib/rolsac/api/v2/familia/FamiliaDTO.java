package es.caib.rolsac.api.v2.familia;

import java.io.Serializable;

public class FamiliaDTO implements Serializable {

    private static final long serialVersionUID = 2119249143177742280L;

    protected Long id;

    // TraduccioFamilia
    private String nombre;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String t_nombre) {
        this.nombre = t_nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String t_descripcion) {
        this.descripcion = t_descripcion;
    }

}
