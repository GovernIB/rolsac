package es.caib.rolsac.api.v2.formulari.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

/**
 * SessionBean para consultas de formularios.
 *
 * @ejb.bean
 *  name="sac/api/FormulariQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.formulari.ejb.FormulariQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class FormulariQueryServiceEJB extends HibernateEJB {
    
    private static final long serialVersionUID = 5649830121332287359L;
    
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene el archivo.
     * @param idArchivo
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirArchivo(Long idArchivo) {
        return EJBUtils.getArxiuDTO(idArchivo);
    }

    /**
     * Obtiene el manual.
     * @param idManual
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ArxiuDTO obtenirManual(Long idManual) {
        return EJBUtils.getArxiuDTO(idManual);
    }

    /**
     * Obtiene el tramite.
     * @param idTramit
     * @return TramitDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public TramitDTO obtenirTramit(Long idTramit) {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId(String.valueOf(idTramit));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();        
        return ejb.obtenirTramit(tramitCriteria);
    }

}
