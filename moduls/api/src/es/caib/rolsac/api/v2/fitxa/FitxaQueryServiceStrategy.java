package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentDTO;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacDTO;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface FitxaQueryServiceStrategy {

    int getNumDocuments(long id);

    int getNumEnllacos(long id);

    int getNumFetsVitals(long id);

    int getNumUnitatsAdministratives(long id);
    
    int getNumSeccions(long id);
    
    List<EnllacDTO> llistarEnllacos(long id, EnllacCriteria enllacCriteria);

    List<DocumentDTO> llistarDocuments(long id, DocumentCriteria documentCriteria);

    List<FetVitalDTO> llistarFetsVitals(long id, FetVitalCriteria fetVitalCritera);

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<SeccioDTO> llistarSeccions(long id, SeccioCriteria seccioCriteria);

    ArxiuDTO obtenirIcona(Long icono);

    ArxiuDTO obtenirImatge(Long imagen);

    ArxiuDTO obtenirBaner(Long baner);

}
