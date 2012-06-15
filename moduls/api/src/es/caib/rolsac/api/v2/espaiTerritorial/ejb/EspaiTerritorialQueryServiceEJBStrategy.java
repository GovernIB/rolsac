package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceEJBStrategy implements EspaiTerritorialQueryServiceStrategy {

    EspaiTerritorialQueryServiceDelegate espaiTerritorialQueryServiceDelegate;

    public void setEspaiTerritorialQueryServiceDelegate(EspaiTerritorialQueryServiceDelegate espaiTerritorialQueryServiceDelegate) {
        this.espaiTerritorialQueryServiceDelegate = espaiTerritorialQueryServiceDelegate;
    }

    public int getNumFills(long id) {
        return espaiTerritorialQueryServiceDelegate.getNumFills(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        return espaiTerritorialQueryServiceDelegate.getNumUnitatsAdministratives(id);
    }

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        return espaiTerritorialQueryServiceDelegate.llistarFills(id, espaiTerritorialCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativa) {
        return espaiTerritorialQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativa);
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) {
        return espaiTerritorialQueryServiceDelegate.obtenirPare(idPadre);
    }

    public ArxiuDTO obtenirMapa(Long idMapa) {
        return espaiTerritorialQueryServiceDelegate.obtenirMapa(idMapa);
    }

    public ArxiuDTO obtenirLogo(Long idLogo) {
        return espaiTerritorialQueryServiceDelegate.obtenirLogo(idLogo);
    }

}
