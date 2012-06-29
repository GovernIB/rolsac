package es.caib.rolsac.api.v2.personal.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultar Personal.
 *
 * @ejb.bean
 *  name="sac/api/PersonalQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.personal.ejb.PersonalQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class PersonalQueryServiceEJB extends HibernateEJB {
    
    private static final long serialVersionUID = 8265163410313242938L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la UA.
     * @param idUA
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) {
        UnitatAdministrativaCriteria criteria = new UnitatAdministrativaCriteria();
        criteria.setId(String.valueOf(idUA));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(criteria);
    }

}
