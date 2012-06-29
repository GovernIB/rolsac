package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.familia.FamiliaCriteria;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.perfil.PerfilCriteria;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de iconos familia.
 *
 * @ejb.bean
 *  name="sac/api/IconaFamiliaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.iconaFamilia.ejb.IconaFamiliaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class IconaFamiliaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -6398078523559530445L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la familia.
     * @param id
     * @return FamiliaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FamiliaDTO obtenirFamilia(long id) {
        FamiliaCriteria famCriteria = new FamiliaCriteria();
        famCriteria.setId(String.valueOf(id));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirFamilia(famCriteria);
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
        return EJBUtils.getArxiuDTO(id);
    }
    
}
