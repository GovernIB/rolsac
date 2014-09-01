package es.caib.rolsac.api.v2.iconaMateria.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de iconos materia.
 *
 * @ejb.bean
 *  name="sac/api/IconaMateriaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.iconaMateria.ejb.IconaMateriaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class IconaMateriaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -933322257794164158L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la materia.
     * @param id
     * @return MateriaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public MateriaDTO obtenirMateria(long id) {
        MateriaCriteria famCriteria = new MateriaCriteria();
        famCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirMateria(famCriteria);
    }

    /**
     * Obtiene el perfil.
     * @param id
     * @return PerfilDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public PerfilDTO obtenirPerfil(long id) {
        PerfilCriteria perCriteria = new PerfilCriteria();
        perCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirPerfil(perCriteria);
    }

    /**
     * Obtiene el icono.
     * @param id
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirIcona(long id) {
        return getArxiuDTO(id);
    }

}
