package es.caib.rolsac.api.v2.enllac.ejb;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;


public class EnllacQueryServiceDelegate {
    
    private EnllacQueryServiceEJBLocator  enllacQueryServiceLocator;

    public void setEnllacQueryServiceLocator(EnllacQueryServiceEJBLocator enllacQueryServiceLocator) {
        this.enllacQueryServiceLocator = enllacQueryServiceLocator;
    }

    public ProcedimentDTO obtenirProcediment(long idProcediment) {
        EnllacQueryServiceEJB ejb = enllacQueryServiceLocator.getEnllacQueryServiceEJB();
        return ejb.obtenirProcediment(idProcediment);
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        EnllacQueryServiceEJB ejb = enllacQueryServiceLocator.getEnllacQueryServiceEJB();
        return ejb.obtenirFitxa(idFitxa);
    }
    
    

}
