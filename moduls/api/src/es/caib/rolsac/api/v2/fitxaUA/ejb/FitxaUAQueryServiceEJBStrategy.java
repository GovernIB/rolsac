package es.caib.rolsac.api.v2.fitxaUA.ejb;

import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceEJBStrategy implements FitxaUAQueryServiceStrategy {
    
    FitxaUAQueryServiceDelegate fitxaUAQueryServiceDelegate;    
    
    public void setFitxaUAQueryServiceDelegate(FitxaUAQueryServiceDelegate fitxaUAQueryServiceDelegate) {
        this.fitxaUAQueryServiceDelegate = fitxaUAQueryServiceDelegate;
    }
    
    public FitxaDTO obtenirFitxa(long idFitxa) {
        return fitxaUAQueryServiceDelegate.obtenirFitxa(idFitxa);
    }
    public SeccioDTO obtenirSeccio(long idSeccio) {
        return fitxaUAQueryServiceDelegate.obtenirSeccio(idSeccio);
    }
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) {
        return fitxaUAQueryServiceDelegate.obtenirUnitatAdministrativa(idUnitatAdministrativa);
    }



}
