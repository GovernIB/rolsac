package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentBySilencioCriteria extends ByStringCriteria {

    public ProcedimentBySilencioCriteria(String i18nAlias) {
        super(i18nAlias + ".silencio");
    }

}
