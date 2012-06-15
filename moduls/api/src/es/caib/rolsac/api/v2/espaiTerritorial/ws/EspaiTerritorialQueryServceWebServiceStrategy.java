package es.caib.rolsac.api.v2.espaiTerritorial.ws;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServceWebServiceStrategy implements EspaiTerritorialQueryServiceStrategy {

    public int getNumFills(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public int getNumUnitatsAdministratives(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public List<EspaiTerritorialDTO> llistarFills(long id, EspaiTerritorialCriteria espaiTerritorialCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativa) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirMapa(Long idMapa) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirLogo(Long idLogo) {
        // TODO Auto-generated method stub
        return null;
    }

    public EspaiTerritorialDTO obtenirPare(Long idPadre) {
        // TODO Auto-generated method stub
        return null;
    }



}
