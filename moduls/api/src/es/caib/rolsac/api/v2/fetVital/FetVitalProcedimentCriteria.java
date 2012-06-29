package es.caib.rolsac.api.v2.fetVital;

import es.caib.rolsac.api.v2.general.BasicCriteria;

public class FetVitalProcedimentCriteria extends BasicCriteria {

    private static final long serialVersionUID = -863107220889793853L;

    private Long idProc;
    private Long idFetVital;
    /**
     * @return the idProc
     */
    public Long getIdProc() {
        return idProc;
    }
    /**
     * @param idProc the idProc to set
     */
    public void setIdProc(Long idProc) {
        this.idProc = idProc;
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
