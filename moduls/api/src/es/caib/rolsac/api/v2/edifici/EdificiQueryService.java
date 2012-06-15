package es.caib.rolsac.api.v2.edifici;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface EdificiQueryService {

    int getNumUnitatsAdministratives();

    List<UnitatAdministrativaQueryServiceAdapter> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    ArxiuQueryServiceAdapter obtenirFotoPequenya();
    
    ArxiuQueryServiceAdapter obtenirFotoGrande();
    
    ArxiuQueryServiceAdapter obtenirPlano();

}
