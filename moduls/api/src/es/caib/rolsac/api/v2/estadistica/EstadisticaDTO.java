package es.caib.rolsac.api.v2.estadistica;

import java.util.Date;

public class EstadisticaDTO {

    // Membres a WEBCAIB
    long uo;

    Date data;
    String codi;
    String abr;

    public long getUO() {
        return uo;
    }

    public Date getData() {
        return data;
    }

    public String getCodi() {
        return codi;
    }

    public String getAbr() {
        return abr;
    }

}
