package es.caib.rolsac.api.v2.general;

import java.io.Serializable;

/**
 * Classe estesa per aquelles entitats que contenen informacio d'entitat remota (DocumentDTO, NormativaDTO, etc)
 */
public abstract class EntitatRemota implements Serializable {

    private static final long serialVersionUID = 4876890000595499133L;

    private Long idExterno;
    private Long administracionRemota;
    private String urlRemota;

    public Long getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(Long idExterno) {
        this.idExterno = idExterno;
    }

    public Long getAdministracionRemota() {
        return administracionRemota;
    }

    public void setAdministracionRemota(Long administracionRemota) {
        this.administracionRemota = administracionRemota;
    }

    public String getUrlRemota() {
        return urlRemota;
    }

    public void setUrlRemota(String urlRemota) {
        this.urlRemota = urlRemota;
    }

}
