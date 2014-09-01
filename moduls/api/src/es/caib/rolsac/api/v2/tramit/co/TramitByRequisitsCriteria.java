package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByRequisitsCriteria extends ByStringCriteria {

    public TramitByRequisitsCriteria(String i18nAlias) {
        super(i18nAlias + ".requisits");
    }

}
