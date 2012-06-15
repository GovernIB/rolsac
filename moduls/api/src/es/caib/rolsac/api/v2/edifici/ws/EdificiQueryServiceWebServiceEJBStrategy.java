package es.caib.rolsac.api.v2.edifici.ws;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceWebServiceEJBStrategy implements EdificiQueryServiceStrategy {

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumUnitatsAdministratives(long id) {
        // TODO Auto-generated method stub
        return 0;
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArxiuDTO obtenirPlano(Long idPlano) {
        // TODO Auto-generated method stub
        return null;
    }

}
