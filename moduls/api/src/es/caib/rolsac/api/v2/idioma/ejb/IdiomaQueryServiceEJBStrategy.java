package es.caib.rolsac.api.v2.idioma.ejb;

import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceStrategy;

public class IdiomaQueryServiceEJBStrategy implements IdiomaQueryServiceStrategy {

	private IdiomaQueryServiceDelegate idiomaQueryServiceDelegate;

    public void setIdiomaQueryServiceDelegate(IdiomaQueryServiceDelegate idiomaQueryServiceDelegate) {
        this.idiomaQueryServiceDelegate = idiomaQueryServiceDelegate;
    }

}
