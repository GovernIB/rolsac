package es.caib.rolsac.api.v2.fitxaUA.ejb;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceDelegate {

    private FitxaUAQueryServiceEJBLocator fitxaUAQueryServiceLocator;
    
    public void setFitxaUAQueryServiceLocator(FitxaUAQueryServiceEJBLocator fitxaUAQueryServiceLocator) {
        this.fitxaUAQueryServiceLocator = fitxaUAQueryServiceLocator;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaUAQueryServiceEJB ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
        return ejb.obtenirFitxa(idFitxa);
    }

    public SeccioDTO obtenirSeccio(long idSeccio) {
        FitxaUAQueryServiceEJB ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
        return ejb.obtenirSeccio(idSeccio);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) {
        FitxaUAQueryServiceEJB ejb = fitxaUAQueryServiceLocator.getFitxaUAQueryServiceEJB();
        return ejb.obtenirUnitatAdministrativa(idUnitatAdministrativa);
    }

}
