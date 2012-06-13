package es.caib.rolsac.api.v2.fitxaUA.ejb;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceEJBStrategy implements FitxaUAQueryServiceStrategy {

    FitxaUAQueryServiceLocator fitxaUAQueryServiceLocator;
    FitxaUAQueryServiceDelegate fitxaUAQueryServiceDelegate;

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public FitxaDTO obtenirFitxa(long id, FitxaCriteria fitxaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public SeccioDTO obtenirSeccio(long id, SeccioCriteria seccioCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
