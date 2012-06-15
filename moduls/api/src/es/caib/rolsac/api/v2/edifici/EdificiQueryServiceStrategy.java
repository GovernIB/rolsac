package es.caib.rolsac.api.v2.edifici;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface EdificiQueryServiceStrategy {

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

    int getNumUnitatsAdministratives(long id);

    ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya);

    ArxiuDTO obtenirFotoGrande(Long idFotoGrande);

    ArxiuDTO obtenirPlano(Long idPlano);

}
