package es.caib.rolsac.api.v2.unitatMateria.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

/**
 * SessionBean para consultas de unidades materia.
 *
 * @ejb.bean
 *  name="sac/api/UnitatMateriaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.unitatMateria.ejb.UnitatMateriaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class UnitatMateriaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -6865621312967374327L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la materia.
     * @param idMateria
     * @return MateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public MateriaDTO obtenirMateria(Long idMateria) {
        MateriaCriteria materiaCriteria = new MateriaCriteria();
        materiaCriteria.setId(String.valueOf(idMateria));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirMateria(materiaCriteria);
    }

    /**
     * Obtiene la unidad administrativa.
     * @param idUnitat
     * @return UnitatAdministrativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) {
        UnitatAdministrativaCriteria uaCriteria = new UnitatAdministrativaCriteria();
        uaCriteria.setId(String.valueOf(idUnitat));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(uaCriteria);
    }

}
