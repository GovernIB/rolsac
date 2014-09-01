package es.caib.rolsac.api.v2.taxa.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

/**
 * SessionBean para consultas de tasas.
 *
 * @ejb.bean
 *  name="sac/api/TaxaQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.taxa.ejb.TaxaQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class TaxaQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 3706201802588841331L;

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
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId(String.valueOf(idTramit));
        return ejb.obtenirTramit(tramitCriteria);
    }

}
