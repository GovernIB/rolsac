package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByDestinatariosCriteria extends ByStringCriteria {

    public ProcedimentByDestinatariosCriteria(String i18nAlias) {
        super(i18nAlias + ".destinatarios");
    }

}