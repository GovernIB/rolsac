package es.caib.rolsac.api.v2.afectacio;

import java.io.Serializable;

public class AfectacioDTO implements Serializable {
    
    private static final long serialVersionUID = 7012338603153711147L;

    private long afectante;
    private long normativa;
    private long tipoAfectacion;

    /**
     * @return the afectante
     */
    public long getAfectante() {
        return afectante;
    }

    /**
     * @param afectante the afectante to set
     */
    public void setAfectante(long afectante) {
        this.afectante = afectante;
    }

    /**
     * @return the normativa
     */
    public long getNormativa() {
        return normativa;
    }

    /**
     * @param normativa the normativa to set
     */
    public void setNormativa(long normativa) {
        this.normativa = normativa;
    }

    /**
     * @return the tipoAfectacion
     */
    public long getTipoAfectacion() {
        return tipoAfectacion;
    }

    /**
     * @param tipoAfectacion the tipoAfectacion to set
     */
    public void setTipoAfectacion(long tipoAfectacion) {
        this.tipoAfectacion = tipoAfectacion;
    }

}
