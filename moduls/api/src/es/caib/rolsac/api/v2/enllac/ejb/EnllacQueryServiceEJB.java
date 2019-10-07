package es.caib.rolsac.api.v2.enllac.ejb;

import java.util.Locale;

import javax.ejb.CreateException;

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
 *  name="sac/api/EnllacQueryServiceEJB"
 *  jndi-name="es.caib.rolsac.api.v2.enllac.ejb.EnllacQueryServiceEJB"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public class EnllacQueryServiceEJB extends HibernateEJB {

    private static final long serialVersionUID = 5728626419374660173L;

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }

    /**
     * Obtiene el procedimiento.
     * @param idProcediment
     * @return ProcedimentDTO
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public ProcedimentDTO obtenirProcediment(long idProcediment) {
        ProcedimentCriteria procedimentCriteria = new ProcedimentCriteria();
        procedimentCriteria.setId(String.valueOf(idProcediment));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirProcediment(procedimentCriteria);
    }

    /**
     * Obtiene la ficha.
     * @param idFitxa
     * @return FitxaDTO
     *
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria fitxaCriteria = new FitxaCriteria();
        fitxaCriteria.setId(String.valueOf(idFitxa));
        if (fitxaCriteria.getIdioma() == null) {
            fitxaCriteria.setIdioma(Locale.getDefault().getLanguage());
        }
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        return ejb.obtenirFitxa(fitxaCriteria);
    }

}
