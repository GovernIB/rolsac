package es.caib.rolsac.api.v2.fetVital;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FetVitalServicioCriteria extends BasicCriteria {

    private static final long serialVersionUID = -863107220889793853L;

    private Long idServ;
    private Long idFetVital;
    /**
     * @return the idServ
     */
    public Long getIdServ() {
        return idServ;
    }
    /**
     * @param idServ the idServ to set
     */
    public void setIdServ(Long idServ) {
        this.idServ = idServ;
    }
    /**
     * @return the idFetVital
     */
    public Long getIdFetVital() {
        return idFetVital;
    }
    /**
     * @param idFetVital the idFetVital to set
     */
    public void setIdFetVital(Long idFetVital) {
        this.idFetVital = idFetVital;
    }
    
}
