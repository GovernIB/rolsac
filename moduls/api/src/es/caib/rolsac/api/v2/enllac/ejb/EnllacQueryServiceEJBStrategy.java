package es.caib.rolsac.api.v2.enllac.ejb;

import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class EnllacQueryServiceEJBStrategy implements EnllacQueryServiceStrategy {

    EnllacQueryServiceDelegate enllacQueryServiceDelegate;

    public void setEnllacQueryServiceDelegate(EnllacQueryServiceDelegate enllacQueryServiceDelegate) {
        this.enllacQueryServiceDelegate = enllacQueryServiceDelegate;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        return enllacQueryServiceDelegate.obtenirFitxa(idFitxa);
    }

    public ProcedimentDTO obtenirProcediment(long idProcediment) {
        return enllacQueryServiceDelegate.obtenirProcediment(idProcediment);
    }

}
