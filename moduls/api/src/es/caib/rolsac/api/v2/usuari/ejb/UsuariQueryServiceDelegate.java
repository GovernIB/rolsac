package es.caib.rolsac.api.v2.usuari.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class UsuariQueryServiceDelegate {

    private UsuariQueryServiceEJBLocator usuariQueryServiceLocator;

    public void setUsuariQueryServiceLocator(UsuariQueryServiceEJBLocator usuariQueryServiceLocator) {
        this.usuariQueryServiceLocator = usuariQueryServiceLocator;
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id, UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        UsuariQueryServiceEJB ejb = usuariQueryServiceLocator.getUsuariQueryServiceEJB();
        return ejb.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    }

    public int getNumUnitatsAdministratives(long id) {
        UsuariQueryServiceEJB ejb = usuariQueryServiceLocator.getUsuariQueryServiceEJB();
        return ejb.getNumUnitatsAdministratives(id);
    }

}
