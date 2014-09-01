package es.caib.rolsac.api.v2.butlleti.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ButlletiByNombreCriteria extends ByStringCriteria {

    public ButlletiByNombreCriteria(String entityAlias) {
        super(entityAlias + ".nombre");
    }

}
