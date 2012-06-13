package es.caib.rolsac.api.v2.usuari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UsuariByObservacionesCriteria extends ByStringCriteria {

    public UsuariByObservacionesCriteria(String entityAlias) {
        super(entityAlias + ".observaciones");
    }

}
