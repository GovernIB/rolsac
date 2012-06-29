package es.caib.rolsac.api.v2.documentTramit.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

/**
 * SessionBean para consultas de documentos tramite.
 *
 * @ejb.bean
 *  name="sac/api/DocumentTramitQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.documentTramit.ejb.DocumentTramitQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class DocumentTramitQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 2814554976475477831L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene el tramite.
     * @param idTramit
     * @return TramitDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public TramitDTO obtenirTramit(long idTramit) {
        TramitCriteria criteria = new TramitCriteria();
        criteria.setId(String.valueOf(idTramit));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirTramit(criteria);
    }

    /**
     * Obtiene el archivo.
     * @param idArxiu
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return EJBUtils.getArxiuDTO(idArxiu);
    }
    
}
