package es.caib.rolsac.api.v2.document;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public interface DocumentQueryServiceStrategy {

    public FitxaDTO obtenirFitxa(long id);

    public ProcedimentDTO obtenirProcediment(long id);
    
    public ArxiuDTO obtenirArxiu(long id);

}
