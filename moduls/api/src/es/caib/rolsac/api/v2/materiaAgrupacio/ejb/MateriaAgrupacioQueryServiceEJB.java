package es.caib.rolsac.api.v2.materiaAgrupacio.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de materia agrupacion.
 *
 * @ejb.bean
 *  name="sac/api/MateriaAgrupacioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.materiaAgrupacio.ejb.MateriaAgrupacioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class MateriaAgrupacioQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 3111055604433139116L;

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
     * Obtiene la agrupacion materia.
     * @param idAgrupacio
     * @return AgrupacioMateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacio) {
        AgrupacioMateriaCriteria agrupacioMateriaCriteria = new AgrupacioMateriaCriteria();
        agrupacioMateriaCriteria.setId(String.valueOf(idAgrupacio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();        
        return ejb.obtenirAgrupacioMateria(agrupacioMateriaCriteria);
    }

}
