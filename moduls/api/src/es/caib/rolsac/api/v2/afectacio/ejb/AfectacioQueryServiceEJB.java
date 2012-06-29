package es.caib.rolsac.api.v2.afectacio.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioCriteria;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

/**
 * SessionBean para consultas de afectaciones.
 *
 * @ejb.bean
 *  name="sac/api/AfectacioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.afectacio.ejb.AfectacioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class AfectacioQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 6400174718132436776L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene normativa afectante.
     * @param idAfectant
     * @return NormativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public NormativaDTO obtenirAfectant(long idAfectant) {
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setId(String.valueOf(idAfectant));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirNormativa(normativaCriteria);
    }

    /**
     * Obtiene normativa.
     * @param idNormativa
     * @return NormativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public NormativaDTO obtenirNormativa(long idNormativa) {
        NormativaCriteria normativaCriteria = new NormativaCriteria(); 
        normativaCriteria.setId(String.valueOf(idNormativa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirNormativa(normativaCriteria);
    }

    /**
     * Obtiene el tipo de afectacion.
     * @param idTipusAfectacio
     * @return TipusAfectacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) {
        TipusAfectacioCriteria tipusAfectacioCriteria = new TipusAfectacioCriteria();
        tipusAfectacioCriteria.setId(String.valueOf(idTipusAfectacio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirTipusAfectacio(tipusAfectacioCriteria);
    }

}
