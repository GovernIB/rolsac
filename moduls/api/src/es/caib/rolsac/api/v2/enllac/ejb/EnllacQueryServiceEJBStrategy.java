package es.caib.rolsac.api.v2.enllac.ejb;

import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceStrategy;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class EnllacQueryServiceEJBStrategy implements EnllacQueryServiceStrategy {

    EnllacQueryServiceDelegate enllacQueryServiceDelegate;

    public void setEnllacQueryServiceDelegate(EnllacQueryServiceDelegate enllacQueryServiceDelegate) {
        this.enllacQueryServiceDelegate = enllacQueryServiceDelegate;
    }

    public FitxaDTO obtenirFitxa(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public ProcedimentDTO obtenirProcediment(long id, ProcedimentCriteria procedimentCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
