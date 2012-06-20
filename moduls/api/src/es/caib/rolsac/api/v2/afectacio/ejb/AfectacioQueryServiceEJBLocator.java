package es.caib.rolsac.api.v2.afectacio.ejb;

public class AfectacioQueryServiceEJBLocator {
    
    AfectacioQueryServiceEJB getAfectacioQueryServiceEJB(){
        return new AfectacioQueryServiceEJB();
    }

}
