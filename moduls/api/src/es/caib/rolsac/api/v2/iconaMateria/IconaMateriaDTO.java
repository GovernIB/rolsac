package es.caib.rolsac.api.v2.iconaMateria;

import java.io.Serializable;

public class IconaMateriaDTO implements Serializable {

    private static final long serialVersionUID = -8957618695801686076L;

    protected Long id;
    protected Long materia;
    protected Long perfil;
    protected Long icono;

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

    public Long getPerfil() {
        return perfil;
    }

    public void setPerfil(Long perfil) {
        this.perfil = perfil;
    }

    public Long getIcono() {
        return icono;
    }

    public void setIcono(Long icono) {
        this.icono = icono;
    }

}
