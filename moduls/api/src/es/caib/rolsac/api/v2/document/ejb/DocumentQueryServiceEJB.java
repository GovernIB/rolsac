package es.caib.rolsac.api.v2.document.ejb;

import java.util.Locale;

import javax.ejb.CreateException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.HibernateEJB;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

/**
 * SessionBean para consultas de boletines.
 *
 * @ejb.bean
 *  name="sac/api/DocumentQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.document.ejb.DocumentQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class DocumentQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 8142069832050964520L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    /**
     * Obtiene la ficha del documento.
     * @param idFitxa
     * @return FitxaDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria criteria = new FitxaCriteria();
        criteria.setId(String.valueOf(idFitxa));
        if (criteria.getIdioma() == null) {
            criteria.setIdioma(Locale.getDefault().getLanguage());
        }
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirFitxa(criteria);
    }

    /**
     * Obtiene el procedimiento del documento.
     * @param idProc
     * @return ProcedimentDTO
     * 
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimentDTO obtenirProcediment(long idProc) {
        ProcedimentCriteria criteria = new ProcedimentCriteria();
        criteria.setId(String.valueOf(idProc));
        if (criteria.getIdioma() == null) {
            criteria.setIdioma(Locale.getDefault().getLanguage());
        }
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirProcediment(criteria);
    }

    /**
     * Obtiene el archivo del documento.
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