package es.caib.rolsac.api.v2.edifici;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public interface EdificiQueryService {

    int getNumUnitatsAdministratives();

    List<UnitatAdministrativaQueryService> llistarUnitatsAdministratives(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    
//    private Archivo fotoPequenya;
//    private Archivo fotoGrande;
//    private Archivo plano;

}
