package es.caib.rolsac.api.v2.edifici.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceEJBStrategy implements EdificiQueryServiceStrategy {

    EdificiQueryServiceDelegate edificiQueryServiceDelegate; 

    public void setEdificiQueryServiceDelegate(EdificiQueryServiceDelegate edificiQueryServiceDelegate) {
        this.edificiQueryServiceDelegate = edificiQueryServiceDelegate;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        return edificiQueryServiceDelegate.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public int getNumUnitatsAdministratives(long id) {
        return edificiQueryServiceDelegate.getNumUnitatsAdministratives(id);
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) {
        return edificiQueryServiceDelegate.obtenirFotoPequenya(idFotoPequenya);
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) {
        return edificiQueryServiceDelegate.obtenirFotoGrande(idFotoGrande);
    }

    public ArxiuDTO obtenirPlano(Long idPlano) {
        return edificiQueryServiceDelegate.obtenirPlano(idPlano);
    }


}
