package es.caib.rolsac.api.v2.documentoServicio.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.HibernateEJB;

/**
 * SessionBean para consultas de documentos normativa.
 *
 * @ejb.bean
 *  name="sac/api/DocumentoServicioQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.documentoServicio.ejb.DocumentoServicioQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class DocumentoServicioQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 2814554976475477831L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
	public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /*
     * Obtiene el normativa.
     * @param idNormativa
     * @return NormativaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
/*    public NormativaDTO obtenirNormativa(long idNormativa) {
        NormativaCriteria criteria = new NormativaCriteria();
        criteria.setId(String.valueOf(idNormativa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirNormativa(criteria);
    }*/

    /**
     * Obtiene el archivo.
     * @param idArxiu
     * @return ArxiuDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return getArxiuDTO(idArxiu);
    }
     
    
}
