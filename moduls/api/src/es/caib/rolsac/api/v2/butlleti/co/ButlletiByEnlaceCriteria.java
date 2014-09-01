package es.caib.rolsac.api.v2.butlleti.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ButlletiByEnlaceCriteria extends ByStringCriteria {

    public ButlletiByEnlaceCriteria(String entityAlias) {
        super(entityAlias + ".enlace");
    }

}
