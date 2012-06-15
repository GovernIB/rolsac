package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceDelegate {
    
    private EspaiTerritorialQueryServiceEJBLocator espaiTerritorialQueryServiceLocator;

    public void setEspaiTerritorialQueryServiceLocator(EspaiTerritorialQueryServiceEJBLocator espaiTerritorialQueryServiceLocator) {
        this.espaiTerritorialQueryServiceLocator = espaiTerritorialQueryServiceLocator;
    }

    public int getNumFills(long id) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.getNumFills(id);
    }

    public int getNumUnitatsAdministratives(long id) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.getNumUnitatsAdministratives(id);
    }

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.llistarFills(id, espaiTerritorialCriteria);
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.obtenirPare(idPadre);
    }

    public ArxiuDTO obtenirMapa(Long idMapa) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.obtenirMapa(idMapa);
    }

    public ArxiuDTO obtenirLogo(Long idLogo) {
        EspaiTerritorialQueryServiceEJB ejb = espaiTerritorialQueryServiceLocator.getEspaiTerritorialQueryServiceEJB();
        return ejb.obtenirLogo(idLogo);
    }

}
