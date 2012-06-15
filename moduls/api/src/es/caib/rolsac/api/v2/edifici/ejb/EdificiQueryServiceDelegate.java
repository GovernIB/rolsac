package es.caib.rolsac.api.v2.edifici.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceDelegate {

    private EdificiQueryServiceEJBLocator edificiQueryServiceLocator;
    
    public void setEdificiQueryServiceLocator(EdificiQueryServiceEJBLocator edificiQueryServiceLocator) {
        this.edificiQueryServiceLocator = edificiQueryServiceLocator;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        EdificiQueryServiceEJB ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public int getNumUnitatsAdministratives(long id) {
        EdificiQueryServiceEJB ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
        return ejb.getNumUnitatsAdministratives(id);
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) {
        EdificiQueryServiceEJB ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
        return ejb.obtenirFotoPequenya(idFotoPequenya);
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) {
        EdificiQueryServiceEJB ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
        return ejb.obtenirFotoGrande(idFotoGrande);
    }

    public ArxiuDTO obtenirPlano(Long idPlano) {
        EdificiQueryServiceEJB ejb = edificiQueryServiceLocator.getEdificiQueryServiceEJB();
        return ejb.obtenirPlano(idPlano);
    }

}
