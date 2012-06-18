package es.caib.rolsac.api.v2.tipus.ejb;

public class TipusQueryServiceEJBLocator {
    
    public TipusQueryServiceEJB getTipusQueryServiceEJB(){
        return new TipusQueryServiceEJB();
    }

}
