package es.caib.rolsac.api.v2.fitxa;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.document.DocumentCriteria;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceAdapter;
import es.caib.rolsac.api.v2.enllac.EnllacCriteria;
import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceAdapter;
import es.caib.rolsac.api.v2.fetVital.FetVitalCriteria;
import es.caib.rolsac.api.v2.fetVital.FetVitalQueryServiceAdapter;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface FitxaQueryService {

    int getNumDocuments();

    int getNumEnllacos();

    int getNumFetsVitals();

    int getNumUnitatsAdministratives();

    int getNumSeccions();
    
    List<EnllacQueryServiceAdapter> llistarEnllacos(EnllacCriteria enllacCriteria);

    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    List<DocumentQueryServiceAdapter> llistarDocuments(DocumentCriteria documentCriteria);

    List<FetVitalQueryServiceAdapter> llistarFetsVitals(FetVitalCriteria fetVitalCritera);
    
    List<SeccioQueryServiceAdapter> llistarSeccions(SeccioCriteria seccioCriteria);
    
    ArxiuQueryServiceAdapter obtenirIcona();

    ArxiuQueryServiceAdapter obtenirImatge();

    ArxiuQueryServiceAdapter obtenirBaner();

}
