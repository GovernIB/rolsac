package es.caib.rolsac.api.v2.documentTramit.ejb;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioCriteria;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
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
        return getArxiuDTO(idArxiu);
    }
    
    /**
     * Obtiene el catálogo de documentos.
     * @param idCatalegDocuments
     * @return CatalegDocumentsDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
    public CatalegDocumentsDTO obtenirCatalegDocuments(long idCatalegDocument) {
        CatalegDocumentsCriteria criteria = new CatalegDocumentsCriteria();
        criteria.setId(String.valueOf(idCatalegDocument));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirCatalegDocuments(criteria);
    }    

    /**
     * Obtiene la excepción de documentación.
     * @param idExcepcioDocumentacio
     * @return ExcepcioDocumentacioDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */      
    public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(long idExcepcioDocumentacio) {
        ExcepcioDocumentacioCriteria criteria = new ExcepcioDocumentacioCriteria();
        criteria.setId(String.valueOf(idExcepcioDocumentacio));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirExcepcioDocumentacio(criteria);
    }        
    
}
