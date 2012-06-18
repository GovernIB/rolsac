package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.tramit.TramitDTO;


public interface FormulariQueryServiceStrategy {

    ArxiuDTO obtenirArchivo(Long idArchivo);

    ArxiuDTO obtenirManual(Long idManual);

    TramitDTO obtenirTramit(Long idTramit);

}
