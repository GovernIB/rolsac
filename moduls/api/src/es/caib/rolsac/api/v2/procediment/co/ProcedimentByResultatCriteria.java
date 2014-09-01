package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByResultatCriteria extends ByStringCriteria {

    public ProcedimentByResultatCriteria(String i18nAlias) {
        super(i18nAlias + ".resultat");
    }

}
