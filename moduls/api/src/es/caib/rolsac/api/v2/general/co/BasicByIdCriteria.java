package es.caib.rolsac.api.v2.general.co;

public class BasicByIdCriteria extends ByLongCriteria {

    public BasicByIdCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
