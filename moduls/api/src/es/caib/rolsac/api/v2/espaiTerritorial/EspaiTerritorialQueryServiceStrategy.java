package es.caib.rolsac.api.v2.espaiTerritorial;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface EspaiTerritorialQueryServiceStrategy {

    int getNumFills(long id);

    int getNumUnitatsAdministratives(long id);

    List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria);

    List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativa);

    ArxiuDTO obtenirMapa(Long idMapa);

    ArxiuDTO obtenirLogo(Long idLogo);

    EspaiTerritorialDTO obtenirPare(Long idPadre);
}
