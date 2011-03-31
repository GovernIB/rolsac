package org.ibit.rol.sac.model;

public class Afectacion implements ValueObject {
    private Normativa afectante = null;
    private Normativa normativa = null;
    private TipoAfectacion tipoAfectacion = null;

    public Normativa getAfectante() {
        return afectante;
    }

    public void setAfectante(Normativa afectante) {
        this.afectante = afectante;
    }

    public Normativa getNormativa() {
        return normativa;
    }

    public void setNormativa(Normativa normativa) {
        this.normativa = normativa;
    }

    public TipoAfectacion getTipoAfectacion() {
        return tipoAfectacion;
    }

    public void setTipoAfectacion(TipoAfectacion tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Afectacion that = (Afectacion) o;

        if (afectante != null ? !afectante.equals(that.afectante) : that.afectante != null) return false;
        if (normativa != null ? !normativa.equals(that.normativa) : that.normativa != null) return false;
        if (tipoAfectacion != null ? !tipoAfectacion.equals(that.tipoAfectacion) : that.tipoAfectacion != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (afectante != null ? afectante.hashCode() : 0);
        result = 29 * result + (normativa != null ? normativa.hashCode() : 0);
        result = 29 * result + (tipoAfectacion != null ? tipoAfectacion.hashCode() : 0);
        return result;
    }
}
