package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByDirElectronicaCriteria extends ByStringCriteria {

    public ServicioByDirElectronicaCriteria(String entityAlias) {
        super(entityAlias + ".dirElectronica");
    }

}
