package es.caib.rolsac.api.v2.enllac.ejb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class EnllacQueryServiceEJB {

    private static Log log = LogFactory.getLog(EnllacQueryServiceEJB.class);
    
    public ProcedimentDTO obtenirProcediment(long idProcediment) {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(String.valueOf(idProcediment));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirProcediment(procedimentCriteria);
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(String.valueOf(idFitxa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirFitxa(fitxaCriteria);
    }

}
