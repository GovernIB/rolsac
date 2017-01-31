package es.caib.rolsac.api.v2.arxiu.ejb;

import javax.ejb.CreateException;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.Documento;

import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de arxiu.
 *
 * @ejb.bean
 *  name="sac/api/ArxiuQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.arxiu.ejb.ArxiuQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class ArxiuQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = -3538074701846345988L;
            
    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    @Override
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene el archivo.
     * @param idArxiu
     * @return Archivo
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    public Archivo obtenirArxiu(long idArxiu) {

        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.obtenirArxiu(idArxiu);
    }

    /**
     * Obtiene el documento archivo.
     * @param idArxiu
     * @return Documento
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */   
    public Documento getDocumentArchiu(long idArxiu) {

        RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.getDocumentArchiu(idArxiu);
    }

    /**
     * Obtiene el documento tramite.
     * @param idArxiu
     * @return DocumentTramit
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */  
	public DocumentTramit getDocumentTramitArchiu(long idArxiu) {
		RolsacQueryServiceEJB rolsacEJB = new RolsacQueryServiceEJB();
        return rolsacEJB.getDocumentTramitArchiu(idArxiu);
	}
}
