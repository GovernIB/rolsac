package org.ibit.rol.sac.persistence.util;

public class FichaUAFichaIds {

    private long fichaUAId;
    private long fichaId;

    public FichaUAFichaIds() {
    }
    
    public FichaUAFichaIds(long fichaUAId, long fichaId) {
        this.fichaUAId = fichaUAId;
        this.fichaId = fichaId;
    }

    public long getFichaUAId() {
        return fichaUAId;
    }

    public void setFichaUAId(long fichaUAId) {
        this.fichaUAId = fichaUAId;
    }

    public long getFichaId() {
        return fichaId;
    }

    public void setFichaId(long fichaId) {
        this.fichaId = fichaId;
    }

}
