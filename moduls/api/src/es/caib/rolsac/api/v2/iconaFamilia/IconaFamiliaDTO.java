package es.caib.rolsac.api.v2.iconaFamilia;

public class IconaFamiliaDTO {

    protected Long id;
    protected Long familia;
    protected Long perfil;
    protected Long icono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFamilia() {
        return familia;
    }

    public void setFamilia(Long familia) {
        this.familia = familia;
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
