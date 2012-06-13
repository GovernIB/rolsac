package es.caib.rolsac.api.v2.agrupacioFetVital;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuDTO;

public interface AgrupacioFetVitalQueryServiceStrategy {

    PublicObjectiuDTO obtenirPublicObjectiu(long idPublic);

    List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCriteria);
   
    ArxiuDTO getFotografia(long idFoto);
    
    ArxiuDTO getIcona(long idIcona);
    
    ArxiuDTO getIconaGran(long idIconaGran);
    
    int getNumFetsVitals(long id);
    
}
