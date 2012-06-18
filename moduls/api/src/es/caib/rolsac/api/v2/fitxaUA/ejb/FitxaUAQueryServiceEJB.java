package es.caib.rolsac.api.v2.fitxaUA.ejb;

import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceEJB {

    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(String.valueOf(idFitxa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirFitxa(fitxaCriteria);
    }

    public SeccioDTO obtenirSeccio(long idSeccio) {
        SeccioCriteria seccioCriteria = new SeccioCriteria();
        seccioCriteria.setId(String.valueOf(idSeccio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirSeccio(seccioCriteria);
    }

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) {
        UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
        unitatAdministrativaCriteria.setId(String.valueOf(idUnitatAdministrativa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
    }

}
